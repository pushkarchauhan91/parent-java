package com.company.iostreams;

import java.util.Scanner;



public class CompileTimeBinding {



	void add(int a, int b) {

		System.out.println("Enter the value a: ");

		Scanner scanner=new Scanner(System.in);

		a=scanner.nextInt();

		

		System.out.println("Enter the value b: ");

		b=scanner.nextInt();

		

		

		int Result = a + b;

		System.out.println("The Result is= " + Result);

	}



	void add(float a, float b) {

		

		System.out.println("Enter the value a: ");

		Scanner scanner=new Scanner(System.in);

		a=scanner.nextFloat();

		

		System.out.println("Enter the value b: ");

		b=scanner.nextInt();

		



		float Result = a + b;

		System.out.println("The Result is= " + Result);

	}



	void add(int a, int b, int c) {

		

		System.out.println("Enter the value a: ");

		Scanner scanner=new Scanner(System.in);

		a=scanner.nextInt();

		

		System.out.println("Enter the value b: ");

		b=scanner.nextInt();

		

		System.out.println("Enter the value c: ");

		c

		

		=scanner.nextInt();

		int Result = a + b + c;

		System.out.println("The Result is= " + Result);

	}



	public static void main(String[] args) {



		CompileTimeBinding ctb = new CompileTimeBinding();
		int a =10,b=20,c=30;

		ctb.add(a, b);

		ctb.add(a, b);

		ctb.add(a, b, c);

		



	}



}

