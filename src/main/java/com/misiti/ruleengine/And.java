package com.misiti.ruleengine;

import java.util.Map;

/**
 * Created by EZDI\haresh.p on 22/5/17.
 */

public class And extends AndOr
{    
    public And()
    {
        super("AND");
    }

    public And copy()
    {
        return new And();
    }

    @Override
    public boolean interpret(Map<String, ?> bindings)
    {
        return leftOperand.interpret(bindings) && rightOperand.interpret(bindings);
    }
}