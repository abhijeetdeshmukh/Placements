package com.company;

import java.util.HashMap;

public class My07_LinkedList {


//    LinkedList
    //1. ListNode - Definition of singly Linked list
    //2. getSizeOfLinkedList
    //3. reverseList
    //4. removeElements
    //5. removeNthFromEnd
    //6. addTwoNumbers
    //7. mergeTwoLists
    //8. getIntersectionNode
    //9. reverseBetween
    //10. partition
    //11. detectCycle

    /*** Definition for singly-linked list.*/
    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    private int getLengthOfLinkedList(ListNode head){
        int length;
        for (length = 0; head != null; length++){
            head = head.next;
        }
        return length ;
    }

    /***
     * Reverse a singly linked list.
     * @param head
     * @return
     */
    public ListNode reverseList(ListNode head) {
        ListNode prevNode = null;
        ListNode currNode = head;
        ListNode nextNode = null;
        while (currNode != null) {
            nextNode = currNode.next;//0.make arrangement for next iteration
            currNode.next = prevNode;   //reverse direction
            prevNode = currNode;        //1.make arrangement for next iteration
            currNode = nextNode;        //2.make arrangement for next iteration
        }
        return prevNode;
    }

    public ListNode reverseList_recursive(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode node = reverseList_recursive( head.next);
        head.next.next = head;
        head.next = null;
        return node;
    }

    /***
     * Remove all elements from a linked list of integers that have value val.
     * @param head
     * @param val
     * @return
     */
    public ListNode removeElements(ListNode head, int val) {
        ListNode previousNode = null ;
        if (head == null) return head;
        while (head != null && val == head.val) head = head.next;         //To handle occurrence at head node
        ListNode currentNode = head ;
        while (currentNode != null){
            if (currentNode.val == val){
                previousNode.next = currentNode.next ;
                currentNode = currentNode.next ;
            } else {
                previousNode = currentNode;
                currentNode = currentNode.next ;
            }
        }
        return head ;
    }

    /**
     * Given a linked list, remove the nth node from the end of list and return its head.
     * @param head
     * @param n
     * @return
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        int length;
        ListNode first = head;
        for (length=0; first != null; length++){
            first = first.next;
        }

        first = dummy;
        for(length -= n; length > 0; length--){
            first = first.next;
        }

        first.next = first.next.next;
        return dummy.next;
    }

    /**
     * You are given two non-empty linked lists representing two non-negative
     * integers. The digits are stored in reverse order and each of their
     * nodes contain a single digit. Add the two numbers and return it as a
     * linked list.
     *
     * @param l1 - first Linked list
     * @param l2 - second linked list
     * @return - head of sum linked list.
     *
     *  https://leetcode.com/articles/add-two-numbers/
     */
    public ListNode addTwoNumbers_1(ListNode l1, ListNode l2) {
        ListNode dummyHead = new ListNode(0);
        ListNode p = l1, q = l2, curr = dummyHead;
        int carry = 0;
        while (p != null || q != null) {
            int x = (p != null) ? p.val : 0;
            int y = (q != null) ? q.val : 0;
            int sum = carry + x + y;
            carry = sum / 10;
            curr.next = new ListNode(sum % 10);
            curr = curr.next;
            if (p != null) p = p.next;
            if (q != null) q = q.next;
        }
        if (carry > 0) {
            curr.next = new ListNode(carry);
        }
        return dummyHead.next;
    }

    public ListNode addTwoNumbers(ListNode a, ListNode b) {

        ListNode sumHead = new ListNode(0);
        ListNode p=a, q=b, sum = sumHead;
        int carry=0;

        if ( p==null && q==null ) return sumHead;
        if ( p==null ) return q;
        if ( q==null ) return p;

        while( p!=null && q!=null){
            int s = carry + p.val + q.val;
            carry = s/10;
            sum.next = new ListNode( s%10);
            p=p.next;
            q=q.next;
            sum=sum.next;
        }
        while( p!=null ){
            int s = carry + p.val;
            carry = s/10;
            sum.next = new ListNode( s%10);
            p=p.next;
            sum=sum.next;
        }
        while( q!=null){
            int s = carry + q.val;
            carry = s/10;
            sum.next = new ListNode( s%10);
            q=q.next;
            sum=sum.next;
        }
        if ( carry!=0) sum.next = new ListNode( carry);     //important
        return sumHead.next;
    }

    public ListNode addTwoNumbers_recursion(ListNode l1, ListNode l2) {
        return calculate(l1, l2, 0);
    }
    ListNode calculate(ListNode l1, ListNode l2, int carry) {
        if(l1==null && l2==null && carry==0) return null;
        if(l1==null && l2==null && carry!=0) return new ListNode(1);
        int temp = (l1==null? 0: l1.val) + (l2==null? 0: l2.val) + carry;
        ListNode result = new ListNode(temp%10);
        result.next = calculate( l1 == null? null: l1.next, l2 == null? null: l2.next, temp/10);
        return result;
    }

    /**
     * given two non-empty linked lists representing two non-negative integers. The most significant digit comes
     * first and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.
     * You may assume the two numbers do not contain any leading zero, except the number 0 itself.
     * Follow up:
     * you cannot modify the input lists. In other words, reversing the lists is not allowed.
     *
     * @param l1
     * @param l2
     * @return
     */
    public ListNode addTwoNumbersList(ListNode l1, ListNode l2) {
        ListNode p = reverseList(l1);
        ListNode q = reverseList(l2);
        ListNode ansRev = addTwoNumbers_1(p,q);
        return reverseList(ansRev);
    }

