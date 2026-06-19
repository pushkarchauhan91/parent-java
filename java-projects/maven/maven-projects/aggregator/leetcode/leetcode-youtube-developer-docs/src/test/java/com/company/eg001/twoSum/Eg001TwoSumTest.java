package com.company.eg001.twoSum;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class Eg001TwoSumTest {

    private final Eg001TwoSum twoSum = new Eg001TwoSum();

    @Test
    void testTwoSum_ValidCase() {
        int[] nums = {2, 7, 11, 15};
        int target = 9;

        int[] result = twoSum.twoSum(nums, target);

        assertArrayEquals(new int[]{0, 1}, result);
    }

    @Test
    void testTwoSum_AnotherValidCase() {
        int[] nums = {3, 2, 4};
        int target = 6;

        int[] result = twoSum.twoSum(nums, target);

        assertArrayEquals(new int[]{1, 2}, result);
    }

    @Test
    void testTwoSum_DuplicateNumbers() {
        int[] nums = {3, 3};
        int target = 6;

        int[] result = twoSum.twoSum(nums, target);

        assertArrayEquals(new int[]{0, 1}, result);
    }

    @Test
    void testTwoSum_NoSolution() {
        int[] nums = {1, 2, 3};
        int target = 10;

        int[] result = twoSum.twoSum(nums, target);

        assertArrayEquals(new int[]{-1, -1}, result);
    }

    @Test
    void testTwoSum_NegativeNumbers() {
        int[] nums = {-3, 4, 3, 90};
        int target = 0;

        int[] result = twoSum.twoSum(nums, target);

        assertArrayEquals(new int[]{0, 2}, result);
    }
}