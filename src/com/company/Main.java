package com.company;

import java.util.*;

import static jdk.nashorn.internal.objects.NativeArray.push;

public class Main {

    public static void main(String[] args) {
        // write your code here
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////


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

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //Maths and Numbers
    /* Iterative function to reverse digits of number*/
    public int reversDigits(int num) {
        boolean isNegative = false;             // Handling negative numbers
        if (num < 0) {
            isNegative = true;
            num = -num ;
        }
        int prevRevNum = 0, revNum = 0;
        while (num != 0) {
            int currentDigit = num%10;
            revNum = (revNum*10) + currentDigit;
            if ((revNum - currentDigit)/10 != prevRevNum) {           // checking if the reverse overflowed or not.
                System.out.println("WARNING OVERFLOWED!!!");          // The values of (rev_num - curr_digit)/10 and prev_rev_num must be same
                return 0;                                            // if there was no problem.
            }
            prevRevNum = revNum;
            num = num/10;
        }
        return (isNegative )? -revNum : revNum;
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

    /**
     *
     * @param x
     * @return
     */
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

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////





    //////////////////////////////////////////////////////////////////////////////////////////////////

    //this code - idk
    /**
     * There are two sorted arrays A and B of size m and n respectively.
     * Find the median of the two sorted arrays ( The median of the array formed by merging both the arrays ).
     * The overall run time complexity should be O(log (m+n)).
     * @param A
     * @param B
     * @return
     */
    public double findMedianSortedArrays(final List<Integer> A, final List<Integer> B) {
        int len = A.size() + B.size();
        if(len % 2 == 1) return findKth(A, 0, B, 0, len / 2 + 1);
        else return (findKth(A, 0, B, 0, len / 2) + findKth(A, 0, B, 0, len / 2 + 1)) / 2.0;
    }

    public int findKth(List<Integer> A, int A_start, List<Integer> B, int B_start, int k){
        if(A_start >= A.size()) return B.get(B_start + k - 1);
        if(B_start >= B.size()) return A.get(A_start + k - 1);
        if(k == 1) return Math.min(A.get(A_start), B.get(B_start));

        int A_key = A_start + k / 2 - 1 < A.size() ? A.get(A_start + k / 2 - 1) : Integer.MAX_VALUE;
        int B_key = B_start + k / 2 - 1 < B.size() ? B.get(B_start + k / 2 - 1) : Integer.MAX_VALUE;

        if(A_key < B_key) return findKth(A, A_start + k / 2, B, B_start, k - k / 2);
        else              return findKth(A, A_start, B, B_start + k / 2, k - k / 2);
    }

    /**
     *
     * @param nums1 array - sorted
     * @param nums2 array - sorted
     * @return median of two combined array
     */
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int[] c = merge(nums1, nums2);
        if (c.length % 2 == 0) {
            return (double) (c[ c.length/2 -1 ] + c[ (c.length / 2) ]) / 2;
        } else {
            return (double) c[ c.length/2 ];
        }
    }
    public static int[] merge(int[] a, int[] b) {
        int c[] = new int[a.length + b.length];     //declaring array to return
        int i = 0;  int j = 0;  int k = 0;
        while ( i < a.length && j < b.length ) {
            if( a[i] == b[j] ) {
                c[k] = a[i]; k++; i++;
                c[k] = b[j]; k++; j++;
            } else if( a[i] < b[j]) {
                c[k] = a[i]; k++; i++;
            } else if( a[i] > b[j]) {
                c[k] = b[j]; k++; j++;
            }
        }

        while(i<a.length) {     c[k] = a[i]; k++; i++;  }
        while (j<b.length) {    c[k] = b[j]; k++; j++; }

        return c;
    }



    //////////////////////////////////////////////////////////////////////////////////////////////////////////


    // X and Y co-ordinates of the points in order.
    // Each point is represented by (X.get(i), Y.get(i))





    /**
     * Given a number N, find all factors of N.
     * @param a
     * @return
     */
    public ArrayList<Integer> allFactors(int a) {
        ArrayList<Integer> factors = new ArrayList<Integer>();

        int sqrt = (int) Math.sqrt(a);
        for (int i = 1; i <= sqrt; i++) {
            if (a % i == 0) {
                factors.add(i);
            }
        }

        for (int i = factors.size() - 1; i >= 0; i--) {
            int num = factors.get(i);
            if (num != a/num) factors.add(a/num);
        }
        return factors;
    }

    public ArrayList<Integer> allFactors_1(int a) {
        ArrayList<Integer> resh=new ArrayList<>();
        LinkedList<Integer> rest=new LinkedList<>();
        int f=1;
        while (f<=Math.sqrt(a)) {
            if (a % f == 0) {
                resh.add(f);
                if (a/f!=f) {
                    rest.addFirst(a/f);
                }
            }
            f++;
        }
        resh.addAll(rest);
        return resh;
    }

    /**
     * Given an even number ( greater than 2 ), return two prime numbers whose sum will be equal to given number.
     * NOTE A solution will always exist. read Goldbach’s conjecture
     * @param A
     * @return
     */
    public ArrayList<Integer> primeSum(int A) {
        ArrayList<Integer> arr = new ArrayList<Integer>();
        for (int i = 2; i < A; i++) {
            if (isPrime(i) && isPrime(A - i)) {
                arr.add(i);
                arr.add(A - i);
                return arr;
            }
        }
        return arr;
    }

    /**
     * 
     * @param a
     * @return
     */
    public boolean isPrime(int a) {
        int sqrt = (int) Math.sqrt(a);
        boolean prime = true ;
        if (a == 1) return false ;
        for(int i = 2; i <= sqrt;i++){
            if (a%i == 0) return !prime;
        }
        return prime ;
    }

//check this function once again
    /**
     * time complexity = O(n log logn)
     * @param a
     * @return
     */
    public ArrayList<Integer> sieve(int a) {
        ArrayList<Integer> primes = new ArrayList<Integer>();
        for(int i =0; i<=a; i++ ){
            primes.add(1,1);
        }
        primes.set(0,0);
        primes.set(1,0);
        for (int i=2;i<=Math.sqrt(a);i++){
            if (primes.get(i)==1){
                for (int j=2;i+j <= a; j++){
                    primes.set(i*j,0);
                }
            }
        }

        ArrayList<Integer> primeTo = new ArrayList<Integer>();
        for (int i = 0; i <= a; i++){
            if (primes.get(i)==1) primeTo.add(i);
        }
        return primeTo;
    }


    public ArrayList<Integer> primeNumbersUpto(int a) {
        ArrayList<Integer> ans=new ArrayList<>();
        for(int i=2;i<=a;i++) {
            if(isPrime(i) == true) ans.add(i);
        }
        return ans;
    }

    public String findDigitsInBinary(int a) {
        return Integer.toBinaryString(a);
    }

    /**
     * Given a number N >= 0, find its representation in binary.
     * @param a
     * @return
     */
    public String findDigitsInBinary_sb(int a) {
        if(a <= 0 ) return "0";
        StringBuilder sb = new StringBuilder();
        while( a > 0){
            sb.append(a%2);
            a = a/2;
        }

        return sb.reverse().toString();
    }

    /**
     * Find out the maximum sub-array of non negative numbers from an array.
     * The sub-array should be continuous. That is, a sub-array created by choosing the second and fourth element
     * and skipping the third element is invalid.
     * Maximum sub-array is defined in terms of the sum of the elements in the sub-array. Sub-array A is
     * greater than sub-array B if sum(A) > sum(B).
     * @param a
     * @return
     */
    public ArrayList<Integer> maxNonNegativeSubArray(ArrayList<Integer> a) {
        long maxSum = 0;
        long newSum = 0;
        ArrayList<Integer> maxArray = new ArrayList<Integer>();
        ArrayList<Integer> newArray = new ArrayList<Integer>();
        for (Integer i : a) {
            if (i >= 0) {
                newSum += i;
                newArray.add(i);
            } else {
                newSum = 0;
                newArray = new ArrayList<Integer>();
            }

            if ((maxSum < newSum) || ((maxSum == newSum) && (newArray.size() > maxArray.size()))) {
                maxSum = newSum;
                maxArray = newArray;
            }
        }
        return maxArray;
    }

    /**
     * Given a list of non negative integers, arrange them such that they form the largest number.
     * Given [3, 30, 34, 5, 9], the largest formed number is 9534330.
     * @param a
     * @return
     */
    public String largestNumber_1(final List<Integer> a) {
        List<String> strs = new ArrayList<String>();
        for(Integer i : a){
            strs.add(String.valueOf(i));
        }
        Collections.sort( strs, new Comparator<String>(){
            @Override
            public int compare(String a, String b) {
                String s1 = a + b;
                String s2 = b + a;
                return s2.compareTo(s1);
            }
        });

        StringBuffer strBuf = new StringBuffer();
        for(String s : strs){
            if("0".equals(s) && strBuf.length() != 0) {
                continue;
            }
            strBuf.append(s);
        }
        if (strBuf.length() == 0 || strBuf.charAt(0) == '0') {
            return "0";
        }
        return strBuf.toString();
    }
//    class MyCompartor implements Comparator<String>{
//        @Override
//        public int compare(String s1, String s2){
//            String first = s1 + s2;
//            String second = s2 + s1;
//            return second.compareTo(first);
//        }
//    }
    
    public String largestNumber_2(final List<Integer> A) {
        StringBuffer strBuf = new StringBuffer();
        Node num[];
        int i = 0;

        num = new Node[A.size()];

        for (int n : A) {
            num[i] = new Node(n);
            i++;
        }
        Arrays.sort(num);

        for (Node n : num) {
            if (n.number == 0 && strBuf.length() != 0)
                continue;
            strBuf.append(n.number);
        }
        return strBuf.toString();
    }

    class Node implements Comparable<Node> {

        int number;

        public Node(int number) {
            this.number = number;
        }

        @Override
        public int compareTo(Node o) {
            String first = String.valueOf(this.number) + String.valueOf(o.number);
            String second = String.valueOf(o.number) + String.valueOf(this.number);

            return second.compareTo(first);
        }

    }

    public String largestNumber(int[] nums) {
        ArrayList<String> string = new ArrayList<String>();
        for(int i=0 ; i< nums.length;i++){
            string.add( Integer.toString(nums[i]));
        }
        String greatestNumber = "" ;
        List<String> subList = string.subList(0, string.size());
        Collections.sort(subList);
        for(String s : subList){
            greatestNumber = s + greatestNumber ;
        }
        return greatestNumber;
    }

    /**
     * GCD/HCF - recursion
     * @param a
     * @param b
     * @return
     */
    public int getGCD( int a, int b){
        if (b != 0){
            return getGCD(b, a%b);
        }
        else {
            return a;
        }
    }

    /**
     * Given an integer array, find if an integer p exists in the array such that the number of integers 
     * greater than p in the array equals to p
     * @param A
     * @return If such an integer is found return 1 else return -1.
     */
    public int solve(ArrayList<Integer> A) {
        Collections.sort(A);
        int totalElements = A.size();
        for(int i=0; i<totalElements; i++){
            int num = A.get(i);
            if((i<totalElements-1 && num==totalElements-i-1 && num!=A.get(i+1)) || (i==totalElements-1 && num==0)){
                return 1;
            }
        }
        return -1;
    }


    /**
     * Binary search - recursion 
     * @param A - given collection
     * @param low
     * @param high
     * @param x - search element
     * @return index of element
     */
    public int BinarySearch_recursion(final List<Integer> A, int low, int high, int x){
        if (low>high) return -1;    //base condition 
        int mid = low + (high-low)/2 ;
        if (x == A.get(mid) ) return mid; //base condition
        else if( x<A.get(mid) ) return BinarySearch_recursion(A,low,mid-1,x);
        else  return BinarySearch_recursion(A,low+1,mid,x);
    }
    
    /**
     * Binary Search
     * @param A
     * @param x
     * @return
     */
    public int BinarySearch_iterative(final List<Integer> A, int x){
        int low=0, high=A.size()-1;
        while (low <= high){
            int mid = low + (high-low)/2 ; // to avoid overflow
            if( x==A.get(mid) ) return mid;
            else if( x<A.get(mid) ) high=mid-1; // x lies before mid
            else low = mid +1 ; //x lies after mid
        }
        return -1 ;
    }

    /**
     * Binary Search to get first occurrence and last occurrence of given number in given collection
     * Iterative approach
     *@param A - collection
     * @param x - given number
     * @param searchFirst if false then gives index of first occurrence ; if true - index of last occurance 
     * @return index of occurrence
     */
    public int BinarySearch_iterative( final List<Integer> A, int x, boolean searchFirst ){
        int low=0, high=A.size()-1, result =-1;
        while (low <= high){
            int mid = low + (high-low)/2 ; // to avoid overflow
            if( x==A.get(mid) ){
                result = mid;
                if(searchFirst) high=mid-1; //go on searching towards left (lowe indices)
                else low = mid+1; //go on searching towards right (higher indices)
            }
            else if( x<A.get(mid) ) high=mid-1; // x lies before mid
            else low = mid +1 ; //x lies after mid
        }
        return result ;
    }


    /**
     * You’re given a read only array of n integers. Find out if any integer occurs more than n/3 times
     * in the array in linear time and constant additional space.
     * If so, return the integer. If not, return -1.
     * If there are multiple solutions, return any one.
     * @param a
     * @return
     */
    public int repeatedNumberNby3(final List<Integer> a) {
        Collections.sort(a);
        int occurance =0 , number =0;
        int size =a.size();
        if (size ==1) return a.get(0);
        for (int m=0; m<size-1;m++){
            number = a.get(m);
            int lower = BinarySearch_iterative( a, number, true );
            int upper = BinarySearch_iterative( a, number, false );
            occurance = upper -lower + 1;
            if (occurance > size/3) return number ;
            m=upper;
        }
        return -1;
    }

    /***
     *
     * @param a
     * @return
     */
    public int getDuplicateNumberInArray(final List<Integer> a) {
        Collections.sort(a);
        int number =0;
        int size =a.size();
        if (size ==1) return -1;
        for (int m=0; m<size-1;m++){
            number = a.get(m);
            int lower =BinarySearch_iterative(a, number, true);
            int upper =BinarySearch_iterative(a, number, false);
            if (upper != lower ) return number ;
        }
        return -1;
    }

    /**
     * Given a sorted array of integers, find the number of occurrences of a given target value.v
     * Your algorithm’s runtime complexity must be in the order of O(log n).
     * If the target is not found in the array, return 0
     * @param A -list 
     * @param B - search element
     * @return
     */
    public int findCount(final List<Integer> A, int B) {
        int lower, upper;
        lower = BinarySearch_iterative(A, B, true);
        upper = BinarySearch_iterative(A, B, false);
        return (upper - lower + 1);
    }

    /**
     * Implement int sqrt(int x).
     * Compute and return the square root of x.
     * If x is not a perfect square, return floor(sqrt(x))
     * @param x
     * @return
     */
    public int sqrtByBinarySearch(int x) {
        if (x == 0 || x == 1) return x;    // Base cases
        int sqrt = 0;
        long start = 1, end = x/2 + 1;        //optimized to start with 'start' = 0 and 'end' = x/2.
                                                // Floor of square root of x cannot be more than x/2 when x > 1.
        while (start <= end) {                  // Do Binary Search for floor(sqrt(x))
            long mid = start + (end - start)/2;
            long check = mid * mid;             // long check  = mid*mid;
            if (check == x)   return (int) mid;   // If x is a perfect square
            else if (check < x) {                   // Since we need floor, we update sqrt when mid*mid is smaller than x, and move closer to sqrt(x)
                start = mid + 1;
                sqrt = (int) mid;
            } else // If mid*mid is greater than x
                end = mid - 1;
        }
        return sqrt;
    }

    /**
     * Suppose a sorted array A is rotated at some pivot unknown to you beforehand.
     * (i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).
     * Find the minimum element. The array will not contain duplicates.
     * @param a
     * @return
     */
    public int findMin(final List<Integer> a) {
        return a.get( findRotationCount(a) );
    }
    /**
     * Function calculate the index of min element of circularly sorted aaray.
     * Case: The array will not contain duplicates.
     * @param A
     * @return
     */
    public int findRotationCount(final List<Integer> A){
        int low=0, high=A.size()-1;
        while (low <= high){
            if(A.get(low) <= A.get(high)) return low;       //case 1 : list is already sorted
            int mid = low + (high-low)/2 ; // to avoid overflow
            int next = mid+1;
            int prev = mid-1;
            if( A.get(mid) <= A.get(next) && A.get(mid)<=A.get(prev) ) return mid;  //case 2: Pivot property - next and previous elements are greater
            else if( A.get(mid) <= A.get(high) ) high=mid-1; // x lies before mid   //case 3: this (Right side) segment is sorted - search pivot in left half
            else low = mid +1 ; //x lies after mid      //A.get(mid) >= A.get(low)  //case 4: opposite to case 3
        }
        return -1 ;
    }


    /**
     * Given a matrix of m * n elements (m rows, n columns), return all elements of the matrix in spiral order.
     * @param A
     * @return
     */
    public ArrayList<Integer> spiralOrder(final List<ArrayList<Integer>> A) {
        ArrayList<Integer> result = new ArrayList<Integer>();
        int m = A.size(), n = A.get(0).size();      //m=rows and n=columns
        if (m == 0) return result;

        int len;
        int direction = 0; // right
        int row, col, layer;
        row = col = layer = 0;

        result.add(A.get(0).get(0));
        for (int step = 1; step < m * n; step++) {
            switch(direction) {
                case 0: if (col == n - layer - 1) {         // Go right
                        direction = 1;
                        row++;
                    } else col++;
                    break;
                case 1: if (row == m - layer - 1) {         // Go down
                        direction = 2;
                        col--;
                    } else row++;
                    break;
                case 2: if (col == layer) {                 // Go left
                        direction = 3;
                        row--;
                    } else
                        col--;
                    break;
                case 3: if (row == layer + 1) {             // Go up
                        direction = 0;
                        col++;
                        layer++;
                    } else row--;
                    break;
            }
            result.add(A.get(row).get(col));        //System.out.println(row + " " + col);
        }
        return result;
    }

    /**
     * Given a set of non-overlapping intervals, insert a new interval into the intervals (merge if necessary).
     * You may assume that the intervals were initially sorted according to their start times.
     * @param intervals
     * @param newInterval
     * @return
     */
    public ArrayList<Interval> insertInterval(ArrayList<Interval> intervals, Interval newInterval) {
        ArrayList<Interval> result = new ArrayList<Interval>();

        for(Interval interval: intervals){
            if(interval.end < newInterval.start){
                result.add(interval);
            }else if(interval.start > newInterval.end){
                result.add(newInterval);
                newInterval = interval;
            }else if(interval.end >= newInterval.start || interval.start <= newInterval.end){
                newInterval = new Interval(Math.min(interval.start, newInterval.start), Math.max(newInterval.end, interval.end));
            }
        }
        result.add(newInterval);
        return result;
    }
    public class Interval {
      int start;
      int end;
      Interval() { start = 0; end = 0; }
      Interval(int s, int e) { start = s; end = e; }
  }

    /**
     * Implement the next permutation, which rearranges numbers into the numerically next greater permutation
     * of numbers.
     * If such arrangement is not possible, it must be rearranged as the lowest possible order ie,
     * sorted in an ascending order.
     * @param nums
     */
    public void nextPermutation( ArrayList<Integer> nums ) {
        int size = nums.size();
        int i = size - 2;
        while (i>=0 && nums.get(i + 1) <= nums.get(i))  i--;  //finding the decreasing element index

        if (i >= 0) {
            int j = size - 1;
            while (j >= 0 && nums.get(j) <= nums.get(i))  j--;  //finding number just more than that
            swapElements(nums, i, j);                                   //swap the two numbers
        }
        reverseSegment(nums, i + 1);                               //reverse the segment from i+1 to j(ie last element)
    }
    private void reverseSegment(ArrayList<Integer> nums, int start) {
        int i = start, j = nums.size() - 1;
        while (i < j) {
            swapElements(nums, i, j);
            i++;
            j--;
        }
    }
    private void swapElements(ArrayList<Integer> nums, int i, int j) {
        int temp = nums.get(i);
        nums.set(i, nums.get(j));
        nums.set(j, temp);
    }

    /**
     * Given a positive integer n and a string s consisting only of letters D or I,
     * you have to find any permutation of first n positive integer that satisfy the given input string.
     * D means the next number is smaller, while I means the next number is greater.
     * Notes: 1 - Length of given string s will always equal to n - 1
     *        2 - Your solution should run in linear time and space.
     * @param A
     * @param n
     * @return
     */
    public ArrayList<Integer> findPermutation(final String A, int n) {
        int max=n, min=1;
        ArrayList<Integer> result = new ArrayList<>();
        for(int i=0;i<n-1;i++) {
            if(A.charAt(i)=='I') {
                result.add(min);
                min++;
            } else {
                result.add(max);
                max--;
            }
        }
        result.add(min);
        return result;
    }

    /**Numbers of length N and value less than K
     * Given a set of digits (A) in sorted order, find how many numbers of length B are
     * possible whose value is less than number C.
     * @param A
     * @param B
     * @param C
     * @return
     */
    public int lengthN_lessThanK_1(ArrayList<Integer> A, int B, int C) {
        int cSize = (int) Math.log10(C)+1;
        int n = A.size();
        if (cSize < B || n == 0) return 0;
        boolean hasZero = A.get(0) == 0;
        if (cSize > B) return (B>1 && hasZero ? n-1:n) * (int) Math.pow(n, B - 1);
        // B == cSize
        int pow10 = (int) Math.pow(10, B-1), count = 0;
        for(int i = B; i > 0; i--) {
            int target = C/pow10, j;
            C %= pow10;
            pow10 /= 10;
            for(j = 0; j < n; j++) {
                if (A.get(j) >= target) break;
            }
            count += (B > 1 && i == B && hasZero ? j - 1 : j) * (int) Math.pow(n, i - 1);
            if (j == n || A.get(j) > target) break;
        }
        return count;
    }

    public int lengthN_lessThanK_2(ArrayList<Integer> A, int B, int C) {
        String str=Integer.toString(C);
        int cl=str.length();
        int size=A.size();
        int d=size,d2;
        if(B>cl || C==0 || size==0) return 0;
        int zeros=A.lastIndexOf(0)-A.indexOf(0)+1;
        boolean zero=A.contains(0);
        int ans=0;
        if(B<cl){
            if(zero && B!=1) ans += size-1;
            else ans=size;
            for(int i=1;i<B;i++) ans *= size;
            return ans;
        }

        int dp[]=new int[B+1];
        int lower[]=new int[11];

        if(B==cl){

            for(int i = 0; i <= B; i++)
                dp[i] = 0;
            for(int i = 0; i <= 10; i++)
                lower[i] = 0;
            for(int i = 0; i < d; i++)
                lower[A.get(i) + 1] = 1;

            for(int i = 1; i <= 10; i++)
                lower[i] = lower[i-1] + lower[i];

            boolean flag = true;
            dp[0] = 0;
            for(int i = 1; i <= B; i++) {
                int digit=str.charAt(i-1)-'0';
                d2 = lower[digit];
                dp[i] = dp[i-1] * d;

                // For first index we can't use 0
                if(i == 1 &&  A.get(0) == 0 && B != 1)
                    d2 = d2 - 1;

                //Whether (i-1) digit of generated number can be equal to (i - 1) digit of C.
                if(flag)
                    dp[i] += d2;
                //Is digit[i - 1] present in A ?
                flag = flag & (lower[digit + 1] == lower[digit] + 1);
            }
            return dp[B];
        }
        return 0;
    }

    public int lengthN_lessThanK_3(ArrayList<Integer> A, int B, int C) {

        if( A.size()==0) return 0;      //Base conditions
        if( C==0) return 0;

        int i=0;
        while( i<A.size() ){
            if( i!=A.size()-1 && A.get(i).equals( A.get(i+1) ) ){
                A.remove(i);
            }
            else i++;
        }

        boolean b=false;
        if(A.get(0)==0) b=true;

        int t=C, co=0;
        while(t>0){
            co++;
            t/=10;
        }

        int s=0;
        if(B<co){
            s=(int)Math.pow(A.size(), B-1);
            if(b && B!=1) s*=A.size()-1;
            else s*=A.size();
            return  s;
        }
        else if(B>co) return 0;

        t=C;int d, n, c;
        while(B>0){
            if(B==1){
                c=count(A, t);
                s+=c;
            } else{
                d=(int)Math.pow(10, B-1);
                n=t/d;
                c=count(A, n);
                if(b && t==C) c--;
                s+=c*Math.pow(A.size(), B-1);
                if(!A.contains(n)) break;
                t=t%d;
            }
            B--;
        }
        return s;
    }
    int count(ArrayList<Integer> A, int B){
        int c=0;
        for(Integer i: A){
            if(i<B) c++;
        }
        return c;
    }


    /**
     * A robot is located at the top-left corner of an A x B grid
     * The robot can only move either down or right at any point in time. The robot is trying
     * to reach the bottom-right corner of the grid (marked ‘Finish’ in the diagram below).
     * How many possible unique paths are there?
     * @param a
     * @param b
     * @return
     */
    public int uniquePaths(int a, int b) {

        return 0;
    }


    /**
     * Given an array of integers, every element appears twice except for one. Find that single one.
     * @param a
     * @return
     */
    public int singleNumber_II(final List<Integer> a) {
        int result =0 ;
        for(int i : a)  result = result ^ i;  // XOR operation in java
        return result;
    }

    /**
     * Given an array of integers, every element appears three times except for one,
     * which appears exactly once. Find that single one.
     * @param a
     * @return
     */
    public int singleNumber_III_1(final List<Integer> a) {
        int ones = 0 ; //At any point of time, this variable holds XOR of all the elements which have appeared "only" once.
        int twos = 0 ; //At any point of time, this variable holds XOR of all the elements which have appeared "only" twice.
        int not_threes ;

        for( int x : a )
        {
            twos |= ones & x ; //add it to twos if it exists in ones
            ones ^= x ; //if it exists in ones, remove it, otherwise, add it

            // Next 3 lines of code just converts the common 1's between "ones" and "twos" to zero.

            not_threes = ~(ones & twos) ;//if x is in ones and twos, dont add it to Threes.
            ones &= not_threes ;//remove x from ones
            twos &= not_threes ;//remove x from twos
        }
        return ones;
    }

    public int singleNumber_III_2(int[] A) {
        int ones = 0, twos = 0;
        for (int i = 0; i < A.length; i++) {
            ones = (ones ^ A[i]) & ~twos;
            twos = (twos ^ A[i]) & ~ones;
        }
        return ones;
    }

    /**
     * Given an array of N integers, find the pair of integers in the array which have
     * minimum XOR value. Report the minimum XOR value.
     * @param A
     * @return
     */
    public int findMinXor(ArrayList<Integer> A) {
        int min= Integer.MAX_VALUE;
        int val=0;
        Collections.sort(A);
        for(int i=0; i<A.size()-1; i++){
            val = A.get(i)^A.get(i+1);
            min = Math.min(min,val);
        }
        return min;
    }

    /***
     * Rearrange a given array so that Arr[i] becomes Arr[Arr[i]] with O(1) extra space.
     * @param a
     */
    public void arrange(ArrayList<Integer> a) {
        ArrayList<Integer> b = new ArrayList<Integer>();
        for(int i=0;i<a.size();i++){
            b.add(a.get(i));
        }
        a.clear();
        for(int i=0;i<b.size();i++){
            a.add(i,b.get(b.get(i)));
        }
    }


    /***
     * Given a column title as appears in an Excel sheet, return its corresponding column number.
     * @param s
     * @return
     */
    public int titleToNumber(String s) {
        if(s==null || s.length() == 0) throw new IllegalArgumentException("Input is not valid!");
        int result = 0;
        int i = s.length()-1;
        int t = 0;
        while(i >= 0){
            char curr = s.charAt(i);
            result = result + (int) Math.pow(26, t) * (curr-'A'+1);
            t++;
            i--;
        }
        return result;
    }


    /***
     * mplement pow(x, n) % d.
     * @param x 
     * @param n
     * @param p
     * @return
     */
    public int pow(int x, int n, int p) {
        if (n == 0) return 1 % p;

        long ans = 1, base = x;

        while (n > 0) {
            // We need (base ** n) % p.
            // Now there are 2 cases.
            // 1) n is even. Then we can make base = base^2 and n = n / 2.
            // 2) n is odd. So we need base * base^(n-1)
            if (n % 2 == 1) {
                ans = (ans * base) % p;
                n--;
            }
            else {
                base = (base * base) % p;
                n /= 2;
            }
        }

        if (ans < 0) ans = (ans + p) % p;
        return (int) ans;
    }


    /////////////////////////////////////////////////////////////////////////////////////////////////////
    //      Strings

    /**
     * Given a string, determine if it is a palindrome, considering only alphanumeric characters
     * and ignoring cases
     * @param a
     * @return
     */
    public int isPalindrome(String a) {
        if(a == null||a.length() == 0) return 0;
        a = a.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();     //REMEMBER !!!!
        int length = a.length();
        for (int i=0;i<length;i++){
            if( a.charAt(i) != a.charAt(length-i-1)) return 0;

        }
        return 1;
    }

    /**
     *  Longest Palindromic Substring
     *
     * @param s
     * @return
     */
    public String longestPalindromeSubstring(String s) {
        int start = 0, end = 0;
        for (int i = 0; i < s.length(); i++) {
            int len1 = expandAroundCenter(s, i, i);
            int len2 = expandAroundCenter(s, i, i + 1);
            int len = Math.max(len1, len2);
            if (len > end - start) {
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }
        return s.substring(start, end + 1);
    }
    private int expandAroundCenter(String s, int left, int right) {
        int L = left, R = right;
        while (L >= 0 && R < s.length()-1 && s.charAt(L) == s.charAt(R)) {
            L--;
            R++;
        }
        return R - L - 1;
    }

    public String longestPalindrome(String a) {
        int n= a.length();
        boolean[][] dp = new boolean[n][n];
        int maxLength=1;
        int startIndex=0;
        for(int i=0;i<n;i++){
            dp[i][i]=true;
        }
        for(int i=0;i<n-1;i++){
            if(a.charAt(i)==a.charAt(i+1)){
                dp[i][i+1]=true;
                maxLength=2;
                startIndex=i;
            }
        }

        for(int k=3;k<=n;k++){
            for(int i=0;i<n-k+1;i++){
                int j=i+k-1;
                if(a.charAt(i)==a.charAt(j) && dp[i+1][j-1]){
                    dp[i][j]=true;
                    if(k>maxLength){
                        maxLength=k;
                        startIndex=i;
                    }
                }
            }
        }

        return a.substring(startIndex,startIndex+maxLength);
    }


    /***
     * strstr - locate a substring ( needle ) in a string ( haystack
     * @param haystack
     * @param needle
     * @return
     */
    public int strStr(final String haystack, final String needle) {
        int lengthNee = needle.length();
        char firstChar = needle.charAt(0);
        int lengthHay = haystack.length();
        if(lengthNee==0 || lengthHay==0) return -1;
        for(int i=0; i<lengthHay-lengthNee+1; i++){
            if(haystack.charAt(i)==firstChar){
                if(needle.equals( haystack.substring( i,i+lengthNee ))){
                    return i;
                }
            }
        }
        return -1;
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////


    /**
     * Given an integer n, return the number of trailing zeroes in n!.
     * @param n
     * @return
     */
    public int trailingZeroes_(int n) {
        int count = 0;
        while(n > 0){
            n /= 5;
            count += n;
        }
        return count;
    }

    public int trailingZeroes(int n) {
        return (n==0) ? 0 : n/5+trailingZeroes(n/5);
    }
}
