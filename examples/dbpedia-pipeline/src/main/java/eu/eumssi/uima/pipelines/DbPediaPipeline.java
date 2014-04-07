package eu.eumssi.uima.pipelines;


import static org.apache.uima.fit.factory.AnalysisEngineFactory.createEngineDescription;
import static org.apache.uima.fit.factory.CollectionReaderFactory.createReaderDescription;
import static org.apache.uima.fit.util.JCasUtil.select;
import static org.apache.uima.fit.util.JCasUtil.selectCovered;

import org.apache.uima.fit.pipeline.JCasIterable;
import org.apache.uima.jcas.JCas;
import org.dbpedia.spotlight.uima.SpotlightAnnotator;
import org.dbpedia.spotlight.uima.types.JCasResource;

import de.tudarmstadt.ukp.dkpro.core.api.segmentation.type.Sentence;
import de.tudarmstadt.ukp.dkpro.core.api.segmentation.type.Token;
import de.tudarmstadt.ukp.dkpro.core.io.text.TextReader;
import de.tudarmstadt.ukp.dkpro.core.io.xmi.XmiWriter;
import de.tudarmstadt.ukp.dkpro.core.languagetool.LanguageToolSegmenter;

/**
 * In this pipeline, we use dbpedia-spotlight to annotate entities.
 * It is configured to use the public endpoint, but should preferably point to a local one.
 */
public class DbPediaPipeline
{
    public static void main(String[] args) throws Exception
    {
        // Assemble pipeline
        JCasIterable pipeline = new JCasIterable(
                // Read input
                createReaderDescription(TextReader.class,
                        TextReader.PARAM_SOURCE_LOCATION, "input/example.txt", 
                        TextReader.PARAM_LANGUAGE, "en"),
                        
                // Token, Sentence
                createEngineDescription(LanguageToolSegmenter.class),
                
                // Annotate using DBpedia spotlight
                createEngineDescription(SpotlightAnnotator.class,
                		//SpotlightAnnotator.PARAM_ENDPOINT, "http://localhost:2222/rest/annotate"),
                		SpotlightAnnotator.PARAM_ENDPOINT, "http://spotlight.dbpedia.org/rest/annotate"),
                		
                // Write output as XMI for inspection in UIMA CAS Editor
                createEngineDescription(XmiWriter.class,
                        XmiWriter.PARAM_TARGET_LOCATION, "output",
                        XmiWriter.PARAM_TYPE_SYSTEM_FILE, "output/TypeSystem.xml"));
        
        // Run and show results in console
        for (JCas jcas : pipeline) {
            for (Sentence sentence : select(jcas, Sentence.class)) {
                System.out.printf("%n== Sentence ==%n");
                System.out.printf("  %-16s %-10s %-10s %-10s %-10s %n", "TOKEN", "LEMMA", "STEM",
                        "CPOS", "POS");
                for (Token token : selectCovered(Token.class, sentence)) {
                    System.out.printf("  %-16s %n", 
                            token.getCoveredText());
                }
                System.out.printf("%n  -- DBpedia --%n");
                for (JCasResource resource : selectCovered(JCasResource.class, sentence)) {
                    System.out.printf("  %-16s %-10s %-10s %n", 
                    		resource.getCoveredText(),
                    		resource.getURI(),
                    		resource.getTypes());
                }
            }
        }
    }
}
