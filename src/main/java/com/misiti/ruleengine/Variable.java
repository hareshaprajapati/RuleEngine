package com.misiti.ruleengine;

import java.util.Map;

/**
 * Created by EZDI\haresh.p on 22/5/17.
 */

public class Variable implements Expression
{
    private String name;

    public Variable(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return this.name;
    }

    @Override
    public boolean interpret(Map<String, ?> bindings)
    {
        return true;
    }
}