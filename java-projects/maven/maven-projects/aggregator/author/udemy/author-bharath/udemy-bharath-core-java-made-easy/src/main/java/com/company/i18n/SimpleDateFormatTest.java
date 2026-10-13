package com.company.i18n;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SimpleDateFormatTest {

	public static void main(String[] args) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/M/yyyy hh:mm:ss");
		String date = sdf.format(new Date());
		System.out.println(date);
		
		String dateAsString = "10-12-2014";
	    SimpleDateFormat sdf1 = new SimpleDateFormat("dd-M-yyyy");
		Date date1 = sdf1.parse(dateAsString);
		System.out.println(date1);

	}

}
