package com.base.ds.csdn.line;

import com.base.ds.line.ArrayStack;
import com.sun.jdi.InternalException;

public class EArrayStack<T> {

    private T[] data;
    private int size;

    public EArrayStack(int capacity) {
        data = (T[])new Object[capacity];
        this.size = 0;
    }

    public void push(T e) {
        if (size == data.length) {
            throw new InternalException("stack has been full.");
        }
        data[size] = e;
        size ++;
    }

    public T pop() {
        if (size == 0) {
            throw new InternalException("stack empty");
        }
        T popElem = data[size - 1];
        size --;
        return popElem;
    }

    public T peek() {
        if (size == 0) {
            throw new InternalException("stack empty");
        }
        return data[size - 1];
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[ ");
        for (int i = 0;i < size;i ++) {
            sb.append(data[i]);
            if (i != size - 1) {
                sb.append(" -> ");
            }
        }
        sb.append(" ]");
        return sb.toString();
    }

    public static void main(String[] args) {
        ArrayStack stack = new ArrayStack(10);
        stack.push(1);
        stack.push(2);
        stack.push(3);
        System.out.println(stack);
        System.out.println(stack.peek());
        System.out.println(stack.pop());
        System.out.println(stack);
    }

}
