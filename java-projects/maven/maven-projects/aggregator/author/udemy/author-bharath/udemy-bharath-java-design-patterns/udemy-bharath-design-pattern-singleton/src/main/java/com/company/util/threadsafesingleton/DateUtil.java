package com.company.util.threadsafesingleton;

public class DateUtil {

    private static DateUtil instance;

    private DateUtil() {

    }

    // Class level lock
    public static DateUtil getInstance() {
        synchronized (DateUtil.class) {
            if (instance == null) {
                instance = new DateUtil();
            }
        }
        return instance;
    }
}
