package com.taboo.exam.calc;

import org.junit.Test;

import static org.junit.Assert.*;

public class JavaExpressionTest {
    String[] inputs = new String[]{
            "i=0",
            "i = 0",
            "j=++i",
            "j = ++i",
            "x=j++ + 5",
            "x = j++ + 5",
            "m+=5",
            "m += 5"};
    JavaExpression expression0 = null;
    JavaExpression expression1 = null;
    JavaExpression expression2 = null;
    JavaExpression expression3 = null;
    JavaExpression expression4 = null;
    JavaExpression expression5 = null;

    public JavaExpressionTest() throws JavaExpression.NotJavaExpressionException {
    }

    @Test
    public void constructorTests() throws JavaExpression.NotJavaExpressionException {
        int failCount = 0;

        try {
            JavaExpression expression = new JavaExpression(inputs[0]);
        }  catch (JavaExpression.NotJavaExpressionException e) {
            System.out.println("expression [" + inputs[0] + "] is not a Java expression");
            failCount++;
        }
        try {
            JavaExpression expression1 = new JavaExpression(inputs[1]);
        }  catch (JavaExpression.NotJavaExpressionException e) {
            System.out.println("expression [" + inputs[1] + "] is not a Java expression");
            failCount++;
        }
        try {
            JavaExpression expression2 = new JavaExpression(inputs[2]);
            System.out.println("expression [" + inputs[2] + "] is a Java expression");
        }  catch (JavaExpression.NotJavaExpressionException e) {
            throw e;
        }
        try {
            JavaExpression expression3 = new JavaExpression(inputs[3]);
            System.out.println("expression [" + inputs[3] + "] is a Java expression");
        }  catch (JavaExpression.NotJavaExpressionException e) {
            throw e;
        }
        try {
            JavaExpression expression4 = new JavaExpression(inputs[4]);
            System.out.println("expression [" + inputs[4] + "] is a Java expression");
        }  catch (JavaExpression.NotJavaExpressionException e) {
            throw e;
        }
        try {
            JavaExpression expression5 = new JavaExpression(inputs[5]);
            System.out.println("expression [" + inputs[5] + "] is a Java expression");
        }  catch (JavaExpression.NotJavaExpressionException e) {
            throw e;
        }
            assertEquals(2, failCount);
        }


        @Test
        public void getRightHandSide () throws Exception {

            try {
                expression2 = new JavaExpression(inputs[2]);
                expression3 = new JavaExpression(inputs[2]);
                expression3 = new JavaExpression(inputs[3]);
                expression4 = new JavaExpression(inputs[4]);
                expression5 = new JavaExpression(inputs[5]);
            } catch (JavaExpression.NotJavaExpressionException e) {
                fail();
            }
            assertEquals("i+1", expression2.getRightHandSide());
            assertEquals(" + 5+1", expression4.getRightHandSide());
            assertEquals(" + 5+1", expression5.getRightHandSide());

            assertEquals("j=", expression2.getLeftHandSide());
            assertEquals("j = ", expression3.getLeftHandSide());
            assertEquals("x=j", expression4.getLeftHandSide());
            assertEquals("x = j", expression5.getLeftHandSide());
        }

        @Test
        public void calculate () throws Exception {
        }

    }