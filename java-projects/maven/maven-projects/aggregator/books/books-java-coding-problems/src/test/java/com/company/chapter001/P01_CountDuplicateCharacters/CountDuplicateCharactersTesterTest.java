package com.company.chapter001.P01_CountDuplicateCharacters;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class CountDuplicateCharactersTesterTest {

    private final CountDuplicateCharacters tester = new CountDuplicateCharacters();

    @ParameterizedTest
    @MethodSource("countCases")
    void shouldCountCharacters(String input, Map<Character, Integer> expected) {
        assertEquals(expected, tester.countDuplicateCharacters(input));
    }

    @ParameterizedTest
    @MethodSource("countCases")
    void shouldCountCharactersUsingStream(String input, Map<Character, Integer> expected) {
        assertEquals(toLongCounts(expected), tester.countDuplicateCharactersUsingStream(input));
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "a", "hello", "Mississippi", "a b  c", "112233!!", "AaBbAa"})
    void loopAndStreamImplementationsShouldAgree(String input) {
        assertEquals(toLongCounts(tester.countDuplicateCharacters(input)),
                tester.countDuplicateCharactersUsingStream(input));
    }

    @Test
    void countsShouldAddUpToInputLength() {
        String input = "the quick brown fox jumps over the lazy dog";

        int total = tester.countDuplicateCharacters(input).values().stream()
                .mapToInt(Integer::intValue)
                .sum();

        assertEquals(input.length(), total);
    }

    @Test
    void shouldReturnMutableEmptyMapForEmptyString() {
        Map<Character, Integer> result = tester.countDuplicateCharacters("");

        assertTrue(result.isEmpty());
        assertDoesNotThrow(() -> result.put('x', 1));
    }

    @Test
    void shouldThrowNpeForNullInput() {
        assertThrows(NullPointerException.class, () -> tester.countDuplicateCharacters(null));
        assertThrows(NullPointerException.class, () -> tester.countDuplicateCharactersUsingStream(null));
    }

    static Stream<Arguments> countCases() {
        return Stream.of(
                Arguments.of("", Map.of()),
                Arguments.of("a", Map.of('a', 1)),
                Arguments.of("aaaa", Map.of('a', 4)),
                Arguments.of("hello", Map.of('h', 1, 'e', 1, 'l', 2, 'o', 1)),
                Arguments.of("programming",
                        Map.of('p', 1, 'r', 2, 'o', 1, 'g', 2, 'a', 1, 'm', 2, 'i', 1, 'n', 1)),
                // case-sensitive: 'A' and 'a' are different characters
                Arguments.of("AaAa", Map.of('A', 2, 'a', 2)),
                // whitespace, digits and symbols are counted like any other character
                Arguments.of("a b a", Map.of('a', 2, ' ', 2, 'b', 1)),
                Arguments.of("1122!!", Map.of('1', 2, '2', 2, '!', 2)),
                Arguments.of("\t\n\t", Map.of('\t', 2, '\n', 1)),
                Arguments.of("éeé", Map.of('é', 2, 'e', 1))
        );
    }

    private static Map<Character, Long> toLongCounts(Map<Character, Integer> counts) {
        return counts.entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey, e -> e.getValue().longValue()));
    }
}
