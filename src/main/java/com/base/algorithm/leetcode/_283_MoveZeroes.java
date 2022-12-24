package com.base.algorithm.leetcode;

/**
 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
 请注意 ，必须在不复制数组的情况下原地对数组进行操作。
 * */
public class _283_MoveZeroes {

    public void moveZeroes(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return;
        }

        int i = 0;
        int j = 0;
        for (;j < nums.length; j ++) {
            if(nums[j] != 0) {
                nums[i] = nums[j];
                i ++;
            }
        }

        if (i < nums.length) {
            for (;i < nums.length;i ++) {
                nums[i] = 0;
            }
        }
    }

}
