package com.dorireuv.querybuilder.formatter;

import com.dorireuv.querybuilder.UpdateQuery;
import com.dorireuv.querybuilder.condition.Condition;
import com.google.common.collect.ImmutableMap;
import org.junit.Test;

import static com.dorireuv.querybuilder.UpdateQueryBuilder.update;
import static com.dorireuv.querybuilder.condition.Conditions.isEqual;
import static com.dorireuv.querybuilder.condition.Conditions.or;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class UpdateQueryFormatterTest {
    @Test
    public void updateTableSetSingleValue() throws Exception {
        ImmutableMap<String, Object> values = ImmutableMap.<String, Object>of("Field", 10);
        UpdateQuery query = update("Table").set(values).build();
        FormattedQuery formattedQuery = query.format();
        assertThat(formattedQuery.queryString, is(equalTo("UPDATE Table SET Field = :p0;")));
        assertThat((Integer) formattedQuery.queryParams.get("p0"), is(equalTo(10)));
    }

    @Test
    public void updateTableSetMultipleValues() throws Exception {
        ImmutableMap<String, Object> values = ImmutableMap.<String, Object>of("Field1", 10, "Field2", 20);
        UpdateQuery query = update("Table").set(values).build();
        FormattedQuery formattedQuery = query.format();
        assertThat(formattedQuery.queryString, is(equalTo("UPDATE Table SET Field1 = :p0, Field2 = :p1;")));
        assertThat((Integer) formattedQuery.queryParams.get("p0"), is(equalTo(10)));
        assertThat((Integer) formattedQuery.queryParams.get("p1"), is(equalTo(20)));
    }

    @Test
    public void updateTableSetMultipleValuesWhereMultipleConditions() throws Exception {
        ImmutableMap<String, Object> values = ImmutableMap.<String, Object>of("Field1", 1, "Field2", 2);
        Condition whereCondition = or(isEqual("Field", 3), isEqual("Field", 4));
        UpdateQuery query = update("Table").set(values).where(whereCondition).build();
        FormattedQuery formattedQuery = query.format();
        assertThat(
                formattedQuery.queryString,
                is(equalTo("UPDATE Table SET Field1 = :p0, Field2 = :p1 WHERE (Field = :p2 OR Field = :p3);"))
        );
        assertThat((Integer) formattedQuery.queryParams.get("p0"), is(equalTo(1)));
        assertThat((Integer) formattedQuery.queryParams.get("p1"), is(equalTo(2)));
        assertThat((Integer) formattedQuery.queryParams.get("p2"), is(equalTo(3)));
        assertThat((Integer) formattedQuery.queryParams.get("p3"), is(equalTo(4)));
    }
}
