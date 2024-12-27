package com.supalle.tools.code.beanwrapper.function;

import com.supalle.tools.code.beanwrapper.PrimitiveValue;

import java.util.function.BiConsumer;

@FunctionalInterface
public interface IntConsumer<T> extends BiConsumer<T, Integer> {

    int DEFAULT_VALUE = PrimitiveValue.DEFAULT.getIntDefaultValue();

    void acceptForInt(T t, int value);

    @Override
    default void accept(T t, Integer value) {
        if (value == null) {
            acceptForInt(t, DEFAULT_VALUE);
        } else {
            acceptForInt(t, value);
        }
    }

}
