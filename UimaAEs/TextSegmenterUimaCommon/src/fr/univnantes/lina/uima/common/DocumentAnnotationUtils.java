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
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.FSIterator;
import org.apache.uima.examples.SourceDocumentInformation;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.tcas.Annotation;

/**
 * <p>
 * Methods dealing with document annotation handling such as 
 * <ul>
 * <li>retrieveSourceDocumentFileName</li>
 * <ul>
 * </p>
 * 
 * @author hernandez
 */
public class DocumentAnnotationUtils {

	/**
	 * Name of the default SourceDocumentInformation
	 */
	static String DEFAULT_SOURCE_DOCUMENT_INFORMATION_ANNOTATION = "org.apache.uima.examples.SourceDocumentInformation";


	/**
	 *  Return the input file from the CAS 
	 *  Assumes that it has the sourceDocumentInformation 
	 *  (set by FileSystemCollectionReader or documentAnalyzer.sh)
	 *  null otherwise
	 */
	public static File retrieveSourceDocumentFile(JCas aJCas)
	throws AnalysisEngineProcessException {
		FSIterator<Annotation> sourceDocumentInformationFSIterator = aJCas.getAnnotationIndex(JCasSofaViewUtils.getJCasType(aJCas,
				DEFAULT_SOURCE_DOCUMENT_INFORMATION_ANNOTATION)).iterator();
		File inFile = null;
		if (sourceDocumentInformationFSIterator.hasNext()) {
			SourceDocumentInformation theSourceDocumentInformation = (SourceDocumentInformation) sourceDocumentInformationFSIterator.next();

			try {
				inFile = new File(new URL(theSourceDocumentInformation.getUri()).getPath());
				// System.out.println("Debug: SourceDocumentInformation File Name "+ inFileName);  	

			} catch (MalformedURLException e) {
				// invalid URL, use default processing below
				String errmsg = "Error: MalformedURLException !";
				throw new AnalysisEngineProcessException(errmsg,
						new Object[] { },e);	
				//e.printStackTrace();
			}

		}
		return inFile;
	}
	
	/**
	 *  Return the filename of the input file from the CAS 
	 *  Assumes that it has the sourceDocumentInformation 
	 *  (set by FileSystemCollectionReader or documentAnalyzer.sh)
	 *  null otherwise
	 */
	public static String retrieveSourceDocumentFileName(JCas aJCas)
	throws AnalysisEngineProcessException {
		
		File inFile = retrieveSourceDocumentFile(aJCas);
		return inFile.getName();
	}

	
}