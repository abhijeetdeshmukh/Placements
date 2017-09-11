package com.company;

import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
	// write your code here
    }

    /***
     * Given an array of integers, return indices of the
     * two numbers such that they add up to a specific target.
     *
     * @param nums - array of integers
     * @param target - targeted sum of two integer in an array
     * @return
     *
     *  @link : https://leetcode.com/articles/two-sum/
     */
    public int[] twoSum_1(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if ( nums[i] + nums[j] == target) {
                    return new int[] { i, j };
                }
            }
        }
        throw new IllegalArgumentException("No two sum solution");
    }

    public int[] twoSum_2(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement) && map.get(complement) != i) {
                return new int[] { i, map.get(complement) };
            }
        }
        throw new IllegalArgumentException("No two sum solution");
    }

    public int[] twoSum_3(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement)) {
                return new int[] { map.get(complement), i };
            }
            map.put(nums[i], i);
        }
        throw new IllegalArgumentException("No two sum solution");
    }


    /*** Definition for singly-linked list.*/
    public class ListNode {
      int val;
      ListNode next;
      ListNode(int x) { val = x; }
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

    public ListNode addTwoNumbers_2(ListNode l1, ListNode l2) {

        ListNode sumHead = new ListNode (0);
        //create variable for current summing node
        ListNode sumNode = sumHead ;

        int carry = 0 , sum;
        //loop until the end of both list
        while ( l1 != null || l2 != null) {

            sum = carry ;

            if ( l1 != null ) {
                sum += l1.val ;
                l1 = l1.next ;
            }

            if ( l2 != null ) {
                sum += l2.val ;
                l2 = l2.next ;
            }

            if ( sum > 9) {
                carry = 1 ;
                sumNode.next =  new ListNode( sum - 10);
            } else {
                carry = 0 ;
                sumNode.next =  new ListNode( sum );
            }
            sumNode = sumNode.next ;
        }

        if (carry > 0) {
            sumNode.next =  new ListNode( carry);
        }

        return sumHead.next ;
    }



    public int reverse(int x) {
        boolean neg = false;
        if (x < 0) {
            neg = !neg;
            x = -x;
        }
        String string = String.valueOf(x);
        StringBuilder builder = new StringBuilder(string);
        System.out.println(builder);
        builder.reverse();
        string = new String(builder);
        Integer result;
        try {
            result = Integer.valueOf(string);
        } catch (Exception e) {
            return 0;
        }
        return neg?-result:result;
    }

    public boolean isPalindrome(int x) {

        // Special cases:
        // when x < 0, x is not a palindrome.
        // Also if the last digit of the number is 0, in order to be a palindrome,
        // the first digit of the number also needs to be 0.
        // Only 0 satisfy this property.
        if(x < 0 || (x % 10 == 0 && x != 0)) {
            return false;
        }

        //
        int revertedNumber = 0;
        while(x > revertedNumber) {
            revertedNumber = revertedNumber * 10 + x % 10;
            x /= 10;
        }

        // When the length is an odd number, we can get rid of the middle digit by revertedNumber/10
        // For example when the input is 12321, at the end of the while loop we get x = 12, revertedNumber = 123,
        // since the middle digit doesn't matter in palidrome(it will always equal to itself), we can simply get rid of it.
        return x == revertedNumber || x == revertedNumber/10;
    }

}
