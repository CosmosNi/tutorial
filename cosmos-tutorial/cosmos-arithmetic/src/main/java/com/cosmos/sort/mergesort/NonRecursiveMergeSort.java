package com.cosmos.sort.mergesort;

import java.util.Arrays;

/**
 * @Author: Cosmos
 * @program: cosmos-tutorial
 * @Description: 非递归归并排序
 * @Date: Create in 2018-12-20 13:28
 * @Modified By：
 */
public class NonRecursiveMergeSort {
    public static void mergeSort(int[] array) {
        int len = 1;
        while (len < array.length) {
            for (int i = 0; i < array.length; i += 2 * len) {
                merge(array, i, len);
            }
            len *= 2;
        }
    }

    public static void merge(int[] array, int startIndex, int endIndex) {
        int leftStartIndex = startIndex;
        //归并的前半部分数组
        int leftHalfLength = startIndex + endIndex;
        int rightStartIndex = startIndex + endIndex;
        //归并的后半部分数组
        int rightHalfLength = rightStartIndex + endIndex;
        int[] temp = new int[2 * endIndex];
        int count = 0;
        while (startIndex < leftHalfLength && rightStartIndex < rightHalfLength && rightStartIndex < array.length) {
            if (array[startIndex] <= array[rightStartIndex]) {
                temp[count++] = array[startIndex++];
            } else {
                temp[count++] = array[rightStartIndex++];
            }
        }
        //注意：这里i也有可能超过数组长度
        while (startIndex < leftHalfLength && startIndex < array.length) {
            temp[count++] = array[startIndex++];
        }
        while (rightStartIndex < rightHalfLength && rightStartIndex < array.length) {
            temp[count++] = array[rightStartIndex++];
        }
        count = 0;
        while (leftStartIndex < rightStartIndex && leftStartIndex < array.length) {
            array[leftStartIndex++] = temp[count++];
        }
    }


    public static void main(String[] args) {

        int[] array = new int[]{1, 5, 6, 8, 9, 4, 3};
        System.out.println("OriginalArray:" + Arrays.toString(array));
        mergeSort(array);
        System.out.println("SortedArray:" + Arrays.toString(array));
    }
}
