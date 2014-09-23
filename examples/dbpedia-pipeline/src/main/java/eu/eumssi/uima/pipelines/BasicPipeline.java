package eu.eumssi.uima.pipelines;


import static org.apache.uima.fit.factory.AnalysisEngineFactory.createEngineDescription;
import static org.apache.uima.fit.factory.ExternalResourceFactory.createExternalResourceDescription;
import static org.apache.uima.fit.factory.CollectionReaderFactory.createReaderDescription;
import static org.apache.uima.fit.util.JCasUtil.select;
import static org.apache.uima.fit.util.JCasUtil.selectCovered;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.apache.commons.io.FilenameUtils;
import org.apache.uima.conceptMapper.ConceptMapper;
import org.apache.uima.conceptMapper.support.dictionaryResource.CompiledDictionaryResource_impl;
import org.apache.uima.fit.pipeline.JCasIterable;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.ExternalResourceDescription;
import org.dbpedia.spotlight.uima.SpotlightAnnotator;
import org.dbpedia.spotlight.uima.types.JCasResource;

import de.tudarmstadt.ukp.dkpro.core.api.metadata.type.DocumentMetaData;
import de.tudarmstadt.ukp.dkpro.core.api.segmentation.type.Lemma;
import de.tudarmstadt.ukp.dkpro.core.api.segmentation.type.Sentence;
import de.tudarmstadt.ukp.dkpro.core.api.segmentation.type.Token;
import de.tudarmstadt.ukp.dkpro.core.io.jdbc.JdbcReader;
import de.tudarmstadt.ukp.dkpro.core.io.text.TextReader;
import de.tudarmstadt.ukp.dkpro.core.io.xmi.XmiWriter;
import de.tudarmstadt.ukp.dkpro.core.languagetool.LanguageToolSegmenter;
import de.tudarmstadt.ukp.dkpro.core.opennlp.OpenNlpPosTagger;
import de.tudarmstadt.ukp.dkpro.core.stanfordnlp.StanfordLemmatizer;
import edu.upf.glicom.uima.ts.opinion.Polar;

/**
 * This is a basic analysis pipeline.
 */
