package com.company.encapsulation;

import java.util.Scanner;

class ArmstrongWhile {
	public static void main(String[] arg) {
		int i = 100, result;
		System.out.println("Armstrong numbers between 100 to 999");
		while (i < 1000) {
			result = armstrongOrNot(i);
			if (result == i)
				System.out.println(i);
			i++;
		}
	}

	static int armstrongOrNot(int num) {
		int digit, sum = 0;
		while (num != 0) {
			digit = num % 10;
			sum = sum + (digit * digit * digit);
			num /= 10;
		}
		return sum;
	}
}