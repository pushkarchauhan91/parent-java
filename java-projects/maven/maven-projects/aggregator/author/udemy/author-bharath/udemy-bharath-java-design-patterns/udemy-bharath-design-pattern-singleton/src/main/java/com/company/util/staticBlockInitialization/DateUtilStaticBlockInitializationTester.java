package com.company.util.staticBlockInitialization;

public class DateUtilStaticBlockInitializationTester {

    public static void main(String[] args) {
        DateUtilStaticBlockInitialization instance1 = DateUtilStaticBlockInitialization.getInstance();
        DateUtilStaticBlockInitialization instance2 = DateUtilStaticBlockInitialization.getInstance();
        System.out.println(instance1 == instance2); // Should print true
    }
}
