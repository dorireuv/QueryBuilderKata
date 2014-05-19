package com.dorireuv.querybuilder;

import com.dorireuv.querybuilder.condition.Condition;
import com.dorireuv.querybuilder.formatter.FormattedQuery;
import com.dorireuv.querybuilder.formatter.UpdateQueryFormatter;
import com.google.common.collect.ImmutableMap;

public class UpdateQuery implements Query {
    public final String tableName;
    public final ImmutableMap<String, Object> values;
    public final Condition whereClause;

    UpdateQuery(String tableName, ImmutableMap<String, Object> values, Condition whereClause) {
        this.tableName = tableName;
        this.values = values;
        this.whereClause = whereClause;
    }

    @Override
    public FormattedQuery format() {
        return new UpdateQueryFormatter().format(this);
    }
}
