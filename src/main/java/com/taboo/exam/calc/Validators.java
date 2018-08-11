package com.taboo.exam.calc;

import java.util.Arrays;

public class Validators {
    public static boolean isValidInput(String input) throws JavaExpression.NotJavaExpressionException {
        if (isJavaExpression(input)){
            JavaExpression expression = new JavaExpression(input);
            return true;
        }
        String[] parts = input.split("=");
        System.out.println("isValidInput debug: parts " + Arrays.toString(parts));
        return parts.length==2;
    }

    public static boolean isJavaExpression(String s) {
        return  (s.contains("++") ||
                s.contains("+=") ||
                s.contains("*=") ||
                s.contains("/="));
    }
}
