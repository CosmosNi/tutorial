package com.cosmos.array.linklist;

import java.util.Iterator;

/**
 * @Author: Cosmos
 * @program: data-structure
 * @Description: TODO（描述此类的用法）
 * @Date: Create in 2018-12-06 13:29
 * @Modified By：
 */
public class NodeIterator<T> implements Iterator<T> {

    private Node node;

    public NodeIterator(Node current) {
        this.node = current;
    }

    public boolean hasNext() {
        return node != null;
    }

    public T next() {
        Object data = node.getData();
        node=node.getNextNode();
        return (T)data;
    }

    public void remove() {
        T t = (T)node.getData();

    }
}
