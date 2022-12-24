package com.base.algorithm.leetcode;

import java.util.HashMap;
import java.util.Map;

public class _1_TwoSum {
    public int[] twoSum(int[] nums, int target) {
        int[] res = new int[2];
        if (nums == nums || nums.length < 2) {
            return  res;
        }

        //双重for循环 暴力求解
        /*
        for (int i = 0;i < nums.length - 1;i ++) {
            for (int j = i + 1; j < nums.length; j ++) {
                if (nums[i] + nums[j] == target) {
                    res[0] = i;
                    res[1] = j;
                    return res;
                }
            }
        }
        return res;
        */


        //用map解决，减少一层循环
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0;i < nums.length;i ++) {
            int temp = target - nums[i];
            if (map.containsKey(temp)) {
                res[0] = i;
                res[1] = map.get(temp);
            }
            map.put(nums[i], i);
        }
        return res;
    }
}
