package com.cosmos.array.array;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author: Cosmos
 * @program: data-structure
 * @Description: TODO（描述此类的用法）
 * @Date: Create in 2018-12-06 08:25
 * @Modified By：
 */
public class Array<V> {
    /**
     * 对象数组
     */
    private Object[] elements;
    /**
     * 元素数量
     */

    private volatile AtomicInteger size = new AtomicInteger(0);
    /**
     * 容量
     */
    private int capacity;
    private Object test;

    public Array(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException("capacity must greater than 0");
        } else {
            this.capacity = capacity;
            elements = new Object[capacity];
        }
    }

    /**
     * 新增对象
     *
     * @param v
     */
    public void add(V v) {
        if (v == null) {
            throw new NullPointerException();
        }
        if (size.get() >= capacity - 1) {
            throw new IndexOutOfBoundsException();
        }
        synchronized (elements) {
            elements[size.getAndIncrement()] = v;
        }
    }

    /**
     * 移除元素
     *
     * @param v
     * @return
     */
    public boolean remove(V v) {
        if (v == null) {
            throw new NullPointerException();
        }
        for (int i = 0; i < size.get(); i++) {

            if (elements[i].equals(v)) {
                synchronized (elements) {
                    while (i < size.get() - 1) {
                        elements[i] = elements[++i];
                    }
                    elements[size.decrementAndGet()] = null;
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 判断元素是否存在
     *
     * @param v
     * @return
     */
    public boolean isExist(V v) {
        if (v == null) {
            throw new NullPointerException();
        }
        for (int i = 0; i < size.get(); i++) {
            if (elements[i].equals(v)) {
                return true;
            }
        }
        return false;
    }


    /**
     * 查找元素
     *
     * @param index
     * @return
     */
    public V get(int index) {
        if (index >= capacity) {
            throw new IndexOutOfBoundsException();
        }
        return (V) elements[index];
    }

    /**
     * 显示所有元素
     */
    public void show() {
        for (int i = 0; i < elements.length; i++) {
            if (i < size.get()) {
                System.out.print(elements[i] + "    ");
            } else {
                System.out.print("null" + "    ");
            }
        }
        System.out.println();
    }


    /**
     * 返回当前数组的元素数量
     *
     * @return
     */
    public int getSize() {
        return size.get();
    }

    public static void main(String[] args) {
        Array<Integer> array = new Array<Integer>(5);
        array.add(1);
        array.add(2);
        array.show();
        array.add(3);
        array.remove(2);
        array.remove(1);
        array.show();
        System.out.println(array.isExist(1));

        System.out.println(array.testEqual(null));
    }

    public boolean testEqual(Object object) {
        Object test = null;
        if (object.equals(test)) {
            return true;
        }
        return false;
    }
}
