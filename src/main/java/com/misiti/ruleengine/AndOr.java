package com.misiti.ruleengine;

import java.text.ParseException;
import java.util.List;
import java.util.Stack;

/**
 * Created by EZDI\haresh.p on 22/5/17.
 */

public abstract class AndOr extends Operation
{
    public AndOr(String symbol)
    {
        super(symbol);
    }

    @Override
    public int parse(List<String> tokens, int pos, Stack<Expression> stack) throws ParseException {
        Expression left = stack.pop();
        int i = findNextExpression(tokens, pos+1, stack);
        Expression right = stack.pop();

        this.leftOperand = left;
        this.rightOperand = right;

        stack.push(this);

        return i;
    }
}