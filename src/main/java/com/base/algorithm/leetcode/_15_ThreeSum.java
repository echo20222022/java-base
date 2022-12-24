package com.base.algorithm.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class _15_ThreeSum {

    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length < 3) {
            return res;
        }
        int len = nums.length;
        //排序
        Arrays.sort(nums);

        for (int i = 0;i < len;i ++) {
            //如果当前数组大于0，那么后面不可能再出现三个数和=0的情况
            if (nums[i] > 0) {
                break;
            }
            //跳过重复的数据
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            //区间指针
            int l = i + 1;
            int r = len - 1;

            while (l < r) {
                int sum = nums[l] + nums[r] + nums[i];
                if (sum == 0) {
                    res.add(Arrays.asList(nums[i], nums[l], nums[r]));
                    //跳过重复数据，更新区间指针
                    while (l < r && nums[l] == nums[l + 1]) l ++;
                    while (l < r && nums[r] == nums[r - 1]) r --;
                    l ++;
                    r -- ;
                } else if (sum > 0) {
                    r --;
                } else {
                    i ++;
                }
            }
        }
        return res;
    }

}
