package com.supalle.tools.code.beanwrapper.function;

import java.util.function.Function;

@FunctionalInterface
public interface ToCharFunction<T> extends Function<T, Character> {

    char applyAsChar(T value);

    @Override
    default Character apply(T t) {
        return applyAsChar(t);
    }

}
