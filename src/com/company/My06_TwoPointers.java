package com.company;

import java.util.ArrayList;
import java.util.List;

public class My06_TwoPointers {


    ///////////////////////////////////////////////////////////////////////////////////////////////////////
    // removeDuplicate

    /***
     * Remove duplicates from Sorted Array
     * Given a sorted array, remove the duplicates in place such that each element appears only once
     * and return the new length.
     * @param a
     * @return
     */
    public int removeDuplicates( ArrayList<Integer> a) {
        if (a.size() < 2) return a.size();

        // List#remove is proportional to the number of elements after the removed index
        // Thus, we move from the end for optimal run time
        int prev = a.get( a.size()-1 );
        for (int i = a.size() - 2; i >= 0; i--) {
            if (a.get(i) == prev) {
                a.remove(i);
            }
            else {
                prev = a.get(i);
            }
        }

        return a.size();
    }


    /**
     * You are given 3 arrays A, B and C. All 3 of the arrays are sorted.
     * Find i, j, k such that :
     * max(abs(A[i] - B[j]), abs(B[j] - C[k]), abs(C[k] - A[i])) is minimized.
     * Return the minimum max(abs(A[i] - B[j]), abs(B[j] - C[k]), abs(C[k] - A[i]))
     *
     * @param a
     * @param b
     * @param c
     * @return
     */
    public int minimize(final List<Integer> a, final List<Integer> b, final List<Integer> c) {
        int minimum=Integer.MAX_VALUE;
        int maximum=Integer.MIN_VALUE;
        int diff=Integer.MAX_VALUE;
        int i = 0, j = 0, k = 0;
        while(i < a.size() && j < b.size() && k < c.size()) {
            minimum = Math.min(a.get(i), Math.min(b.get(j), c.get(k)));
            maximum = Math.max(a.get(i), Math.max(b.get(j), c.get(k)));
            diff = Math.min(diff, maximum - minimum);
            if (diff == 0) break;
            if (a.get(i) == minimum) i++;
            else if (b.get(j) == minimum) j++;
            else
                k++;
        }
        return diff;
    }

}
