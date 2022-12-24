package com.base.algorithm.leetcode;

//删除数组中的重复元素
public class _26_RemoveArrayDuplicates {

    public int removeDuplicates(int[] nums) {
        int index = 0;
        for (int i = 1;i < nums.length;i ++) {
            if (nums[index] != nums[i]) {
                nums[++index] = nums[i];
            }
        }
        return index + 1;
    }

}
