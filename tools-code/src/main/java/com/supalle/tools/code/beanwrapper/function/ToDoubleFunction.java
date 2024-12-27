package com.supalle.tools.code.beanwrapper.function;

import java.util.function.Function;

@FunctionalInterface
public interface ToDoubleFunction<T> extends Function<T, Double> {

    double applyAsDouble(T value);

    @Override
    default Double apply(T t) {
        return applyAsDouble(t);
    }

}
