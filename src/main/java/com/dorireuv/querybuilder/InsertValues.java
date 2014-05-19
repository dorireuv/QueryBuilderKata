package com.dorireuv.querybuilder;

import com.google.common.collect.ImmutableMap;

import javax.annotation.Nonnull;

public interface InsertValues {
    InsertQueryBuilder values(@Nonnull ImmutableMap<String, Object> values);
}
