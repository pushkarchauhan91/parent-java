package com.company.encapsulation;

public class Customer {

	private String firstName;
	private String lastName;
	private String creditCard;
	
	static void  hello() {
		
	}
	
	 void hello2() {
		hello();
	}
	
	public static void main(String[] args) {
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getCreditCard() {
		return creditCard;
	}

	public void setCreditCard(String creditCard) {
		this.creditCard = creditCard;
	}

}
