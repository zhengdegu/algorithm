package com.gu.algorithm.datastructures.sparsearray;

/**
 * 稀疏数组的处理方法是:
 * 1) 记录数组一共有几行几列，有多少个不同的值
 * 2) 把具有不同值的元素的行列及值记录在一个小规模的数组中，从而缩小程序的规模
 * 稀疏数组
 *
 * @author gu
 * @create 2021/1/19 上午9:03
 */
public class SparseArray {
    public static void main(String[] args) {
        //创建一个原始的二维数组 11*11  0表示没有棋子  1表示黑色棋子  2表示白色棋子
        int chessArr1[][] = new int[11][11];
        chessArr1[1][2] = 1;
        chessArr1[2][3] = 2;
        chessArr1[4][5] = 2;
        //输出原始二维数组
        System.out.println("原始二维数组~~~~~");
        for (int[] ints : chessArr1) {
            for (int anInt : ints) {
                System.out.printf("%d\t", anInt);
            }
            System.out.println();
        }
        //将二维数组转稀疏数组
        // 1.先遍历二位数组 得出非0的个数
        int sum = 0;
        for (int i = 0; i < chessArr1.length; i++) {
            for (int j = 0; j < chessArr1[0].length; j++) {
                if (chessArr1[i][j] != 0) {
                    sum++;
                }
            }
        }
        //2.创建稀疏数组   赋值给稀疏数组
        int sparseArr[][] = new int[sum + 1][3];
        sparseArr[0][0] = 11;
        sparseArr[0][1] = 11;
        sparseArr[0][2] = sum;
        //遍历二维数组，将非零
        int count = 0;//记录第几个非0数
        for (int i = 0; i < chessArr1.length; i++) {
            for (int j = 0; j < chessArr1[0].length; j++) {
                if (chessArr1[i][j] != 0) {
                    count++;
                    sparseArr[count][0] = i;
                    sparseArr[count][1] = j;
                    sparseArr[count][2] = chessArr1[i][j];
                }
            }
        }
        //输出稀疏数组的形式
        System.out.println("稀疏数组~~~~");
        for (int i = 0; i < sparseArr.length; i++) {
            System.out.printf("%d\t%d\t%d\t\n", sparseArr[i][0], sparseArr[i][1], sparseArr[i][2]);
        }
        System.out.println();
        //将稀疏数组恢复为二位数组
        //1.首先读取稀疏数组的第一行 根据第一行的数据确定二位数组的大小和有几个非零的数据
        int chessArr2[][] = new int[sparseArr[0][0]][sparseArr[0][1]];
        //2.读取稀疏数组后几位将数据赋值给二位数珠
        for (int i = 1; i <= sparseArr[0][2]; i++) {
            chessArr2[sparseArr[i][0]][sparseArr[i][1]] = sparseArr[i][2];
        }
        System.out.println("恢复出的二维数组~~~");
        for (int[] row : chessArr2) {
            for (int data : row) {
                System.out.printf("%d\t", data);
            }
            System.out.println();
        }
    }
}
