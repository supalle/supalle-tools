package com.supalle.tools.code;

import com.supalle.tools.code.beanwrapper.BeanWrapper;
import com.supalle.tools.code.beanwrapper.BeanWrapperConfig;
import com.supalle.tools.code.beanwrapper.PropertyAccess;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Map;
import java.util.function.Function;

public class JsonFormat<T> implements Function<T, String> {
    @Override
    public String apply(T target) {
        return format(target);
    }

    @SuppressWarnings("unchecked")
    public static <T> String format(T target) {
        if (target == null) {
            return "null";
        }
        Class<T> targetClass = (Class<T>) target.getClass();
        if (targetClass.isPrimitive()) {
            return target.toString();
        }
        if (!BeanWrapperConfig.hasDeep(targetClass)) {
            if (targetClass == Byte.class) {
                return target.toString();
            }
            if (targetClass == Short.class) {
                return target.toString();
            }
            if (targetClass == Integer.class) {
                return target.toString();
            }
            if (targetClass == Long.class) {
                return target.toString();
            }
            if (targetClass == BigInteger.class) {
                return target.toString();
            }
            if (targetClass == Character.class) {
                return target.toString();
            }
            if (targetClass == Float.class) {
                return target.toString();
            }
            if (targetClass == Double.class) {
                return target.toString();
            }
            if (targetClass == BigDecimal.class) {
                return target.toString();
            }
            if (targetClass == Boolean.class) {
                return target.toString();
            }
            return '\"' + target.toString() + '"';
        }
        if (targetClass == Object.class) {
            return "{}";
        }
        if (target instanceof Iterable) {
            StringBuilder builder = new StringBuilder();
            builder.append('[');
            ((Iterable<?>) target).forEach(item -> {
                if (builder.length() > 1) {
                    builder.append(',');
                }
                builder.append(format(item));
            });
            builder.append(']');
            return builder.toString();
        } else if (targetClass.isArray()) {
            Class<?> componentType = targetClass.getComponentType();
            if (componentType.isPrimitive()) {
                if (componentType == Integer.TYPE) {
                    return formatIntegerArray((int[]) target);
                } else if (componentType == Boolean.TYPE) {
                    return formatBooleanArray((boolean[]) target);
                } else if (componentType == Byte.TYPE) {
                    return formatByteArray((byte[]) target);
                } else if (componentType == Character.TYPE) {
                    return formatCharacterArray((char[]) target);
                } else if (componentType == Short.TYPE) {
                    return formatShortArray((short[]) target);
                } else if (componentType == Double.TYPE) {
                    return formatDoubleArray((double[]) target);
                } else if (componentType == Float.TYPE) {
                    return formatFloatArray((float[]) target);
                } else if (componentType == Long.TYPE) {
                    return formatLongArray((long[]) target);
                } else {
                    throw new AssertionError();
                }
            } else {
                return formatObjectArray((Object[]) target);
            }
        }
        if (target instanceof Map<?, ?>) {
            StringBuilder builder = new StringBuilder();
            builder.append('{');
            ((Map<?, ?>) target).forEach((k, v) -> {
                if (builder.length() > 1) {
                    builder.append(',');
                }
                builder.append('"').append(k).append('"').append(':');
                builder.append(format(v));
            });
            builder.append('}');
            return builder.toString();
        }

        BeanWrapper<T> beanWrapper = BeanWrappers.getWrapper(targetClass);
        StringBuilder builder = new StringBuilder();
        builder.append('{');
        writeProperties(target, beanWrapper.getPropertyAccesses(), builder);
        /* for (PropertyAccess<T> propertyAccess : beanWrapper.getPropertyAccesses()) {
            if (builder.length() > 1) {
                builder.append(',');
            }
            builder.append('"').append(propertyAccess.getPropertyName()).append('"').append(':');
            Function<T, ?> lambdaGetter = propertyAccess.getLambdaGetter();
            if (lambdaGetter == null) {
                builder.append("null");
            } else {
                builder.append(format(lambdaGetter.apply(target)));
            }
        } */
        builder.append('}');
        return builder.toString();
    }

