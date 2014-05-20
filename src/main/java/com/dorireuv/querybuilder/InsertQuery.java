package com.dorireuv.querybuilder;

import com.dorireuv.querybuilder.formatter.FormattedQuery;
import com.dorireuv.querybuilder.formatter.InsertQueryFormatter;
import com.google.common.collect.ImmutableMap;

import javax.annotation.Nonnull;

import static com.dorireuv.querybuilder.Validators.*;

public class InsertQuery implements Query {
    public final String tableName;
    public final ImmutableMap<String, Object> values;

    public static InsertValues insertInto(@Nonnull String tableName) {
        validate(TABLE_NAME_VALIDATOR, tableName);
        return new Builder(tableName);
    }

    private InsertQuery(String tableName, ImmutableMap<String, Object> values) {
        this.tableName = tableName;
        this.values = values;
    }

    @Override
    public FormattedQuery format() {
        return new InsertQueryFormatter().format(this);
    }

    public static interface InsertValues {
        Builder values(@Nonnull ImmutableMap<String, Object> values);
    }

    public static class Builder implements InsertValues {
        private final String tableName;
        private ImmutableMap<String, Object> values;

        private Builder(String tableName) {
            this.tableName = tableName;
        }

        public Builder values(@Nonnull ImmutableMap<String, Object> values) {
            validate(VALUES_VALIDATOR, values);
            this.values = values;
            return this;
        }

        public InsertQuery build() {
            return new InsertQuery(tableName, values);
        }
    }
}
