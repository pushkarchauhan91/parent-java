package com.company.dto;

public record PaymentMethod(
        String type,
        String provider
) {
}