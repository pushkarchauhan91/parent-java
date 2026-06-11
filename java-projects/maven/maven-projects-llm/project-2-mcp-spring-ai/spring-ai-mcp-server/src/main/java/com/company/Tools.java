package com.company;

import org.springaicommunity.mcp.annotation.McpTool;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class Tools {

    @McpTool(description = "Returns the current server time")
    public Map<String, Object> timeNow() {
        return Map.of("now", LocalDateTime.now().toString());
    }

    @McpTool(description = "Greets a person by name")
    public Map<String, Object> greet(String name) {
        return Map.of("message", "Hello " + name + " 👋");
    }

    @McpTool(description = "Adds two numbers")
    public Map<String, Object> addNumbers(int a, int b) {
        return Map.of(
                "a", a,
                "b", b,
                "result", a + b
        );
    }

    @McpTool(description = "Calculates simple interest")
    public Map<String, Object> calculateSimpleInterest(double principal,
                                                       double rate,
                                                       double years) {
        double interest = (principal * rate * years) / 100;
        double total = principal + interest;

        return Map.of(
                "principal", principal,
                "rate", rate,
                "years", years,
                "interest", interest,
                "totalAmount", total
        );
    }

    @McpTool(description = "Generates a random UUID")
    public Map<String, Object> generateUuid() {
        return Map.of("uuid", UUID.randomUUID().toString());
    }

    @McpTool(description = "Converts text to uppercase")
    public Map<String, Object> uppercase(String text) {
        return Map.of("result", text.toUpperCase());
    }

    @McpTool(description = "Returns basic order status by order id")
    public Map<String, Object> getOrderStatus(String orderId) {
        return Map.of(
                "orderId", orderId,
                "status", "CONFIRMED",
                "estimatedDelivery", LocalDateTime.now().plusDays(3).toString()
        );
    }

    @McpTool(description = "Returns available products")
    public Map<String, Object> getProducts() {
        return Map.of(
                "products", List.of(
                        Map.of("id", "P1001", "name", "Laptop", "price", 75000),
                        Map.of("id", "P1002", "name", "Keyboard", "price", 2500),
                        Map.of("id", "P1003", "name", "Mouse", "price", 1200)
                )
        );
    }

    @McpTool(description = "Finds product details by product id")
    public Map<String, Object> getProductById(String productId) {
        return switch (productId) {
            case "P1001" -> Map.of("id", "P1001", "name", "Laptop", "price", 75000, "stock", 10);
            case "P1002" -> Map.of("id", "P1002", "name", "Keyboard", "price", 2500, "stock", 25);
            case "P1003" -> Map.of("id", "P1003", "name", "Mouse", "price", 1200, "stock", 40);
            default -> Map.of("id", productId, "status", "NOT_FOUND");
        };
    }
}