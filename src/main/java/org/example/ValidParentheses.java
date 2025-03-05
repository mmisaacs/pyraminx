package org.example;
import java.util.*;

public class ValidParentheses {
    public boolean isValid(String s){
        if(s.isEmpty()) {
            throw new IllegalArgumentException("Error: empty string.");
        }

        Stack<Character> stack = new Stack<>();
        for(char c : s.toCharArray()){
            if(c == '('){
                stack.push(')');
            }
            else if (c == '{'){
                stack.push('}');
            }
            else if (c == '['){
                stack.push(']');
            }
            else if (stack.isEmpty() || stack.pop() != c){
                return false;
            }
        }

        return stack.isEmpty();
    }
}
