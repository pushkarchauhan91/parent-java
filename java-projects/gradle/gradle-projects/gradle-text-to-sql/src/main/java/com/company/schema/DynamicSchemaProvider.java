package com.company.schema;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component("dynamicSchemaProvider")
@RequiredArgsConstructor
public class DynamicSchemaProvider implements SchemaProvider {

    private static final String SCHEMA_NAME = "orders";

    private final JdbcTemplate jdbcTemplate;

    @Override
    public List<TableSchema> getTables() {

        Map<String, List<ColumnSchema>> columnsByTable = loadColumns();
        Map<String, String> tableDescriptions = loadTableDescriptions();

        String sql = """
                    SELECT table_name
                    FROM information_schema.tables
                    WHERE table_schema = ?
                      AND table_type = 'BASE TABLE'
                """;

        List<TableSchema> tables = new ArrayList<>(
                jdbcTemplate.query(sql, (rs, rowNum) -> {

                    String tableName = rs.getString("table_name");

                    return new TableSchema(
                            tableName,
                            tableDescriptions.get(tableName),
                            columnsByTable.getOrDefault(tableName, List.of())
                    );

                }, SCHEMA_NAME)
        );

        tables.addAll(getAllowedMetadataTables());

        return tables;
    }

    private List<TableSchema> getAllowedMetadataTables() {
        return List.of(
                new TableSchema(
                        "pg_tables",
                        "PostgreSQL metadata view containing table names by schema",
                        List.of(
                                new ColumnSchema("schemaname", "Schema name"),
                                new ColumnSchema("tablename", "Table name")
                        )
                ),
                new TableSchema(
                        "pg_class",
                        "PostgreSQL system catalog for relations",
                        List.of(
                                new ColumnSchema("relname", "Relation name"),
                                new ColumnSchema("relkind", "Relation type")
                        )
                ),
                new TableSchema(
                        "pg_namespace",
                        "PostgreSQL system catalog for schemas",
                        List.of(
                                new ColumnSchema("nspname", "Schema name")
                        )
                )
        );
    }


    private Map<String, String> loadTableDescriptions() {

        String sql = """
                    SELECT
                        c.relname AS table_name,
                        d.description
                    FROM pg_class c
                    JOIN pg_namespace n
                        ON n.oid = c.relnamespace
                    LEFT JOIN pg_description d
                        ON d.objoid = c.oid
                       AND d.objsubid = 0
                    WHERE n.nspname = ?
                      AND c.relkind = 'r'
                """;

        Map<String, String> result = new HashMap<>();

        jdbcTemplate.query(sql, rs -> {

            result.put(
                    rs.getString("table_name"),
                    rs.getString("description")
            );

        }, SCHEMA_NAME);

        return result;
    }

    private Map<String, List<ColumnSchema>> loadColumns() {

        Map<String, String> columnDescriptions =
                loadColumnDescriptions();

        String sql = """
                    SELECT
                        table_name,
                        column_name
                    FROM information_schema.columns
                    WHERE table_schema = ?
                """;

        Map<String, List<ColumnSchema>> result =
                new HashMap<>();

        jdbcTemplate.query(sql, rs -> {

            String tableName =
                    rs.getString("table_name");

            String columnName =
                    rs.getString("column_name");

            String key =
                    tableName + "_" + columnName;

            result
                    .computeIfAbsent(
                            tableName,
                            t -> new ArrayList<>()
                    )
                    .add(
                            new ColumnSchema(
                                    columnName,
                                    columnDescriptions.get(key)
                            )
                    );

        }, SCHEMA_NAME);

        return result;
    }

    private Map<String, String> loadColumnDescriptions() {

        String sql = """
                    SELECT
                        c.relname AS table_name,
                        a.attname AS column_name,
                        d.description
                    FROM pg_class c
                    JOIN pg_namespace n
                        ON n.oid = c.relnamespace
                    JOIN pg_attribute a
                        ON a.attrelid = c.oid
                       AND a.attnum > 0
                    LEFT JOIN pg_description d
                        ON d.objoid = c.oid
                       AND d.objsubid = a.attnum
                    WHERE n.nspname = ?
                """;

        Map<String, String> result =
                new HashMap<>();

        jdbcTemplate.query(sql, rs -> {

            String key =
                    rs.getString("table_name")
                            + "_"
                            + rs.getString("column_name");

            result.put(
                    key,
                    rs.getString("description")
            );

        }, SCHEMA_NAME);

        return result;
    }

    @Override
    public List<Relationship> getRelationships() {

        String sql = """
                    SELECT
                        tc.table_name AS source_table,
                        kcu.column_name AS source_column,
                        ccu.table_name AS target_table,
                        ccu.column_name AS target_column
                    FROM information_schema.table_constraints tc
                    JOIN information_schema.key_column_usage kcu
                        ON tc.constraint_name = kcu.constraint_name
                    JOIN information_schema.constraint_column_usage ccu
                        ON ccu.constraint_name = tc.constraint_name
                    WHERE tc.constraint_type = 'FOREIGN KEY'
                      AND tc.table_schema = ?
                """;

        return jdbcTemplate.query(sql,

                (rs, rowNum) -> new Relationship(

                        rs.getString("source_table"),

                        rs.getString("source_column"),

                        rs.getString("target_table"),

                        rs.getString("target_column")
                ),

                SCHEMA_NAME
        );
    }

    @Override
    public List<BusinessRule> getBusinessRules() {

        return List.of(

                new BusinessRule(
                        "Do not assume data that is not present in the schema."
                ),

                new BusinessRule(
                        "Always generate valid PostgreSQL SQL syntax."
                ),

                new BusinessRule(
                        "Use joins only when relationships exist."
                ),

                new BusinessRule(
                        "The target database schema is always 'orders'. Never use schema 'public'."
                ),

                new BusinessRule(
                        "All business tables belong to schema 'orders'."
                ),

                new BusinessRule(
                        "For metadata questions like table count, schema information, column information, or listing tables, use PostgreSQL catalog tables."
                ),

                new BusinessRule(
                        "Allowed metadata tables are pg_tables, pg_class, pg_namespace, and pg_attribute."
                ),

                new BusinessRule(
                        "Never use information_schema.tables."
                ),

                new BusinessRule(
                        "For table count questions, generate SQL like: SELECT COUNT(*) FROM pg_tables WHERE schemaname = 'orders';"
                ),

                new BusinessRule(
                        "For listing tables, generate SQL like: SELECT tablename FROM pg_tables WHERE schemaname = 'orders';"
                ),

                new BusinessRule(
                        "For column information, use pg_attribute joined with pg_class."
                ),

                new BusinessRule(
                        "Never query tables outside the allowed schema and metadata tables."
                )

//                new BusinessRule(
//                        "Use LIMIT whenever user requests top records or limited results."
//                )
        );
    }
}
