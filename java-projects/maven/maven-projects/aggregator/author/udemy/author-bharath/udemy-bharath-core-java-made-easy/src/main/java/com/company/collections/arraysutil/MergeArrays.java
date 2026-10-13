package com.company.collections.arraysutil;

import java.util.ArrayList;
import java.util.Arrays;

public class MergeArrays {

	public static void main(String[] args) {
		String a1[] = {"A","B","C"};
		String a2[] = {"D","E","F"};
		ArrayList<String> list = new ArrayList<String>(Arrays.asList(a1));
		list.addAll(Arrays.asList(a2));
		Object[] a3 = list.toArray();
		System.out.println(Arrays.toString(a3));
	}

}
