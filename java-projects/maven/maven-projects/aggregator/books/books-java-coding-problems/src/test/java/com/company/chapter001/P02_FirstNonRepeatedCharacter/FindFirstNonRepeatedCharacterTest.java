package com.company.chapter001.P02_FirstNonRepeatedCharacter;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class FindFirstNonRepeatedCharacterTest {

    private final FindFirstNonRepeatedCharacter finder = new FindFirstNonRepeatedCharacter();

    @ParameterizedTest
    @MethodSource("uniqueCharacterCases")
    void shouldReturnFirstNonRepeatedCharacter(String input, char expected) {
        assertEquals(expected, finder.findFirstNonRepeatedCharacter(input));
    }

    @ParameterizedTest
    @MethodSource("noUniqueCharacterCases")
    void shouldReturnMinValueWhenNoCharacterIsUnique(String input) {
        assertEquals(Character.MIN_VALUE, finder.findFirstNonRepeatedCharacter(input));
    }

    @Test
    void shouldPickFirstUniqueByPositionNotByAlphabet() {
        // 'z' appears before 'a'; both are unique
        assertEquals('z', finder.findFirstNonRepeatedCharacter("zbba"));
    }

    @Test
    void shouldIgnoreCharacterThatRepeatsLaterInString() {
        // 'a' looks unique until its second occurrence at the very end
        assertEquals('b', finder.findFirstNonRepeatedCharacter("abcca"));
    }

    @Test
    void shouldHandleWhitespaceAsUniqueCharacter() {
        assertEquals(' ', finder.findFirstNonRepeatedCharacter("aa bb"));
    }

    @Test
    void shouldHandleNonAsciiCharacters() {
        assertEquals('ü', finder.findFirstNonRepeatedCharacter("ééü"));
    }

    @Test
    void shouldHandleLongInput() {
        String input = "ab".repeat(10_000) + "x";
        assertEquals('x', finder.findFirstNonRepeatedCharacter(input));
    }

    @Test
    void shouldThrowNpeForNullInput() {
        assertThrows(NullPointerException.class, () -> finder.findFirstNonRepeatedCharacter(null));
    }

    // ---- findFirstNonRepeatedCharacterUsingStream ----

    @ParameterizedTest
    @MethodSource("uniqueCharacterCases")
    void streamShouldReturnFirstNonRepeatedCharacter(String input, char expected) {
        assertEquals(String.valueOf(expected), finder.findFirstNonRepeatedCharacterUsingStream(input));
    }

    @ParameterizedTest
    @MethodSource("noUniqueCharacterCases")
    void streamShouldReturnMinValueWhenNoCharacterIsUnique(String input) {
        assertEquals(String.valueOf(Character.MIN_VALUE), finder.findFirstNonRepeatedCharacterUsingStream(input));
    }

    @ParameterizedTest
    @ValueSource(strings = {"zbba", "abcca", "aa bb", "ééü", "swiss", "AaBbAac"})
    void streamAndLoopShouldAgreeForBmpCharacters(String input) {
        assertEquals(String.valueOf(finder.findFirstNonRepeatedCharacter(input)),
                finder.findFirstNonRepeatedCharacterUsingStream(input));
    }

    @Test
    void streamShouldHandleLongInput() {
        String input = "ab".repeat(10_000) + "x";
        assertEquals("x", finder.findFirstNonRepeatedCharacterUsingStream(input));
    }

    @Test
    void streamShouldReturnWholeEmojiAsSingleCharacter() {
        String result = finder.findFirstNonRepeatedCharacterUsingStream("😀😀😃");

        assertEquals("😃", result);
        assertEquals(2, result.length());              // surrogate pair: two chars
        assertEquals(1, result.codePointCount(0, result.length()));
    }

    @Test
    void streamShouldTreatEmojiSharingHighSurrogateAsDifferentCharacters() {
        // 😀 (U+1F600) and 😃 (U+1F603) share the high surrogate U+D83D
        assertEquals("😀", finder.findFirstNonRepeatedCharacterUsingStream("😀😃"));
    }

    @Test
    void loopVersionSplitsSurrogatePairsUnlikeStreamVersion() {
        // The char-based loop sees the shared high surrogate twice and returns a lone low surrogate
        char loopResult = finder.findFirstNonRepeatedCharacter("😀😃");

        assertTrue(Character.isLowSurrogate(loopResult));
        assertNotEquals(String.valueOf(loopResult), finder.findFirstNonRepeatedCharacterUsingStream("😀😃"));
    }

    @Test
    void streamShouldSkipRepeatedEmojiAndReturnAsciiCharacter() {
        assertEquals("a", finder.findFirstNonRepeatedCharacterUsingStream("😀a😀"));
    }

    @Test
    void streamShouldThrowNpeForNullInput() {
        assertThrows(NullPointerException.class, () -> finder.findFirstNonRepeatedCharacterUsingStream(null));
    }

    static Stream<Arguments> uniqueCharacterCases() {
        return Stream.of(
                Arguments.of("a", 'a'),
                Arguments.of("abc", 'a'),
                Arguments.of("aabbc", 'c'),
                Arguments.of("abacabad", 'c'),
                Arguments.of("swiss", 'w'),
                Arguments.of("programming", 'p'),
                Arguments.of("aabbccddeef", 'f'),
                Arguments.of("stress", 't'),
                Arguments.of("aA", 'a'),      // case-sensitive: 'a' and 'A' are different
                Arguments.of("Aa", 'A'),
                Arguments.of("112!", '2'),
                Arguments.of("a a b", 'b')    // space repeats, so 'b' is first unique
        );
    }

    static Stream<String> noUniqueCharacterCases() {
        return Stream.of("", "aa", "aabb", "abab", "abcabc", "  ", "AaAa");
    }
}
