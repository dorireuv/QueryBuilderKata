package com.dorireuv.querybuilder;

import com.google.common.collect.ImmutableMap;

class Validators {
    // for code coverage
    static {
        new Validators();
    }

    private Validators() {
    }

    public static <T> void validate(Validator<T> validator, T value) {
        validator.validate(value);
    }

    public static <T> void validate(Validator<T> validator, Iterable<T> values) {
        for (T value : values) {
            validator.validate(value);
        }
    }

    public static final Validator<String> FIELD_NAME_VALIDATOR = new Validator<String>() {
        @Override
        public void validate(String fieldName) {
            if (fieldName == null) {
                throw new IllegalArgumentException("Field name must not be null");
            }
            if (fieldName.isEmpty()) {
                throw new IllegalArgumentException("Field name must not be empty");
            }
        }
    };

    public static final Validator<String> TABLE_NAME_VALIDATOR = new Validator<String>() {
        @Override
        public void validate(String tableName) {
            if (tableName.isEmpty()) {
                throw new IllegalArgumentException("Table name must not be empty");
            }
        }
    };

    public static final Validator<ImmutableMap<String, Object>> VALUES_VALIDATOR =
            new Validator<ImmutableMap<String, Object>>() {
                @Override
                public void validate(ImmutableMap<String, Object> values) {
                    if (values.isEmpty()) {
                        throw new IllegalArgumentException("Values must not be empty");
                    }
                    Validators.validate(FIELD_NAME_VALIDATOR, values.keySet());
                }
            };

    static interface Validator<T> {
        void validate(T data);
    }
}
