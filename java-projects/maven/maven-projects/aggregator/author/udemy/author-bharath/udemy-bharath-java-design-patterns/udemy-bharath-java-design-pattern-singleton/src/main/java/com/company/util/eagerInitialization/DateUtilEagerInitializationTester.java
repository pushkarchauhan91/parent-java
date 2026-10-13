package com.company.util.eagerInitialization;

public class DateUtilEagerInitializationTester {

    public static void main(String[] args) {
        DateUtilEagerInitialization instance1 = DateUtilEagerInitialization.getInstance();
        DateUtilEagerInitialization instance2 = DateUtilEagerInitialization.getInstance();
        System.out.println(instance1 == instance2); // Should print true
    }
}
