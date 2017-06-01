package com.misiti.ruleengine;

import java.text.ParseException;
import java.util.List;
import java.util.Map;
import java.util.Stack;

/**
 * Created by EZDI\haresh.p on 22/5/17.
 */

public class OpeningBracket extends Operation {
    public OpeningBracket() {
        super("(");
    }

    public OpeningBracket copy() {
        return new OpeningBracket();
    }

    @Override
    public int parse(List<String> tokens, int pos, Stack<Expression> stack) throws ParseException {
        int i = findNextExpression(tokens, pos + 1, stack);
        Expression left  = null ;
        i = findNextExpression(tokens, i , stack);
        Expression right = stack.pop();
        this.leftOperand = left;
        this.rightOperand = right;

        stack.push(this);

        return i;
    }

    @Override
    public boolean interpret(Map<String, ?> bindings) {
        return this.rightOperand.interpret(bindings);
    }
}