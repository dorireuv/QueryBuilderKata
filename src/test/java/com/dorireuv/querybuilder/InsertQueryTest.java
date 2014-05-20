package com.dorireuv.querybuilder;

import com.dorireuv.querybuilder.formatter.FormattedQuery;
import com.google.common.collect.ImmutableMap;
import org.junit.Test;

import static com.dorireuv.querybuilder.InsertQuery.insertInto;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class InsertQueryTest {
    @Test
    public void insertIntoTableSingleValue() throws Exception {
        ImmutableMap<String, Object> values = ImmutableMap.<String, Object>of("Field", 10);
        InsertQuery query = insertInto("Table").values(values).build();
        FormattedQuery formattedQuery = query.format();
        assertThat(formattedQuery.queryString, is(equalTo("INSERT INTO Table (Field) VALUES (:p0);")));
        assertThat((Integer) formattedQuery.queryParams.get("p0"), is(equalTo(10)));
    }

    @Test
    public void insertIntoTableMultipleValues() throws Exception {
        ImmutableMap<String, Object> values = ImmutableMap.<String, Object>of("Field1", 10, "Field2", 20);
        InsertQuery query = insertInto("Table").values(values).build();
        FormattedQuery formattedQuery = query.format();
        assertThat(formattedQuery.queryString, is(equalTo("INSERT INTO Table (Field1, Field2) VALUES (:p0, :p1);")));
        assertThat((Integer) formattedQuery.queryParams.get("p0"), is(equalTo(10)));
        assertThat((Integer) formattedQuery.queryParams.get("p1"), is(equalTo(20)));
    }

    @Test(expected = IllegalArgumentException.class)
    public void insertIntoEmptyTableNameThrowsIllegalArgumentException() throws Exception {
        insertInto("");
    }

    @Test(expected = IllegalArgumentException.class)
    public void insertIntoTableSetNoValuesThrowsIllegalArgumentException() throws Exception {
        insertInto("Table").values(ImmutableMap.<String, Object>of());
    }

    @Test(expected = IllegalArgumentException.class)
    public void insertIntoTableSetValueWithEmptyFieldNameThrowsIllegalArgumentException() throws Exception {
        insertInto("Table").values(ImmutableMap.<String, Object>of("", 10));
    }
}
