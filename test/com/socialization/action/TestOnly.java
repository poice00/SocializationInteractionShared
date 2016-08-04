package com.socialization.action;

import org.junit.Test;

public class TestOnly {
	@Test
	public void testString() {
		String str="1.2.3.mp3"; 
		str=str.substring(str.lastIndexOf(".")+1);
		System.out.println(str);
	}
}
