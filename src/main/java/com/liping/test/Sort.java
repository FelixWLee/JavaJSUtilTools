package com.liping.test;

import java.util.Arrays;

/**
 * 排序算法
 *
 * @author LiPing
 * @create 2019-03-31-13:40
 */
public class Sort {
    
    /**
     * 直接选择排序：每次从找出最小的元素放在前面。依次循环
     * @param arr
     */
    public static void selectSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        for (int i = 0; i < arr.length - 1; i++) {
            //表示最小元素的下标
            int k = i;
            //找到当前排序区间中最小元素的下标
            for (int j = i + 1; j < arr.length; j++) {
                //如果后面的元素小于当前最小元素，则用k记录下后面最小元素的下标
                if (arr[j] < arr[k]) {
                    k = j;
                }
            }
            //如果k==i,说明k就是当前排序区间首位元素的下标，因此不用交换
            if (k != i) {
                int temp = arr[k];
                arr[k] = arr[i];
                arr[i] = temp;
            }
        }
    }
    
    public static void main(String[] args){
//        int[] a = {5,1, 4, 6, 0 , 9};
//        selectSort(a);
//        System.out.println(Arrays.toString(a));

        int i = (4-1)/2 + 1;
        System.out.println(i);
        
    }
}
