package com.company.bookstore.inventory;

import com.company.bookstore.inventory.exception.DuplicateItemException;
import com.company.bookstore.inventory.exception.InsufficientStockException;
import com.company.bookstore.inventory.exception.ItemNotFoundException;
import com.company.bookstore.inventory.model.Item;
import com.company.bookstore.inventory.report.InventoryReport;

import java.util.Collection;

/**
 * Public API of the bookstore inventory library. Implementations must be thread-safe.
 */
public interface Inventory {

    /**
     * Registers a new item with zero stock.
     *
     * @throws DuplicateItemException if an item with the same id is already registered
     */
    default void addItem(Item item) {
        addItem(item, 0);
    }

    /**
     * Registers a new item with the given opening stock.
     *
     * @throws DuplicateItemException   if an item with the same id is already registered
     * @throws IllegalArgumentException if {@code initialStock} is negative
     */
    void addItem(Item item, int initialStock);

    /**
     * Registers all items with zero stock. Stops at the first failure; items added before it remain.
     */
    default void addItems(Collection<Item> items) {
        items.forEach(this::addItem);
    }

    /**
     * Adds newly received stock for an item.
     *
     * @return stock after the update
     * @throws ItemNotFoundException    if the item is not registered
     * @throws IllegalArgumentException if {@code quantity} is not positive
     * @throws ArithmeticException      if the new stock would exceed {@link Integer#MAX_VALUE}
     */
    int updateStock(String itemId, int quantity);

    /**
     * Removes sold units of an item from stock. Either the full quantity is checked out or nothing is.
     *
     * @return stock remaining after the checkout
     * @throws ItemNotFoundException      if the item is not registered
     * @throws InsufficientStockException if fewer than {@code quantity} units are in stock
     * @throws IllegalArgumentException   if {@code quantity} is not positive
     */
    int checkout(String itemId, int quantity);

    /**
     * @throws ItemNotFoundException if the item is not registered
     */
    int getStock(String itemId);

    /**
     * @throws ItemNotFoundException if the item is not registered
     */
    Item getItem(String itemId);

    /**
     * Snapshot of every item with its current stock, ordered by item id.
     */
    InventoryReport generateReport();

}
