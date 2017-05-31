package com.misiti.ruleengine;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * Created by EZDI\haresh.p on 22/5/17.
 */

public class Equals extends LessThanGreaterThanEquals
{      
    public Equals()
    {
        super("=");
    }

    @Override
    public Equals copy()
    {
        return new Equals();
    }

    @Override
    public boolean interpret(Map<String, ?> bindings)
    {
        Variable v = (Variable)this.leftOperand;
        Object obj = bindings.get(v.getName());
        if (obj == null)
            return false;
        Date obj1= null;
        BaseType<?> type = (BaseType<?>)this.rightOperand;
        if(type.getValue() instanceof Date){
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
            try {
                obj1 = sdf.parse((String) obj);
                Date value = (Date) type.getValue();
                if(obj1.equals(value) ){
                    return true;
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }else if (type.getType().equals(obj.getClass()))
        {
            if (type.getValue().equals(obj))
                return true;
        }
        return false;
    }
}
