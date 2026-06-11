package com.company.rules;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class SqlRuleResult {
    private boolean valid = true;
    private List<String> errors = new ArrayList<>();

    public void reject(String message) {
        this.valid = false;
        this.errors.add(message);
    }
}