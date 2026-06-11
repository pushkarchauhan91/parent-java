package com.example.support;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringAICustomerSupportAgentUsingOpenaiApplication {

	public static void main(String[] args) {
		String key = System.getenv("OPENAI_API_KEY");
		System.out.println("OPENAI_API_KEY is " +
				(key == null ? "NOT SET" : "set, starts with: " + key.substring(0, 7) + "..."));
		SpringApplication.run(SpringAICustomerSupportAgentUsingOpenaiApplication.class, args);
	}

}
