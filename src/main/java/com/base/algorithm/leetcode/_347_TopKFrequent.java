package com.base.algorithm.leetcode;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

//前k个高频元素
public class _347_TopKFrequent {
    public int[] topKFrequent(int[] nums, int k) {
        //用一个map记录频次
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0;i < nums.length;i ++) {
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
        }
        //优先级队列内部实际是一个堆结构
        PriorityQueue<Map.Entry<Integer, Integer>> queue = new PriorityQueue<>((e1, e2) -> {
            //自定义排序规则
            return e2.getValue() - e1.getValue();
        });
        queue.addAll(map.entrySet());
        int[] res = new int[k];
        for (int i = 0;i < k && !queue.isEmpty();i ++) {
            res[i] = queue.poll().getKey();
        }
        return res;
    }
}
