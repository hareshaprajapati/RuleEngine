package com.misiti.ruleengine;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by EZDI\haresh.p on 16/5/17.
 */
public class Rules {
    List<Rule> ruleList = new ArrayList<>();
    public void addRule(Rule rule){
        ruleList.add(rule);
    }

    public boolean eval(Map<String, String> bindings) {
        for (Rule rule : ruleList) {
            System.out.println(rule.eval(bindings));
        }
        return false;
    }
}
