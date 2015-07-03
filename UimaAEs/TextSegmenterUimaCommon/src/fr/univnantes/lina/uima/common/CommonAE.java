/** 
 * Analysis Engine 
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

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.uima.UimaContext;
import org.apache.uima.analysis_component.AnalysisComponent;
import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.FSIterator;
import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.text.AnnotationIndex;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.tcas.Annotation;
import org.apache.uima.resource.ResourceInitializationException;

import fr.univnantes.lina.java.util.JavaUtilities;

/**
 * <p>
 * UIMA Analysis Engine abstract class
 * </p>
 * 
 * <p>
 * <ul>
 * <li>Perform some process on some annotations (also called InputAnnotation)
 * covered by some others (also called ContextAnnotation) which are only present
 * in some views (also called InputView).</li>
 * <li>Create a new view (also called OutputView) or a new annotation (also
 * called OutputAnnotation) to receive the processing result.</li>
 * </ul>
 * </p>
 * 
 * <p>
 * The process has to be implemented (see the abstract method of the class).
 * </p>
 * 
 * <p>
 * The class is type system agnostic and so handle generically the views and the
 * annotations you process. This is made possible by specifying the names of the
 * handled views and annotations by parameters.
 * </p>
 * 
 * @author Nicolas Hernandez
 */
public  class CommonAE extends JCasAnnotator_ImplBase {

	/**
	 * When you use the code source as an AE example, please redefine the
	 * "Common component constants Default" and the
	 * "Common component parameter values"
	 */

	/**
	 * Common component constants To define when you create a new component
	 * */

	/**
	 * Name of the component
	 */
	protected String COMPONENT_NAME = "";
	protected String COMPONENT_VERSION = "";
	protected String COMPONENT_ID = COMPONENT_NAME + "-" + COMPONENT_VERSION;
	// exception
	protected final String MESSAGE_DIGEST = COMPONENT_ID + "_Messages";
	// tmp file
	// log

	/** Common component parameters in descriptor file */
	// View name to consider as the view to process
	protected static String PARAM_NAME_INPUT_VIEW = "InputView";
	// Type name of the annotations to consider as the context annotations in
	// which
	// the process will be performed
	protected static String PARAM_NAME_CONTEXT_ANNOTATION = "ContextAnnotation";
	// Type name of the annotations to consider as the token units to be
	// processed
	protected static String PARAM_NAME_INPUT_ANNOTATION = "InputAnnotation";
	// Feature name of the annotations to consider as the token units to be
	// processed
	protected static String PARAM_NAME_INPUT_FEATURE = "InputFeature";
	// View name to consider as the view to receive the result
	protected static String PARAM_NAME_OUTPUT_VIEW = "OutputView";
	// Type mime to consider for storing the result in the sofaDataString
	protected static String PARAM_NAME_OUTPUT_VIEW_TYPE_MIME = "OutputViewTypeMime";
	// Type name of the annotations to create as the analysis result
	protected static String PARAM_NAME_OUTPUT_ANNOTATION = "OutputAnnotation";
	// Type name of the feature to create as the analysis result
	protected static String PARAM_NAME_OUTPUT_FEATURE = "OutputFeature";
	// An identifier for the current run.
	// This identifier is added into all the annotations that are created during
	// the current execution.
	protected static String PARAM_NAME_RUNID = "RunId";

	/** Default common component parameter values in descriptor file **/
	// Default view name if none are specified by the view parameter
	protected static String DEFAULT_INPUT_VIEW = "_InitialView";
	// Default context annotation name if none is specified by the context
	// annotation parameter
	protected static String DEFAULT_CONTEXT_ANNOTATION = "uima.tcas.DocumentAnnotation";
	// Default annotation name if none are specified by the input annotation
	// parameter
	// Default feature name if none are specified by the input feature
	// parameter when the input annotation parameter is set
	public static String DEFAULT_INPUT_FEATURE = "coveredText";
	// public static String DEFAULT_INPUT_ANNOTATION = "TokenAnnotation";
	// Default annotation name if none are specified by the output annotation
	// parameter
	// public static String DEFAULT_OUTPUT_ANNOTATION =
	// "fr.univnantes.lina.uima.shell.types.ShellAnnotation";
	// Default feature name if none are specified by the output feature
	// parameter
	// public static String DEFAULT_OUTPUT_FEATURE = "value";
	// Default type mime value for the sofaDataString in case of the output type
	// is view
	protected static String DEFAULT_OUTPUTVIEW_TYPEMIME = "text/plain";
	// 
	protected static String INPUTTYPE_ANNOTATION = "annotation";
	protected static String INPUTTYPE_VIEW = "view";
	protected static String OUTPUTTYPE_ANNOTATION = "annotation";
	protected static String OUTPUTTYPE_VIEW = "view";

	/** Common component variables */
	protected String runIdString = null;

	/**
	 * InputView List of view names to process
	 */
	protected String[] inputViewStringArray = null;
	// protected String inputViewString = null;

	/**
	 * ContextAnnotation List of annotation names which delimits the text areas
	 * or the covered annotations to process
	 */
	protected String[] contextAnnotationStringArray = null;
	protected HashMap<String, Integer> contextAnnotationStringHashMap = null;
	// protected String contextAnnotationString = null;

	/**
	 * InputAnnotation Ordered list of annotation names to consider as the
	 * Feature Structure index to process
	 */
	protected String[] inputAnnotationStringArray = null;
	protected HashMap<String, Integer> inputAnnotationStringHashMap = null;
	// protected String inputAnnotationString = null;



