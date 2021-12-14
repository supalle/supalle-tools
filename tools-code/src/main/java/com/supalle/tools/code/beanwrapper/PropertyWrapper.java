package com.supalle.tools.code.beanwrapper;


public interface PropertyWrapper {

    PropertyAccess[] getPropertyAccesses();
//
//    default Object getValue(Object bean, String propertyName) {
//        return getValue(bean, propertyName, false);
//    }
//
//    Object getValue(Object bean, String propertyName, boolean ignoreError);
//
//    default TypeValue getTypeValue(Object bean, String propertyName) {
//        return getTypeValue(bean, propertyName, false);
//    }
//
//    TypeValue getTypeValue(Object bean, String propertyName, boolean ignoreError);
//
//    TypeValue[] getTypeValues(Object bean);
}
