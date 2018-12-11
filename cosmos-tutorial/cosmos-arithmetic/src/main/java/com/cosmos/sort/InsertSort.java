package com.cosmos.sort;

import java.util.Arrays;

/**
 * @Author: Cosmos
 * @program: cosmos-tutorial
 * @Description: 插入排序(Insert Sort)
 * 将待排序的数组分为2部分：有序区，无序区。其核心思想是每次取出无序区中的第一个元素，
 * 插入到有序区中。 有序与无序区划分，就是通过一个变量标记当前数组中，前多少个元素已经是局部有序了。
 * 在排序开始的时候，把数组的第1个元素当成有序区(即有序区只有一个元素)，其余的所有元素当做无序区。
 * 之后在往有序区插入无序去第一个元素值时，
 * 有序区中比这个值小(或者大)的元素都要右移一个位置。
 * 右移并不会覆盖的数组中已有的数据项值，因为我们总是取无序区中的第一个元素插入，
 * 右移也只是覆盖了我们取出的这个元素的位置而已。当无序区为空时，排序完成。
 * 插入排序根据具体实现方式又分为：直接插入排序，二分插入排序（又称折半插入排序），
 * 链表插入排序，希尔排序（又称缩小增量排序）。属于稳定排序的一种（通俗地讲，就是两个相等的数不会交换位置） 。
 * @Date: Create in 2018-12-07 10:07
 * @Modified By：
 */
public class InsertSort {

    public static void sort(int[] arr,boolean asc){
        //有序区最后一个元素位置
        int orderedLastIndex=0;//开始排序时，将有序区结束位置设为0 (开始位置总是0)，对应的无序区范围就是 1-arr.length
        for (int i = orderedLastIndex+1; i < arr.length; i++) { //迭代无序区中的每一个元素，依次插入有序区中
            int temp=arr[i];//记录无序区中的第一个元素值
            int insertIndex=i;//在有序区中插入的索引的位置，刚开始就设置为自己的位置
            for (int j = orderedLastIndex; j >= 0; j--) { //从有序区从后往前开始比较
                if(asc){//升序，有序区中比当前无序区中元素大的都右移一个位置
                    if(arr[j]>temp){
                        arr[j+1]=arr[j];
                        insertIndex--;//有序区每移动一次，将插入位置-1
                    }else{
                        break;//有序区当前位置元素<=无序区第一个元素，那么之前的元素都会<=，不需要继续比较
                    }
                }else{//升序，有序区中比当前无序区中元素小的都右移一个位置
                    if(arr[j]<temp){
                        arr[j+1]=arr[j];
                        insertIndex--;
                    }else{
                        break;
                    }
                }
            }
            arr[insertIndex]=temp;
            orderedLastIndex++;
        }
    }


    public static void main(String[] args) {
        int[] arr=new int[]{1,5,6,8,9,4,3,3};
        System.out.println("排序数组："+ Arrays.toString(arr));
        sort(arr,true);
        System.out.println("升序排列："+Arrays.toString(arr));
        sort(arr,false);
        System.out.println("降序排列："+Arrays.toString(arr));
    }
}
