package com.company.java8features.com.bharath.java8.methodref;

public class MethodRefDemo {
	
	public static void myMethod() {
		
		for(int i=0;i<=10;i++) {
			System.out.println("Child Thread");
		}
	}
	

	public static void main(String[] args) {
		Runnable r = MethodRefDemo::myMethod;
		
		Thread t = new Thread(r);
		t.start();
		
		for(int i=0;i<=10;i++) {
			System.out.println("Parent Thread");
		}
	}

}

