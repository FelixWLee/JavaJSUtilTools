package com.liping.leecode.array.doubledim;

/**
 * @author LiPing
 * @create 2023-03-05-19:05
 */
public class GenMatrix {

    /**
     * 生成一个 n×n 空矩阵 mat，随后模拟整个向内环绕的填入过程：
     * 定义当前左右上下边界 l,r,t,b，初始值 num = 1，迭代终止值 tar = n * n；
     * 当 num <= tar 时，始终按照 从左到右 从上到下 从右到左 从下到上 填入顺序循环，每次填入后：
     * 执行 num += 1：得到下一个需要填入的数字；
     * 更新边界：例如从左到右填完后，上边界 t += 1，相当于上边界向内缩 1。
     * 使用num <= tar而不是l < r || t < b作为迭代条件，是为了解决当n为奇数时，矩阵中心数字无法在迭代过程中被填充的问题。
     * 最终返回 mat 即可。
     *
     * 作者：Krahets
     * 链接：https://leetcode.cn/problems/spiral-matrix-ii/solutions/12594/spiral-matrix-ii-mo-ni-fa-she-ding-bian-jie-qing-x/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。 
     * @param args
     */
    public static void main(String[] args) {

        int[][] a = gen(4);
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[i].length; j++) {
                System.out.print(a[i][j] + " ");
            }
            System.out.print("\n");
        }


    }

    private static int[][] gen(int n) {
        int[][] m = new int[n][n];

        int t = 0, b = n - 1, l = 0, r = n - 1;

        int num = 1;
        while (num <= n * n) {
            // 从左往右。行不变列变
            for (int i = 0; i <= r; i++) {
                m[t][i] = num++;
            }
            t++;
            // 从上往下。行变列不变
            for (int i = t; i <= b; i++) {
                m[i][r] = num++;
            }
            r--;
            // 从右往左。列变行不变
            for (int i = r; i >= l; i--) {
                m[b][i] = num++;
            }
            b--;

            // 从下往上。行变，列不变
            for (int i = b; i >= t; i--) {
                m[i][l] = num++;
            }
            l++;
        }


        return m;
    }


}
