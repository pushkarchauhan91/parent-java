package com.company.chapter001.stringsnumbersmath;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class CountingDuplicateCharactersTester {

    public Map<Character, Integer> countDuplicateCharacters(String str) {
        Map<Character, Integer> cache = new HashMap<>();
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            cache.compute(ch, (k, v) -> v == null ? 1 : ++v);
        }
        return cache;
    }

    public Map<Character, Long> countDuplicateCharactersUsingStream(String str) {
        return str.chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.groupingBy(
                        c -> c,
                        Collectors.counting()
                ));
    }
}

/*
Version	                        Time Complexity	                Space Complexity
Loop + HashMap.compute()	        O(n) average                 O(k)
Stream + groupingBy()	            O(n) average                 O(k)
 */