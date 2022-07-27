package com.base.algorithm;

import java.util.Locale;

/**
 * KMP算法.
 * */
public class KMPStrMatch {

    public int kmp(String targetStr, String originStr) {
        char[] originChars = originStr.toCharArray();
        char[] targetChars = targetStr.toCharArray();

        //原串指针
        int i = 0;
        //模式串指针
        int j = 0;

        int[] next = null;
        while (i < originChars.length && j < targetChars.length) {
            if (i == -1 || originChars[i] == targetChars[j]) {
                i ++ ;
                j ++;
            } else {
                //有了next数组，在主串上就不需要一个回溯指针k了
                j = next[j];
            }
        }
        if (j == targetChars.length) {
            //如果j走到最后了，说明匹配成功了
            return i - j;
        }
        return -1;
    }

    /**
     * @param targetStr 模式串
     * */
    private int[] getNext(String targetStr) {
        char[] chars = targetStr.toCharArray();
        int[] next = new int[chars.length];
        //表示 第一个就不匹配，j = -1是，两个指针整体向前移动
        next[0] = -1;
       //[-1, ]
        int j = 0;
        int k = -1;
        // 0 ~ length -1
        while (j < chars.length - 1) {
            //k == -1 表示遍历的是字符数组中的第一个值
            /**
              a b a b a a
              0 1 2 3 4 5
         [-1  0  ]   ++j, ++k, j = 1, k = 0
         [-1  0  ]   k = next[k], j = 1, k = -1
         [-1  0  0 ]  ++j, ++k j = 2, k = 0
         [-1, 0, 0 ]  k = next[k], j = 2, k = -1
         [-1, 0, 0 0 ]  ++j, ++k, j = 3, k = 0
         [-1, 0, 0 0 ] k = next[k], j = 3, k = -1
         [-1, 0, 0 0 0 ] ++j, ++k, j = 4, k = 0
         [-1, 0, 0 0 0 1 ] ++j, ++k, j = 5, k = 1
             * */
            if (k == -1 || chars[j] == chars[k]) {
                next[++j]=++k;
            } else {
                k = next[k];
            }
        }
        return next;
    }
}
