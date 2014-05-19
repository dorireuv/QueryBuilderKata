package com.dorireuv.querybuilder.formatter;

import com.google.common.collect.ImmutableMap;

public class FormattedQuery {
    public final String queryString;
    public final ImmutableMap<String, Object> queryParams;

    public FormattedQuery(String queryString, ImmutableMap<String, Object> queryParams) {
        this.queryString = queryString;
        this.queryParams = queryParams;
    }
}
