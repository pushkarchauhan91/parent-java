package com.example.support;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringAICustomerSupportAgentUsingClaudeApplication {

	public static void main(String[] args) {
		String key = System.getenv("ANTHROPIC_API_KEY");
		System.out.println("ANTHROPIC_API_KEY is " +
				(key == null ? "NOT SET" : "set, starts with: " + key.substring(0, 7) + "..."));
		SpringApplication.run(SpringAICustomerSupportAgentUsingClaudeApplication.class, args);
	}

}
