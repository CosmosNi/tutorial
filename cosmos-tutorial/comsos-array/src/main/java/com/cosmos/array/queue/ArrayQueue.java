package com.cosmos.array.queue;

import java.util.Arrays;

/**
 * @Author: Cosmos
 * @program: data-structure
 * @Description: TODO（描述此类的用法）
 * @Date: Create in 2018-12-06 15:42
 * @Modified By：
 */
public class ArrayQueue<T> {

    Object[] data = null;
    /**
     * 队头指针
     */

    private int front;
    /**
     * 队尾指针
     */
    private int rear;
    /**
     * 队列中的当前元素个数
     */
    private int itemNums;
    private int maxSize;


    public ArrayQueue(int maxSize) {
        this.data = new Object[maxSize];
        this.front = 0;
        this.rear = -1;
        this.itemNums = 0;
        this.maxSize = maxSize;
    }

    /**
     *  插入元素:
     * 1、一般情况下，插入操作是在队列不满的情况下，才调用。因此在插入前，应该先调用isFull
     * 2、在队列中插入元素，正常情况下是在队尾指针(rear)+1的位置上插入，因为我们编写的是循环队列
     * 因此，当队尾指针指向数组顶端的时候，
     * 我们要将队尾指针(rear)重置为-1，此时再加1，就是0，也就是数组顶端
     *
    @param
    element
     *@return
             */

    public void enqueue(T element) {
        if (isFull()) {
            throw new IllegalStateException("queue is full");
        }
        /**
         * 让队列支持循环的核心代码:
         * 如果rear= maxSize - 1，说明下一个元素因该是的数组的首部，将rear置为-1
         * 因为插入操作是队尾指针rear+1的位置，因此下一个位置就是0，即数组第一个元素下标
         */
        if (rear == maxSize - 1) {
            rear = -1;
        }
        data[++rear] = element;
        itemNums++;
    }

    /**
     * 移除元素，返回队头指针front所指向的数据项的值
     * 正常情况下，在remove之前，应该调用isEmpty，如果为空，则不能输入
     * @return
     */
    public T dequeue() {
        if (isEmpty()) {
            throw new IllegalStateException("queue is empty");
        }
        T t = (T) data[front];
        data[front++] = null;
        //当头指针等于最大长度时，此时的queue为空
        if (front == maxSize - 1) {
            front = 0;
        }
        itemNums--;
        return t;
    }

    /**
     * @return
     */
    public T peekFront() {
        if (isEmpty()) {
            throw new IllegalStateException("queue is empty");
        }
        return (T) data[front];
    }

    public int size() {
        return itemNums;
    }

    public boolean isFull() {
        return itemNums == maxSize;
    }

    public boolean isEmpty() {
        return itemNums == 0;
    }

    @Override
    public String toString() {
        return "ArrayQueue [container=" + Arrays.toString(data)
                + ", front=" + front + ", rear=" + rear + ", size="
                + itemNums + ", maxSize=" + maxSize + "]";
    }

}
