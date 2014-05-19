package com.dorireuv.querybuilder.formatter;

import com.dorireuv.querybuilder.condition.Condition;

import javax.annotation.Nullable;

class WhereClauseFormatter {
    private final ParametersBinder parametersBinder;

    public WhereClauseFormatter(ParametersBinder parametersBinder) {
        this.parametersBinder = parametersBinder;
    }

    public String format(@Nullable Condition whereCondition) {
        if (whereCondition == null) {
            return "";
        }

        return " WHERE " + whereCondition.format(parametersBinder);
    }
}
