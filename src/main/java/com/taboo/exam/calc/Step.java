package com.taboo.exam.calc;

public class Step {
    private String input;
    private String rhs;
    private String lhs;
    private String evaluatedRHS;


    public Step(String input) {
        this.input = input;
        String[] split = input.split(" = ");
        this.lhs = split[0];
        this.rhs = split[1];
    }

    public String getInput() {
        return input;
    }

    public String getLeftHandSide() {
        return lhs;
    }

    public String getRightHandSide() {
        return rhs;
    }

    public void setRhs(String rhs) {
        this.rhs = rhs;
    }

    public void setLhs(String lhs) {
        this.lhs = lhs;
    }

    public boolean isStepEvaluated() {
        try {
            int i = Integer.parseInt(getRightHandSide());
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }
}
