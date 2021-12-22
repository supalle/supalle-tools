package com.supalle.tools.code.bytecode;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static com.supalle.tools.code.bytecode.ClassFileConstants.*;

public class ClassMain {
    public static void main(String[] args) {
        ClassFile classFile = ClassFile.create("Lcom/supalle/tools/code/bytecode/PropertyAccessorImpl2")
                .accessFlags((short) (ACC_PUBLIC | ACC_FINAL))
                .superClass("Ljava/lang/Object")
                .interfaces(Collections.singleton("Lcom/supalle/tools/code/bytecode/PropertyAccessor"))
                .methods(Arrays.asList(
                        MethodInfo.create("set")
                                .accessFlags((short) (ACC_PUBLIC | ACC_FINAL))
                                .paramTypes(Arrays.asList("Ljava/lang/Object", "Ljava/lang/Object"))
                                .returnType("V")
                                .codes(Arrays.asList(
                                        CodeRow.of(opc_iload_0),
                                        CodeRow.of(opc_iload_0)
                                ))
                ));
    }

    public static MethodInfo getter(PropertyDescriptor propertyDescriptor) {
        Class<?> propertyType = propertyDescriptor.getPropertyType();
        Method readMethod = propertyDescriptor.getReadMethod();

        MethodInfo methodInfo = MethodInfo.create("get")
                .accessFlags((short) (ACC_PUBLIC | ACC_FINAL))
                .paramTypes(Collections.singletonList("Ljava/lang/Object"))
                .returnType("Ljava/lang/Object");

        List<CodeRow> codes = new ArrayList<>();

        codes.add(CodeRow.of(opc_aaload));
        codes.add(CodeRow.of(opc_checkcast, toFullClassName(readMethod.getDeclaringClass())));
        codes.add(CodeRow.of(opc_invokevirtual, readMethod));

        if (propertyType.isPrimitive()) {
            CodeRow boxed = boxed(propertyType);
            if (boxed != null) {
                codes.add(boxed);
            }
        }

        codes.add(CodeRow.of(opc_areturn));

        return methodInfo.codes(codes);
    }

    private static CodeRow boxed(Class<?> clazz) {
        assert clazz.isPrimitive();
        if (clazz == Integer.TYPE) {
            return CodeRow.of(opc_invokestatic, getMethod(Integer.class, "valueOf", Integer.TYPE));
        } else if (clazz == Boolean.TYPE) {
            return CodeRow.of(opc_invokestatic, getMethod(Boolean.class, "valueOf", Boolean.TYPE));
        } else if (clazz == Byte.TYPE) {
            return CodeRow.of(opc_invokestatic, getMethod(Byte.class, "valueOf", Byte.TYPE));
        } else if (clazz == Character.TYPE) {
            return CodeRow.of(opc_invokestatic, getMethod(Character.class, "valueOf", Character.TYPE));
        } else if (clazz == Short.TYPE) {
            return CodeRow.of(opc_invokestatic, getMethod(Short.class, "valueOf", Short.TYPE));
        } else if (clazz == Double.TYPE) {
            return CodeRow.of(opc_invokestatic, getMethod(Double.class, "valueOf", Double.TYPE));
        } else if (clazz == Float.TYPE) {
            return CodeRow.of(opc_invokestatic, getMethod(Float.class, "valueOf", Float.TYPE));
        } else if (clazz == Long.TYPE) {
            return CodeRow.of(opc_invokestatic, getMethod(Long.class, "valueOf", Long.TYPE));
        }
        return null;
    }

    private static Method getMethod(Class<?> primitiveClass, String name, Class<?>... parameterTypes) {
        try {
            return primitiveClass.getMethod(name, parameterTypes);
        } catch (NoSuchMethodException ignored) {
        }
        return null;
    }

    public static String toFullClassName(Class<?> clazz) {
        if (clazz.isPrimitive()) {
            if (clazz == Integer.TYPE) {
                return "I";
            } else if (clazz == Void.TYPE) {
                return "V";
            } else if (clazz == Boolean.TYPE) {
                return "Z";
            } else if (clazz == Byte.TYPE) {
                return "B";
            } else if (clazz == Character.TYPE) {
                return "C";
            } else if (clazz == Short.TYPE) {
                return "S";
            } else if (clazz == Double.TYPE) {
                return "D";
            } else if (clazz == Float.TYPE) {
                return "F";
            } else if (clazz == Long.TYPE) {
                return "J";
            }
        }
        if (clazz.isArray()) {
            return "[" + toFullClassName(clazz.getComponentType());
        }
        return "L" + clazz.getName().replace(".", "/");
    }

}
