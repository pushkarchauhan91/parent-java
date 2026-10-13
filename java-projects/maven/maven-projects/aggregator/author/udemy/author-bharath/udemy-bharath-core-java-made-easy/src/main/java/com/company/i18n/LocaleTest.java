package com.company.i18n;

import java.util.Locale;

public class LocaleTest {

	public static void main(String[] args) {
		
		Locale l = Locale.getDefault();
		
		System.out.println(l.getCountry()+" "+l.getLanguage());
		System.out.println(l.getDisplayCountry()+" "+l.getDisplayLanguage());
		
		Locale.setDefault(Locale.UK);
		System.out.println(Locale.getDefault().getDisplayCountry());
		
		String[] isoCountries = Locale.getISOCountries();
		
		for (String eachCountry : isoCountries) {
			System.out.println(eachCountry);
			
		}
		
		String[] languages = Locale.getISOLanguages();
		for (String eachLanguage : languages) {
			System.out.println(eachLanguage);
			
		}
		

	}

}
