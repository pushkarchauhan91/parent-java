package com.company.stream;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StreamTest {

    private final StreamTester tester = new StreamTester();

    // ---- distinctSortedDescending ----

    @Test
    void distinctSortedDescendingRemovesDuplicatesAndSortsDescending() {
        List<Integer> input = Arrays.asList(5, 3, 1, 3, 2, 5, 4);

        assertEquals(List.of(5, 4, 3, 2, 1), tester.distinctSortedDescending(input));
    }

    @Test
    void distinctSortedDescendingDoesNotModifyInput() {
        List<Integer> input = new ArrayList<>(List.of(5, 3, 1, 3));

        tester.distinctSortedDescending(input);

        assertEquals(List.of(5, 3, 1, 3), input);
    }

    @Test
    void distinctSortedDescendingHandlesNegativeNumbers() {
        assertEquals(List.of(2, 0, -1, -3), tester.distinctSortedDescending(List.of(-1, 2, -3, 0, -1, 2)));
    }

    @Test
    void distinctSortedDescendingAllDuplicatesGivesSingleElement() {
        assertEquals(List.of(7), tester.distinctSortedDescending(List.of(7, 7, 7)));
    }

    @Test
    void distinctSortedDescendingEmptyListGivesEmptyList() {
        assertTrue(tester.distinctSortedDescending(List.of()).isEmpty());
    }

    @Test
    void distinctSortedDescendingReturnsUnmodifiableList() {
        List<Integer> result = tester.distinctSortedDescending(List.of(1, 2));

        assertThrows(UnsupportedOperationException.class, () -> result.add(3));
    }

    @Test
    void distinctSortedDescendingThrowsOnNullElement() {
        // Comparator.reverseOrder() cannot compare null
        assertThrows(NullPointerException.class, () -> tester.distinctSortedDescending(Arrays.asList(1, null, 2)));
    }

    // ---- squaresOfOddNumbers ----

    @Test
    void squaresOfOddNumbersKeepsOddsAndSquaresThemInOrder() {
        List<Integer> input = Arrays.asList(5, 1, 3, 7, 2, 18, 11);

        assertEquals(List.of(25, 1, 9, 49, 121), tester.squaresOfOddNumbers(input));
    }

    @Test
    void squaresOfOddNumbersDoesNotModifyInput() {
        List<Integer> input = new ArrayList<>(List.of(1, 2, 3));

        tester.squaresOfOddNumbers(input);

        assertEquals(List.of(1, 2, 3), input);
    }

    @Test
    void squaresOfOddNumbersKeepsDuplicates() {
        assertEquals(List.of(9, 9), tester.squaresOfOddNumbers(List.of(3, 3)));
    }

    @Test
    void squaresOfOddNumbersHandlesNegativeOddNumbers() {
        // -3 % 2 == -1 (not 1), so the "!= 0" check is what makes negatives work
        assertEquals(List.of(9, 1), tester.squaresOfOddNumbers(List.of(-3, -2, -1, 0)));
    }

    @Test
    void squaresOfOddNumbersOnlyEvensGivesEmptyList() {
        assertTrue(tester.squaresOfOddNumbers(List.of(2, 4, 0, 18)).isEmpty());
    }

    @Test
    void squaresOfOddNumbersEmptyListGivesEmptyList() {
        assertTrue(tester.squaresOfOddNumbers(List.of()).isEmpty());
    }

    @Test
    void squaresOfOddNumbersOverflowsForLargeValues() {
        // 46341^2 = 2_147_488_281 > Integer.MAX_VALUE, so the int result wraps around
        assertEquals(46341 * 46341, tester.squaresOfOddNumbers(List.of(46341)).getFirst());
        assertTrue(tester.squaresOfOddNumbers(List.of(46341)).getFirst() < 0);
    }
}
