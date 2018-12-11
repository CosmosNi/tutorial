package com.cosmos.sort;

import java.util.Arrays;

/**
 * @Author: Cosmos
 * @program: cosmos-tutorial
 * @Description: 选择排序（Selection sort）是一种简单直观的排序算法。
 * 它的工作原理是每一次从待排序的数据元素中选出最小（或最大）的一个元素，
 * 存放在序列的起始位置，直到全部待排序的数据元素排完。
 * 选择排序是不稳定的排序方法（比如序列[5， 5， 3]第一次就将第一个[5]与[3]交换，
 * 导致第一个5挪动到第二个5后面）。
 * 选择排序只是比冒泡排序优化了一点点，比较的次数没有变，但是减少了交换的次数
 * 回顾冒泡排序中，我们每次遇到两个数字的顺序不对时，
 * 立马交换其位置(体现在swap方法写在内层的for循环中)，
 * 而选择排序中，其最大的优化方面体现在(以降序为例) ，
 * 通过一次循环选择出无序区中最大的数字的下标，然后再与无序区第一个位置进行交换。
 * 体现在swap方法在外层for循环中调用。
 * @Date: Create in 2018-12-07 08:58
 * @Modified By：
 */
public class SelectionSort {

    public static void sort(int[] array, boolean asc) {
        for (int i = 0; i < array.length - 1; i++) {
            int index = i;
            for (int j = i + 1; j < array.length; j++) {
                if (asc) {
                    if (array[index] > array[j]) {
                        index = j;
                    }
                } else {
                    if (array[index] < array[j]) {
                        index = j;
                    }
                }
            }

            if (index != i) {
                swap(array, i, index);
            }
        }
    }


    private static void swap(int array[], int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public static void main(String[] args) {

        int[] arr = new int[]{1, 5, 6, 8, 9, 4, 3, 3, 3, 4, 5, 6};
        System.out.println("sort before：" + Arrays.toString(arr));
        sort(arr,true);
        System.out.println("sort asc after：" + Arrays.toString(arr));
        sort(arr, false);
        System.out.println("sort desc after：" + Arrays.toString(arr));
    }

}
