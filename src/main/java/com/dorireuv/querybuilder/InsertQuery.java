package com.dorireuv.querybuilder;

import com.dorireuv.querybuilder.formatter.FormattedQuery;
import com.dorireuv.querybuilder.formatter.InsertQueryFormatter;
import com.google.common.collect.ImmutableMap;

public class InsertQuery implements Query {
    public final String tableName;
    public final ImmutableMap<String, Object> values;

    InsertQuery(String tableName, ImmutableMap<String, Object> values) {
        this.tableName = tableName;
        this.values = values;
    }

    @Override
    public FormattedQuery format() {
        return new InsertQueryFormatter().format(this);
    }
}
