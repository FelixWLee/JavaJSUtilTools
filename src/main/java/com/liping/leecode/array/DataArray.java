package com.liping.leecode.array;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

/**
 * 二分查找 时间复杂度 O(logn)
 * 数组mid计算 = （right下标-left下标）/ 2 + left下标。（mid = (left + right) / 2 容易溢出！因为left+right很容易超过int范围），取整数
 *
 *
 * @author LiPing
 * @create 2022-06-14-17:15
 */
public class DataArray {

    /**
     * 给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。
     * 请必须使用时间复杂度为 O(log n) 的算法。
     * 二分（折半）查找，
     *
     * 链接：https://leetcode.cn/leetbook/read/array-and-string/cxqdh/
     *
     *
     * @param nums 已排序的数组
     * @param target  要查询的数字
     * @return 已查询或插入的数组下标
     */
    public static int searchInsert(int[] nums, int target) {
        int n = nums.length;
        // 数组左下标，数组右下标
        int left = 0;
        int right = n - 1;


        while (left <= right) {

            // 中间下标
            int mid = (right - left)/2 + left;
            if (target <= nums[mid]) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return left;

    }


    /**
     * 给一个二维的区间数组，合并有重合的区间
     *
     * https://leetcode.cn/problems/merge-intervals/solution/he-bing-qu-jian-by-leetcode-solution/
     * @param intervals 需要合并的区间
     * @return 合并完的区间
     */
    public static int[][] mergeIntervals(int[][] intervals){

        if (intervals.length == 0) {
            return new int[0][2];
        }
        // 按区间左端点，按升序排序
        java.util.Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));
        List<int[]> merged = new ArrayList<>();
        for (int i = 0; i < intervals.length; i++){
            int l = intervals[i][0];
            int r = intervals[i][1];

            // 当前区间的左端点大于merge数组（结果集）的右端点，无重合，直接放入结果集
            if (merged.size() == 0 || merged.get(merged.size() - 1)[1] < l) {
                merged.add(new int[]{l, r});
            } else {
                // 取当前区间的右端值和merged数组的右端点的最大值
                merged.get(merged.size() - 1)[1] = Math.max(merged.get(merged.size() - 1)[1], r);
            }
        }
        return merged.toArray(new int[merged.size()][]);
    }


    /**
     * 二维数组 矩阵（n*n）顺时针旋转90度
     * 对于矩阵中的第一行而言，在旋转后，它出现在倒数第一列的位置：
     * 并且，第一行的第 xx 个元素在旋转后恰好是倒数第一列的第 xx 个元素。
     * 对于矩阵中的第二行而言，在旋转后，它出现在倒数第二列的位置
     * 对于矩阵中的第三行和第四行同理。这样我们可以得到规律：
     * 对于矩阵中第 i 行的第 j 个元素，在旋转后，它出现在倒数第 i 列的第 j 个位置。
     * 我们将其翻译成代码。由于矩阵中的行列从 00 开始计数，因此对于矩阵中的元素 matrix[row][col]，在旋转后，它的新位置为 matrixnew[col][n−row−1]
     *
     * 链接：https://leetcode.cn/problems/rotate-image/solution/xuan-zhuan-tu-xiang-by-leetcode-solution-vu3m/
     *
     * @return 旋转后的矩阵
     */
    public static void matrixRotate(int[][] matrix) {
        int n = matrix.length;
        int[][] matrixNew = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                // 因此对于矩阵中的元素 matrix[row][col]，在旋转后，它的新位置为 matrixnew[col][n−row−1]
                matrixNew[j][n - i -1] = matrix[i][j];
            }
        }

        for (int i = 0; i < n; i++) {
            System.arraycopy(matrixNew[i], 0, matrix[i], 0, n);
        }

    }

    public static void main(String[] args){
//        int[] nums = new int[4];
//        nums[0] = 1;
//        nums[1] = 3;
//        nums[2] = 5;
//        nums[3] = 6;
//
//        int i = searchInsert(nums, 5);
//        System.out.println(i);
        List<int[]> merged = new ArrayList<int[]>();
        int[] arr = new int[]{1, 9, 2, 5, 19, 20, 10, 11, 12, 20, 0, 3, 0, 1, 0, 2};
        int[][] intervals = new int[arr.length][2];

        for (int i = 0; i < arr.length - 1; i= i+2){
            intervals[i][0] = arr[i];
            intervals[i][1] = arr[i+1];
        }

        int[][] ints = mergeIntervals(intervals);
        Stream.of(ints).forEach(t -> System.out.println(java.util.Arrays.toString(t)));


    }
}


