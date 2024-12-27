package com.supalle.tools.code.beanwrapper.function;

import java.util.function.Function;

@FunctionalInterface
public interface ToIntFunction<T, R> extends Function<T, Integer> {

    int applyAsInt(T value);

    @Override
    default Integer apply(T t) {
        return applyAsInt(t);
    }

}
