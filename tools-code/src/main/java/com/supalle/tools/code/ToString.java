package com.supalle.tools.code;

import java.util.Objects;
import java.util.function.Function;

public class ToString<T> {

    private final T target;
    private final Function<T, String> toStringFunction;

    public ToString(T target, Function<T, String> toStringFunction) {
        this.target = Objects.requireNonNull(target);
        this.toStringFunction = Objects.requireNonNull(toStringFunction);
    }

    public static <T> ToString<T> of(T target, Function<T, String> toStringFunction) {
        return new ToString<>(target, toStringFunction);
    }

    @Override
    public String toString() {
        return toStringFunction.apply(target);
    }


}
