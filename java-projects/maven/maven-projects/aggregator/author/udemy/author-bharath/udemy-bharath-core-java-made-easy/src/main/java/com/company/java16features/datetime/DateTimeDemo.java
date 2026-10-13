package com.company.java16features.datetime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class DateTimeDemo {

	public static void main(String[] args) {
		LocalTime time = LocalTime.parse("19:30:09.123456");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("h B");
        System.out.println(time.format(formatter));
	}

}