package fr.univnantes.lina.uima.resources;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import org.apache.uima.resource.DataResource;
import org.apache.uima.resource.ResourceInitializationException;
import org.apache.uima.resource.SharedResourceObject;

import fr.univnantes.lina.uima.dataModels.PrefixTree_Impl;

/**
 * Load and parse a dictionary resource 
 * For each entry build a branch of a character tree
 * @author rocheteau, (revised by hernandez)
 *
 */
public abstract class DictionaryResource implements SharedResourceObject {

	/**
	 * Root of a character tree, 
	 * a branch stands for an entry of the dictionary, 
	 * a leaf is a special node which contains an array of values associated to the entry 
	 */
	protected PrefixTree_Impl root;

	/**
	 * Inform if the shared resource has been loaded once
	 */
	protected boolean loaded;

	public DictionaryResource() {
		this.setLoaded(false);
		this.setRoot();			// not protected by several calls, should be run once... when the first load occurs I think
	}
	/*
	 * Getter, Setter
	 */
	private void setRoot() {
		this.root = new PrefixTree_Impl();
	}

	private void setLoaded(boolean enabled) {
		this.loaded = enabled;
	}

	public PrefixTree_Impl getRoot() {
		return this.root;
	}

	private boolean isLoaded() {
		return this.loaded;
	}

	/*
	 * Load a resource 
	 */
	 @Override
	 public void load(DataResource data) throws ResourceInitializationException {
		 try {
			 if (!this.isLoaded()) {
				 this.setLoaded(true);
				 this.doParse(data.getInputStream());
			 }
		 } catch (Exception e) {
			 throw new ResourceInitializationException(e);
		 }
	 }

	 public void doLoad(File file) throws Exception {
		 InputStream inputStream = new FileInputStream(file);
		 this.doParse(inputStream);
	 }

	 /**
	  * Parse the resource
	  * Depending on the resource format (CSV, XML), should switch the stream on the correct parser 
	  */
	 abstract public void doParse(InputStream inputStream) throws Exception;




}
