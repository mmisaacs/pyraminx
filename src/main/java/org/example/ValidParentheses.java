package org.example;
import java.util.*;

public class ValidParentheses {
    public boolean isValid(String s){
        //checks if provided string is empty, throws error if empty
        if(s.isEmpty()) {
            throw new IllegalArgumentException("Error: empty string.");
        }

        //create a stack to hold parentheses for later verification
        Stack<Character> stack = new Stack<>();

        //turns string into char array to parse through easier
        for(char c : s.toCharArray()){
            //if left parenthesis is read, a right one is added to stack
            if(c == '('){
                stack.push(')');
            }
            else if (c == '{'){
                stack.push('}');
            }
            else if (c == '['){
                stack.push(']');
            }
            //case c = ] or ) or }:
                // checks if stack is empty, returns false (no matching left)
            else if (stack.isEmpty() || stack.pop() != c){
                return false;
            }
        }

        //will return true if is empty (all left parentheses have matching right, stack is empty)
        return stack.isEmpty();
    }
}
