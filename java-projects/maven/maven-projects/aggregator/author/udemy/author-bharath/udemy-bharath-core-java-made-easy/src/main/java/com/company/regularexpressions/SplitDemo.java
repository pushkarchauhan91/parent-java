package com.company.regularexpressions;

import java.util.regex.Pattern;

public class SplitDemo {

	public static void main(String[] args) {
		Pattern pattern = Pattern.compile("[.]");
		String[] words = pattern.split("www.bharaththippireddy.com");
		
		String s = "www.bharaththippireddy.com";
		String[] words2 = s.split("[.]");
		for (String word : words2) {
			System.out.println(word);
		}
	}

}
