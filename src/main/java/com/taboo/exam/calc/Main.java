package com.taboo.exam.calc;

import com.taboo.exam.calc.exceptions.UnsopportedOperationException;

import java.io.IOException;
import java.util.*;

import static com.taboo.exam.calc.Utils.*;
import static com.taboo.exam.calc.Validators.isJavaExpression;
import static com.taboo.exam.calc.Validators.validateInput;

public class Main {
    private static boolean hasNext = true;
    private static Scanner scanner = new Scanner(System.in);
    private static Properties properties = new Properties();
    private static Queue<Step> steps = new ArrayDeque<>();


    public static void main(String[] args) throws IOException {
        print(getWelcomMessage(), true);
        print(getInstructions(), true);

        while (true) {
            if (hasNext) {
                // add more calculation steps as long as user did not hit enter
                print("Please write an equation:", true);
                addSteps();
            } else {
                //user has not entered any input
                if (areYouSure()) {
                    //user has approved calculation start
                    calculate();
                    break;
                }
                continue;
            }
        }
    }

    private static void addSteps() {
        String s = scanner.nextLine();

        if ("".equals(s)) {
            hasNext = false;
            return;
        }

        //todo: implement java expressions here as well (+= etc.)
        if(!validateInput(s)){
            print("Invalid input [" + s + "]",true);
            print(getInstructions(),true);
            return;
        }
        String[] split = s.split(" = ");

        Step step = new Step(s);
        steps.add(step);
        properties.setProperty(split[0], split[1]);
        print("Stored", true);
        print(properties.toString(), true);
    }

    private static boolean areYouSure() {
        print("Are you sure that's it? (y/N)", true);
        String sure = scanner.nextLine();

        if (sure.equals("y")) {
            return true;
        }
        hasNext = true;
        return false;
    }

    private static void calculate() {
        print("Starting Calculation", true);
        for (Step step : steps) {
            print("Input: " + step.getInput(), true);
            // if the left hand side of the equation is a number
            if (step.isStepEvaluated()) {
                print(step.getLeftHandSide() + "=" + step.getRightHandSide(), true);
                print("------------------------", true);
                continue;
            }

            //todo: then if there are devisions or multiplications --> calculate
            //todo: then split by +
            //todo then split by -
            if (isJavaExpression(step.getRightHandSide())) {
                //string containing [++]
                //todo: WRONG! java expressions --> replace
                String replacedRightHandSide = replaceJava(step.getRightHandSide());
                print(step.getLeftHandSide() + " = " + replacedRightHandSide, true);
                properties.setProperty(step.getLeftHandSide(), replacedRightHandSide);
            }

            if (isEquation(step.getRightHandSide())) {
                // string containing [+-*/] but not [++]
                String simplifiedRightHandSide = simplify(step.getRightHandSide());
                if (isNumber(simplifiedRightHandSide)) {
                    properties.setProperty(step.getLeftHandSide(), simplifiedRightHandSide);
                    print(step.getLeftHandSide() + "=" + simplifiedRightHandSide, true);
                    //todo: recurse - there may be more parts!!!
                } else {
                    System.err.println("ERROR - element is not a number.....");
                }
            }

            print(properties.toString(), true);
            print("------------------------", true);
        }
        print("Calculation Ended", true);
    }

    static String replaceJava(String input) {
        StringBuilder result = new StringBuilder();
        //        int result = 0;
        if (input.contains(" ")) {
            String[] elements = input.split(" ");
            for (int i = 0; i < elements.length; i++) {
                String element = elements[i];
                if (isNumber(element)) {
                    result.append(element);
                    continue;
                }
                if (isOperator(element)) {
                    if (element.equals("+")) {
                        result.append(elements[i + 1]);
                        continue;
                    }
                }
                if (isJavaExpression(element)) {
                    String i1 = replaceJava(element);
                    String replace = input.replace(element, i1);
                    List<String> strings = listParts(replace);
                    if (isSimpleEquation(strings)) {
                        return solveSimpleEquation(strings);
                    } else {
                        //todo: implement recursion
                    }


                }
            }
        }

        if (input.contains("++")) {
            result = new StringBuilder(doIncrement(input));
        } else {
            throw new UnsopportedOperationException("+=");
        }
        return result.toString();
    }

    private static String getVarValue(String s) {
        String property = properties.getProperty(s);
        if (property.equals("")) {
            return null;
        }
        return property;
    }

    private static String doIncrement(String value) {
        String[] elements = value.split("\\+{2}");
        String result = "";
        String propKey = "";
        String propVal = "";

        if (value.startsWith("++")) {
            // pre-increment
            propKey = elements[1];
            propVal = getVarValue(propKey);
            result = "1 + " + propVal;
        } else if (value.endsWith("++")) {
            propKey = elements[0];
            propVal = getVarValue(propKey);
            result = propVal;
        }
        assert propVal != null;
        properties.setProperty(propKey, String.valueOf(Integer.parseInt(propVal)+1));
        print(propKey + "=" + properties.getProperty(propKey), true);
        return result;
    }
}
