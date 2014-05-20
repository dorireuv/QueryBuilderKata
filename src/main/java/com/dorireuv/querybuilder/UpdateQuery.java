package com.dorireuv.querybuilder;

import com.dorireuv.querybuilder.condition.Condition;
import com.dorireuv.querybuilder.formatter.FormattedQuery;
import com.dorireuv.querybuilder.formatter.UpdateQueryFormatter;
import com.google.common.collect.ImmutableMap;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import static com.dorireuv.querybuilder.Validators.*;

public class UpdateQuery implements Query {
    public final String tableName;
    public final ImmutableMap<String, Object> values;
    public final Condition whereClause;

    public static UpdateSetValues update(@Nonnull String tableName) {
        validate(TABLE_NAME_VALIDATOR, tableName);
        return new Builder(tableName);
    }

    private UpdateQuery(String tableName, ImmutableMap<String, Object> values, Condition whereClause) {
        this.tableName = tableName;
        this.values = values;
        this.whereClause = whereClause;
    }

    @Override
    public FormattedQuery format() {
        return new UpdateQueryFormatter().format(this);
    }

    public static interface UpdateSetValues {
        Builder set(@Nonnull ImmutableMap<String, Object> values);
    }

    public static class Builder implements UpdateSetValues {
        private final String tableName;
        private ImmutableMap<String, Object> values;
        private Condition whereClause;

        private Builder(String tableName) {
            this.tableName = tableName;
        }

        public Builder set(@Nonnull ImmutableMap<String, Object> values) {
            validate(VALUES_VALIDATOR, values);
            this.values = values;
            return this;
        }

        public Builder where(@Nullable Condition whereCondition) {
            this.whereClause = whereCondition;
            return this;
        }

        public UpdateQuery build() {
            return new UpdateQuery(tableName, values, whereClause);
        }
    }
}
