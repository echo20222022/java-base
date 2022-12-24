package com.base.algorithm.leetcode;

public class _70_ClimbStairs {

    //f(x) = f(x - 1) + f(x - 2);
    public int climbStairs(int n) {
        if (n <= 1) {
            return n;
        }
        //0 1 1 2 3 5 8
        int p = 0, q = 0, r = 1;
        for (int i = 1;i <= n;i ++) {
            p = q;
            q = r;
            r = p + q;
        }
        return r;
    }

}
