package com.dorireuv.querybuilder.formatter;

import com.dorireuv.querybuilder.SelectQuery;
import com.google.common.base.Joiner;
import com.google.common.collect.ImmutableList;

public class SelectQueryFormatter implements QueryFormatter<SelectQuery> {
    @Override
    public FormattedQuery format(SelectQuery query) {
        ParametersBinder parametersBinder = new ParametersBinder();
        return new FormattedQuery(
                String.format(
                        "SELECT %s FROM %s%s;",
                        new FieldNamesFormatter().format(query.fieldNames),
                        new TableNameFormatter().format(query.tableName),
                        new WhereClauseFormatter(parametersBinder).format(query.whereClause)
                ),
                parametersBinder.getBindings()
        );
    }

    private static class FieldNamesFormatter {
        public String format(ImmutableList<String> fieldNames) {
            return Joiner.on(", ").join(fieldNames);
        }
    }
}
