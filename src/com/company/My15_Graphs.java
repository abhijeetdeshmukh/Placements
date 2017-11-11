package com.company;

import java.util.ArrayList;
import java.util.TreeSet;


import com.company.My12_Trees.TreeNode;
import com.company.My07_LinkedList.ListNode;

public class My15_Graphs {

    ////////////////////////////////////////////////////////////////////////////////////////////
    //BFS

    //01.  solveSmallestSequence - Smallest sequence with given Primes


    /**
     * Given three prime number(p1, p2, p3) and an integer k.
     * Find the first(smallest) k integers which have only p1, p2, p3 or a combination of them as their prime factors.

     Example:

     Input : Prime numbers : [2,3,5]    k : 5
     If primes are given as p1=2, p2=3 and p3=5 and k is given as 5, then the sequence of first 5 integers will be:
     Output:   {2,3,4,5,6}

     Explanation :
     4 = p1 * p1 ( 2 * 2 )
     6 = p1 * p2 ( 2 * 3 )

     Note: The sequence should be sorted in ascending order
     * @param A
     * @param B
     * @param C
     * @param D
     * @return
     */
    public ArrayList<Integer> solveSmallestSequence(int A, int B, int C, int D) {

        /*
        About Java TreeSet class are:
            1. Contains unique elements only like HashSet.
            2. Access and retrieval times are quiet fast.
            3. Maintains ascending order.
         */


        /*
        this problem can be addressed as a modified BFS / Dijkstra. We push p1, p2 and p3 to a min heap.
        Every time, we repeat the following process till we find k numbers :
            - M = Pop out the min element in the min heap.
            - Add M to the final answer.
            - Push M * p1, M * p2, M * p3 to the min heap if they are not already present in the heap
                ( We can use a hash map to track this )
            However, this is O( k * log k ).
        Can we do better than this ?

        It turns out we can.
        We use the fact that there are only 3 possibilities of getting to a new number : Multiply by p1 or p2 or p3.

        For each of p1, p2 and p3, we maintain the minimum number in our set which has not been multiplied with the corresponding prime number yet.
        So, at a time we will have 3 numbers to compare.
        The corresponding approach would look something like the following :


        initialSet = [p1, p2, p3]
        indexInFinalSet = [0, 0, 0]
                for i = 1 to k
                  M = get min from initialSet.
                  add M to the finalAnswer if last element in finalAnswer != M
                  if M corresponds to p1 ( or in other words M = initialSet[0] )
                    initialSet[0] = finalAnswer[indexInFinalSet[0]] * p1
                    indexInFinalSet[0] += 1
                  else if M corresponds to p2 ( or in other words M = initialSet[1] )
                    initialSet[1] = finalAnswer[indexInFinalSet[1]] * p1
                    indexInFinalSet[1] += 1
                  else
                    # Similar steps for p3.
                end
         */

        ArrayList<Integer> resultList = new ArrayList<>() ;
        TreeSet<Integer> treeSet = new TreeSet<>() ;  //important data type in Java
        treeSet.add(A) ;
        treeSet.add(B) ;
        treeSet.add(C) ;

        for(int i = 0; i < D; i++) {
            int minElement = treeSet.first() ;
            treeSet.remove( minElement) ;   //remove the minElements so tha in next iteration we will get next min element
            resultList.add( minElement) ;

            treeSet.add( minElement*A) ;
            treeSet.add( minElement*B) ;
            treeSet.add( minElement*C) ;

        }
        return resultList ;
    }

    public ArrayList<Integer> solveSmallestSequence_(int p1,int p2,int p3,int k) {
        ArrayList<Integer> finalAnswer = new ArrayList<Integer>();
        int[] nextBestNumbers = new int[3];         //array declaration in Java
        int[] currIndex = new int[3];
        int[] prime = new int[3];

        nextBestNumbers[0] = prime[0] = p1;
        nextBestNumbers[1] = prime[1] = p2;
        nextBestNumbers[2] = prime[2] = p3;

        currIndex[0] = currIndex[1] = currIndex[2] = -1;

        for(int j=1;j<=k;j++) {
            int nextNumber = Math.min( nextBestNumbers[0], Math.min( nextBestNumbers[1],nextBestNumbers[2]));
            finalAnswer.add(nextNumber);

            for(int i=0;i<3;i++) {
                if(nextNumber==nextBestNumbers[i]) {
                    currIndex[i]++;
                    nextBestNumbers[i] = finalAnswer.get( currIndex[i])*prime[i];
                }
            }
        }

        return finalAnswer;
    }

    public ArrayList<Integer> solveSmallestSequence__(int A, int B, int C, int D) {
        ArrayList<Integer> res = new ArrayList();
        int indexA = 0, indexB = 0, indexC=0;
        res.add(1);
        while (D-- > 0) {
            int A1 = res.get(indexA)*A;
            int A2 = res.get(indexB)*B;
            int A3 = res.get(indexC)*C;
            int min = Math.min(A1, Math.min(A2, A3));
            res.add(min);
            if (min == A1) { ++indexA;}
            if (min == A2) { ++indexB; }
            if (min == A3) { ++indexC;}
        }
        res.remove(0);
        return res;
    }




//    public TreeNode sortedListToBST(ListNode head) {
//
//        if(head==null) return null;
//        return toBST(head,null);
//
//    }
//
//    public TreeNode toBST(ListNode head, ListNode tail){
//        ListNode slow = head;
//        ListNode fast = head;
//        if(head==tail) return null;
//
//        while(fast!=tail&&fast.next!=tail){
//            fast = fast.next.next;
//            slow = slow.next;
//        }
//        TreeNode thead = new TreeNode(slow.val);
//        thead.left = toBST(head,slow);
//        thead.right = toBST(slow.next,tail);
//        return thead;
//    }


}
