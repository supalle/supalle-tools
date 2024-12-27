package com.supalle.tools.code.beanwrapper.function;

import com.supalle.tools.code.beanwrapper.PrimitiveValue;

import java.util.function.BiConsumer;

@FunctionalInterface
public interface LongConsumer<T> extends BiConsumer<T, Long> {

    long DEFAULT_VALUE = PrimitiveValue.DEFAULT.getLongDefaultValue();

    void acceptForLong(T t, long value);

    @Override
    default void accept(T t, Long value) {
        if (value == null) {
            acceptForLong(t, DEFAULT_VALUE);
        } else {
            acceptForLong(t, value);
        }

    }

}
