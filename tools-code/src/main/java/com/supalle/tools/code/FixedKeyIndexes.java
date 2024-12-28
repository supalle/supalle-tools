package com.supalle.tools.code;

import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 固定Key的索引
 *
 * @param <K> key的类型
 */
public class FixedKeyIndexes<K> extends LinkedHashMap<K, Integer> {
    public static final FixedKeyIndexes<?> EMPTY = new FixedKeyIndexes<>();

    public FixedKeyIndexes() {
    }

    public FixedKeyIndexes(Collection<K> keys) {
        super((keys = normalizeKeys(keys)).size(), 1F);
        if (keys.isEmpty()) {
            return;
        }

        int index = 0;
        for (K key : keys) {
            super.put(key, index++);
        }
    }

    /**
     * 规范化 Keys
     *
     * @param keys 键集合
     * @param <K>  键的类型
     * @return 规范化后的键集合
     */
    public static <K> List<K> normalizeKeys(Collection<K> keys) {
        if (keys == null || keys.isEmpty()) {
            return Collections.emptyList();
        }
        return keys.stream().filter(Objects::nonNull).distinct().collect(Collectors.toList());
    }

    @SuppressWarnings("unchecked")
    public static <K> FixedKeyIndexes<K> of(Collection<K> keys) {
        if (keys == null || keys.isEmpty()) {
            return (FixedKeyIndexes<K>) EMPTY;
        }
        return new FixedKeyIndexes<>(keys);
    }

    @SuppressWarnings("unchecked")
    public static <K> FixedKeyIndexes<K> of(K... keys) {
        if (keys == null || keys.length == 0) {
            return (FixedKeyIndexes<K>) EMPTY;
        }
        return new FixedKeyIndexes<>(Arrays.asList(keys));
    }

    public Integer put(K key, Integer value) {
        throw new UnsupportedOperationException();
    }

    public Integer remove(Object key) {
        throw new UnsupportedOperationException();
    }

    public void putAll(Map<? extends K, ? extends Integer> m) {
        throw new UnsupportedOperationException();
    }

    public void clear() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void replaceAll(BiFunction<? super K, ? super Integer, ? extends Integer> function) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Integer putIfAbsent(K key, Integer value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean remove(Object key, Object value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean replace(K key, Integer oldValue, Integer newValue) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Integer replace(K key, Integer value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Integer computeIfAbsent(K key, Function<? super K, ? extends Integer> mappingFunction) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Integer computeIfPresent(K key,
                                    BiFunction<? super K, ? super Integer, ? extends Integer> remappingFunction) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Integer compute(K key,
                           BiFunction<? super K, ? super Integer, ? extends Integer> remappingFunction) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Integer merge(K key, Integer value,
                         BiFunction<? super Integer, ? super Integer, ? extends Integer> remappingFunction) {
        throw new UnsupportedOperationException();
    }

}