public class BasicPipeline
{
	public static void main(String[] args) throws Exception
	{
		ExternalResourceDescription polMapEN = createExternalResourceDescription(
				CompiledDictionaryResource_impl.class,
				"file:///home/mmelero/projects/opinion/CompileDict/dictLIWC2001.dic"); //match with the form
				 //"file:///home/mmelero/projects/opinion/CompileDict/dictSenticNet.dic"); //match with the lemma
				//"file:///home/mmelero/projects/opinion/CompileDict/dictOF.dic"); //match with the lemma
		// Assemble pipeline
		JCasIterable pipeline = new JCasIterable(
				// Read input
				/*createReaderDescription(TextReader.class, 
						TextReader.PARAM_SOURCE_LOCATION, "/home/mmelero/projects/opinion/tweet_sample/en/example.txt",
						TextReader.PARAM_LANGUAGE, "en"),*/
				createReaderDescription(
						JdbcReader.class, 
						JdbcReader.PARAM_DRIVER, "com.mysql.jdbc.Driver",//default parameter
						JdbcReader.PARAM_CONNECTION, "jdbc:mysql://127.0.0.1/",//def
						JdbcReader.PARAM_DATABASE, "sm_RepLab2013",
						JdbcReader.PARAM_USER, "root",
						JdbcReader.PARAM_PASSWORD, "krahloa0",
						JdbcReader.PARAM_QUERY, "SELECT text AS cas_text, tweet_id AS cas_metadata_document_id, 'en' as cas_metadata_language FROM GOLD_crawled_tweets"
						),

						// Token, Sentence
						createEngineDescription(LanguageToolSegmenter.class),

						// Annotate POS
						createEngineDescription(OpenNlpPosTagger.class),

						// Annotate Lemma
						createEngineDescription(StanfordLemmatizer.class),

						// Compute Polarity
						createEngineDescription(
								ConceptMapper.class,
								ConceptMapper.PARAM_DICT_FILE, polMapEN,
								ConceptMapper.PARAM_ANNOTATION_NAME, Polar.class.getCanonicalName(), 
								ConceptMapper.PARAM_ATTRIBUTE_LIST, new String[]{"polarity"},
								ConceptMapper.PARAM_FEATURE_LIST, new String[]{"polarity"},
								ConceptMapper.PARAM_DATA_BLOCK_FS, "uima.tcas.DocumentAnnotation", // can't use DocumentAnnotation.class.getCanonicalName() because it gets the jcas version instead of tcas
								ConceptMapper.PARAM_FINDALLMATCHES, false,
								ConceptMapper.PARAM_ORDERINDEPENDENTLOOKUP, false,
								ConceptMapper.PARAM_SEARCHSTRATEGY, ConceptMapper.PARAMVALUE_CONTIGUOUSMATCH,
								ConceptMapper.PARAM_TOKENANNOTATION, Token.class.getCanonicalName()   //comment for matching with the lemma (SN & OF)
								//ConceptMapper.PARAM_TOKENANNOTATION, Lemma.class.getCanonicalName(), //comment for matching with the form (LIWC)
								//ConceptMapper.PARAM_TOKENTEXTFEATURENAME, "value"               //comment for matching with the form (LIWC)
								),


								// Write output as XMI for inspection in UIMA CAS Editor
								createEngineDescription(
										XmiWriter.class,
										XmiWriter.PARAM_TARGET_LOCATION, "output",
										XmiWriter.PARAM_TYPE_SYSTEM_FILE, "output/TypeSystem.xml"
										)
				);

		// Run and show results in console

		PrintWriter stats_out1 = new PrintWriter(new FileWriter("/home/mmelero/projects/opinion/Benchmarking/statsLIWC2001_sum.txt")); 
		PrintWriter stats_out2 = new PrintWriter(new FileWriter("/home/mmelero/projects/opinion/Benchmarking/statsLIWC2001_ratio.txt")); 
		PrintWriter trace_out = new PrintWriter(new FileWriter("/home/mmelero/projects/opinion/Benchmarking/traceLIWC2001.txt"));
		/*PrintWriter stats_out1 = new PrintWriter(new FileWriter("/home/mmelero/projects/opinion/Benchmarking/statsSenticNet_sum.txt")); 
		PrintWriter stats_out2 = new PrintWriter(new FileWriter("/home/mmelero/projects/opinion/Benchmarking/statsSenticNet_ratio.txt")); 
		PrintWriter trace_out = new PrintWriter(new FileWriter("/home/mmelero/projects/opinion/Benchmarking/traceSenticNet.txt"));*/
		/*PrintWriter stats_out1 = new PrintWriter(new FileWriter("/home/mmelero/projects/opinion/Benchmarking/statsOF_sum.txt")); 
		PrintWriter stats_out2 = new PrintWriter(new FileWriter("/home/mmelero/projects/opinion/Benchmarking/statsOF_ratio.txt")); 
		PrintWriter trace_out = new PrintWriter(new FileWriter("/home/mmelero/projects/opinion/Benchmarking/traceOF.txt"));*/
		for (JCas jcas : pipeline) {
			double tot_pol = 0;
			String final_pol = "Null";
			String final_pol2 = "Null";
			int pos_words = 0;
			int neg_words = 0;
			//for (DocumentMetaData meta: select(jcas, DocumentMetaData.class)) {
			//System.out.println("document id: " + getRelativePath(jcas));

			//}
			trace_out.println("document id: " + getRelativePath(jcas));
			for (Polar polar : select(jcas, Polar.class)) {
				trace_out.println("polar: "+ polar.getCoveredText() + " " + polar.getPolarity());
				if (Double.parseDouble(polar.getPolarity()) >= 0.5) {pos_words = pos_words + 1;}
				else if (Double.parseDouble(polar.getPolarity()) <= -0.5) {neg_words = neg_words + 1;}
				tot_pol = tot_pol + Double.parseDouble(polar.getPolarity());
			}

			if (tot_pol < 0.5 && tot_pol > -0.5) {final_pol = "NEUTRAL";}
			else if (tot_pol >= 0.5) {final_pol = "POSITIVE";}
			else if (tot_pol <= -0.5) {final_pol = "NEGATIVE";}

			if (pos_words > neg_words){final_pol2 = "POSITIVE";}
			else if (pos_words < neg_words) {final_pol2 = "NEGATIVE";}
			else {final_pol2 = "NEUTRAL";}

			trace_out.println("Total polarity (summation): " + tot_pol + " (" + final_pol + ")");
			stats_out1.println(getRelativePath(jcas) + "\t" + final_pol);
			stats_out2.println(getRelativePath(jcas) + "\t" + final_pol2);

			//System.out.println("document id: " + getRelativePath(jcas));
		}
		stats_out1.close();
		stats_out2.close();
		trace_out.close();
		System.out.println("Finished!!");
	}
	
	/**
	 * Get the relative path from the CAS. If the CAS does not contain relative path information or
	 * if {@link #PARAM_USE_DOCUMENT_ID} is set, the document ID is used.
	 *
	 * @param aJCas a CAS.
	 * @return the relative target path.
	 */
	static String getRelativePath(JCas aJCas)
	{
		DocumentMetaData meta = DocumentMetaData.get(aJCas);
		if (meta.getDocumentId()!=null) {
			return meta.getDocumentId();
		}
		String baseUri = meta.getDocumentBaseUri();
		String docUri = meta.getDocumentUri();

		String relativeDocumentPath;
		if ((docUri == null) || !docUri.startsWith(baseUri)) {
			throw new IllegalStateException("Base URI [" + baseUri
					+ "] is not a prefix of document URI [" + docUri + "]");
		}
		relativeDocumentPath = docUri.substring(baseUri.length());
		relativeDocumentPath = FilenameUtils.removeExtension(relativeDocumentPath);
		return relativeDocumentPath;
	}
}