	/**
	 * InputFeature Feature name of the annotations whose string value will be
	 * processed
	 */
	protected String inputFeatureString = null;



	/**
	 * OutputView View name to consider as the view to receive the result; to be
	 * created whether OutputAnnotation is empty or simply to edit if
	 * OutputAnnotation is defined
	 */
	protected String outputViewString = null;


	protected String outputViewTypeMimeString = null;
	/**
	 * OutputAnnotation Name of the annotation to create as the analysis result
	 */
	protected String outputAnnotationString = null;
	/**
	 * OutputFeature Feature name of the annotation whose string value will
	 * contain the analysis result
	 */
	protected String outputFeatureString = null;

	protected String inputType = "";
	protected String outputType = "";

	/**
	 * @see AnalysisComponent#initialize(UimaContext)
	 */
	public void initialize(UimaContext aContext)
	throws ResourceInitializationException {
		super.initialize(aContext);

		/** Get parameter values **/
		runIdString = (String) aContext
		.getConfigParameterValue(PARAM_NAME_RUNID);

		inputViewStringArray = (String[]) aContext
		.getConfigParameterValue(PARAM_NAME_INPUT_VIEW);
		if (inputViewStringArray == null) {
			// If no input view is specified, we use the default (i.e.
			// _InitialView)
			inputViewStringArray = new String[1];
			inputViewStringArray[0] = DEFAULT_INPUT_VIEW;
		}

		// contextAnnotationString = (String) aContext
		// .getConfigParameterValue(PARAM_NAME_CONTEXT_ANNOTATION);
		contextAnnotationStringArray = (String[]) aContext
		.getConfigParameterValue(PARAM_NAME_CONTEXT_ANNOTATION);

		if (contextAnnotationStringArray == null) {
			// If no context specified, we do over the whole CAS
			// en d'autres termes, on traite le uima.tcas.DocumentAnnotation
			contextAnnotationStringArray = new String[1];
			contextAnnotationStringArray[0] = DEFAULT_CONTEXT_ANNOTATION;
		}

		contextAnnotationStringHashMap = new HashMap<String, Integer>();
		if (contextAnnotationStringArray != null) {
			if (contextAnnotationStringArray.length != 0) {
				for (String c : contextAnnotationStringArray) {
					contextAnnotationStringHashMap.put(c, 1);
				}
			}
		}

		// ... otherwise over segments covered by the contextAnnotation
		// parameter

		// inputAnnotationString = (String) aContext
		// .getConfigParameterValue(PARAM_NAME_INPUT_ANNOTATION);

		inputAnnotationStringArray = (String[]) aContext
		.getConfigParameterValue(PARAM_NAME_INPUT_ANNOTATION);
		// System.out.println("Debug: après getConfigParameterValue inputAnnotationStringArray"
		// +inputAnnotationStringArray + "size" +
		// inputAnnotationStringArray.length);

		inputAnnotationStringHashMap = new HashMap<String, Integer>();

		// un getConfigParameterValue d'un parameter multivalued vide donne
		// (parfois?) une variable tableau vide mais non null
		// donc ne peut être comparer à null
		// attention on ne peut comparer sa lengh qu'après un
		// getConfigParameterValue sans quoi on obtiendrait un null
		// je décide de faire les 2
		// System.out.println("Debug: après getConfigParameterValue inputAnnotationStringArray"
		// +inputAnnotationStringArray);

		if (inputAnnotationStringArray != null) {
			if (inputAnnotationStringArray.length != 0) {
				for (String inputAnnotationString : inputAnnotationStringArray) {
					inputAnnotationStringHashMap.put(inputAnnotationString, 1);
				}
			}
		}
		inputFeatureString = (String) aContext
		.getConfigParameterValue(PARAM_NAME_INPUT_FEATURE);
		//		if (((inputFeatureString != null) && (inputAnnotationStringArray == null))
		//				|| ((inputFeatureString != null) && ((inputAnnotationStringArray != null) && (inputAnnotationStringArray.length == 0)))
		//				|| ((inputFeatureString == null) && ((inputAnnotationStringArray != null) && (inputAnnotationStringArray.length != 0)))) {
		if (((inputFeatureString != null) && (inputAnnotationStringHashMap == null))
				|| ((inputFeatureString != null) && ((inputAnnotationStringHashMap != null) && (inputAnnotationStringHashMap.size() == 0)))
				|| ((inputFeatureString == null) && ((inputAnnotationStringHashMap != null) && (inputAnnotationStringHashMap.size() != 0)))) {

			String errmsg = "Error: If one of the parameter "
				+ PARAM_NAME_INPUT_ANNOTATION + " or "
				+ PARAM_NAME_INPUT_FEATURE + " is defined, both must be !";

			// System.out.println("Debug: inputFeatureString " +
			// inputFeatureString + " inputAnnotationStringArray" +
			// inputAnnotationStringArray +":");
			// for (String s : inputAnnotationStringArray) {
			// System.out.print(">"+ s + "<");
			// }System.out.println();

			throw new ResourceInitializationException(errmsg, new Object[] {});
			// e.printStackTrace();
		}

		// Ce test intervient après avoir vérifier que le couple
		// Annotation/Feature était bien complet
		//
		// Il est possible de ne pas avoir une méthode dédiée au traitement de
		// vue
		// et de ne pas y faire appel dans la méthode process
		// en utilisant DocumentAnnotation et dans la méthode
		// processContextAnnotation
		// créer un index de InputAnnotation à partir des ContextAnnotation
		// Rajoute le fait que l'on doit gérer un param supplémentaire le
		// ContextFeature
		// Mais si on veut spécifier des ContextAnnotation a proprement parlé et
		// pas des InputView
		// Il faut quand même rajouter ce param et par conséquent il faudra
		// tester sa co-existence avec ContextAnnotation
		// Donc le test et l'affectation suivante n'est là que si on décide de
		// ne pas passer par processInputView en natif
		// On décide de ne pas ajouter le param ContextFeature donc le teste
		// suivante sert pour 2 cas
		//
		// Si inputFeatureString est null c'est que soit ContextAnnotation soit
		// InputView
		// car si InputAnnotation alors exception aurait été levée donc on le
		// laisse par défaut
		if (inputFeatureString == null) {
			inputFeatureString = DEFAULT_INPUT_FEATURE;
		}

		outputViewString = (String) aContext
		.getConfigParameterValue(PARAM_NAME_OUTPUT_VIEW);

		outputViewTypeMimeString = (String) aContext
		.getConfigParameterValue(PARAM_NAME_OUTPUT_VIEW_TYPE_MIME);
		if (outputViewTypeMimeString == null) {
			// If no output view type mime is specified, we set it a default one
			outputViewTypeMimeString = DEFAULT_OUTPUTVIEW_TYPEMIME;
		}

		outputAnnotationString = (String) aContext
		.getConfigParameterValue(PARAM_NAME_OUTPUT_ANNOTATION);

		outputFeatureString = (String) aContext
		.getConfigParameterValue(PARAM_NAME_OUTPUT_FEATURE);
		// outputAnnotationString ET outputFeatureString doivent être
		// initialisés les deux à la fois ou aucun d'eux
		/*if (((outputAnnotationString != null) && (outputFeatureString == null))
				|| ((outputAnnotationString == null) && (outputFeatureString != null))) {
			String errmsg = "Error: If one of the parameter "
				+ PARAM_NAME_OUTPUT_ANNOTATION + " or "
				+ PARAM_NAME_OUTPUT_FEATURE + " is defined, both must be !";
			throw new ResourceInitializationException(errmsg, new Object[] {});
			// e.printStackTrace();
		}*/
		System.err.println("Warning: uima-shell may require the " +
				"definition of the couple "+ PARAM_NAME_OUTPUT_ANNOTATION + " and "
				+ PARAM_NAME_OUTPUT_FEATURE +  " but it is not necessary for uima-word-segmenter" +
						" so we remove the test");

		// Si l'input_type est annotation, alors on va traiter chacune d'elle
		if (inputAnnotationStringArray != null)
			inputType = INPUTTYPE_ANNOTATION;
		// Sinon on va traiter le datastring de la vue
		else
			inputType = INPUTTYPE_VIEW;

		if ((outputAnnotationString != null) && (outputFeatureString != null))
			outputType = OUTPUTTYPE_ANNOTATION;
		else
			outputType = OUTPUTTYPE_VIEW;

		// String[] patternStrings = (String[])
		// aContext.getConfigParameterValue("Patterns");
		// mLocations = (String[])
		// aContext.getConfigParameterValue("Locations");

		// compile regular expressions
		// mPatterns = new Pattern[patternStrings.length];
		// for (int i = 0; i < patternStrings.length; i++) {
		// mPatterns[i] = Pattern.compile(patternStrings[i]);
		// }

		// dans le process
		// Vérifier que context, input et output AnnotationString si !=null
		// alors ont un type défini dans le TS
		// Vérifier aussi que l'input view existe

	}

