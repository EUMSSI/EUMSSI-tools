package fr.univnantes.lina.uima.common;
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

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import org.apache.uima.jcas.cas.StringArray;

import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.Type;
import org.apache.uima.jcas.tcas.Annotation;


/**
 * Try to encourage the use of 
 * 		Return a feature anyAnnotation.getType().getFeatureByBaseName(featureName)
 * 		Return a string value anyAnnotation.getStringValue(anyAnnotation.getType().getFeatureByBaseName(featureName)) 
 * which is much simpler for getting the feature values ...
 * 
 * <p>
 * Methods dealing with annotation features handling such as 
 * <ul>
 * <li>buildFeatureGetterMethodName</li>
 * <li>buildFeatureSetterMethodName</li>
 * <li>feature getter/setter methods associated to a given annotation</li>
 * <li>method to invoke </li>
 * <li></li>
 * <ul>
 * </p>
 * 
 * @author hernandez
 */
public class FeatureUtils {

	/**
	 * Return the corresponding method getter name of a feature name
	 * @param featureNameString
	 * @return getFeatureMethodName
	 */
	public static String buildFeatureGetterMethodName(String featureNameString) {
		String featureMethodName = "get" + featureNameString.substring(0, 1).toUpperCase() + featureNameString.substring(1);
		return featureMethodName;
	}

	/**
	 * Return the getter method of a given feature name
	 * @param inputAnnotationClass
	 * @param inputFeatureString
	 * @return Method Get
	 * @throws AnalysisEngineProcessException
	 */
	public static Method getFeatureGetterMethod(Class inputAnnotationClass, String inputFeatureString)
	throws AnalysisEngineProcessException {

		// Récupère la méthode pour "getter" la value de l'InputFeature
		String getFeatureMethodName = buildFeatureGetterMethodName(inputFeatureString);

		Method getFeatureMethod = null;
		try {
			getFeatureMethod = inputAnnotationClass.getMethod(getFeatureMethodName);
		} catch (SecurityException e) {
			String errmsg = "Error: a SecurityException with getMethod " + getFeatureMethodName
			+ " !";
			throw new AnalysisEngineProcessException(errmsg,
					new Object[] { getFeatureMethodName },e);	
			//e.printStackTrace();
		} catch (NoSuchMethodException e) {
			String errmsg = "Error: NoSuchMethodException getMethod " + getFeatureMethodName
			+ " !";
			throw new AnalysisEngineProcessException(errmsg,
					new Object[] { getFeatureMethodName },e);	
			//e.printStackTrace();
		}
		return getFeatureMethod;
	}

	/**
	 * Return the corresponding method setter name of a feature name
	 * @param featureNameString
	 * @return setFeatureMethodName
	 */
	public static String buildFeatureSetterMethodName(String featureNameString) {
		String featureMethodName = "set" + featureNameString.substring(0, 1).toUpperCase() + featureNameString.substring(1);
		return featureMethodName;
	}

	/**
	 * Return the setter method of a given feature name
	 * @param inputAnnotationClass
	 * @param inputFeatureString
	 * @return a Method set
	 * @throws AnalysisEngineProcessException
	 */
	public static Method getFeatureSetterMethod(Class inputAnnotationClass, String inputFeatureString,
			Type inputFeatureType) throws AnalysisEngineProcessException {

		// Construit le nom de laa méthode pour "setter" la value de l'InputFeature
		String setFeatureMethodName = buildFeatureSetterMethodName(inputFeatureString);

		//System.out.println("Debug: UIMAUtilities getSetterMethod inputFeatureType.getName() "+inputFeatureType.getName());

		Method setFeatureMethod = null;
		try {

			// Récupère la méthode Getter selon le type de la valeur attendue
			if (inputFeatureType.getName().equalsIgnoreCase("uima.cas.String")) {
				setFeatureMethod = inputAnnotationClass.getMethod(setFeatureMethodName,String.class);
			}
			else if (inputFeatureType.getName().equalsIgnoreCase("uima.cas.Integer")) {
				setFeatureMethod = inputAnnotationClass.getMethod(setFeatureMethodName, Integer.TYPE);
			}
			else if (inputFeatureType.getName().equalsIgnoreCase("uima.cas.Double")) {
				setFeatureMethod = inputAnnotationClass.getMethod(setFeatureMethodName, Double.TYPE);
			}
			else if (inputFeatureType.getName().equalsIgnoreCase("uima.cas.Short")) {
				setFeatureMethod = inputAnnotationClass.getMethod(setFeatureMethodName, Short.TYPE);
			}
			else if (inputFeatureType.getName().equalsIgnoreCase("uima.cas.Long")) {
				setFeatureMethod = inputAnnotationClass.getMethod(setFeatureMethodName, Long.TYPE);
			}
			else if (inputFeatureType.getName().equalsIgnoreCase("uima.cas.Float")) {
				setFeatureMethod = inputAnnotationClass.getMethod(setFeatureMethodName, Float.TYPE);
			}
			else if (inputFeatureType.getName().equalsIgnoreCase("uima.cas.Boolean")) {
				setFeatureMethod = inputAnnotationClass.getMethod(setFeatureMethodName, Boolean.TYPE);
			}
			else if (inputFeatureType.getName().equalsIgnoreCase("uima.cas.Byte")) {
				setFeatureMethod = inputAnnotationClass.getMethod(setFeatureMethodName, Byte.TYPE);
			}
			else if (inputFeatureType.getName().equalsIgnoreCase("uima.cas.StringArray")) {
				setFeatureMethod = inputAnnotationClass.getMethod(setFeatureMethodName, StringArray.class);
			}
			else  {
				String errmsg = "Error: unhandled inputFeatureType in UIMAUtilities getSetterMethod :" + inputFeatureType.getName()
				+ " !";
				throw new AnalysisEngineProcessException(errmsg,
						new Object[] { setFeatureMethodName });	
			}


		} catch (SecurityException e) {
			String errmsg = "Error: a SecurityException with getMethod " + setFeatureMethodName
			+ " !";
			throw new AnalysisEngineProcessException(errmsg,
					new Object[] { setFeatureMethodName },e);	
			//e.printStackTrace();
		} catch (NoSuchMethodException e) {
			String errmsg = "Error: NoSuchMethodException getMethod " + setFeatureMethodName
			+ " !";
			throw new AnalysisEngineProcessException(errmsg,
					new Object[] { setFeatureMethodName },e);	
			//e.printStackTrace();
		}
		return setFeatureMethod;
	}

