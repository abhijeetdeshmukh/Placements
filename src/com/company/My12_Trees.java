package com.company;

import java.util.Stack;

public class My12_Trees {


    //Definition for binary tree
    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) {
            val = x;
        }
    }

    /**
     * Given a binary tree, determine if it is a valid binary search tree (BST).
     * Assume a BST is defined as follows:
     * - The left subtree of a node contains only nodes with keys less than the node’s key.
     * - The right subtree of a node contains only nodes with keys greater than the node’s key.
     * - Both the left and right subtrees must also be binary search trees
     * @param treeNode .. root pointer to Tree
     * @return
     */
    public int isValidBST(TreeNode treeNode) {

        if (treeNode == null) return 1;
        Stack< TreeNode> stack = new Stack<>();     //stack of TreeNode
        TreeNode pre = null;
        while (treeNode != null || !stack.isEmpty()) {
            while (treeNode != null) {
                stack.push(treeNode);
                treeNode = treeNode.left;
            }
            treeNode = stack.pop();
            if(pre != null && treeNode.val <= pre.val) return 0;
            pre = treeNode;
            treeNode = treeNode.right;
        }
        return 1;
    }

    public int isValidBST_rec(TreeNode treeNode) {
        return isValidBSTUtil(treeNode, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }
    private int isValidBSTUtil(TreeNode a, int min, int max){
        if(a == null) return 1; //Base condition

        if(a.val > min && a.val < max){
            return isValidBSTUtil(a.left, min, a.val) & isValidBSTUtil(a.right, a.val, max);
        }else{
            return 0;
        }
    }
}
