package com.company.util.doublechecklock;

public class DateUtil {

    private static volatile DateUtil instance;

    private DateUtil() {

    }

    // double check lock
    public static DateUtil getInstance() {
        if (instance == null) {
            synchronized (DateUtil.class) {
                if (instance == null) {
                    instance = new DateUtil();
                }
            }
        }
        return instance;
    }
}
