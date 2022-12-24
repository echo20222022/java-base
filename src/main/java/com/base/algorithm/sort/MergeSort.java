package com.base.algorithm.sort;

public class MergeSort {

    public static int[] sort(int[] arrs) {
        int[] res = new int[arrs.length];
        mergeSort(arrs, 0, arrs.length -1, res);
        //arrs = res;
        return res;
    }


    //执行递归
    public static void mergeSort(int[] arrs, int start, int end, int[] res) {
        //递归到一个元素，到底了
        if (start == end) {
            return;
        }
        int mid = (start + end) / 2;
        //全半段
        mergeSort(arrs, start, mid, res);
        //后半段
        mergeSort(arrs, mid + 1, end, res);
        merge(arrs, start, end, res);
    }

    /**
     * @param arrs   原数组
     * @param start  局部起始位置
     * @param end    局部结束位置
     * @param res    存放排序结果的数组
     * */
    public static void merge(int[] arrs, int start, int end, int[] res) {

        //start ~ end 肯定是连续的

        int mid = (start + end) / 2;
        //分成两段
        int start1 = start;
        int end1 = mid;
        int start2 = mid + 1;
        int end2 = end;

        int idx1 = start1;
        int idx2 = start2;

        int resultIndex= start1;
        while (idx1 <= end1 && idx2 <= end2) {
            if (arrs[idx1] <= arrs[idx2]) {
                res[resultIndex ++] = arrs[idx1 ++];
            } else {
                res[resultIndex ++] = arrs[idx2 ++];
            }
        }

        //处理剩下的数据，只有一个while会被执行
        while (idx1 <= end1) {
            res[resultIndex ++] = arrs[idx1 ++];
        }

        while (idx2 <= end2) {
            res[resultIndex ++] = arrs[idx2 ++];
        }

        // 将 result 操作区间的数字拷贝到 arr 数组中，以便下次比较
        for(int i=start;i<=end;i++){
            arrs[i]=res[i];
        }
    }

    public static void main(String[] args) {
        int[] arr = {9,4,2,6,1,8,10};
        int[] res = sort(arr);
        BubboSort.print(res);
    }
}
