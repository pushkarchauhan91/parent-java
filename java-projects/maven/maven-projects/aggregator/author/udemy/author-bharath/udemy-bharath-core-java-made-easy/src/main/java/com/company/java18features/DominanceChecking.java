package com.company.java18features;

public class DominanceChecking {

	public static void main(String[] args) {
		Object obj = null;

		switch (obj) {
		case CharSequence s -> System.out.println(s);
		//case String i -> System.out.println(i);
		default -> System.out.println(0);
		}

	}

}
