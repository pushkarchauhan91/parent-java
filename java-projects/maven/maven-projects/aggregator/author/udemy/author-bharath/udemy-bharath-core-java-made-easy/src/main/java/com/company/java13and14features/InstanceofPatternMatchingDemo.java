package com.company.java13and14features;

public class InstanceofPatternMatchingDemo {

	public static void main(String[] args) {
		Object object = get();
		if (object instanceof String str) {
			System.out.println(str);
		}
	}

	public static Object get() {
		return "Pattern Matching in action";
	}

}
