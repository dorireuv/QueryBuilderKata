package com.dorireuv.querybuilder;

import com.dorireuv.querybuilder.condition.Condition;
import com.google.common.collect.ImmutableMap;
import org.junit.Test;

import static com.dorireuv.querybuilder.UpdateQueryBuilder.update;
import static com.dorireuv.querybuilder.condition.Conditions.isEqual;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class UpdateQueryBuilderTest {
    @Test
    public void updateTableSetSingleValueWhereSingleCondition() throws Exception {
        ImmutableMap<String, Object> values = ImmutableMap.<String, Object>of("Field", 10);
        Condition whereCondition = isEqual("Field", 20);
        UpdateQuery query = update("Table").set(values).where(whereCondition).build();
        assertThat(query.tableName, is(equalTo("Table")));
        assertThat(query.values, is(equalTo(values)));
        assertThat(query.whereClause, is(equalTo(whereCondition)));
    }

    @Test(expected = IllegalArgumentException.class)
    public void updateEmptyTableNameThrowsIllegalArgumentException() throws Exception {
        update("");
    }

    @Test(expected = IllegalArgumentException.class)
    public void updateTableSetNoValuesThrowsIllegalArgumentException() throws Exception {
        update("Table").set(ImmutableMap.<String, Object>of());
    }

    @Test(expected = IllegalArgumentException.class)
    public void updateTableSetValueWithEmptyFieldNameThrowsIllegalArgumentException() throws Exception {
        update("Table").set(ImmutableMap.<String, Object>of("", 10));
    }
}
