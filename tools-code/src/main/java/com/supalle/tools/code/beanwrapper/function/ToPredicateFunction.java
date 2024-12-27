package com.supalle.tools.code.beanwrapper.function;

import java.util.function.Function;

@FunctionalInterface
public interface ToPredicateFunction<T> extends Function<T, Boolean> {

    boolean test(T t);

    @Override
    default Boolean apply(T t) {
        return test(t);
    }

}