	/**
	 * From the given JCas, process sequentially each InputView. Eventually may
	 * create a view with the concatenated results obtained for each view.
	 * 
	 * Previously the method was intending to route toward the correct
	 * subprocess method depending on the InputType which is either Annotation
	 * or View. Recent modifications led to use the same subprocess method.
	 * Indeed the InputView will be processed indirectly by the
	 * DocumentAnnotation.
	 * 
	 * @param aJCas
	 *            the CAS over which the process is performed
	 * @param inputViewString
	 *            [] List of names of each InputView to process
	 * @param contextAnnotationStringArray
	 *            List of the Context Annotation names to process
	 * @param inputAnnotationStringArray
	 *            List of Input Annotation names to process
	 * @param inputFeatureString
	 *            Feature name of the inputAnnotation whose String Value will be
	 *            actually processed
	 * @param outputViewString
	 *            View name to consider as the view to receive the result; to be
	 *            created whether `OutputAnnotation` is empty or simply to edit
	 *            if `OutputAnnotation` is defined
	 * @param outputViewTypeMimeString
	 *            Type Mime of the view to create
	 * @param outputAnnotationString
	 *            Name of the annotation to create as the analysis result
	 * @param ouputFeatureString
	 *            Feature name of the annotation whose string value will contain
	 *            the analysis result
	 * 
	 * @throws AnalysisEngineProcessException
	 * 
	 * @see JCasAnnotator_ImplBase#process(JCas)
	 */
	public void process(JCas aJCas) throws AnalysisEngineProcessException {

		Date startDate = JavaUtilities.getNow();
		System.err.println("INFO: "+this.getClass().getName()+" starts at " + JavaUtilities.stringFormatADate(startDate));

		/** -- process the analysis **/

		log("-----------------------------------------------------------------------------------------------------------------");
		if (inputType.equalsIgnoreCase(INPUTTYPE_ANNOTATION)) {
			log("AnalysisEngine - Process the input annotation of a given type (potentially covered by a context annotation of a given type)");
			// processContextAnnotations(aJCas, inputViewString,
			// contextAnnotationString, inputAnnotationStringArray,
			// inputFeatureString, outputViewString, outputViewTypeMimeString,
			// outputAnnotationString, outputFeatureString);
		} else {
			log("AnalysisEngine - Process the input view; actually the default ContextAnnotation DocumentAnnotation");
			// log("Process the input view");
			// processInputViewType(aJCas, inputViewString,
			// contextAnnotationString, inputAnnotationStringArray,
			// inputFeatureString, outputViewString, outputViewTypeMimeString,
			// outputAnnotationString, outputFeatureString);
		}

		/*
		 * processInputViews(aJCas, inputViewStringArray,
		 * contextAnnotationStringArray, inputAnnotationStringArray,
		 * inputFeatureString, outputViewString, outputViewTypeMimeString,
		 * outputAnnotationString, outputFeatureString);
		 */

		// JCas aJCas,
		// String[] inputViewStringArray,
		// String[] contextAnnotationStringArray,
		// String[] inputAnnotationStringArray,
		// String inputFeatureString,
		// String outputViewString,
		// String outputViewTypeMimeString,
		// String outputAnnotationString,
		// String outputFeatureString

		log("Getting the Absolute  Context Annotation ");

		// Récupère le type de la DEFAULT_CONTEXT_ANNOTATION
		AnnotationIndex<Annotation> absoluteContextAnnotationIndex = null;
		Type absoluteContextAnnotationType = null;
		absoluteContextAnnotationType = JCasSofaViewUtils.getJCasType(aJCas,
				DEFAULT_CONTEXT_ANNOTATION);
		// Récupération d'index d'annotations à partir de type d'annotation!
		// soit comme cela
		// AnnotationIndex idxMonType = (AnnotationIndex)
		// cas.getAnnotationIndex(inputAnnotationType);
		// FSIterator monTypeIt = idxMonType.iterator();
		// while (monTypeIt.hasNext()) {
		// On peut le manipuler comme on veut ...
		// }
		// soit comme cela
		// sans reflect
		// FSIndex tokenAnnotationFSIdx =
		// aJCas.getAnnotationIndex(TokenAnnotation.type);
		// avec reflect
		// FSIndex<Annotation> inputAnnotationFSIdx = aJCas
		// .getAnnotationIndex(inputAnnotationType);
		
		
		Iterator<Annotation> absoluteContextAnnotationIndexIterator = null;
		absoluteContextAnnotationIndex = (AnnotationIndex<Annotation>) aJCas
		.getAnnotationIndex(absoluteContextAnnotationType);
		absoluteContextAnnotationIndexIterator = absoluteContextAnnotationIndex
		.iterator();

		// Pour l'absolute context
		// il y a un seul élément à savoir DocumentAnnotation
		while (absoluteContextAnnotationIndexIterator.hasNext()) {

			// Context Annotation suivante de même type
			Annotation absoluteContextAnnotation = null;
			absoluteContextAnnotation = (Annotation) absoluteContextAnnotationIndexIterator
			.next();

			// var to concat the results in case of a view as the output type
			String inputViewsConcatenedResults = "";

			Boolean atLeastOneInputViewIsEqualToOutputView = false;

			log("For each inputView");
			for (String inputViewString : inputViewStringArray) {

				/** -- Prepare the view to be processed **/
				log("Getting the inputViewJCas " + inputViewString);
				JCas inputViewJCas = JCasSofaViewUtils.getView(aJCas,
						inputViewString);

				// On spécifie ici la valeur par défaut de l'outputView
				if (outputViewString == null) {
					// If no output view is specified, we set it to
					// inputViewString
					outputViewString = inputViewString;
				}
				
				//
				absoluteContextAnnotationIndex = (AnnotationIndex<Annotation>) inputViewJCas
						.getAnnotationIndex(absoluteContextAnnotationType);
				absoluteContextAnnotationIndexIterator = absoluteContextAnnotationIndex
						.iterator();
				Annotation inputViewJCasAbsoluteContextAnnotation = null;
				inputViewJCasAbsoluteContextAnnotation = (Annotation) absoluteContextAnnotationIndexIterator
					.next();

				/**
				 * -- In case of the output type is annotation, get the view to
				 * store the result
				 **/
				// si les param annotations sont renseignés alors cela signifie
				// que l'on
				// suppose qu'une vue existe pour accueillir les annotations
				// on effectue ici le getView pour d'éviter de le faire à chaque
				// tour de boucle
				// si l'inputType est annotation
				JCas outputViewJCas = null;
				if (outputType.equalsIgnoreCase(OUTPUTTYPE_ANNOTATION)) {
					log("Getting the outputViewJCas");
					outputViewJCas = JCasSofaViewUtils.getView(aJCas,
							outputViewString);
				}

				log("Getting the Context Annotation index");
				// Iterator<Annotation> inputAnnotationIterator = null;
				// inputAnnotationIterator =
				// inputViewJCas.getAnnotationIndex(inputAnnotationType).subiterator(contextAnnotation);
				//System.out.println("Debug: CommonAE process absoluteContextAnnotation " + absoluteContextAnnotation);
				//System.out.println("Debug: CommonAE process absoluteContextAnnotation " + inputViewJCasAbsoluteContextAnnotation);
				//System.out.println("Debug: CommonAE process inputViewJCas.getViewName() " + inputViewJCas.getViewName());

				FSIterator contextAnnotationsFSIter = null;
				
				//AnnotationUtils.createAnnotation(inputViewJCas, "org.apache.uima.jcas.tcas.DocumentAnnotation", 0, sofaDataString.length());

				contextAnnotationsFSIter = AnnotationCollectionUtils.subiterator(
						inputViewJCas, inputViewJCasAbsoluteContextAnnotation,
						contextAnnotationStringHashMap, false);

				inputViewsConcatenedResults += processInputView(inputViewJCas,
						contextAnnotationsFSIter, inputAnnotationStringHashMap,
						inputFeatureString, outputViewJCas,
						outputAnnotationString, outputFeatureString);

				//
				if (inputViewString.equalsIgnoreCase(outputViewString)) {atLeastOneInputViewIsEqualToOutputView = true; }
			}
			/** -- Create view **/
			// output_v_string est défini ; potentiellement il est égal à
			// input_v ; normalement la vue n'existe pas et est à créer
			if (outputType.equalsIgnoreCase(OUTPUTTYPE_VIEW) && !atLeastOneInputViewIsEqualToOutputView) {
				log("Creating output view");
				// ici on suppose que outputViewString ne correspond à aucune
				// vue existante (a fortiori est différent de inputViewString)
				// et que createView génèrera une erreur si la vue existe déjà
				JCasSofaViewUtils.createView(aJCas, outputViewString,
						inputViewsConcatenedResults, outputViewTypeMimeString);
			}
			else if (outputType.equalsIgnoreCase(OUTPUTTYPE_VIEW) && atLeastOneInputViewIsEqualToOutputView) {
				System.err.println("Warning: "+this.getClass().getName()+" outputType.equalsIgnoreCase(OUTPUTTYPE_VIEW) && atLeastOneInputViewIsEqualToOutputView ; The process may work, if it s not the case you may search why because of this warning");
			}
		}
		
		Date endDate = JavaUtilities.getNow();
		System.err.println("Info: "+this.getClass().getName()+" ends at " + JavaUtilities.stringFormatADate(endDate) + " after " + JavaUtilities.dateDiff(startDate, endDate)+" milliseconds");
		
		
	}

