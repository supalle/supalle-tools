package com.supalle.tools.code.beanwrapper;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class BeanWrapperCodeBuilder {

    private static final String WRAPPER_PACKAGE = BeanWrapperCodeBuilder.class.getPackage().getName();


    public static BeanWrapperCode build(Class<?> clazz) {
        assertNoArgsConstructor(clazz);

        BeanWrapperCodeOptions options = new BeanWrapperCodeOptions();
        options.setClazz(clazz);
        options.setPackageName(clazz.getPackage().getName());
        options.setClazzName(getFormatSimpleName(clazz) + "_BeanWrapperImpl");
        options.setInstanceMethod(buildInstanceMethod(clazz));

        PropertyDescriptor[] properties = WrapperUtil.findProperties(clazz);
        List<String> propertyAccesses = options.getPropertyAccesses();
        for (PropertyDescriptor property : properties) {
            propertyAccesses.add(buildPropertyAccess(clazz, property));
        }

        String code = generateCode(options);

        return new BeanWrapperCode(clazz, options.getPackageName(), options.getClazzName(), code);
    }

    public static void assertNoArgsConstructor(Class<?> clazz) {
        try {
            clazz.newInstance();
        } catch (Exception e) {
            throw new IllegalArgumentException("class: " + clazz.getName() + " NoArgsConstructor not found.", e);
        }
    }

    private static String generateCode(BeanWrapperCodeOptions options) {
        String template = "package %s;" +
                "public class %s implements %s.BeanWrapper<%s> {\n" +
                "    private final %s.PropertyAccess<?,?>[] propertyAccesses;\n" +
                "    @Override\n" +
                "    public %s.PropertyAccess<?,?>[] getPropertyAccesses() { return this.propertyAccesses; }\n" +
                "    %s\n" +
                "    public %s() {\n" +
                "        this.propertyAccesses = new %s.PropertyAccess<?,?>[%d];\n" +
                "        %s\n" +
                "    }" +
                "}";

        return String.format(template, options.getPackageName()
                , options.getClazzName(), WRAPPER_PACKAGE, getFormatClassName(options.getClazz())
                , WRAPPER_PACKAGE, WRAPPER_PACKAGE
                , options.getInstanceMethod(), options.getClazzName()
                , WRAPPER_PACKAGE, options.getPropertyAccesses().size()
                , buildSetPropertyAccesses(options.getPropertyAccesses()));
    }

    private static Object buildSetPropertyAccesses(List<String> propertyAccesses) {
        int index = 0;
        List<String> lines = new ArrayList<>();
        for (String propertyAccess : propertyAccesses) {
            lines.add("        this.propertyAccesses[" + index + "]=" + propertyAccess + ";");
            index++;
        }
        return String.join("\n", lines);
    }

    private static String getFormatSimpleName(Class<?> clazz) {
        String clazzName = clazz.getSimpleName();
        if (clazz.isAnonymousClass()) {
            Class<?> superclass = clazz.getSuperclass();
            while (superclass.isMemberClass() || superclass.isAnonymousClass()) {
                if (superclass.isMemberClass())
                    superclass = superclass.getDeclaringClass();
                if (superclass.isAnonymousClass())
                    superclass = superclass.getSuperclass();
            }
            clazzName = superclass.getSimpleName();
            String formatClassTypeName = getFormatClassTypeName(clazz);
            clazzName += "_" + formatClassTypeName.substring(formatClassTypeName.lastIndexOf(".") + 1);
        }
        return clazzName;
    }

    private static String getFormatClassName(Class<?> clazz) {
        String clazzName = clazz.getName();
        if (clazz.isAnonymousClass()) {
            return getFormatClassName(clazz.getSuperclass());
        }
        if (clazz.isMemberClass()) {
            Class<?> declaringClass = clazz.getDeclaringClass();
            return getFormatClassName(declaringClass) + "." + clazzName.substring(declaringClass.getName().length() + 1);
        }
        return clazzName;
    }

    private static String getFormatClassTypeName(Class<?> clazz) {
        String clazzName = clazz.getTypeName();
        if (clazz.isArray()) {
            Class<?> componentType = clazz.getComponentType();
            return getFormatClassTypeName(componentType) + "[]";
        }
        if (clazz.isAnonymousClass()) {
            Class<?> superclass = clazz.getSuperclass();
            while (superclass.isMemberClass() || superclass.isAnonymousClass()) {
                if (superclass.isMemberClass())
                    superclass = superclass.getDeclaringClass();
                if (superclass.isAnonymousClass())
                    superclass = superclass.getSuperclass();
            }
            return getFormatClassTypeName(superclass) + "." + clazzName.substring(superclass.getName().length() + 1);
        }
        if (clazz.isMemberClass()) {
            Class<?> declaringClass = clazz.getDeclaringClass();
            return getFormatClassTypeName(declaringClass) + "." + clazzName.substring(declaringClass.getName().length() + 1);
        }
        return clazzName;
    }

    private static String buildInstanceMethod(Class<?> clazz) {
        if (clazz.isAnonymousClass()) {
            return buildAnonymousClassInstanceMethod(clazz);
        }
        return String.format("@Override public %s instance() { return new %s(); }", getFormatClassName(clazz), getFormatClassName(clazz));
    }

    private static String buildAnonymousClassInstanceMethod(Class<?> clazz) {
        String template = "public final Class<?> clazz;\n" +
                "    {\n" +
                "          try {\n" +
                "              clazz = Class.forName(\"%s\");\n" +
                "          } catch (Exception e) {\n" +
                "              throw new RuntimeException(e);\n" +
                "          }\n" +
                "    }\n" +
                "    @Override public %s instance() { try { return (%s)clazz.newInstance(); } catch (Exception e) { throw new RuntimeException(e); } }";
        return String.format(template, clazz.getName()
                , getFormatClassName(clazz)
                , getFormatClassName(clazz));
    }

    private static String buildPropertyAccess(Class<?> clazz, PropertyDescriptor property) {
        String propertyName = property.getName();
        Class<?> propertyType = property.getPropertyType();
        Class<?> genericType = WrapperUtil.getGenericType(propertyType);

        String propertyAccessClass = String.format("new %s.PropertyAccess<%s,%s>(\"%s\",%s.class)"
                , WRAPPER_PACKAGE, getFormatClassTypeName(clazz), getFormatClassTypeName(genericType), propertyName, getFormatClassTypeName(propertyType));

        String getter = buildGetter(clazz, property, propertyType, genericType);
        String setter = buildSetter(clazz, property, propertyType, genericType);

        return String.join("\n", propertyAccessClass, "{", getter, setter, "}");
    }

    private static String buildSetter(Class<?> clazz, PropertyDescriptor property, Class<?> propertyType, Class<?> genericType) {
        String method = "@Override public void set(%s o, %s v) { ";
        method = String.format(method, getFormatClassTypeName(clazz), getFormatClassTypeName(genericType));
        if (property.getWriteMethod() != null) {
            method += "o." + property.getWriteMethod().getName();
            if (!propertyType.isPrimitive()) {
                method += "(v);";
            } else if (boolean.class.equals(propertyType)) {
                method += "(v == null ? false : v);";
            } else {
                method += "(v == null ? ((" + propertyType.getTypeName() + ")0) : v);";
            }
        }
        method += " }";
        return method;
    }

    private static String buildGetter(Class<?> clazz, PropertyDescriptor property, Class<?> propertyType, Class<?> genericType) {
        String method = "@Override public %s get(%s o) { ";
        method = String.format(method, getFormatClassTypeName(genericType), getFormatClassTypeName(clazz));
        if (property.getReadMethod() != null) {
            method += "return o." + property.getReadMethod().getName() + "();";
        } else {
            if (!propertyType.isPrimitive()) {
                method += "return null;";
            } else if (boolean.class.equals(propertyType)) {
                method += "return false;";
            } else {
                method += "return ((" + propertyType.getTypeName() + ")0);";
            }
        }
        method += " }";
        return method;
    }

    public static void main(String[] args) throws ClassNotFoundException {

        Bean.BB bean3 = new Bean.BB() {

        };

        Class<?> clazz = bean3.getClass();
        System.out.println(clazz.isMemberClass());

        System.out.println(clazz.getTypeName());
        System.out.println(getFormatClassTypeName(clazz));
        System.out.println(clazz.getTypeName());
        System.out.println(clazz.getTypeName());
        System.out.println(getFormatClassTypeName(clazz));

        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            System.out.println(field.getName());
        }

        Class<?> aClass = Class.forName("com.supalle.tools.code.beanwrapper.BeanWrapperCodeBuilder$1");
        System.out.println(aClass);

        Bean<Long> bean = new Bean<>();
        BeanWrapperCode beanWrapperCode = BeanWrapperCodeBuilder.build(clazz);
        System.out.println(beanWrapperCode.getWrapperCode());

    }

    public static class Bean<T> {

        public T[] getBean2() {
            return null;
        }

        public void setBean2(T bean2) {

        }

        public static class BB {

        }
    }

    static class Bean2<T> {

    }

    static class BeanWrapperCodeOptions {
        private Class<?> clazz;
        private String packageName;
        private String clazzName;
        private String instanceMethod;
        private final List<String> propertyAccesses = new ArrayList<>();

        public Class<?> getClazz() {
            return clazz;
        }

        public void setClazz(Class<?> clazz) {
            this.clazz = clazz;
        }

        public String getPackageName() {
            return packageName;
        }

        public void setPackageName(String packageName) {
            this.packageName = packageName;
        }

        public String getClazzName() {
            return clazzName;
        }

        public void setClazzName(String clazzName) {
            this.clazzName = clazzName;
        }

        public String getInstanceMethod() {
            return instanceMethod;
        }

        public void setInstanceMethod(String instanceMethod) {
            this.instanceMethod = instanceMethod;
        }

        public List<String> getPropertyAccesses() {
            return propertyAccesses;
        }
    }

}
