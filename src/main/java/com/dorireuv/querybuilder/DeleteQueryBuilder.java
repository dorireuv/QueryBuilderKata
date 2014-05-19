package com.dorireuv.querybuilder;

import com.dorireuv.querybuilder.condition.Condition;

import javax.annotation.Nonnull;

import static com.dorireuv.querybuilder.Validators.TABLE_NAME_VALIDATOR;
import static com.dorireuv.querybuilder.Validators.validate;

public class DeleteQueryBuilder {
    private final String tableName;
    private Condition whereClause;

    private DeleteQueryBuilder(String tableName) {
        this.tableName = tableName;
    }

    public static DeleteQueryBuilder deleteFrom(@Nonnull String tableName) {
        validate(TABLE_NAME_VALIDATOR, tableName);
        return new DeleteQueryBuilder(tableName);
    }

    public DeleteQueryBuilder where(Condition whereCondition) {
        this.whereClause = whereCondition;
        return this;
    }

    public DeleteQuery build() {
        return new DeleteQuery(tableName, whereClause);
    }
}
