package com.gu.algorithm.common;

/**
 * 分治算法
 * 分治法在每一层递归上都有三个步骤：
 * 1) 分解：将原问题分解为若干个规模较小，相互独立，与原问题形式相同的子问题
 * 2) 解决：若子问题规模较小而容易被解决则直接解，否则递归地解各个子问题
 * 3) 合并：将各个子问题的解合并为原问题的解。
 * <p>
 * 汉诺塔
 *
 * @author gu
 * @create 2020/12/11 下午2:09
 */
public class Hanoitower {

    public static void main(String[] args) {
        hanoiTower(3, 'A', 'B', 'C');
    }

    /**
     * @param num 盘
     * @param a
     * @param b
     * @param c
     */
    public static void hanoiTower(int num, char a, char b, char c) {
        if (num == 1) {
            System.out.println("第1个盘从 " + a + "->" + c);
        } else {
            //如果我们有 n >= 2 情况，我们总是可以看做是两个盘 1.最下边的一个盘 2. 上面的所有盘
            //1. 先把 最上面的所有盘 A->B， 移动过程会使用到 c
            hanoiTower(num-1,a,c,b);
            //2. 把最下边的盘 A->C
            System.out.println("第" + num + "个盘从 " + a + "->" + c);
            //3. 把 B 塔的所有盘 从 B->C , 移动过程使用到 a 塔
            hanoiTower(num - 1, b, a, c);
        }
    }
}
