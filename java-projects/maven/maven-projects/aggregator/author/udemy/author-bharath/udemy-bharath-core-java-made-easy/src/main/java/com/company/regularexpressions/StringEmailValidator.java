package com.company.regularexpressions;

public class StringEmailValidator {

	public static void main(String[] args) {
		String email = "bharathbharath.com";
		if (email.matches("^[a-zA-Z0-9_.-]+@[a-zA-Z0-9_.-]+$")) {
			System.out.println("valid email id");
		} else {
			System.out.println("invalid email");
		}

	}

}
