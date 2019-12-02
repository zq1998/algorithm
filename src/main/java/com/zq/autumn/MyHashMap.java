package com.zq.autumn;

/**
 * <p>Description: </p>
 * <p>Copyright: Copyright(c) 2019</p>
 * <p>Email: 1012872209@qq.com</p>
 *
 * @author apple
 * @date 2019-08-05:21:17
 */
public class MyHashMap {

    private static final int DEFAULT_CAPACITY = 16;
    private static final float DEFAULT_LOAD_FACTOR = 0.75f;
    private int size;
    private int capacity;
    private float factor;
    private int thresholad;
    private Node[] table;

    private class Node {

        private int key;
        private int value;
        private Node next;

        public Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    public MyHashMap(int capacity, float factor) {
        if (capacity <= 0) {
            capacity = DEFAULT_CAPACITY;
        }
        if (factor < 0) {
            factor = DEFAULT_LOAD_FACTOR;
        }
        int mini = 1;
        capacity -= 1;
        while (capacity != 0) {
            capacity >>= 1;
            mini <<= 1;
        }
        capacity = mini;
        this.factor = factor;
        this.capacity = capacity;
        this.thresholad = (int) factor * capacity;
        this.table = new Node[capacity];

    }

    public MyHashMap() {
        this(DEFAULT_CAPACITY, DEFAULT_LOAD_FACTOR);
    }

    private int hash(Object key) {
        if (key == null) {
            return 0;
        }
        int hashcode = key.hashCode();
        //高低16异或
        return hashcode ^ (hashcode >>> 16);
    }

    public void put(int key, int value) {
        ensureCapacity();
        int pos = getPos(key, table);
        if(table[pos]==null){
            Node node=new Node(key,value);
            table[pos]=node;
            size++;
        }else{
            Node head=table[pos];
            Node tmp=head;
            while (tmp!=null){
                if(key==tmp.key){
                    tmp.value=value;
                    break;
                }
                tmp=tmp.next;
            }
            if(tmp==null){
                Node node=new Node(key,value);
                node.next=head;
                table[pos]=node;
                size++;
            }
        }
    }

    public int get(int key){
        int pos=getPos(key,table);
        if(table[pos]==null){
            return -1;
        }else{
            Node head=table[pos];
            while (head!=null){
                if(key==head.key){
                    return head.value;
                }
                head=head.next;
            }
            return -1;
        }
    }

    public void remove(int key){
        int pos=getPos(key,table);
        if(table[pos]!=null){
            Node head=table[pos];
            Node pre=null;
            while (head!=null){
                if(key==head.key){
                    break;
                }
                pre=head;
                head=head.next;
            }

            if(head!=null){
                Node next=head.next;
                head.next=null;
                if(pre==null){
                    table[pos]=next;
                }else{
                    pre.next=next;
                }
                size--;
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (Node node : table) {
            Node tmp = node;
            sb.append("(");
            while (tmp != null) {
                sb.append("{").append(tmp.key).append(":").append(tmp.value).append("},");
                tmp = tmp.next;
            }
            sb.append(")");
        }
        return sb.toString();
    }

    public int size(){
        return size;
    }

    private void ensureCapacity() {
        if (size > capacity) {
            capacity <<= 1;
            thresholad = (int) (capacity * factor);
            Node[] newTable = new Node[capacity];
            for (Node node : table) {
                Node tmp = node;
                while (tmp != null) {
                    //死锁
                    int pos = getPos(tmp.key, newTable);
                    Node next = tmp.next;
                    tmp.next = newTable[pos];
                    newTable[pos] = tmp;
                    tmp = next;
                }
            }
            table = newTable;
        }
    }

    private int getPos(Object key, Node[] table) {
        return hash(key) & (table.length - 1);
    }

}
