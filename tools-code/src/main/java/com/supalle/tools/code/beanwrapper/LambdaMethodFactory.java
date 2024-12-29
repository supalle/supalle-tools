package com.supalle.tools.code.beanwrapper;

import com.supalle.tools.code.beanwrapper.function.*;

import java.lang.invoke.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Supplier;


/**
 * Lambda方法工厂
 * <p>参考和照搬了一部分fastjson2的代码</p>
 *
 * @see com.alibaba.fastjson2.writer.ObjectWriterCreator
 * @see com.alibaba.fastjson2.reader.ObjectReaderCreator
 * @see com.alibaba.fastjson2.util.TypeUtils
 */
public class LambdaMethodFactory {
    private static final MethodHandles.Lookup LOOKUP = MethodHandles.lookup();
    public static final MethodType METHOD_TYPE_SUPPLIER = MethodType.methodType(Supplier.class);
    public static final MethodType METHOD_TYPE_FUNCTION = MethodType.methodType(Function.class);
    public static final MethodType METHOD_TYPE_BI_CONSUMER = MethodType.methodType(BiConsumer.class);
    public static final MethodType METHOD_TYPE_VOID = MethodType.methodType(void.class);
    public static final MethodType METHOD_TYPE_OBJECT = MethodType.methodType(Object.class);
    public static final MethodType METHOD_TYPE_OBJECT_OBJECT = MethodType.methodType(Object.class, Object.class);
    public static final MethodType METHOD_TYPE_VOO = MethodType.methodType(void.class, Object.class, Object.class);
    private static final Map<Class<?>, LambdaGetterInfo> lambdaPrimitiveGetterMapping = new HashMap<>();
    private static final Map<Class<?>, LambdaSetterInfo> lambdaPrimitiveSetterMapping = new HashMap<>();

    static {
        lambdaPrimitiveGetterMapping.put(boolean.class, new LambdaGetterInfo(boolean.class, ToPredicateFunction.class, "test"));
        lambdaPrimitiveGetterMapping.put(char.class, new LambdaGetterInfo(char.class, ToCharFunction.class, "applyAsChar"));
        lambdaPrimitiveGetterMapping.put(byte.class, new LambdaGetterInfo(byte.class, ToByteFunction.class, "applyAsByte"));
        lambdaPrimitiveGetterMapping.put(short.class, new LambdaGetterInfo(short.class, ToShortFunction.class, "applyAsShort"));
        lambdaPrimitiveGetterMapping.put(int.class, new LambdaGetterInfo(int.class, ToIntFunction.class, "applyAsInt"));
        lambdaPrimitiveGetterMapping.put(long.class, new LambdaGetterInfo(long.class, ToLongFunction.class, "applyAsLong"));
        lambdaPrimitiveGetterMapping.put(float.class, new LambdaGetterInfo(float.class, ToFloatFunction.class, "applyAsFloat"));
        lambdaPrimitiveGetterMapping.put(double.class, new LambdaGetterInfo(double.class, ToDoubleFunction.class, "applyAsDouble"));

        lambdaPrimitiveSetterMapping.put(boolean.class, new LambdaSetterInfo(boolean.class, PredicateConsumer.class, "acceptForBoolean"));
        lambdaPrimitiveSetterMapping.put(char.class, new LambdaSetterInfo(char.class, CharConsumer.class, "acceptForChar"));
        lambdaPrimitiveSetterMapping.put(byte.class, new LambdaSetterInfo(byte.class, ByteConsumer.class, "acceptForByte"));
        lambdaPrimitiveSetterMapping.put(short.class, new LambdaSetterInfo(short.class, ShortConsumer.class, "acceptForShort"));
        lambdaPrimitiveSetterMapping.put(int.class, new LambdaSetterInfo(int.class, IntConsumer.class, "acceptForInt"));
        lambdaPrimitiveSetterMapping.put(long.class, new LambdaSetterInfo(long.class, LongConsumer.class, "acceptForLong"));
        lambdaPrimitiveSetterMapping.put(float.class, new LambdaSetterInfo(float.class, FloatConsumer.class, "acceptForFloat"));
        lambdaPrimitiveSetterMapping.put(double.class, new LambdaSetterInfo(double.class, DoubleConsumer.class, "acceptForDouble"));
    }

    @SuppressWarnings("unchecked")
    public static <T> Supplier<T> lambdaNoArgsConstructor(Class<T> beanClass) {
        Objects.requireNonNull(beanClass, "beanClass不能为null");
        try {
            MethodHandle handle = LOOKUP.findConstructor(beanClass, METHOD_TYPE_VOID);
            CallSite callSite = LambdaMetafactory.metafactory(
                    LOOKUP,
                    "get",
                    METHOD_TYPE_SUPPLIER,
                    METHOD_TYPE_OBJECT,
                    handle,
                    METHOD_TYPE_OBJECT
            );
            return (Supplier<T>) callSite.getTarget().invokeExact();
        } catch (Throwable e) {

            try {
                Constructor<T> constructor = beanClass.getConstructor();
                constructor.setAccessible(true);
                return () -> {
                    try {
                        return constructor.newInstance();
                    } catch (Exception ex) {
                        throw new RuntimeException(ex.getMessage(), ex);
                    }
                };
            } catch (Throwable ex) {
                throw new IllegalStateException("create lambdaNoArgsConstructor error: " + e.getMessage() + "； reflect ex:" + ex.getMessage(), e);
            }
        }
    }

