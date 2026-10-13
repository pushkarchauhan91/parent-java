package com.company.java16features.proxy;

public interface Worker {
	
	default String work() {
		return "Done";
	}

}
