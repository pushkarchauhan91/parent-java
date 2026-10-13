package com.company.java8features;

public class Child extends Parent {
 
	@Override
	void display() {
		System.out.println("Inside Child -> display()\n");
	}
 
	@Override
	void display(String msg) {
		System.out.println("Inside Child -> display( " + msg + " )\n");
	}
 
	void show() {
		System.out.println("Inside Parent -> show()\n");
	}
}
 
