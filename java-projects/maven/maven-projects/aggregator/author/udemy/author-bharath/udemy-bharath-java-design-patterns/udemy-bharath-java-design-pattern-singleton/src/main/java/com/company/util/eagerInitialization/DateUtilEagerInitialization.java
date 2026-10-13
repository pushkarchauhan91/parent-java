package com.company.util.eagerInitialization;

public class DateUtilEagerInitialization {

    private static DateUtilEagerInitialization instance = new DateUtilEagerInitialization();

    private DateUtilEagerInitialization() {

    }

    public static DateUtilEagerInitialization getInstance() {
        return instance;
    }
}
