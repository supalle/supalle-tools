package com.supalle.tools.code.beanwrapper.function;

import com.supalle.tools.code.beanwrapper.PrimitiveValue;

import java.util.function.BiConsumer;

@FunctionalInterface
public interface CharConsumer<T> extends BiConsumer<T, Character> {

    char DEFAULT_VALUE = PrimitiveValue.DEFAULT.getCharDefaultValue();

    void acceptForChar(T t, char value);

    @Override
    default void accept(T t, Character value) {
        if (value == null) {
            acceptForChar(t, DEFAULT_VALUE);
        } else {
            acceptForChar(t, value);
        }
    }

}
