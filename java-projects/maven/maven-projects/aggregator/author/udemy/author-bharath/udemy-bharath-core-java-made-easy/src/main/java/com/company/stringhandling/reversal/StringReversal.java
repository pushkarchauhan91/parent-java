package com.company.stringhandling.reversal;

public class StringReversal {

	public String firstWay(String actual) {
		String reversed = "";
		for (int i = actual.length(); i > 0; i--) {
			reversed += actual.charAt(i - 1);
		}
		return reversed;
	}
	
	public String secondWay(String actual) {
		byte[] s1 = actual.getBytes();
		byte[] s2 = new byte[s1.length];
		
		for(int i=0;i<s1.length;i++) {
			s2[i] = s1[s1.length-i-1];
		}
		
		String reversed = new String(s2);
		
		return reversed;
		
		
	}

	public static void main(String[] args) {
		String str = "Bharath";
		StringReversal sr = new StringReversal();
		System.out.println(sr.secondWay(str));
	}

}
