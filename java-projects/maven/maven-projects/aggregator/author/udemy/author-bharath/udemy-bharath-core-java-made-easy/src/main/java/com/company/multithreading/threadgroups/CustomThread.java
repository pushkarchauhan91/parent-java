package com.company.multithreading.threadgroups;

public class CustomThread extends Thread {

	CustomThread(ThreadGroup g, String name) {
		super(g, name);
	}

	@Override
	public void run() {

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
