package com.dorireuv.querybuilder;

import com.dorireuv.querybuilder.condition.Condition;
import com.dorireuv.querybuilder.formatter.FormattedQuery;
import com.dorireuv.querybuilder.formatter.SelectQueryFormatter;
import com.google.common.collect.ImmutableList;

public class SelectQuery implements Query {
    public final ImmutableList<String> fieldNames;
    public final String tableName;
    public final Condition whereClause;

    SelectQuery(ImmutableList<String> fieldNames, String tableName, Condition whereClause) {
        this.fieldNames = fieldNames;
        this.tableName = tableName;
        this.whereClause = whereClause;
    }

    @Override
    public FormattedQuery format() {
        return new SelectQueryFormatter().format(this);
    }
}
