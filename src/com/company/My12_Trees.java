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
     * Given a BST node, return the node which has value just greater than the given node.

     Example:
     Given the tree :
             100
            /   \
            98    102
           /  \
        96    99
        \
        97
     Given 97, you should return the node corresponding to 98 as that's the value just greater than 97 in the tree.
     If there are no successor in the tree ( the value is the largest in the tree, return NULL).

     Using recursion is not allowed.

     Assume that the value is always present in the tree.
     * @param A
     * @param B
     * @return
     */
    public TreeNode getSuccessor(TreeNode A, int B) {
        if( A==null) return null;
        TreeNode travel = A;
        TreeNode nextMaxNode = null;
        while(travel.val != B){             //getting node for B
            if(B < travel.val){
                nextMaxNode = travel;       // case : when there is no right subtree - parent will be next successor
                travel = travel.left;
            } else{
                travel = travel.right;
            }
        }

        travel = travel.right;              //case : travel right to get the successor
        while(travel != null){
            nextMaxNode = travel;
            travel = travel.left;           // go left for next successor
        }

        return nextMaxNode;
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
                treeNode=treeNode.left;       //left subtree
            }
            treeNode=stack.pop();
            inorderList.add( treeNode.val);
            treeNode=treeNode.right;          //right subtree
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


    /**
     * Given an inorder traversal of a cartesian tree, construct the tree.
     * - Cartesian tree : is a heap ordered binary tree, where the root is greater than all the elements in the subtree.
     * - Note: You may assume that duplicates do not exist in the tree.

     Example :
     Input : [1 2 3]
     Return :
         3
        /
       2
      /
     1
     * @param arrayList
     * @return
     */
    public TreeNode buildTree(ArrayList<Integer> arrayList) {

        /*
        inorder traversal : (Left tree) root (Right tree)
        Note that the root is the max element in the whole array. Based on the info, can you figure out the position
        of the root in inorder traversal ? If so, can you separate out the elements which go in the left subtree and
        right subtree ?
        Once you have the inorder traversal for left subtree, you can recursively solve for left subtree.
        Same for right subtree.
         */

        return help(arrayList, 0, arrayList.size()-1);
    }
    public TreeNode help(ArrayList<Integer> arrayList, int start, int end) {
        if (start > end) return null;
        if (start == end) { return new TreeNode( arrayList.get( start)); }

        int maxIdx=start;
        for (int i = start + 1; i<=end; i++) {
            if ( arrayList.get(i) > arrayList.get( maxIdx)) maxIdx = i;
        }

        TreeNode root = new TreeNode( arrayList.get(maxIdx));
        root.left = help( arrayList, start, maxIdx-1);
        root.right = help( arrayList, maxIdx+1, end);
        return root;
    }

}
