package com.base.interview;


public class FindMaxIncreaseSequenceLenInArray {

    public static int getIncreaseSeqLen(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        if (arr.length == 1){
            return 1;
        }

        int start = 0;
        int end = 1;
        int tempLen = 0;
        int len = 0;
        for (int i = 0;i < arr.length && end < arr.length;i++) {
            if (arr[start] > arr[end]) {
                tempLen = 0;
            } else {
                tempLen ++;
            }
            start ++;
            end ++;
            len = tempLen > len ? tempLen : len;
        }
        return len > 0 ? len + 1 : len;
    }

    public static void main(String[] args) {
        int[] arr = {1,2,3,4,5,6,7,8,9,2,8,4,5,10,9,20,30,40,50};
        System.out.println(getIncreaseSeqLen(arr));
    }
}
