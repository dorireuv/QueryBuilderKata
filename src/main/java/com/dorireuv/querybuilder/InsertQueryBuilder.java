package com.dorireuv.querybuilder;

import com.google.common.collect.ImmutableMap;

import javax.annotation.Nonnull;

import static com.dorireuv.querybuilder.Validators.*;

public class InsertQueryBuilder implements InsertValues {
    private final String tableName;
    private ImmutableMap<String, Object> values;

    private InsertQueryBuilder(String tableName) {
        this.tableName = tableName;
    }

    public static InsertValues insertInto(@Nonnull String tableName) {
        validate(TABLE_NAME_VALIDATOR, tableName);
        return new InsertQueryBuilder(tableName);
    }

    public InsertQueryBuilder values(@Nonnull ImmutableMap<String, Object> values) {
        validate(VALUES_VALIDATOR, values);
        this.values = values;
        return this;
    }

    public InsertQuery build() {
        return new InsertQuery(tableName, values);
    }
}
