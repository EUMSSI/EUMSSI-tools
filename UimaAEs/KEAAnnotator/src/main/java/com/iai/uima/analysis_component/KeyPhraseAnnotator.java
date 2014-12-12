package com.iai.uima.analysis_component;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import kea.main.KEAKeyphraseExtractor;
import kea.main.KeyPhrase;
import kea.stemmers.FrenchStemmer;
import kea.stemmers.GermanStemmer;
import kea.stemmers.PorterStemmer;
import kea.stemmers.SpanishStemmer;
import kea.stemmers.Stemmer;
import kea.stopwords.Stopwords;
import kea.stopwords.StopwordsEnglish;
import kea.stopwords.StopwordsFrench;
import kea.stopwords.StopwordsGerman;
import kea.stopwords.StopwordsSpanish;

import org.apache.uima.UimaContext;
import org.apache.uima.fit.component.JCasAnnotator_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.fit.descriptor.ConfigurationParameter;
import org.apache.uima.fit.util.JCasUtil;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.ResourceInitializationException;

import com.iai.uima.jcas.tcas.KeyPhraseAnnotation;

import de.tudarmstadt.ukp.dkpro.core.api.segmentation.type.Lemma;

public class KeyPhraseAnnotator extends JCasAnnotator_ImplBase {

	KEAKeyphraseExtractor ke = new KEAKeyphraseExtractor();

	private Pattern phrasePattern;
	private static String KEA_HOME = System.getProperty("KEA_HOME");

	public static final String PARAM_LANGUAGE = "language";
	@ConfigurationParameter(name = PARAM_LANGUAGE, defaultValue = "en")
	private String LANGUAGE;

	public static final String PARAM_KEYPHRASE_RATIO = "ratioOfKeyPhrases";
	@ConfigurationParameter(name = PARAM_KEYPHRASE_RATIO, defaultValue = "75")
	private int KEAPHRASE_RATIO;

	private Stemmer getStemmer(String lang) {
		return lang.equals("es") ? new SpanishStemmer()
				: lang.equals("fr") ? new FrenchStemmer()
						: lang.equals("en") ? new PorterStemmer() : lang
								.equals("de") ? new GermanStemmer() : null;
	}

	private Stopwords getStopwords(String lang) {
		return lang.equals("es") ? new StopwordsSpanish()
				: lang.equals("fr") ? new StopwordsFrench()
						: lang.equals("en") ? new StopwordsEnglish() : lang
								.equals("de") ? new StopwordsGerman() : null;
	}

	@Override
	public void initialize(UimaContext aContext)
			throws ResourceInitializationException {
		super.initialize(aContext);
		
		ke = new KEAKeyphraseExtractor();

		// A. required arguments (no defaults):

		// 1. Name of the directory -- give the path to your directory with
		// documents
		// documents should be in txt format with an extention "txt".
		// Note: keyphrases with the same name as documents, but extension "key"
		// one keyphrase per line!

		// ke.setDirName(KEA_HOME + "/testdocs/en/test");

		// 3. Name of the vocabulary -- name of the file (without extension)
		// that is stored in VOCABULARIES
		// or "none" if no Vocabulary is used (free keyphrase extraction).
		ke.setVocabulary("none");

		// 4. Format of the vocabulary in 3. Leave empty if vocabulary = "none",
		// use "skos" or "txt" otherwise.
		ke.setVocabularyFormat("");

		// B. optional arguments if you want to change the defaults
		// 5. Encoding of the document
		ke.setEncoding("UTF-8");

		// 6. Language of the document -- use "es" for Spanish, "fr" for French
		// or other languages as specified in your "skos" vocabulary
		ke.setDocumentLanguage(LANGUAGE); // es for Spanish, fr for French

		// 7. Stemmer -- adjust if you use a different language than English or
		// want to alterate results
		// (We have obtained better results for Spanish and French with
		// NoStemmer)
		ke.setStemmer(getStemmer(LANGUAGE));
		// ke.setStemmer(new NoStemmer());
		// 8. Stopwords
		ke.setStopwords(getStopwords(LANGUAGE));

		// 10. Set to true, if you want to compute global dictionaries from the
		// test collection
		ke.setBuildGlobal(false);

		ke.setModelName(KEA_HOME + "/data/models/" + LANGUAGE + "/model");

		try {
			ke.loadModel();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void process(JCas aJCas) throws AnalysisEngineProcessException {

		int numOfwords = aJCas.getDocumentText().split("[^\\s]").length;

		ke.setNumPhrases(numOfwords / KEAPHRASE_RATIO);

		ArrayList<KeyPhrase> keyPhrases = ke.extractKeyphrasesToList(aJCas
				.getDocumentText());

		for (KeyPhrase kp : keyPhrases) {
			phrasePattern = Pattern.compile(kp.getUnstemmed());
			Matcher matcher = phrasePattern.matcher(aJCas.getDocumentText());
			while (matcher.find()) {
				List<Lemma> lemmata = JCasUtil.selectCovered(aJCas,
						Lemma.class, matcher.start(), matcher.end());

				if (lemmata.size() > 0) {
					Lemma first = lemmata.get(0);
					Lemma last = lemmata.get(lemmata.size() - 1);
					KeyPhraseAnnotation annotation = new KeyPhraseAnnotation(
							aJCas);
					annotation.setBegin(first.getBegin());
					annotation.setEnd(last.getEnd());
					annotation.setKeyPhrase(kp.getUnstemmed());
					annotation.setProbability(kp.getProbability());
					annotation.setStem(kp.getStemmed());
					annotation.setRank(kp.getRank());
					annotation.addToIndexes();
				}
			}
		}
	}

	public KEAKeyphraseExtractor getKeyphraseExtractor() {
		return ke;
	}

	public void setKEAKeyphraseExtractor(KEAKeyphraseExtractor ke) {
		this.ke = ke;
	}

}
