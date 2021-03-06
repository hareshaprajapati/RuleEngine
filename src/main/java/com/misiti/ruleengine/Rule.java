package com.misiti.ruleengine;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by EZDI\haresh.p on 22/5/17.
 */

public class Rule
{
    private List<Expression> expressions;

    public static class Builder
    {
        private List<Expression> expressions = new ArrayList<>();

        public Builder withExpression(Expression expr)
        {
            expressions.add(expr);
            return this;
        }

        public Rule build()
        {
            return new Rule(expressions);
        }
    }

    private Rule(List<Expression> expressions)
    {
        this.expressions = expressions;
    }

    public boolean eval(Map<String, ?> bindings)
    {
        boolean eval = false;
        for (Expression expression : expressions)
        {
            eval = expression.interpret(bindings);
        }
        return eval;
    }
}
