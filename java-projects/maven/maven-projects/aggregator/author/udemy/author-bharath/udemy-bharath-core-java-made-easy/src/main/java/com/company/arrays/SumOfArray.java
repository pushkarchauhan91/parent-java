package com.company.arrays;

public class SumOfArray {

	public static void main(String[] args) {
		int a[] = {1,2,3};
		int sum = 0;

		for (int i : a) {
			sum += i;
		}

		System.out.println(sum);
	}

}
