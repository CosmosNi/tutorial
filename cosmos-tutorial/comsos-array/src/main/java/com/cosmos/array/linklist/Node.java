package com.cosmos.array.linklist;

/**
 * @Author: Cosmos
 * @program: data-structure
 * @Description: TODO（描述此类的用法）
 * @Date: Create in 2018-12-06 10:47
 * @Modified By：
 */
public class Node {

    private Object data;

    private Node NextNode;

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Node getNextNode() {
        return NextNode;
    }

    public void setNextNode(Node nextNode) {
        NextNode = nextNode;
    }
}
