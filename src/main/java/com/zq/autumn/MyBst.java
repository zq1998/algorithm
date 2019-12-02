package com.zq.autumn;

/**
 * <p>Description: 二叉搜索树</p>
 * <p>Copyright: Copyright(c) 2019</p>
 * <p>Email: 1012872209@qq.com</p>
 *
 * @author zq
 * @date 2019-08-16:10:13
 */
public class MyBst {

    TreeNode root;

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int val) {
            this.val = val;
        }
    }

    public TreeNode insert(TreeNode root, int val) {
        if (root == null) {
            root = new TreeNode(val);
            return root;
        }
        if (val > root.val) {
            insert(root.right, val);
        } else if (val < root.val) {
            insert(root.left, val);
        }
        return root;
    }

    private TreeNode delete(TreeNode root, int val) {
        if (root == null) {
            return null;
        }
        if (root.val > val) {
            root.right = delete(root.right, val);
        } else if (root.val < val) {
            root.right = delete(root.left, val);
        } else {
            if (root.left == null) {
                return root.right;
            } else if (root.right == null) {
                return root.left;
            }
            TreeNode min = findMin(root.right);
            root.val = min.val;
            root.right = delete(root.right, min.val);
        }
        return root;
    }

    private TreeNode findMin(TreeNode node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    public TreeNode search(TreeNode root, int val) {
        if (root == null) {
            return null;
        }
        while (true) {
            if (root.val == val) {
                return root;
            } else if (root.val > val) {
                root = root.left;
            } else {
                root = root.right;
            }
            if (root == null) {
                return null;
            }
        }
    }
}
