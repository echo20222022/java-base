package com.base.ds.tree;

import jdk.nashorn.internal.runtime.regexp.joni.exception.InternalException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 大顶堆.
 * 堆的性质：完全二叉树，子节点比父节点小/大
 *
 * */
public class MaxHeap<E extends Comparable<E>> {

    private List<E> data;
    //private int size;

    public MaxHeap(int capacity) {
        //底层用一个动态数组来存储数据
        data = new ArrayList<>(capacity);
    }

    public void add(E e){
        //添加到末尾
        data.add(e);
        //执行上浮操作
        siftUp(data.size() - 1);
    }

    public E findMax() {
        if (isEmpty()) {
            throw new InternalException("heap is empty.");
        }
        //返回堆顶数据
        return data.get(0);
    }

    public E extractMax() {
        E ret = findMax();
        E last = data.get(getSize() - 1);
        data.set(0, last);
        data.remove(getSize() - 1);
        siftDown(0);
        return ret;
    }

    //初始化时，给定数据，自动构建一个大顶堆
    public MaxHeap(E[] dataArray) {
        data = new ArrayList<E>(Arrays.asList(dataArray));
        if (dataArray.length > 1) {
            //从最后一个父节点开始，指向shiftDown操作
            for (int i = parent(data.size() - 1);i >= 0;i --) {
                siftDown(i);
            }
        }
    }

    /**
     * 上浮：添加数据时，先将数据添加到最后一个节点，然后在执行上浮操作
     *      直到整体满足堆的性质
     *  过程：如果父节点的值比当前节点小，就讲父节点和当前节点调换位置，直到根节点为止
     * */
    private void siftUp(int idx) {
        while (idx > 0 && data.get(parent(idx)).compareTo(data.get(idx)) < 0) {
            //交换父子节点的位置
            E temp = data.get(parent(idx));
            data.set(parent(idx), data.get(idx));
            data.set(idx, temp);

            //向上移动指针
            idx = parent(idx);
        }
    }

    /**
     * 下沉：移除头节点时，将最后一个节点移动到头节点，然后执行下沉操作，
     *      直到整体满足堆的性质
     *
     * */
    private void siftDown(int idx) {
        //卡以下左节点的边界
        while (left(idx) < data.size()) {
            int j = left(idx);
            //如果存在右节点，并且右节点的值比左节点的值大，就将j指向右节点，
            //即找到左右子节点中较大的那个
            if (j + 1 < data.size()
                    && data.get(j + 1).compareTo(data.get(j)) > 0) {
                j ++;
            }

            //如果idx处的值比子节点中最大的值还大，就可以终止了
            if (data.get(idx).compareTo(data.get(j)) > 0) {
                break;
            }

            //否则，就交换位置
            E temp = data.get(idx);
            data.set(idx, data.get(j));
            data.set(j, temp);

            //相下一层推进指针
            idx = j;
        }
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

    public int getSize() {
        return data.size();
    }
    public boolean isEmpty() {
        return data.isEmpty();
    }

    public static void main(String[] args) {
        MaxHeap<Integer> heap = new MaxHeap<>(10);
        heap.add(4);
        heap.add(100);
        heap.add(45);
        heap.add(34);
        heap.add(888);
        System.out.println(heap.findMax());
        System.out.println("----");
        for (int i = 0;i < 5;i ++) {
            System.out.println(heap.extractMax());
        }
    }
}
