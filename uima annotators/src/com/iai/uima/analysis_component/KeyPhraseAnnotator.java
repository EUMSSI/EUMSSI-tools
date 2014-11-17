package com.iai.uima.analysis_component;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import kea.main.KEAKeyphraseExtractor;
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
import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.fit.descriptor.ConfigurationParameter;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.ResourceInitializationException;

import com.iai.uima.jcas.tcas.KeyPhraseAnnotation;

public class KeyPhraseAnnotator extends JCasAnnotator_ImplBase {

	KEAKeyphraseExtractor ke = new KEAKeyphraseExtractor();
	
	private Pattern phrasePattern;
	private static String KEA_HOME = System.getProperty("KEA_HOME");
	
	public static final String PARAM_LANGUAGE = "language";
	@ConfigurationParameter(name=PARAM_LANGUAGE, defaultValue="en")
	private String LANGUAGE ="en";
	
	public static final String PARAM_NUM_OF_KEYPHRASES = "numOfKeyPhrases";
	@ConfigurationParameter(name=PARAM_NUM_OF_KEYPHRASES, defaultValue="10")
	private int NUM_OF_KEYPHRASES = 10;
	
	private String modelName = KEA_HOME + "/data/models/"
			+ LANGUAGE + "/model";

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

		modelName = KEA_HOME + "/data/models/" + LANGUAGE + "/model";

		ke = new KEAKeyphraseExtractor();

		// A. required arguments (no defaults):

		// 1. Name of the directory -- give the path to your directory with
		// documents
		// documents should be in txt format with an extention "txt".
		// Note: keyphrases with the same name as documents, but extension "key"
		// one keyphrase per line!

		//ke.setDirName(KEA_HOME + "/testdocs/en/test");

		// 2. Name of the model -- give the path to the model
		ke.setModelName(modelName);

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
		//ke.setStemmer(new NoStemmer());
		// 8. Stopwords
		ke.setStopwords(getStopwords(LANGUAGE));

		// 9. Number of Keyphrases to extract
		ke.setNumPhrases(NUM_OF_KEYPHRASES);

		// 10. Set to true, if you want to compute global dictionaries from the
		// test collection
		ke.setBuildGlobal(false);

		try {
			ke.loadModel();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void process(JCas aJCas) throws AnalysisEngineProcessException {

		HashMap<String,HashMap<String,String>> keyPhrases = ke.extractKeyphrasesToMap(aJCas.getDocumentText());
		for (String s : keyPhrases.keySet()) {
			phrasePattern = Pattern.compile(s);
			Matcher matcher = phrasePattern.matcher(aJCas.getDocumentText());
			System.out.println(s);
			while (matcher.find()) {
				KeyPhraseAnnotation annotation = new KeyPhraseAnnotation(aJCas);
				annotation.setBegin(matcher.start());
				annotation.setEnd(matcher.end());
				annotation.setKeyPhrase(s);
				annotation.setProbability(Double.valueOf(keyPhrases.get(s).get("probability")));
				annotation.setStem(keyPhrases.get(s).get("stem"));
				annotation.setRank(Integer.valueOf(keyPhrases.get(s).get("rank")));
				annotation.addToIndexes();
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
