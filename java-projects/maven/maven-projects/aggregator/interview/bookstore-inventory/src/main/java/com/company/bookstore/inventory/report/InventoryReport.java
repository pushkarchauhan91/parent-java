package com.company.bookstore.inventory.report;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Immutable point-in-time snapshot of the inventory.
 */
public record InventoryReport(Instant generatedAt, List<ReportLine> lines) {

    private static final String ROW_FORMAT = "%-10s | %-30s | %10s | %6s%n";

    public InventoryReport {
        lines = List.copyOf(lines);
    }

    public int totalStock() {
        return lines.stream().mapToInt(ReportLine::stock).sum();
    }

    /**
     * Renders the report as a plain-text table.
     */
    public String format() {
        String header = String.format(ROW_FORMAT, "ID", "NAME", "COST", "STOCK");
        String separator = "-".repeat(header.length() - 1) + System.lineSeparator();
        String rows = lines.stream()
                .map(line -> String.format(ROW_FORMAT, line.id(), line.name(), line.cost().toPlainString(), line.stock()))
                .collect(Collectors.joining());

        return "Inventory report generated at " + generatedAt + System.lineSeparator()
                + header + separator + rows + separator
                + "Items: " + lines.size() + ", total stock: " + totalStock() + System.lineSeparator();
    }

    @Override
    public String toString() {
        return format();
    }

}
