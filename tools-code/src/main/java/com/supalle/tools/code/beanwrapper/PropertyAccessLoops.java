package com.supalle.tools.code.beanwrapper;

import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.Function;

public class PropertyAccessLoops {

    public static <T> void copyLoop(final T source, final T target, final PropertyAccess<T>[] propertyAccesses) {
        final int length = propertyAccesses.length;
        PropertyAccess<T> propertyAccess;
        BiConsumer<T, Object> lambdaSetter;
        Function<T, ?> lambdaGetter;
        int index = 0;
        while (index < length) {
            int sub = length - index;
            sub = sub > 20 ? 20 : sub;
            switch (sub) {
                case 20: {
                    propertyAccess = propertyAccesses[index++];
                    lambdaSetter = propertyAccess.getLambdaSetter();
                    lambdaGetter = propertyAccess.getLambdaGetter();
                    if (lambdaSetter != null && lambdaGetter != null) {
                        lambdaSetter.accept(target, lambdaGetter.apply(source));
                    }
                }
                case 19: {
                    propertyAccess = propertyAccesses[index++];
                    lambdaSetter = propertyAccess.getLambdaSetter();
                    lambdaGetter = propertyAccess.getLambdaGetter();
                    if (lambdaSetter != null && lambdaGetter != null) {
                        lambdaSetter.accept(target, lambdaGetter.apply(source));
                    }
                }
                case 18: {
                    propertyAccess = propertyAccesses[index++];
                    lambdaSetter = propertyAccess.getLambdaSetter();
                    lambdaGetter = propertyAccess.getLambdaGetter();
                    if (lambdaSetter != null && lambdaGetter != null) {
                        lambdaSetter.accept(target, lambdaGetter.apply(source));
                    }
                }
                case 17: {
                    propertyAccess = propertyAccesses[index++];
                    lambdaSetter = propertyAccess.getLambdaSetter();
                    lambdaGetter = propertyAccess.getLambdaGetter();
                    if (lambdaSetter != null && lambdaGetter != null) {
                        lambdaSetter.accept(target, lambdaGetter.apply(source));
                    }
                }
                case 16: {
                    propertyAccess = propertyAccesses[index++];
                    lambdaSetter = propertyAccess.getLambdaSetter();
                    lambdaGetter = propertyAccess.getLambdaGetter();
                    if (lambdaSetter != null && lambdaGetter != null) {
                        lambdaSetter.accept(target, lambdaGetter.apply(source));
                    }
                }
                case 15: {
                    propertyAccess = propertyAccesses[index++];
                    lambdaSetter = propertyAccess.getLambdaSetter();
                    lambdaGetter = propertyAccess.getLambdaGetter();
                    if (lambdaSetter != null && lambdaGetter != null) {
                        lambdaSetter.accept(target, lambdaGetter.apply(source));
                    }
                }
                case 14: {
                    propertyAccess = propertyAccesses[index++];
                    lambdaSetter = propertyAccess.getLambdaSetter();
                    lambdaGetter = propertyAccess.getLambdaGetter();
                    if (lambdaSetter != null && lambdaGetter != null) {
                        lambdaSetter.accept(target, lambdaGetter.apply(source));
                    }
                }
                case 13: {
                    propertyAccess = propertyAccesses[index++];
                    lambdaSetter = propertyAccess.getLambdaSetter();
                    lambdaGetter = propertyAccess.getLambdaGetter();
                    if (lambdaSetter != null && lambdaGetter != null) {
                        lambdaSetter.accept(target, lambdaGetter.apply(source));
                    }
                }
                case 12: {
                    propertyAccess = propertyAccesses[index++];
                    lambdaSetter = propertyAccess.getLambdaSetter();
                    lambdaGetter = propertyAccess.getLambdaGetter();
                    if (lambdaSetter != null && lambdaGetter != null) {
                        lambdaSetter.accept(target, lambdaGetter.apply(source));
                    }
                }
                case 11: {
                    propertyAccess = propertyAccesses[index++];
                    lambdaSetter = propertyAccess.getLambdaSetter();
                    lambdaGetter = propertyAccess.getLambdaGetter();
                    if (lambdaSetter != null && lambdaGetter != null) {
                        lambdaSetter.accept(target, lambdaGetter.apply(source));
                    }
                }
                case 10: {
                    propertyAccess = propertyAccesses[index++];
                    lambdaSetter = propertyAccess.getLambdaSetter();
                    lambdaGetter = propertyAccess.getLambdaGetter();
                    if (lambdaSetter != null && lambdaGetter != null) {
                        lambdaSetter.accept(target, lambdaGetter.apply(source));
                    }
                }
                case 9: {
                    propertyAccess = propertyAccesses[index++];
                    lambdaSetter = propertyAccess.getLambdaSetter();
                    lambdaGetter = propertyAccess.getLambdaGetter();
                    if (lambdaSetter != null && lambdaGetter != null) {
                        lambdaSetter.accept(target, lambdaGetter.apply(source));
                    }
                }
                case 8: {
                    propertyAccess = propertyAccesses[index++];
                    lambdaSetter = propertyAccess.getLambdaSetter();
                    lambdaGetter = propertyAccess.getLambdaGetter();
                    if (lambdaSetter != null && lambdaGetter != null) {
                        lambdaSetter.accept(target, lambdaGetter.apply(source));
                    }
                }
                case 7: {
                    propertyAccess = propertyAccesses[index++];
                    lambdaSetter = propertyAccess.getLambdaSetter();
                    lambdaGetter = propertyAccess.getLambdaGetter();
                    if (lambdaSetter != null && lambdaGetter != null) {
                        lambdaSetter.accept(target, lambdaGetter.apply(source));
                    }
                }
                case 6: {
                    propertyAccess = propertyAccesses[index++];
                    lambdaSetter = propertyAccess.getLambdaSetter();
                    lambdaGetter = propertyAccess.getLambdaGetter();
                    if (lambdaSetter != null && lambdaGetter != null) {
                        lambdaSetter.accept(target, lambdaGetter.apply(source));
                    }
                }
                case 5: {
                    propertyAccess = propertyAccesses[index++];
                    lambdaSetter = propertyAccess.getLambdaSetter();
                    lambdaGetter = propertyAccess.getLambdaGetter();
                    if (lambdaSetter != null && lambdaGetter != null) {
                        lambdaSetter.accept(target, lambdaGetter.apply(source));
                    }
                }
                case 4: {
                    propertyAccess = propertyAccesses[index++];
                    lambdaSetter = propertyAccess.getLambdaSetter();
                    lambdaGetter = propertyAccess.getLambdaGetter();
                    if (lambdaSetter != null && lambdaGetter != null) {
                        lambdaSetter.accept(target, lambdaGetter.apply(source));
                    }
                }
                case 3: {
                    propertyAccess = propertyAccesses[index++];
                    lambdaSetter = propertyAccess.getLambdaSetter();
                    lambdaGetter = propertyAccess.getLambdaGetter();
                    if (lambdaSetter != null && lambdaGetter != null) {
                        lambdaSetter.accept(target, lambdaGetter.apply(source));
                    }
                }
                case 2: {
                    propertyAccess = propertyAccesses[index++];
                    lambdaSetter = propertyAccess.getLambdaSetter();
                    lambdaGetter = propertyAccess.getLambdaGetter();
                    if (lambdaSetter != null && lambdaGetter != null) {
                        lambdaSetter.accept(target, lambdaGetter.apply(source));
                    }
                }
                case 1: {
                    propertyAccess = propertyAccesses[index++];
                    lambdaSetter = propertyAccess.getLambdaSetter();
                    lambdaGetter = propertyAccess.getLambdaGetter();
                    if (lambdaSetter != null && lambdaGetter != null) {
                        lambdaSetter.accept(target, lambdaGetter.apply(source));
                    }
                    break;
                }
                default: {
                }
            }
        }
    }

