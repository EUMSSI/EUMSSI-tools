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

import java.io.IOException;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;

import fr.univnantes.lina.java.util.JavaUtilities;

/**
 * Library of UIMA Utilities such as methods to get 
 * <p>
 * from its string name
 * <ul>
 * <li>a view </li>
 * <li>an annotation type  </li>
 * <li>a class </li>
 * <li>a feature</li>
 * <li>a feature getter method</li>
 * </ul>
 * </p>
 * <p>
 * or methods to invoke 
 * <ul>
 * <li>an getter/setter method associated to a given annotation</li>
 * </ul>
 * </p>
 * <p>
 * or methods to create 
 * <ul>
 * <li>annotations from its name and feature name to set</li>
 * <li>views  </li>
 * </ul>
 * </p>

 * <p>
 * or methods to subiterate with FeatureConstraints 
 * </p>
 * <p>
 * Take in charge the exceptions
 * </p>
 * 
 * @author hernandez
 *
 */
public class UIMAUtils   {


	/**
	 * Name of the current class
	 */
	private static String CURRENTCLASSNAME = "UIMAUtilities";


	/**
	 * Return the sofaDataString of a JCAS corresponding to the given view 
	 * @param aJCas
	 * @return inputSofaDataString
	 * @throws AnalysisEngineProcessException
	 */
	public static String  createAETempTextFile (String prefix, String suffix, String content)throws AnalysisEngineProcessException {
		String tempTextFilePath = null ; 

		try {
			tempTextFilePath = JavaUtilities.createTempTextFile (prefix,suffix,content);

		} catch (IOException ioexception) {
			String errmsg = "ERROR: Cannot create a temporary text file The view !";
			throw new AnalysisEngineProcessException(errmsg,
					new Object[] { tempTextFilePath },ioexception);
		}
		return tempTextFilePath;
	}
}
