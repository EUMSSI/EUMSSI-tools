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
import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.resource.ResourceInitializationException;


/**
 * Java Utilities
 * 
 * @author hernandez-n
 *
 */
public class CollectionUtilities {


	static class HashMapStringKeyComparatorByIntegerValue implements Comparator<String> {

		Map<String, Integer> base;
		public HashMapStringKeyComparatorByIntegerValue(Map<String, Integer> base) {
			this.base = base;
		}

		// Note: this comparator imposes orderings that are inconsistent with equals.    
		public int compare(String a, String b) {
			if (base.get(a) >= base.get(b)) {
				return -1;
			} else {
				return 1;
			} // returning 0 would merge keys
		}
	}
	/**
	 * Sort an hashmap with string keys by int value 
	 * @return 
	 */
	public static  TreeMap<String,Integer> sortStringIntegerHashMapByValue (Map<String,Integer> anHashMapToSort) {
		//Map<String,Integer> aMWECatintsMap = new HashMap<String,Integer>();
		HashMapStringKeyComparatorByIntegerValue sortedHashMapObject =  new HashMapStringKeyComparatorByIntegerValue(anHashMapToSort);
		TreeMap<String,Integer> aSortedMap = new TreeMap<String,Integer>(sortedHashMapObject);
		//if (anHashMapToSort != null)
		aSortedMap.putAll(anHashMapToSort);
		//else {
		//	System.err.println("Debug: anHashMapToSort.isEmpty()");
		//}
		return aSortedMap;
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

}
