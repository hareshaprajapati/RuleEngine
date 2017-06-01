package com.misiti.ruleengine;

import java.text.ParseException;
import java.util.List;
import java.util.Stack;

/**
 * Created by EZDI\haresh.p on 22/5/17.
 */

public abstract class Operation implements Expression {
    protected String symbol;

    protected Expression leftOperand = null;
    protected Expression rightOperand = null;

    public Operation(String symbol) {
        this.symbol = symbol;
    }

    public abstract Operation copy();

    public String getSymbol() {
        return this.symbol;
    }

    public abstract int parse(final List<String> tokens, final int pos, final Stack<Expression> stack) throws ParseException;

    protected int findNextExpression(List<String> tokens, int pos, Stack<Expression> stack) throws ParseException {
        Operations operations = Operations.INSTANCE;

        for (int i = pos; i < tokens.size(); i++) {
            Operation op = operations.getOperation(tokens.get(i));
            if (op != null) {
                op = op.copy();
                i = op.parse(tokens, i, stack);

                return i;
            }
        }
        return -1;
    }
}