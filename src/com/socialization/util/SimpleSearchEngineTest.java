package com.socialization.util;

public class SimpleSearchEngineTest {
	
	public static void main(String[] args) {
		
		System.out.println("test");
		
		SimpleSearchEngineImpl se = new SimpleSearchEngineImpl("c://temp//searchFiles", "c://temp//indexFile", "c://temp//docNameIndex", "c://temp//stopWords");
		se.init();
		se.addDoc("braveNewWord");
		se.addDoc("weAre");
		se.addDoc("VDISeriesPart2");
		se.addDoc("theDinosaurs");
		se.printSearchIndex();
		se.printDocNameIndexFile();
		
		// sample search
		System.out.println("ehllo" + se.query("accross").size());
		
		se.query("word year");
		se.query("yahoo world will");
	}
}
