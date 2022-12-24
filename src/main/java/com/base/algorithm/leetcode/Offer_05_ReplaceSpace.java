package com.base.algorithm.leetcode;

public class Offer_05_ReplaceSpace {

    //请实现一个函数，把字符串 s 中的每个空格替换成"%20"。
    public String replaceSpace(String s) {
        //找出空格的个数
        int spaceCount = 0;
        for (int i = 0;i < s.length();i ++) {
            if (s.charAt(i) == ' '){
                spaceCount ++;
            }
        }

        //新的字符容器
        char[] newC = new char[s.length() + spaceCount * 2];

        //定义两个指针，指向新老数组
        int m = newC.length - 1;
        int n = s.length() - 1;

        while (m >= 0 && n >= 0) {
            if (s.charAt(n) == ' ') {
                newC[m --] = '0';
                newC[m --] = '2';
                newC[m --] = '%';

                m = m - 3;
                n = n - 1;
                continue;
            }
            else {
                newC[m--] = s.charAt(n--);
            }
        }
        return String.valueOf(newC);
    }

}
