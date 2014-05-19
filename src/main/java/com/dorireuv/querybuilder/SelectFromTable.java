package com.dorireuv.querybuilder;

import javax.annotation.Nonnull;

public interface SelectFromTable {
    SelectQueryBuilder from(@Nonnull String tableName);
}
