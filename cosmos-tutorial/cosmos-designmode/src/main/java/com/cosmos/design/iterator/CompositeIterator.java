package com.cosmos.design.iterator;

import java.util.Iterator;
import java.util.Stack;

/**
 * @Author: Cosmos
 * @program: cosmos-tutorial
 * @Description: TODO（描述此类的用法）
 * @Date: Create in 2018-12-20 08:51
 * @Modified By：
 */
public class CompositeIterator<T> implements Iterator {

    Stack stack = new Stack();

    public CompositeIterator(Iterator iterator) {
        //将初代迭代器压入栈
        stack.push(iterator);
    }

    @Override
    public boolean hasNext() {
        //检查堆栈是否被清空了
        if (stack.isEmpty()) {
            return false;
        } else {
            //从堆栈的顶层取出迭代器，看看是否还有下一个元素，如果没有，则将迭代器弹出堆栈，遍历下一个迭代器
            Iterator iterator = (Iterator) stack.peek();
            if (!iterator.hasNext()) {
                stack.pop();
                return hasNext();
            } else {
                return true;
            }
        }

    }

    @Override
    public T next() {
        if (hasNext()) {
            //查看栈顶部的对象
            Iterator iterator = (Iterator) stack.peek();
            MenuComponent component = (MenuComponent) iterator.next();
            //如果该对象是一个菜单对象，则将对象的子菜单集合压入栈，用作后续遍历
            if (component instanceof Menu) {
                stack.push(component.createIterator());
            }
        }
        return null;
    }
}
