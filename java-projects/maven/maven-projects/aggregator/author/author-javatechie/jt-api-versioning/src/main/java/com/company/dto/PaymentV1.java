package com.company.dto;

public record PaymentV1(
        double amount,
        String currency,
        String paymentMethod
) {
}
