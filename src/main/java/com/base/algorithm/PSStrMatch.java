package com.base.algorithm;

/**
 * 字符串的朴素模式匹配算法
 * 时间复杂度是O(nm)
 * */
public class PSStrMatch {

    public static int match(String mainStr, String subStr) {
        if (mainStr == null
                || subStr == null) {
            return -1;
        }
        if ("".equals(mainStr) && "".equals(subStr)) {
            return 0;
        }

        char[] mainChar = mainStr.toCharArray();
        char[] subChar = subStr.toCharArray();

        int i = 0;
        int j = 0;
        int k = i;
        while (i < mainChar.length && j < subChar.length) {
            //如果当前字符匹配，就把 j和k向前推进
            if (mainChar[k] == subChar[j]) {
                j ++;
                k ++;
            } else {
                //如果不匹配， 把i向前推进，k根据i，并且吧j归零
                i ++;
                k = i;
                //回溯
                j = 0;
            }
        }
        if (j == subChar.length) {
            return i;
        }
        return -1;
    }

    public static void main(String[] args) {
        String s1 = "googlogooglegooglo";
        String s2 = "google";
        System.out.println(match(s1, s2));
    }

}
