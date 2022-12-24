package com.base.algorithm.leetcode;

//盛水最多的容器
public class _11_MaxArea {

    public int maxArea(int[] height) {
        if (height == null || height.length <= 1) {
            return 0;
        }
        int idx1 = 0, idx2 = height.length, res = 0;
        while (idx1 < idx2) {
            res = height[idx1] < height[idx2] ?
                    Math.max(res, (idx2 - idx1) * height[idx1++]) : Math.max(res, (idx2 - idx1) * height[idx2 --]);
        }
        return res;
    }
}
