package com.company.rules;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Set;

@Data
@AllArgsConstructor
public class SqlRuleFact {
    private String sql;
    private boolean selectStatement;
    private Set<String> tables;
    private Set<String> columns;
}