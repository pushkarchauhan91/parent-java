package com.company.bookstore.inventory.exception;

import lombok.Getter;

@Getter
public class ItemNotFoundException extends InventoryException {

    private final String itemId;

    public ItemNotFoundException(String itemId) {
        super("No item with id '" + itemId + "' exists in the inventory");
        this.itemId = itemId;
    }

}