	/**
	 * From the given inputView, process sequentially each ContextAnnotation.
	 * Returns the contatenated results obtained for each Context Annotation.
	 * 
	 * @param inputViewJCas
	 *            the CAS View over which the process is performed
	 * @param contextAnnotationsFSIter
	 *            FSIterator of context Annotations
	 * @param inputAnnotationStringHashMap
	 *            List of Input Annotation names to process
	 * @param inputFeatureString
	 *            Feature name of the inputAnnotation whose String Value will be
	 *            actually processed
	 * @param outputViewJCas
	 *            the CAS View to update with potential future created
	 *            annotations
	 * @param outputAnnotationString
	 *            Name of the annotation to create as the analysis result
	 * @param ouputFeatureString
	 *            Feature name of the annotation whose string value will contain
	 *            the analysis result
	 * 
	 * @return Contatenated results obtained for each Context Annotation
	 * 
	 * @throws AnalysisEngineProcessException
	 */
	protected String processInputView(
			JCas inputViewJCas,
			FSIterator contextAnnotationsFSIter,
			HashMap<String, Integer> inputAnnotationStringHashMap,
			String inputFeatureString, 
			JCas outputViewJCas,
			String outputAnnotationString, 
			String ouputFeatureString)
	throws AnalysisEngineProcessException {
		log("AnalysisEngine - processInputView");
		//System.out.println("Debug: CommonAE processInputView");
		
		// var to concat the results in case of a view as the output type
		String contextAnnotationResultString = "";

		// Pour chaque inputAnnotation présent dans le context
		while (contextAnnotationsFSIter.hasNext()) {

			//System.out.println("Debug: CommonAE contextAnnotationsFSIter.hasNext()");

			Annotation contextAnnotation = (Annotation) contextAnnotationsFSIter
			.next();

			// Récupère le type d'input annotations
			// Type inputAnnotationType = null;
			// inputAnnotationType = UIMAUtilities.getType(inputViewJCas,
			// inputAnnotationString);
			// Type inputAnnotationType = null;
			// inputAnnotationType = UIMAUtilities.getType(inputViewJCas,
			// inputAnnotationStringHashMap.keySet().iterator().next());

			// Si InputAnnotation n'est pas renseigné
			// alors on traite la valeur String d'une certaine feature de chaque
			// ContextAnnotation
			// Par défaut ContextAnnotation est au moins égal à
			// DocumentAnnotation
			// et possède la feature coveredText
			if (inputAnnotationStringHashMap.isEmpty()) {
				// System.out.println("Debug: contextAnnotation.getType().getName()"+contextAnnotation.getType().getName());
				inputAnnotationStringHashMap.put(contextAnnotation.getType()
						.getName(), 1);
				log("Building an Input Annotations index from the ContextAnnotations");
			}
			// Si InputAnnotation est renseigné
			// alors on construit un iterator ordonné à partir de celles
			// renseignées
			else {
				log("Getting the Input Annotations index ");
			}

			// Récupération de la liste des inputAnnotation
			// Iterator<Annotation> inputAnnotationIterator = null;
			// inputAnnotationIterator =
			// inputViewJCas.getAnnotationIndex(inputAnnotationType).subiterator(contextAnnotation);
			FSIterator contextualizedInputAnnotationsFSIter = null;
			contextualizedInputAnnotationsFSIter = AnnotationCollectionUtils.subiterator(
					inputViewJCas, contextAnnotation,
					inputAnnotationStringHashMap, false);

			// contextAnnotationResultString +=
			// processInputAnnotations(inputViewJCas,
			// contextualizedInputAnnotationsFSIter, inputFeatureString,
			// outputViewJCas, outputAnnotationString, ouputFeatureString);

			contextAnnotationResultString += processContextAnnotation(
					inputViewJCas, contextAnnotationsFSIter,
					contextAnnotation, contextualizedInputAnnotationsFSIter,
					inputFeatureString, outputViewJCas, outputAnnotationString, ouputFeatureString);
		}

		return contextAnnotationResultString;
	}

