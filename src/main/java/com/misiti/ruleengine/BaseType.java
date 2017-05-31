package com.misiti.ruleengine;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * Created by EZDI\haresh.p on 22/5/17.
 */

public class BaseType<T> implements Expression {
    public T value;
    public Class<T> type;

    public BaseType(T value, Class<T> type) {
        this.value = value;
        this.type = type;
    }

    public T getValue() {
        return this.value;
    }

    public Class<T> getType() {
        return this.type;
    }

    @Override
    public boolean interpret(Map<String, ?> bindings) {
        return true;
    }

    public static BaseType<?> getBaseType(String string) throws ParseException {
        if (string == null)
            throw new IllegalArgumentException("The provided string must not be null");

        if ("true".equals(string) || "false".equals(string))
            return new BaseType<>(Boolean.getBoolean(string), Boolean.class);
        else if (string.contains("."))
            return new BaseType<>(Double.parseDouble(string), Double.class);
        else if (string.contains(":")) {
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
            return new BaseType<>(sdf.parse(string.substring(1, string.length() - 1)), Date.class);
        } else if (string.startsWith("'"))
            return new BaseType<>(string, String.class);
        else
            return new BaseType<>(Integer.parseInt(string), Integer.class);
    }
}