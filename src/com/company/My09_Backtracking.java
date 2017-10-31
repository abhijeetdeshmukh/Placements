package com.company;

public class My09_Backtracking {

    ///////////////////////////////////////////////////////////////////////////////////////////////
    //01. factorial
    //02. Fibbonachhi


    private int factorial(int n){
        if( n==0) return 1;             //Base condition  or termination condition
        return n*factorial(n-1);
    }

    private int fibonachhi( int n){        //took very long time to calculate
        if( n==0 ) return 0;
        if( n==1 ) return 1;
        return fibonachhi(n-1)+fibonachhi(n-2);
    }

    /**
     * Implement pow(A, B) % C.
     * In other words, given A, B and C
     * @param a
     * @param b
     * @param c
     * @return
     */
    public int Mod(int a, int b, int c) {
        if(a == 0){ return 0; }
        if(b == 0){ return 1; }
        long  y = 0;
        if (b % 2 == 0) {
            y = Mod( a,b/2, c);
            y = (y*y)%c;
        } else {
            y = a%c;
            y = ( y*Mod( a,b-1,c)) % c;
        }
        // Incase y is negative.
        return (int)((y + c) % c);
    }
}
