package com.company.operators;

public class OperatorShifting {
	public static void main(String args[]) {
		byte x, y;
		x = 10;
		y = -10;
		System.out.println("Bitwise Left Shift: x<<2 = " + (x << 2));
		System.out.println("Bitwise Right Shift: x>>2 = " + (x >> 2));
		System.out.println("Bitwise Zero Fill Right Shift: x>>>2 = " + (x >>> 2));
		System.out.println("Bitwise Zero Fill Right Shift: y>>>2 = " + (y >>> 2));
	}
}