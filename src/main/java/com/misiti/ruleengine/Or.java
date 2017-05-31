package com.misiti.ruleengine;

import java.util.Map;

/**
 * Created by EZDI\haresh.p on 22/5/17.
 */

public class Or extends AndOr
{
    public Or()
    {
        super("OR");
    }

    public Or copy()
    {
        return new Or();
    }

    @Override
    public boolean interpret(Map<String, ?> bindings)
    {
        return leftOperand.interpret(bindings) || rightOperand.interpret(bindings);
    }
}