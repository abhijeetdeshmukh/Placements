package com.company;

import java.util.*;

public class My08_StacksQueue {

    /**
     * Given a string S, reverse the string using stack.
     * @param string
     * @return
     */
    public String reverseString(String string) {
        Stack<Character> stack = new Stack<Character>();
        StringBuilder sb= new StringBuilder();
        for(int i=0; i<string.length(); i++)
            stack.add( string.charAt(i) );
        while ( !stack.empty() )
            sb.append( stack.pop() );
        return sb.toString();
    }

    public int isValid_(String A) {

        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < A.length(); i++) {
            char c= A.charAt(i);
            if (c == '(' || c == '{' || c == '[') {
                stack.push(c);
            } else {
                if (stack.isEmpty()) { return 0; }

                char top = stack.peek();
                if ((top == '(' && c == ')') || (top == '{' && c == '}') || (top == '[' && c == ']')) {
                    stack.pop();
                } else return 0;
            }
        }

        if (!stack.isEmpty()) return 0;

        return 1;
    }

    public int isValid(String s) {
        HashMap<Character, Character> map = new HashMap<Character, Character>();
        map.put('(', ')');
        map.put('[', ']');
        map.put('{', '}');

        Stack<Character> stack = new Stack<Character>();

        for (int i = 0; i < s.length(); i++) {
            char curr = s.charAt(i);

            if ( map.keySet().contains(curr)) {
                stack.push(curr);
            } else if ( map.values().contains(curr)) {
                if ( !stack.empty() && map.get(stack.peek()) == curr) {
                    stack.pop();
                } else {
                    return 0;
                }
            }
        }

        return stack.empty()?1:0;
    }


    public String simplifyPath(String path) {

        Stack<String> stack = new Stack<String>();

        //stack.push(path.substring(0,1));

        while(path.length()> 0 && path.charAt(path.length()-1) =='/'){
            path = path.substring(0, path.length()-1);
        }

        int start = 0;
        for(int i=1; i<path.length(); i++){
            if(path.charAt(i) == '/'){
                stack.push(path.substring(start, i));
                start = i;
            }else if(i==path.length()-1){
                stack.push(path.substring(start));
            }
        }

        LinkedList<String> result = new LinkedList<String>();
        int back = 0;
        while(!stack.isEmpty()){
            String top = stack.pop();

            if(top.equals("/.") || top.equals("/")){
                //nothing
            }else if(top.equals("/..")){
                back++;
            }else{
                if(back > 0){
                    back--;
                }else{
                    result.push(top);
                }
            }
        }

        //if empty, return "/"
        if(result.isEmpty()){
            return "/";
        }

        StringBuilder sb = new StringBuilder();
        while(!result.isEmpty()){
            String s = result.pop();
            sb.append(s);
        }

        return sb.toString();
    }

    public String simplifyPath_(String a) {
        return java.nio.file.Paths.get(a).normalize().toString();
    }

    public String simplifyPath__(String a) {

        Stack<String> s = new Stack();

	    /* Characters to be excluded from pushing in the stack */
        HashSet<String> c = new HashSet<>(Arrays.asList("..",".",""));

	    /* Split the input string by / and iterate over each of the substrings */
        for(String dir : a.split("/")){
            if(dir.equals("..") && !s.empty()) s.pop();
            if(!c.contains(dir)) s.push(dir);
        }

	    /* Prepend the elements of stack to the result */
        String res="";
        while(!s.empty()){
            String dir = s.peek();
            res = "/" + dir + res;
            s.pop();
        }

        return res.equals("") ? "/" : res;
    }

    public String simplifyPath___(String a) {

        if (a == null || a.length() == 0) {
            return "/";
        }

        String[] s = a.split("[/]");
        StringBuilder path = new StringBuilder();

        int i = 0;
        String[] stack = new String[s.length];
        for (int j = 0; j < s.length; j++) {
            if (s[j].equals(".") || s[j].equals("")) {
                continue;
            }
            if (s[j].equals("..")) {
                if (i > 0) {
                    i--;
                }
            }else{
                stack[i++] = s[j];
            }
        }
        for (int j = 0; j < i; j++){

            path.append("/" + stack[j]);
        }
        return i == 0 ? "/" : path.toString();
    }

}
