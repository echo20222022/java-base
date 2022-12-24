package com.base.algorithm.leetcode;

import java.util.Arrays;

//获取数组中最小的k个数
public class Offer_40_GetLeastNumbers {

    //1. 排序后去k个数(普通排序，堆排序)
    public int[] getLeastNumbers(int[] arr, int k) {
        Arrays.sort(arr);
        return Arrays.copyOfRange(arr, 0, k);
    }

}
