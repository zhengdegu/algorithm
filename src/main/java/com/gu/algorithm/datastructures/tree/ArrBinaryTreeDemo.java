package com.gu.algorithm.datastructures.tree;

/**
 * 1) 顺序二叉树通常只考虑完全二叉树
 * 2) 第 n 个元素的左子节点为 2 * n + 1
 * 3) 第 n 个元素的右子节点为 2 * n + 2
 * 4) 第 n 个元素的父节点为 (n-1) / 2
 * 5) n : 表示二叉树中的第几个元素(按 0 开始编号如图所示)
 *
 * @author gu
 * @create 2021/1/25 上午10:35
 */
public class ArrBinaryTreeDemo {
}

class ArrBinaryTree {
    private final int[] arr;//存储数据结点的数组

    public ArrBinaryTree(int[] arr) {
        this.arr = arr;
    }

    //重载 preOrder
    public void preOrder() {
        this.preOrder(0);
    }
//编写一个方法，完成顺序存储二叉树的前序遍历

    /**
     * @param index 数组的下标
     */
    public void preOrder(int index) {
//如果数组为空，或者 arr.length = 0
        if (arr == null || arr.length == 0) {
            System.out.println("数组为空，不能按照二叉树的前序遍历");
        }
//输出当前这个元素
        System.out.println(arr[index]);
//向左递归遍历
        if ((index * 2 + 1) < arr.length) {
            preOrder(2 * index + 1);
        }
//向右递归遍历
        if ((index * 2 + 2) < arr.length) {
            preOrder(2 * index + 2);
        }
    }
}