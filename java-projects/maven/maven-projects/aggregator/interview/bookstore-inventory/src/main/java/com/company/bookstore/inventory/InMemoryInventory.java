package com.company.bookstore.inventory;

import com.company.bookstore.inventory.exception.DuplicateItemException;
import com.company.bookstore.inventory.exception.InsufficientStockException;
import com.company.bookstore.inventory.exception.ItemNotFoundException;
import com.company.bookstore.inventory.model.Item;
import com.company.bookstore.inventory.report.InventoryReport;
import com.company.bookstore.inventory.report.ReportLine;
import lombok.extern.slf4j.Slf4j;

import java.time.Clock;
import java.util.Comparator;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Thread-safe, in-memory {@link Inventory}.
 * <p>
 * Each item owns an {@link AtomicInteger} stock counter. Restock and checkout on the same item are
 * lock-free compare-and-set loops, so concurrent replenishment and checkout never lose an update
 * and stock can never go negative. Operations on different items do not contend at all.
 */
@Slf4j
public class InMemoryInventory implements Inventory {

    private final ConcurrentMap<String, StockEntry> entries = new ConcurrentHashMap<>();
    private final Clock clock;

    public InMemoryInventory() {
        this(Clock.systemUTC());
    }

    public InMemoryInventory(Clock clock) {
        this.clock = Objects.requireNonNull(clock, "clock must not be null");
    }

    @Override
    public void addItem(Item item, int initialStock) {
        Objects.requireNonNull(item, "item must not be null");
        if (initialStock < 0) {
            throw new IllegalArgumentException("Initial stock must not be negative: " + initialStock);
        }

        StockEntry existing = entries.putIfAbsent(item.id(), new StockEntry(item, initialStock));
        if (existing != null) {
            log.warn("Rejected duplicate item id '{}'", item.id());
            throw new DuplicateItemException(item.id());
        }
        log.info("Added item {} with initial stock {}", item, initialStock);
    }

    @Override
    public int updateStock(String itemId, int quantity) {
        requirePositive(quantity, "Restock");
        StockEntry entry = entryFor(itemId);

        int updated = entry.stock.accumulateAndGet(quantity, Math::addExact);
        log.info("Restocked item '{}' by {}; stock is now {}", itemId, quantity, updated);
        return updated;
    }

    @Override
    public int checkout(String itemId, int quantity) {
        requirePositive(quantity, "Checkout");
        StockEntry entry = entryFor(itemId);

        while (true) {
            int available = entry.stock.get();
            if (available < quantity) {
                log.warn("Checkout of {} x '{}' rejected; only {} in stock", quantity, itemId, available);
                throw new InsufficientStockException(itemId, quantity, available);
            }
            int remaining = available - quantity;
            if (entry.stock.compareAndSet(available, remaining)) {
                log.info("Checked out {} x '{}'; {} remaining", quantity, itemId, remaining);
                return remaining;
            }
        }
    }

    @Override
    public int getStock(String itemId) {
        return entryFor(itemId).stock.get();
    }

    @Override
    public Item getItem(String itemId) {
        return entryFor(itemId).item;
    }

    @Override
    public InventoryReport generateReport() {
        var lines = entries.values().stream()
                .map(entry -> new ReportLine(entry.item.id(), entry.item.name(), entry.item.cost(), entry.stock.get()))
                .sorted(Comparator.comparing(ReportLine::id))
                .toList();
        log.debug("Generated inventory report with {} items", lines.size());
        return new InventoryReport(clock.instant(), lines);
    }

    private StockEntry entryFor(String itemId) {
        Objects.requireNonNull(itemId, "itemId must not be null");
        StockEntry entry = entries.get(itemId.strip());
        if (entry == null) {
            log.warn("Lookup failed for unknown item id '{}'", itemId);
            throw new ItemNotFoundException(itemId);
        }
        return entry;
    }

    private static void requirePositive(int quantity, String operation) {
        if (quantity <= 0) {
            throw new IllegalArgumentException(operation + " quantity must be positive: " + quantity);
        }
    }

    private static final class StockEntry {

        private final Item item;
        private final AtomicInteger stock;

        private StockEntry(Item item, int initialStock) {
            this.item = item;
            this.stock = new AtomicInteger(initialStock);
        }

    }

}
