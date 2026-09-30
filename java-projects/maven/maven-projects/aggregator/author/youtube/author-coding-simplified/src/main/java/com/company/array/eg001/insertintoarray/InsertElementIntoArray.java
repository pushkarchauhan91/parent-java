package com.company.array.eg001.insertintoarray;

public class InsertElementIntoArray {

    int count = 0;

    public static void main(String[] args) {

        InsertElementIntoArray obj = new InsertElementIntoArray();
        obj.insertIntoArrayOneByOne(new int[5]);
        System.out.println("\n");
        obj.insertIntoArrayUsingLoop(new int[5]);

    }

    private void insertIntoArrayOneByOne(int[] nums) {
        nums[0] = 12;
        nums[1] = 24;
        nums[2] = 36;
        nums[3] = 48;
        nums[4] = 60;

        for (int i = 0; i < nums.length; i++) {
            System.out.println(nums[i] + " ");
        }
    }

    private void insertIntoArrayUsingLoop(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            insert(nums, i + 1);
        }

        for (int i = 0; i < nums.length; i++) {
            System.out.println(nums[i] + " ");
        }
    }

    private void insert(int[] nums, int value) {
        nums[count++] = value;
    }
}
