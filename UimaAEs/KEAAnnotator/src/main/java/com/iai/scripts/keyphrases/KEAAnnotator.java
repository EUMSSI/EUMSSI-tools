package com.iai.scripts.keyphrases;

import static org.apache.uima.fit.factory.AnalysisEngineFactory.createEngineDescription;
import static org.apache.uima.fit.factory.CollectionReaderFactory.createReaderDescription;

import org.apache.uima.analysis_engine.AnalysisEngineDescription;
import org.apache.uima.collection.CollectionReaderDescription;
import org.apache.uima.fit.pipeline.SimplePipeline;
import org.dbpedia.spotlight.uima.SpotlightAnnotator;

import de.tudarmstadt.ukp.dkpro.core.io.text.TextReader;
import de.tudarmstadt.ukp.dkpro.core.io.xmi.XmiWriter;
import de.tudarmstadt.ukp.dkpro.core.matetools.MateLemmatizer;
import de.tudarmstadt.ukp.dkpro.core.opennlp.OpenNlpPosTagger;
import de.tudarmstadt.ukp.dkpro.core.opennlp.OpenNlpSegmenter;
import de.tudarmstadt.ukp.dkpro.core.stanfordnlp.StanfordNamedEntityRecognizer;

import com.iai.uima.analysis_component.KeyPhraseAnnotator;

public class KEAAnnotator {
	
	private static String lang = "en";
	private static float conf = .5f;
	private static int ratio = 75;
	private static String target = "output/TypeSystem";
	private static String source;
	private static String endpoint = "http://10.10.10.125:2222/rest/annotate";
	
	private static void parseArgs(String [] args){
		int i=0;
		while (i<args.length){
			String current = args[i];
			if (current.startsWith("-"))
				if (current.length()==2){
					switch (current.charAt(1)) {
						case 'l' : lang = args[++i]; break;
						case 'i' : source = args[++i]; break;
						case 'e' : endpoint = args[++i]; break;
						case 'o' : target = args[++i]; break;
						case 'c' : conf = Float.valueOf(args[++i]); break;
						case 'r' : ratio = Integer.valueOf(args[++i]); break;
						default : 	System.err.println("Unrecognized Option: "
														+current);
									System.exit(-1); break;
					}
				}
			else {
				System.err.println("Wrong Parameter: "+current);
				System.exit(-1);
			}
		i++;
		}
	}
	
	public static void main(String[] args) throws Exception {
		
		parseArgs(args);
		
		CollectionReaderDescription reader = createReaderDescription(
				TextReader.class,
				TextReader.PARAM_SOURCE_LOCATION, source,
				TextReader.PARAM_LANGUAGE, lang);
	    
		AnalysisEngineDescription seg = createEngineDescription(OpenNlpSegmenter.class,
				OpenNlpSegmenter.PARAM_LANGUAGE,lang);
		
		AnalysisEngineDescription lem = createEngineDescription(MateLemmatizer.class,
				MateLemmatizer.PARAM_LANGUAGE,lang);
		
		AnalysisEngineDescription pos = createEngineDescription(OpenNlpPosTagger.class,
				OpenNlpPosTagger.PARAM_LANGUAGE,lang);
		
		AnalysisEngineDescription key = createEngineDescription(KeyPhraseAnnotator.class,
				KeyPhraseAnnotator.PARAM_LANGUAGE,lang,
				KeyPhraseAnnotator.PARAM_KEYPHRASE_RATIO,ratio);
		
		AnalysisEngineDescription dbp = createEngineDescription(SpotlightAnnotator.class,
         		SpotlightAnnotator.PARAM_ENDPOINT, endpoint,
         		SpotlightAnnotator.PARAM_CONFIDENCE,conf);
		AnalysisEngineDescription ner = createEngineDescription(StanfordNamedEntityRecognizer.class,
				StanfordNamedEntityRecognizer.PARAM_LANGUAGE,lang);
		
		AnalysisEngineDescription type = createEngineDescription(
				XmiWriter.class, XmiWriter.PARAM_TARGET_LOCATION,target);
		
		SimplePipeline.runPipeline(reader,seg,pos,lem,ner,dbp,key,type);
	}
}