    public static <T> void writeProperties(final T target, final PropertyAccess<T>[] propertyAccesses, final StringBuilder builder) {
        final int length = propertyAccesses.length;
        PropertyAccess<T> propertyAccess;
        Function<T, ?> lambdaGetter;
        int index = 0;
        while (index < length) {
            int sub = length - index;
            if (sub > 20) sub = 20;
            switch (sub) {
                case 20: {
                    propertyAccess = propertyAccesses[index++];
                    if (builder.length() > 1) {
                        builder.append(',');
                    }
                    builder.append('"').append(propertyAccess.getPropertyName()).append('"').append(':');
                    lambdaGetter = propertyAccess.getLambdaGetter();
                    if (lambdaGetter == null) {
                        builder.append("null");
                    } else {
                        builder.append(format(lambdaGetter.apply(target)));
                    }
                }
                case 19: {
                    propertyAccess = propertyAccesses[index++];
                    if (builder.length() > 1) {
                        builder.append(',');
                    }
                    builder.append('"').append(propertyAccess.getPropertyName()).append('"').append(':');
                    lambdaGetter = propertyAccess.getLambdaGetter();
                    if (lambdaGetter == null) {
                        builder.append("null");
                    } else {
                        builder.append(format(lambdaGetter.apply(target)));
                    }
                }
                case 18: {
                    propertyAccess = propertyAccesses[index++];
                    if (builder.length() > 1) {
                        builder.append(',');
                    }
                    builder.append('"').append(propertyAccess.getPropertyName()).append('"').append(':');
                    lambdaGetter = propertyAccess.getLambdaGetter();
                    if (lambdaGetter == null) {
                        builder.append("null");
                    } else {
                        builder.append(format(lambdaGetter.apply(target)));
                    }
                }
                case 17: {
                    propertyAccess = propertyAccesses[index++];
                    if (builder.length() > 1) {
                        builder.append(',');
                    }
                    builder.append('"').append(propertyAccess.getPropertyName()).append('"').append(':');
                    lambdaGetter = propertyAccess.getLambdaGetter();
                    if (lambdaGetter == null) {
                        builder.append("null");
                    } else {
                        builder.append(format(lambdaGetter.apply(target)));
                    }
                }
                case 16: {
                    propertyAccess = propertyAccesses[index++];
                    if (builder.length() > 1) {
                        builder.append(',');
                    }
                    builder.append('"').append(propertyAccess.getPropertyName()).append('"').append(':');
                    lambdaGetter = propertyAccess.getLambdaGetter();
                    if (lambdaGetter == null) {
                        builder.append("null");
                    } else {
                        builder.append(format(lambdaGetter.apply(target)));
                    }
                }
                case 15: {
                    propertyAccess = propertyAccesses[index++];
                    if (builder.length() > 1) {
                        builder.append(',');
                    }
                    builder.append('"').append(propertyAccess.getPropertyName()).append('"').append(':');
                    lambdaGetter = propertyAccess.getLambdaGetter();
                    if (lambdaGetter == null) {
                        builder.append("null");
                    } else {
                        builder.append(format(lambdaGetter.apply(target)));
                    }
                }
                case 14: {
                    propertyAccess = propertyAccesses[index++];
                    if (builder.length() > 1) {
                        builder.append(',');
                    }
                    builder.append('"').append(propertyAccess.getPropertyName()).append('"').append(':');
                    lambdaGetter = propertyAccess.getLambdaGetter();
                    if (lambdaGetter == null) {
                        builder.append("null");
                    } else {
                        builder.append(format(lambdaGetter.apply(target)));
                    }
                }
                case 13: {
                    propertyAccess = propertyAccesses[index++];
                    if (builder.length() > 1) {
                        builder.append(',');
                    }
                    builder.append('"').append(propertyAccess.getPropertyName()).append('"').append(':');
                    lambdaGetter = propertyAccess.getLambdaGetter();
                    if (lambdaGetter == null) {
                        builder.append("null");
                    } else {
                        builder.append(format(lambdaGetter.apply(target)));
                    }
                }
                case 12: {
                    propertyAccess = propertyAccesses[index++];
                    if (builder.length() > 1) {
                        builder.append(',');
                    }
                    builder.append('"').append(propertyAccess.getPropertyName()).append('"').append(':');
                    lambdaGetter = propertyAccess.getLambdaGetter();
                    if (lambdaGetter == null) {
                        builder.append("null");
                    } else {
                        builder.append(format(lambdaGetter.apply(target)));
                    }
                }
                case 11: {
                    propertyAccess = propertyAccesses[index++];
                    if (builder.length() > 1) {
                        builder.append(',');
                    }
                    builder.append('"').append(propertyAccess.getPropertyName()).append('"').append(':');
                    lambdaGetter = propertyAccess.getLambdaGetter();
                    if (lambdaGetter == null) {
                        builder.append("null");
                    } else {
                        builder.append(format(lambdaGetter.apply(target)));
                    }
                }
                case 10: {
                    propertyAccess = propertyAccesses[index++];
                    if (builder.length() > 1) {
                        builder.append(',');
                    }
                    builder.append('"').append(propertyAccess.getPropertyName()).append('"').append(':');
                    lambdaGetter = propertyAccess.getLambdaGetter();
                    if (lambdaGetter == null) {
                        builder.append("null");
                    } else {
                        builder.append(format(lambdaGetter.apply(target)));
                    }
                }
                case 9: {
                    propertyAccess = propertyAccesses[index++];
                    if (builder.length() > 1) {
                        builder.append(',');
                    }
                    builder.append('"').append(propertyAccess.getPropertyName()).append('"').append(':');
                    lambdaGetter = propertyAccess.getLambdaGetter();
                    if (lambdaGetter == null) {
                        builder.append("null");
                    } else {
                        builder.append(format(lambdaGetter.apply(target)));
                    }
                }
                case 8: {
                    propertyAccess = propertyAccesses[index++];
                    if (builder.length() > 1) {
                        builder.append(',');
                    }
                    builder.append('"').append(propertyAccess.getPropertyName()).append('"').append(':');
                    lambdaGetter = propertyAccess.getLambdaGetter();
                    if (lambdaGetter == null) {
                        builder.append("null");
                    } else {
                        builder.append(format(lambdaGetter.apply(target)));
                    }
                }
                case 7: {
                    propertyAccess = propertyAccesses[index++];
                    if (builder.length() > 1) {
                        builder.append(',');
                    }
                    builder.append('"').append(propertyAccess.getPropertyName()).append('"').append(':');
                    lambdaGetter = propertyAccess.getLambdaGetter();
                    if (lambdaGetter == null) {
                        builder.append("null");
                    } else {
                        builder.append(format(lambdaGetter.apply(target)));
                    }
                }
                case 6: {
                    propertyAccess = propertyAccesses[index++];
                    if (builder.length() > 1) {
                        builder.append(',');
                    }
                    builder.append('"').append(propertyAccess.getPropertyName()).append('"').append(':');
                    lambdaGetter = propertyAccess.getLambdaGetter();
                    if (lambdaGetter == null) {
                        builder.append("null");
                    } else {
                        builder.append(format(lambdaGetter.apply(target)));
                    }
                }
                case 5: {
                    propertyAccess = propertyAccesses[index++];
                    if (builder.length() > 1) {
                        builder.append(',');
                    }
                    builder.append('"').append(propertyAccess.getPropertyName()).append('"').append(':');
                    lambdaGetter = propertyAccess.getLambdaGetter();
                    if (lambdaGetter == null) {
                        builder.append("null");
                    } else {
                        builder.append(format(lambdaGetter.apply(target)));
                    }
                }
                case 4: {
                    propertyAccess = propertyAccesses[index++];
                    if (builder.length() > 1) {
                        builder.append(',');
                    }
                    builder.append('"').append(propertyAccess.getPropertyName()).append('"').append(':');
                    lambdaGetter = propertyAccess.getLambdaGetter();
                    if (lambdaGetter == null) {
                        builder.append("null");
                    } else {
                        builder.append(format(lambdaGetter.apply(target)));
                    }
                }
                case 3: {
                    propertyAccess = propertyAccesses[index++];
                    if (builder.length() > 1) {
                        builder.append(',');
                    }
                    builder.append('"').append(propertyAccess.getPropertyName()).append('"').append(':');
                    lambdaGetter = propertyAccess.getLambdaGetter();
                    if (lambdaGetter == null) {
                        builder.append("null");
                    } else {
                        builder.append(format(lambdaGetter.apply(target)));
                    }
                }
                case 2: {
                    propertyAccess = propertyAccesses[index++];
                    if (builder.length() > 1) {
                        builder.append(',');
                    }
                    builder.append('"').append(propertyAccess.getPropertyName()).append('"').append(':');
                    lambdaGetter = propertyAccess.getLambdaGetter();
                    if (lambdaGetter == null) {
                        builder.append("null");
                    } else {
                        builder.append(format(lambdaGetter.apply(target)));
                    }
                }
                case 1: {
                    propertyAccess = propertyAccesses[index++];
                    if (builder.length() > 1) {
                        builder.append(',');
                    }
                    builder.append('"').append(propertyAccess.getPropertyName()).append('"').append(':');
                    lambdaGetter = propertyAccess.getLambdaGetter();
                    if (lambdaGetter == null) {
                        builder.append("null");
                    } else {
                        builder.append(format(lambdaGetter.apply(target)));
                    }
                    break;
                }
                default: {
                }
            }
        }
    }