    public static <T> void copyLoop(final T source, final T target, final PropertyAccess<T>[] propertyAccesses, final Set<String> ignoredPropertySet) {
        final int length = propertyAccesses.length;
        PropertyAccess<T> propertyAccess;
        BiConsumer<T, Object> lambdaSetter;
        Function<T, ?> lambdaGetter;
        int index = 0;
        while (index < length) {
            int sub = length - index;
            sub = sub > 20 ? 20 : sub;
            switch (sub) {
                case 20: {
                    propertyAccess = propertyAccesses[index++];
                    lambdaSetter = propertyAccess.getLambdaSetter();
                    lambdaGetter = propertyAccess.getLambdaGetter();
                    if (lambdaSetter != null && lambdaGetter != null && !ignoredPropertySet.contains(propertyAccess.getPropertyName())) {
                        lambdaSetter.accept(target, lambdaGetter.apply(source));
                    }
                }
                case 19: {
                    propertyAccess = propertyAccesses[index++];
                    lambdaSetter = propertyAccess.getLambdaSetter();
                    lambdaGetter = propertyAccess.getLambdaGetter();
                    if (lambdaSetter != null && lambdaGetter != null && !ignoredPropertySet.contains(propertyAccess.getPropertyName())) {
                        lambdaSetter.accept(target, lambdaGetter.apply(source));
                    }
                }
                case 18: {
                    propertyAccess = propertyAccesses[index++];
                    lambdaSetter = propertyAccess.getLambdaSetter();
                    lambdaGetter = propertyAccess.getLambdaGetter();
                    if (lambdaSetter != null && lambdaGetter != null && !ignoredPropertySet.contains(propertyAccess.getPropertyName())) {
                        lambdaSetter.accept(target, lambdaGetter.apply(source));
                    }
                }
                case 17: {
                    propertyAccess = propertyAccesses[index++];
                    lambdaSetter = propertyAccess.getLambdaSetter();
                    lambdaGetter = propertyAccess.getLambdaGetter();
                    if (lambdaSetter != null && lambdaGetter != null && !ignoredPropertySet.contains(propertyAccess.getPropertyName())) {
                        lambdaSetter.accept(target, lambdaGetter.apply(source));
                    }
                }
                case 16: {
                    propertyAccess = propertyAccesses[index++];
                    lambdaSetter = propertyAccess.getLambdaSetter();
                    lambdaGetter = propertyAccess.getLambdaGetter();
                    if (lambdaSetter != null && lambdaGetter != null && !ignoredPropertySet.contains(propertyAccess.getPropertyName())) {
                        lambdaSetter.accept(target, lambdaGetter.apply(source));
                    }
                }
                case 15: {
                    propertyAccess = propertyAccesses[index++];
                    lambdaSetter = propertyAccess.getLambdaSetter();
                    lambdaGetter = propertyAccess.getLambdaGetter();
                    if (lambdaSetter != null && lambdaGetter != null && !ignoredPropertySet.contains(propertyAccess.getPropertyName())) {
                        lambdaSetter.accept(target, lambdaGetter.apply(source));
                    }
                }
                case 14: {
                    propertyAccess = propertyAccesses[index++];
                    lambdaSetter = propertyAccess.getLambdaSetter();
                    lambdaGetter = propertyAccess.getLambdaGetter();
                    if (lambdaSetter != null && lambdaGetter != null && !ignoredPropertySet.contains(propertyAccess.getPropertyName())) {
                        lambdaSetter.accept(target, lambdaGetter.apply(source));
                    }
                }
                case 13: {
                    propertyAccess = propertyAccesses[index++];
                    lambdaSetter = propertyAccess.getLambdaSetter();
                    lambdaGetter = propertyAccess.getLambdaGetter();
                    if (lambdaSetter != null && lambdaGetter != null && !ignoredPropertySet.contains(propertyAccess.getPropertyName())) {
                        lambdaSetter.accept(target, lambdaGetter.apply(source));
                    }
                }
                case 12: {
                    propertyAccess = propertyAccesses[index++];
                    lambdaSetter = propertyAccess.getLambdaSetter();
                    lambdaGetter = propertyAccess.getLambdaGetter();
                    if (lambdaSetter != null && lambdaGetter != null && !ignoredPropertySet.contains(propertyAccess.getPropertyName())) {
                        lambdaSetter.accept(target, lambdaGetter.apply(source));
                    }
                }
                case 11: {
                    propertyAccess = propertyAccesses[index++];
                    lambdaSetter = propertyAccess.getLambdaSetter();
                    lambdaGetter = propertyAccess.getLambdaGetter();
                    if (lambdaSetter != null && lambdaGetter != null && !ignoredPropertySet.contains(propertyAccess.getPropertyName())) {
                        lambdaSetter.accept(target, lambdaGetter.apply(source));
                    }
                }
                case 10: {
                    propertyAccess = propertyAccesses[index++];
                    lambdaSetter = propertyAccess.getLambdaSetter();
                    lambdaGetter = propertyAccess.getLambdaGetter();
                    if (lambdaSetter != null && lambdaGetter != null && !ignoredPropertySet.contains(propertyAccess.getPropertyName())) {
                        lambdaSetter.accept(target, lambdaGetter.apply(source));
                    }
                }
                case 9: {
                    propertyAccess = propertyAccesses[index++];
                    lambdaSetter = propertyAccess.getLambdaSetter();
                    lambdaGetter = propertyAccess.getLambdaGetter();
                    if (lambdaSetter != null && lambdaGetter != null && !ignoredPropertySet.contains(propertyAccess.getPropertyName())) {
                        lambdaSetter.accept(target, lambdaGetter.apply(source));
                    }
                }
                case 8: {
                    propertyAccess = propertyAccesses[index++];
                    lambdaSetter = propertyAccess.getLambdaSetter();
                    lambdaGetter = propertyAccess.getLambdaGetter();
                    if (lambdaSetter != null && lambdaGetter != null && !ignoredPropertySet.contains(propertyAccess.getPropertyName())) {
                        lambdaSetter.accept(target, lambdaGetter.apply(source));
                    }
                }
                case 7: {
                    propertyAccess = propertyAccesses[index++];
                    lambdaSetter = propertyAccess.getLambdaSetter();
                    lambdaGetter = propertyAccess.getLambdaGetter();
                    if (lambdaSetter != null && lambdaGetter != null && !ignoredPropertySet.contains(propertyAccess.getPropertyName())) {
                        lambdaSetter.accept(target, lambdaGetter.apply(source));
                    }
                }
                case 6: {
                    propertyAccess = propertyAccesses[index++];
                    lambdaSetter = propertyAccess.getLambdaSetter();
                    lambdaGetter = propertyAccess.getLambdaGetter();
                    if (lambdaSetter != null && lambdaGetter != null && !ignoredPropertySet.contains(propertyAccess.getPropertyName())) {
                        lambdaSetter.accept(target, lambdaGetter.apply(source));
                    }
                }
                case 5: {
                    propertyAccess = propertyAccesses[index++];
                    lambdaSetter = propertyAccess.getLambdaSetter();
                    lambdaGetter = propertyAccess.getLambdaGetter();
                    if (lambdaSetter != null && lambdaGetter != null && !ignoredPropertySet.contains(propertyAccess.getPropertyName())) {
                        lambdaSetter.accept(target, lambdaGetter.apply(source));
                    }
                }
                case 4: {
                    propertyAccess = propertyAccesses[index++];
                    lambdaSetter = propertyAccess.getLambdaSetter();
                    lambdaGetter = propertyAccess.getLambdaGetter();
                    if (lambdaSetter != null && lambdaGetter != null && !ignoredPropertySet.contains(propertyAccess.getPropertyName())) {
                        lambdaSetter.accept(target, lambdaGetter.apply(source));
                    }
                }
                case 3: {
                    propertyAccess = propertyAccesses[index++];
                    lambdaSetter = propertyAccess.getLambdaSetter();
                    lambdaGetter = propertyAccess.getLambdaGetter();
                    if (lambdaSetter != null && lambdaGetter != null && !ignoredPropertySet.contains(propertyAccess.getPropertyName())) {
                        lambdaSetter.accept(target, lambdaGetter.apply(source));
                    }
                }
                case 2: {
                    propertyAccess = propertyAccesses[index++];
                    lambdaSetter = propertyAccess.getLambdaSetter();
                    lambdaGetter = propertyAccess.getLambdaGetter();
                    if (lambdaSetter != null && lambdaGetter != null && !ignoredPropertySet.contains(propertyAccess.getPropertyName())) {
                        lambdaSetter.accept(target, lambdaGetter.apply(source));
                    }
                }
                case 1: {
                    propertyAccess = propertyAccesses[index++];
                    lambdaSetter = propertyAccess.getLambdaSetter();
                    lambdaGetter = propertyAccess.getLambdaGetter();
                    if (lambdaSetter != null && lambdaGetter != null && !ignoredPropertySet.contains(propertyAccess.getPropertyName())) {
                        lambdaSetter.accept(target, lambdaGetter.apply(source));
                    }
                    break;
                }
                default: {
                }
            }
        }
    }

