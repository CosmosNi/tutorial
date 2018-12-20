package com.cosmos.sort.mergesort;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * @Author: Cosmos
 * @program: cosmos-tutorial
 * @Description: 归并排序的思想：
 * <p>
 * 将一个数组拆分成两半，分别对每一半进行排序，然后使用合并(merge)操作，
 * 把两个有序的子数组合并成一个整体的有序数组。我们可以把一个数组刚开始先分成两，
 * 也就是2个1/2，之后再将每一半分成两半，也就是4个1/4，
 * 以此类推，反复的分隔数组，直到得到的子数组中只包含一个数据项，
 * 这就是基值条件，只有一个数据项的子数组肯定是有序的。
 * @Date: Create in 2018-12-20 12:50
 * @Modified By：
 */
public class RecursiveMergeSort {

    public static void mergeSort(int[] data, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;
            //递归左侧半边数组
            mergeSort(data, left, mid);
            //递归右侧半边数组
            mergeSort(data, mid + 1, right);
            merge(data, left, right);
        }
    }

    private static void merge(int array[], int startIndex, int endIndex) {
        int mid = (startIndex + endIndex) / 2;
        int leftStartIndex = startIndex;
        int rightStartIndex = mid + 1;
        int[] tempArray = new int[endIndex - startIndex + 1];
        int count = 0;
        //同时判断，当两个数组都还存在元素时不退出
        while (leftStartIndex <= mid && rightStartIndex <= endIndex) {
            if (array[leftStartIndex] <= array[rightStartIndex]) {
                tempArray[count++] = array[leftStartIndex++];
            } else {
                tempArray[count++] = array[rightStartIndex++];
            }
        }
        while (leftStartIndex <= mid) {
            tempArray[count++] = array[leftStartIndex++];
        }
        while (rightStartIndex <= endIndex) {
            tempArray[count++] = array[rightStartIndex++];
        }
        count = 0;
        //将temp数组写入原数组

        while (startIndex <= endIndex) {
            array[startIndex++] = tempArray[count++];
        }

    }

    public static void printArray(int arr[]) {
        for (int k = 0; k < arr.length; k++) {
            System.out.print(arr[k] + "\t");
        }
    }

    public static void main(String[] args) {
        int[] data = {543, 23, 45, 65, 76, 1, 456, 7, 77, 88, 3, 9};
        System.out.print("数组排序前：");
        printArray(data);
        System.out.print("\n");
        mergeSort(data, 0, data.length - 1);
        System.out.print("归并排序后：");
        printArray(data);
    }

}
