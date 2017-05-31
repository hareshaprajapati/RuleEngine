package com.misiti.ruleengine;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by EZDI\haresh.p on 22/5/17.
 */

public class ExpressionParser {
    private static final Operations operations = Operations.INSTANCE;

    public static Expression fromString(String expr) throws ParseException {
        Stack<Expression> stack = new Stack<>();

        List<String> tokens = new ArrayList<>();
        Matcher m = Pattern.compile("([^']\\S*|'.+?')\\s*").matcher(expr);
        int j = 0;
        while (m.find()) {
            tokens.add(m.group(1));
            j++;
        }
        int k = 0;
        int inPos = 0;
        String newExpr = "";
        while (k < tokens.size()) {
            if (tokens.get(k).equals("IN")) {
                boolean isNotIn = false;
                if (tokens.get(k - 2).equals("NOT")) {
                    isNotIn = true;
                    newExpr = newExpr.substring(0, newExpr.lastIndexOf(" "));
                    String before = newExpr.substring(0,newExpr.lastIndexOf(" ")) + " ";
                    String substring = newExpr.substring(newExpr.lastIndexOf(" "));
                    newExpr = before + " ( NOT " + substring + " ";

                }else{
                        newExpr = newExpr.substring(0, newExpr.lastIndexOf(" "));
                        String before = newExpr.substring(0,newExpr.lastIndexOf(" ")) + " ";
                        String substring = newExpr.substring(newExpr.lastIndexOf(" ")) + " ";
                        newExpr = before + " ( " + substring + " ";

                }
                String in = "";
                inPos = k;
                k += 2;

                while (k < tokens.size()) {
                    if (tokens.get(k).equals(")")) {
                        break;
                    }
                    in += tokens.get(k++);
                }
                String[] split = in.split(",");
                int splitLength = split.length;
                String beforeIn = tokens.get(inPos - 1);
                List<String> beforeInTemp = new ArrayList<>();
                int beforeInTempInt = 0;
                for (String s : split) {
                    if (isNotIn) {
                        if (beforeInTempInt == 0) {
                            if (beforeInTempInt != splitLength - 1) {
                                beforeInTemp.add(" = " + s + " AND NOT ");
                            } else {
                                beforeInTemp.add(" = " + s + " ");
                            }

                        } else if (beforeInTempInt != splitLength - 1) {
                            beforeInTemp.add(beforeIn + " = " + s + " AND NOT ");
                        } else {
                            beforeInTemp.add(beforeIn + " = " + s + " ");
                        }
                    } else {
                        if (beforeInTempInt == 0) {
                            if (beforeInTempInt != splitLength - 1) {
                                beforeInTemp.add(" = " + s + " OR ");
                            } else {
                                beforeInTemp.add(" = " + s + " ");
                            }

                        } else if (beforeInTempInt != splitLength - 1) {
                            beforeInTemp.add(beforeIn + " = " + s + " OR ");
                        } else {
                            beforeInTemp.add(beforeIn + " = " + s + " ");
                        }
                    }

                    beforeInTempInt++;
                }
                String orString = "";
                for (String s : beforeInTemp) {
                    orString += s;
                }
                    newExpr +=   orString + " " + " ) ";
            } else {
                if (!"NOT".equals(tokens.get(k)) || ("NOT".equals(tokens.get(k)) && !"IN".equals(tokens.get(k + 2)))) {
                    newExpr += tokens.get(k) + " ";
                }
            }
            k++;
        }
        tokens = new ArrayList<>();
        m = Pattern.compile("([^']\\S*|'.+?')\\s*").matcher(newExpr);
        j = 0;
        while (m.find()) {
            tokens.add(m.group(1));
            j++;
        }

        for (int i = 0; i < tokens.size(); i++) {
            Operation op = operations.getOperation(tokens.get(i));
            if (op != null) {
                op = op.copy();
                i = op.parse(tokens, i, stack);
                if (i == -1) {
                    break;
                }
            }
        }
        return stack.pop();
    }
}