    public static <S, T> void copyLoop(final S source, final T target, final PropertyAccess<T>[] targetPropertyAccesses, Map<String, PropertyAccess<S>> sourcePropertyAccessMapping) {
        final int length = targetPropertyAccesses.length;
        PropertyAccess<S> sourcePropertyAccess;
        PropertyAccess<T> targetPropertyAccess;
        BiConsumer<T, Object> lambdaSetter;
        Function<S, ?> lambdaGetter;
        int index = 0;
        while (index < length) {
            int sub = length - index;
            sub = sub > 20 ? 20 : sub;
            switch (sub) {
                case 20: {
                    targetPropertyAccess = targetPropertyAccesses[index++];
                    lambdaSetter = targetPropertyAccess.getLambdaSetter();
                    sourcePropertyAccess = sourcePropertyAccessMapping.get(targetPropertyAccess.getPropertyName());
                    if (lambdaSetter != null && sourcePropertyAccess != null && (lambdaGetter = sourcePropertyAccess.getLambdaGetter()) != null) {
                        lambdaSetter.accept(target, lambdaGetter.apply(source));
                    }
                }
                case 19: {
                    targetPropertyAccess = targetPropertyAccesses[index++];
                    lambdaSetter = targetPropertyAccess.getLambdaSetter();
                    sourcePropertyAccess = sourcePropertyAccessMapping.get(targetPropertyAccess.getPropertyName());
                    if (lambdaSetter != null && sourcePropertyAccess != null && (lambdaGetter = sourcePropertyAccess.getLambdaGetter()) != null) {
                        lambdaSetter.accept(target, lambdaGetter.apply(source));
                    }
                }
                case 18: {
                    targetPropertyAccess = targetPropertyAccesses[index++];
                    lambdaSetter = targetPropertyAccess.getLambdaSetter();
                    sourcePropertyAccess = sourcePropertyAccessMapping.get(targetPropertyAccess.getPropertyName());
                    if (lambdaSetter != null && sourcePropertyAccess != null && (lambdaGetter = sourcePropertyAccess.getLambdaGetter()) != null) {
                        lambdaSetter.accept(target, lambdaGetter.apply(source));
                    }
                }
                case 17: {
                    targetPropertyAccess = targetPropertyAccesses[index++];
                    lambdaSetter = targetPropertyAccess.getLambdaSetter();
                    sourcePropertyAccess = sourcePropertyAccessMapping.get(targetPropertyAccess.getPropertyName());
                    if (lambdaSetter != null && sourcePropertyAccess != null && (lambdaGetter = sourcePropertyAccess.getLambdaGetter()) != null) {
                        lambdaSetter.accept(target, lambdaGetter.apply(source));
                    }
                }
                case 16: {
                    targetPropertyAccess = targetPropertyAccesses[index++];
                    lambdaSetter = targetPropertyAccess.getLambdaSetter();
                    sourcePropertyAccess = sourcePropertyAccessMapping.get(targetPropertyAccess.getPropertyName());
                    if (lambdaSetter != null && sourcePropertyAccess != null && (lambdaGetter = sourcePropertyAccess.getLambdaGetter()) != null) {
                        lambdaSetter.accept(target, lambdaGetter.apply(source));
                    }
                }
                case 15: {
                    targetPropertyAccess = targetPropertyAccesses[index++];
                    lambdaSetter = targetPropertyAccess.getLambdaSetter();
                    sourcePropertyAccess = sourcePropertyAccessMapping.get(targetPropertyAccess.getPropertyName());
                    if (lambdaSetter != null && sourcePropertyAccess != null && (lambdaGetter = sourcePropertyAccess.getLambdaGetter()) != null) {
                        lambdaSetter.accept(target, lambdaGetter.apply(source));
                    }
                }
                case 14: {
                    targetPropertyAccess = targetPropertyAccesses[index++];
                    lambdaSetter = targetPropertyAccess.getLambdaSetter();
                    sourcePropertyAccess = sourcePropertyAccessMapping.get(targetPropertyAccess.getPropertyName());
                    if (lambdaSetter != null && sourcePropertyAccess != null && (lambdaGetter = sourcePropertyAccess.getLambdaGetter()) != null) {
                        lambdaSetter.accept(target, lambdaGetter.apply(source));
                    }
                }
                case 13: {
                    targetPropertyAccess = targetPropertyAccesses[index++];
                    lambdaSetter = targetPropertyAccess.getLambdaSetter();
                    sourcePropertyAccess = sourcePropertyAccessMapping.get(targetPropertyAccess.getPropertyName());
                    if (lambdaSetter != null && sourcePropertyAccess != null && (lambdaGetter = sourcePropertyAccess.getLambdaGetter()) != null) {
                        lambdaSetter.accept(target, lambdaGetter.apply(source));
                    }
                }
                case 12: {
                    targetPropertyAccess = targetPropertyAccesses[index++];
                    lambdaSetter = targetPropertyAccess.getLambdaSetter();
                    sourcePropertyAccess = sourcePropertyAccessMapping.get(targetPropertyAccess.getPropertyName());
                    if (lambdaSetter != null && sourcePropertyAccess != null && (lambdaGetter = sourcePropertyAccess.getLambdaGetter()) != null) {
                        lambdaSetter.accept(target, lambdaGetter.apply(source));
                    }
                }
                case 11: {
                    targetPropertyAccess = targetPropertyAccesses[index++];
                    lambdaSetter = targetPropertyAccess.getLambdaSetter();
                    sourcePropertyAccess = sourcePropertyAccessMapping.get(targetPropertyAccess.getPropertyName());
                    if (lambdaSetter != null && sourcePropertyAccess != null && (lambdaGetter = sourcePropertyAccess.getLambdaGetter()) != null) {
                        lambdaSetter.accept(target, lambdaGetter.apply(source));
                    }
                }
                case 10: {
                    targetPropertyAccess = targetPropertyAccesses[index++];
                    lambdaSetter = targetPropertyAccess.getLambdaSetter();
                    sourcePropertyAccess = sourcePropertyAccessMapping.get(targetPropertyAccess.getPropertyName());
                    if (lambdaSetter != null && sourcePropertyAccess != null && (lambdaGetter = sourcePropertyAccess.getLambdaGetter()) != null) {
                        lambdaSetter.accept(target, lambdaGetter.apply(source));
                    }
                }
                case 9: {
                    targetPropertyAccess = targetPropertyAccesses[index++];
                    lambdaSetter = targetPropertyAccess.getLambdaSetter();
                    sourcePropertyAccess = sourcePropertyAccessMapping.get(targetPropertyAccess.getPropertyName());
                    if (lambdaSetter != null && sourcePropertyAccess != null && (lambdaGetter = sourcePropertyAccess.getLambdaGetter()) != null) {
                        lambdaSetter.accept(target, lambdaGetter.apply(source));
                    }
                }
                case 8: {
                    targetPropertyAccess = targetPropertyAccesses[index++];
                    lambdaSetter = targetPropertyAccess.getLambdaSetter();
                    sourcePropertyAccess = sourcePropertyAccessMapping.get(targetPropertyAccess.getPropertyName());
                    if (lambdaSetter != null && sourcePropertyAccess != null && (lambdaGetter = sourcePropertyAccess.getLambdaGetter()) != null) {
                        lambdaSetter.accept(target, lambdaGetter.apply(source));
                    }
                }
                case 7: {
                    targetPropertyAccess = targetPropertyAccesses[index++];
                    lambdaSetter = targetPropertyAccess.getLambdaSetter();
                    sourcePropertyAccess = sourcePropertyAccessMapping.get(targetPropertyAccess.getPropertyName());
                    if (lambdaSetter != null && sourcePropertyAccess != null && (lambdaGetter = sourcePropertyAccess.getLambdaGetter()) != null) {
                        lambdaSetter.accept(target, lambdaGetter.apply(source));
                    }
                }
                case 6: {
                    targetPropertyAccess = targetPropertyAccesses[index++];
                    lambdaSetter = targetPropertyAccess.getLambdaSetter();
                    sourcePropertyAccess = sourcePropertyAccessMapping.get(targetPropertyAccess.getPropertyName());
                    if (lambdaSetter != null && sourcePropertyAccess != null && (lambdaGetter = sourcePropertyAccess.getLambdaGetter()) != null) {
                        lambdaSetter.accept(target, lambdaGetter.apply(source));
                    }
                }
                case 5: {
                    targetPropertyAccess = targetPropertyAccesses[index++];
                    lambdaSetter = targetPropertyAccess.getLambdaSetter();
                    sourcePropertyAccess = sourcePropertyAccessMapping.get(targetPropertyAccess.getPropertyName());
                    if (lambdaSetter != null && sourcePropertyAccess != null && (lambdaGetter = sourcePropertyAccess.getLambdaGetter()) != null) {
                        lambdaSetter.accept(target, lambdaGetter.apply(source));
                    }
                }
                case 4: {
                    targetPropertyAccess = targetPropertyAccesses[index++];
                    lambdaSetter = targetPropertyAccess.getLambdaSetter();
                    sourcePropertyAccess = sourcePropertyAccessMapping.get(targetPropertyAccess.getPropertyName());
                    if (lambdaSetter != null && sourcePropertyAccess != null && (lambdaGetter = sourcePropertyAccess.getLambdaGetter()) != null) {
                        lambdaSetter.accept(target, lambdaGetter.apply(source));
                    }
                }
                case 3: {
                    targetPropertyAccess = targetPropertyAccesses[index++];
                    lambdaSetter = targetPropertyAccess.getLambdaSetter();
                    sourcePropertyAccess = sourcePropertyAccessMapping.get(targetPropertyAccess.getPropertyName());
                    if (lambdaSetter != null && sourcePropertyAccess != null && (lambdaGetter = sourcePropertyAccess.getLambdaGetter()) != null) {
                        lambdaSetter.accept(target, lambdaGetter.apply(source));
                    }
                }
                case 2: {
                    targetPropertyAccess = targetPropertyAccesses[index++];
                    lambdaSetter = targetPropertyAccess.getLambdaSetter();
                    sourcePropertyAccess = sourcePropertyAccessMapping.get(targetPropertyAccess.getPropertyName());
                    if (lambdaSetter != null && sourcePropertyAccess != null && (lambdaGetter = sourcePropertyAccess.getLambdaGetter()) != null) {
                        lambdaSetter.accept(target, lambdaGetter.apply(source));
                    }
                }
                case 1: {
                    targetPropertyAccess = targetPropertyAccesses[index++];
                    lambdaSetter = targetPropertyAccess.getLambdaSetter();
                    sourcePropertyAccess = sourcePropertyAccessMapping.get(targetPropertyAccess.getPropertyName());
                    if (lambdaSetter != null && sourcePropertyAccess != null && (lambdaGetter = sourcePropertyAccess.getLambdaGetter()) != null) {
                        lambdaSetter.accept(target, lambdaGetter.apply(source));
                    }
                    break;
                }
                default: {
                }
            }
        }
    }

