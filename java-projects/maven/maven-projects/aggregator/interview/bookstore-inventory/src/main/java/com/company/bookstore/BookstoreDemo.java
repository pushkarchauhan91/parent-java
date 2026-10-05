package com.company.bookstore;

import com.company.bookstore.inventory.InMemoryInventory;
import com.company.bookstore.inventory.Inventory;
import com.company.bookstore.inventory.exception.InventoryException;
import com.company.bookstore.inventory.model.Item;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * Walks through the four operations from the problem statement.
 */
@Slf4j
public class BookstoreDemo {

    public static void main(String[] args) {
        // 1. Create inventory
        Inventory inventory = new InMemoryInventory();
        inventory.addItems(List.of(
                Item.of("PEN-01", "Ball Pen", "10.00"),
                Item.of("BK-101", "Clean Code", "450.50"),
                Item.of("NB-200", "Notebook A5", "60")
        ));

        // 2. Update stock
        inventory.updateStock("PEN-01", 5);
        inventory.updateStock("PEN-01", 5);
        inventory.updateStock("BK-101", 3);

        // 3. Checkout
        inventory.checkout("PEN-01", 3);

        try {
            inventory.checkout("BK-101", 10);
        } catch (InventoryException e) {
            log.error("Checkout failed: {}", e.getMessage());
        }

        // 4. Report
        System.out.println(inventory.generateReport().format());
    }

}
