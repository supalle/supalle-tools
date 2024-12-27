package com.supalle.tools.code.beanwrapper.function;

import com.supalle.tools.code.beanwrapper.PrimitiveValue;

import java.util.function.BiConsumer;

@FunctionalInterface
public interface PredicateConsumer<T> extends BiConsumer<T, Boolean> {

    boolean DEFAULT_VALUE = PrimitiveValue.DEFAULT.isBooleanDefaultValue();

    void acceptForBoolean(T t, boolean value);

    @Override
    default void accept(T t, Boolean value) {
        if (value == null) {
            acceptForBoolean(t, DEFAULT_VALUE);
        } else {
            acceptForBoolean(t, value);
        }
    }

}
