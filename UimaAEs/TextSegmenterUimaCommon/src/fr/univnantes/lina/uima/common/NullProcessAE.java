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

package fr.univnantes.lina.uima.common;


import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.jcas.JCas;
import fr.univnantes.lina.java.util.JavaUtilities;

/**
 * Do nothing
 */
public class NullProcessAE extends JCasAnnotator_ImplBase {

	/*
	 * PARAMETERS NAMES
	 */

	/**
	 * Name of the Parameter
	 */
	//public static final String PARAM_STRING_ARRAY = "StringArrayParameterName";

	/*
	 * DEFAULT VALUES
	 */
	/**
	 * Name of the default value
	 */
	// public static final String DEFAULT_ = ""

	/*
	 * LOCAL VARIABLES
	 */
	private String[] aStringArray;



	/*
	 * ACCESSORS
	 */


	/*
	 * METHODS 
	 */


	/**
	 * Parameter settings and checking
	 * 
	 */
	//public void initialize(UimaContext context) throws ResourceInitializationException {
		//aStringArray = (String[]) aContext.getConfigParameterValue(PARAM_STRING_ARRAY);

		//if (aStringArray == null) {
		//String errmsg = "The parameter "+ PARAM_STRING_ARRAY+" cannot be empty, please enter a valid value";
		//throw new ResourceInitializationException(errmsg, new Object[] {});
		//}
	//}

	/**
	 * Main processing method
	 * 
	 */
	public void process(JCas aJCas) throws AnalysisEngineProcessException {

		System.err.println("INFO: "+this.getClass().getName()+" starts and ends at " + JavaUtilities.now());
		
//		try {
//			csvData = (new CSVParser(new StringReader(JCasSofaViewUtils.getSofaDataString(inputViewJCas)), 
//					new CSVStrategy(separatorCharacter,valueEncapsulatorCharacter,commentStartingCharacter))).getAllValues();
//		} catch (IOException e) {
//			String errmsg = "Problem to CSV parse the JCAS. Check the sofaDataString of the jcas view "+ inputViewJCas.getViewName() +" ; " +
//			"< and the commentStartingCharacter >"+commentStartingCharacter+"<" ;
//			throw new AnalysisEngineProcessException(errmsg, new Object[] {});
//		}
	}



}
