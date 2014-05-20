package com.dorireuv.querybuilder;

import com.dorireuv.querybuilder.condition.Condition;
import com.dorireuv.querybuilder.formatter.FormattedQuery;
import com.dorireuv.querybuilder.formatter.SelectQueryFormatter;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;

import javax.annotation.Nonnull;
import java.util.List;

import static com.dorireuv.querybuilder.Validators.*;

public class SelectQuery implements Query {
    public final ImmutableList<String> fieldNames;
    public final String tableName;
    public final Condition whereClause;

    public static SelectFromTable select(String firstFieldName, String... restFieldNames) {
        List<String> fieldNames = Lists.asList(firstFieldName, restFieldNames);
        validate(FIELD_NAME_VALIDATOR, fieldNames);
        return new Builder(ImmutableList.copyOf(fieldNames));
    }

    private SelectQuery(ImmutableList<String> fieldNames, String tableName, Condition whereClause) {
        this.fieldNames = fieldNames;
        this.tableName = tableName;
        this.whereClause = whereClause;
    }

    @Override
    public FormattedQuery format() {
        return new SelectQueryFormatter().format(this);
    }

    public static interface SelectFromTable {
        Builder from(@Nonnull String tableName);
    }

    public static class Builder implements SelectFromTable {
        private final ImmutableList<String> fieldNames;
        private String tableName;
        private Condition whereClause;

        private Builder(ImmutableList<String> fieldNames) {
            this.fieldNames = fieldNames;
        }

        @Override
        public Builder from(@Nonnull String tableName) {
            validate(TABLE_NAME_VALIDATOR, tableName);
            this.tableName = tableName;
            return this;
        }

        public Builder where(Condition whereCondition) {
            this.whereClause = whereCondition;
            return this;
        }

        public SelectQuery build() {
            return new SelectQuery(fieldNames, tableName, whereClause);
        }
    }
}
