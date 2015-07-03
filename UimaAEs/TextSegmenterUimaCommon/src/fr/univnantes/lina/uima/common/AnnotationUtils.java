/** 
 * UIMA common
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
import org.apache.uima.cas.FSIndex;
import org.apache.uima.cas.FSIterator;
import org.apache.uima.cas.Feature;
import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.text.AnnotationIndex;
import org.apache.uima.examples.SourceDocumentInformation;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.cas.FSArray;
import org.apache.uima.jcas.tcas.Annotation;

import fr.univnantes.lina.java.util.JavaUtilities;

/**
 * <p>
 * Methods for handling annotations like
 * introspection and creation 
 * </p> 
 * <p>
 * Take in charge the exceptions
 * </p>
 * 
 * @author hernandez
 *
 */
public class AnnotationUtils   {


	/**
	 * Name of the current class
	 */
	private static String CURRENTCLASSNAME = "AnnotationUtilities";

	/**
	 * Get the class of a given annotation name in order to cast annotations latter
	 * Allow to know the type of the annotation to handle only at the runtime level
	 * @param annotationString
	 * @return Class<Annotation>
	 * 
	 * @throws AnalysisEngineProcessException
	 */
	public static Class<Annotation> getAnnotationClass(String annotationString) throws AnalysisEngineProcessException {
		// Récupère l'annotation courante d'un type connu
		// TokenAnnotation tokenAnnotation = (TokenAnnotation)
		// inputAnnotationIter
		// .next();

		//Génération de la classe  correspondante à la string annotationString
		Class<Annotation> annotationClass = null;
		try {
			annotationClass = (Class<Annotation>) Class
			.forName(annotationString);
		} catch (ClassNotFoundException e) {
			String errmsg = "Error: Class " + annotationString
			+ " not found !";
			throw new AnalysisEngineProcessException(errmsg,
					new Object[] { annotationString },e);	
			//e.printStackTrace();
		}
		// Annotation tokenAnnotation = (Annotation) inputAnnotationIter.next();
		// InputAnnotationClass.cast(tokenAnnotation);

		return annotationClass;

	}


	/**
	 * Get the object of a given class 
	 * Remains to cast the object
	 * Allow to know the type of the annotation to handle only at the runtime level
	 * @param annotationString
	 * @return annotationString
	 * @throws AnalysisEngineProcessException
	 */
	public static Object newJCasObjectClassInstance(Class aObjectClass, JCas aJCas) throws AnalysisEngineProcessException {

		Constructor aClassConstructor = null;
		try {
			aClassConstructor = aObjectClass.getConstructor(new Class [] {JCas.class});
		} catch (SecurityException e) {
			String errmsg = "Error: "+CURRENTCLASSNAME+" getObject SecurityException !";
			throw new AnalysisEngineProcessException(errmsg,
					new Object[] { },e);	
			//e.printStackTrace();
		} catch (NoSuchMethodException e) {
			String errmsg = "Error: "+CURRENTCLASSNAME+" getObject NoSuchMethodException !";
			throw new AnalysisEngineProcessException(errmsg,
					new Object[] { },e);	
			//e.printStackTrace();
		}

		Object aConstructedObject = null;
		try {
			aConstructedObject = aClassConstructor.newInstance(new Object [] {aJCas});
		} catch (IllegalArgumentException e) {
			String errmsg = "Error: "+CURRENTCLASSNAME+" getObject IllegalArgumentException !";
			throw new AnalysisEngineProcessException(errmsg,
					new Object[] { },e);	
			//e.printStackTrace();
		} catch (InstantiationException e) {
			String errmsg = "Error: "+CURRENTCLASSNAME+" getObject InstantiationException !";
			throw new AnalysisEngineProcessException(errmsg,
					new Object[] { },e);	
			//e.printStackTrace();
		} catch (IllegalAccessException e) {
			String errmsg = "Error: "+CURRENTCLASSNAME+" getObject IllegalAccessException !";
			throw new AnalysisEngineProcessException(errmsg,
					new Object[] { },e);	
			//e.printStackTrace();
		} catch (InvocationTargetException e) {
			String errmsg = "Error: "+CURRENTCLASSNAME+" getObject InvocationTargetException !";
			throw new AnalysisEngineProcessException(errmsg,
					new Object[] { },e);	
			//e.printStackTrace();
		}

		return aConstructedObject;

	}


