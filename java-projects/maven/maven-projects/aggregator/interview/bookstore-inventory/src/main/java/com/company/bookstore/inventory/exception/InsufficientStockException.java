package com.company.bookstore.inventory.exception;

import lombok.Getter;

@Getter
public class InsufficientStockException extends InventoryException {

    private final String itemId;
    private final int requested;
    private final int available;

    public InsufficientStockException(String itemId, int requested, int available) {
        super("Cannot check out " + requested + " of item '" + itemId + "': only " + available + " in stock");
        this.itemId = itemId;
        this.requested = requested;
        this.available = available;
    }

}
