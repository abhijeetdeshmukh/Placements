package com.company;

import java.util.ArrayList;
import java.util.Collections;

public class My05_BitManipulation {

    //operations
        // 1. Bitwise & --- A&B
        // 2. Bitwise | --- A|B
        // 3. Bitwise ^ --- A^B
        // 4. Bitwise ~ --- A~B

/////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////// Bucketing
    // 01. findMinXor
    /////// Bit Play
    // 02.





    /**
     * Given an array of N integers, find the pair of integers in the array which have
     * minimum XOR value. Report the minimum XOR value.
     * @param A
     * @return
     */
    public int findMinXor(ArrayList<Integer> A) {

        /*
        The first step is to sort the array.
        The answer will be the minimal value of X[i] XOR X[i+1] for every i.

                Proof:
                Let’s suppose that the answer is not X[i] XOR X[i+1], but A XOR B and there exists C
                in the array such as A <= C <= B.

                Next is the proof that either A XOR C or C XOR B are smaller than A XOR B.

                Let A[i] = 0/1 be the i-th bit in the binary representation of A
                Let B[i] = 0/1 be the i-th bit in the binary representation of B
                Let C[i] = 0/1 be the i-th bit in the binary representation of C

                This is with the assumption that all of A, B and C are padded with 0 on the left until they all have the same length

                Example: A=169, B=187, C=185

                A=101010012
                B=101110112
                C=101110012

                Let i be the leftmost (biggest) index such that A[i] differs from B[i]. There are 2 cases now:
                1) C[i] = A[i] = 0,
                then (A XOR C)[i] = 0 and (A XOR B)[i] = 1
                This implies (A XOR C) < (A XOR B)
                2) C[i] = B[i] = 1,
                then (B XOR C)[i] = 0 and (A XOR B)[i] = 1
                This implies (B XOR C) < (A XOR B)

                Time complexity: O( N*logN) to sort the array and O(N) to find the smallest XOR
                Space complexity: O(N)
         */

        int min= Integer.MAX_VALUE;
        Collections.sort(A);
        for(int i=0; i<A.size()-1; i++){
            int xor = A.get(i)^A.get(i+1);
            min = Math.min( min,xor);
        }
        return min;
    }


    /**
     * Write a function that takes an unsigned integer and returns the number of 1 bits it has.
     * @param a
     * @return
     */
    public int numSetBits(long a) {

        /*
        Bruteforce:
            Iterate 32 times, each time determining if the ith bit is a ’1′ or not.
            This is probably the easiest solution, and the interviewer would probably not be too happy about it.
            This solution is also machine dependent (You need to be sure that an unsigned integer is 32-bit).
            In addition, this solution is not very efficient too because you need to iterate 32 times no matter what.
         */

        int count =0;
        for(int i=1; a>0||i<32; i++){
            if (a%2 ==1) count++;
            a=a/2;  //  a = a >> 1;  // same meaning
        }
        return count;

        /*
        A better solution:
            This special solution uses a trick which is normally used in bit manipulations.
            Notice what x-1 does to bit representation of x.
            x-1 would find the first set bit from the end, and then set it to 0, and set all the bits following it.

            Which means if x = 10101001010100
                                          ^
                                          |
                                          |
                                          |
                                   First set bit from the end
            Then x-1 becomes 10101001010(011)

            All other bits in x - 1 remain unaffected.
            This means that if we do (x & (x-1)), it would just unset the last set bit in x
            (which is why x&(x-1) is 0 for powers of 2).

            Can you use the above fact to come up with a solution ?
         */
    }

}
