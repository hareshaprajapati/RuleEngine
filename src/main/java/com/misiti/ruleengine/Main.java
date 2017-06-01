package com.misiti.ruleengine;


import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by EZDI\haresh.p on 22/5/17.
 */

public class Main 
{
    public static void main( String[] args ) throws ParseException {

        Operations operations = Operations.INSTANCE;

        operations.registerOperation(new And());
        operations.registerOperation(new Or());
        operations.registerOperation(new Equals());
        operations.registerOperation(new Not());
        operations.registerOperation(new OpeningBracket());
        operations.registerOperation(new ClosingBracket());
        operations.registerOperation(new GreaterThan());
        operations.registerOperation(new GreaterThanEqual());
        operations.registerOperation(new LessThan());
        operations.registerOperation(new LessThanEqual());
        /*Expression ex1 = ExpressionParser.fromString("( PATIENT_TYPE = 'A B' AND  DATE >=  '21-05-2017 15:38:55' AND  LOS > 1 ) OR ( " +
                "PATIENT_CLASS > 1 )  "); //*/
        Expression ex1 = ExpressionParser.fromString("( DOUBLE_VALUE = 2.0 OR NOT STRING_VALUE IN ( 'A B' , 'C C' ) AND NOT INT_VALUE IN ( 2 , 3 ) AND " +
                "DATE >=  '21-05-2017 15:38:55' )" );

        Rule rule1 = new Rule.Builder()
                            .withExpression(ex1)
                            .build();


        Rules rules = new Rules();
        rules.addRule(rule1);

        Map<String, Object> bindings = new HashMap<>();
        bindings.put("STRING_VALUE", "'C C'");
        bindings.put("DOUBLE_VALUE", 3.0);
        bindings.put("DATE","22-05-2017 15:38:56");
        bindings.put("INT_VALUE",0);
        System.out.println(rule1.eval(bindings));

    }
}
