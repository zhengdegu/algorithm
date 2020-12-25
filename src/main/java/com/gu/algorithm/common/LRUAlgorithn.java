package com.gu.algorithm.common;

import java.util.HashMap;

/**
 * https://www.cnblogs.com/wyq178/p/9976815.html
 *
 * LRU是什么？按照英文的直接原义就是Least Recently Used,最近最久未使用法，它是按照一个非常著名的计算机操作系统基础理论得来的：
 * 最近使用的页面数据会在未来一段时期内仍然被使用,已经很久没有使用的页面很有可能在未来较长的一段时间内仍然不会被使用
 *
 * @author gu
 * @create 2020/12/23 上午11:29
 */
public class LRUAlgorithn {

    public static void main(String[] args) {
        LRU<Integer, String> lru = new LRU<Integer, String>(5);
        lru.put(1, "a");
        lru.put(2, "b");
        lru.put(3, "c");
        lru.put(4, "d");
        lru.put(5, "e");
        System.out.println("原始链表为：" + lru.toString()+"当前容量："+lru.size());

        lru.get(4);
        System.out.println("获取key为4的元素之后的链表：" + lru.toString()+"当前容量："+lru.size());

        lru.put(6, "f");
        System.out.println("新添加一个key为6之后的链表：" + lru.toString()+"当前容量："+lru.size());

        lru.remove(3);
        System.out.println("移除key=3的之后的链表:" + lru.toString()+"当前容量："+lru.size());
    }


    public static class LRU<K, V> {
        private int currentCapacity;
        private int capacity;
        private HashMap<K, Node> caches;
        private Node first;
        private Node tail;

        public LRU(int capacity) {
            this.currentCapacity = 0;
            this.capacity = capacity;
            caches = new HashMap<>(capacity);
        }

        public Object get(Object key) {
            Node node = caches.get(key);
            if (node == null) {
                return null;
            }
            moveToHead(node);
            return node.value;
        }

        public void put(K key, V value) {
            Node node = caches.get(key);
            if (node == null) {
                if (capacity <= caches.size()) {
                    caches.remove(tail.key);
                    removeLast();
                    currentCapacity--;
                }
                node = new Node(key, value);
                currentCapacity++;
            }
            node.value = value;
            moveToHead(node);
            caches.put(key, node);
        }

        /**
         * 根据key移除节点
         *
         * @param key
         * @return
         */
        public Object remove(Object key) {
            Node node = caches.get(key);
            if (node != null) {
                if (node.pre != null) {
                    node.pre.next = node.next;
                }
                if (node.next != null) {
                    node.next.pre = node.pre;
                }
                if (node == first) {
                    first = node.next;
                }
                if (node == tail) {
                    tail = tail.pre;
                }
            }
            currentCapacity--;
            return caches.remove(key);

        }

        public void clear() {
            first = null;
            tail = null;
            caches.clear();
        }

        public int size() {
            return this.currentCapacity;
        }

        private void removeLast() {
            if (tail != null) {
                tail = tail.pre;
                if (tail == null) {
                    first = null;
                } else {
                    tail.next = null;
                }
            }
        }

        private void moveToHead(Node node) {
            if (node == first) {
                return;
            }
            if (node.pre != null) {
                node.pre.next = node.next;
            }
            if (node.next != null) {
                node.next.pre = node.pre;
            }
            if (node == tail) {
                tail = tail.pre;
            }
            if (first == null || tail == null) {
                first = tail = node;
                return;
            }
            node.next = first;
            first.pre = node;
            first = node;
            first.pre = null;
        }

        @Override
        public String toString() {
            Node node = first;
            StringBuilder builder = new StringBuilder();
            while (node != null) {
                builder.append(String.format("%s:%s ", node.key, node.value));
                node = node.next;
            }
            return builder.toString();
        }
    }

    public static class Node {

        Object key;
        Object value;
        Node pre;
        Node next;

        public Node(Object key, Object value) {
            this.key = key;
            this.value = value;
        }
    }
}
