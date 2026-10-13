package com.company.inheritance.com.company.inheritance;

public class Test {

	public static void main(String[] args) {
		String[] projects = new String[] { "Flight Reservation", "Check In" };
		Manager manager = new Manager(1, "John", "Travel", 1000000, projects,2000000);
		System.out.println(manager.id);
		System.out.println(manager.name);
		System.out.println(manager.dept);
		System.out.println(manager.salary);
		manager.work();
		
		Developer developer = new Developer(2, "Bharath", "Travel", 2000000, "Full Stack Java Developer and AWS");
		developer.work();
	}

}
