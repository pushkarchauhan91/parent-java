package com.company.stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StreamTesterTest {

    private StreamTester streamTester;
    private List<Customer> customers;

    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    @BeforeEach
    void setUp() throws Exception {
        streamTester = new StreamTester();
        customers = streamTester.getCustomers();
        System.setOut(new PrintStream(outputStream));
    }

    @Test
    void testGetCustomers() {
        assertEquals(5, customers.size());
        assertEquals("John", customers.get(0).getFirstName());
        assertEquals("GOLD", customers.get(0).getMembership().getMembershipId());
    }

    @Test
    void testPrintGoldMembers() {
        streamTester.printGoldMembers(customers);

        String output = outputStream.toString();

        assertTrue(output.contains("John"));
        assertTrue(output.contains("David"));
        assertTrue(output.contains("GOLD"));
        assertFalse(output.contains("Alice"));
    }

    @Test
    void testPrintCustomerNames() {
        streamTester.printCustomerNames(customers);

        String output = outputStream.toString();

        assertTrue(output.contains("John Smith"));
        assertTrue(output.contains("Alice Johnson"));
        assertTrue(output.contains("Emma Wilson"));
    }

    @Test
    void testCountGoldMembers() {
        streamTester.countGoldMembers(customers);

        String output = outputStream.toString();

        assertTrue(output.contains("Gold Members Count : 2"));
    }

    @Test
    void testFindFirstSilverMember() {
        streamTester.findFirstSilverMember(customers);

        String output = outputStream.toString();

        assertTrue(output.contains("Alice"));
        assertTrue(output.contains("SILVER"));
    }

    @Test
    void testPrintEmailList() {
        streamTester.printEmailList(customers);

        String output = outputStream.toString();

        assertTrue(output.contains("john@gmail.com"));
        assertTrue(output.contains("alice@gmail.com"));
        assertTrue(output.contains("emma@gmail.com"));
    }

    @Test
    void testGroupCustomersByMembership() {
        streamTester.groupCustomersByMembership(customers);

        String output = outputStream.toString();

        assertTrue(output.contains("GOLD"));
        assertTrue(output.contains("SILVER"));
        assertTrue(output.contains("PLATINUM"));
        assertTrue(output.contains("John"));
        assertTrue(output.contains("Emma"));
    }

    @Test
    void testCheckPlatinumMember() {
        streamTester.checkPlatinumMember(customers);

        String output = outputStream.toString();

        assertTrue(output.contains("Has Platinum Member : true"));
    }

    @Test
    void testPrintCustomerWithHighestId() {
        streamTester.printCustomerWithHighestId(customers);

        String output = outputStream.toString();

        assertTrue(output.contains("Emma"));
        assertTrue(output.contains("5"));
    }
}