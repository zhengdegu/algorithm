package com.gu.algorithm.common;

import java.util.Collections;
import java.util.Stack;

/**
 * @author gu
 * @create 2020/12/15 上午9:06
 */
public class ListNodeSum {


    public static void main(String[] args) {

        ListNode listNode1 = new ListNode(new ListNode(new ListNode(3), 4), 2);
        ListNode listNode2 = new ListNode(new ListNode(new ListNode(4), 6), 5);
//        ListNode listNode = sumList(listNode1, listNode2);
//
        ListNode listNode = reverseList(listNode1);
        System.out.println("=====================================");
        System.out.println(jumpFloor(4));

    }


    /**
     * 调整数组是奇数为前面   偶数位于后面
     *
     * @param array
     * @return
     */


    /**
     * 跳台阶问题   青蛙一次可以跳一个台阶  也可以挑两个台阶
     *
     * @param floor
     * @return
     */
    public static int jumpFloor(int floor) {
        if (floor <= 0) {
            return 0;
        }
        if (floor == 1) {
            return 1;
        }
        if (floor == 2) {
            return 2;
        }
        //递归的思想
        //return jumpFloor(floor-1)+jumpFloor(floor-2);
        int first = 1, second = 2, third = 0;
        for (int i = 3; i <= floor; i++) {
            third = first + second;
            first = second;
            second = third;
        }
        return third;
    }


    /**
     * 二维数组查找
     * 矩阵是有序的，从左下⻆来看，向上数字递减，向右数字递增， 因此从左下⻆开始查找，当要查找
     * 数字⽐左下⻆数字⼤时。右移 要查找数字⽐左下⻆数字⼩时，上移。这样找的速度最快
     *
     * @param data
     * @return
     */
    public static boolean find(int[][] data, int tar) {
        int row = data.length;
        int colm = 0;
        while ((row >= 0) && (colm <= data[row].length - 1)) {
            if (data[row][colm] > tar) {
                row--;
            } else if (data[row][colm] < tar) {
                colm++;
            } else {
                return true;
            }
        }
        return false;
    }

    /**
     * 移除链表倒数地K个
     * 给定⼀个链表: 1>2>3>4>5, 和 n = 2.
     * 当删除了倒数第⼆个节点后链表变为  1>2>3>5
     * <p>
     * ⾸先我们将添加⼀个 哑结点 作为ᬀ助，该结点位于列表头部。哑结点⽤来简化某些极端情况，例如列
     * 表中只含有⼀个结点，或需要删除列表的头部。在第⼀次遍历中，我们找出列表的⻓度 L。然后设置⼀
     * 个指向哑结点的指针，并移动它遍历列表，直⾄它到达第 (L - n) 个结点那⾥。我们把第 (L - n)个
     * 结点的 next 指针重新链接⾄第 (L - n + 2)个结点，完成这个算法。
     *
     * @param listNode
     * @param k
     * @return
     */
    public static ListNode removeNthFromEnd(ListNode listNode, int k) {
        // 哑结点，哑结点⽤来简化某些极端情况，例如列表中只含有⼀个结点，或需要删除列表的头部
        ListNode dummy = new ListNode(0);
        // 哑结点指向头结点
        dummy.next = listNode;
        //链表的长度
        int length = 0;
        ListNode len = listNode;
        while (len != null) {
            length++;
            len = len.next;
        }
        length = length - k;
        ListNode tar = dummy;
        while (length > 0) {
            tar = tar.next;
            length--;
        }
        tar.next = tar.next.next;
        return dummy.next;
    }


