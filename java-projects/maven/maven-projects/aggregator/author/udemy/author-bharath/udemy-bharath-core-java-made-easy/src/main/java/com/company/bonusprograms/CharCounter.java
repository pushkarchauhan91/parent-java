package com.company.bonusprograms;

import java.util.HashMap;

public class CharCounter {
	
	public static void main(String[] args) {
		String s = "I am the creator of my destiny!!";
        HashMap<Character, Integer> map = new HashMap<Character, Integer>();
        int i = 0;
        while (i < s.length()) {
        	char currentChar = s.charAt(i); 
        	if (map.containsKey(currentChar)) {
        		map.put(currentChar, map.get(currentChar) + 1);

            } else {
            	map.put(currentChar, 1);

            }

            i++;
        }

        System.out.println(map);

    }

}