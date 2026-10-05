package com.company.bookstore.inventory.model;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * An immutable catalogue entry. Stock is tracked separately by the inventory,
 * so an {@code Item} can be shared freely across threads.
 */
public record Item(String id, String name, BigDecimal cost) {

    public Item {
        Objects.requireNonNull(id, "Item id must not be null");
        Objects.requireNonNull(name, "Item name must not be null");
        Objects.requireNonNull(cost, "Item cost must not be null");
        if (id.isBlank()) {
            throw new IllegalArgumentException("Item id must not be blank");
        }
        if (name.isBlank()) {
            throw new IllegalArgumentException("Item name must not be blank");
        }
        if (cost.signum() < 0) {
            throw new IllegalArgumentException("Item cost must not be negative: " + cost);
        }
        id = id.strip();
        name = name.strip();
    }

    public static Item of(String id, String name, String cost) {
        return new Item(id, name, new BigDecimal(cost));
    }

}
