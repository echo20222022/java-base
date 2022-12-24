package com.base.algorithm.sort;

//堆排序
public class HeapSort {

    public static int[] sort(int[] arrs) {
        Heap heap = new Heap(arrs);
        for (int i = 0;i < arrs.length;i ++) {
            arrs[i] = heap.extractMin();
        }
        return arrs;
    }

    //堆是一个完全二叉树，用数组构造一个小顶堆
    public static class Heap {

        int[] data;
        int size ;

        public Heap(int[] originData) {
            data = new int[originData.length];
            //复制到内部数组中
            System.arraycopy(originData, 0, data, 0, originData.length);
            size = originData.length;
            //从最后一个数据的parent执行上浮操作
            for (int i = parent(size - 1);i >= 0;i --) {
                siftDown(i);
            }
        }

        //上浮
        /*public void siftUp(int idx) {
            while (idx > 0 && data[parent(idx)] > data[idx]) {
                //交换位置
                int temp = data[parent(idx)];
                data[parent(idx)] = data[idx];
                data[idx] = temp;
                //推进idx
                idx = parent(idx);
            }
        }*/

        //下沉
        public void siftDown(int idx) {
            while (left(idx) < size) {
                int j = left(idx);
                //判断是否存在右节点，并且比左节点大
                if (j + 1 < size && data[j] > data[j + 1]) {
                    j = j + 1;
                }

                //如果根节点比页节点都大，终止执行
                if (data[idx] < data[j]) {
                    break;
                }

                //交换数据，并向下推进idx
                int temp = data[j];
                data[j] = data[idx];
                data[idx] = temp;

                idx = j;
            }
        }

        //获取并移除堆顶数据
        public int findMin() {
            return data[0];
        }

        public int extractMin() {
            int min = findMin();
            //把最后一个放到最前面，然后执行下沉
            data[0] = data[size - 1];
            size = size - 1;
            siftDown(0);
            return min;
        }

        //定义计算索引的公式
        private int parent(int i) {
            return (i - 1) / 2;
        }
        private int left(int i) {
            return 2 * i + 1;
        }
        private int right(int i) {
            return 2 * i + 2;
        }
    }

    public static void main(String[] args) {
        int[] arr = {9,4,2,6,1,8,10};
        int[] res = sort(arr);
        BubboSort.print(res);
    }
}
