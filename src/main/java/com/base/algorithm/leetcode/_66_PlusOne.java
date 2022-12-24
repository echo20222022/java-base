package com.base.algorithm.leetcode;

public class _66_PlusOne {

    public int[] plusOne(int[] digits) {
        int len = digits.length;
        for (int i = len - 1; i >= 0; i --) {
            //判断当前数组是否需要进位
            digits[i] = (digits[i] + 1) % 10;
            //如果没有进位，则是在原数上直接+1
            if (digits[i] != 0) {
                return digits;
            }
        }

        digits = new int[len + 1];
        digits[0] = 1;
        return digits;
    }

}
