package com.cosmos.sort;

import java.util.Arrays;

/**
 * @Author: Cosmos
 * @program: cosmos-tutorial
 * @Description: TODO（描述此类的用法）
 * @Date: Create in 2018-12-20 13:39
 * @Modified By：
 */
public class QuickSort {

    public static void sort(int array[], int leftIndex, int rightIndex) {
        if (leftIndex >= rightIndex) {
            return;
        }
        int partitionIndex = doSort(array, leftIndex, rightIndex);

        sort(array, leftIndex, partitionIndex - 1);
        sort(array, partitionIndex + 1, rightIndex);
    }

    public static int doSort(int[] arr, int start, int end) {
        int pivot = getPivot(arr, start, end);
        int left_pointer = start - 1;
        int right_pointer = end + 1;
        while (true) {
            //left_pointer当遇到比基准值大的元素，停下来
            while (arr[++left_pointer] < pivot) {
            }
            //right_pointer当遇到比基准值小的元素，停下来
            while (arr[--right_pointer] > pivot) {
            }
            if (left_pointer >= right_pointer) {
                break;
            }
            int temp = arr[left_pointer];
            arr[left_pointer] = arr[right_pointer];
            arr[right_pointer] = temp;
        }
        return right_pointer;
    }

    /**
     * 获取基准值
     */
    private static int getPivot(int array[], int start, int end) {
        return array[start];
    }

    public static void main(String[] args) {
        // int[] arr = {3,4,2,0,4,7,9,6,5,8};
        int[] arr = {3, 3, 3, 3, 4, 7, 2, 6, 5, 8};
        System.out.println("排序前数组:" + Arrays.toString(arr));
        sort(arr, 0, arr.length - 1);
        System.out.println("排序前数后:" + Arrays.toString(arr));
        Arrays.sort(arr);
    }
}
