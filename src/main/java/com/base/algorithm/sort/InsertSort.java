package com.base.algorithm.sort;

public class InsertSort {

    public void sort(int[] arrs) {
        for (int i = 1;i < arrs.length;i ++) {
            int val = arrs[i];
            for (int j = i -1;j >= 0;j --) {
                if (arrs[j] > val) {
                    arrs[j + 1] = arrs[j];
                    arrs[j] = val;
                } else {
                    arrs[j + 1] = val;
                    break;
                }
            }
        }
    }

    public static void main(String[] args) {
        InsertSort sort = new InsertSort();
        int[] arr = {9,4,2,6,1,8,10};
        sort.sort(arr);
        BubboSort.print(arr);
    }
}
