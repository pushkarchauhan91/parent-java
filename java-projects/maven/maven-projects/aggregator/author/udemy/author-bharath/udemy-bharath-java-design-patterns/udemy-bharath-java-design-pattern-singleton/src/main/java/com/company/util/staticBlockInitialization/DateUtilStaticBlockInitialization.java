package com.company.util.staticBlockInitialization;

public class DateUtilStaticBlockInitialization {

    private static DateUtilStaticBlockInitialization instance;

    static {
        instance = new DateUtilStaticBlockInitialization();
    }

    private DateUtilStaticBlockInitialization() {

    }

    public static DateUtilStaticBlockInitialization getInstance() {
        return instance;
    }
}