	/**
	 * For a given ContextAnnotation, process each InputAnnotation by analyzing 
	 * the value of its InputFeature
	 * If some OutputAnnotations are specified then they are created and their
	 * OutputFeature is set with the analysis result (Else) it returns the
	 * contatenated results obtained for each InputAnnotation
	 * 
	 * @param inputViewJCas
	 *            the CAS View over which the process is performed
	 * @param contextualizedInputAnnotationsFSIter
	 *            FSIterator of the input Annotations to process which are covered 
	 *            by the contextAnnotation
	 * @param contextAnnotation 
	 *			  the current Context Annotation            
	 * @param inputFeatureString
	 *            Feature name of the Input Annotations whose String Value will
	 *            be actually processed
	 * @param outputViewJCas
	 *            the CAS View to update with potential future created
	 *            annotations
	 * @param outputAnnotationString
	 *            Name of the annotation to create as the analysis result
	 * @param ouputFeatureString
	 *            Feature name of the annotation whose string value will contain
	 *            the analysis result
	 * @return the contatenated results obtained for each InputAnnotation
	 * 
	 * @throws AnalysisEngineProcessException
	 */
	protected String processContextAnnotation(JCas inputViewJCas,
			FSIterator contextAnnotationsFSIter,
			Annotation contextAnnotation, 
			FSIterator contextualizedInputAnnotationsFSIter,
			String inputFeatureString, 
			JCas outputViewJCas,
			String outputAnnotationString, 
			String ouputFeatureString) throws AnalysisEngineProcessException {
		log("AnalysisEngine - processContextAnnotation");
		//System.out.println("Debug: CommonAE processContextAnnotation");

		
		String commandResultString = "";

		// Pour chaque inputAnnotation présent dans le context
		while (contextualizedInputAnnotationsFSIter.hasNext()) {

			//
			String commandLocalResultString = processInputAnnotation(inputViewJCas,
					contextAnnotationsFSIter,
					contextAnnotation, 
					contextualizedInputAnnotationsFSIter,
					contextualizedInputAnnotationsFSIter.next(),
					inputFeatureString,
					outputViewJCas,
					outputAnnotationString, 
					ouputFeatureString);

			// L'output_type est view
			// On stocke les résultats obtenus pour chaque annotation
			// On copiera le tout dans le sofaDataString en une seule fois
			log("Concating the result");
			commandResultString += commandLocalResultString;

		}
		return commandResultString;
	}

