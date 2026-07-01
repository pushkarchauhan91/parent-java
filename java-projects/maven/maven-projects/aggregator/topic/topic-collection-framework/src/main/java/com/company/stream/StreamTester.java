package com.company.stream;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

public class StreamTester {

    public static void main(String[] args) throws Exception {

        StreamTester obj = new StreamTester();
        List<Customer> customers = obj.getCustomers();

        obj.printAllCustomers(customers);
        obj.printGoldMembers(customers);
        obj.printCustomerNames(customers);
        obj.sortCustomersByFirstName(customers);
        obj.countGoldMembers(customers);
        obj.findFirstSilverMember(customers);
        obj.printEmailList(customers);
        obj.groupCustomersByMembership(customers);
        obj.checkPlatinumMember(customers);
        obj.printCustomerWithHighestId(customers);
    }

    public List<Customer> getCustomers() throws Exception {

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

        List<Customer> customers = new ArrayList<>();

        customers.add(createCustomer(1, "John", "Smith", "john@gmail.com",
                101, "GOLD", sdf.parse("01-01-2024"), sdf.parse("31-12-2024")));

        customers.add(createCustomer(2, "Alice", "Johnson", "alice@gmail.com",
                102, "SILVER", sdf.parse("01-02-2024"), sdf.parse("31-01-2025")));

        customers.add(createCustomer(3, "Bob", "Brown", "bob@gmail.com",
                103, "PLATINUM", sdf.parse("01-03-2024"), sdf.parse("28-02-2025")));

        customers.add(createCustomer(4, "David", "Smith", "david@gmail.com",
                104, "GOLD", sdf.parse("15-01-2024"), sdf.parse("14-01-2025")));

        customers.add(createCustomer(5, "Emma", "Wilson", "emma@gmail.com",
                105, "SILVER", sdf.parse("01-04-2024"), sdf.parse("31-03-2025")));

        return customers;
    }

    public void printAllCustomers(List<Customer> customers) {
        System.out.println("\n=== All Customers ===");
        customers.forEach(System.out::println);
    }

    public void printGoldMembers(List<Customer> customers) {
        System.out.println("\n=== GOLD Members ===");
        customers.stream()
                .filter(c -> "GOLD".equals(c.getMembership().getMembershipId()))
                .forEach(System.out::println);
    }

    public void printCustomerNames(List<Customer> customers) {
        System.out.println("\n=== Customer Names ===");
        customers.stream()
                .map(c -> c.getFirstName() + " " + c.getLastName())
                .forEach(System.out::println);
    }

    public void sortCustomersByFirstName(List<Customer> customers) {
        System.out.println("\n=== Sorted By First Name ===");
        customers.stream()
                .sorted(Comparator.comparing(Customer::getFirstName))
                .forEach(System.out::println);
    }

    public void countGoldMembers(List<Customer> customers) {
        long count = customers.stream()
                .filter(c -> "GOLD".equals(c.getMembership().getMembershipId()))
                .count();

        System.out.println("\nGold Members Count : " + count);
    }

    public void findFirstSilverMember(List<Customer> customers) {
        System.out.println("\n=== First SILVER Member ===");

        customers.stream()
                .filter(c -> "SILVER".equals(c.getMembership().getMembershipId()))
                .findFirst()
                .ifPresent(System.out::println);
    }

    public void printEmailList(List<Customer> customers) {
        System.out.println("\n=== Email List ===");

        customers.stream()
                .map(Customer::getEmail)
                .forEach(System.out::println);
    }

    public void groupCustomersByMembership(List<Customer> customers) {
        System.out.println("\n=== Group By Membership ===");

        Map<String, List<Customer>> map = customers.stream()
                .collect(Collectors.groupingBy(
                        c -> c.getMembership().getMembershipId()));

        map.forEach((membership, list) -> {
            System.out.println(membership);
            list.forEach(c -> System.out.println("  " + c.getFirstName()));
        });
    }

    public void checkPlatinumMember(List<Customer> customers) {
        boolean exists = customers.stream()
                .anyMatch(c -> "PLATINUM".equals(c.getMembership().getMembershipId()));

        System.out.println("\nHas Platinum Member : " + exists);
    }

    public void printCustomerWithHighestId(List<Customer> customers) {
        System.out.println("\n=== Customer With Highest Id ===");

        customers.stream()
                .max(Comparator.comparingLong(Customer::getId))
                .ifPresent(System.out::println);
    }

    public Customer createCustomer(long id,
                                   String firstName,
                                   String lastName,
                                   String email,
                                   long membershipId,
                                   String membershipType,
                                   Date startDate,
                                   Date endDate) {

        Membership membership = new Membership();
        membership.setId(membershipId);
        membership.setMembershipId(membershipType);
        membership.setStartDate(startDate);
        membership.setEndDate(endDate);

        Customer customer = new Customer();
        customer.setId(id);
        customer.setFirstName(firstName);
        customer.setLastName(lastName);
        customer.setEmail(email);
        customer.setMembership(membership);

        return customer;
    }
}
