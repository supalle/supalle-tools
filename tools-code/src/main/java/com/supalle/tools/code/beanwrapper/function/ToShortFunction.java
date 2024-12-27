package com.supalle.tools.code.beanwrapper.function;

import java.util.function.Function;

@FunctionalInterface
public interface ToShortFunction<T> extends Function<T, Short> {

    short applyAsShort(T value);

    @Override
    default Short apply(T t) {
        return applyAsShort(t);
    }

}
