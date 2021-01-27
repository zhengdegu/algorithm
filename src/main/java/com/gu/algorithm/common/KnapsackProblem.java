package com.gu.algorithm.common;


/**
 * 动态规划算法
 * <p>
 * 1) 动态规划(Dynamic Programming)算法的核心思想是： 将大问题划分为小问题进行解决， 从而一步步获取最优解
 * 的处理算法
 * 2) 动态规划算法与分治算法类似， 其基本思想也是将待求解问题分解成若干个子问题， 先求解子问题， 然后从这
 * 些子问题的解得到原问题的解。
 * 3) 与分治法不同的是， 适合于用动态规划求解的问题， 经分解得到子问题往往不是互相独立的。 ( 即下一个子
 * 阶段的求解是建立在上一个子阶段的解的基础上， 进行进一步的求解 )
 * 4) 动态规划可以通过填表的方式来逐步推进， 得到最优解.
 *
 *
 * 利用动态规划来解决。每次遍历到的第 i 个物品，根据 w[i]和 v[i]来确定是否需要将该物品
 * 放入背包中。即对于给定的 n 个物品，设 v[i]、w[i]分别为第 i 个物品的价值和重量，C 为背包的容量。再令 v[i][j]
 * 表示在前 i 个物品中能够装入容量为 j 的背包中的最大价值
 *   1.v[i][0]=v[0][j]=0;
 *   2.档w[j]>j时 v[i][j]=v[i-1][j]
 *   3.当j>=w[j]时，v[i][j]=max{v[i-1][j],v[i-1][j-w[i]+v[i]]}
 * @author gu
 * @create 2020/12/11 下午2:09
 */
public class KnapsackProblem {

    public static void main(String[] args) {
        //物品的重量
        int[] w = {1, 4, 3};
        //物品的价值 这里 val[i] 就是前面讲的 v[i]
        int[] val = {1500, 3000, 2000};
        //背包的容量
        int m = 4;
        //物品的个数
        int n = val.length;
        //v[i][j] 表示在前 i 个物品中能够装入容量为 j 的背包中的最大价值
        int[][] v = new int[n + 1][m + 1];

        //为了记录放入商品的情况，我们定一个二维数组
        int[][] path = new int[n + 1][m + 1];
        //初始化第一行和第一列, 这里在本程序中，可以不去处理，因为默认就是 0
        for (int i = 0; i < v.length; i++) {
            v[i][0] = 0;
            v[0][i] = 0;
        }
        //不处理第一行 i 是从 1 开始的
        for (int i = 1; i < v.length; i++) {
            //不处理第一列, j 是从 1 开始的
            for (int j = 1; j < v[0].length; j++) {
                if (w[i - 1] > j) {
                    v[i][j] = v[i - 1][j];
                } else {
                    //说明:
//因为我们的 i 从 1 开始的， 因此公式需要调整成
//v[i][j]=Math.max(v[i-1][j], val[i-1]+v[i-1][j-w[i-1]]);
                    if (v[i - 1][j] < val[i - 1] + v[i - 1][j - w[i - 1]]) {
                        v[i][j] = val[i - 1] + v[i - 1][j - w[i - 1]];
                        path[i][j] = 1;
                    } else {
                        v[i][j] = v[i - 1][j];
                    }
                }
            }
        }
        //输出一下 v 看看目前的情况
        for (int i = 0; i < v.length; i++) {
            for (int j = 0; j < v[i].length; j++) {
                System.out.print(v[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("============================");

        //动脑筋
        int i = path.length - 1; //行的最大下标
        int j = path[0].length - 1; //列的最大下标
        while (i > 0 && j > 0) { //从 path 的最后开始找
            if (path[i][j] == 1) {
                System.out.printf("第%d 个商品放入到背包\n", i);
                j -= w[i - 1]; //w[i-1]
            }
            i--;
        }
    }


    public void update() {

        //物品的重量
        int[] w = {1, 4, 3};
        //物品的价值 这里 val[i] 就是前面讲的 v[i]
        int[] val = {1500, 3000, 2000};
        //背包的容量
        int m = 4;
        //物品的个数
        int n = val.length;
        //v[i][j] 表示在前 i 个物品中能够装入容量为 j 的背包中的最大价值
        int[][] v = new int[n + 1][m + 1];
        //初始化第一行和第一列, 这里在本程序中，可以不去处理，因为默认就是 0
        for (int i = 0; i < v.length; i++) {
            v[i][0] = 0;
            v[0][i] = 0;
        }

        for (int i = 1; i < v.length; i++) {
            for (int j = 1; j < v[0].length; j++) {
                if (j<w[j-1]){
                    v[i][j]=v[i-1][j];
                }

                if (j>=w[j-1]){
                    v[i][j]=val[i-1]+v[i-1][j-w[j-1]];
                }
            }
        }
    }
}

