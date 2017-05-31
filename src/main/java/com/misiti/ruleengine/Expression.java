package com.misiti.ruleengine;

import java.util.Map;

/**
 * Created by EZDI\haresh.p on 22/5/17.
 */

public interface Expression
{
    public boolean interpret(final Map<String, ?> bindings);
}