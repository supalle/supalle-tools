package com.supalle.tools.code.beanwrapper;

import lombok.Getter;

@Getter
public enum PrimitiveValue {
    DEFAULT;
    private byte byteDefaultValue;
    private short shortDefaultValue;
    private int intDefaultValue;
    private long longDefaultValue;
    private float floatDefaultValue;
    private double doubleDefaultValue;
    private char charDefaultValue;
    private boolean booleanDefaultValue;
}
