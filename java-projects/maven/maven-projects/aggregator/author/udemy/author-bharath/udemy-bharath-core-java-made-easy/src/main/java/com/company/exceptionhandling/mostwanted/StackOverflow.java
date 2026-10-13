package com.company.exceptionhandling.mostwanted;

public class StackOverflow {
	
	public static void method1() {
		System.out.println("method1");
		method2();
	}
	
	public static void method2() {
		System.out.println("method2");
		method1();
	}

	public static void main(String[] args) {
		method1();
	}

}
