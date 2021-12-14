package com.supalle.tools.code.beanwrapper;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public interface CopyWrapper<S, T> extends ConstructorWrapper<T>, PropertyWrapper {

    default T copy(S bean) {
        if (bean == null) {
            return null;
        }
        T t = instance();
        copyTo(bean, t);
        return t;
    }

    default T copy(S bean, String... ignoredProperties) {
        if (bean == null) {
            return null;
        }
        T t = instance();
        copyTo(bean, t, ignoredProperties);
        return t;
    }

    default T copy(S bean, Set<String> ignoredPropertySet) {
        if (bean == null) {
            return null;
        }
        T t = instance();
        copyTo(bean, t, ignoredPropertySet);
        return t;
    }

    void copyTo(S sourceBean, T targetBean);

    default void copyTo(S sourceBean, T targetBean, String... ignoredProperties) {
        if (sourceBean == null || targetBean == null) {
            return;
        }
        if (ignoredProperties != null && ignoredProperties.length > 0) {
            copyTo(sourceBean, targetBean, new HashSet<>(Arrays.asList(ignoredProperties)));
            return;
        }
        copyTo(sourceBean, targetBean);
    }

    void copyTo(S sourceBean, T targetBean, Set<String> ignoredPropertySet);

    default void copyPropertiesTo(S sourceBean, T targetBean, String... properties) {
        if (sourceBean == null || targetBean == null) {
            return;
        }
        if (properties != null && properties.length > 0) {
            copyPropertiesTo(sourceBean, targetBean, new HashSet<>(Arrays.asList(properties)));
            return;
        }
        copyTo(sourceBean, targetBean);
    }

    void copyPropertiesTo(S sourceBean, T targetBean, Set<String> propertySet);

}
