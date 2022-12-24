package com.base.algorithm.leetcode;

import java.util.Arrays;

//判断有效字母异位词
public class _24_IsAnagram {

    //若 s 和 t 中每个字符出现的次数都相同，则称 s 和 t 互为字母异位词。
    public boolean isAnagram(String s, String t) {
        //排序后equals
        /*char[] c1 = s.toCharArray();
        char[] c2 = t.toCharArray();

        Arrays.sort(c1);
        Arrays.sort(c2);
        return Arrays.equals(c1, c2);*/

        //哈希表计数法
        int[] sC = new int[26];
        int[] tC = new int[26];
        //0-25
        for (char c : s.toCharArray()) {
            sC[c - 'a'] ++;
        }
        for (char c : t.toCharArray()) {
            tC[c - 'a'] ++;
        }
        for (int i = 0;i < 26;i ++) {
            //某个字母的个数不相等
            if (sC[i] != tC[i]) {
                return false;
            }
        }
        return true;
    }

}
