package com.supalle.tools.code.beanwrapper;

public abstract class PropertyAccess<O, T> {

    private final String propertyName;
    private final Class<T> propertyType;
    private final boolean hasDeep;

    public PropertyAccess(String propertyName, Class<T> propertyType) {
        this.propertyName = propertyName;
        this.propertyType = propertyType;
        this.hasDeep = BeanWrapperConfig.hasDeep(propertyType);
    }

    public String getPropertyName() {
        return propertyName;
    }

    public Class<T> getPropertyType() {
        return propertyType;
    }

    public abstract T get(O bean);

    public abstract void set(O bean, T v);

    public boolean isHasDeep() {
        return hasDeep;
    }
}
