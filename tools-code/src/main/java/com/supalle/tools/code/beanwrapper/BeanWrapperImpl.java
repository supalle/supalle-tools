package com.supalle.tools.code.beanwrapper;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class BeanWrapperImpl<T> implements BeanWrapper<T> {

    private final Class<T> beanClass;
    private final Supplier<T> lambdaNoArgsConstructor;
    private final PropertyAccess<T>[] propertyAccesses;
    private final Map<String, PropertyAccess<T>> propertyAccessMapping;

    public BeanWrapperImpl(Class<T> beanClass) {
        this.beanClass = beanClass;
        this.lambdaNoArgsConstructor = LambdaMethodFactory.lambdaNoArgsConstructor(beanClass);
        this.propertyAccesses = buildPropertyAccesses();
        this.propertyAccessMapping = Collections.unmodifiableMap(Arrays.stream(this.propertyAccesses).collect(Collectors.toMap(PropertyAccess::getPropertyName, e -> e)));
    }

    @Override
    public Class<T> getBeanClass() {
        return this.beanClass;
    }

    @Override
    public T newInstance() {
        return this.lambdaNoArgsConstructor.get();
    }

    @Override
    public PropertyAccess<T>[] getPropertyAccesses() {
        return this.propertyAccesses;
    }

    @Override
    public Map<String, PropertyAccess<T>> getPropertyAccessMapping() {
        return this.propertyAccessMapping;
    }

    @SuppressWarnings("unchecked")
    private PropertyAccess<T>[] buildPropertyAccesses() {
        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(getBeanClass());
            PropertyDescriptor[] propertyDescriptors = Optional.ofNullable(beanInfo.getPropertyDescriptors()).orElseGet(() -> new PropertyDescriptor[0]);
            return Arrays.stream(propertyDescriptors).filter(e -> !"class".equals(e.getName())).map(PropertyAccess::new)
                    .toArray(PropertyAccess[]::new);
        } catch (IntrospectionException e) {
            throw new IllegalArgumentException("buildPropertyAccesses error: " + e.getMessage(), e);
        }
    }

}
