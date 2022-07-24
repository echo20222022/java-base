package com.base.ds.tree;

/**
 * 利用大顶堆实现一个优先级队列
 * */
public class EPriorityQueue<E extends Comparable<E>> {

    private MaxHeap<E> maxHeap;

    public EPriorityQueue() {
        maxHeap = new MaxHeap<>(16);
    }

    //入队
    public void enqueue(E e) {
        maxHeap.add(e);
    }
    //出队
    public E enqueue() {
        return maxHeap.extractMax();
    }

    //获取队头元素
    public E getFront() {
        return maxHeap.findMax();
    }

    public int getSize() {
        return maxHeap.getSize();
    }

    public boolean isEmpty() {
        return maxHeap.isEmpty();
    }
}
