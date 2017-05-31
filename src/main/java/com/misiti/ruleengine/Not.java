package com.misiti.ruleengine;

import java.text.ParseException;
import java.util.List;
import java.util.Map;
import java.util.Stack;

/**
 * Created by EZDI\haresh.p on 22/5/17.
 */

public class Not extends Operation
{    
    public Not()
    {
        super("NOT");
    }

    public Not copy()
    {
        return new Not();
    }

    @Override
    public int parse(List<String> tokens, int pos, Stack<Expression> stack) throws ParseException {
        int i = findNextExpression(tokens, pos+1, stack);
        Expression right = stack.pop();

        this.rightOperand = right;
        stack.push(this);

        return i;
    }

    @Override
    public boolean interpret(final Map<String, ?> bindings)
    {
        return !this.rightOperand.interpret(bindings);
    }    
}
