package com.base.algorithm.leetcode;

import java.util.PriorityQueue;

public class _239_MaxSlidingWindow {

    /**
     * nums 原数据
     * k 窗口大小
     * */
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums.length == 0 || k == 0) {
            return new int[0];
        }
        int n = nums.length;
        int[] res = new int[n - k + 1];

        //用一个大顶堆来计算每一次窗口移动过程中的最大值
        PriorityQueue<Integer> maxPQ = new PriorityQueue<>((o1, o2) ->
            o2 - o1
        );

        for (int i = 0;i < n;i ++) {
            int start = i - k;
            if (start >= 0) {
                //移除床的左边界
                maxPQ.remove(nums[start]);
            }

            maxPQ.offer(nums[i]);
            //计算最大值
            if (maxPQ.size() == k) {
                res[i - k + 1] = maxPQ.peek();
            }
        }
        return res;
    }
}
