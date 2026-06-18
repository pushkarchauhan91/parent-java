package com.company.array;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Tester {

    public static void main(String[] args) {
        Tester tester = new Tester();
        int[] arr = {3, 2, 1, 3, 2, 7};
        System.out.println(tester.firstNonRepeating(arr).get());
    }

    public Optional<Integer> firstNonRepeating(int[] arr) {
        Map<Integer, Long> freq =
                Arrays.stream(arr)
                        .boxed()
                        .collect(Collectors.groupingBy(
                                Function.identity(),
                                LinkedHashMap::new,   // preserves insertion order
                                Collectors.counting()
                        ));

        return freq.entrySet().stream()
                .filter(e -> e.getValue() == 1)
                .map(Map.Entry::getKey)
                .findFirst();
    }


}