    /**
     * Merge two sorted linked lists and return it as a new list. The new list should be made by
     * splicing together the nodes of the first two lists.
     *
     * @param l1
     * @param l2
     * @return
     */
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode p = l1, q =l2;

        ListNode result = new ListNode(0);
        ListNode ans = result;
        //Base Conditions
        if( p == null) return q ;
        if ( q == null) return p ;

        if (p.val >= q.val){
            result = new ListNode(q.val);
            result.next = mergeTwoLists(p,q.next);
        }else{
            result = new ListNode(p.val);
            result.next = mergeTwoLists(p.next,q);
        }
        return result ;
    }


    /**
     * Write a program to find the node at which the intersection of two singly linked lists begins.
     *      - If the two linked lists have no intersection at all, return null.
     *      - The linked lists must retain their original structure after the function returns.
     *      - You may assume there are no cycles anywhere in the entire linked structure.
     *      - Your code should preferably run in O(n) time and use only O(1) memory.
     * @param a
     * @param b
     * @return
     */
    public ListNode getIntersectionNode(ListNode a, ListNode b) {
        ListNode p=a, q=b;
        if (p == null || q == null) return null;

        int lenA = getLengthOfLinkedList(p);
        int lenB = getLengthOfLinkedList(q);
        int difference = 0 ;
        if(lenA > lenB){
            difference = lenA-lenB;
            for(; 0<difference; difference--) p=p.next;
        }else{
            difference = lenB-lenA;
            for(; 0<difference; difference--) q=q.next;
        }

        while( p!=null && q!=null){
            if( p == q) return p;
            p=p.next;
            q=q.next;
        }
        return null;
    }

    public ListNode reverseBetween_(ListNode head, int m, int n) {

        if(m==n) return head;

        ListNode prev = null;//track (m-1)th node
        ListNode first = new ListNode(0);//first's next points to mth
        ListNode second = new ListNode(0);//second's next points to (n+1)th

        int i=0;
        ListNode p = head;
        while(p!=null){
            i++;
            if(i==m-1) prev = p;
            if(i==m) first.next = p;
            if(i==n){
                second.next = p.next;
                p.next = null;
            }
            p= p.next;
        }

        if(first.next == null)
            return head;

        // reverse list [m, n]
        ListNode p1 = first.next;
        ListNode p2 = p1.next;
        p1.next = second.next;

        while(p1!=null && p2!=null){
            ListNode t = p2.next;
            p2.next = p1;
            p1 = p2;
            p2 = t;
        }

        //connect to previous part
        if(prev!=null) prev.next = p1;
        else return p1;
        return head;
    }


    public ListNode reverseBetween(ListNode input, int m, int n) {

        int count = 1;
        ListNode head = input;

        ListNode firstPart = head;
        ListNode curr = head;
        ListNode reverseFrom = null;
        ListNode secondPart = null;

        if(m==n) return head;

        /* The new linked list is composed of  {first_part, reversed_part, second_part} */
        while (count <= n && curr != null) {
            if (count == m - 1) firstPart = curr;
            if (count == m) reverseFrom = curr;
            if (count == n) {
                secondPart = curr.next;
                curr.next = null;
                break;
            }
            curr = curr.next;
            count++;
        }

        if (count == n) {
            final ListNode reverse = reverse(reverseFrom, n - m + 1);

            /* If you are reversing from the head node, reset the head */
            if(m == 1) head = reverse;
            else firstPart.next = reverse;

            if (secondPart != null) reverseFrom.next = secondPart;
        }
        return head;
    }
    private ListNode reverse(ListNode n, int k) {
        ListNode curr = n;
        ListNode prev = null;
        int count = 0;
        while (count < k) {
            ListNode nextNode = curr.next;
            curr.next = prev;
            prev = curr;
            curr = nextNode;
            count++;
        }
        return prev;
    }


    /**
     * Given a linked list and a value x, partition it such that all nodes
     * less than x come before nodes greater than or equal to x
     * @param head
     * @param b
     * @return
     */
    public ListNode partition(ListNode head, int b) {
        if (head==null || head.next==null) return head;

        ListNode smallHead =new ListNode(0);
        ListNode greaterHead =new ListNode(0);
        ListNode small=smallHead, greater=greaterHead;

        while( head!=null){
            if( head.val <b) {
                small.next = new ListNode( head.val);
                head=head.next;
                small=small.next;
            } else {
                greater.next = new ListNode( head.val);
                head=head.next;
                greater=greater.next;
            }
        }

        if (smallHead != null && greaterHead!=null){
            small.next=greaterHead.next;
            return smallHead.next;
        } else if ( smallHead != null && greaterHead==null)
            return smallHead.next;
        else
            return greaterHead.next;
    }


    public ListNode detectCycle(ListNode head) {
        if(head==null) return null;
        ListNode slow = head, fast = head;      //start these two pointers from same point
        try {
            do {
                fast = fast.next.next;
                slow = slow.next;
            } while(fast!=null && fast!=slow);
        } catch(Exception e){}

        if( slow==null || fast==null || slow!=fast) return null; //case when there is no loop in linked list

        slow = head;
        while(slow!=fast) {
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }

    public ListNode detectCycle_HashMap(ListNode a) {
        HashMap<Integer,Integer> h=new HashMap<Integer,Integer>();
        int i=0;
        while(a!=null){
            if(h.containsKey(a.val)){
                return a;
            }
            else{
                h.put(a.val,i);
            }
            a=a.next;
            i++;
        }
        return null;
    }
    ////////////////////////////////////////////////////////////////////////////////

}
