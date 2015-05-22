package eu.eumssi.uima.mallet.lda;

import static org.apache.uima.fit.util.JCasUtil.select;
import static org.apache.uima.fit.util.JCasUtil.selectCovered;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;

import org.apache.commons.lang.ArrayUtils;
import org.apache.uima.UimaContext;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.fit.component.JCasAnnotator_ImplBase;
import org.apache.uima.fit.descriptor.ConfigurationParameter;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.cas.DoubleArray;
import org.apache.uima.jcas.cas.IntegerArray;
import org.apache.uima.jcas.cas.StringArray;
import org.apache.uima.resource.ResourceInitializationException;

import cc.mallet.grmm.types.FactorGraph;
import cc.mallet.grmm.util.Models;
import cc.mallet.pipe.Pipe;
import cc.mallet.pipe.TokenSequence2FeatureSequence;
import cc.mallet.topics.ParallelTopicModel;
import cc.mallet.topics.TopicInferencer;
import cc.mallet.types.Alphabet;
import cc.mallet.types.IDSorter;
import cc.mallet.types.Instance;
import cc.mallet.types.InstanceList;
import cc.mallet.types.TokenSequence;
import de.tudarmstadt.ukp.dkpro.core.api.metadata.type.DocumentMetaData;
import de.tudarmstadt.ukp.dkpro.core.api.segmentation.type.Sentence;
import de.tudarmstadt.ukp.dkpro.core.api.segmentation.type.Token;
import eu.eumssi.uima.jcas.MalletTopicAnnotation;

/**
 * Infers the topic distribution over documents using a Mallet
 * {@link ParallelTopicModel}.
 *
 * @author Carsten Schnober, Merlin Lang
 */
public class MalletTopicInferer extends JCasAnnotator_ImplBase {
	private static final String NONE_LABEL = "X";

	public final static String PARAM_MODEL_LOCATION = "model";
	@ConfigurationParameter(name = PARAM_MODEL_LOCATION, mandatory = true)
	private File modelLocation;

	/**
	 * The annotation type to use as tokens. Default: {@link Token}
	 */
	public final static String PARAM_TYPE_NAME = "typeName";
	@ConfigurationParameter(name = PARAM_TYPE_NAME, mandatory = true, defaultValue = "de.tudarmstadt.ukp.dkpro.core.api.segmentation.type.Token")
	private String typeName;

	/**
	 * The number of iterations during inference. Default: 10.
	 */
	public final static String PARAM_N_ITERATIONS = "nIterations";
	@ConfigurationParameter(name = PARAM_N_ITERATIONS, mandatory = true, defaultValue = "10")
	private int nIterations;

	/**
	 * The number of iterations before hyperparameter optimization begins.
	 * Default: 1
	 */
	public final static String PARAM_BURN_IN = "burnIn";
	@ConfigurationParameter(name = PARAM_BURN_IN, mandatory = true, defaultValue = "1")
	private int burnIn;

	public final static String PARAM_THINNING = "thinning";
	@ConfigurationParameter(name = PARAM_THINNING, mandatory = true, defaultValue = "5")
	private int thinning;

	/**
	 * Minimum topic proportion for the document-topic assignment.
	 */
	public final static String PARAM_MIN_TOPIC_PROB = "minTopicProb";
	@ConfigurationParameter(name = PARAM_MIN_TOPIC_PROB, mandatory = true, defaultValue = "0.2")
	private double minTopicProb;

	/**
	 * Maximum number of topics to assign. If not set (or &lt;= 0), the number
	 * of topics in the model will be set.
	 */
	public final static String PARAM_MAX_TOPIC_ASSIGNMENTS = "maxTopicAssignments";
	@ConfigurationParameter(name = PARAM_MAX_TOPIC_ASSIGNMENTS, mandatory = true, defaultValue = "5")
	private int maxTopicAssignments;

	/**
	 * If set, uses lemmas instead of original text as features.
	 */
	public static final String PARAM_USE_LEMMA = "useLemma";
	@ConfigurationParameter(name = PARAM_USE_LEMMA, mandatory = true, defaultValue = "false")
	private boolean useLemma;

	/**
	 * Ignore tokens (or lemmas, respectively) that are shorter than the given
	 * value. Default: 3.
	 */
	public static final String PARAM_MIN_TOKEN_LENGTH = "minTokenLength";
	@ConfigurationParameter(name = PARAM_MIN_TOKEN_LENGTH, mandatory = true, defaultValue = "3")
	private int minTokenLength;

	private TopicInferencer inferencer;
	private ParallelTopicModel model;
	private Pipe malletPipe;

	@Override
	public void initialize(UimaContext context)
			throws ResourceInitializationException {
		super.initialize(context);

		try {
			model = ParallelTopicModel.read(modelLocation);
			inferencer = model.getInferencer();
			if (maxTopicAssignments <= 0) {
				maxTopicAssignments = model.getNumTopics();
			}
		} catch (Exception e) {
			throw new ResourceInitializationException(e);
		}
		malletPipe = new TokenSequence2FeatureSequence();
	};

