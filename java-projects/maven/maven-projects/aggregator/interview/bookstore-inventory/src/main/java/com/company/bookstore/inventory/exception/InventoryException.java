package com.company.bookstore.inventory.exception;

/**
 * Base type for all business failures raised by the inventory library.
 */
public class InventoryException extends RuntimeException {

    public InventoryException(String message) {
        super(message);
    }

}
