package com.zq.autumn;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>Description: </p>
 * <p>Copyright: Copyright(c) 2019</p>
 * <p>Email: 1012872209@qq.com</p>
 *
 * @author apple
 * @date 2019-08-05:21:00
 */
public class LruCache {

    class Node{
        int key;
        int value;
        Node pre;
        Node next;

    }

    private void addNode(Node node){
        node.pre=head;
        node.next=head.next;
        head.next.pre=node;
        head.next=node;
    }

    private void removeNode(Node node){
        node.pre.next=node.next;
        node.next.pre=node.pre;
    }

    private void moveToHead(Node node){
        removeNode(node);
        addNode(node);
    }

    private Node popTail(){
        Node node=tail.pre;
        removeNode(node);
        return node;
    }


    private Map<Integer,Node> cache=new HashMap<>();
    private Node head,tail;
    private int size,capacity;

    public LruCache(int capacity){
        this.capacity=capacity;
        this.size=0;
        head=new Node();
        tail=new Node();
        head.next=tail;
        tail.pre=head;
    }

    public int get(int key){
        if(cache.containsKey(key)){
            Node node = cache.get(key);
            moveToHead(node);
            return node.value;
        }else {
            return -1;
        }
    }

    public void put(int key,int value){
        if(cache.containsKey(key)){
            Node node = cache.get(key);
            node.value=value;
            moveToHead(node);
        }else{
            Node node=new Node();
            node.key=key;
            node.value=value;
            cache.put(key,node);
            addNode(node);
            size++;
            if(size>capacity){
                Node node1 = popTail();
                cache.remove(node1.key);
                size--;
            }
        }
    }


}
