package com.dorireuv.querybuilder;

import com.dorireuv.querybuilder.condition.Condition;
import com.google.common.collect.ImmutableMap;
import org.junit.Test;

import static com.dorireuv.querybuilder.InsertQueryBuilder.insertInto;
import static com.dorireuv.querybuilder.UpdateQueryBuilder.update;
import static com.dorireuv.querybuilder.condition.Conditions.isEqual;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class InsertQueryBuilderTest {
    @Test
    public void insertIntoTableSingleValue() throws Exception {
        ImmutableMap<String, Object> values = ImmutableMap.<String, Object>of("Field", 10);
        InsertQuery query = insertInto("Table").values(values).build();
        assertThat(query.tableName, is(equalTo("Table")));
        assertThat(query.values, is(equalTo(values)));
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