    public static <S, T> void copyLoop(final S source, final T target, final PropertyAccess<T>[] targetPropertyAccesses, final Map<String, PropertyAccess<S>> sourcePropertyAccessMapping, final Set<String> ignoredPropertySet) {
        final int length = targetPropertyAccesses.length;
        PropertyAccess<S> sourcePropertyAccess;
        PropertyAccess<T> targetPropertyAccess;
        BiConsumer<T, Object> lambdaSetter;
        Function<S, ?> lambdaGetter;
        int index = 0;
        while (index < length) {
            int sub = length - index;
            sub = sub > 20 ? 20 : sub;
            switch (sub) {
                case 20: {
                    targetPropertyAccess = targetPropertyAccesses[index++];
                    lambdaSetter = targetPropertyAccess.getLambdaSetter();
                    sourcePropertyAccess = sourcePropertyAccessMapping.get(targetPropertyAccess.getPropertyName());
                    if (lambdaSetter != null && sourcePropertyAccess != null && (lambdaGetter = sourcePropertyAccess.getLambdaGetter()) != null && !ignoredPropertySet.contains(targetPropertyAccess.getPropertyName())) {
                        lambdaSetter.accept(target, lambdaGetter.apply(source));
                    }
                }
                case 19: {
                    targetPropertyAccess = targetPropertyAccesses[index++];
                    lambdaSetter = targetPropertyAccess.getLambdaSetter();
                    sourcePropertyAccess = sourcePropertyAccessMapping.get(targetPropertyAccess.getPropertyName());
                    if (lambdaSetter != null && sourcePropertyAccess != null && (lambdaGetter = sourcePropertyAccess.getLambdaGetter()) != null && !ignoredPropertySet.contains(targetPropertyAccess.getPropertyName())) {
                        lambdaSetter.accept(target, lambdaGetter.apply(source));
                    }
                }
                case 18: {
                    targetPropertyAccess = targetPropertyAccesses[index++];
                    lambdaSetter = targetPropertyAccess.getLambdaSetter();
                    sourcePropertyAccess = sourcePropertyAccessMapping.get(targetPropertyAccess.getPropertyName());
                    if (lambdaSetter != null && sourcePropertyAccess != null && (lambdaGetter = sourcePropertyAccess.getLambdaGetter()) != null && !ignoredPropertySet.contains(targetPropertyAccess.getPropertyName())) {
                        lambdaSetter.accept(target, lambdaGetter.apply(source));
                    }
                }
                case 17: {
                    targetPropertyAccess = targetPropertyAccesses[index++];
                    lambdaSetter = targetPropertyAccess.getLambdaSetter();
                    sourcePropertyAccess = sourcePropertyAccessMapping.get(targetPropertyAccess.getPropertyName());
                    if (lambdaSetter != null && sourcePropertyAccess != null && (lambdaGetter = sourcePropertyAccess.getLambdaGetter()) != null && !ignoredPropertySet.contains(targetPropertyAccess.getPropertyName())) {
                        lambdaSetter.accept(target, lambdaGetter.apply(source));
                    }
                }
                case 16: {
                    targetPropertyAccess = targetPropertyAccesses[index++];
                    lambdaSetter = targetPropertyAccess.getLambdaSetter();
                    sourcePropertyAccess = sourcePropertyAccessMapping.get(targetPropertyAccess.getPropertyName());
                    if (lambdaSetter != null && sourcePropertyAccess != null && (lambdaGetter = sourcePropertyAccess.getLambdaGetter()) != null && !ignoredPropertySet.contains(targetPropertyAccess.getPropertyName())) {
                        lambdaSetter.accept(target, lambdaGetter.apply(source));
                    }
                }
                case 15: {
                    targetPropertyAccess = targetPropertyAccesses[index++];
                    lambdaSetter = targetPropertyAccess.getLambdaSetter();
                    sourcePropertyAccess = sourcePropertyAccessMapping.get(targetPropertyAccess.getPropertyName());
                    if (lambdaSetter != null && sourcePropertyAccess != null && (lambdaGetter = sourcePropertyAccess.getLambdaGetter()) != null && !ignoredPropertySet.contains(targetPropertyAccess.getPropertyName())) {
                        lambdaSetter.accept(target, lambdaGetter.apply(source));
                    }
                }
                case 14: {
                    targetPropertyAccess = targetPropertyAccesses[index++];
                    lambdaSetter = targetPropertyAccess.getLambdaSetter();
                    sourcePropertyAccess = sourcePropertyAccessMapping.get(targetPropertyAccess.getPropertyName());
                    if (lambdaSetter != null && sourcePropertyAccess != null && (lambdaGetter = sourcePropertyAccess.getLambdaGetter()) != null && !ignoredPropertySet.contains(targetPropertyAccess.getPropertyName())) {
                        lambdaSetter.accept(target, lambdaGetter.apply(source));
                    }
                }
                case 13: {
                    targetPropertyAccess = targetPropertyAccesses[index++];
                    lambdaSetter = targetPropertyAccess.getLambdaSetter();
                    sourcePropertyAccess = sourcePropertyAccessMapping.get(targetPropertyAccess.getPropertyName());
                    if (lambdaSetter != null && sourcePropertyAccess != null && (lambdaGetter = sourcePropertyAccess.getLambdaGetter()) != null && !ignoredPropertySet.contains(targetPropertyAccess.getPropertyName())) {
                        lambdaSetter.accept(target, lambdaGetter.apply(source));
                    }
                }
                case 12: {
                    targetPropertyAccess = targetPropertyAccesses[index++];
                    lambdaSetter = targetPropertyAccess.getLambdaSetter();
                    sourcePropertyAccess = sourcePropertyAccessMapping.get(targetPropertyAccess.getPropertyName());
                    if (lambdaSetter != null && sourcePropertyAccess != null && (lambdaGetter = sourcePropertyAccess.getLambdaGetter()) != null && !ignoredPropertySet.contains(targetPropertyAccess.getPropertyName())) {
                        lambdaSetter.accept(target, lambdaGetter.apply(source));
                    }
                }
                case 11: {
                    targetPropertyAccess = targetPropertyAccesses[index++];
                    lambdaSetter = targetPropertyAccess.getLambdaSetter();
                    sourcePropertyAccess = sourcePropertyAccessMapping.get(targetPropertyAccess.getPropertyName());
                    if (lambdaSetter != null && sourcePropertyAccess != null && (lambdaGetter = sourcePropertyAccess.getLambdaGetter()) != null && !ignoredPropertySet.contains(targetPropertyAccess.getPropertyName())) {
                        lambdaSetter.accept(target, lambdaGetter.apply(source));
                    }
                }
                case 10: {
                    targetPropertyAccess = targetPropertyAccesses[index++];
                    lambdaSetter = targetPropertyAccess.getLambdaSetter();
                    sourcePropertyAccess = sourcePropertyAccessMapping.get(targetPropertyAccess.getPropertyName());
                    if (lambdaSetter != null && sourcePropertyAccess != null && (lambdaGetter = sourcePropertyAccess.getLambdaGetter()) != null && !ignoredPropertySet.contains(targetPropertyAccess.getPropertyName())) {
                        lambdaSetter.accept(target, lambdaGetter.apply(source));
                    }
                }
                case 9: {
                    targetPropertyAccess = targetPropertyAccesses[index++];
                    lambdaSetter = targetPropertyAccess.getLambdaSetter();
                    sourcePropertyAccess = sourcePropertyAccessMapping.get(targetPropertyAccess.getPropertyName());
                    if (lambdaSetter != null && sourcePropertyAccess != null && (lambdaGetter = sourcePropertyAccess.getLambdaGetter()) != null && !ignoredPropertySet.contains(targetPropertyAccess.getPropertyName())) {
                        lambdaSetter.accept(target, lambdaGetter.apply(source));
                    }
                }
                case 8: {
                    targetPropertyAccess = targetPropertyAccesses[index++];
                    lambdaSetter = targetPropertyAccess.getLambdaSetter();
                    sourcePropertyAccess = sourcePropertyAccessMapping.get(targetPropertyAccess.getPropertyName());
                    if (lambdaSetter != null && sourcePropertyAccess != null && (lambdaGetter = sourcePropertyAccess.getLambdaGetter()) != null && !ignoredPropertySet.contains(targetPropertyAccess.getPropertyName())) {
                        lambdaSetter.accept(target, lambdaGetter.apply(source));
                    }
                }
                case 7: {
                    targetPropertyAccess = targetPropertyAccesses[index++];
                    lambdaSetter = targetPropertyAccess.getLambdaSetter();
                    sourcePropertyAccess = sourcePropertyAccessMapping.get(targetPropertyAccess.getPropertyName());
                    if (lambdaSetter != null && sourcePropertyAccess != null && (lambdaGetter = sourcePropertyAccess.getLambdaGetter()) != null && !ignoredPropertySet.contains(targetPropertyAccess.getPropertyName())) {
                        lambdaSetter.accept(target, lambdaGetter.apply(source));
                    }
                }
                case 6: {
                    targetPropertyAccess = targetPropertyAccesses[index++];
                    lambdaSetter = targetPropertyAccess.getLambdaSetter();
                    sourcePropertyAccess = sourcePropertyAccessMapping.get(targetPropertyAccess.getPropertyName());
                    if (lambdaSetter != null && sourcePropertyAccess != null && (lambdaGetter = sourcePropertyAccess.getLambdaGetter()) != null && !ignoredPropertySet.contains(targetPropertyAccess.getPropertyName())) {
                        lambdaSetter.accept(target, lambdaGetter.apply(source));
                    }
                }
                case 5: {
                    targetPropertyAccess = targetPropertyAccesses[index++];
                    lambdaSetter = targetPropertyAccess.getLambdaSetter();
                    sourcePropertyAccess = sourcePropertyAccessMapping.get(targetPropertyAccess.getPropertyName());
                    if (lambdaSetter != null && sourcePropertyAccess != null && (lambdaGetter = sourcePropertyAccess.getLambdaGetter()) != null && !ignoredPropertySet.contains(targetPropertyAccess.getPropertyName())) {
                        lambdaSetter.accept(target, lambdaGetter.apply(source));
                    }
                }
                case 4: {
                    targetPropertyAccess = targetPropertyAccesses[index++];
                    lambdaSetter = targetPropertyAccess.getLambdaSetter();
                    sourcePropertyAccess = sourcePropertyAccessMapping.get(targetPropertyAccess.getPropertyName());
                    if (lambdaSetter != null && sourcePropertyAccess != null && (lambdaGetter = sourcePropertyAccess.getLambdaGetter()) != null && !ignoredPropertySet.contains(targetPropertyAccess.getPropertyName())) {
                        lambdaSetter.accept(target, lambdaGetter.apply(source));
                    }
                }
                case 3: {
                    targetPropertyAccess = targetPropertyAccesses[index++];
                    lambdaSetter = targetPropertyAccess.getLambdaSetter();
                    sourcePropertyAccess = sourcePropertyAccessMapping.get(targetPropertyAccess.getPropertyName());
                    if (lambdaSetter != null && sourcePropertyAccess != null && (lambdaGetter = sourcePropertyAccess.getLambdaGetter()) != null && !ignoredPropertySet.contains(targetPropertyAccess.getPropertyName())) {
                        lambdaSetter.accept(target, lambdaGetter.apply(source));
                    }
                }
                case 2: {
                    targetPropertyAccess = targetPropertyAccesses[index++];
                    lambdaSetter = targetPropertyAccess.getLambdaSetter();
                    sourcePropertyAccess = sourcePropertyAccessMapping.get(targetPropertyAccess.getPropertyName());
                    if (lambdaSetter != null && sourcePropertyAccess != null && (lambdaGetter = sourcePropertyAccess.getLambdaGetter()) != null && !ignoredPropertySet.contains(targetPropertyAccess.getPropertyName())) {
                        lambdaSetter.accept(target, lambdaGetter.apply(source));
                    }
                }
                case 1: {
                    targetPropertyAccess = targetPropertyAccesses[index++];
                    lambdaSetter = targetPropertyAccess.getLambdaSetter();
                    sourcePropertyAccess = sourcePropertyAccessMapping.get(targetPropertyAccess.getPropertyName());
                    if (lambdaSetter != null && sourcePropertyAccess != null && (lambdaGetter = sourcePropertyAccess.getLambdaGetter()) != null && !ignoredPropertySet.contains(targetPropertyAccess.getPropertyName())) {
                        lambdaSetter.accept(target, lambdaGetter.apply(source));
                    }
                    break;
                }
                default: {
                }
            }
        }
    }

}
