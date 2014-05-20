package com.dorireuv.querybuilder;

import com.dorireuv.querybuilder.condition.Condition;
import com.dorireuv.querybuilder.formatter.DeleteQueryFormatter;
import com.dorireuv.querybuilder.formatter.FormattedQuery;

import javax.annotation.Nonnull;

import static com.dorireuv.querybuilder.Validators.TABLE_NAME_VALIDATOR;
import static com.dorireuv.querybuilder.Validators.validate;

public class DeleteQuery implements Query {
    public final String tableName;
    public final Condition whereClause;

    public static Builder deleteFrom(@Nonnull String tableName) {
        validate(TABLE_NAME_VALIDATOR, tableName);
        return new Builder(tableName);
    }

    private DeleteQuery(String tableName, Condition whereClause) {
        this.tableName = tableName;
        this.whereClause = whereClause;
    }

    @Override
    public FormattedQuery format() {
        return new DeleteQueryFormatter().format(this);
    }

    public static class Builder {
        private final String tableName;
        private Condition whereClause;

        private Builder(String tableName) {
            this.tableName = tableName;
        }

        public Builder where(Condition whereCondition) {
            this.whereClause = whereCondition;
            return this;
        }

        public DeleteQuery build() {
            return new DeleteQuery(tableName, whereClause);
        }
    }
}
