package com.company.multithreading.daemon;

public class DaemonDemo {

	public static void main(String[] args) {
		System.out.println(Thread.currentThread().isDaemon());
		MyThread myThread = new MyThread();
		
		System.out.println(myThread.isDaemon());
		myThread.start();
		myThread.setDaemon(true);
	}

}
