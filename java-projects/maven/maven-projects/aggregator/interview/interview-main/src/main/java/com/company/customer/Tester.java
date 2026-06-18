package com.company.customer;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Tester {

    public static void main(String[] args) {

        // ---------- Customer 1 (expires in 5 months - SHOULD be included)
        Customer firstCustomer = new Customer();
        firstCustomer.setId(1L);
        firstCustomer.setName("John");

        Membership firstMembership = new Membership();
        firstMembership.setId(1L);
        firstMembership.setMembershipId("M001");

        Date startDate1 = new Date();
        firstMembership.setStartDate(startDate1);

        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(startDate1);
        cal1.add(Calendar.MONTH, 5);
        firstMembership.setEndDate(cal1.getTime());

        firstCustomer.setMembership(firstMembership);

        // ---------- Customer 2 (expires in 7 months - SHOULD NOT be included)
        Customer secondCustomer = new Customer();
        secondCustomer.setId(2L);
        secondCustomer.setName("Tom");

        Membership secondMembership = new Membership();
        secondMembership.setId(2L);
        secondMembership.setMembershipId("M002");

        Date startDate2 = new Date();
        secondMembership.setStartDate(startDate2);

        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(startDate2);
        cal2.add(Calendar.MONTH, 7);
        secondMembership.setEndDate(cal2.getTime());

        secondCustomer.setMembership(secondMembership);

        // ---------- Customer 3 (already expired - SHOULD NOT be included)
        Customer thirdCustomer = new Customer();
        thirdCustomer.setId(3L);
        thirdCustomer.setName("Sam");

        Membership thirdMembership = new Membership();
        thirdMembership.setId(3L);
        thirdMembership.setMembershipId("M003");

        Calendar cal3 = Calendar.getInstance();
        cal3.add(Calendar.MONTH, -1); // expired last month
        thirdMembership.setEndDate(cal3.getTime());

        thirdCustomer.setMembership(thirdMembership);

        // ---------- Add all customers to List
        List<Customer> customers = new ArrayList<>();
        customers.add(firstCustomer);
        customers.add(secondCustomer);
        customers.add(thirdCustomer);

        // ---------- Find customers expiring in 6 months
        List<Customer> expiringSoon =
                findCustomersExpiringIn6Months(customers);

        // ---------- Print result
        expiringSoon.forEach(c ->
                System.out.println(c.getName()));
    }

    public static List<Customer> findCustomersExpiringIn6Months(
            List<Customer> customers) {

        Date today = new Date();

        Calendar cal = Calendar.getInstance();
        cal.setTime(today);
        cal.add(Calendar.MONTH, 6);
        Date sixMonthsLater = cal.getTime();

        return customers.stream()
                .filter(c -> c.getMembership() != null)
                .filter(c -> c.getMembership().getEndDate() != null)
                .filter(c -> {
                    Date endDate = c.getMembership().getEndDate();
                    return !endDate.before(today)
                            && !endDate.after(sixMonthsLater);
                })
                .toList();
    }
}
