package com.gu.algorithm.common;

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

    }


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
