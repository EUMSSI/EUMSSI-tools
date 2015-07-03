package fr.univnantes.lina.java.util;

import org.apache.uima.analysis_engine.AnalysisEngineProcessException;

public class StringUtilities extends IOUtilities {

	/**
	 * Build a random String name based on the current System Time in milliseconds
	 * (could be more sure with Calendar?) and with the name of the calling Class 
	 * if the parameter value is <code>this</code>. Leave to <code>null</code> for 
	 * the other case.
	 */
	public static String buildARandomStringName(Object obj)
			throws AnalysisEngineProcessException {
				
				if (obj == null) {
					return Long.toString(System.currentTimeMillis());
				}
				else { 
					return obj.getClass().getName()+ "-" + Long.toString(System.currentTimeMillis());
				}
			}

	/**
	 * Convert unicode CodePoint to String
	 */
	public static String codePointToString(int codePoint) {
		char[] currentChars = Character.toChars(codePoint);
		String currentString = "";
		for (int i = 0 ; i < currentChars.length ; i++) {
			currentString += currentChars[i];
		}
		return currentString;
	}

	public StringUtilities() {
		super();
	}

}