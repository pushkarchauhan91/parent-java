package com.company.bookstore.inventory;

import com.company.bookstore.inventory.exception.DuplicateItemException;
import com.company.bookstore.inventory.exception.InsufficientStockException;
import com.company.bookstore.inventory.exception.ItemNotFoundException;
import com.company.bookstore.inventory.model.Item;
import com.company.bookstore.inventory.report.InventoryReport;
import com.company.bookstore.inventory.report.ReportLine;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.Clock;
import java.time.Instant;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class InMemoryInventoryTest {

    private static final Item PEN = Item.of("PEN-01", "Ball Pen", "10.00");
    private static final Item BOOK = Item.of("BK-101", "Clean Code", "450.50");

    private InMemoryInventory inventory;

    @BeforeEach
    void setUp() {
        inventory = new InMemoryInventory();
    }

    @Test
    void newItemStartsWithZeroStock() {
        inventory.addItem(PEN);

        assertEquals(0, inventory.getStock("PEN-01"));
        assertSame(PEN, inventory.getItem("PEN-01"));
    }

    @Test
    void duplicateItemIdIsRejected() {
        inventory.addItem(PEN);

        assertThrows(DuplicateItemException.class, () -> inventory.addItem(Item.of("PEN-01", "Other Pen", "5")));
    }

    @Test
    void updateStockAddsToExistingStock() {
        inventory.addItem(PEN, 5);

        assertEquals(10, inventory.updateStock("PEN-01", 5));
        assertEquals(10, inventory.getStock("PEN-01"));
    }

    @Test
    void checkoutReducesStock() {
        inventory.addItem(PEN, 10);

        assertEquals(7, inventory.checkout("PEN-01", 3));
        assertEquals(7, inventory.getStock("PEN-01"));
    }

    @Test
    void checkoutOfEntireStockLeavesZero() {
        inventory.addItem(PEN, 4);

        assertEquals(0, inventory.checkout("PEN-01", 4));
    }

    @Test
    void checkoutMoreThanAvailableFailsAndLeavesStockUntouched() {
        inventory.addItem(PEN, 2);

        var ex = assertThrows(InsufficientStockException.class, () -> inventory.checkout("PEN-01", 3));

        assertEquals(3, ex.getRequested());
        assertEquals(2, ex.getAvailable());
        assertEquals(2, inventory.getStock("PEN-01"));
    }

    @Test
    void unknownItemIsReported() {
        assertThrows(ItemNotFoundException.class, () -> inventory.updateStock("NOPE", 1));
        assertThrows(ItemNotFoundException.class, () -> inventory.checkout("NOPE", 1));
        assertThrows(ItemNotFoundException.class, () -> inventory.getStock("NOPE"));
    }

    @Test
    void nonPositiveQuantitiesAreRejected() {
        inventory.addItem(PEN, 5);

        assertThrows(IllegalArgumentException.class, () -> inventory.updateStock("PEN-01", 0));
        assertThrows(IllegalArgumentException.class, () -> inventory.updateStock("PEN-01", -1));
        assertThrows(IllegalArgumentException.class, () -> inventory.checkout("PEN-01", 0));
        assertThrows(IllegalArgumentException.class, () -> inventory.addItem(BOOK, -1));
        assertEquals(5, inventory.getStock("PEN-01"));
    }

    @Test
    void stockOverflowIsRejected() {
        inventory.addItem(PEN, Integer.MAX_VALUE);

        assertThrows(ArithmeticException.class, () -> inventory.updateStock("PEN-01", 1));
        assertEquals(Integer.MAX_VALUE, inventory.getStock("PEN-01"));
    }

    @Test
    void invalidItemsAreRejected() {
        assertThrows(IllegalArgumentException.class, () -> Item.of(" ", "Pen", "1"));
        assertThrows(IllegalArgumentException.class, () -> Item.of("P1", "", "1"));
        assertThrows(IllegalArgumentException.class, () -> Item.of("P1", "Pen", "-0.01"));
        assertThrows(NullPointerException.class, () -> new Item("P1", "Pen", null));
    }

    @Test
    void reportListsAllItemsSortedById() {
        Instant now = Instant.parse("2018-03-06T09:00:00Z");
        var inventory = new InMemoryInventory(Clock.fixed(now, ZoneOffset.UTC));
        inventory.addItem(PEN, 7);
        inventory.addItem(BOOK, 2);

        InventoryReport report = inventory.generateReport();

        assertEquals(now, report.generatedAt());
        assertEquals(List.of(
                new ReportLine("BK-101", "Clean Code", new BigDecimal("450.50"), 2),
                new ReportLine("PEN-01", "Ball Pen", new BigDecimal("10.00"), 7)
        ), report.lines());
        assertEquals(9, report.totalStock());
        assertTrue(report.format().contains("Clean Code"));
    }

    @Test
    void concurrentRestockAndCheckoutKeepStockConsistent() throws Exception {
        int initialStock = 1_000;
        int threads = 16;
        int operationsPerThread = 1_000;
        inventory.addItem(PEN, initialStock);

        AtomicInteger restocked = new AtomicInteger();
        AtomicInteger checkedOut = new AtomicInteger();
        CountDownLatch start = new CountDownLatch(1);
        List<Future<?>> futures = new ArrayList<>();

        try (ExecutorService pool = Executors.newFixedThreadPool(threads)) {
            for (int t = 0; t < threads; t++) {
                boolean restocker = t % 2 == 0;
                futures.add(pool.submit(() -> {
                    start.await();
                    for (int i = 0; i < operationsPerThread; i++) {
                        if (restocker) {
                            inventory.updateStock("PEN-01", 1);
                            restocked.incrementAndGet();
                        } else {
                            try {
                                inventory.checkout("PEN-01", 2);
                                checkedOut.addAndGet(2);
                            } catch (InsufficientStockException ignored) {
                                // expected when checkouts outrun restocks
                            }
                        }
                    }
                    return null;
                }));
            }
            start.countDown();
            for (Future<?> future : futures) {
                future.get();
            }
        }

        int expected = initialStock + restocked.get() - checkedOut.get();
        assertEquals(expected, inventory.getStock("PEN-01"));
        assertTrue(inventory.getStock("PEN-01") >= 0);
    }

}
