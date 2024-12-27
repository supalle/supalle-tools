package com.supalle.tools.code;

import com.supalle.tools.code.beanwrapper.BeanWrapper;
import com.supalle.tools.code.beanwrapper.BeanWrapperImpl;
import com.supalle.tools.code.beanwrapper.PropertyAccess;
import com.supalle.tools.code.beanwrapper.PropertyAccessLoops;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class BeanWrappers {

    public static final ConcurrentMap<Class<?>, BeanWrapper<?>> BEAN_WRAPPER_CACHE = new ConcurrentHashMap<>(32);

    @SuppressWarnings("unchecked")
    public static <T> BeanWrapper<T> getWrapper(Class<T> clazz) {
        BeanWrapper<T> beanWrapper = (BeanWrapper<T>) BEAN_WRAPPER_CACHE.get(clazz);
        if (beanWrapper != null) {
            return beanWrapper;
        }
        return (BeanWrapper<T>) BEAN_WRAPPER_CACHE.computeIfAbsent(clazz, BeanWrapperImpl::new);
    }

    @SuppressWarnings("unchecked")
    private static <T> Class<T> getClazz(T bean) {
        return (Class<T>) bean.getClass();
    }

    public static <T> T copy(T bean) {
        if (bean == null) {
            return null;
        }
        BeanWrapper<T> beanWrapper = getWrapper(getClazz(bean));
        T newInstance = beanWrapper.newInstance();
        PropertyAccessLoops.copyLoop(bean, newInstance, beanWrapper.getPropertyAccesses());
        return newInstance;
    }

    public static <T> T copy(T bean, String... ignoredProperties) {
        if (bean == null) {
            return null;
        }
        if (ignoredProperties == null || ignoredProperties.length == 0) {
            return copy(bean);
        }

        BeanWrapper<T> beanWrapper = getWrapper(getClazz(bean));
        T newInstance = beanWrapper.newInstance();
        copyProperties(bean, newInstance, ignoredProperties);
        return newInstance;
    }

    public static <T> T copy(T bean, Set<String> ignoredPropertySet) {
        if (bean == null) {
            return null;
        }
        if (ignoredPropertySet == null || ignoredPropertySet.isEmpty()) {
            return copy(bean);
        }

        BeanWrapper<T> beanWrapper = getWrapper(getClazz(bean));
        T newInstance = beanWrapper.newInstance();
        copyProperties(bean, newInstance, ignoredPropertySet);
        return newInstance;
    }


    public static void copyProperties(Object source, Object target, String... ignoredProperties) {
        if (source == null || target == null) {
            return;
        }
        if (ignoredProperties == null || ignoredProperties.length == 0) {
            copyProperties(source, target);
            return;
        }
        copyProperties(source, target, new HashSet<>(Arrays.asList(ignoredProperties)));
    }

    public static void copyProperties(Object source, Object target) {
        if (source == null || target == null) {
            return;
        }
        Class<Object> sourceClass = getClazz(source);
        Class<Object> targetClass = getClazz(target);

        if (sourceClass.equals(targetClass)) {
            BeanWrapper<Object> beanWrapper = getWrapper(sourceClass);
            PropertyAccessLoops.copyLoop(source, target, beanWrapper.getPropertyAccesses());
            return;
        }

        BeanWrapper<Object> sourceBeanWrapper = getWrapper(sourceClass);
        BeanWrapper<Object> targetBeanWrapper = getWrapper(targetClass);
        PropertyAccess<Object>[] targetBeanPropertyAccesses = targetBeanWrapper.getPropertyAccesses();
        Map<String, PropertyAccess<Object>> propertyAccessMapping = sourceBeanWrapper.getPropertyAccessMapping();
        PropertyAccessLoops.copyLoop(source, target, targetBeanPropertyAccesses, propertyAccessMapping);

    }

    public static void copyProperties(Object source, Object target, Set<String> ignoredPropertySet) {
        if (source == null || target == null) {
            return;
        }
        if (ignoredPropertySet == null || ignoredPropertySet.isEmpty()) {
            copyProperties(source, target);
            return;
        }
        Class<Object> sourceClass = getClazz(source);
        Class<Object> targetClass = getClazz(target);

        if (sourceClass.equals(targetClass)) {
            BeanWrapper<Object> beanWrapper = getWrapper(sourceClass);
            PropertyAccessLoops.copyLoop(source, target, beanWrapper.getPropertyAccesses(), ignoredPropertySet);
            return;
        }

        BeanWrapper<Object> sourceBeanWrapper = getWrapper(sourceClass);
        BeanWrapper<Object> targetBeanWrapper = getWrapper(targetClass);
        PropertyAccess<Object>[] targetBeanPropertyAccesses = targetBeanWrapper.getPropertyAccesses();
        Map<String, PropertyAccess<Object>> propertyAccessMapping = sourceBeanWrapper.getPropertyAccessMapping();
        PropertyAccessLoops.copyLoop(source, target, targetBeanPropertyAccesses, propertyAccessMapping, ignoredPropertySet);
    }
}
