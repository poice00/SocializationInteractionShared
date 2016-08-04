package com.socialization.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * A simple search engine with the following features:
 * 
 * 1. build inverted index for terms in documents and store in an index file. The index will be updated as more documents are added. 
 *    And the index is loaded into memory during startup
 * 2. examine stop words
 * 3. simple query by splitting the query string into words and returning the list of the names of documents with one or more words in them
 * 4. simple ranking of the search result based on the number of search words in the documents
 * 
 * 
 * Preparation:
 *   1. a document folder where all the documents resides
 *   2. path of the index file. An index file has the inverted index of term mapped to a list of doc Ids. This index will be updated and 
 *      the file will be updated as documents are added.
 *   3. path of a document name index file. This file has the docId to docName mapping. This file will be updated as documents are added.
 *   4. a stop word file with the stop words
 *   
 * @author dennis li
 *
 */
public class SimpleSearchEngineImpl implements SimpleSearchEngine {
	private String docFolderPath = null;
	private String indexFilePath = null;
	private String docNameIndexPath = null;
	private String stopWordsFilePath = null;
	// search index map, mapping words to a set of document Ids
	private Map<String, Set<Integer>> searchIndex = new HashMap<String, Set<Integer>>();
	// doc name index map, mapping the docId to docName
	private Map<Integer, String> docNames = new HashMap<Integer, String>();
	// a set of stop words
	private Set<String> stopWords = new HashSet<String>();
	
	public SimpleSearchEngineImpl(String docFolderPath, String indexFilePath, String docNameIndexPath, String stopWordsFilePath) {
		if (docFolderPath.charAt(docFolderPath.length()-1) == '/')
			this.docFolderPath = docFolderPath;
		else
			this.docFolderPath = docFolderPath + "//";
		this.indexFilePath = indexFilePath;
		this.docNameIndexPath = docNameIndexPath;
		this.stopWordsFilePath = stopWordsFilePath;
	}
	
	/*
	 * initialize
	 * 
	 * load the search index, doc name index, stop words from files if they exist
	 * 
	 */
	public void init() {
		loadIndexFile();
		loadDocNameIndex();
		loadStopWords();
	}
	
	/*
	 * simple query by splitting the query into search terms and looking up the index, 
	 * ranking results by the number of search terms appearing in a document
	 * 
	 * @return the list of document names
	 * 
	 */
	public List<String> query(String queryStr) {
		// split the query string into query terms
		String[] terms = queryStr.split("[\\s]+");

		// look up the search index and generate fildId -> count map
        HashMap<Integer,Integer> map = new HashMap<Integer,Integer>();
		for (int i=0; i<terms.length; i++) {
			Set<Integer> docIds = searchIndex.get(terms[i]);
			if (docIds != null && docIds.size() > 0) {
				for (Integer id : docIds) {
					Integer count = map.get(id);
					if (count == null)
						map.put(id, new Integer(1));
					else
						map.put(id, count+1);
				}
			}
		}
		
		// rank the search result, simply based on the number of query terms appearing in a document. The more the higher the rank.
        ValueComparator bvc =  new ValueComparator(map);
        TreeMap<Integer,Integer> sortedMap = new TreeMap<Integer,Integer>(bvc);

        sortedMap.putAll(map);
        StringBuilder builder = new StringBuilder();
        Iterator<Integer> iter = sortedMap.keySet().iterator();
        if (iter.hasNext())
        	builder.append(docNames.get(iter.next()));
        while (iter.hasNext())
        	builder.append(","+docNames.get(iter.next()));
        
        System.out.println(" search results: "+builder.toString());
		return null;
	}
	
	/**
	 * add a document and update the index
	 * 
	 * @param docName
	 */
	public void addDoc(String docName) {		
		BufferedReader br = null;
		try {
			Integer fileId = docNames.size();
			// find the next available file Id
			while (docNames.containsKey(fileId))
				fileId++;
			docNames.put(fileId, docName);
			String line;
			br = new BufferedReader(new FileReader(docFolderPath + docName));
			while((line = br.readLine()) != null) {
				line = line.toLowerCase();
				String[] terms = line.split("[^a-z]+");
				for (int i = 0; i < terms.length; i++) {
					// check stop word
					if (terms[i].length() <= 1 || stopWords.contains(terms[i]))
						continue;
					Set<Integer> docIds = searchIndex.get(terms[i]);
					// create docIds list and add to the search index
					if (docIds == null) {
						docIds = new TreeSet<Integer>();
						docIds.add(fileId);
						searchIndex.put(terms[i], docIds);
					}
					else
						docIds.add(fileId);
				}
			}
			//printSearchIndex();
		}
		catch (IOException ex) {
			System.err.println("error accessing doc: " +ex);
		}	
		finally {
			if (br != null) {
				try {
					br.close();
				}
				catch (IOException ex) {
					System.err.println("error closing doc: "+ ex);
				}
			}
		}		
		//printDocNameIndexFile();
	}
	
