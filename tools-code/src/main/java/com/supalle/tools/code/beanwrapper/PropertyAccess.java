package com.supalle.tools.code.beanwrapper;

import lombok.Data;

import java.beans.PropertyDescriptor;
import java.util.function.BiConsumer;
import java.util.function.Function;

/**
 * 可以考虑把PropertyAccess变成Getter对象，不用再getLambdaGetter()一遍，可以增加很多的效率
 * <br/>从BeanWrapper到getter中间多了一层PropertyAccess[]，效率减少了1/4这样
 *
 * @param <T>
 */
@Data
public class PropertyAccess<T> {

    private final String propertyName;
    private final Class<?> propertyType;
    private final boolean hasDeep;
    private final Function<T, ?> lambdaGetter;
    private final BiConsumer<T, Object> lambdaSetter;

    public PropertyAccess(PropertyDescriptor propertyDescriptor) {
        this.propertyName = propertyDescriptor.getName();
        this.propertyType = propertyDescriptor.getPropertyType();
        this.hasDeep = BeanWrapperConfig.hasDeep(propertyType);
        this.lambdaGetter = LambdaMethodFactory.lambdaGetter(propertyDescriptor.getReadMethod());
        this.lambdaSetter = LambdaMethodFactory.lambdaSetter(propertyDescriptor.getWriteMethod());
    }

    public Object get(T bean) {
        if (lambdaGetter == null) {
            throw new NullPointerException("property [" + propertyName + "] lambdaGetter is null.");
        }
        return lambdaGetter.apply(bean);
    }

    public <V> void set(T bean, V v) {
        if (lambdaSetter == null) {
            throw new NullPointerException("property [" + propertyName + "] lambdaSetter is null.");
        }
        lambdaSetter.accept(bean, v);
    }

}