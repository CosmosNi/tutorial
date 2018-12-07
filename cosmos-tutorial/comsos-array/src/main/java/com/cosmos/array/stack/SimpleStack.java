package com.cosmos.array.stack;

import java.util.Arrays;

/**
 * @Author: Cosmos
 * @program: data-structure
 * @Description: 栈(Stack)是一种后进先出的数据结构（LIFO:last in first out），
 * 只允许访问栈中的第一个数据项：即最后插入的数据项。移除这个数据项之后，
 * 才能看到第二个数据项，以此类推。
 * 往栈中存入数据称之为压栈(push)，移除数据称之为弹栈(pop)，
 * 此外通常还提供查看栈顶元素的peek方法，此方法可以可以栈顶元素的值，但是并不会将其移除
 * java.util.Stack就是JDK提供的一种对栈的实现，这个实现是基于数组的，
 * 由于栈非常简单，我们没有必须要分析源码，直接按照以下方法提供一个相同的自己的实现，
 * 此外，我们也可以基于链表来实现一个栈
 * @Date: Create in 2018-12-06 13:57
 * @Modified By：
 */
public class SimpleStack<T> {
    private Object[] array = null;
    private int size = 0;
    private static final int DEFAULT_INITIAL_SIZE = 10;
    private int capacity = 0;


    public SimpleStack() {
        this(DEFAULT_INITIAL_SIZE);
    }

    public SimpleStack(int initValue) {
        super();
        this.capacity = initValue;
        array = new Object[initValue];
    }

    /**
     * 取出一个元素
     *
     * @return
     */
    public T pop() {
        if (size < 1) {
            throw new IllegalStateException("no element");
        }
        T result = (T) array[--size];
        array[size] = null;
        return result;
    }

    /**
     * 查看元素
     *
     * @return
     */
    public T peek() {
        if (size < 1) {
            throw new IllegalStateException("no element");
        }
        return (T) array[size - 1];
    }

    public void push(T t) {
        int index = size++;
        //扩容
        if (index > capacity) {
            int new_capacity = capacity + capacity >> 1;
            if (new_capacity <= 0) {
                new_capacity = Integer.MAX_VALUE;
            }
            array = Arrays.copyOf(array, new_capacity);
        }
        array[index] = t;
    }

    public int getSize() {
        return size;
    }


    public static void main(String[] args) {
        SimpleStack<Integer> simpleStack = new SimpleStack<Integer>();
        System.out.print("push:\t");
        for (int i = 0; i < 10; i++) {
            simpleStack.push(i);
            System.out.print(i + "\t");
        }
        System.out.print("\npop:\t");
        while (simpleStack.getSize() > 0) {
            System.out.print(simpleStack.pop() + "\t");
        }
    }
}