	/**
	 * Invoke a getter method of a given annotation Annotation which returns an Object  
	 * The Object should be called then with .toString() if we want it as a String
	 * Allow to know the method of the annotation to handle only at the runtime level
	 * 
	 * @param inputAnnotation
	 * @param getFeatureMethod
	 * @return result
	 * 
	 * @throws AnalysisEngineProcessException
	 */
	public static Object invokeFeatureGetterMethod(Annotation inputAnnotation, Method getFeatureMethod)
	throws AnalysisEngineProcessException {

		Object result = null;

		// Test contre la création d'annotations fantomes
		try {
			result = (Object) getFeatureMethod.invoke(inputAnnotation);
		} catch (IllegalArgumentException e) {
			String errmsg = "Error: IllegalArgumentException invoked " + inputAnnotation
			+ " !";
			throw new AnalysisEngineProcessException(errmsg,
					new Object[] { inputAnnotation },e);	
			//e.printStackTrace();
		} catch (IllegalAccessException e) {
			String errmsg = "Error: IllegalAccessException invoked " + inputAnnotation
			+ " !";
			throw new AnalysisEngineProcessException(errmsg,
					new Object[] { inputAnnotation },e);	
			//e.printStackTrace();
		} catch (InvocationTargetException e) {
			String errmsg = "Error: InvocationTargetException invoked " + inputAnnotation
			+ " !";
			throw new AnalysisEngineProcessException(errmsg,
					new Object[] { inputAnnotation },e);	
			//e.printStackTrace();
		}

		return result;
	}



	/**
	 * Invoke a setter method of a given annotation feature name 
	 *   
	 * @param t
	 * @param featureType
	 * @param setFeatureMethod
	 * @param featuresHashMap
	 * @param featureName
	 * @throws AnalysisEngineProcessException
	 */
	public static void invokeFeatureSetterMethod(Object t, Type featureType, Method setFeatureMethod, HashMap<String,Object> featuresHashMap, String featureName)
			throws AnalysisEngineProcessException {

				try {

					// En fonction du type, invoque la méthode en castant selon la valeur adéquate attendue
					if (featureType.getName().equalsIgnoreCase("uima.cas.String")) {
						setFeatureMethod.invoke(t, (String) featuresHashMap.get(featureName));}
					
					else if (featureType.getName().equalsIgnoreCase("uima.cas.Integer")) {
						setFeatureMethod.invoke(t,  Integer.parseInt((String)featuresHashMap.get(featureName)));	}
					
					else if (featureType.getName().equalsIgnoreCase("uima.cas.Double")) {
						setFeatureMethod.invoke(t,  Double.parseDouble((String)featuresHashMap.get(featureName)));				}
					
					else if (featureType.getName().equalsIgnoreCase("uima.cas.Short")) {
						setFeatureMethod.invoke(t,  Short.parseShort((String)featuresHashMap.get(featureName)));				}
					
					else if (featureType.getName().equalsIgnoreCase("uima.cas.Long")) {
						setFeatureMethod.invoke(t,  Long.parseLong((String)featuresHashMap.get(featureName)));				}
					
					else if (featureType.getName().equalsIgnoreCase("uima.cas.Float")) {
						setFeatureMethod.invoke(t,  Float.parseFloat((String)featuresHashMap.get(featureName)));				}
					
					else if (featureType.getName().equalsIgnoreCase("uima.cas.Boolean")) {
						setFeatureMethod.invoke(t,  Boolean.parseBoolean((String)featuresHashMap.get(featureName)));				}
					
					else if (featureType.getName().equalsIgnoreCase("uima.cas.Byte")) {
						setFeatureMethod.invoke(t,  Byte.parseByte((String)featuresHashMap.get(featureName)));				}
					
					else if (featureType.getName().equalsIgnoreCase("uima.cas.StringArray")) {
						setFeatureMethod.invoke(t, (StringArray) featuresHashMap.get(featureName));
					}
					else  {
						String errmsg = "Error: unhandled inputFeatureType in UIMAUtilities getSetterMethod :" + featureType.getName()
						+ " !";
						throw new AnalysisEngineProcessException(errmsg,
								new Object[] { featureType.getName() });	
					}
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
				}	
				catch (SecurityException e) {
					String errmsg = "Error: SecurityException  !";
					throw new AnalysisEngineProcessException(errmsg,
							new Object[] {  },e);	
					//e.printStackTrace();
				} 
			}
	
	/**
	 * Return the value of a given feature name from a given annotation
	 * @param anAnnotation
	 * @param aFeatureName
	 * @return an Object to cast
	 * @throws AnalysisEngineProcessException
	 */
	public static Object getFeatureValue(Annotation anAnnotation,  String aFeatureName)
	throws AnalysisEngineProcessException {
		Method anAnnotationFeatureGetterMethod = FeatureUtils.getFeatureGetterMethod(anAnnotation.getClass(), aFeatureName);
		return FeatureUtils.invokeFeatureGetterMethod(anAnnotation, anAnnotationFeatureGetterMethod);
		
	}

}