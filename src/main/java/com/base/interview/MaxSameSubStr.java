package com.base.interview;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CountDownLatch;

public class MaxSameSubStr {

    public String getMaxSameSubStr(String text1, String text2) {

        if (isEmpty(text1) || isEmpty(text2)) {
            return "";
        }

        int endIdx1 = 0;
        int endIdx2 = 0;

        int tempMaxLen = 0;
        String tempMaxSubStr = "";

        char[] str1Arr = text1.toCharArray();
        char[] str2Arr = text2.toCharArray();
        for (int i = 0; i < str1Arr.length;i ++) {
            for (int j = 0;j < str2Arr.length;j ++) {
                endIdx1 = i;
                endIdx2 = j;
                if (str1Arr[i] == str2Arr[j]) {
                    while (endIdx1 < str1Arr.length && endIdx2 < str2Arr.length && str1Arr[endIdx1] == str2Arr[endIdx2]) {
                        endIdx1 ++;
                        endIdx2 ++;
                        tempMaxLen ++;
                    }
                    if (tempMaxSubStr.length() < endIdx2 - j) {
                        tempMaxSubStr = text2.substring(j, endIdx2);
                    }
                    tempMaxLen = 0;
                }
            }
        }
        return tempMaxSubStr;
    }

    private boolean isEmpty(String str) {
        return str == null || "".equals(str);
    }

    public static void main(String[] args) {
        MaxSameSubStr sameSubStr = new MaxSameSubStr();
        //String res = sameSubStr.getMaxSameSubStr("private_void_method","public_void_method");
        //String res = sameSubStr.getMaxSameSubStr("hello123world","hello123abc4");
        String res = sameSubStr.getMaxSameSubStr("abc","def");
        System.out.println(res);

        ///ThreadLocal
        //CountDownLatch
        ArrayBlockingQueue
    }

}
