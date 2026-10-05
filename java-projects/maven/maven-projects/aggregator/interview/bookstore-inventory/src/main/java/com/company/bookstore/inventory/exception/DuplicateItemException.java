package com.company.bookstore.inventory.exception;

import lombok.Getter;

@Getter
public class DuplicateItemException extends InventoryException {

    private final String itemId;

    public DuplicateItemException(String itemId) {
        super("An item with id '" + itemId + "' already exists in the inventory");
        this.itemId = itemId;
    }

}
