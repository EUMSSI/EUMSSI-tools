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
import java.util.ArrayList;

public class IOUtilities {

	/**
	 * Utility method to read a given text from a file
	 * fileName doit intégrer le nom du répertoire dans le filename"/"
	 * @throws IOException 
	 */
	public static ArrayList<String> readFromFileNameToLineArray(String fileName) {
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

	public IOUtilities() {
		super();
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

}