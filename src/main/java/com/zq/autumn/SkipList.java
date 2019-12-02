package com.zq.autumn;

import java.util.Random;

/**
 * <p>Description: </p>
 * <p>Copyright: Copyright(c) 2019</p>
 * <p>Email: 1012872209@qq.com</p>
 *
 * @author zq
 * @date 2019-10-08:14:24
 */
public class SkipList {

    private static final int MAX_LEVEL = 16;
    private int levelCount = 1;
    private Node head = new Node();
    private Random random = new Random();

    public Node find(int value) {
        Node p = head;
        for (int i = levelCount - 1; i >= 0; i--) {
            while (p.next[i] != null && p.next[i].data < value) {
                p = p.next[i];
            }
        }
        if (p.next[0] != null && p.next[0].data == value) {
            return p.next[0];
        } else {
            return null;
        }
    }

    public void insert(int value) {
        int level = randomLevel();
        Node newNode = new Node();
        newNode.data = value;
        newNode.maxLevel = level;
        Node update[] = new Node[level];
        for (int i = 0; i < level; i++) {
            update[i] = head;
        }
        Node p = head;
        for (int i = level - 1; i >= 0; i--) {
            while (p.next[i] != null && p.next[i].data < value) {
                p = p.next[i];
            }
            update[i] = p;
        }
        for (int i = 0; i < level; i++) {
            newNode.next[i] = update[i].next[i];
            update[i].next[i] = newNode;
        }
        if (levelCount < level) {
            levelCount = level;
        }

    }

    public void delete(int value) {
        Node[] update = new Node[levelCount];
        Node p = head;
        for (int i = levelCount - 1; i >= 0; i--) {
            while (p.next[i] != null && p.next[i].data < value) {
                p = p.next[i];
            }
            update[i] = p;
        }
        if (p.next[0] != null && p.next[0].data == value) {
            for (int i = levelCount - 1; i >= 0; i--) {
                if (update[i].next[i] != null && update[i].next[i].data == value) {
                    update[i].next[i] = update[i].next[i].next[i];
                }
            }
        }
    }

    // 随机函数
    private int randomLevel() {
        int level = 1;
        for (int i = 1; i < MAX_LEVEL; ++i) {
            if (random.nextInt() % 2 == 1) {
                level++;
            }
        }

        return level;
    }


    public class Node {
        private int data = -1;
        private Node[] next = new Node[MAX_LEVEL];
        private int maxLevel = 0;

        @Override
        public String toString() {
            StringBuilder builder = new StringBuilder();
            builder.append("{data:");
            builder.append(data);
            builder.append("; leves: ");
            builder.append(maxLevel);
            builder.append(" }");
            return builder.toString();
        }
    }

    public void display() {
        Node p = head;
        while (p.next[0] != null) {
            System.out.println(p.next[0] + " ");
            p = p.next[0];
        }
        System.out.println();
    }
}
