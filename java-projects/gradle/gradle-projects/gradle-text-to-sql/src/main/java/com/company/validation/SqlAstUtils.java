package com.company.validation;

import net.sf.jsqlparser.expression.ExpressionVisitorAdapter;
import net.sf.jsqlparser.schema.Column;
import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.statement.select.Limit;
import net.sf.jsqlparser.statement.select.PlainSelect;
import net.sf.jsqlparser.statement.select.Select;
import net.sf.jsqlparser.statement.select.SelectItem;
import net.sf.jsqlparser.util.TablesNamesFinder;

import java.util.HashSet;
import java.util.Set;

public class SqlAstUtils {


    public static Long extractLimit(Select select) {

        if (select instanceof PlainSelect plainSelect) {
            Limit limit = plainSelect.getLimit();
            if (limit != null && limit.getRowCount() != null) {
                return Long.parseLong(limit.getRowCount().toString());
            }
        }

        return null;
    }

    public static Set<String> extractTables(Select select) {
        return new HashSet<>(new TablesNamesFinder<>().getTables((Statement) select));
    }

    public static Set<String> extractColumns(Select select) {
        Set<String> columns = new HashSet<>();

        if (select instanceof PlainSelect plainSelect) {
            for (SelectItem<?> item : plainSelect.getSelectItems()) {
                item.getExpression().accept(new ExpressionVisitorAdapter<Void>() {
                    @Override
                    public <S> Void visit(Column column, S context) {
                        columns.add(column.getColumnName());
                        return null;
                    }
                });
            }
        }

        return columns;
    }
}
