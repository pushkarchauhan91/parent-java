package com.company.bonusprograms;

import java.util.Scanner;

public class TemperatureConverter {
	public static void main(String[] args) {
		double f,c;
		Scanner scanner = new Scanner(System.in);
        System.out.println("Choose the conversion type \n 1. Fahrenheit to Celsius \n 2. Celsius toFahrenheit");
        int choice = scanner.nextInt();
        switch(choice) {
        case 1:
        	System.out.println("Enter Fahrenheit Temperature");
        	f = scanner.nextDouble();
        	c = (f-32)*5/9;
        	System.out.println("Celsius temperature is "+c);
        	break;
        case 2:
        	System.out.println("Enter Celsius Temperature");
        	c = scanner.nextDouble();
        	f = ((9*c)/5)+32;
        	System.out.println("Fahrenheit Temperature is "+f);
            break;
        default:
        	System.out.println("Invalid Choice selected");
        }
	}
}
        