	@Override
	public void process(JCas aJCas) throws AnalysisEngineProcessException {
		/* convert tokens (or other annotation type) into a Mallet TokenSequence */
		TokenSequence tokenStream = new TokenSequence();

		MalletTopicAnnotation annotation = new MalletTopicAnnotation(aJCas);
		
		for (Sentence sentence : select(aJCas, Sentence.class)) {
			for (Token t : selectCovered(Token.class, sentence)) {
				if (useLemma) {
					String text = t.getLemma().getValue();
					if (text.length() >= minTokenLength) {
						tokenStream.add(text);
					}
				} else {
					String text = t.getCoveredText();
					if (text.length() >= minTokenLength) {
						tokenStream.add(text);
					}
				}
			}
		}

		/* create Mallet Instance */
		DocumentMetaData metadata = DocumentMetaData.get(aJCas);
		Instance instance = new Instance(tokenStream, NONE_LABEL,
				metadata.getDocumentId(), metadata.getDocumentUri());

		Alphabet dataAlphabet = model.getAlphabet();

		/* infer topic distribution across document */
		double[] topicDistribution = inferencer.getSampledDistribution(
				malletPipe.instanceFrom(instance), nIterations, thinning,
				burnIn);
		
		/* convert data type */
		DoubleArray da = new DoubleArray(aJCas, topicDistribution.length);
		da.copyFromArray(topicDistribution, 0, 0, topicDistribution.length);

		/* assign topics to document according to topic distribution */
		int[] assignedTopicIndexes = assignTopics(topicDistribution);
		
		IntegerArray topicIndexes = new IntegerArray(aJCas,
				assignedTopicIndexes.length);
		topicIndexes.copyFromArray(assignedTopicIndexes, 0, 0,
				assignedTopicIndexes.length);

		ArrayList<TreeSet<IDSorter>> topicSortedWords = model.getSortedWords();
		
		String [] topics = new String[assignedTopicIndexes.length];
		
		int i = 0;
		for (int topic : assignedTopicIndexes) {
            Iterator<IDSorter> iterator = topicSortedWords.get(topic).iterator();
            int rank = 0;
            topics[i] = new String();
            while (iterator.hasNext() && rank < 5) {
                IDSorter idCountPair = iterator.next();
                topics[i] += dataAlphabet.lookupObject(idCountPair.getID()).toString() + " ";
                rank++;	
            }
            topics[i] = topics[i].trim();
            i++;
        }
		
		StringArray sa = new StringArray(aJCas, assignedTopicIndexes.length);
		sa.copyFromArray(topics, 0, 0, assignedTopicIndexes.length);
		
		annotation.setAssignedTopicIndexes(topicIndexes);
		annotation.setTopicDistribution(da);
		annotation.setAssignedTopics(sa);
		
		aJCas.addFsToIndexes(annotation);
	}

	/**
	 * Assign topics according to the following formula:
	 * <p>
	 * Topic proportion must be at least the maximum topic's proportion divided
	 * by the maximum number of topics to be assigned. In addition, the topic
	 * proportion must not lie under the minTopicProb. If more topics comply
	 * with these criteria, only retain the n (maxTopicAssignments) largest
	 * values.
	 *
	 * @param topicDistribution
	 *            a double array containing the document's topic proportions
	 * @return an array of integers pointing to the topics assigned to the
	 *         document
	 */
	private int[] assignTopics(final double[] topicDistribution) {
		/*
		 * threshold is the largest value divided by the maximum number of
		 * topics or the fixed number set as minTopicProb parameter.
		 */
		double threshold = 0;
		/*Math.max(
				Collections.max(Arrays.asList(ArrayUtils
						.toObject(topicDistribution))) / maxTopicAssignments,
				minTopicProb);
		*/
		/*
		 * assign indexes for values that are above threshold
		 */
		List<Integer> indexes = new ArrayList<>(topicDistribution.length);
		for (int i = 0; i < topicDistribution.length; i++) {
			if (topicDistribution[i] >= threshold) {
				indexes.add(i);
			}
		}

		/*
		 * Reduce assignments to maximum number of allowed assignments.
		 */
		if (indexes.size() > maxTopicAssignments) {

			/* sort index list by corresponding values */
			Collections.sort(indexes, new Comparator<Integer>() {
				@Override
				public int compare(Integer aO1, Integer aO2) {
					return Double.compare(topicDistribution[aO1],
							topicDistribution[aO2]);
				}
			});

			while (indexes.size() > maxTopicAssignments) {
				indexes.remove(0);
			}
		}

		return ArrayUtils.toPrimitive(indexes.toArray(new Integer[indexes
				.size()]));
	}
}