	/**
	 * Cast object of a given class 
	 * Remains to cas the object
	 * Allow to know the type of the annotation to handle only at the runtime level
	 * @param annotationString
	 * @return annotationString
	 * @throws AnalysisEngineProcessException
	 */
	public static void castObjectWithAGivenClass(Class aObjectClass, Object aConstructedObject) throws AnalysisEngineProcessException {

		aObjectClass.cast(aConstructedObject);		
	}


	/*
	 * This method create an annotation and sets one feature with a String value.
	 * 
	 * @deprecated
	 * 
	 * @param aJCas
	 *            the CAS over which the process is performed
	 * @param annotationNameToCreate
	 * @param beginFeatureValue
	 * @param endFeatureValue
	 * @param featureNameToSet            
	 * @param valueFeatureValue
	 * @throws AnalysisEngineProcessException 

	public static void createAnnotation(JCas aJCas, String annotationNameToCreate,
			int beginFeatureValue, int endFeatureValue, String featureNameToSet, String valueFeatureValue) throws AnalysisEngineProcessException {

		// Crée une annotation générique
		// SequenceMatch sequenceMatch = new SequenceMatch(aJCas);
		// sequenceMatch.setBegin(patternHashMap.get(patternKeyString).getCurrentStartIndexCursor());
		// sequenceMatch.setEnd(tokenAnnotation.getEnd());
		// sequenceMatch.setValue(patternKeyString);
		// sequenceMatch.addToIndexes();

		// Crée une annotation prédéfinie
		// Object[] args = null;


		try {
			Object[] args = null;

			Class<Annotation> TgtClass = (Class<Annotation>) Class
			.forName(annotationNameToCreate);

			// System.out.println("Debug: ----------------------------------------------------------------------"
			// );
			// System.out.println("Debug: TgtClass.getName()	= " +
			// TgtClass.getName());
			// System.out.println("Debug: patternHashMap.get(patternKeyString).getTargetType()	= "
			// + patternHashMap.get(patternKeyString).getTargetType());

			// Génére le constructeur de la classe de l'annotation à créer
			Constructor<?> tgtConstr = TgtClass
			.getConstructor(new Class[] { JCas.class });

			// Crée une annotation du type target
			Object t = null;
			t = tgtConstr.newInstance(new Object[] { aJCas });
			TgtClass.cast(t);

			// System.out.println("Debug: t.getClass().getName()	= " +
			// t.getClass().getName());
			// System.out.println("Debug: t.getClass().getDeclaredMethods().length	= "
			// + t.getClass().getDeclaredMethods().length);

			// for (int l = 0 ; l < t.getClass().getDeclaredMethods().length ;
			// l++ ) {
			// System.out.println("Debug: t.getClass().getDeclaredMethods()[l]= "
			// + t.getClass().getDeclaredMethods()[l]);
			// }

			// for (int l = 0 ; l < t.getClass().getMethods().length ; l++ ) {
			// System.out.println("Debug: t.getClass().getMethods()[l]= " +
			// t.getClass().getMethods()[l]);
			// }

			// jxpathContext = JXPathContext.newContext(t);
			// Récupère la méthode addToIndexes de la classe target
			Method addToIndexes = TgtClass.getMethod("addToIndexes",
					new Class[] {});
			// Récupère les méthodes pour accéder aux features souhaitées
			Method setBegin = TgtClass.getMethod("setBegin", Integer.TYPE);
			Method setEnd = TgtClass.getMethod("setEnd", Integer.TYPE);

			// value -> setValue
			String setFeatureMethodName = FeatureUtilities.buildFeatureGetterMethodName(featureNameToSet);
			//featureNameToSet.substring(0, 1).toUpperCase() + featureNameToSet.substring(1);

			Method setValue = TgtClass.getMethod(setFeatureMethodName, String.class);

			// Ajouts à l'annotation du type target
			setBegin.invoke(t, beginFeatureValue);
			setEnd.invoke(t, endFeatureValue);
			setValue.invoke(t, valueFeatureValue);


			// Test contre la création d'annotations fantomes
			if (beginFeatureValue < endFeatureValue) 
				addToIndexes.invoke(t, args);

		} catch (IllegalArgumentException e) {
			String errmsg = "Error: IllegalArgumentException  !";
			throw new AnalysisEngineProcessException(errmsg,
					new Object[] {  },e);	
			//e.printStackTrace();
		} catch (IllegalAccessException e) {
			String errmsg = "Error: IllegalAccessException  !";
			throw new AnalysisEngineProcessException(errmsg,
					new Object[] {  },e);	
			//e.printStackTrace();
		} catch (InvocationTargetException e) {
			String errmsg = "Error: InvocationTargetException  !";
			throw new AnalysisEngineProcessException(errmsg,
					new Object[] {  },e);	
			//e.printStackTrace();
		} catch (ClassNotFoundException e) {
			String errmsg = "Error: ClassNotFoundException  !";
			throw new AnalysisEngineProcessException(errmsg,
					new Object[] {  },e);	
			//e.printStackTrace();
		} catch (SecurityException e) {
			String errmsg = "Error: SecurityException  !";
			throw new AnalysisEngineProcessException(errmsg,
					new Object[] {  },e);	
			//e.printStackTrace();
		} catch (NoSuchMethodException e) {
			String errmsg = "Error: NoSuchMethodException  !";
			throw new AnalysisEngineProcessException(errmsg,
					new Object[] {  },e);	
			//e.printStackTrace();
		} catch (InstantiationException e) {
			String errmsg = "Error: InstantiationException  !";
			throw new AnalysisEngineProcessException(errmsg,
					new Object[] {  },e);	
			//e.printStackTrace();
		}
	}
	 */

