package com.supalle.tools.code.beanwrapper.function;

import com.supalle.tools.code.beanwrapper.PrimitiveValue;

import java.util.function.BiConsumer;

@FunctionalInterface
public interface ShortConsumer<T> extends BiConsumer<T, Short> {

    short DEFAULT_VALUE = PrimitiveValue.DEFAULT.getShortDefaultValue();

    void acceptForShort(T t, short value);

    @Override
    default void accept(T t, Short value) {
        if (value == null) {
            acceptForShort(t, DEFAULT_VALUE);
        } else {
            acceptForShort(t, value);
        }
    }

}
