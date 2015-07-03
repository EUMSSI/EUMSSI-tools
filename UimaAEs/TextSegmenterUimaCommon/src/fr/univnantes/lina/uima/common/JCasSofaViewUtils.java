/** 
 * UIMA Common
 * Copyright (C) 2010  Nicolas Hernandez
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *     http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package fr.univnantes.lina.uima.common;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.CASException;
import org.apache.uima.cas.CASRuntimeException;
import org.apache.uima.cas.ConstraintFactory;
import org.apache.uima.cas.FSIndex;
import org.apache.uima.cas.FSIntConstraint;
import org.apache.uima.cas.FSIterator;
import org.apache.uima.cas.FSMatchConstraint;
import org.apache.uima.cas.FSTypeConstraint;
import org.apache.uima.cas.Feature;
import org.apache.uima.cas.FeaturePath;
import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.text.AnnotationIndex;
import org.apache.uima.examples.SourceDocumentInformation;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.cas.FSArray;
import org.apache.uima.jcas.tcas.Annotation;
import org.apache.uima.jcas.tcas.DocumentAnnotation;

import fr.univnantes.lina.java.util.JavaUtilities;

/**
 * Part of the UIMA Utilities dedicated to handle
 * <p>
 * <ul>
 * <li>jcas</li>
 * <li>sofas </li>
 * <li>and views</li>
 * </ul>
 * </p>
 * 
 * @author hernandez
 *
 */
public class JCasSofaViewUtils  {

	/**
	 * Name of the default SourceDocumentInformation
	 */
	private static String DEFAULT_SOURCE_DOCUMENT_INFORMATION_ANNOTATION = "org.apache.uima.examples.SourceDocumentInformation";

	/**
	 * Name of the default DocumentAnnotation
	 */
	private static String DEFAULT_DOCUMENT_ANNOTATION = "org.apache.uima.jcas.tcas.DocumentAnnotation";

	/**
	 * Name of the current class
	 */
	private static String CURRENTCLASSNAME = "SofaViewUtilities";


	/**
	 * Return the view of a jcas corresponding to the given view name
	 * @param aJCas
	 * @param viewNameString string
	 * @return viewJCas
	 * @throws AnalysisEngineProcessException
	 */
	public static JCas getView(JCas aJCas, String viewNameString) throws AnalysisEngineProcessException {
		JCas viewJCas = null;
		try {
			viewJCas = aJCas.getView(viewNameString);
		} catch (CASException exception) {
			String errmsg = "ERROR: The view " + viewNameString
			+ " does not exist in the JCAS!";
			throw new AnalysisEngineProcessException(errmsg,
					new Object[] { viewNameString },exception);
			// http://uima.apache.org/downloads/releaseDocs/2.3.0-incubating/docs/api/org/apache/uima/analysis_engine/AnalysisEngineProcessException.html
			// http://uima.apache.org/downloads/releaseDocs/2.3.0-incubating/docs/api/constant-values.html#org.apache.uima.UIMAException.STANDARD_MESSAGE_CATALOG
		}
		return viewJCas;
	}



	/**
	 * This method create a view.
	 * 
	 * @param aJCas
	 *            the CAS over which the process is performed
	 * @param outputViewString
	 * @param sofaDataString
	 * @param sofaDataStringTypeMimeString

	 * @throws AnalysisEngineProcessException 
	 */
	public static void createView(JCas aJCas, String outputViewString, String sofaDataString, String sofaDataStringTypeMimeString) throws AnalysisEngineProcessException {

		try {
			aJCas.createView(outputViewString);
			JCas outputView = JCasSofaViewUtils.getView(aJCas, outputViewString);
			//outputView.setDocumentText(commandResultString);
			outputView.setSofaDataString(sofaDataString,sofaDataStringTypeMimeString);
	

		} catch (CASException e) {
			String errmsg = "Error: Cannot create the view "+outputViewString +" !";
			throw new AnalysisEngineProcessException(errmsg,
					new Object[] {  },e);	
			// e.printStackTrace();
		}
	}


	/**
	 * Get the type of a given annotation name and check if it exists
	 * @param aJCas
	 * @param annotationString
	 * @return annotationType
	 * @throws AnalysisEngineProcessException
	 */
	public static Type getJCasType(JCas aJCas, String annotationString) throws AnalysisEngineProcessException {

		// récupère le type context à partir de la String le désignant
		// et vérifie son existence dans le Type System
		Type annotationType = null; 
		annotationType = aJCas.getTypeSystem().getType(
				annotationString);
		// On s'assure que le type existe bien
		if ((annotationType == null)) {
			String errmsg = "Error: Type " + annotationString
			+ " is not defined in the Type System !";
			throw new AnalysisEngineProcessException(errmsg,
					new Object[] { annotationType });
		}
		return annotationType;
	}


	/**
	 * Return the sofaDataString of a JCAS corresponding to the given view 
	 * @param aJCas
	 * @return inputSofaDataString
	 * @throws AnalysisEngineProcessException
	 */
	public static String getSofaDataString(JCas aJCas) throws AnalysisEngineProcessException {
		String inputSofaDataString = null ; 
		inputSofaDataString = aJCas.getSofaDataString();

		if (inputSofaDataString == null) {
			String errmsg = "ERROR: The given view " + aJCas.toString()
			+ " does not contain a sofaDataString!";
			throw new AnalysisEngineProcessException(errmsg,
					new Object[] { });
		}
		return inputSofaDataString;
	}