	/**
	 * This method create an annotation setting only the begin and end features
	 * 
	 * @param aJCas
	 *            the CAS over which the process is performed
	 * @param annotationNameToCreate
	 * @param beginFeatureValue
	 * @param endFeatureValue

	 * @throws AnalysisEngineProcessException 
	 */
	public static void createAnnotation(JCas aJCas, String annotationNameToCreate,
			int beginFeatureValue, int endFeatureValue) throws AnalysisEngineProcessException {

		try {
			Object[] args = null;

			Class<Annotation> TgtClass = (Class<Annotation>) Class
			.forName(annotationNameToCreate);

			// Génére le constructeur de la classe de l'annotation à créer
			Constructor<?> tgtConstr = TgtClass
			.getConstructor(new Class[] { JCas.class });

			// Crée une annotation du type target
			Object t = null;
			t = tgtConstr.newInstance(new Object[] { aJCas });
			TgtClass.cast(t);

			Method addToIndexes = TgtClass.getMethod("addToIndexes",
					new Class[] {});
			// Récupère les méthodes pour accéder aux features souhaitées
			Method setBegin = TgtClass.getMethod("setBegin", Integer.TYPE);
			Method setEnd = TgtClass.getMethod("setEnd", Integer.TYPE);

			// Ajouts à l'annotation du type target
			setBegin.invoke(t, beginFeatureValue);
			setEnd.invoke(t, endFeatureValue);

			// Test contre la création d'annotations fantomes
			if (beginFeatureValue < endFeatureValue) 
				addToIndexes.invoke(t, args);

		} catch (IllegalArgumentException e) {
			String errmsg = "Error: IllegalArgumentException  !";
			throw new AnalysisEngineProcessException(errmsg,
					new Object[] {  },e);	
			//e.printStackTrace();
		} catch (IllegalAccessException e) {
			String errmsg = "Error: IllegalAccessException  !";
			throw new AnalysisEngineProcessException(errmsg,
					new Object[] {  },e);	
			//e.printStackTrace();
		} catch (InvocationTargetException e) {
			String errmsg = "Error: InvocationTargetException  !";
			throw new AnalysisEngineProcessException(errmsg,
					new Object[] {  },e);	
			//e.printStackTrace();
		} catch (ClassNotFoundException e) {
			String errmsg = "Error: ClassNotFoundException  !";
			throw new AnalysisEngineProcessException(errmsg,
					new Object[] {  },e);	
			//e.printStackTrace();
		} catch (SecurityException e) {
			String errmsg = "Error: SecurityException  !";
			throw new AnalysisEngineProcessException(errmsg,
					new Object[] {  },e);	
			//e.printStackTrace();
		} catch (NoSuchMethodException e) {
			String errmsg = "Error: NoSuchMethodException  !";
			throw new AnalysisEngineProcessException(errmsg,
					new Object[] {  },e);	
			//e.printStackTrace();
		} catch (InstantiationException e) {
			String errmsg = "Error: InstantiationException  !";
			throw new AnalysisEngineProcessException(errmsg,
					new Object[] {  },e);	
			//e.printStackTrace();
		}
	}


