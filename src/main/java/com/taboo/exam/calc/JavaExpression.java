package com.taboo.exam.calc;

import com.taboo.exam.calc.exceptions.UnsopportedOperationException;

import static com.taboo.exam.calc.Validators.isJavaExpression;

public class JavaExpression implements Equation {
    private String input;
    private String rhs;
    private String lhs;
    private String operation;
    private String increment;


    public JavaExpression(String input) throws NotJavaExpressionException {
        this.input = input;
        if (!isJavaExpression(input)) {
            throw new NotJavaExpressionException(input);
        }

        if (input.contains("+=")) {
            operation = "+=";
            String[] split = input.split("\\+=");
            lhs = split[0].trim();
            rhs = split[1].trim();
        }
        if (input.contains("-=")) {
            operation = "-=";
            String[] split = input.split("-=");
            lhs = split[0].trim();
            rhs = split[1].trim();
        }
        if (input.contains("*=")) {
            operation = "*=";
            String[] split = input.split("\\*=");
            lhs = split[0].trim();
            rhs = split[1].trim();
        }
        if (input.contains("/=")) {
            operation = "/=";
            String[] split = input.split("\\*=");
            lhs = split[0].trim();
            rhs = split[1].trim();
        }
        if (input.contains("++")) {
            operation = "++";
            String[] split = input.split("\\+\\+");
            if (split.length == 1) {
                lhs = split[0].trim();
                rhs = operation + "+1";
            }
            if (split.length == 2) {
                lhs = split[0];
                rhs = split[1] + "+1";
            }

        }
        if (input.contains("--")) {
            operation = "--";
            String[] split = input.split("--");
            lhs = split[0].trim();
            rhs = split[1].trim();
        }

    }

    @Override
    public String getRightHandSide() {
        return rhs;
    }

    @Override
    public String getLeftHandSide() {
        return lhs;
    }

    @Override
    public String calculate() throws UnsopportedOperationException {
        String result = null;
        if (operation.equals("+=")) {
            return lhs + " = " + rhs + " + " + lhs;
        }

        if (operation.equals("-=")) {
            return lhs + " = " + rhs + " - " + lhs;
        }

        if (result == null) {
            throw new UnsopportedOperationException(operation);
        }
        return null;
    }

    class NotJavaExpressionException extends Throwable {
        private String messge = "Is not a JAVA Expression";
        private String expression;

        NotJavaExpressionException(String input) {
            expression = input;
        }

        public String getExpression() {
            return expression;
        }

        String getMessge() {
            return messge;
        }
    }
}
