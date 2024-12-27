package com.supalle.tools.code.beanwrapper.function;

import com.supalle.tools.code.beanwrapper.PrimitiveValue;

import java.util.function.BiConsumer;

@FunctionalInterface
public interface ByteConsumer<T> extends BiConsumer<T, Byte> {

    byte DEFAULT_VALUE = PrimitiveValue.DEFAULT.getByteDefaultValue();

    void acceptForByte(T t, byte value);

    @Override
    default void accept(T t, Byte value) {
        if (value == null) {
            acceptForByte(t, DEFAULT_VALUE);
        } else {
            acceptForByte(t, value);
        }
    }

}
