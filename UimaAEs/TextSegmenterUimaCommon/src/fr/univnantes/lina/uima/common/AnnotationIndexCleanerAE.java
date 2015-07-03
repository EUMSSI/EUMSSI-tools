package fr.univnantes.lina.uima.common;

/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Vector;
import org.apache.uima.UimaContext;
import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.CAS;
import org.apache.uima.cas.CASException;
import org.apache.uima.cas.FSIterator;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.text.AnnotationIndex;
import org.apache.uima.examples.SourceDocumentInformation;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.tcas.Annotation;
import org.apache.uima.resource.ResourceInitializationException;
import org.apache.uima.resource.ResourceProcessException;
import org.apache.uima.util.CasToInlineXml;

import fr.univnantes.lina.java.util.JavaUtilities;
import fr.univnantes.lina.uima.common.AnnotationCollectionUtils;
import fr.univnantes.lina.uima.common.AnnotationUtils;
import fr.univnantes.lina.uima.common.CommonAE;
import fr.univnantes.lina.uima.common.DocumentAnnotationUtils;
import fr.univnantes.lina.uima.common.FeatureUtils;
import fr.univnantes.lina.uima.common.JCasSofaViewUtils;
import fr.univnantes.lina.uima.common.UIMAUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;



/**
 * Remove duplicate and subsumed annotations from given annotations
 * 
 * <p>
 * This AE takes two parameters:
 * <ul>
 * <li><code>SubsumingAnnotation</code> - type name of the subsuming annotation. Mandatory</li>
 * <li><code>SubsumedAnnotation</code> - type name of the subsumed annotation. Optional, by default would be equal to the SubsumingAnnotation</li>
 * <li><code>StrictOffset</code> - boolean value to specify if the process should only care about duplicate (i.e. SubsumingAnnotation and SubsumedAnnotation at the same offset) </li>
 * </ul>
 * 
 * 
 */
public class AnnotationIndexCleanerAE extends CommonAE {

	/*
	 * PARAMETERS NAMES
	 */
	/**
	 * Parameter name of the SubsumingAnnotation 
	 */
	public static final String PARAM_SUBSUMING_ANNOTATION = "SubsumingAnnotation";
	/**
	 * Parameter name of the SubsumedAnnotation
	 */
	public static final String PARAM_SUBSUMED_ANNOTATION = "SubsumedAnnotation";
	/**
	 * Parameter name of StrictOffset
	 */
	public static final String PARAM_STRICT_OFFSET = "StrictOffset";
	
	/*
	 * DEFAULT VALUES
	 */

	private static final boolean DEFAULT_STRICT_OFFSET = false;


	/*
	 * LOCAL VARIABLES
	 */
	private String subsumingAnnotationName;
	private String subsumedAnnotationName;
	private boolean strictOffset;
	/*
	 * ACCESSORS
	 */


	/*
	 * METHODS 
	 */

	/**
	 * Get the parameter values, setting the variables and checking the values 
	 */
	public void initialize(UimaContext aContext) throws ResourceInitializationException {

		// SUPER PARAMETER SETTINGS
		super.initialize(aContext);

		// CURRENT AE PARAMETER SETTINGS
		subsumingAnnotationName = ((String) aContext.getConfigParameterValue(PARAM_SUBSUMING_ANNOTATION));

		subsumedAnnotationName = ((String) aContext.getConfigParameterValue(PARAM_SUBSUMED_ANNOTATION));
		if (subsumedAnnotationName == null) subsumedAnnotationName = subsumingAnnotationName;
		
		strictOffset = ((Boolean) aContext.getConfigParameterValue(PARAM_STRICT_OFFSET));
		//if (strictOffset. == null) strictOffset = DEFAULT_STRICT_OFFSET;

		//try {
		//} catch (Exception e) {
		//	String errmsg = "!";
		//	throw new ResourceInitializationException(errmsg, new Object[] {});
		//}		
	}

	/**
	 * 
	 */
	@Override
	protected String processContextAnnotation(JCas inputViewJCas,
			FSIterator contextAnnotationsFSIter, Annotation contextAnnotation,
			FSIterator contextualizedInputAnnotationsFSIter,
			String inputFeatureString, JCas outputViewJCas,
			String outputAnnotationString, String ouputFeatureString)
					throws AnalysisEngineProcessException {

		// remove...
		  //removeDuplicateAnnotations(JCas inputViewJCas), removeDuplicateAndSubsumedAnnotations(JCas inputViewJCas, String subsumingAnnotation)
		AnnotationCollectionUtils.removeSubsumedAnnotation(inputViewJCas, subsumingAnnotationName, subsumedAnnotationName, strictOffset);

		//
		return contextAnnotation.getCoveredText();
	}


}




