package com.gu.algorithm.datastructures.linkedlist;

import java.util.Stack;

/**
 * @author gu
 * @create 2021/1/19 上午10:35
 */
public class SingleLinkedListDemo {
    public static void main(String[] args) {
        HeroNode heroNode = new HeroNode(1, "1", "1", new HeroNode(2, "2", "2", new HeroNode(3, "3", "3")));

        //查找链表中的倒数第K个节点
        System.out.printf("查找链表中的倒数第K个节点:%s\n", findLastLinkListNode(heroNode, 2));
        //链表翻转
        System.out.printf("单链表翻转,翻转前的链表：%s,翻转后的链表：%s", heroNode, reversetList(heroNode));

        //单链表逆序打印
        System.out.println();
        printNode(new HeroNode(1, "1", "1", new HeroNode(2, "2", "2", new HeroNode(3, "3", "3"))));

    }

    //单链表翻转
    public static HeroNode reversetList(HeroNode heroNode) {
        if (heroNode == null) {
            return null;
        }
        HeroNode pre = null, tail = null;
        while (heroNode != null) {
            pre = heroNode.next;
            heroNode.next = tail;
            tail = heroNode;
            heroNode = pre;
        }
        return tail;
    }

    //查找单链表中的倒数第 k 个结点
    public static HeroNode findLastLinkListNode(HeroNode heroNode, int k) {
        if (heroNode == null) {
            throw new NullPointerException("链表为空");
        }
        int count = 0;
        HeroNode pre = heroNode, tail = heroNode;
        while (pre != null) {
            count++;
            pre = pre.next;
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

    //从尾到头打印单链表
    public static void printNode(HeroNode heroNode) {
        if (heroNode == null) {
            return;
        }
        Stack<HeroNode> stack = new Stack<>();
        HeroNode  cru=heroNode;
        while (cru != null) {
            stack.push(cru);
            cru = cru.next;
        }
        System.out.printf("单链表逆序打印：");
        while (!stack.empty()){
            System.out.printf("%s\t",stack.pop());
        }
    }
}


class SingleLinkedList {
    //初始化一个头结点
    private HeroNode head = new HeroNode(0, "", "");

    //添加一个节点
    public void add(HeroNode heroNode) {
        HeroNode temp = head;
        while (true) {
            if (temp.next == null) {
                break;
            }
            temp = temp.next;
        }
        temp.next = heroNode;
    }

    //第二种方式在添加英雄时，根据排名将英雄插入到指定位置
    public void addOrder(HeroNode heroNode) {
        HeroNode temp = head;
        //标志添加的编号是否存在
        boolean flag = false;
        while (true) {
            if (temp.next == null) {
                break;
            }
            if (temp.next.no > heroNode.no) {
                //位置找到在后面插入
                break;
            } else if (temp.next.no == heroNode.no) {
                //说明编号存在
                flag = true;
                break;
            }
            temp = temp.next;
        }
        //判断 flag 的值
        if (flag) { //不能添加，说明编号存在
            System.out.printf("准备插入的英雄的编号 %d 已经存在了, 不能加入\n", heroNode.no);
        } else {
//插入到链表中, temp 的后面
            heroNode.next = temp.next;
            temp.next = heroNode;
        }

    }

    public void update(HeroNode heroNode) {
        if (head.next == null) {
            System.out.println("链表为空~");
            return;
        }
        HeroNode temp = head.next;
        boolean flag = false;
        while (true) {
            if (temp == null) {
                break;
            }
            if (temp.no == heroNode.no) {
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag) {
            temp.name = heroNode.name;
            temp.nickName = heroNode.name;
        } else {
            System.out.printf("没有找到 编号 %d 的节点，不能修改\n", heroNode.no);
        }
    }

    public void del(int no) {
        HeroNode temp = head;
        boolean flag = false;
        if (temp.next == null) {
            System.out.println("链表为空~");
            return;
        }
        while (true) {
            if (temp.next == null) {
                break;
            }
            if (temp.next.no == no) {
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag) {
            temp.next = temp.next.next;
        } else {
            System.out.printf("没有找到 编号 %d 的节点，不能修改\n", no);
        }
    }

    public void list() {
        if (head.next == null) {
            System.out.println("链表为空~");
            return;
        }
        //因为头节点，不能动，因此我们需要一个辅助变量来遍历
        HeroNode temp = head.next;
        while (true) {
//判断是否到链表最后
            if (temp == null) {
                break;
            }
//输出节点的信息
            System.out.println(temp);
//将 temp 后移， 一定小心
            temp = temp.next;
        }
    }
}

class HeroNode {
    public int no;
    public String name;
    public String nickName;
    public HeroNode next;

    public HeroNode(int no, String name, String nickName) {
        this.no = no;
        this.name = name;
        this.nickName = nickName;
    }

    public HeroNode(int no, String name, String nickName, HeroNode next) {
        this.no = no;
        this.name = name;
        this.nickName = nickName;
        this.next = next;
    }

    //为了显示方法，我们重新 toString
    @Override
    public String toString() {
        return "HeroNode [no=" + no + ", name=" + name + ", nickname=" + nickName + "]";
    }
}