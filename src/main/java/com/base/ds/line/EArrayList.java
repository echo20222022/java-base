package com.base.ds.line;

/**
 * 动态数组.
 * @author
 *  存储结构：数组
 *  操作：创建、新增、更新、删除、查找
 * */
public class EArrayList<T> {

    private T[] data;
    private int size;

    public EArrayList() {
        this(16);
    }

    //创建/初始化
    public EArrayList(int capacity) {
        this.data = (T[])new Object[capacity];
        this.size = 0;
    }

    //默认往末尾添加
    public void add(T data) {
        add(data, size);
    }

    //向指定位置添加你
    public void add(T data, int idx) {
        if (idx < 0 || idx > size) {
            throw new IllegalArgumentException("illegal index.");
        }

        //底层数组容量满了，要扩容
        if (size == this.data.length) {
            growUp(2 * size);
        }

        //将idx 后面的元素向后移动1个位置
        for (int i = size - 1;i >= idx;i --) {
            this.data[i + 1] = this.data[i];
        }
        this.data[idx] = data;
        this.size ++ ;
    }

    //修改
    public void set(T data, int idx) {
        if (idx < 0 || idx >= size) {
            throw new IllegalArgumentException("illegal index.");
        }
        this.data[idx] = data;
    }

    //删除-指定的数据
    /**
     * 先找到指定的位置，
     * 然后将这个位置后面的值向前移动一个位置
     * */
    public void remove(T data) {
        int res = find(data);
        if (res != -1) {
            for (int i = res;i < size - 1;i ++) {
                this.data[i] = this.data[i + 1];
            }
            this.size -- ;
        }
    }

    //删除某一个索引上的数据
    public T remove(int idx) {
        if (idx < 0 || idx >= size) {
            throw new IllegalArgumentException("illegal index.");
        }
        T res = this.data[idx];
        for (int i = idx;i < size - 1;i ++) {
            this.data[i] = this.data[i + 1];
        }
        this.size -- ;
        return res;
    }

    //得到指定索引位置的数据
    public T get(int idx) {
        if (idx < 0 || idx >= size) {
            throw new IllegalArgumentException("illegal index.");
        }
        return this.data[idx];
    }

    public int find(T data) {
        int res = -1;
        for (int i = 0;i < size;i ++) {
            if (data.equals(this.data[i])) {
                res = i;
            }
        }
        return res;
    }

    /**
     * 扩容逻辑：
     *   1.创建新的数组，长度 * 2
     *   2.将原数组中的内容复制到新数组中
     *   3.将data指向新数组
     * */
    private void growUp(int newCapacity) {
        System.out.println("growing up...");
        T[] newData = (T[])new Object[newCapacity];
        System.arraycopy(this.data, 0, newData, 0, size);
        this.data = newData;
    }

    public int size() {
        return size;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[ ");
        if (size > 0) {
            for (int i = 0;i < size;i ++) {
                stringBuilder.append(this.data[i]);
                if (i < size -1) {
                    stringBuilder.append(",");
                }
            }
        }
        stringBuilder.append(" ]");
        return stringBuilder.toString();
    }

    /**

     [ 0 ]
     growing up...
     growing up...
     [ 0,1,2 ]
     [ 0,1,20 ]
     growing up...
     5
     [ 0,1,20,30,40 ]
     [ 0,1,20,30 ]
     20
     [ 0,1,30 ]
     30

     * */
    public static void main(String[] args) {
        EArrayList<Integer> list = new EArrayList<>(1);
        list.add(0);
        System.out.println(list);
        list.add(1);
        list.add(2);
        System.out.println(list);
        list.set(20, 2);
        System.out.println(list);
        list.add(30);
        list.add(40);
        System.out.println(list.size());
        System.out.println(list);
        list.remove(new Integer(40));
        System.out.println(list);
        System.out.println(list.remove(2));
        System.out.println(list);
        System.out.println(list.get(2));
    }
}
