package example;

import java.util.HashMap;

public class LRUCache {
    public static class ListNode{
        public ListNode pre;
        public ListNode next;
        public int key;
        public int value;
    }

    int size;
    ListNode head;
    ListNode tail;
    HashMap<Integer, ListNode> map = new HashMap<>();

    public LRUCache(int capacity) {
        size = capacity;
        head = new ListNode();
        tail = new ListNode();
        head.next = tail;
        tail.pre = head;
    }

    public int get(int key) {
        if (map.containsKey(key)){
            ListNode node = map.get(key);
            int res = node.value;
            removeNode(node);
            tail(node);
            return res;
        }else {
            return -1;
        }
    }

    public void put(int key, int value) {
        if (map.containsKey(key)){
            ListNode node = map.get(key);
            node.value = value;
            removeNode(node);
            tail(node);
        }else {
            if (size == 0){
                map.remove(head.next.key);
                size++;
                removeNode(head.next);
            }
            ListNode node = new ListNode();
            node.key = key;
            node.value = value;
            tail(node);
            map.put(key, node);
            size--;
        }
    }

    public void removeNode(ListNode node){
        node.pre.next = node.next;
        node.next.pre = node.pre;
    }

    public void tail(ListNode node){
        node.pre = tail.pre;
        node.next = tail;
        node.pre.next = node;
        tail.pre = node;
    }
}
