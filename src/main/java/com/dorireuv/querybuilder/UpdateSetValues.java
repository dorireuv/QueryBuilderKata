package com.dorireuv.querybuilder;

import com.google.common.collect.ImmutableMap;

import javax.annotation.Nonnull;

public interface UpdateSetValues {
    UpdateQueryBuilder set(@Nonnull ImmutableMap<String, Object> values);
}
