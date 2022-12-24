package com.base.algorithm.sort;

import java.util.ArrayList;

public class BubboSort {

    public void sort(int[] arrs) {
        for (int i = 0;i < arrs.length;i ++) {
            for (int j = 0;j < arrs.length - 1 - i;j ++) {
                if (arrs[j] > arrs[j +1]) {
                    int temp = arrs[j];
                    arrs[j] = arrs[j + 1];
                    arrs[j +1] = temp;
                }
            }
        }
    }

    public static void print(int[] arr) {
        String s = "";
        for (int i = 0;i < arr.length;i ++) {
            s = s + arr[i] + " ";
        }
        System.out.println(s);
    }

    public static void main(String[] args) {
        BubboSort sort = new BubboSort();
        int[] arr = {9,4,2,6,1,8,10};
        sort.sort(arr);
        print(arr);
    }
}
