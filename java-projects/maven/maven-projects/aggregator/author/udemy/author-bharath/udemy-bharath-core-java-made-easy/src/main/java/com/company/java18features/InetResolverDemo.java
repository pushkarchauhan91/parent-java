package com.company.java18features;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class InetResolverDemo {

	public static void main(String[] args) {
		
		try {
			InetAddress name = InetAddress.getByName("google.com");
			System.out.println(name.getHostAddress());
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}

	}

}
