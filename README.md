# RuleEngine

This project is useful to evaluate any expression like "( DOUBLE_VALUE = 2.0 OR NOT STRING_VALUE IN ( 'A B' , 'C C' ) AND NOT INT_VALUE IN ( 2 , 3 ) AND DATE >=  '21-05-2017 15:38:55' )" by passing variable values in map. It will evaluate the expression by checking variable value from map and will return true or false. Just run main class to see the output and how easy to evaluate such expression.
Just create expression like :
Expression ex1 = ExpressionParser.fromString("( DOUBLE_VALUE = 2.0 OR NOT STRING_VALUE IN ( 'A B' , 'C C' ) AND NOT INT_VALUE IN ( 2 , 3 ) AND DATE >=  '21-05-2017 15:38:55' )" );

and fill values of variable in map like :

Map<String, Object> bindings = new HashMap<>();
        bindings.put("STRING_VALUE", "'C C'");
        bindings.put("DOUBLE_VALUE", 3.0);
        bindings.put("DATE","22-05-2017 15:38:56");
        bindings.put("INT_VALUE",0);
and evaluate the rule it will return true or false.        
