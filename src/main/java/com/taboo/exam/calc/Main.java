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

        //todo: check that all operations are space delimited
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
                print(step.getLHS() + "=" + step.getRHS(), true);
                print("------------------------", true);
                continue;
            }

            String leftHandSide = step.getLHS();
            String rightHandSide = step.getRHS();

            if (isEquation(rightHandSide)) {
                // string containing [+-*/] but not [++]
                String simplifiedRightHandSide = simplify(rightHandSide);
                if (isNumber(simplifiedRightHandSide)) {
                    properties.setProperty(leftHandSide, simplifiedRightHandSide);
                    print(leftHandSide + "=" + simplifiedRightHandSide, true);
                //todo: recurse - there may be more parts!!!
                } else {
                    System.err.println("ERROR - element is not a number.....");
                }
            }

            if (isJavaExpression(rightHandSide)) {
                //string containing [++]
                int i = evaluateJava(rightHandSide);
                print(leftHandSide + "=" + i, true);
                properties.setProperty(leftHandSide, String.valueOf(i));
            }
            print(properties.toString(), true);
            print("------------------------", true);
        }
        print("Calculation Ended", true);
    }

    static int evaluateJava(String value) {
        int result = 0;
        if (value.contains(" ")) {
            String[] elements = value.split(" ");
            for (int i = 0; i < elements.length; i++) {
                String element = elements[i];
                if (isNumber(element)) {
                    result += Integer.parseInt(element);
                    continue;
                }
                if (isOperator(element)) {
                    if (element.equals("+")) {
                        result += Integer.parseInt(elements[i + 1]);
                        continue;
                    }
                }
                if (isJavaExpression(element)) {
                    int i1 = evaluateJava(element);
                    String replace = value.replace(element, String.valueOf(i1));
                    List<String> strings = listParts(replace);
                    if (isSimpleEquation(strings)) {
                        return Integer.parseInt(solveSimpleEquation(strings));
                    } else {
                        //todo: implement recursion
                    }


                }
            }
        }

        if (value.contains("++")) {
            result = doIncrement(value, result);
        } else {
            throw new UnsopportedOperationException("+=");
        }
        return result;
    }

    private static String getVarValue(String s) {
        String property = properties.getProperty(s);
        if (property.equals("")) {
            return null;
        }
        return property;
    }

    private static int doIncrement(String value, int result) {
        String[] elements = value.split("\\+{2}");
        String propKey = null;
        int propVal = 0;

        if (value.startsWith("++")) {
            // pre-increment
            propKey = elements[1];
            propVal = Integer.parseInt(properties.getProperty(propKey));
            result = ++propVal;
        } else if (value.endsWith("++")) {
            propKey = elements[0];
            propVal = Integer.parseInt(properties.getProperty(propKey));
            result = propVal++;
        }
        properties.setProperty(propKey, String.valueOf(propVal));
        print(propKey + "=" + properties.getProperty(propKey), true);
        return result;
    }
}