    public static String formatIntegerArray(int[] target) {
        StringBuilder builder = new StringBuilder();
        builder.append('[');
        for (int item : target) {
            if (builder.length() > 1) {
                builder.append(',');
            }
            builder.append(format(item));
        }
        builder.append(']');
        return builder.toString();
    }

    public static String formatBooleanArray(boolean[] target) {
        StringBuilder builder = new StringBuilder();
        builder.append('[');
        for (boolean item : target) {
            if (builder.length() > 1) {
                builder.append(',');
            }
            builder.append(format(item));
        }
        builder.append(']');
        return builder.toString();
    }

    public static String formatByteArray(byte[] target) {
        StringBuilder builder = new StringBuilder();
        builder.append('[');
        for (byte item : target) {
            if (builder.length() > 1) {
                builder.append(',');
            }
            builder.append(format(item));
        }
        builder.append(']');
        return builder.toString();
    }

    public static String formatCharacterArray(char[] target) {
        StringBuilder builder = new StringBuilder();
        builder.append('[');
        for (char item : target) {
            if (builder.length() > 1) {
                builder.append(',');
            }
            builder.append(format(item));
        }
        builder.append(']');
        return builder.toString();
    }

    public static String formatShortArray(short[] target) {
        StringBuilder builder = new StringBuilder();
        builder.append('[');
        for (short item : target) {
            if (builder.length() > 1) {
                builder.append(',');
            }
            builder.append(format(item));
        }
        builder.append(']');
        return builder.toString();
    }

    public static String formatDoubleArray(double[] target) {
        StringBuilder builder = new StringBuilder();
        builder.append('[');
        for (double item : target) {
            if (builder.length() > 1) {
                builder.append(',');
            }
            builder.append(format(item));
        }
        builder.append(']');
        return builder.toString();
    }

    public static String formatFloatArray(float[] target) {
        StringBuilder builder = new StringBuilder();
        builder.append('[');
        for (float item : target) {
            if (builder.length() > 1) {
                builder.append(',');
            }
            builder.append(format(item));
        }
        builder.append(']');
        return builder.toString();
    }

    public static String formatLongArray(long[] target) {
        StringBuilder builder = new StringBuilder();
        builder.append('[');
        for (long item : target) {
            if (builder.length() > 1) {
                builder.append(',');
            }
            builder.append(format(item));
        }
        builder.append(']');
        return builder.toString();
    }

    public static String formatObjectArray(Object[] target) {
        StringBuilder builder = new StringBuilder();
        builder.append('[');
        for (Object item : target) {
            if (builder.length() > 1) {
                builder.append(',');
            }
            builder.append(format(item));
        }
        builder.append(']');
        return builder.toString();
    }

}
