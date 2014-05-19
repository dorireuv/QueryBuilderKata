package com.dorireuv.querybuilder.formatter;

import com.dorireuv.querybuilder.DeleteQuery;

public class DeleteQueryFormatter implements QueryFormatter<DeleteQuery> {
    @Override
    public FormattedQuery format(DeleteQuery query) {
        ParametersBinder parametersBinder = new ParametersBinder();
        return new FormattedQuery(
                String.format(
                        "DELETE FROM %s%s;",
                        new TableNameFormatter().format(query.tableName),
                        new WhereClauseFormatter(parametersBinder).format(query.whereClause)
                ),
                parametersBinder.getBindings()
        );
    }
}
