package com.company.ch001.stringnumbermath;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Eg001PrintCharactersCount {

    public static void main(String[] args) {
        Eg001PrintCharactersCount obj = new Eg001PrintCharactersCount();
        System.out.println(obj.printCharacterCount("hello world"));
        System.out.println(obj.printCharactersCount("hello world"));
        System.out.println(obj.printCharactersCountUsingStream("hello world"));
    }

    public Map<Character, Integer> printCharacterCount(String s) {
        Map<Character, Integer> result = new HashMap<>();
        for (int j = 0; j < s.length(); j++) {
            char c = s.charAt(j);
            if (result.containsKey(c)) {
                result.put(c, result.get(c) + 1);
            } else {
                result.put(c, 1);
            }
        }
        return result;
    }

    public Map<Character, Integer> printCharactersCount(String s) {
        Map<Character, Integer> result = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            result.compute(ch, (k, v) -> (v == null) ? 1 : ++v);
        }
        return result;
    }

    public Map<Character, Long> printCharactersCountUsingStream(String s) {
        Map<Character, Long> result = s.chars()
                .mapToObj(i -> (char) i)
                .collect(Collectors.groupingBy(c -> c, Collectors.counting()));
        return result;
    }
}
