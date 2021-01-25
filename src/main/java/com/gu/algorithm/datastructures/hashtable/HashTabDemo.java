package com.gu.algorithm.datastructures.hashtable;

import java.util.Scanner;

/**
 * @author gu
 * @create 2021/1/25 上午9:32
 */
public class HashTabDemo {
    public static void main(String[] args) {
        //创建
        HashTable hashTab = new HashTable(7);
        //写一个简单的菜单
        String key = "";
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("add: 添加雇员");
            System.out.println("list: 显示雇员");
            System.out.println("find: 查找雇员");
            System.out.println("exit: 退出系统");
            key = scanner.next();
            switch (key) {
                case "add":
                    System.out.println("输入 id");
                    int id = scanner.nextInt();
                    System.out.println("输入名字");
                    String name = scanner.next();
                    //创建 雇员
                    Emp emp = new Emp(id, name);
                    hashTab.add(emp);
                    break;
                case "list":
                    hashTab.list();
                    break;
                case "find":
                    System.out.println("请输入要查找的 id");
                    id = scanner.nextInt();
                    hashTab.findEmpByiD(id);
                    break;
                case "exit":
                    scanner.close();
                    System.exit(0);
                default:
                    break;
            }
        }
    }
}

//创建HashTable管理多条链表、
class HashTable {

    private EmpLinkList[] empLinkLists;
    private int size;

    public HashTable(int size) {
        this.size = size;
        empLinkLists = new EmpLinkList[size];
    }

    //根据输入的I查找雇员信息
    public void findEmpByiD(int no) {
        int empLinkListNo = hashFun(no);
        Emp empById = empLinkLists[empLinkListNo].findEmpById(no);
        if (empById == null) {
            System.out.println("在哈希表中，没有找到该雇员~");
        } else {
            System.out.printf("在第%d 条链表中找到 雇员 id = %d\n", (empLinkListNo + 1), no);
        }

    }

    //遍历所有的链表，遍历hashTable
    public void list() {
        for (int i = 0; i < size; i++) {
            empLinkLists[i].list(i);
        }
    }

    //添加雇员
    public void add(Emp emp) {

        int empLinkListNo = hashFun(emp.id);
        empLinkLists[empLinkListNo].add(emp);
    }

    public int hashFun(int id) {
        return id % size;
    }
}

//创建EmpLinkLIst，表示链表
class EmpLinkList {
    private Emp head;

    //根据id查找雇员信息

    public Emp findEmpById(int id) {
        if (head == null) {
            System.out.println("链表为空");
            return null;
        }
        Emp curEmp = head;
        while (true) {
            if (curEmp.id == id) {
                break;
            }
            if (curEmp.next == null) {
                curEmp = null;
                break;
            }
            curEmp = curEmp.next;
        }
        return curEmp;
    }

    //遍历链表中的雇员信息
    public void list(int no) {
        if (head == null) {
            System.out.println("第 " + (no + 1) + "链表为空");
            return;
        }
        System.out.println("(第 " + (no + 1) + " 链表的信息为");
        Emp curEmp = head;
        while (true) {
            System.out.printf(" => id=%d name=%s\t", curEmp.id, curEmp.name);
            if (curEmp.next == null) {
                break;
            }
            curEmp = curEmp.next;
        }
        System.out.println();
    }

    //添加一个雇员
    public void add(Emp emp) {
        if (head == null) {
            head = emp;
            return;
        }
        Emp curEmp = head;
        while (true) {
            if (curEmp.next == null) {
                break;
            }
            curEmp = curEmp.next;
        }
        curEmp.next = emp;
    }
}

//一个雇员
class Emp {
    public int id;
    public String name;
    public Emp next;

    public Emp(int id, String name) {
        this.id = id;
        this.name = name;
    }
}