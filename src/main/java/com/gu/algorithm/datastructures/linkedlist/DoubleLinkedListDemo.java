package com.gu.algorithm.datastructures.linkedlist;

/**
 * @author gu
 * @create 2021/1/19 下午2:51
 */
public class DoubleLinkedListDemo {


    public static void main(String[] args) {
        // 测试
        System.out.println("双向链表的测试");
// 先创建节点
        HeroNode2 hero1 = new HeroNode2(1, "宋江", "及时雨");
        HeroNode2 hero2 = new HeroNode2(2, "卢俊义", "玉麒麟");
        HeroNode2 hero3 = new HeroNode2(3, "吴用", "智多星");
        HeroNode2 hero4 = new HeroNode2(4, "林冲", "豹子头");
// 创建一个双向链表
        DoubleLinkedList doubleLinkedList = new DoubleLinkedList();
        doubleLinkedList.add(hero1);
        doubleLinkedList.add(hero2);
        doubleLinkedList.add(hero3);
        doubleLinkedList.add(hero4);
        doubleLinkedList.list();
// 修改
        HeroNode2 newHeroNode = new HeroNode2(4, "公孙胜", "入云龙");
        doubleLinkedList.update(newHeroNode);
        System.out.println("修改后的链表情况");
        doubleLinkedList.list();
// 删除
        doubleLinkedList.del(3);
        System.out.println("删除后的链表情况~~");
        doubleLinkedList.list();
    }
}





class DoubleLinkedList {
    private final HeroNode2 head = new HeroNode2(0, "", "");

    // 返回头节点
    public HeroNode2 getHead() {
        return head;
    }

    //添加一个节点到双向链表的最后.
    public void add(HeroNode2 heroNode2) {
        HeroNode2 pre = head;
        while (pre.next != null) {
            pre = pre.next;
        }
        pre.next = heroNode2;
        heroNode2.head = pre;
    }

    //// 修改一个节点的内容, 可以看到双向链表的节点内容修改和单向链表一样
    public void update(HeroNode2 heroNode2) {
        if (head.next == null) {
            throw new NullPointerException("链表为空");
        }
        HeroNode2 heroNode21 = head.next;
        while (heroNode21 != null) {
            if (heroNode21.no == heroNode2.no) {
                heroNode21.nickName = heroNode2.nickName;
                heroNode21.name = heroNode2.name;
                break;
            }
            heroNode21 = heroNode21.next;
        }
    }

    public void del(int no) {
        if (head.next == null) {
            throw new NullPointerException("链表为空");
        }
        HeroNode2 heroNode21 = head.next;
        while (heroNode21 != null) {
            if (heroNode21.no == no) {
                heroNode21.head.next = heroNode21.next;
                heroNode21.next.head = heroNode21.head;
                break;
            }
            heroNode21 = heroNode21.next;
        }
    }

    //循环遍历链表
    public void list() {
        if (head.next == null) {
            throw new NullPointerException("链表为空");
        }
        HeroNode2 next = head.next;
        while (next != null) {
            System.out.println(next);
            next = next.next;
        }
    }
}

class HeroNode2 {
    public int no;
    public String name;
    public String nickName;
    public HeroNode2 head;
    public HeroNode2 next;

    public HeroNode2(int no, String name, String nickName) {
        this.no = no;
        this.name = name;
        this.nickName = nickName;
    }

    //为了显示方法，我们重新 toString
    @Override
    public String toString() {
        return "HeroNode [no=" + no + ", name=" + name + ", nickname=" + nickName + "]";
    }
}