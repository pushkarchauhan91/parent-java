package com.company.inheritance.com.company.inheritance;

public class Employee {
	int id;
	String name;
	String dept;
	double salary;

	public Employee(int id, String name, String dept, double salary) {
		System.out.println("Inside Employee Constructor");
		this.id = id;
		this.name = name;
		this.dept = dept;
		this.salary = salary;
	}

	protected void work() {
		System.out.println("Common work for everyone");
	}
}
