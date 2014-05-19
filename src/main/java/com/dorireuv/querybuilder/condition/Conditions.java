package com.dorireuv.querybuilder.condition;

public class Conditions {
    // for code coverage
    static {
        new Conditions();
    }

    private Conditions() {
    }

    public static Condition isEqual(String fieldName, Object value) {
        return new Equals(fieldName, value);
    }

    public static Condition or(Condition firstCondition, Condition secondCondition, Condition... restConditions) {
        return new Or(firstCondition, secondCondition, restConditions);
    }
}
