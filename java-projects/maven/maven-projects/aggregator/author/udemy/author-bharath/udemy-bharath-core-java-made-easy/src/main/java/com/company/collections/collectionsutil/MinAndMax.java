package com.company.collections.collectionsutil;

import java.util.Arrays;
import java.util.Collections;

public class MinAndMax {

	public static void main(String[] args) {
		Integer[] numbers = {10,300,30,-50,11};
		Integer min = Collections.min(Arrays.asList(numbers));
		Integer max = Collections.max(Arrays.asList(numbers));
		System.out.println(min);
		System.out.println(max);
	}

}
