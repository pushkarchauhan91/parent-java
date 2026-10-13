package com.company.i18n;

import java.text.NumberFormat;

public class NumberFormatTest {

	public static void main(String[] args) {
		
		Double d = 1232322.3434;
		NumberFormat nf = NumberFormat.getInstance();
		nf.setMinimumFractionDigits(4);
		nf.setMaximumFractionDigits(3);
		
		nf.setMaximumIntegerDigits(4);
		System.out.println(nf.format(d));

	}

}
