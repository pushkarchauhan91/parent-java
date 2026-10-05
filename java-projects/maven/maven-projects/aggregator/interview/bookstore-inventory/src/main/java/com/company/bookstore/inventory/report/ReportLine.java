package com.company.bookstore.inventory.report;

import java.math.BigDecimal;

public record ReportLine(String id, String name, BigDecimal cost, int stock) {
}
