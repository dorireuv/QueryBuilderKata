package com.dorireuv.querybuilder.condition;

import com.dorireuv.querybuilder.formatter.ParametersBinder;

class Equals implements Condition {
    private final String fieldName;
    private final Object value;

    Equals(String fieldName, Object value) {
        this.fieldName = fieldName;
        this.value = value;
    }

    @Override
    public String format(ParametersBinder parametersBinder) {
        String bindingName = parametersBinder.bind(value);
        return String.format("%s = :%s", fieldName, bindingName);
    }
}
