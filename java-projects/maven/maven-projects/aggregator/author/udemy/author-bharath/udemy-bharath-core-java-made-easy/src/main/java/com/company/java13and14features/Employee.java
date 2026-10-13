package com.company.java13and14features;

public record Employee(String name, int sal) {
	
	static int id;
	
	public static void myMethod() {
		System.out.println(id);
	}
	
	public void myInstanceMethod() {
		System.out.println(this.name());
		System.out.println(this.sal());
	}

	public Employee() {
        this("", 0);

    }

	public Employee(String empData) {
        this("", 0);
    }
}
