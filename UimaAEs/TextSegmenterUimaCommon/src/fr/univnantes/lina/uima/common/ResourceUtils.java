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

import org.apache.uima.UimaContext;
import org.apache.uima.resource.ResourceAccessException;
import org.apache.uima.resource.ResourceInitializationException;
import fr.univnantes.lina.uima.resources.CSVDictionaryResource;

/**
 * <p>
 * Methods for handling resources:
 * </p> 
 * <p>
 * </p>
 * 
 * @author hernandez
 *
 */
public class ResourceUtils   {


	/**
	 * Name of the current class
	 */
	private static String CURRENTCLASSNAME = "ResourceUtilities";


	/**
	 * Automatic loading, parsing, and building a node 
	 * general Method
	 * @param aResource this.resource = new CSVDictionaryResource();
	 * @param aContext
	 * @param aDictionaryResource
	 * @param aDictionaryResourceFile
	 * @throws ResourceInitializationException
	 */
	public static void loadAResource(CSVDictionaryResource aResource, UimaContext aContext, String aDictionaryResource, String aDictionaryResourceFile) throws ResourceInitializationException {

		try {
			

			// Load, parse, and build an index automatically from a conventional declaration of a resource 
			ResourceUtils.loadAResourceObject(aResource,(CSVDictionaryResource) aContext.getResourceObject(aDictionaryResource));

			// Load, parse, and build an index from a declaration of a resource by a file parameter (crush the previous declaration)
			ResourceUtils.loadAResourceFromFilePath(aResource, (String) aContext.getConfigParameterValue(aDictionaryResourceFile));

		} catch (ResourceAccessException e) {
			throw new ResourceInitializationException(e);
		}
	}
	/**
	 * Automatic loading, parsing, and building a node tree by declaration of 
	 * the resource in the dedicated section in the descriptor 
	 * @param aResource
	 */
	public static void loadAResourceObject(CSVDictionaryResource aResource, CSVDictionaryResource with) {
		aResource = with;
	}

	/**
	 * Loading, parsing, and building a node tree from a resource set by a path parameter
	 * @param aResource 
	 * @param path
	 * @throws ResourceInitializationException
	 */
	public static void loadAResourceFromFilePath(CSVDictionaryResource aResource, String path) throws ResourceInitializationException {
		if (path != null) {
			System.out.println("Debug: path>"+path+"<");
			File file = new File(path);
			try {
				aResource.doLoad(file);
			} catch (Exception e) {
				throw new ResourceInitializationException(e);
			}
		}
	}
}
