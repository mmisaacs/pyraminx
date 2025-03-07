package org.example;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ValidParenthesesTest {
    @Test
    public void testIsValid(){
        ValidParentheses vp = new ValidParentheses();
        assertTrue(vp.isValid("()"));
        assertTrue(vp.isValid("()[]{}"));
        assertFalse(vp.isValid("(]"));
        assertFalse(vp.isValid("([)]"));
    }

    //verifies that empty strings are invalid
    //if error is thrown, test case passes
    @Test
    public void TestIsValid2(){
        ValidParentheses vp = new ValidParentheses();
        assertThrows(IllegalArgumentException.class, () -> {vp.isValid("");});
    }

    @Test
    public void TestIsValid3(){
        ValidParentheses vp = new ValidParentheses();
        //long input?
        assertFalse(vp.isValid("(({({({({({({(({({({({({({({})})})})}))})"));
        assertTrue(vp.isValid("((((({({})})))))"));
        //checking when only a right brace is given
        assertFalse(vp.isValid("]"));
    }
}