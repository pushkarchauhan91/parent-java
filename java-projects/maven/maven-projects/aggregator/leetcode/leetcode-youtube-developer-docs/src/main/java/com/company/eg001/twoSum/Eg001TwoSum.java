package com.company.eg001.twoSum;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Eg001TwoSum {

    public static void main(String[] args) {
        int[] nums = {2, 7, 11, 15};
        int target = 9;
        Eg001TwoSum obj = new Eg001TwoSum();
        System.out.println(Arrays.toString(obj.twoSum(nums, target)));
    }

    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement)) {
                return new int[]{map.get(complement), i};
            } else {
                map.put(nums[i], i);
            }
        }
        return new int[]{-1, -1};
    }

}
