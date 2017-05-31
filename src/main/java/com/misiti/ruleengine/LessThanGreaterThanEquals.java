package com.misiti.ruleengine;

import java.text.ParseException;
import java.util.List;
import java.util.Stack;

/**
 * Created by EZDI\haresh.p on 22/5/17.
 */

public abstract class LessThanGreaterThanEquals extends Operation
{
    public LessThanGreaterThanEquals(String symbol)
    {
        super(symbol);
    }

    @Override
    public int parse(final List<String> tokens, int pos, Stack<Expression> stack) throws ParseException {
        if (pos-1 >= 0 && tokens.size() >= pos+1)
        {
            String var = tokens.get(pos-1);

            this.leftOperand = new Variable(var);
            this.rightOperand = BaseType.getBaseType(tokens.get(pos+1));
            stack.push(this);

            return pos+1;
        }
        throw new IllegalArgumentException("Cannot assign value to variable");
    }
}
