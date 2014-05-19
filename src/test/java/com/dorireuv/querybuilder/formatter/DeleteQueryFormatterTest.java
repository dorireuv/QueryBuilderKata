package com.dorireuv.querybuilder.formatter;

import com.dorireuv.querybuilder.DeleteQuery;
import com.dorireuv.querybuilder.condition.Condition;
import org.junit.Test;

import static com.dorireuv.querybuilder.DeleteQueryBuilder.deleteFrom;
import static com.dorireuv.querybuilder.condition.Conditions.isEqual;
import static com.dorireuv.querybuilder.condition.Conditions.or;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class DeleteQueryFormatterTest {
    @Test
    public void deleteFromTableWhereSingleCondition() throws Exception {
        DeleteQuery query = deleteFrom("Table").where(isEqual("Field", 1)).build();
        FormattedQuery formattedQuery = query.format();
        assertThat(formattedQuery.queryString, is(equalTo("DELETE FROM Table WHERE Field = :p0;")));
        assertThat((Integer) formattedQuery.queryParams.get("p0"), is(equalTo(1)));
    }

    @Test
    public void deleteFromTableWhereMultipleConditions() throws Exception {
        Condition whereCondition = or(isEqual("Field", 1), isEqual("Field", 2));
        DeleteQuery query = deleteFrom("Table").where(whereCondition).build();
        FormattedQuery formattedQuery = query.format();
        assertThat(formattedQuery.queryString, is(equalTo("DELETE FROM Table WHERE (Field = :p0 OR Field = :p1);")));
        assertThat((Integer) formattedQuery.queryParams.get("p0"), is(equalTo(1)));
        assertThat((Integer) formattedQuery.queryParams.get("p1"), is(equalTo(2)));
    }
}