	/**
	 * This method creates an annotation from a given String name 
	 * and sets up a "list" (actually an hashMap) of 
	 * features (couples name/value)
	 * 
	 * So far accepts feature with any primitive type (integer, boolean, float, string...)
	 * 
	 * @param aJCas
	 *            the CAS over which the process is performed
	 * @param annotationNameToCreate
	 * @param featuresHashMap

	 * @throws AnalysisEngineProcessException 
	 */
	public static void createAnnotation(JCas aJCas, String annotationNameToCreate, HashMap<String,Object> featuresHashMap) throws AnalysisEngineProcessException {

		try {
			Object[] args = null;

			Class<Annotation> annotationClass = (Class<Annotation>) Class
			.forName(annotationNameToCreate);

			// Génére le constructeur de la classe de l'annotation à créer
			Constructor<?> annotationClassConstructor = annotationClass
			.getConstructor(new Class[] { JCas.class });

			// Crée une annotation du type target
			Object t = null;
			t = annotationClassConstructor.newInstance(new Object[] { aJCas });
			annotationClass.cast(t);

			// Récupère la méthode addToIndexes
			Method addToIndexes = annotationClass.getMethod("addToIndexes",
					new Class[] {});		

			// Récupère le type correspondant à l'annotation à créer
			// Servira pour récupérer le type de ses features
			// Qui a son tour servira pour récupérer la méthode getter adéquate
			Type annotationType = JCasSofaViewUtils.getJCasType(aJCas, annotationNameToCreate);

			Iterator featureHashMapKeySetIterator = featuresHashMap.keySet().iterator();
			while (featureHashMapKeySetIterator.hasNext()) {

				// featureName
				String featureName = (String) featureHashMapKeySetIterator.next();

				// featureName -> setFeatureName
				//String setFeatureMethodName = FeatureUtils.buildFeatureSetterMethodName(featureName);

				// Récupère le Feature d'après son featureName
				// Puis récupère le type de la feature
				Feature featureFeature = annotationType.getFeatureByBaseName(featureName);
				Type featureType = featureFeature.getRange();

				// Récupère la method Setter pour cette featureNAme
				Method setFeatureMethod = FeatureUtils.getFeatureSetterMethod(annotationClass,featureName,featureType);

				// En fonction du type, invoque la méthode en castant selon la valeur adéquate attendue
				FeatureUtils.invokeFeatureSetterMethod(t, featureType, setFeatureMethod, featuresHashMap, featureName);

			}

			// Test contre la création d'annotations fantomes
			//if (beginFeatureValue < endFeatureValue) 
			addToIndexes.invoke(t, args);

		} catch (IllegalArgumentException e) {
			String errmsg = "Error: IllegalArgumentException  !";
			throw new AnalysisEngineProcessException(errmsg,
					new Object[] {  },e);	
			//e.printStackTrace();
		} catch (IllegalAccessException e) {
			String errmsg = "Error: IllegalAccessException  !";
			throw new AnalysisEngineProcessException(errmsg,
					new Object[] {  },e);	
			//e.printStackTrace();
		} catch (InvocationTargetException e) {
			String errmsg = "Error: InvocationTargetException  !";
			throw new AnalysisEngineProcessException(errmsg,
					new Object[] {  },e);	
			//e.printStackTrace();
		} catch (ClassNotFoundException e) {
			String errmsg = "Error: ClassNotFoundException  !";
			throw new AnalysisEngineProcessException(errmsg,
					new Object[] {  },e);	
			//e.printStackTrace();
		} catch (SecurityException e) {
			String errmsg = "Error: SecurityException  !";
			throw new AnalysisEngineProcessException(errmsg,
					new Object[] {  },e);	
			//e.printStackTrace();
		} catch (NoSuchMethodException e) {
			String errmsg = "Error: NoSuchMethodException  !";
			throw new AnalysisEngineProcessException(errmsg,
					new Object[] {  },e);	
			//e.printStackTrace();
		} catch (InstantiationException e) {
			String errmsg = "Error: InstantiationException  !";
			throw new AnalysisEngineProcessException(errmsg,
					new Object[] {  },e);	
			//e.printStackTrace();
		}
	}
	
