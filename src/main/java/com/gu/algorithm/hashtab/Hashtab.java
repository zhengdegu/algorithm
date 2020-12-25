package com.gu.algorithm.hashtab;

/**
 * 有一个公司,当有新的员工来报道时,要求将该员工的信息加入(id,性别,年龄,名字,住址..),当输入该员工的 id 时, 要求查找到该员工的 所有信息.
 * 要求:
 * 1) 不使用数据库,速度越快越好=>哈希表(散列)
 * 2) 添加时，保证按照 id 从低到高插入 [课后思考：如果 id 不是从低到高插入，但要求各条链表仍是从低到高，怎么解决?]
 * 3) 使用链表来实现哈希表, 该链表不带表头[即: 链表的第一个结点就存放雇员信息]
 * 4) 思路分析并画出示意图
 *
 * @author gu
 * @create 2020/12/23 下午5:37
 */
public class Hashtab {

    public static class HashTab {
        private EmpLinkedList[] empLinkedListArray;
        private int size; //表示有多少条链表

        //构造器
        public HashTab(int size) {
            this.size = size;
            //初始化 empLinkedListArray
            empLinkedListArray = new EmpLinkedList[size];
            //？留一个坑, 这时不要分别初始化每个链表
//            for (int i = 0; i < size; i++) {
//                empLinkedListArray[i] = new EmpLinkedList();
//            }
        }

        //添加雇员
        public void add(Emp emp) {
            //根据员工的 id ,得到该员工应当添加到哪条链表
            int empLinkedListNO = hashFun(emp.id);
            //将 emp 添加到对应的链表中
            empLinkedListArray[empLinkedListNO].add(emp);
        }

        public Emp findEmpById(int id) {
            int empLinkedListNO = hashFun(id);
            EmpLinkedList empLinkedList = empLinkedListArray[empLinkedListNO];
            if (empLinkedList == null) {
                return null;
            }
            Emp emp = empLinkedList.findEmpById(id);
            if (emp == null) {
                return null;
            }
            return emp;
        }

        public String list() {
            if (empLinkedListArray.length == 0) {
                return "没有数据";
            }
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < empLinkedListArray.length; i++) {
                builder.append(String.format("数组下标：%s,全部雇员：%s", i, empLinkedListArray[i].list()));
            }
            return builder.toString();

        }

        //编写散列函数, 使用一个简单取模法
        public int hashFun(int id) {
            return id % size;
        }
    }

    public static class EmpLinkedList {
        private Emp head; //默认 null

        public Emp findEmpById(int id) {
            if (head == null) {
                return null;
            }
            //辅助指针
            Emp curEmp = head;
            while (curEmp != null) {
                if (curEmp.id == id) {
                    break;
                }
                curEmp = curEmp.next;
            }
            return curEmp;
        }

        public void add(Emp emp) {
            if (head == null) {
                head = emp;
                return;
            }
            Emp currentEmp = head;
            while (currentEmp.next != null) {
                currentEmp = currentEmp.next;
            }
            currentEmp.next = emp;
        }

        public String list() {
            if (head == null) {
                return "此链表无数据";
            }
            Emp currentEmp = head;
            StringBuilder builder = new StringBuilder();
            while (currentEmp != null) {
                builder.append(String.format("雇员id:%s 姓名：%s ", currentEmp.id, currentEmp.name));
                currentEmp = currentEmp.next;
            }
            return builder.toString();
        }
    }

    public static class Emp {
        int id;
        String name;
        Emp pre;
        Emp next;

        public Emp(int id, String name) {
            this.id = id;
            this.name = name;
        }
    }
}
