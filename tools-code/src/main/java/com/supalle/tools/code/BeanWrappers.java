package com.supalle.tools.code;

import com.supalle.tools.code.beanwrapper.*;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;

public class BeanWrappers {

    public static final ConcurrentMap<Class<?>, BeanWrapper<?>> BEAN_WRAPPER_CACHE = new ConcurrentHashMap<>(32);

    public static <T> BeanWrapper<T> getWrapper(Class<T> clazz) {
        BeanWrapper<T> beanWrapper = (BeanWrapper<T>) BEAN_WRAPPER_CACHE.get(clazz);
        if (beanWrapper == null) {
            synchronized (BEAN_WRAPPER_CACHE) {
                beanWrapper = (BeanWrapper<T>) BEAN_WRAPPER_CACHE.get(clazz);
                if (beanWrapper != null) {
                    return beanWrapper;
                }
                BeanWrapperCode beanWrapperCode = BeanWrapperCodeBuilder.build(clazz);
                Class<? extends BeanWrapper> wrapperClass = existsBeaWrapper(beanWrapperCode.getWrapperFullName());
                if (wrapperClass == null) {
                    wrapperClass = (Class<? extends BeanWrapper>) JavaCompilers.getCompiler()
                            .compile(beanWrapperCode.getWrapperFullName(), beanWrapperCode.getWrapperCode());
                }
                try {
                    beanWrapper = wrapperClass.newInstance();
                    BEAN_WRAPPER_CACHE.put(clazz, beanWrapper);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return beanWrapper;
    }

    private static Class<? extends BeanWrapper<?>> existsBeaWrapper(String beanWrapperFullName) {
        try {
            Class<?> clazz = Class.forName(beanWrapperFullName);
            return (Class<? extends BeanWrapper<?>>) clazz;
        } catch (ClassNotFoundException e) {
            return null;
        }
    }


    public static Object copy(Object bean) {
        if (bean == null) {
            return null;
        }
        Object t = getWrapper(bean.getClass()).instance();
        copyTo(bean, t);
        return t;
    }


    public static Object copy(Object bean, String... ignoredProperties) {
        if (bean == null) {
            return null;
        }
        Object t = getWrapper(bean.getClass()).instance();
        copyTo(bean, t, ignoredProperties);
        return t;
    }


    public static Object copy(Object bean, Set<String> ignoredPropertySet) {
        if (bean == null) {
            return null;
        }
        Object t = getWrapper(bean.getClass()).instance();
        copyTo(bean, t, ignoredPropertySet);
        return t;
    }


    public static void copyTo(BeanWrapper beanWrapper, Object sourceBean, Object targetBean) {
        if (sourceBean == null || targetBean == null || beanWrapper == null) {
            return;
        }
        PropertyAccess[] propertyAccesses = beanWrapper.getPropertyAccesses();
        for (PropertyAccess propertyAccess : propertyAccesses) {
            propertyAccess.set(targetBean, propertyAccess.get(sourceBean));
        }
    }

    public static void copyTo(Object sourceBean, Object targetBean) {
        if (sourceBean == null || targetBean == null) {
            return;
        }
        Class<?> sourceBeanClass = sourceBean.getClass();
        Class<?> targetBeanClass = targetBean.getClass();
        BeanWrapper sourceWrapper = getWrapper(sourceBeanClass);
        if (sourceBeanClass == targetBeanClass) {
            Loops.loop(sourceBean, targetBean, sourceWrapper.getPropertyAccesses());
            return;
        }
        BeanWrapper targetWrapper = getWrapper(targetBeanClass);

        PropertyAccess[] sourceWrapperPropertyAccesses = sourceWrapper.getPropertyAccesses();
        PropertyAccess[] targetWrapperPropertyAccesses = targetWrapper.getPropertyAccesses();

        if (targetWrapperPropertyAccesses.length > 16) {
            Map<String, PropertyAccess> targetMap = Arrays.stream(targetWrapperPropertyAccesses).collect(Collectors.toMap(PropertyAccess::getPropertyName, e -> e));
            for (PropertyAccess sourceWrapperPropertyAccess : sourceWrapperPropertyAccesses) {
                PropertyAccess targetPropertyAccess = targetMap.get(sourceWrapperPropertyAccess.getPropertyName());
                if (targetPropertyAccess != null) {
                    targetPropertyAccess.set(targetBean, sourceWrapperPropertyAccess.get(sourceBean));
                }
            }
        } else {
            for (PropertyAccess sourceWrapperPropertyAccess : sourceWrapperPropertyAccesses) {
                for (PropertyAccess targetWrapperPropertyAccess : targetWrapperPropertyAccesses) {
                    if (sourceWrapperPropertyAccess.getPropertyName().equals(targetWrapperPropertyAccess.getPropertyName())) {
                        targetWrapperPropertyAccess.set(targetBean, sourceWrapperPropertyAccess.get(sourceBean));
                        break;
                    }
                }
            }
        }
    }


    public static void copyTo(Object sourceBean, Object targetBean, String... ignoredProperties) {
        if (sourceBean == null || targetBean == null) {
            return;
        }
        if (ignoredProperties != null && ignoredProperties.length > 0) {
            copyTo(sourceBean, targetBean, new HashSet<>(Arrays.asList(ignoredProperties)));
            return;
        }
        copyTo(sourceBean, targetBean);
    }


    public static void copyTo(Object sourceBean, Object targetBean, Set<String> ignoredPropertySet) {
        if (sourceBean == null || targetBean == null) {
            return;
        }
        if (WrapperUtil.isEmpty(ignoredPropertySet)) {
            copyTo(sourceBean, targetBean);
            return;
        }
        BeanWrapper sourceWrapper = getWrapper(sourceBean.getClass());
        BeanWrapper targetWrapper = getWrapper(sourceBean.getClass());

        PropertyAccess[] sourceWrapperPropertyAccesses = sourceWrapper.getPropertyAccesses();
        PropertyAccess[] targetWrapperPropertyAccesses = targetWrapper.getPropertyAccesses();

        Map<String, PropertyAccess> targetMap = Arrays.stream(targetWrapperPropertyAccesses).filter(e -> !ignoredPropertySet.contains(e.getPropertyName())).collect(Collectors.toMap(PropertyAccess::getPropertyName, e -> e));
        for (PropertyAccess sourceWrapperPropertyAccess : sourceWrapperPropertyAccesses) {
            PropertyAccess targetPropertyAccess = targetMap.get(sourceWrapperPropertyAccess.getPropertyName());
            if (targetPropertyAccess != null) {
                targetPropertyAccess.set(targetBean, sourceWrapperPropertyAccess.get(sourceBean));
            }
        }
    }


    public static void copyPropertiesTo(Object sourceBean, Object targetBean, String... properties) {
        if (sourceBean == null || targetBean == null) {
            return;
        }
        if (properties != null && properties.length > 0) {
            copyPropertiesTo(sourceBean, targetBean, new HashSet<>(Arrays.asList(properties)));
            return;
        }
        copyTo(sourceBean, targetBean);
    }


    public static void copyPropertiesTo(Object sourceBean, Object targetBean, Set<String> propertySet) {
        if (sourceBean == null || targetBean == null || WrapperUtil.isEmpty(propertySet)) {
            return;
        }
        BeanWrapper sourceWrapper = getWrapper(sourceBean.getClass());
        BeanWrapper targetWrapper = getWrapper(sourceBean.getClass());

        PropertyAccess[] sourceWrapperPropertyAccesses = sourceWrapper.getPropertyAccesses();
        PropertyAccess[] targetWrapperPropertyAccesses = targetWrapper.getPropertyAccesses();

        Map<String, PropertyAccess> targetMap = Arrays.stream(targetWrapperPropertyAccesses).filter(e -> propertySet.contains(e.getPropertyName())).collect(Collectors.toMap(PropertyAccess::getPropertyName, e -> e));
        for (PropertyAccess sourceWrapperPropertyAccess : sourceWrapperPropertyAccesses) {
            PropertyAccess targetPropertyAccess = targetMap.get(sourceWrapperPropertyAccess.getPropertyName());
            if (targetPropertyAccess != null) {
                targetPropertyAccess.set(targetBean, sourceWrapperPropertyAccess.get(sourceBean));
            }
        }
    }

    public static Object copyDeep(Object bean) {
        if (bean == null) {
            return null;
        }
        Object t = getWrapper(bean.getClass()).instance();
        copyTo(bean, t);
        return t;
    }

    public static Object copyDeep(Object bean, String... ignoredProperties) {
        if (bean == null) {
            return null;
        }
        Object t = getWrapper(bean.getClass()).instance();
        copyTo(bean, t, ignoredProperties);
        return t;
    }

    public static Object copyDeep(Object bean, Set<String> ignoredPropertySet) {
        if (bean == null) {
            return null;
        }
        Object t = getWrapper(bean.getClass()).instance();
        copyTo(bean, t, ignoredPropertySet);
        return t;
    }

    public static void copyDeepTo(Object sourceBean, Object targetBean) {
        if (sourceBean == null || targetBean == null) {
            return;
        }
        Class<?> sourceBeanClass = sourceBean.getClass();
        Class<?> targetBeanClass = targetBean.getClass();
        BeanWrapper sourceWrapper = getWrapper(sourceBeanClass);
        if (targetBeanClass == targetBeanClass) {
            for (PropertyAccess propertyAccess : sourceWrapper.getPropertyAccesses()) {
                copyPropertyDeep(sourceBean, targetBean, propertyAccess, propertyAccess);
            }
            return;
        }
        BeanWrapper targetWrapper = getWrapper(targetBeanClass);

        PropertyAccess[] sourceWrapperPropertyAccesses = sourceWrapper.getPropertyAccesses();
        PropertyAccess[] targetWrapperPropertyAccesses = targetWrapper.getPropertyAccesses();

        if (targetWrapperPropertyAccesses.length > 16) {
            Map<String, PropertyAccess> targetMap = Arrays.stream(targetWrapperPropertyAccesses).collect(Collectors.toMap(PropertyAccess::getPropertyName, e -> e));
            for (PropertyAccess sourceWrapperPropertyAccess : sourceWrapperPropertyAccesses) {
                PropertyAccess targetPropertyAccess = targetMap.get(sourceWrapperPropertyAccess.getPropertyName());
                if (targetPropertyAccess != null) {
                    copyPropertyDeep(sourceBean, targetBean, sourceWrapperPropertyAccess, targetPropertyAccess);
                }
            }
        } else {
            for (PropertyAccess sourceWrapperPropertyAccess : sourceWrapperPropertyAccesses) {
                for (PropertyAccess targetWrapperPropertyAccess : targetWrapperPropertyAccesses) {
                    if (sourceWrapperPropertyAccess.getPropertyName().equals(targetWrapperPropertyAccess.getPropertyName())) {
                        copyPropertyDeep(sourceBean, targetBean, sourceWrapperPropertyAccess, targetWrapperPropertyAccess);
                        break;
                    }
                }
            }
        }

    }

    public static void copyDeepTo(Object sourceBean, Object targetBean, String... ignoredProperties) {
        if (sourceBean == null || targetBean == null) {
            return;
        }
        if (ignoredProperties != null && ignoredProperties.length > 0) {
            copyDeepTo(sourceBean, targetBean, new HashSet<>(Arrays.asList(ignoredProperties)));
            return;
        }
        copyDeepTo(sourceBean, targetBean);
    }

    public static void copyDeepTo(Object sourceBean, Object targetBean, Set<String> ignoredPropertySet) {
        if (sourceBean == null || targetBean == null || WrapperUtil.isEmpty(ignoredPropertySet)) {
            return;
        }

        BeanWrapper sourceWrapper = getWrapper(sourceBean.getClass());
        BeanWrapper targetWrapper = getWrapper(sourceBean.getClass());

        PropertyAccess[] sourceWrapperPropertyAccesses = sourceWrapper.getPropertyAccesses();
        PropertyAccess[] targetWrapperPropertyAccesses = targetWrapper.getPropertyAccesses();

        Map<String, PropertyAccess> targetMap = Arrays.stream(targetWrapperPropertyAccesses).filter(e -> !ignoredPropertySet.contains(e.getPropertyName())).collect(Collectors.toMap(PropertyAccess::getPropertyName, e -> e));
        for (PropertyAccess sourceWrapperPropertyAccess : sourceWrapperPropertyAccesses) {
            PropertyAccess targetPropertyAccess = targetMap.get(sourceWrapperPropertyAccess.getPropertyName());
            if (targetPropertyAccess != null) {
                copyPropertyDeep(sourceBean, targetBean, sourceWrapperPropertyAccess, targetPropertyAccess);
            }
        }
    }

    public static void copyDeepPropertiesTo(Object sourceBean, Object targetBean, String... properties) {
        if (sourceBean == null || targetBean == null) {
            return;
        }
        if (properties != null && properties.length > 0) {
            copyDeepPropertiesTo(sourceBean, targetBean, new HashSet<>(Arrays.asList(properties)));
            return;
        }
        copyDeepTo(sourceBean, targetBean);
    }

    public static void copyDeepPropertiesTo(Object sourceBean, Object targetBean, Set<String> properties) {
        if (sourceBean == null || targetBean == null) {
            return;
        }
        if (WrapperUtil.isEmpty(properties)) {
            copyDeepTo(sourceBean, targetBean);
            return;
        }
        BeanWrapper sourceWrapper = getWrapper(sourceBean.getClass());
        BeanWrapper targetWrapper = getWrapper(sourceBean.getClass());

        PropertyAccess[] sourceWrapperPropertyAccesses = sourceWrapper.getPropertyAccesses();
        PropertyAccess[] targetWrapperPropertyAccesses = targetWrapper.getPropertyAccesses();

        Map<String, PropertyAccess> targetMap = Arrays.stream(targetWrapperPropertyAccesses).filter(e -> properties.contains(e.getPropertyName())).collect(Collectors.toMap(PropertyAccess::getPropertyName, e -> e));
        for (PropertyAccess sourceWrapperPropertyAccess : sourceWrapperPropertyAccesses) {
            PropertyAccess targetPropertyAccess = targetMap.get(sourceWrapperPropertyAccess.getPropertyName());
            if (targetPropertyAccess != null) {
                copyPropertyDeep(sourceBean, targetBean, sourceWrapperPropertyAccess, targetPropertyAccess);
            }
        }
    }

    private static void copyPropertyDeep(Object sourceBean, Object targetBean, PropertyAccess sourceWrapperPropertyAccess, PropertyAccess targetPropertyAccess) {
        if (targetPropertyAccess.isHasDeep()) {
            Object v = sourceWrapperPropertyAccess.get(sourceBean);
            if (v == null) {
                targetPropertyAccess.set(targetBean, null);
            } else {
                targetPropertyAccess.set(targetBean, copyDeep(v));
            }
        } else {
            targetPropertyAccess.set(targetBean, sourceWrapperPropertyAccess.get(sourceBean));
        }
    }

}
