package com.company.validation;

import com.company.rules.DroolsSqlRuleEngine;
import com.company.rules.SqlRuleFact;
import com.company.schema.SchemaProvider;
import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.statement.select.Select;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class SqlValidator {

    private final SchemaProvider schemaProvider;
    private final DroolsSqlRuleEngine droolsSqlRuleEngine;

    public SqlValidator(
            @Qualifier("cachedSchemaProvider") SchemaProvider schemaProvider,
            DroolsSqlRuleEngine droolsSqlRuleEngine
    ) {
        this.schemaProvider = schemaProvider;
        this.droolsSqlRuleEngine = droolsSqlRuleEngine;
    }

    public ValidationResult validate(String sql) {

        try {
            Statement statement = CCJSqlParserUtil.parse(sql);

            boolean selectStatement = statement instanceof Select;

            if (!selectStatement) {
                return ValidationResult.failure(
                        "Only SELECT statements are allowed. Unsafe SQL detected."
                );
            }

            Select select = (Select) statement;

            Set<String> tables = SqlAstUtils.extractTables(select)
                    .stream()
                    .map(this::normalizeTableName)
                    .collect(Collectors.toSet());

            Set<String> columns = SqlAstUtils.extractColumns(select);

            SqlRuleFact fact = new SqlRuleFact(
                    sql,
                    true,
                    tables,
                    columns
            );

            ValidationResult droolsResult =
                    droolsSqlRuleEngine.validate(fact);

            if (!droolsResult.isValid()) {
                return droolsResult;
            }

            ValidationResult tableCheck = validateTables(tables);
            if (!tableCheck.isValid()) {
                return tableCheck;
            }

            ValidationResult columnCheck =
                    validateColumns(tables, columns);

            if (!columnCheck.isValid()) {
                return columnCheck;
            }

            return ValidationResult.success();

        } catch (JSQLParserException e) {
            return ValidationResult.failure(
                    "Invalid SQL syntax. Unable to parse query."
            );
        }
    }

    private ValidationResult validateColumns(
            Set<String> tables,
            Set<String> columns
    ) {

        Map<String, Set<String>> tableColumns =
                schemaProvider.getTables()
                        .stream()
                        .collect(Collectors.toMap(
                                table -> normalizeTableName(
                                        table.getTableName()
                                ),
                                table -> table.getColumns()
                                        .stream()
                                        .map(column ->
                                                column.getName()
                                                        .toLowerCase()
                                                        .trim()
                                        )
                                        .collect(Collectors.toSet()),
                                (existing, replacement) -> existing
                        ));

        for (String column : columns) {

            String normalizedColumn =
                    normalizeColumnName(column);

            if ("*".equals(normalizedColumn)) {
                continue;
            }

            boolean found = false;

            for (String table : tables) {

                String normalizedTable =
                        normalizeTableName(table);

                Set<String> allowedColumns =
                        tableColumns.get(normalizedTable);

                if (
                        allowedColumns != null
                                && allowedColumns.contains(normalizedColumn)
                ) {
                    found = true;
                    break;
                }
            }

            if (!found) {
                return ValidationResult.failure(
                        "Query references unknown column: " + column
                );
            }
        }

        return ValidationResult.success();
    }

    private ValidationResult validateTables(Set<String> tables) {

        Set<String> allowedTables =
                schemaProvider.getTables()
                        .stream()
                        .map(table ->
                                normalizeTableName(table.getTableName())
                        )
                        .collect(Collectors.toSet());

        for (String table : tables) {

            String normalizedTable =
                    normalizeTableName(table);

            if (!allowedTables.contains(normalizedTable)) {
                return ValidationResult.failure(
                        "Query references unknown or unauthorized table: "
                                + table
                );
            }
        }

        return ValidationResult.success();
    }

    private String normalizeTableName(String tableName) {

        if (tableName == null) {
            return "";
        }

        String normalized =
                tableName
                        .toLowerCase()
                        .trim()
                        .replace("\"", "");

        if (normalized.contains(".")) {
            normalized =
                    normalized.substring(
                            normalized.lastIndexOf(".") + 1
                    );
        }

        return normalized;
    }

    private String normalizeColumnName(String columnName) {

        if (columnName == null) {
            return "";
        }

        String normalized =
                columnName
                        .toLowerCase()
                        .trim()
                        .replace("\"", "");

        if (normalized.contains(".")) {
            normalized =
                    normalized.substring(
                            normalized.lastIndexOf(".") + 1
                    );
        }

        return normalized;
    }

    public void validateOrThrow(String sql) {

        ValidationResult result = validate(sql);

        if (!result.isValid()) {
            throw new SqlValidationException(
                    result.getMessage()
                            + " SQL: "
                            + sql
            );
        }
    }
}