package com.misiti.ruleengine;

import java.text.ParseException;
import java.util.List;
import java.util.Map;
import java.util.Stack;

/**
 * Created by EZDI\haresh.p on 22/5/17.
 */

public class ClosingBracket extends Operation
{
    public ClosingBracket()
    {
        super(")");
    }

    public ClosingBracket copy()
    {
        return new ClosingBracket();
    }

    @Override
    public int parse(List<String> tokens, int pos, Stack<Expression> stack) throws ParseException {
        Expression left = null; /// stack.pop();
        int i = findNextExpression(tokens, pos+1, stack);
        if(i == -1){
            return i;
        }
        Expression right = null;
        if(!stack.empty()){
            right = stack.pop();
        }


        this.leftOperand = left;
        this.rightOperand = right;

        stack.push(this);

        return i;
    }

    @Override
    public boolean interpret(Map<String, ?> bindings)
    {
        return this.rightOperand.interpret(bindings);
    }
}