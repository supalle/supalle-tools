package com.supalle.tools.code.beanwrapper;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.*;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class BeanWrapperConfig {

    private static final Set<Class<?>> DEFAULT_NOT_DEEP_TYPE_SET = new HashSet<>();

    public static void register(Class<?> clazz) {
        registerNotDeepType(clazz);
    }

    public static void registerNotDeepType(Class<?> clazz) {
        synchronized (DEFAULT_NOT_DEEP_TYPE_SET) {
            DEFAULT_NOT_DEEP_TYPE_SET.add(clazz);
        }
    }

    public static boolean hasDeep(Class<?> clazz) {
        return hasDeep(clazz, DEFAULT_NOT_DEEP_TYPE_SET);
    }

    public static boolean hasDeep(Class<?> clazz, Set<Class<?>> notDeepTypeSet) {
        return !notDeepTypeSet.contains(clazz);
    }

    static {
        register(null);
        register(String.class);

        register(byte.class);
        register(short.class);
        register(int.class);
        register(long.class);
        register(char.class);
        register(float.class);
        register(double.class);
        register(boolean.class);

        register(Byte.class);
        register(Short.class);
        register(Integer.class);
        register(Long.class);
        register(BigInteger.class);
        register(Character.class);
        register(Float.class);
        register(Double.class);
        register(BigDecimal.class);
        register(Boolean.class);

        register(Date.class);
        register(Calendar.class);
        register(LocalDate.class);
        register(LocalTime.class);
        register(LocalDateTime.class);
        register(Instant.class);
        register(ZonedDateTime.class);
        register(OffsetDateTime.class);
        register(Duration.class);
        register(java.sql.Time.class);
        register(java.sql.Date.class);
        register(java.sql.Timestamp.class);

    }

}