	/**
	 * Process a given InputAnnotation by analyzing 
	 * the value of its InputFeature
	 * 
	 * @param inputViewJCas
	 *            the CAS View over which the process is performed
	 * @param contextAnnotation 
	 * 	 		  the current Context Annotation    
	 * @param contextualizedInputAnnotationsFSIter
	 *            FSIterator of the input Annotations to process
	 * @param inputFeatureString
	 *            Feature name of the Input Annotations whose String Value will
	 *            be actually processed
	 * @param outputViewJCas
	 *            the CAS View to update with potential future created
	 *            annotations
	 * @param outputAnnotationString
	 *            Name of the annotation to create as the analysis result
	 * @param ouputFeatureString
	 *            Feature name of the annotation whose string value will contain
	 *            the analysis result
	 * @return the contatenated results obtained for each InputAnnotation
	 * 
	 * @throws AnalysisEngineProcessException
	 */
	protected String processInputAnnotation(JCas inputViewJCas,
			FSIterator contextAnnotationsFSIter,
			Annotation contextAnnotation, 
			FSIterator contextualizedInputAnnotationsFSIter, 
			Object inputAnnotationObject,
			String inputFeatureString, 
			JCas outputViewJCas,
			String outputAnnotationString, 
			String ouputFeatureString) throws AnalysisEngineProcessException {

		log("AnalysisEngine - processInputAnnotation");

		//System.out.println("Debug: CommonAE processInputAnnotation");

		
		// Récupère le texte à traiter et ses offsets qui pourront
		// éventuellement servir
		// si l'outputType est Annotation
		log("Getting the current annotation to be proceeded");

		// Récupère et cast l'inputAnnotation courante à manipuler
		Class annotationClass = inputAnnotationObject.getClass();
		// if (annotationClass != null ) {
		String className = "null";
		className = annotationClass.getName(); // .toString(
		// System.out.println("Debug: class>"+className+"<");
		Class<Annotation> inputAnnotationClass = AnnotationUtils
		.getAnnotationClass(className);

		Annotation inputAnnotation = (Annotation) inputAnnotationObject;
		inputAnnotationClass.cast(inputAnnotation);
		// System.out.println("inputAnnotationType.getName()>"+inputAnnotation.getType().getName()+"<");
		// System.out.println("inputAnnotation.coveredText>"+inputAnnotation.getCoveredText()+"<");
		// System.out.println("inputAnnotation.begin>"+inputAnnotation.getBegin()+"<");
		// System.out.println("inputAnnotation.end>"+inputAnnotation.getEnd()+"<");

		// Invoque la récupération de la valeur dont l'inputFeatureString
		// est spécifiée pour l'annotation courante
		String inputTextToProcess = "";
		// inputTextToProcess = inputAnnotation.getCoveredText();

		inputTextToProcess = FeatureUtils.invokeFeatureGetterMethod(
				inputAnnotation,
				FeatureUtils.getFeatureGetterMethod(inputAnnotationClass,
						inputFeatureString)).toString();
		// log ("Debug: inputTextToProcess>"+inputTextToProcess+"<");

		int beginFeatureValueFromAnnotationToCreate;
		int endFeatureValueFromAnnotationToCreate;
		beginFeatureValueFromAnnotationToCreate = inputAnnotation
		.getBegin();
		endFeatureValueFromAnnotationToCreate = inputAnnotation.getEnd();

		/** -- Execute and get result **/
		log("Executing and getting the result");
		String commandLocalResultString = "";
		commandLocalResultString = processAnnotationFeatureStringValue(
				inputViewJCas, inputTextToProcess,
				beginFeatureValueFromAnnotationToCreate,
				endFeatureValueFromAnnotationToCreate);

		// Soit pour chaque annotation en entrée à traiter soit pour la vue
		// en entrée
		if (outputType.equalsIgnoreCase(OUTPUTTYPE_ANNOTATION)) {
			/** -- Create annotation **/
			log("Creating output annotation");
			HashMap<String, Object>	featuresHashMap = new HashMap<String, Object>();
			featuresHashMap.put("begin", String.valueOf(beginFeatureValueFromAnnotationToCreate));
			featuresHashMap.put("end", String.valueOf(endFeatureValueFromAnnotationToCreate));
			featuresHashMap.put(outputFeatureString, commandLocalResultString);
			AnnotationUtils.createAnnotation(outputViewJCas, outputAnnotationString, featuresHashMap);
			// @deprecated
			// AnnotationUtilities.createAnnotation(outputViewJCas,
			//		outputAnnotationString,
			//		beginFeatureValueFromAnnotationToCreate,
			//		endFeatureValueFromAnnotationToCreate,
			//		outputFeatureString, commandLocalResultString);
		}

		return commandLocalResultString;
	}

