package com.liping.test;

import java.util.Arrays;

/**
 * @author LiPing
 * @create 2017-12-19-15:32
 */
public class Test1 {


    public static void main(String[] args){
        int[] a = {3,2,4};
        System.out.println(Arrays.toString(twoSum(a, 6)));
    }
    
    public static  int[] twoSum(int[] nums, int target) {
        int[] result = new int[2];
        for(int i = 0; i < nums.length - 1; i++) {
            for(int j = i + 1; j < nums.length; j++) {
                if(nums[i] + nums[j] == target) {
                    result[0] = i;
                    result[1] = j;
                    return result;
                }
            }
        }
        return null;
    }
}
