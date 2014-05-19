package com.dorireuv.querybuilder.formatter;

import com.dorireuv.querybuilder.InsertQuery;
import com.google.common.base.Function;
import com.google.common.base.Joiner;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Iterables;

public class InsertQueryFormatter implements QueryFormatter<InsertQuery> {
    @Override
    public FormattedQuery format(InsertQuery query) {
        ParametersBinder parametersBinder = new ParametersBinder();
        return new FormattedQuery(
                String.format(
                        "INSERT INTO %s %s;",
                        new TableNameFormatter().format(query.tableName),
                        new InsertValuesFormatter(parametersBinder).format(query.values)
                ),
                parametersBinder.getBindings()
        );
    }

    private static class InsertValuesFormatter {
        private final ParametersBinder parametersBinder;

        public InsertValuesFormatter(ParametersBinder parametersBinder) {
            this.parametersBinder = parametersBinder;
        }

        public String format(ImmutableMap<String, Object> values) {
            return String.format(
                    "(%s) VALUES (%s)",
                    Joiner.on(", ").join(values.keySet()),
                    Joiner.on(", ").join(
                            Iterables.transform(
                                    values.values(),
                                    new Function<Object, String>() {
                                        @Override
                                        public String apply(Object value) {
                                            String bindingName = parametersBinder.bind(value);
                                            return String.format(":%s", bindingName);
                                        }
                                    }
                            )
                    )
            );
        }
    }
}