	/**
	 * This method corresponds to the actual process to perform on the
	 * String Value of a given Feature of a given Annotation
	 * 
	 * By default it echoes the text to process
	 * 
	 * @param inputViewJCas
	 *            the CAS view that will be processed.
	 * @param inputTextToProcess
	 *            the text to process (actually it corresponds to the String
	 *            Value of a given Feature of a given Annotation )
	 * @param beginFeatureValue
	 *            the begin offset of the Annotation whose one Feature is going
	 *            to be processed
	 * @param endFeatureValue
	 *            the end offset of the Annotation whose one Feature is going to
	 *            be processed
	 * 
	 * @throws AnalysisEngineProcessException
	 *             if something wrong happened while processing this CAS view.
	 * 
	 * @return the result of the performed processing
	 */
	protected String processAnnotationFeatureStringValue(
			JCas inputViewJCas, 
			String inputTextToProcess,
			int beginFeatureValue, 
			int endFeatureValue)
	throws AnalysisEngineProcessException {

		
		return inputTextToProcess;
	}

	/**
	 * This method is invoked when the analysis has to be processed for some
	 * views
	 * 
	 * @deprecated
	 * 
	 * @param aJCas
	 *            the CAS over which the process is performed
	 * @param inputViewString
	 * @param contextAnnotationType
	 *            Type name of the annotations to consider as the context
	 *            annotations in which the process will be performed
	 * @param inputAnnotationType
	 *            Type name of the annotations to consider as the token units to
	 *            be processed
	 * @param inputFeatureString
	 * @param outputViewString
	 * @param outputViewTypeMimeString
	 * @param outputAnnotationString
	 * @param ouputFeatureString
	 * @throws AnalysisEngineProcessException
	 */
	private void processInputViewType(JCas aJCas, String inputViewString,
			String contextAnnotationString,
			String[] inputAnnotationStringArray, String inputFeatureString,
			String outputViewString, String outputViewTypeMimeString,
			String outputAnnotationString, String ouputFeatureString)
	throws AnalysisEngineProcessException {

		/** -- Prepare the view to be processed **/
		log("Getting the inputViewJCas");
		JCas inputViewJCas = JCasSofaViewUtils.getView(aJCas, inputViewString);

		/**
		 * -- In case of the output type is annotation, get the view to store
		 * the result
		 **/
		// si les param annotations sont renseignés alors cela signifie que l'on
		// suppose qu'une vue existe pour accueillir les annotations
		// on effectue ici le getView pour d'éviter de le faire à chaque tour de
		// boucle
		// si l'inputType est annotation
		JCas outputViewJCas = null;
		String inputTextToProcess = "";
		inputTextToProcess = inputViewJCas.getSofaDataString();
		int beginFeatureValueFromAnnotationToCreate = 0;
		int endFeatureValueFromAnnotationToCreate = inputViewJCas
		.getSofaDataString().length(); // +1;

		/** -- Execute and get result **/
		log("Executing and gettint the result");
		String commandResultString = "";
		commandResultString = processAnnotationFeatureStringValue(
				inputViewJCas, inputTextToProcess,
				beginFeatureValueFromAnnotationToCreate,
				endFeatureValueFromAnnotationToCreate);

		// Soit pour chaque annotation en entrée à traiter soit pour la vue en
		// entrée
		if (outputType.equalsIgnoreCase(OUTPUTTYPE_ANNOTATION)) {
			/** -- Create annotation **/
			log("Getting the outputViewJCas");
			outputViewJCas = JCasSofaViewUtils.getView(aJCas, outputViewString);

			log("Creating output annotation");
			// createANewAnnotation(aJCas,
			// inputAnnotation.getBegin(),inputAnnotation.getEnd(),commandLocalResultString);
			HashMap<String, Object>	featuresHashMap = new HashMap<String, Object>();
			featuresHashMap.put("begin", String.valueOf(beginFeatureValueFromAnnotationToCreate));
			featuresHashMap.put("end", String.valueOf(endFeatureValueFromAnnotationToCreate));
			featuresHashMap.put(outputFeatureString, commandResultString);
			AnnotationUtils.createAnnotation(outputViewJCas, outputAnnotationString, featuresHashMap);
			// @deprecated
			//AnnotationUtilities.createAnnotation(outputViewJCas,
			//		outputAnnotationString,
			//		beginFeatureValueFromAnnotationToCreate,
			//		endFeatureValueFromAnnotationToCreate, outputFeatureString,
			//		commandResultString);
		} else {
			/** -- Create view **/
			// L'output_type est view
			// On stocke les résultats obtenus pour chaque annotation
			// On copiera le tout dans le sofaDataString en une seule fois
			// if (commandResultString == null ) {commandResultString =
			// commandLocalResultString;}
			// else {
			log("Creating output view");
			// ici on suppose que outputViewString ne correspond à aucune vue
			// existante (a fortiori est différent de inputViewString)
			// et que createView génèrera une erreur si la vue existe déjà
			JCasSofaViewUtils.createView(aJCas, outputViewString,
					commandResultString, outputViewTypeMimeString);

		}

	}