	/**
	 * Get the name part of the uri stored in the source documentation of the annotation index of the current view
	 * @param aJCas
	 * @return
	 * @throws CASRuntimeException
	 * @throws AnalysisEngineProcessException
	 */
	public static String getArtifactViewName(JCas aJCas) throws CASRuntimeException, AnalysisEngineProcessException  {
		FSIterator<Annotation> sourceDocumentInformationFSIterator = aJCas.getAnnotationIndex(JCasSofaViewUtils.getJCasType(aJCas,
				DEFAULT_SOURCE_DOCUMENT_INFORMATION_ANNOTATION)).iterator();
		String outFileName = "";
		File inFile = null;
		if (sourceDocumentInformationFSIterator.hasNext()) {
			SourceDocumentInformation theSourceDocumentInformation = (SourceDocumentInformation) sourceDocumentInformationFSIterator.next();

			try {
				inFile = new File(new URL(theSourceDocumentInformation.getUri()).getPath());
				outFileName = inFile.getName();
				if (theSourceDocumentInformation.getOffsetInSource() > 0) {
					outFileName += ("-" + theSourceDocumentInformation.getOffsetInSource());
				}
				//outFile = new File(outputDirForCSV, outFileName);
				//System.out.println("Debug: outputDirForCSV "+ outputDirForCSVString+ " outFileName "+outFileName);  	

			} catch (MalformedURLException e) {
				// invalid URL, use default processing below
				e.printStackTrace();
			}
		}
		return outFileName;
	}

	/**
	 * This designation may be clearer than getArtifactName
	 * @param aJCas
	 * @return
	 * @throws AnalysisEngineProcessException 
	 * @throws CASRuntimeException 
	 */
	public String getDocumentName(JCas aJCas) throws CASRuntimeException, AnalysisEngineProcessException {
		return getTheArtifactName(aJCas);
	}

	/**
	 * Get the name of the artifact whatever it appears in any annotation index of associated view
	 * @param aJCas
	 * @return
	 * @throws AnalysisEngineProcessException 
	 * @throws CASRuntimeException 
	 */
	public static String getTheArtifactName(JCas aJCas) throws CASRuntimeException, AnalysisEngineProcessException {
		String artifactName = "";
		try {
			Iterator<JCas>	jCasViewIter =  aJCas.getViewIterator();

			while (jCasViewIter.hasNext()) {
				JCas aJCasView = jCasViewIter.next();
				String currentArtifactViewName = getArtifactViewName(aJCasView);
				//System.out.println("Debug: one view of the current JCas >"+aJCasView.getViewName()+"< and the artifact name is >"+currentArtifactViewName+"<");

				if (currentArtifactViewName != null) 
					if (!currentArtifactViewName.equalsIgnoreCase("")) artifactName = currentArtifactViewName;

			}
		} catch (CASException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 

		return artifactName;
	}


	/**
	 * Get the name of the artifact whatever it appears in any annotation index of associated view
	 * or create one specifying default prefix and suffix elements
	 * @param aJCas
	 * @return
	 * @throws AnalysisEngineProcessException 
	 * @throws CASRuntimeException 
	 */
	public static String getAnArtifactName(JCas aJCas) throws CASRuntimeException, AnalysisEngineProcessException {
		return getAnArtifactName(aJCas, true, "", "");
	}

	/**
	 * Get the name of the artifact whatever it appears in any annotation index of associated view
	 * or create one specifying prefix and suffix elements
	 * @param aJCas
	 * @return
	 * @throws AnalysisEngineProcessException 
	 * @throws CASRuntimeException 
	 */
	public static String getAnArtifactName(JCas aJCas, Boolean removeExtension, String prefix, String suffix) throws CASRuntimeException, AnalysisEngineProcessException {

		String artifactName = getTheArtifactName(aJCas) ;
		
		if (artifactName != null) {
			//System.out.println("Debug: SourceDocumentInformation is present in this view");
			if (!artifactName.equalsIgnoreCase("")) {
				int lastIndex = artifactName.lastIndexOf(".");
				artifactName = artifactName.substring(0, lastIndex);
			}
			else {System.err.println("Error: an artifact name not null but empty ; in that case should create A name");}
		}

		//if (artefactName == null) 
		else {
			//System.out.println("Debug: SourceDocumentInformation is not present in this view");

			byte[] hash      = null;
			try {
				hash= MessageDigest.getInstance("MD5").digest(aJCas.getSofaDataString().getBytes());
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//outFileName = defaultDocumentPrefix + hash;

			//cal = new GregorianCalendar(); // donne l'heure a l'instant t + x, l'heure systeme ayant change 
			//int milliseconde = cal.get(Calendar.MILLISECOND); // de 0 a 999 
//System.nanoTime() + '-' +
			artifactName = "" + System.nanoTime() + '-' + hash ;
			//artefactName = defaultDocumentPrefix + System.nanoTime() + '_' + hash;
			//inFile = new File(outputDirForCSV, DEFAULT_DOCUMENT_PREFIX + hash );   
			//System.out.println("Debug: outputDirForCSV "+ outputDirForCSVString+ " outFileName "+DEFAULT_DOCUMENT_PREFIX + hash + DEFAULT_CSV_EXTENSION);  	
		}
		
		artifactName = prefix + artifactName + suffix;

		return artifactName;
	}


}
