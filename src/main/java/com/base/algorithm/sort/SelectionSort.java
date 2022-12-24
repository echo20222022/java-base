package com.base.algorithm.sort;

public class SelectionSort {


    public static void sort(int[] arrs) {
        for (int i = 0;i < arrs.length -1;i ++) {
            int minIdx = i;
            for (int j = i + 1;j < arrs.length;j ++) {
                if (arrs[j] < arrs[minIdx]) {
                    minIdx = j;
                }
            }
            if (minIdx != i) {
                int temp = arrs[minIdx];
                arrs[minIdx] = arrs[i];
                arrs[i] = temp;
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = {9,4,2,6,1,8,10};
        sort(arr);
        BubboSort.print(arr);
    }

}
