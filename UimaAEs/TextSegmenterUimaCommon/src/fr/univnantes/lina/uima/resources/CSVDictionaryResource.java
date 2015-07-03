package fr.univnantes.lina.uima.resources;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.apache.uima.resource.DataResource;
import org.apache.uima.resource.ResourceInitializationException;
import org.apache.uima.resource.SharedResourceObject;

/**
 * Load and parse a dictionary resource 
 * For each entry build a branch of a character tree
 * @author rocheteau, (revised by hernandez)
 *
 */
public class CSVDictionaryResource extends DictionaryResource {

	 static final private String CSV_COLUMN_SEPARATOR = "\t";
	
	 /**
	  * Parse CSV resource file
	  * @param inputStream
	  * @throws Exception
	  */
	 public void doParse(InputStream inputStream) throws Exception {
		 String delimiter = java.lang.System.getProperty("line.separator");
		 Scanner scanner = new Scanner(inputStream);
		 scanner.useDelimiter(delimiter);
		 while (scanner.hasNext()) {
			 this.doParseCSVLine(scanner.next());
		 }
		 scanner.close();
	 }   

	 /**
	  * Create a corresponding node in the tree
	  * Should receive the rank of the column to use as the key
	  * 
	  */
	 private void doParseCSVLine(String line) throws Exception {
		 if (line == null || line.isEmpty() || line.startsWith("#")) {
			 // do nothing
		 } else {
			 String characters = line.trim();
			 
			 // Here is the code which connects the CSV columns to the internal data structure to store the values (an ArrayList<String>)
			 String[] columns = characters.split(CSV_COLUMN_SEPARATOR);
			 ArrayList<String> values = new ArrayList<String>();
			 for (int i = 1 ; i< columns.length ; i++) {
				 values.add(columns[i]);
			 }
			 
			 // Add the current entry (columns[0]) and its values (values) to the tree (this.getRoot())
			 this.getRoot().add(columns[0],0,values);
		 }
	 }



}
