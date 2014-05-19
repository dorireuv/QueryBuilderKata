package com.dorireuv.querybuilder.formatter;

import com.dorireuv.querybuilder.Query;

interface QueryFormatter<T extends Query> {
    FormattedQuery format(T query);
}
