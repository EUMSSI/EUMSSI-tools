package com.iai.scripts.keyphrases;

import static org.apache.uima.fit.factory.AnalysisEngineFactory.createAnalysisEngineDescriptionFromPath;
import static org.apache.uima.fit.factory.AnalysisEngineFactory.createEngineDescription;
import static org.apache.uima.fit.factory.CollectionReaderFactory.createReaderDescription;

import org.apache.uima.analysis_engine.AnalysisEngineDescription;
import org.apache.uima.collection.CollectionReaderDescription;
import org.apache.uima.fit.component.CasDumpWriter;
import org.apache.uima.fit.pipeline.SimplePipeline;

import de.tudarmstadt.ukp.dkpro.core.io.text.TextReader;
import de.tudarmstadt.ukp.dkpro.core.io.xmi.XmiWriter;
import de.tudarmstadt.ukp.dkpro.core.matetools.MateLemmatizer;
import de.tudarmstadt.ukp.dkpro.core.stanfordnlp.StanfordSegmenter;

public class KEAAnnotator {
	public static void main(String[] args) throws Exception {
		
		System.setProperty("KEA_HOME", "../kea-5.0");
		
		CollectionReaderDescription reader = createReaderDescription(
				TextReader.class,
				TextReader.PARAM_SOURCE_LOCATION, "../examples/dbpedia-pipeline/input/review.txt", 
				TextReader.PARAM_LANGUAGE, "en");
	    
		AnalysisEngineDescription key = createAnalysisEngineDescriptionFromPath("desc/KeyPhraseDescriptor.xml");
		AnalysisEngineDescription seg = createEngineDescription(
				StanfordSegmenter.class);
		
		AnalysisEngineDescription type = createEngineDescription(
				XmiWriter.class, XmiWriter.PARAM_TARGET_LOCATION,
				"output/TypeSystem");
		
		AnalysisEngineDescription cc = createEngineDescription(
				CasDumpWriter.class, CasDumpWriter.PARAM_OUTPUT_FILE,
				"output/review.txt");

		SimplePipeline.runPipeline(reader, seg, key, type);
	}
}