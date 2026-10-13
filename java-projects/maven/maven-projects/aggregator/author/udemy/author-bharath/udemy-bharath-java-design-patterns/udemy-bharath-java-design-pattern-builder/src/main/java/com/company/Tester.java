package com.company;

public class Tester {
	
	public static void main(String[] args) {
		HttpClient client = new HttpClient.HttpClientBuilder()
				.method("POST")
				.url("testurl")
				.body("{}")
				.secure("user", "pass")
				.build();
		System.out.println(client);
	}

}