	/**
	 * load the search index from file.
	 * 
	 * The format of each line of the index file is as follows:
	 *    word: docId1,docId2,docId3,...
	 *  
	 * Example:
	 *    will: 0,1,2,3
	 *    wise: 2
	 */
	public void loadIndexFile() {
		
		BufferedReader br = null;
		try {
			File file = new File(indexFilePath);			 
			// if file doesnt exists, then create it
			if (!file.exists()) {
				file.createNewFile();
				return;
			}
			
			br = new BufferedReader(new FileReader(file));
			String line;
			while((line = br.readLine()) != null) {
				int i = line.indexOf(':');
				String key = line.substring(0, i);
				String[] terms = line.substring(i+1).trim().split("[,\\s]+");
				Set<Integer> set = new TreeSet<Integer>();
				for (String term : terms) {
					term = term.trim();
					if (term.length() > 0) {
						try {
							set.add(Integer.valueOf(term));
						}
						catch (NumberFormatException ex) {
							System.err.println("loadIndexFile: bad doc Id in line: "+line);
						}
					}
				}
				searchIndex.put(key, set);
			}
		}
		catch (IOException ex) {
			System.err.println("error accessing file: " +ex);
		}	
		finally {
			if (br != null) {
				try {
					br.close();
				}
				catch (IOException ex) {
					System.err.println("error closing file: "+ ex);
				}
			}
		}			
	}
	
	/**
	 * load the document name index from file
	 * 
	 * The format is:
	 *    docId docName
	 * 
	 * example:
	 *  0 braveNewWord
	 *  1 weAre
	 * 
	 */
	public void loadDocNameIndex() {
		BufferedReader br = null;
		try {
			File file = new File(docNameIndexPath);			 
			// if file doesnt exists, then create it
			if (!file.exists()) {
				file.createNewFile();
				return;
			}
			br = new BufferedReader(new FileReader(file));
			String line;
			while((line = br.readLine()) != null) {
				String[] terms = line.split("[\\s]+");
				if (terms[0].length() > 0 && terms[1].length() > 0) {
					try {
						docNames.put(Integer.valueOf(terms[0]), terms[1]);
					}
					catch (NumberFormatException ex) {
						System.err.println("loadDocNameIndex: bad doc Id in line: "+line);
					}					
				}
			}
		}
		catch (IOException ex) {
			System.err.println("error accessing file: " +ex);
		}	
		finally {
			if (br != null) {
				try {
					br.close();
				}
				catch (IOException ex) {
					System.err.println("error closing file: "+ ex);
				}
			}
		}		
	}
	
	/**
	 * load the stop words from file
	 * the format is one word per line
	 */
	public void loadStopWords() {
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(stopWordsFilePath));
			String line;
			while((line = br.readLine()) != null) {
				line = line.trim();
				if (line.length() > 0)
					stopWords.add(line);
			}
		}
		catch (IOException ex) {
			System.err.println("error accessing file: " +ex);
		}	
		finally {
			if (br != null) {
				try {
					br.close();
				}
				catch (IOException ex) {
					System.err.println("error closing file: "+ ex);
				}
			}
		}			
	}
	
	/**
	 * output the search index to file
	 */
	public void printSearchIndex() {
		Iterator<String> iterator = (new TreeSet<String>(searchIndex.keySet())).iterator();  
		
		try {
			FileWriter fw = new FileWriter(indexFilePath, false); // overwrite
	
			while (iterator.hasNext()) {  
			   String key = iterator.next().toString();  
			   Set<Integer> idSet = searchIndex.get(key);  
			   
			   StringBuilder builder = new StringBuilder();
			   fw.write(key+": ");
			   Iterator<Integer> iter = idSet.iterator();
			   if (iter.hasNext())
				   builder.append(iter.next());
			   while (iter.hasNext())
				   builder.append(",").append(iter.next());
			   		System.out.println(builder.toString());  
			       fw.write(builder.toString()+"\n");
			 }
		     fw.close();
		}
	   catch(IOException ioe)
	   {
	       System.err.println("IOException: " + ioe.getMessage());
	   }
		 		
	}
	
	/**
	 * output the document name index to file
	 */
	public void printDocNameIndexFile() {		
		try {
			FileWriter fw = new FileWriter(docNameIndexPath, false); // overwrite
			for (Integer docId : docNames.keySet()) {
			       fw.write(docId+" "+docNames.get(docId)+"\n");
			 }
		     fw.close();
		}
		catch(IOException ioe)
		{
	       System.err.println("IOException: " + ioe.getMessage());
		}		 		
	}
	
	private class ValueComparator implements Comparator<Integer> {
	    Map<Integer, Integer> base;
	    
	    public ValueComparator(Map<Integer, Integer> base) {
	        this.base = base;
	    }

	    public int compare(Integer a, Integer b) {
	        if (base.get(a) >= base.get(b)) {
	            return -1;
	        } else {
	            return 1;
	        } // returning 0 would merge keys
	    }
	}
}

