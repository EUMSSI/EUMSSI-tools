package fr.univnantes.lina.uima.dataModels;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fr.univnantes.lina.java.util.JavaUtilities;


/**
 * Basic data structure of a tree: the node
 * No tree structure exists but a reference to a root node
 * Some of the nodes are leaves (not always the terminal ones) and contains a list of string values   
 * 
 * @author rocheteau, (revised by hernandez)
 *
 */
public class PrefixTree_Impl implements PrefixTree {

	private PrefixTree_Impl parent;
	private Map<Integer,PrefixTree_Impl> children;

	/**
	 * Is a final node of a branch corresponding to the last character of an entry
	 */
	private boolean leaf;
	
	/**
	 * Associated values (from CSV columns) 
	 * to the branch defined by the path from the root node to the current node 
	 */
	//private List<String> values;
	private List<List<String>> valuesList;

	public PrefixTree_Impl() {
		this.setChildren();
		this.setLeaf(false);
	}

	/*
	 * Setter 
	 */
	public void setParent(PrefixTree_Impl node) {
		this.parent = node;
	}

	private void setChildren() {
		//this.children = new HashMap<Character,Node_Impl>();
		this.children = new HashMap<Integer,PrefixTree_Impl>();

	}

	public void setLeaf(boolean leaf) {
		this.leaf = leaf;
	}

	public void setValues (ArrayList<String> values) {
		if (this.valuesList == null) this.valuesList = new ArrayList<List<String>>();
		List<String> currentValues = new ArrayList<String>();
		currentValues = values;
		this.valuesList.add(currentValues);
		//this.values = new ArrayList<String>();
		//this.values = values;
	}

	/*
	 * Getter
	 */
	private PrefixTree_Impl getParent() {
		return this.parent;
	}

	//private Map<Character,Node_Impl> getChildren() {
	private Map<Integer,PrefixTree_Impl> getChildren() {

		return this.children;
	}

	/**
	 * An implementation of getChild
	 * Define the way to access and test if a character (char Character or codePoint) is present in the children
	 * @see fr.univnantes.lina.uima.dataModels.PrefixTree Interface 
	 */
	@Override
	public PrefixTree_Impl getChild(Character character) {
		return this.getChildren().get(character);
	}

	/**
	 * An implementation of getChild
	 * Define the way to access and test if a character (char Character or codePoint) is present in the children
	 * @see fr.univnantes.lina.uima.dataModels.PrefixTree Interface 
	 */
	@Override
	public PrefixTree_Impl getChild(int codePoint) {
		return this.getChildren().get(codePoint);
	}	
	
	public List<List<String>> getValues () {
	//	public List<String> getValues () {
		//return this.values;
		return this.valuesList;
	}


	/*
	 * 
	 */
	@Override
	public boolean isLeaf() {
		return this.leaf;
	}

	/**
	 * An implementation of the add method to add a new node to the tree
	 * Recursive method which parses the string of the key
	 * 
	 * @param characters the string whose characters have to be added to the tree as nodes
	 * @param index cursor which points to the current character parsed in the characters string
	 * @param values list of values to associate with a leaf node
	 * @see fr.univnantes.lina.uima.dataModels.PrefixTree Interface 
	 */
	@Override
	public void add(String characters, int index, ArrayList<String> values) {
		int length = characters.length(); 
		if (index < length) {
			//char ch = characters.charAt(index);
			//Character character = new Character(ch);
			int currentCodePoint = characters.codePointAt(index);

			//Node_Impl node = this.getChild(character);
			PrefixTree_Impl node = this.getChild(currentCodePoint);

			// the current node character has never been followed by the current character parsed of the string
			if (node == null) {
				// create a new child node for this character
				node = new PrefixTree_Impl();
				//this.getChildren().put(character,node);
				this.getChildren().put(currentCodePoint,node);

			}

			if (!node.isLeaf()) {
				// declare the current child node as a potential leaf 
				// may reset an existing leaf... means duplicate entries
				node.setLeaf((index + 1) == length);
			}

			if (node.isLeaf() && ((index + 1) == length)) {
				// set the array of values 
				node.setValues(values);
			}

			if (node.getParent() == null) {
				// link the child Node to its parent
				node.setParent(this);   
			}

			// recursively call the add method with the following characters 
			node.add(characters, index + 1, values);
		}
	}



	/*
	 * 
	 */
	@Override
	public String toString() {
		String string = "";                     
		//for (Character character : this.getChildren().keySet()) {
		for (Integer charCodePoint : this.getChildren().keySet()) {

			PrefixTree_Impl node = this.getChild(charCodePoint);
			for (int index = 0; index < node.deep(); index++) {
				string += "  ";
			}
			;
			//string += character.toString();
			string += JavaUtilities.codePointToString(charCodePoint);
			if (node.isLeaf()) {
				string += " * ";
			}
			string += "\n";
			string += node.toString();
		}
		return string;
	}

	public int deep() {
		PrefixTree_Impl parent = this.getParent();
		if (parent == null) {
			return 0;
		} else {
			return parent.deep() + 1;
		}
	}

}