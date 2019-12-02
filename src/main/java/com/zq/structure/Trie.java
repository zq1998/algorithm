package com.zq.structure;

/**
 * <p>Description: </p>
 * <p>Copyright: Copyright(c) 2019</p>
 * <p>Email: 1012872209@qq.com</p>
 *
 * @author zq
 * @date 2019-10-11:20:14
 */
public class Trie {

    public Trie() {

    }

    private class Node {

        Node[] childs = new Node[26];
        boolean isLeaf;

    }

    private Node root = new Node();

    public void insert(String word) {
        insert(word, root);
    }

    private void insert(String word, Node node) {
        if (node == null) {
            return;
        }
        if (word.length() == 0) {
            node.isLeaf = true;
            return;

        }
        int index = indexForChar(word.charAt(0));
        if (node.childs[index] == null) {
            node.childs[index] = new Node();
        }
        insert(word.substring(1), node.childs[index]);
    }

    private boolean search(String word) {
        return search(word, root);
    }

    private boolean search(String word, Node node) {
        if (node == null) {
            return false;
        }
        if (word.length() == 0) {
            return node.isLeaf;
        }
        int index = indexForChar(word.charAt(0));
        return search(word.substring(1), node.childs[index]);
    }

    private boolean startsWith(String prefix) {
        return startwith(prefix, root);
    }

    private boolean startwith(String prefix, Node node) {
        if (node == null) {
            return false;
        }
        if (prefix.length() == 0) {
            return true;
        }
        int index = indexForChar(prefix.charAt(0));
        return startwith(prefix.substring(1), node.childs[index]);
    }

    private int indexForChar(char c) {
        return c - 'a';
    }
}
