package com.company.ch001.stringnumbermath;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Eg002FindFirstNonRepeatingCharacter {

    public static void main(String[] args) {
        Eg002FindFirstNonRepeatingCharacter obj = new Eg002FindFirstNonRepeatingCharacter();
        System.out.println(obj.findFirstNonRepeatingCharacter("hello world"));
    }

    public String findFirstNonRepeatingCharacter(String s) {
        Map<Character, Long> chars = s.chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.groupingBy(Function.identity(), LinkedHashMap::new, Collectors.counting()));

        int cp = chars.entrySet().stream().filter(entry -> entry.getValue() == 1)
                .findFirst()
                .map(Map.Entry::getKey)
                .orElse(Character.MIN_VALUE);

        return String.valueOf(Character.toChars(cp));
    }
}