    /**
     * 查找链表倒数地k个节点
     * <p>
     * 查找倒数k节点也就是查找顺序l-k+1节点
     *
     * @param listNode
     * @param k
     * @return k节点
     */
    public static ListNode findKthToTail(ListNode listNode, int k) {
        if (listNode == null || k < 0) {
            return null;
        }
        int count = 0;
        ListNode head = listNode, tail = listNode;
        // p指针先跑，并且记录节点数，当node1节点跑了k-1个节点后，node2节点
        // 当node1节点跑到最后时，node2节点所指的节点就是倒数第k个节点
        while (head != null) {
            head = head.next;
            count++;
            if (k < 1) {
                tail = tail.next;
            }
            k--;
        }
        if (count < k) {
            return null;
        }
        return tail;
    }


    /**
     * 链表翻转
     *
     * @param listNode
     * @return
     */
    public static ListNode reverseList(ListNode listNode) {
        ListNode head = null;
        ListNode tail = null;
        while (listNode != null) {
            head = listNode.next;
            listNode.next = tail;
            tail = listNode;
            listNode = head;
        }
        return tail;
    }

    /**
     * 两盒链表元素相加
     *
     * @param listNode1
     * @param listNode2
     * @return
     */
    public static ListNode sumList(ListNode listNode1, ListNode listNode2) {
        ListNode pre = new ListNode(0);
        ListNode cur = pre;
        int carry = 0;
        while (listNode1 != null || listNode2 != null) {
            int x = listNode1 == null ? 0 : listNode1.value;
            int y = listNode2 == null ? 0 : listNode2.value;
            int sum = x + y + carry;
            carry = sum / 10;
            sum = sum % 10;
            cur.next = new ListNode(sum);
            cur = cur.next;
            if (listNode1 != null) {
                listNode1 = listNode1.next;
            }
            if (listNode2 != null) {
                listNode2 = listNode2.next;
            }
        }
        if (carry >= 1) {
            cur.next = new ListNode(carry);
        }
        return pre.next;
    }

    /**
     * 删除排序链表中的重复元素
     *
     * @param listNode
     * @return
     */
    public static ListNode delReList(ListNode listNode) {
        if (listNode == null || listNode.next == null) {
            return listNode;
        }
        if (listNode.value == listNode.next.value) {

            listNode = listNode.next;
            return delReList(listNode);
        } else {
            listNode.next = delReList(listNode.next);
            return listNode;
        }
    }

    /**
     * 合并两个有序链表
     * <p>
     * 1. 假设我们有两个链表 A,B；
     * 2. A的头节点A1的值与B的头结点B1的值⽐᫾，假设A1⼩，则A13. A2再和B1⽐᫾，假设B1⼩,则，A1指向B1；
     * 4. A2再和B2⽐᫾ 就这样循环往复就⾏了，应该还算好理解。
     *
     * @param listNode1
     * @param listNode2
     * @return
     */
    public static ListNode mergeTwoListS(ListNode listNode1, ListNode listNode2) {
        if (listNode1 == null) {
            return listNode2;
        }
        if (listNode2 == null) {
            return listNode1;
        }
        if (listNode1.value < listNode2.value) {
            listNode1.next = mergeTwoListS(listNode1.next, listNode2);
            return listNode1;
        } else {
            listNode2.next = mergeTwoListS(listNode2.next, listNode1);
            return listNode2;
        }

    }


    public static class Solution {
        Stack<Integer> stack1 = new Stack<Integer>();
        Stack<Integer> stack2 = new Stack<Integer>();

        public void push(Integer data) {
            stack1.push(data);
        }

        //stack   先进后出   queue   先进先出
        public Integer pop() {
            if (stack1.empty() && stack2.empty()) {
                throw new NullPointerException("队列不存在数据!");
            }
            if (stack2.empty()) {
                while (!stack1.empty()) {
                    stack2.push(stack1.pop());
                }
            }
            return stack2.pop();
        }
    }

    public static class ListNode {
        private ListNode next;
        private int value;

        public ListNode(int value) {
            this.value = value;
        }

        public ListNode(ListNode next, int value) {
            this.next = next;
            this.value = value;
        }

        public ListNode getNext() {
            return next;
        }

        public void setNext(ListNode next) {
            this.next = next;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }
    }
}
