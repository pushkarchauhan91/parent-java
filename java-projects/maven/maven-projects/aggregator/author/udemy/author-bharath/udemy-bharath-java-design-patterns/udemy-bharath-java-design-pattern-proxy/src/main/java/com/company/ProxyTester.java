package com.company;

public class ProxyTester {
	
	public static void main(String[] args) {
		
		Customer customer = new CustomerProxyImpl();
		System.out.println(customer.getId());
		System.out.println(customer.getOrders().size());
		
	}

}
