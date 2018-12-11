package com.cosmos.sort;

import java.util.Arrays;

/**
 * @Author: Cosmos
 * @program: cosmos-tutorial
 * @Description: 冒泡排序
 * 其核心思想是：对于一组需要排序的数字，依次将个位置上的数字与逐一与其之后的数字进行比较，
 * 如果他们的顺序错误就把他们交换过来。
 * 这个算法的名字由来是因为越大的元素会经由交换慢慢“浮”到数列的顶端，故名。
 * 以降序排列为例， 假设有一个n个元素的数组，那么需要进行n-1轮排序，
 * 每一轮排序都确定一个数字的最终位置：
 * 第1轮 将下标为0的数字 与 下标在 [1,n-1]之间的数字逐一进行比较，
 * 每次遇到比下标为0大的数字，都将位置交换，当第一轮排序完成，
 * 最大的数字就是数组第0个位置上的数字
 * 第2轮， 将下标为1的数字 与 下标在 [2,n-1]之间的数字逐一进行比较，
 * 类似第一轮的交换规则，完成之后，第二大的数字就会在下标为1的位置上
 * ..，
 * 第N-1轮，将下标为n-2的数字 与 [n-1,n-1](只剩1个数字)之间的数字逐一进行比较，
 * 将较大大的数字放在第n-2个位置上的数字，此时剩下的最后一个数字就是在n-1位置上，排序完成。
 * 冒泡排序总的平均时间复杂度为O(N2)
 * @Date: Create in 2018-12-07 08:42
 * @Modified By：
 */
public class BubbleSort {

    public static void sort(int array[], boolean asc) {
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = i + 1; j < array.length; j++) {
                if (asc) {
                    if (array[i] > array[j]) {
                        swap(array, i, j);
                    }
                } else {
                    if (array[i] < array[j]) {
                        swap(array, i, j);
                    }
                }
            }
        }
    }

    private static void swap(int array[], int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public static void main(String[] args) {
        int[] array = new int[]{1, 5, 6, 8, 9, 4, 3,3,3,4,5,6};
        System.out.println("排序前：" + Arrays.toString(array));
        sort(array, true);
        System.out.println("升序后：" + Arrays.toString(array));
        sort(array, false);
        System.out.println("降序后：" + Arrays.toString(array));
    }
}
