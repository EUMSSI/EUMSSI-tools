package fr.univnantes.lina.uima.dataModels;

import java.util.ArrayList;
import java.util.List;

public interface PrefixTree {


	public boolean isLeaf();

	/**
	 * Get a character from a Character char object
	 * The implementation of this method corresponds to the way a character is searched among the children which are stored in a Map
	 * The simple implementation is a get(key) method. 
	 * If you wish to be case sensitive you should reimplement this method. 
	 * @param c
	 * @return
	 */
	public PrefixTree getChild(Character c);

	/**
	 * Get a character from its Unicode code point
	 * The implementation of this method corresponds to the way a character is searched among the children which are stored in a Map
	 * The simple implementation is a get(key) method. 
	 * If you wish to be case sensitive you should reimplement this method. 
	 *  
	 * @param c
	 * @return
	 */
	public PrefixTree getChild(int c);

	/**
	 * Add a new node to the tree
	 * Recursive method which parses a string key
	 * 
	 * @param characters the string whose characters have to be added to the tree as nodes
	 * @param index cursor which points to the current character parsed in the characters string
	 * @param values list of values to associate with a leaf node
	 */
	public void add(String characters,int index,ArrayList<String> values);
	
	//	public List<String> getValues();
	public List<List<String>> getValues();


}