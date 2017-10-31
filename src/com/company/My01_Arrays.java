package com.company;

import java.util.ArrayList;
import java.util.List;

public class My01_Arrays {

////////////////////////////////////////////////////////////////////////////////////////
    //01. rotateArray
    //02. spiralOrder

    // 1. minStepsToCoverPointsOnGrid
    // 2. plusOne

    //  . rotate

    /**
     * rotate the array A by B positions.
     * @param A
     * @param B
     * @return
     */
    public ArrayList<Integer> rotateArray(ArrayList<Integer> A, int B) {
        ArrayList<Integer> ret = new ArrayList<Integer>();
        for (int i = 0; i < A.size(); i++) {
            ret.add( A.get( (i+B)%A.size() ) );
        }
        return ret;
    }

    /**
     * Given a matrix of m*n elements (m rows, n columns),
     * return all elements of the matrix in spiral order.
     * @param A
     * @return
     */
    public ArrayList<Integer> spiralOrder(final List<ArrayList<Integer>> A) {
        ArrayList<Integer> result = new ArrayList<Integer>();
        // Populate result;
        int m, n;
        m = A.size();           //rows
        if (m == 0) return result;
        n = A.get(0).size();    //columns

        int dir = 0;                        // right
        int row, col, layer;
        row = col = layer = 0;

        result.add( A.get(0).get(0) );
        for (int step = 1; step < m*n; step++) {
            switch(dir) {
                case 0:                     // Go right
                    if (col == n - layer - 1) {
                        dir = 1;
                        row++;
                    } else { col++; }
                    break;
                case 1:                     // Go down
                    if (row == m - layer - 1) {
                        dir = 2;
                        col--;
                    } else { row++; }
                    break;
                case 2:                     // Go left
                    if (col == layer) {
                        dir = 3;
                        row--;
                    } else { col--; }
                    break;
                case 3:                     // Go up
                    if (row == layer + 1) {
                        dir = 0;
                        col++;
                        layer++;
                    } else { row--; }
                    break;
            }
            result.add(A.get(row).get(col));
        }
        return result;
    }




    /**
     * You are in an infinite 2D grid where you can move in any of the 8 directions : 1 unit
     * You are given a sequence of points and the order in which you need to cover the points. Give the
     * minimum number of steps in which you can achieve it. You start from the first point.
     *
     * @param X - co-ordinates of the points in order.
     * @param Y - co-ordinates of the points in order.
     * @return
     */
    public int minStepsToCoverPointsOnGrid(ArrayList<Integer> X, ArrayList<Integer> Y) {
        int numSteps = 0;
        for(int i = 1; i < X.size(); i++){
            numSteps += Math.max( Math.abs( X.get(i)-X.get( i-1) ), Math.abs( Y.get(i)-Y.get(i-1) ) );
        }
        return numSteps;
    }

    /**
     * Given a non-negative number represented as an array of digits,
     * add 1 to the number ( increment the number represented by the digits ).
     * The digits are stored such that the most significant digit is at the head of the list.
     *
     * @param A
     * @return
     */
    public ArrayList<Integer> plusOne(ArrayList<Integer> A) {
        int carry = 1;  // adding 1 to LSD
        int num;
        for (int i=A.size()-1; i>=0; i--) {
            num = A.get(i);
            num += carry;
            carry = 0;
            if (num == 10) {
                num = 0;
                carry = 1;
            }
            A.set( i,num);
        }
        ArrayList<Integer> res = new ArrayList<Integer>();      // to handle carry after MSB
        if (carry == 1) res.add(1);
        for (int x : A) {
            if (x==0 && res.size()==0) continue;          //to avoid over-writing to first element and deleting first zero number in place of MSB
            res.add(x);
        }
        return res;
    }


    /**
     * ou are given an n x n 2D matrix representing an image.
     * Rotate the image by 90 degrees (clockwise).
     * You need to do this in place.
     * @param a
     */
    public void rotate(ArrayList< ArrayList<Integer>> a) {
        int S = a.size() - 1;
        for(int i = 0 ; i < a.size() / 2 ; i++){
            for(int j = i; j < S - i; ++j){
                int temp1 = a.get(S-j).get(i);
                int temp2 = a.get(S-i).get(S-j);
                int temp3 = a.get(j).get(S-i);
                int temp4 = a.get(i).get(j);
                // swap
                a.get(i).set(j,temp1);
                a.get(S-j).set(i,temp2);
                a.get(S-i).set(S-j,temp3);
                a.get(j).set(S-i,temp4);
            }
        }
    }
//check this code
    public void rotate_(ArrayList< ArrayList<Integer>> a) {
        int l = a.size();
        for(int i=l-1; i>=0; i--){
            for(int j=0; j<l; j++){
                a.get(j).add(a.get(i).get(0));
                a.get(i).remove(0);
            }
        }
    }
//check this code
    public void rotate__(ArrayList<ArrayList<Integer>> a) {
        ArrayList<Integer> l = new ArrayList<Integer>();
        l = a.get(0);
        int x = l.size();
        int[][] arr = new int[ a.size()][x];

        for(int i=0;i<a.size();i++){
            l = a.get(i);
            for(int j=0;j<x;j++){
                arr[i][j] = l.get(j);
            }
            l = new ArrayList<Integer>();
        }

        l = new ArrayList<Integer>();

        int[][] result = new int[x][a.size()];
        for(int i=0;i<a.size();i++){

            for(int j=0;j<x;j++){
                result[j][a.size()-1-i] = arr[i][j];

            }

        }

        ArrayList<ArrayList<Integer>> main = new ArrayList<ArrayList<Integer>>();
        String s="";
        for(int i=0;i<a.size();i++){
            s="[";
            for(int j=0;j<x;j++){
                s = s+(result[i][j])+" ";
            }
            s=s+"] ";
            System.out.print(s);
            s="";
        }
        a.clear();
    }




}
