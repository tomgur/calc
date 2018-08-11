package com.taboo.exam.calc;

import org.junit.Test;

import static com.taboo.exam.calc.Validators.isJavaExpression;
import static com.taboo.exam.calc.Validators.validateInput;
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

    @Test
    public void validateInputTest() throws Exception {
            assertEquals(false,validateInput(inputs[0]));
            assertEquals(true,validateInput(inputs[1]));
            assertEquals(false,validateInput(inputs[2]));
            assertEquals(true,validateInput(inputs[3]));
            assertEquals(false,validateInput(inputs[4]));
            assertEquals(true,validateInput(inputs[5]));
            assertEquals(false,validateInput(inputs[6]));
            assertEquals(false,validateInput(inputs[7]));
    }
}