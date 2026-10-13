package com.company.java8features;

public class Parent {
 
	void display() {
		System.out.println("Inside Parent -> display()\n");
	}
 
	// Overloading
	void display(String msg) {
		System.out.println("Inside Parent -> display( " + msg + " )\n");
	}
}
 
