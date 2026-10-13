package com.company.arrays;

public class CharArray {

	public static void main(String[] args) {
		char c[] = { 'a', 'z', 'q', 'p', 'a' };

		if (c[0] == c[c.length - 1]) {
			System.out.println("First and Last elements are same");
		} else {
			System.out.println("They are different");
		}

	}

}
