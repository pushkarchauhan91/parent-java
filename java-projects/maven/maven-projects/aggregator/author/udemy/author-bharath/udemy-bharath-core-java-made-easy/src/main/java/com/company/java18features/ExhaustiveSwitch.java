package com.company.java18features;

public class ExhaustiveSwitch {

	public static void main(String[] args) {
		Object obj = null;
		
		switch(obj) {
		case String s -> System.out.println(s);
		case Integer i -> System.out.println(i);
		default -> System.out.println(0);
		}

	}

}
