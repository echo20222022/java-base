package com.base.algorithm.leetcode;

//合并两个有序数组
public class _88_MergeTwoArray {

    //nums2合并到nums1
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int index = m + n - 1;

        m --;
        n --;

        while (n >= 0) {
            //如果nums1大，将nus1 放入
            if (m >= 0 && nums1[m] > nums2[n]) {
                nums1[index] = nums1[m --];
            } else {
                nums1[index] = nums2[n --];
            }
            index --;
        }
    }

}
