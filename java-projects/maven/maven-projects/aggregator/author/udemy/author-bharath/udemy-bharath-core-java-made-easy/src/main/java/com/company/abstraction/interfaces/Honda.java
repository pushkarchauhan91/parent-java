package com.company.abstraction.interfaces;

public class Honda implements Car,DriverLess {

	@Override
	public void go() {
		System.out.println("Inside Hondas Go");
		
	}

	@Override
	public void stop() {
		System.out.println("Inside Hondas Stop");
	}

	@Override
	public void sitDownAndRelax() {
		
	}

}
