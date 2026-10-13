package com.company;

public class Tester {
	
    public static void main(String[] args) {
    	
        Bank abc = new ABCBank();
        Bank xyz = new XYZBank();

        Payment upiPayment = new UPIPayment(abc);
        upiPayment.makePayment("abc123", 1000);

        Payment creditCardPayment = new CreditCardPayment(xyz);
        creditCardPayment.makePayment("xyz123", 2000);
        
    }

}
