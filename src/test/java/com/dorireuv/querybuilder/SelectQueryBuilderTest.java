package com.dorireuv.querybuilder;

import com.dorireuv.querybuilder.condition.Condition;
import org.junit.Test;

import java.util.Arrays;

import static com.dorireuv.querybuilder.SelectQueryBuilder.select;
import static com.dorireuv.querybuilder.condition.Conditions.isEqual;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class SelectQueryBuilderTest {
    @Test
    public void selectFieldsFromTableWhereCondition() throws Exception {
        Condition whereCondition = isEqual("Field", 1);
        SelectQuery query = select("Field1", "Field2").from("Table").where(whereCondition).build();
        assertThat(query.fieldNames, is(equalTo(Arrays.asList("Field1", "Field2"))));
        assertThat(query.tableName, is(equalTo("Table")));
        assertThat(query.whereClause, is(equalTo(whereCondition)));
    }

    @Test(expected = IllegalArgumentException.class)
    public void selectNullFieldNameThrowsIllegalArgumentException() throws Exception {
        select("Field1", "Field2", null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void selectEmptyFieldNameThrowsIllegalArgumentException() throws Exception {
        select("Field1", "Field2", "");
    }

    @Test(expected = IllegalArgumentException.class)
    public void selectFieldFromEmptyTableNameThrowsIllegalArgumentException() throws Exception {
        select("Field").from("");
    }
}
