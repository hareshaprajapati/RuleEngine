package com.misiti.ruleengine;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * Created by EZDI\haresh.p on 22/5/17.
 */

public class LessThan extends LessThanGreaterThanEquals
{

    public LessThan()
    {
        super("<");
    }

    @Override
    public LessThan copy()
    {
        return new LessThan();
    }

    @Override
    public boolean interpret(Map<String, ?> bindings)  {
        Variable v = (Variable)this.leftOperand;
        Object obj = bindings.get(v.getName());
        if (obj == null)
            return false;
        Date obj1= null;
        BaseType<?> type = (BaseType<?>)this.rightOperand;
        if(obj instanceof String){
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
            try {
                obj1 = sdf.parse((String) obj);
                Date value = (Date) type.getValue();
                if(obj1.before(value) ){
                    return true;
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }else{
            if (type.getType().equals(obj.getClass()))
            {
                if(obj instanceof Integer){
                    Integer value = (Integer) type.getValue();
                    if((int) obj < value.intValue() ){
                        return true;
                    }
                }else if(obj instanceof Float){
                    Float value = (Float) type.getValue();
                    if((float) obj < value.floatValue() ){
                        return true;
                    }
                }else if(obj instanceof Double){
                    Double value = (Double) type.getValue();
                    if((double) obj < value.doubleValue() ){
                        return true;
                    }
                }

            }
        }
        return false;
    }
}
