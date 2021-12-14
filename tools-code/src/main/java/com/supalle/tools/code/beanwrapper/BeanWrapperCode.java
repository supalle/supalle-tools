package com.supalle.tools.code.beanwrapper;

public class BeanWrapperCode {

    private final Class<?> clazz;
    private final String wrapperPackageName;
    private final String wrapperClazzName;
    private final String wrapperCode;

    public BeanWrapperCode(Class<?> clazz, String wrapperPackageName, String wrapperClazzName, String wrapperCode) {
        this.clazz = clazz;
        this.wrapperPackageName = wrapperPackageName;
        this.wrapperClazzName = wrapperClazzName;
        this.wrapperCode = wrapperCode;
    }

    public Class<?> getClazz() {
        return clazz;
    }

    public String getWrapperPackageName() {
        return wrapperPackageName;
    }

    public String getWrapperClazzName() {
        return wrapperClazzName;
    }

    public String getWrapperCode() {
        return wrapperCode;
    }

    public String getWrapperFullName() {
        return getWrapperPackageName() + "." + getWrapperClazzName();
    }
}
