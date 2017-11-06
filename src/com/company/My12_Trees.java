package com.company;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class My12_Trees {

    ///////////////////////////////////////////////////////////////////////////////////
    // 00. Definition of Binary tree

    //    isValidBST
    //    getSuccessor
    //    inorderTraversal
    //




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
//above cose is also efficient
    //always use this
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


    public TreeNode getSuccessor(TreeNode A, int B) {
        if( A==null) return null;
        TreeNode travel = A;
        TreeNode ret = null;
        while(travel.val != B){
            if(B < travel.val){
                ret = travel;
                travel = travel.left;
            } else{
                travel = travel.right;
            }
        }

        travel = travel.right;
        while(travel != null){
            ret = travel;
            travel = travel.left;
        }

        return ret;
    }


    /**
     * Given a binary tree, return the inorder traversal of its nodes’ values.
     * @param treeNode
     * @return
     */
    public ArrayList<Integer> inorderTraversal(TreeNode treeNode) {
        ArrayList< Integer> inorderList = new ArrayList< Integer>();
        Stack< TreeNode> stack = new Stack< TreeNode>();
        while( treeNode!=null || !stack.isEmpty()){
            while( treeNode!=null){
                stack.push( treeNode);
                treeNode=treeNode.left;
            }
            treeNode=stack.pop();
            inorderList.add( treeNode.val);
            treeNode=treeNode.right;
//            if(treeNode == null && !stack.isEmpty()) {    //is this if condition necessary
//                treeNode = stack.pop();
//                inorderList.add(treeNode.val);
//                treeNode = treeNode.right;
//            }
        }
        return inorderList;
    }


    /**
     * Given a binary tree, determine if it is height-balanced.
     * Height-balanced binary tree : is defined as a binary tree in which the depth of the two subtrees of every node never differ by more than 1.
     * Return 0 / 1 ( 0 for false, 1 for true ) for this problem
     * @param root
     * @return
     */
    public int isBalanced(TreeNode root) {
        if (root == null) return 1;
        if ( getHeight(root)==-1) return 0;
        return 1;
    }
    public int getHeight(TreeNode root) {
        if (root == null) return 0;
        int left = getHeight(root.left);
        int right = getHeight(root.right);
        if (left==-1 || right==-1) return -1;
        if (Math.abs(left - right) > 1) return -1;
        return Math.max(left, right) + 1;

    }


    /**
     * Given a binary tree, invert the binary tree and return it.
     * @param root
     * @return
     */
    public TreeNode invertTree_recursive(TreeNode root) {
        if (root == null) { return null; }
        TreeNode right = invertTree_recursive( root.right);  //get pointers
        TreeNode left = invertTree_recursive( root.left);
        root.left = right;                         //swap pointers
        root.right = left;
        return root;
    }
//recursive approach is most suited
    public TreeNode invertTree_iterative(TreeNode root) {
        if (root == null) return null;
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode current = queue.poll();
            TreeNode temp = current.left;
            current.left = current.right;
            current.right = temp;
            if (current.left != null) queue.add(current.left);
            if (current.right != null) queue.add(current.right);
        }
        return root;
    }


    public int kthsmallest_iterative(TreeNode treeNode, int k) {
        Stack< TreeNode> stack = new Stack< TreeNode>();
        while( treeNode!=null || !stack.isEmpty()){
            while( treeNode!=null){
                stack.push( treeNode);
                treeNode=treeNode.left;
            }
            treeNode=stack.pop();
            k--;
            if (0==k) return treeNode.val;
            treeNode=treeNode.right;
        }
        return -1;
    }

    /**
     * Given a binary tree, flatten it to a linked list in-place.
     * Given

     1
     / \
     2   5
     / \   \
     3   4   6

     The flattened tree should look like:

     1
     \
     2
     \
     3
     \
     4
     \
     5
     \
     6

     * @param a
     * @return
     */
    public TreeNode flatten(TreeNode a) {
        if(a == null) return null;
        TreeNode result = new TreeNode(0);
        TreeNode node = result;
        result.right = node;

        Stack<TreeNode> stack =  new Stack<TreeNode>();
        stack.push(a);
        while(!stack.isEmpty()){
            TreeNode popped = stack.pop();
            node.right = popped;
            node.left = null;
            node = node.right;

            if(popped.right != null)    stack.push(popped.right);
            if(popped.left != null)     stack.push(popped.left);
        }
        return result.right;
    }

    /**
     * Given two binary trees, write a function to check if they are equal or not.
     * Two binary trees are considered equal if they are structurally identical and the nodes have the same value.
     * Return 0 / 1 ( 0 for false, 1 for true ) for this problem
     * Example :
     Input :
     1       1
     / \     / \
     2   3   2   3
     Output :
     1 or True

     * @param a
     * @param b
     * @return
     */
    public int isSameTree(TreeNode a, TreeNode b) {
        if( a==null && b==null) return 1;
        else if( a==null || b==null) return 0;
        if( a.val==b.val) return isSameTree(a.left,b.left) & isSameTree(a.right,b.right);
        return 0;
    }


}