    static class LambdaGetterInfo {
        final Class<?> fieldClass;
        final Class<?> supplierClass;
        final String methodName;
        final MethodType methodType;
        final MethodType invokedType;
        final MethodType samMethodType;

        LambdaGetterInfo(Class<?> fieldClass, Class<?> supplierClass, String methodName) {
            this.fieldClass = fieldClass;
            this.supplierClass = supplierClass;
            this.methodName = methodName;
            this.methodType = MethodType.methodType(fieldClass);
            this.invokedType = MethodType.methodType(supplierClass);
            this.samMethodType = MethodType.methodType(fieldClass, Object.class);
        }
    }

    @SuppressWarnings("unchecked")
    public static <T, V> Function<T, V> lambdaGetter(Method getterMethod) {
        if (getterMethod == null) {
            return null;
        }
        Class<?> fieldClass = getterMethod.getReturnType();
        LambdaGetterInfo buildInfo = lambdaPrimitiveGetterMapping.get(fieldClass);

        MethodType methodType;
        MethodType invokedType;
        String methodName;
        MethodType samMethodType;
        if (buildInfo != null) {
            methodType = buildInfo.methodType;
            invokedType = buildInfo.invokedType;
            methodName = buildInfo.methodName;
            samMethodType = buildInfo.samMethodType;
        } else {
            methodType = MethodType.methodType(fieldClass);
            invokedType = METHOD_TYPE_FUNCTION;
            methodName = "apply";
            samMethodType = METHOD_TYPE_OBJECT_OBJECT;
        }
        try {
            MethodHandle target = LOOKUP.findVirtual(getterMethod.getDeclaringClass(),
                    getterMethod.getName(),
                    methodType
            );
            MethodType instantiatedMethodType = target.type();

            CallSite callSite = LambdaMetafactory.metafactory(
                    LOOKUP,
                    methodName,
                    invokedType,
                    samMethodType,
                    target,
                    instantiatedMethodType
            );
            return (Function<T, V>) callSite.getTarget().invoke();
        } catch (Throwable e) {
            throw new IllegalStateException("create lambdaGetter error: " + e.getMessage(), e);
        }
    }

    static class LambdaSetterInfo {
        final String methodName;
        final Class<?> fieldClass;
        final MethodType samMethodType;
        final MethodType methodType;
        final MethodType invokedType;

        LambdaSetterInfo(Class<?> fieldClass, Class<?> functionClass, String methodName) {
            this.methodName = methodName;
            this.fieldClass = fieldClass;
            this.samMethodType = MethodType.methodType(void.class, Object.class, fieldClass);
            this.methodType = MethodType.methodType(void.class, fieldClass);
            this.invokedType = MethodType.methodType(functionClass);
        }
    }

    @SuppressWarnings("unchecked")
    public static <T, V> BiConsumer<T, V> lambdaSetter(Method setterMethod) {
        if (setterMethod == null) {
            return null;
        }
        MethodHandles.Lookup lookup = MethodHandles.lookup();
        Class<?> declaringClass = setterMethod.getDeclaringClass();
        Class<?> returnType = setterMethod.getReturnType();
        Class<?>[] parameterTypes = setterMethod.getParameterTypes();
        if (parameterTypes.length != 1) {
            throw new IllegalArgumentException("setter method parameters size must be 1.");
        }
        Class<?> parameterType = parameterTypes[0];

        // MethodType samMethodType = MethodType.methodType(void.class, Object.class, Object.class);    // METHOD_TYPE_BI_CONSUMER
        // MethodType invokedType = MethodType.methodType(BiConsumer.class);                            // METHOD_TYPE_BI_CONSUMER
        // MethodType methodType = MethodType.methodType(returnType, parameterType);

        LambdaSetterInfo buildInfo = lambdaPrimitiveSetterMapping.get(parameterType);

        MethodType methodType;
        MethodType invokedType;
        String methodName;
        MethodType samMethodType;
        if (buildInfo != null) {
            methodType = buildInfo.methodType;
            invokedType = buildInfo.invokedType;
            methodName = buildInfo.methodName;
            samMethodType = buildInfo.samMethodType;
        } else {
            methodType = MethodType.methodType(returnType, parameterType);
            invokedType = METHOD_TYPE_BI_CONSUMER;
            methodName = "accept";
            samMethodType = METHOD_TYPE_VOO;
        }

        try {
            MethodHandle target = lookup.findVirtual(declaringClass, setterMethod.getName(), methodType);
            MethodType instantiatedMethodType = MethodType.methodType(void.class, declaringClass, parameterType);
            CallSite callSite = LambdaMetafactory.metafactory(
                    lookup,
                    methodName,
                    invokedType,
                    samMethodType,
                    target,
                    instantiatedMethodType
            );

            return (BiConsumer<T, V>) callSite.getTarget().invoke();
        } catch (Throwable e) {
            throw new IllegalStateException("create lambdaSetter error", e);
        }
    }

}
