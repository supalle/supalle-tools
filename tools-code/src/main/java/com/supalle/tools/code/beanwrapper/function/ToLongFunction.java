package com.supalle.tools.code.beanwrapper.function;

import java.util.function.Function;

@FunctionalInterface
public interface ToLongFunction<T> extends Function<T, Long> {

    long applyAsLong(T value);

    @Override
    default Long apply(T t) {
        return applyAsLong(t);
    }

}
