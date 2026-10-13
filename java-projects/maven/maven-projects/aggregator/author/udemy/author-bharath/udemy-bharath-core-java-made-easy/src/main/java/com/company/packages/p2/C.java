package com.company.packages.p2;
import com.company.packages.p1.A;
import static com.company.packages.p1.A.a1;
import com.company.packages.p1.p3.E;

public class C {

	public static void main(String[] args) {
		
		a1();
		com.company.packages.p1.A aObject = new A();
		aObject.a2();
		
		E eObject = new E();
		eObject.e1();
	}

}
