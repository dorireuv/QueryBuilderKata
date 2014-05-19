package com.dorireuv.querybuilder.formatter;

import com.google.common.collect.ImmutableMap;

public class ParametersBinder {
    private int counter;
    private final ImmutableMap.Builder<String, Object> bindings;

    ParametersBinder() {
        counter = 0;
        bindings = ImmutableMap.builder();
    }

    public String bind(Object value) {
        String bindingName = String.format("p%d", counter++);
        bindings.put(bindingName, value);
        return bindingName;
    }

    public ImmutableMap<String, Object> getBindings() {
        bindings.build();
        return bindings.build();
    }
}
