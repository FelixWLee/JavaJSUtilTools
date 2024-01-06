package com.liping.leecode.array.doubledim;

import java.util.Arrays;
import java.util.Random;

/**
 * @author LiPing
 * @create 2023-02-26-15:43
 */
public class DoubleDimArray {
    /**
     * 题目：
     *
     * 某公司同兄弟公司在今年3月份举办了摄影比赛，收到100张参赛照片，有10个评委为每张照片评分，分数为1~100分，照片最后得分为：去掉一个最高分和一个最低分后其余8个分数的平均值，照片分数已经存入grade[100][10]整型数组中，grade[1][3]表示4号评委对2号照片的评分。
     *
     * 1、请编程输出每张照片的最后得分
     * 2、同时对评委评分进行裁判，即在10个评委中找出最公平（评分最接近平均分）和最不公平（与平均分差别最大）的评委，请输出最公平和最不公平的评委号数。
     * ————————————————
     * 版权声明：本文为CSDN博主「明快de玄米61」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
     * 原文链接：https://blog.csdn.net/qq_42449963/article/details/103589691
     * @param args
     */
    public static void main(String[] args){

        int[][] grade = new int[100][10];
        // 生成测试数据
        Random random = new Random();
        for (int i = 0; i < grade.length; i++) {
            for (int j = 0; j < grade[i].length; j++) {
                // 随机生成0~100的分数
                grade[i][j] = random.nextInt(101);
            }
        }
        getSocreInfo(grade);

    }

    private static void getSocreInfo(int[][] grade) {
        // 各张照片的分数
        Double[] socres = new Double[100];

        // 去掉一个最低分和一个最高分，获得每张照片的平均分
        for (int i = 0; i < grade.length; i++) {
            // 把每张照片的10个分数复制到一个新的数组中
            int[] score = Arrays.copyOf(grade[i], grade[i].length);
            // 把新数组中的元素按照从小到大的顺序排序
            Arrays.sort(score);
            // 计算每张照片的总分
            double sum = 0;
            // 由于上面已经进行了排序，然后下面去除最低分和最高分，就可以获得中间8个分数的总分
            for (int j = 1; j < score.length - 1; j++) {
                sum += score[j];
            }
            // 获得每张照片的平均分
//            socres[i] = Double.parseDouble(new DecimalFormat("#.00").format(sum / 8));
            socres[i] = Double.parseDouble(String.valueOf(sum / 8));
        }
        // 打印得分
        Arrays.asList(socres).forEach(System.out::println);

        // 存储每位评委对100幅作品的打分与平均分的差值之和
        double[] differenceSum = new double[10];
        // 计算每位评委对100幅作品的打分与平均分的差值之和
        for (int i = 0; i < grade.length; i++) {
            for (int j = 0; j < grade[i].length; j++) {
                differenceSum[j] += Math.abs(grade[i][j] - socres[i]);
            }
        }

        // 找出最公平的和最不公平的评委号码
        int noFairIndex = 0;
        int fairIndex = 0;
        for (int i = 0; i < differenceSum.length; i++) {
            if (differenceSum[noFairIndex] < differenceSum[i]) {
                noFairIndex = i;
            }
            if (differenceSum[fairIndex] > differenceSum[i]) {
                fairIndex = i;
            }
        }
        System.out.println("最公平：" + (fairIndex + 1) + "号评委");
        System.out.println("最不公平：" + (noFairIndex + 1) + "号评委");
    }
}