	/**
	 * This method update a given annotation by setting some of its features 
	 * given as an hashMap of couples name/value
	 * 
	 * So far accepts feature with any primitive type (integer, boolean, float, string...)
	 * 
	 * @param aJCas
	 *            the CAS over which the process is performed
	 * @param annotationToUpdate
	 * @param annotationToProcessString
	 * @param featuresHashMap

	 * @throws AnalysisEngineProcessException 
	 */
	public static void updateAnnotation(JCas aJCas, Object annotationToUpdate, String annotationToProcessString, HashMap<String,Object> featuresHashMap) throws AnalysisEngineProcessException {

		try {

			Object[] args = null;

			Class<Annotation> annotationClass = (Class<Annotation>) Class
			.forName(annotationToUpdate.getClass().getName());

			annotationClass = (Class<Annotation>) Class
			.forName(annotationToProcessString);

			annotationClass.cast(annotationToUpdate);

			// Récupère le type correspondant à l'annotation à créer
			// Servira pour récupérer le type de ses features
			// Qui a son tour servira pour récupérer la méthode getter adéquate
			Type annotationType = JCasSofaViewUtils.getJCasType(aJCas, annotationToUpdate.getClass().getName());

			Iterator featureHashMapKeySetIterator = featuresHashMap.keySet().iterator();
			while (featureHashMapKeySetIterator.hasNext()) {

				// featureName
				String featureName = (String) featureHashMapKeySetIterator.next();

				// featureName -> setFeatureName
				String setFeatureMethodName = FeatureUtils.buildFeatureSetterMethodName(featureName);

				// Récupère le Feature d'après son featureName
				// Puis récupère le type de la feature
				Feature featureFeature = annotationType.getFeatureByBaseName(featureName);
				Type featureType = featureFeature.getRange();

				// Récupère la method Setter pour cette featureNAme
				Method setFeatureMethod = FeatureUtils.getFeatureSetterMethod(annotationClass,featureName,featureType);

				// En fonction du type, invoque la méthode en castant selon la valeur adéquate attendue
				FeatureUtils.invokeFeatureSetterMethod(annotationToUpdate, featureType, setFeatureMethod, featuresHashMap, featureName);
			}

		} catch (IllegalArgumentException e) {
			String errmsg = "Error: IllegalArgumentException  !";
			throw new AnalysisEngineProcessException(errmsg,
					new Object[] {  },e);	
			//e.printStackTrace();
		}  catch (ClassNotFoundException e) {
			String errmsg = "Error: ClassNotFoundException  !";
			throw new AnalysisEngineProcessException(errmsg,
					new Object[] {  },e);	
			//e.printStackTrace();
		} catch (SecurityException e) {
			String errmsg = "Error: SecurityException  !";
			throw new AnalysisEngineProcessException(errmsg,
					new Object[] {  },e);	
			//e.printStackTrace();
		} 
	}


}
