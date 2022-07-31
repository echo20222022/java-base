package com.base.ds.csdn.line;

/**
 * 基于数组实现的线性表
 * 操作：
 * 0. 初始化
 * 1. 添加  O(n)
 * 2. 修改  O(1)
 * 3. 删除  O(n)
 * 4. 查找  O(1)
 * 5. 扩容  O(n)
 * */
public class EArrayList<E> {

    private E[] data;
    private int size;

    public EArrayList() {
        this(16);
    }

    public EArrayList(int capacity) {
        data = (E[])new Object[capacity];
        size = 0;
    }

    /**
     * 将元素e添加到列表末尾.
     * @param e
     * */
    public void add(E e) {
        add(e, size);
    }
    //在任意位置添加元素e
    public void add(E e, int index) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("index error");
        }
        if (size == data.length) {
            growUp(data.length * 2);
        }
        //将index后面的位置的数据向后移动一个位置
        for (int i = size - 1;i >= index;i --) {
            data[i + 1] = data[i];
        }
        data[index] = e;
        size ++;
    }

    //扩容到newCapacity
    private void growUp(int newCapacity) {
        System.out.println("EArrayList growing up..");
        E[] newData = (E[])new Object[newCapacity];
        System.arraycopy(data, 0, newData, 0, size);
        data = newData;
    }

    /**
     * 删除指定位置上的数据.
     * @param index
     * @return E
     * */
    public E remove(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("index error");
        }
        E removeValue = data[index];
        //将index之后的元素向前移动一个位置
        for (int i = index;i < size -  1;i ++) {
            data[i] = data[i + 1];
        }
        size --;
        return removeValue;
    }

    /**
     * 修改某个索引上的值
     * @param e
     * @param index
     * @return E
     * */
    public E set(E e, int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("index error");
        }
        E oldValue = data[index];
        data[index] = e;
        return oldValue;
    }

    /**
     * 获取指定位置上的值
     * @param index
     * @return E
     * */
    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("index error");
        }
        return data[index];
    }

    public int getSize() {
        return size;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[ ");
        for (int i = 0;i < size;i ++) {
            sb.append(data[i]);
            if (i != size -1) {
                sb.append(",");
            }
        }
        sb.append(" ]");
        return sb.toString();
    }

    public static void main(String[] args) {
        EArrayList<Integer> list = new EArrayList<>(2);
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        //[ 1,2,3,4 ]
        System.out.println(list);
        list.add(10, 2);
        //[ 1,2,10,3,4 ]
        System.out.println(list);
        list.remove(1);
        //[ 1,10,3,4 ]
        System.out.println(list);
    }
}
