package com.liping.algorithm.sort;

import org.apache.poi.ss.formula.functions.T;

import java.util.Arrays;

/**
 * 冒泡排序算法
 *
 * @author Felix Lee
 * @create 2019-05-08-18:24
 */
public class BubbleSort {
    
    public static void bubbleSort(int[] array) {
        
        if (array == null || array.length <= 0) {
            return;
        }
        
        
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = 0; j < array.length - 1 - i; j++) {
                if (array[j] > array[j + 1]) {
                    //swap(array, array[j], array[j + 1]);
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
        
    }
    
    public static void swap(T[] arr, int a, int b) {
        T temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }
    
    public static void main(String[] args) {
        int[] arr = {21, 12, 32, 23, 35, 39, 54, 7, 29, 67, 9};
        System.out.println("排序前：" + Arrays.toString(arr));
        BubbleSort.bubbleSort(arr);
        System.out.println("排序后：" + Arrays.toString(arr));
        //Arrays.stream(arr).forEach(System.out::println);
    }
    
    
}
