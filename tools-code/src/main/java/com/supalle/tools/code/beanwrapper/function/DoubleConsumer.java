package com.supalle.tools.code.beanwrapper.function;

import com.supalle.tools.code.beanwrapper.PrimitiveValue;

import java.util.function.BiConsumer;

@FunctionalInterface
public interface DoubleConsumer<T> extends BiConsumer<T, Double> {

    double DEFAULT_VALUE = PrimitiveValue.DEFAULT.getDoubleDefaultValue();

    void acceptForDouble(T t, double value);

    @Override
    default void accept(T t, Double value) {
        if (value == null) {
            acceptForDouble(t, DEFAULT_VALUE);
        } else {
            acceptForDouble(t, value);
        }
    }

}
