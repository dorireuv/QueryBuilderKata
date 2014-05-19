package com.dorireuv.querybuilder.condition;

import com.dorireuv.querybuilder.formatter.ParametersBinder;

public interface Condition {
    String format(ParametersBinder parametersBinder);
}
