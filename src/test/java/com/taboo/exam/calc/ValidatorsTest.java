package com.taboo.exam.calc;

import org.junit.Test;

import static com.taboo.exam.calc.Validators.isJavaExpression;
import static org.junit.Assert.*;

public class ValidatorsTest {
    //todo: add -= *= /= tests
    String[] inputs = new String[]{"i=0","i = 0","j=++i","j = ++i","x=j++ + 5","x = j++ + 5","m+=5","m += 5"};

    @Test
    public void isValidInputTest() throws Exception, JavaExpression.NotJavaExpressionException {
        for (String input : inputs) {
            System.out.println("Input [" + input + "] " + (Validators.isValidInput(input) ? "is valid" : "ain't valid"));
        }
    }

    @Test
    public void isJavaExpressionTest() throws Exception {
        assertFalse(isJavaExpression(inputs[0]));
        assertFalse(isJavaExpression(inputs[1]));
        assertTrue(isJavaExpression(inputs[2]));
        assertTrue(isJavaExpression(inputs[3]));
        assertTrue(isJavaExpression(inputs[4]));
        assertTrue(isJavaExpression(inputs[5]));
        assertTrue(isJavaExpression(inputs[6]));
        assertTrue(isJavaExpression(inputs[7]));
    }

}