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
package fr.univnantes.lina.java.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.resource.ResourceInitializationException;


/**
 * Java Utilities
 * 
 * @author hernandez-n
 *
 */
public class JavaUtilities {


	public static final String DATE_FORMAT_NOW = "yyyy-MM-dd HH:mm:ss";
	public static final long MILISECOND_PER_DAY = 24 * 60 * 60 * 1000; 

	/**
	 * 
	 * @return the current Date
	 */
	public static Date getNow() {
	    Calendar cal = Calendar.getInstance();
	    return cal.getTime();

	  }
	
	/**
	 * 
	 * @return a Date as a formatted String
	 * 
	 */
	public static String stringFormatADate(Date aDate) {
		    SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW);
	    
	    return sdf.format(aDate.getTime());
	    // formats
//	    System.out.println(DateUtils.now("dd MMMMM yyyy"));
//	     System.out.println(DateUtils.now("yyyyMMdd"));
//	     System.out.println(DateUtils.now("dd.MM.yy"));
//	     System.out.println(DateUtils.now("MM/dd/yy"));
//	     System.out.println(DateUtils.now("yyyy.MM.dd G 'at' hh:mm:ss z"));
//	     System.out.println(DateUtils.now("EEE, MMM d, ''yy"));
//	     System.out.println(DateUtils.now("h:mm a"));
//	     System.out.println(DateUtils.now("H:mm:ss:SSS"));
//	     System.out.println(DateUtils.now("K:mm a,z"));
//	     System.out.println(DateUtils.now("yyyy.MMMMM.dd GGG hh:mm aaa"));
	  }
	
	/**
	 * 
	 * @return display the current Date
	 * 
	 */
	public static String now() {
	    Calendar cal = Calendar.getInstance();
	        
	    return stringFormatADate (Calendar.getInstance().getTime());
	    // formats
//	    System.out.println(DateUtils.now("dd MMMMM yyyy"));
//	     System.out.println(DateUtils.now("yyyyMMdd"));
//	     System.out.println(DateUtils.now("dd.MM.yy"));
//	     System.out.println(DateUtils.now("MM/dd/yy"));
//	     System.out.println(DateUtils.now("yyyy.MM.dd G 'at' hh:mm:ss z"));
//	     System.out.println(DateUtils.now("EEE, MMM d, ''yy"));
//	     System.out.println(DateUtils.now("h:mm a"));
//	     System.out.println(DateUtils.now("H:mm:ss:SSS"));
//	     System.out.println(DateUtils.now("K:mm a,z"));
//	     System.out.println(DateUtils.now("yyyy.MMMMM.dd GGG hh:mm aaa"));
	  }

	/**
	 * 
	 * @return the diff between two dates expressed in milliseconds (long)
	 */
	public static long dateDiff(Date startDate, Date endDate) {
//		System.out.println("Debug: startDate.getTime()"+startDate.getTime());
//		System.out.println("Debug: endDate.getTime()"+endDate.getTime());
//		System.out.println("Debug: diff"+ (endDate.getTime()- startDate.getTime()));
		//Math.round(Math.abs((endDate.getTime()- startDate.getTime())/MILISECOND_PER_DAY))
		return 	endDate.getTime()- startDate.getTime();
		 
	}
	
	/**
	 * Fusionne deux tableaux de Type T en un seul
	 * */
	public static <T> T[] concat (T[] a, T[] b) {
		final int alen = a.length;
		final int blen = b.length;
		final T[] result = (T[]) java.lang.reflect.Array.
		newInstance(a.getClass().getComponentType(), alen + blen);
		System.arraycopy(a, 0, result, 0, alen);
		System.arraycopy(b, 0, result, alen, blen);
		return result;
	}

	/**
	 * Create a temporary text file with a text given in parameter and return its absolute path
	 * @param prefixTmpFile
	 * @param suffixTmpFile
	 * @param text
	 * @return fileAbsPathStrg
	 * @throws IOException 
	 */
	public static String createTempTextFile (String prefixTmpFile, String suffixTmpFile, String text) throws IOException {
		String fileAbsPathStrg = null;
		File file = File.createTempFile(prefixTmpFile, suffixTmpFile);
		fileAbsPathStrg = file.getAbsolutePath();

		FileWriter fstream = new FileWriter(fileAbsPathStrg);
		BufferedWriter out = new BufferedWriter(fstream);
		out.write(text);
		out.close();
		return fileAbsPathStrg;
	}


	/**
	 * Utility method to write a given text to a file
	 * fileName doit intégrer le nom du répertoire +"/"
	 */
	public static boolean writeStringArrayToFileName(String fileName, String[] lines  ) {

		Writer out = null;
		//System.out.println("Debug: full Output File Name "+ fileName);

		try {

			out = new OutputStreamWriter(new FileOutputStream(fileName),"UTF8");

			for (int l = 0 ;  l< lines.length ; l++) {
				//System.out.println("Debug: current line "+ lines[l]);

				out.write(lines[l]);
			}
			out.close();
		} catch (FileNotFoundException ex) {
			return (false);
		} catch (IOException ex) {
			return (false);
		}
		return (true);

	}
	
	/**
	 * Utility method to write a given text to a file
	 * fileName doit intégrer le nom du répertoire +"/"
	 */
	public static boolean writeStringToFileName(String fileName, String line  ) {

		Writer out = null;
		//System.out.println("Debug: full Output File Name "+ fileName);

		try {

			out = new OutputStreamWriter(new FileOutputStream(fileName),"UTF8");

			out.write(line);

			out.close();
		} catch (FileNotFoundException ex) {
			return (false);
		} catch (IOException ex) {
			return (false);
		}
		return (true);

	}


	/**
	 * Utility method to read a given text from a file
	 * fileName doit intégrer le nom du répertoire dans le filename"/"
	 * @throws IOException 
	 */
	public static ArrayList<String> readFromFileNameToLineArray(String fileName){
		System.out.println("Debug: full input File Name "+ fileName);
		ArrayList<String> lines = new ArrayList<String>();

		// Open the file that is the first 
		// command line parameter
		FileInputStream fstream = null;
		try {
			fstream = new FileInputStream(fileName);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// Get the object of DataInputStream
		DataInputStream in = new DataInputStream(fstream);
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		String strLine; 
		//Read File Line By Line
		try {
			while ((strLine = br.readLine()) != null)   {
				lines.add(strLine);
				// Print the content on the console
				//System.out.println (strLine);
			}
			//Close the input stream
			in.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return lines;
	}
	

	/**
	 * Utility method to read a given text from a file
	 * fileName doit intégrer le nom du répertoire dans le filename"/"
	 * @throws IOException 
	 */
	public static ArrayList<String> readFromFileToLineArray(File file){
		System.out.println("Debug: full input File Name "+ file);
		ArrayList<String> lines = new ArrayList<String>();

		// Open the file that is the first 
		// command line parameter
		FileInputStream fstream = null;
		try {
			fstream = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// Get the object of DataInputStream
		DataInputStream in = new DataInputStream(fstream);
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		String strLine; 
		//Read File Line By Line
		try {
			while ((strLine = br.readLine()) != null)   {
				lines.add(strLine);
				// Print the content on the console
				//System.out.println (strLine);
			}
			//Close the input stream
			in.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return lines;
	}
	
	/**
	 * Retrieves a list of files (not the directories) from a given directory 
	 * @throws FileNotFoundException 
	 */
	public static ArrayList<File> retrieveFilesFromDirectory(String directoryName) throws FileNotFoundException {

		File inputDirFile = new File(directoryName);
		if (!inputDirFile.exists()) {
			String errorMsg =    "The input dir,  " + directoryName + ", is not valid." ;
			throw new FileNotFoundException  (errorMsg); 
			//MESSAGE_DIGEST, errorMsg,
			//new Object[]{inputCSVDirString});
		}

		//get list of files (not subdirectories) in the specified directory
		ArrayList<File> inputFileArrayList = new ArrayList();
		File[] files = inputDirFile.listFiles();
		for (int i = 0; i < files.length; i++) {
			if (!files[i].isDirectory()) {
				inputFileArrayList.add(files[i]);  
			}
		}
		return inputFileArrayList;
	}
	
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
	public static String codePointToString (int codePoint) {
		char[] currentChars = Character.toChars(codePoint);
		String currentString = "";
		for (int i = 0 ; i < currentChars.length ; i++) {
			currentString += currentChars[i];
		}
		return currentString;
	}


}
