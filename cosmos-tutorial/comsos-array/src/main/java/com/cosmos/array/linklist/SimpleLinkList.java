package com.cosmos.array.linklist;


import java.util.Iterator;

/**
 * @Author: Cosmos
 * @program: data-structure
 * @Description: 双向链表的实现
 *               实现Iterable，才能够使用增强for循环
 *               链表与数组一样，都作为数据的基本存储结构，
 *               但是在存储原理上二者是不同的。
 *               在数组中，数据是存储在一段连续的内存空间中，我们可以通过下标来访问数组中的元素；
 *               而在链表中，元素是存储在不同的内存空间中，前一个元素的位置维护了后一个元素在内存中的地址，
 *               在Java中，就是前一个元素维护了后一个元素的引用。
 * @Date: Create in 2018-12-06 10:41
 * @Modified By：
 */


public class SimpleLinkList<T> implements Iterable {

    protected Node firstNode = null;
    protected Node lastNode = null;

    protected int size;
    private Node currentNode;

    /**
     * 添加元素到头部
     *
     * @param element
     */
    public void addFirst(T element) {
        if (element == null) {
            throw new NullPointerException();
        }
        Node node = new Node();
        node.setData(element);
        Node currentFirst = firstNode;
        node.setNextNode(currentFirst);

        if (firstNode == null) {
            lastNode = node;
        }
        firstNode = node;
        size++;
    }


    /**
     * 添加元素到尾部
     *
     * @param element
     */
    public void addLast(T element) {
        if (element == null) {
            throw new NullPointerException();
        }
        Node node = new Node();
        node.setData(element);
        if (lastNode == null) {
            lastNode = node;
            firstNode = node;
        } else {
            lastNode.setNextNode(node);
            lastNode = node;
        }


    }

    /**
     * 移除元素
     *
     * @param element
     * @return
     */
    public boolean remove(T element) {
        if (element == null) {
            throw new NullPointerException();
        }
        if (size == 0) {
            return false;
        }
        if (size == 1 && firstNode.getData().equals(element)) {
            firstNode = null;
            size--;
        }
        Node pre = firstNode;
        Node currentNode = firstNode.getNextNode();
        while (currentNode != null) {
            if (currentNode.getData().equals(element)) {
                pre.setNextNode(currentNode.getNextNode());
                return true;
            }
            pre = currentNode;
            currentNode = currentNode.getNextNode();
        }
        return false;
    }


    public boolean contain(T element) {
        if (element == null) {
            throw new NullPointerException();
        }
        if (size == 0) {
            return false;
        }
        Node currentNode = firstNode;
        while (currentNode != null) {
            if (currentNode.getData().equals(element)) {
                return true;
            }
            currentNode = currentNode.getNextNode();
        }
        return false;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void display() {
        if (!isEmpty()) {
            Node current = firstNode;
            while (current != null) {
                System.out.print(current.getData() + "\t");
                current = current.getNextNode();
            }
        }
    }

    public Iterator iterator() {
        return new NodeIterator<T>(firstNode);
    }

    public static void main(String[] args) {
        SimpleLinkList<Integer> simpleLinkList = new SimpleLinkList();
        for (int i = 0; i < 10; i++) {
            simpleLinkList.addFirst(i);
        }
        for (int i = 0; i < 10; i++) {
            simpleLinkList.addLast(i);
        }
        simpleLinkList.remove(0);
        simpleLinkList.display();
        System.out.println(simpleLinkList.contain(0));

        Iterator<Integer> iterator = simpleLinkList.iterator();
        while (iterator.hasNext()) {
            Integer next = iterator.next();
            System.out.print(next + "\t");
        }
        System.out.println();
        for (Object data : simpleLinkList) {
            System.out.print(data + "\t");
        }
    }


}
