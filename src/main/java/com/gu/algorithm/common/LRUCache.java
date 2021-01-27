package com.gu.algorithm.common;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 大致思路:
 * <p>
 * 1.构建双向链表节点ListNode，应包含key,value,prev,next这几个基本属性
 * <p>
 * 2.对于Cache对象来说，我们需要规定缓存的容量，所以在初始化时，设置容量大小，
 * 然后实例化双向链表的head,tail，并让head.next->tail tail.prev->head，这样我们的双向链表构建完成
 * <p>
 * 3.对于get操作,我们首先查阅hashmap，如果存在的话，直接将Node从当前位置移除，然后插入到链表的首部，在链表中实现删除直接让node的前驱节点指向后继节点，很方便.如果不存在，那么直接返回Null
 * <p>
 * 4.对于put操作，比较麻烦
 *
 * @author gu
 * @create 2021/1/27 上午10:22
 */
public class LRUCache<K, V> {

    private int capacity = 1024;

    private Map<K, ListNode<K, V>> table;
    private ListNode<K, V> head;
    private ListNode<K, V> tail;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.table = new ConcurrentHashMap<>(capacity);
    }

    public V get(K key) {
        ListNode<K, V> node = table.get(key);
        if (node == null) {
            return null;
        }
        moveHead(node);
        return node.value;
    }

    public void add(K key, V value) {
        ListNode<K, V> node = table.get(key);
        if (node == null) {
            if (table.size() > this.capacity) {
                removeLast();
                table.remove(tail.key);
            }
            node = new ListNode<>(key, value);
        }
        moveHead(node);
        table.put(key, node);
    }

    public V remove(K key) {
        ListNode<K, V> node = table.get(key);
        if (node != null) {
            if (node == head) {
                head = head.next;
            }
            if (node == tail) {
                tail = tail.pre;
            }
            if (node.pre != null) {
                node.pre.next = node.next;
            }
            if (node.next != null) {
                node.next.pre = node.pre;
            }
        }
        return node.value;
    }

    public void removeLast() {
        if (tail != null) {
            tail = tail.pre;
            if (tail == null) {
                head = null;
            } else {
                tail.next = null;
            }
        }
    }

    public void moveHead(ListNode<K, V> node) {
        if (node == head) {
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
        node.next = head;
        head.pre = node;
        head = node;
        head.pre = null;
    }
}

class ListNode<K, V> {

    public K key;
    public V value;
    public ListNode<K, V> pre;
    public ListNode<K, V> next;

    public ListNode(K key, V value) {
        this.key = key;
        this.value = value;
    }


}