package com.dorireuv.querybuilder.condition;

import com.dorireuv.querybuilder.formatter.ParametersBinder;
import com.google.common.base.Function;
import com.google.common.base.Joiner;
import com.google.common.collect.Lists;

import java.util.List;

class Or implements Condition {
    private final List<Condition> conditions;

    Or(Condition firstCondition, Condition secondCondition, Condition... restConditions) {
        conditions = Lists.asList(firstCondition, secondCondition, restConditions);
    }

    @Override
    public String format(final ParametersBinder parametersBinder) {
        return String.format(
                "(%s)",
                Joiner.on(" OR ").join(
                        Lists.transform(
                                conditions,
                                new Function<Condition, String>() {
                                    @Override
                                    public String apply(Condition condition) {
                                        return condition.format(parametersBinder);
                                    }
                                }
                        )
                )
        );
    }
}
