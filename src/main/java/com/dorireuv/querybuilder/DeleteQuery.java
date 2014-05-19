package com.dorireuv.querybuilder;

import com.dorireuv.querybuilder.condition.Condition;
import com.dorireuv.querybuilder.formatter.DeleteQueryFormatter;
import com.dorireuv.querybuilder.formatter.FormattedQuery;

public class DeleteQuery implements Query {
    public final String tableName;
    public final Condition whereClause;

    DeleteQuery(String tableName, Condition whereClause) {
        this.tableName = tableName;
        this.whereClause = whereClause;
    }

    @Override
    public FormattedQuery format() {
        return new DeleteQueryFormatter().format(this);
    }
}
