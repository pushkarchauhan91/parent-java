package com.company;

public class FacadeTester {
	public static void main(String[] args) {
		
		OrderFacade facade = new OrderFacade();
		facade.processOrder("MacBook", 3);
	}

}
