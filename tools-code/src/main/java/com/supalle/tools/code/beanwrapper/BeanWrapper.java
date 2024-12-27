package com.supalle.tools.code.beanwrapper;

import java.util.Map;

public interface BeanWrapper<T> {

    Class<T> getBeanClass();

    T newInstance();

    PropertyAccess<T>[] getPropertyAccesses();

    Map<String, PropertyAccess<T>> getPropertyAccessMapping();

}
