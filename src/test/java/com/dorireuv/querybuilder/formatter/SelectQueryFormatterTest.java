package com.dorireuv.querybuilder.formatter;

import com.dorireuv.querybuilder.SelectQuery;
import com.dorireuv.querybuilder.condition.Condition;
import org.junit.Test;

import java.util.Collections;

import static com.dorireuv.querybuilder.SelectQueryBuilder.select;
import static com.dorireuv.querybuilder.condition.Conditions.isEqual;
import static com.dorireuv.querybuilder.condition.Conditions.or;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class SelectQueryFormatterTest {
    @Test
    public void selectSingleFieldFromTable() throws Exception {
        SelectQuery query = select("Field").from("Table").build();
        FormattedQuery formattedQuery = query.format();
        assertThat(formattedQuery.queryString, is(equalTo("SELECT Field FROM Table;")));
        assertThat(formattedQuery.queryParams, is(equalTo(Collections.<String, Object>emptyMap())));
    }

    @Test
    public void selectMultipleFieldsFromTable() throws Exception {
        SelectQuery query = select("Field1", "Field2").from("Table").build();
        FormattedQuery formattedQuery = query.format();
        assertThat(formattedQuery.queryString, is(equalTo("SELECT Field1, Field2 FROM Table;")));
        assertThat(formattedQuery.queryParams, is(equalTo(Collections.<String, Object>emptyMap())));
    }

    @Test
    public void selectFieldFromTableWhereSingleCondition() throws Exception {
        SelectQuery query = select("Field").from("Table").where(isEqual("Field", 1)).build();
        FormattedQuery formattedQuery = query.format();
        assertThat(formattedQuery.queryString, is(equalTo("SELECT Field FROM Table WHERE Field = :p0;")));
        assertThat((Integer) formattedQuery.queryParams.get("p0"), is(equalTo(1)));
    }

    @Test
    public void selectFieldFromTableWhereMultipleConditions() throws Exception {
        Condition whereCondition = or(isEqual("Field", 1), isEqual("Field", 2));
        SelectQuery query = select("Field").from("Table").where(whereCondition).build();
        FormattedQuery formattedQuery = query.format();
        assertThat(formattedQuery.queryString, is(equalTo("SELECT Field FROM Table WHERE (Field = :p0 OR Field = :p1);")));
        assertThat((Integer) formattedQuery.queryParams.get("p0"), is(equalTo(1)));
        assertThat((Integer) formattedQuery.queryParams.get("p1"), is(equalTo(2)));
    }
}
