package com.dorireuv.querybuilder.formatter;

import com.dorireuv.querybuilder.UpdateQuery;
import com.google.common.base.Function;
import com.google.common.base.Joiner;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Iterables;

import java.util.Map;

public class UpdateQueryFormatter implements QueryFormatter<UpdateQuery> {
    @Override
    public FormattedQuery format(UpdateQuery query) {
        ParametersBinder parametersBinder = new ParametersBinder();
        return new FormattedQuery(
                String.format(
                        "UPDATE %s SET %s%s;",
                        new TableNameFormatter().format(query.tableName),
                        new SetValuesFormatter(parametersBinder).format(query.values),
                        new WhereClauseFormatter(parametersBinder).format(query.whereClause)
                ),
                parametersBinder.getBindings()
        );
    }

    private static class SetValuesFormatter {
        private final ParametersBinder parametersBinder;

        public SetValuesFormatter(ParametersBinder parametersBinder) {
            this.parametersBinder = parametersBinder;
        }

        public String format(ImmutableMap<String, Object> values) {
            return Joiner.on(", ").join(
                    Iterables.transform(
                            values.entrySet(),
                            new Function<Map.Entry<String, Object>, String>() {
                                @Override
                                public String apply(Map.Entry<String, Object> value) {
                                    String fieldName = value.getKey();
                                    Object setValue = value.getValue();
                                    String bindingName = parametersBinder.bind(setValue);
                                    return String.format("%s = :%s", fieldName, bindingName);
                                }
                            }
                    )
            );
        }
    }
}
