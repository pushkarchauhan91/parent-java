package com.company;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class McpClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(McpClientApplication.class, args);
    }

    @Bean
    ChatClient chatClient(ChatClient.Builder chatClientBuilder) {
        return chatClientBuilder.build();
    }

    @Bean
    CommandLineRunner run(ChatClient chatClient,
                          ToolCallbackProvider mcpToolProvider) {

        return args -> {

            String response = chatClient
                    .prompt("""
                            Use MCP tools and answer clearly:
                            
                            1. Greet Pushkar
                            2. Tell me the current server time
                            3. Add 10 and 20
                            4. Calculate simple interest for:
                               principal = 100000
                               rate = 8.5
                               years = 2
                            5. Show product details for product id P1001
                            6. Generate one UUID
                            7. Convert this text to uppercase: spring ai mcp is working
                            """)
                    .toolCallbacks(mcpToolProvider)
                    .call()
                    .content();

            System.out.println("========== MCP CLIENT RESPONSE ==========");
            System.out.println(response);
            System.out.println("=========================================");
        };
    }
}