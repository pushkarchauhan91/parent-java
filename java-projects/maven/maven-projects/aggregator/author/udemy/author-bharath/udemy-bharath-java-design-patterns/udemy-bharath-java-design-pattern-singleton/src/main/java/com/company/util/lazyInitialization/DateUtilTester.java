package com.company.util.lazyInitialization;

public class DateUtilTester {

    public static void main(String[] args) {
        DateUtil instance1 = DateUtil.getInstance();
        DateUtil instance2 = DateUtil.getInstance();
        System.out.println(instance1 == instance2); // Should print true
    }
}
