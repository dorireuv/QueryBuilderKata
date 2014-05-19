package com.dorireuv.querybuilder;

import com.dorireuv.querybuilder.condition.Condition;
import org.junit.Test;

import static com.dorireuv.querybuilder.DeleteQueryBuilder.deleteFrom;
import static com.dorireuv.querybuilder.condition.Conditions.isEqual;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class DeleteQueryBuilderTest {
    @Test
    public void deleteFromTableWhereCondition() throws Exception {
        Condition whereCondition = isEqual("Field", 1);
        DeleteQuery query = deleteFrom("Table").where(whereCondition).build();
        assertThat(query.tableName, is(equalTo("Table")));
        assertThat(query.whereClause, is(equalTo(whereCondition)));
    }

    @Test(expected = IllegalArgumentException.class)
    public void deleteFromEmptyTableNameThrowsIllegalArgumentException() throws Exception {
        deleteFrom("");
    }
}
