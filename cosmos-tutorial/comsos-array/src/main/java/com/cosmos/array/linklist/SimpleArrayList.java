package com.cosmos.array.linklist;

/**
 * @Author: Cosmos
 * @program: data-structure
 * @Description: 简化版的ArrayList
 * @Date: Create in 2018-12-06 09:49
 * @Modified By：
 */
public class SimpleArrayList<T> {

    /**
     * 数组元素个数
     */
    private Integer elementSize = 0;

    /**
     * 初始容量
     */
    private Integer arrayCapacity = 4;

    /**
     * 扩容
     */
    private static final Integer DEFUALT_EXPAND_SIZE = 8;

    Object[] array;

    public SimpleArrayList() {
       this(DEFUALT_EXPAND_SIZE);
    }

    public SimpleArrayList(Integer arrayCapacity) {
        if (arrayCapacity <= 0) {
            throw new IllegalArgumentException("array_capacity must >0");
        }
        array = new Object[arrayCapacity];
        this.arrayCapacity = arrayCapacity;
    }

    public void add(T t) {
        //扩容
        if (elementSize >= arrayCapacity) {
            arrayCapacity = arrayCapacity << 1;

            Object[] newArray = new Object[arrayCapacity];
            System.arraycopy(array, 0, newArray, 0, array.length);
            array = newArray;
        }
        array[elementSize++] = t;
    }

    /**
     * 删除指定位置的元素，所有之后的元素需要前移
     *
     * @param index
     */
    public void remove(int index) {
        if (index < 0 || index > elementSize - 1) {
            throw new ArrayIndexOutOfBoundsException(index);
        }
        for (int i = index; i < elementSize - 1; i++) {
            array[i] = array[i + 1];
        }
        array[--elementSize] = null;

    }

    /**
     * 根据指定下标查找元素
     * @param index
     * @return
     */
    @SuppressWarnings("unchecked")
    public T get(int index){
        if(index<0||index>elementSize-1){
            throw new ArrayIndexOutOfBoundsException(index);
        }
        return (T) array[index];
    }

    /**
     * 更新指定位置上的元素
     * @param index
     * @param element
     */
    public void update(Integer index,T element){
        if(index<0||index>elementSize-1){
            throw new ArrayIndexOutOfBoundsException(index);
        }
        array[index]=element;
    }

    /**
     * 返回array中元素的大小
     * @return
     */
    public Integer size(){
        return elementSize;
    }

    public Integer capacity(){
        return arrayCapacity;
    }

    public static void main(String[] args) {
        SimpleArrayList<Integer> list = new SimpleArrayList<Integer>();
        for (int i = 0; i < 2000; i++) {
            list.add(i);
        }
        list.remove(10);
        System.out.println("size:" + list.size() + ",capacity:" + list.capacity());
    }
}
