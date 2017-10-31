package com.company;

import java.util.ArrayList;

public class My04_Strings {


    /**
     * Given a string s consists of upper/lower-case alphabets and empty space characters ' ',
     * return the length of last word in the string. If the last word does not exist, return 0.
     * @param a
     * @return
     */
    public int lengthOfLastWord(final String a) {
        String []x = a.split(" ");
        if(x.length==0) return 0;
        else
            return x[x.length-1].length();
    }

    public int lengthOfLastWord_(final String a) {
        boolean char_flag = false;
        int len = 0;
        for (int i=a.length()-1; i>=0; i--) {               // Once the first chracter from last
            if ( Character.isLetter(a.charAt(i)) ) {        // is encountered, set char_flag to true.
                char_flag = true;
                len++;
            } else {                             // When the first space after the characters
                if (char_flag == true)          // (from the last) is encountered, return the
                    return len;                 // length of the last word
            }
        }
        return len;
    }

    public int lengthOfLastWord__(final String s) {
        if(s==null || s.length() == 0) return 0;

        int result = 0;
        int len = s.length();

        boolean flag = false;
        for(int i=len-1; i>=0; i--){
            char c = s.charAt(i);
            if ((c>='a' && c<='z') || (c>='A' && c<='Z')){  //
                flag = true;
                result++;
            } else {
                if(flag) return result;
            }
        }
        return result;
    }


    /**
     *Find if Given number is power of 2 or not.
     *
     * @param A number length can be more than 64, which mean number can be greater than
     *          2 ^ 64 (out of long long range)
     * @return return 1 if the number is a power of 2 else return 0
     */
    public int power(String A) {
        java.math.BigInteger a = new java.math.BigInteger(A);
        if( a.compareTo( java.math.BigInteger.ONE)==0 || a.compareTo( java.math.BigInteger.ZERO)==0) return 0;
        for(int i=0;i<a.bitLength()-1;i++){
            if(a.testBit(i))return 0;
        }
        return a.testBit(a.bitLength()-1)?1:0;
    }

    public int power_(String A) {
        String dividend = A;
        StringBuilder str;

        if (A == null || A.length() == 0) return 0;

        if (A.length() == 1 && A.charAt(0) == '0') return 0;

        while (dividend.length() > 0 && !dividend.equalsIgnoreCase("2")) {
            str = new StringBuilder();
            int carry = 0;
            int n = dividend.length();

            if (n > 0) {
                int num = dividend.charAt(n - 1) - '0';
                if (num % 2 == 1)
                    return 0;
            }

            for (int i = 0; i < n; i++) {

                char c = (char) (dividend.charAt(i) - '0');
                int res = c + 10 * carry;

                c = (char) (res / 2 + '0');
                carry = res % 2;

                str.append(c);
            }

            if (str.charAt(0) == '0')
                str.deleteCharAt(0);

            dividend = str.toString();
        }

        return 1;
    }

    /**
     *
     * @param s
     * @return
     */
    public ArrayList<String> restoreIpAddresses(String s) {

        ArrayList< ArrayList< String>> result = new ArrayList< ArrayList<String>>();
        ArrayList<String> t = new ArrayList<String>();
        dfs(result, s, 0, t);

        ArrayList<String> finalResult = new ArrayList<String>();

        for(ArrayList<String> l: result){
            StringBuilder sb = new StringBuilder();
            for(String str: l){
                sb.append(str+".");
            }
            sb.setLength(sb.length() - 1);
            finalResult.add(sb.toString());
        }

        return finalResult;

    }
    public void dfs( ArrayList<ArrayList<String>> result, String s, int start, ArrayList<String> t){
        //if already get 4 numbers, but s is not consumed, return
        if(t.size()>=4 && start!=s.length())
            return;

        //make sure t's size + remaining string's length >=4
        if((t.size()+s.length()-start+1)<4)
            return;

        //t's size is 4 and no remaining part that is not consumed.
        if(t.size()==4 && start==s.length()){
            ArrayList<String> temp = new ArrayList<String>(t);
            result.add(temp);
            return;
        }

        for(int i=1; i<=3; i++){
            //make sure the index is within the boundary
            if(start+i>s.length())
                break;

            String sub = s.substring(start, start+i);
            //handle case like 001. i.e., if length > 1 and first char is 0, ignore the case.
            if(i>1 && s.charAt(start)=='0'){
                break;
            }

            //make sure each number <= 255
            if(Integer.valueOf(sub)>255)
                break;

            t.add(sub);
            dfs(result, s, start+i, t);
            t.remove(t.size()-1);
        }
    }


    /**
     * Pretty print a json object using proper indentation.
     * - Every inner brace should increase one indentation to the following lines.
     * - Every close brace should decrease one indentation to the same line and the following lines.
     * - The indents can be increased with an additional ‘\t’
     * @param a
     * @return
     */
    public ArrayList<String> prettyJSON(String a) {
        ArrayList<String> result = new ArrayList<String>();
        int tabCounter = 0;
        StringBuffer current = new StringBuffer();
        for(int i=0; i<a.length(); i++) {
            char c = a.charAt(i);
            switch(c) {
                case ('{'):
                case ('['):
                    if(current.length() > 0) {
                        result.add( current.toString());
                        current = new StringBuffer();
                    }
                    for(int j=0; j<tabCounter; j++) {
                        current.append('\t');
                    }
                    current.append(c);
                    result.add(current.toString());
                    current = new StringBuffer();
                    tabCounter++;
                    break;

                case ']':
                case '}':
                    if(current.length() > 0) {
                        result.add(current.toString());
                        current = new StringBuffer();
                    }
                    tabCounter--;
                    for(int j=0; j<tabCounter; j++) {
                        current.append('\t');
                    }
                    current.append(c);
                    break;

                case ',':
                    current.append(c);
                    result.add(current.toString());
                    current = new StringBuffer();
                    break;

                default:
                    if(current.length() == 0) {
                        for(int j=0; j<tabCounter; j++) {
                            current.append('\t');
                        }
                    }
                    current.append(c);
            }
        }

        if(current.length() > 0) {
            result.add(current.toString());
        }

        return result;

    }

}


