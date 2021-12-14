package com.supalle.tools.code.beanwrapper;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.*;

public class WrapperUtil {

    public static boolean isEmpty(String s) {
        return s == null || s.isEmpty();
    }

    public static boolean isEmpty(Collection<?> collection) {
        return collection == null || collection.isEmpty();
    }

    public static boolean isNotEmpty(Collection<?> collection) {
        return !isEmpty(collection);
    }

    public static Method[] findMethods(Class<?> clazz) {
        Map<String, Method> methodMap = new LinkedHashMap<>();
        Class<?> c = clazz;
        while (c != null && !"java.lang.Object".equals(c.getName())) {
            Method[] declaredMethods = c.getDeclaredMethods();
            if (declaredMethods.length > 0) {
                for (Method declaredMethod : declaredMethods) {
                    methodMap.putIfAbsent(declaredMethod.getName(), declaredMethod);
                }
            }
            c = c.getSuperclass();
        }
        return methodMap.values().toArray(new Method[0]);
    }

    public static Method[] filterGetters(Method[] methods) {
        return Arrays.stream(methods).filter(e -> e.getName().startsWith("get") || e.getName().startsWith("is")).toArray(Method[]::new);
    }

    public static Method[] filterSetters(Method[] methods) {
        return Arrays.stream(methods).filter(e -> e.getName().startsWith("set")).toArray(Method[]::new);
    }

    public static PropertyDescriptor[] findProperties(Class<?> clazz) {
        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(clazz);
            PropertyDescriptor[] propertyDescriptors = Optional.ofNullable(beanInfo.getPropertyDescriptors()).orElseGet(() -> new PropertyDescriptor[0]);
            return Arrays.stream(propertyDescriptors).filter(e -> !"class".equals(e.getName())).toArray(PropertyDescriptor[]::new);
        } catch (IntrospectionException e) {
            e.printStackTrace();
        }
        return new PropertyDescriptor[0];
    }

    public static Class<?> getGenericType(Class<?> clazz) {
        if (!clazz.isPrimitive()) {
            return clazz;
        }
        if (clazz == byte.class) {
            return Byte.class;
        }
        if (clazz == short.class) {
            return Short.class;
        }
        if (clazz == int.class) {
            return Integer.class;
        }
        if (clazz == long.class) {
            return Long.class;
        }
        if (clazz == float.class) {
            return Float.class;
        }
        if (clazz == double.class) {
            return Double.class;
        }
        if (clazz == boolean.class) {
            return Boolean.class;
        }
        if (clazz == char.class) {
            return Character.class;
        }
        return clazz;
    }
}
