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

    public static boolean validateInput(String input) {
        if (!input.contains(" = ")){
            return false;
        }

        if (input.matches("^[a-z]\\s=\\s.*$")){
            String[] split = input.split(" = ");
            assert (split[0].matches("^[a-z]$"));
            if (split.length == 2){
                return true;
            }
        }
        return false;
    }
}
