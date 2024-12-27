package com.supalle.tools.code.beanwrapper.function;

import com.supalle.tools.code.beanwrapper.PrimitiveValue;

import java.util.function.BiConsumer;

@FunctionalInterface
public interface FloatConsumer<T> extends BiConsumer<T, Float> {

    float DEFAULT_VALUE = PrimitiveValue.DEFAULT.getFloatDefaultValue();

    void acceptForFloat(T t, float value);

    @Override
    default void accept(T t, Float value) {
        if (value == null) {
            acceptForFloat(t, DEFAULT_VALUE);
        } else {
            acceptForFloat(t, value);
        }
    }

}
