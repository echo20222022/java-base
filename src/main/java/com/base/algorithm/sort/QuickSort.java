package com.base.algorithm.sort;

//快速排序
public class QuickSort {


    public static void quickSort(int[] nums, int start, int end) {
        if (start > end) {
            return;
        }
        int i, j, base;
        i = start;
        j = end;
        //基准数
        base = nums[start];

        while (i < j) {
            while (i < j && nums[j] >= base) j --;
            while (i < j && nums[i] <= base) i ++;
            if (i < j) {

            }
        }
    }

    public static void swap(int[] nums, int left, int right) {
        int temp = nums[left];
        nums[left] = nums[right];
        nums[right] = temp;
    }
}
