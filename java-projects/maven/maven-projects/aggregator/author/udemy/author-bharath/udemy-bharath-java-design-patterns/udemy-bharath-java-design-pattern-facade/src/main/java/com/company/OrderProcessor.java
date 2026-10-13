package com.company;

public class OrderProcessor {
	
	public boolean checkStock(String name) {
		System.out.println("Checking stock");
		return true;
	}
	
	public String placeOrder(String name, int quantity) {
		System.out.println("Order Placed");
		return "abc123";
	}
	
	public void shipOrder(String orderId) {
		System.out.println("Order Shipped");
	}

}
