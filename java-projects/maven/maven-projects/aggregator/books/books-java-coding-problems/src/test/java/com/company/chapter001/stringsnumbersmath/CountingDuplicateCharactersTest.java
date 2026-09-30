package com.company.chapter001.stringsnumbersmath;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Map;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CountingDuplicateCharactersTest {

    private final CountingDuplicateCharactersTester tester =
            new CountingDuplicateCharactersTester();

    @ParameterizedTest
    @MethodSource("duplicateCharacterCases")
    void shouldCountCharacters(String input, Map<Character, Integer> expected) {
        assertEquals(expected, tester.countDuplicateCharacters(input));
    }

    @ParameterizedTest
    @MethodSource("duplicateCharacterStreamCases")
    void shouldCountCharactersUsingStream(String input, Map<Character, Long> expected) {
        assertEquals(expected, tester.countDuplicateCharactersUsingStream(input));
    }

    static Stream<Arguments> duplicateCharacterCases() {
        return Stream.of(
                Arguments.of("", Map.of()),
                Arguments.of("a", Map.of('a', 1)),
                Arguments.of("aaaa", Map.of('a', 4)),
                Arguments.of(
                        "programming",
                        Map.of(
                                'p', 1,
                                'r', 2,
                                'o', 1,
                                'g', 2,
                                'a', 1,
                                'm', 2,
                                'i', 1,
                                'n', 1
                        )
                ),
                Arguments.of(
                        "hello",
                        Map.of(
                                'h', 1,
                                'e', 1,
                                'l', 2,
                                'o', 1
                        )
                )
        );
    }

    static Stream<Arguments> duplicateCharacterStreamCases() {
        return Stream.of(
                Arguments.of("", Map.of()),
                Arguments.of("a", Map.of('a', 1L)),
                Arguments.of("aaaa", Map.of('a', 4L)),
                Arguments.of(
                        "programming",
                        Map.of(
                                'p', 1L,
                                'r', 2L,
                                'o', 1L,
                                'g', 2L,
                                'a', 1L,
                                'm', 2L,
                                'i', 1L,
                                'n', 1L
                        )
                ),
                Arguments.of(
                        "hello",
                        Map.of(
                                'h', 1L,
                                'e', 1L,
                                'l', 2L,
                                'o', 1L
                        )
                )
        );
    }
}