package com.company.validation;

public class SqlValidationException extends RuntimeException {
    public SqlValidationException(String message) {
        super(message);
    }
}
