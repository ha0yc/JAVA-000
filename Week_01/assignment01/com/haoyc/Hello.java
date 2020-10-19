package com.haoyc;

public class Hello {
    public static void main(String[] args) {
        int a = 10;
        int b = 2;

        double d = 0.1;
        boolean boo = false;
        float f = 1.23f;

        int result1 = a + b;
        int result2 = a - b;
        int result3 = a / b;
        int result4 = a * b;

        if (result1 > result2) {
            System.out.println(result1);
        }

        int[] nums = new int[]{1,2,3,4};
        for (int i = 0; i < nums.length; i++) {
            System.out.println(nums[i]);
        }
    }
}
