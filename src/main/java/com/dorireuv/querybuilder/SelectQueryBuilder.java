package com.dorireuv.querybuilder;

import com.dorireuv.querybuilder.condition.Condition;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;

import javax.annotation.Nonnull;
import java.util.List;

import static com.dorireuv.querybuilder.Validators.*;

public class SelectQueryBuilder implements SelectFromTable {
    private final ImmutableList<String> fieldNames;
    private String tableName;
    private Condition whereClause;

    private SelectQueryBuilder(ImmutableList<String> fieldNames) {
        this.fieldNames = fieldNames;
    }

    public static SelectFromTable select(String firstFieldName, String... restFieldNames) {
        List<String> fieldNames = Lists.asList(firstFieldName, restFieldNames);
        validate(FIELD_NAME_VALIDATOR, fieldNames);
        return new SelectQueryBuilder(ImmutableList.copyOf(fieldNames));
    }

    @Override
    public SelectQueryBuilder from(@Nonnull String tableName) {
        validate(TABLE_NAME_VALIDATOR, tableName);
        this.tableName = tableName;
        return this;
    }

    public SelectQueryBuilder where(Condition whereCondition) {
        this.whereClause = whereCondition;
        return this;
    }

    public SelectQuery build() {
        return new SelectQuery(fieldNames, tableName, whereClause);
    }
}
