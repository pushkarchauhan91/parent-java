package com.company.java8features;

public class Test {
 
	public static void main(String[] args) {
		
		//Dynamic dispatch
		Parent obj1 = new Child();
		
		obj1.display();
		obj1.display("Message from Test Class.");
//		obj1.sh -> UNABLE TO FIND THE SHOW() METHOD WRITTEN IN CHILD CLASS
	}
}