	/**
	 * Log messages
	 * 
	 * @param message
	 *            to log
	 */
	protected void log(String message) {
		// getContext()
		// .getLogger()
		// .log(Level.FINEST, COMPONENT_ID + "- "+ message);
		//System.out.println(COMPONENT_ID + "- " + message);
	}

	/**
	 * @return the cOMPONENT_NAME
	 */
	protected String getCOMPONENT_NAME() {
		return COMPONENT_NAME;
	}

	/**
	 * @param cOMPONENTNAME
	 *            the cOMPONENT_NAME to set
	 */
	protected void setCOMPONENT_NAME(String cOMPONENTNAME) {
		COMPONENT_NAME = cOMPONENTNAME;
	}

	/**
	 * @return the cOMPONENT_VERSION
	 */
	protected String getCOMPONENT_VERSION() {
		return COMPONENT_VERSION;
	}

	/**
	 * @param cOMPONENTVERSION
	 *            the cOMPONENT_VERSION to set
	 */
	protected void setCOMPONENT_VERSION(String cOMPONENTVERSION) {
		COMPONENT_VERSION = cOMPONENTVERSION;
	}

	/**
	 * @return the cOMPONENT_ID
	 */
	protected String getCOMPONENT_ID() {
		return COMPONENT_ID;
	}

	/**
	 * @param cOMPONENTID
	 *            the cOMPONENT_ID to set
	 */
	protected void setCOMPONENT_ID(String cOMPONENTID) {
		COMPONENT_ID = cOMPONENTID;
	}

	/**
	 * @return the runIdString
	 */
	protected String getRunIdString() {
		return runIdString;
	}

	/**
	 * @param runIdString
	 *            the runIdString to set
	 */
	protected void setRunIdString(String runIdString) {
		this.runIdString = runIdString;
	}

	/**
	 * @return the inputViewStringArray
	 */
	protected String[] getInputViewStringArray() {
		return inputViewStringArray;
	}

	/**
	 * @return the contextAnnotationStringArray
	 */
	protected String[] getContextAnnotationStringArray() {
		return contextAnnotationStringArray;
	}

	/**
	 * @return the contextAnnotationStringHashMap
	 */
	protected HashMap<String, Integer> getContextAnnotationStringHashMap() {
		return contextAnnotationStringHashMap;
	}

	/**
	 * @return the inputAnnotationStringArray
	 */
	protected String[] getInputAnnotationStringArray() {
		return inputAnnotationStringArray;
	}

	/**
	 * @return the inputAnnotationStringHashMap
	 */
	protected HashMap<String, Integer> getInputAnnotationStringHashMap() {
		return inputAnnotationStringHashMap;
	}

	/**
	 * @return the inputFeatureString
	 */
	protected String getInputFeatureString() {
		return inputFeatureString;
	}

	/**
	 * @return the outputViewString
	 */
	protected String getOutputViewString() {
		return outputViewString;
	}

	/**
	 * @return the outputViewTypeMimeString
	 */
	protected String getOutputViewTypeMimeString() {
		return outputViewTypeMimeString;
	}

	/**
	 * @return the outputAnnotationString
	 */
	protected String getOutputAnnotationString() {
		return outputAnnotationString;
	}


	/**
	 * @return the outputFeatureString
	 */
	protected String getOutputFeatureString() {
		return outputFeatureString;
	}


	/**
	 * @param outputViewString the outputViewString to set
	 */
	protected void setOutputViewString(String outputViewString) {
		this.outputViewString = outputViewString;
	}


	/**
	 * @param outputAnnotationString the outputAnnotationString to set
	 */
	protected void setOutputAnnotationString(String outputAnnotationString) {
		this.outputAnnotationString = outputAnnotationString;
	}

	/**
	 * @param inputAnnotationString the inputAnnotationString to set
	 */
	protected void setInputAnnotationString(String inputAnnotationString) {
		// TODO 	this.input = inputAnnotationString;
	}

	/**
	 * @param inputFeatureString the inputFeatureString to set
	 */
	protected void setInputFeatureString(String inputFeatureString) {
		this.inputFeatureString = inputFeatureString;
	}


	/**
	 * @param inputAnnotationStringHashMap the inputAnnotationStringHashMap to set
	 */
	protected void setInputAnnotationStringHashMap(
			HashMap<String, Integer> inputAnnotationStringHashMap) {
		this.inputAnnotationStringHashMap = inputAnnotationStringHashMap;
	}
}