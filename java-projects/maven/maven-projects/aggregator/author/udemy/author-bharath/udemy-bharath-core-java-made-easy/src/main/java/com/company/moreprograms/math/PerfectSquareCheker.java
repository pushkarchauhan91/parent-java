package com.company.moreprograms.math;

public class PerfectSquareCheker {

	public static void main(String[] args) {
		int n = 728;

		int s = (int) Math.sqrt(n);
		System.out.println(Math.sqrt(n));
		System.out.println(s);

		if ((s * s) == n) {
			System.out.println("Perfect Square");
		} else {
			System.out.println("Not a Perfect Square");
		}
	}

}
