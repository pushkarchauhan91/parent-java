package com.company.regularexpressions;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MobileNumberValidator {

	public static void main(String[] args) {
		Pattern pattern = Pattern.compile("^\\d{10}$");
		Matcher matcher = pattern.matcher("1234567890");
		if(matcher.matches()) {
			System.out.println("valid cell no");
		}else {
			System.out.println("invalid cell no");
		}
	
	}

}
