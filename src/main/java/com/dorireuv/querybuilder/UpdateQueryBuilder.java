package com.dorireuv.querybuilder;

import com.dorireuv.querybuilder.condition.Condition;
import com.google.common.collect.ImmutableMap;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import static com.dorireuv.querybuilder.Validators.*;

public class UpdateQueryBuilder implements UpdateSetValues {
    private final String tableName;
    private ImmutableMap<String, Object> values;
    private Condition whereClause;

    private UpdateQueryBuilder(String tableName) {
        this.tableName = tableName;
    }

    public static UpdateSetValues update(@Nonnull String tableName) {
        validate(TABLE_NAME_VALIDATOR, tableName);
        return new UpdateQueryBuilder(tableName);
    }

    public UpdateQueryBuilder set(@Nonnull ImmutableMap<String, Object> values) {
        validate(VALUES_VALIDATOR, values);
        this.values = values;
        return this;
    }

    public UpdateQueryBuilder where(@Nullable Condition whereCondition) {
        this.whereClause = whereCondition;
        return this;
    }

    public UpdateQuery build() {
        return new UpdateQuery(tableName, values, whereClause);
    }
}
