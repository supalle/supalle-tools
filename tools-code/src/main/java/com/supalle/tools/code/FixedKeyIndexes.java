package com.supalle.tools.code;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * 固定Key的索引
 *
 * @param <K> key的类型
 */
public class FixedKeyIndexes<K> extends HashMap<K, Integer> {
    public static final FixedKeyIndexes<?> EMPTY = new FixedKeyIndexes<>();

    public FixedKeyIndexes() {
    }

    public FixedKeyIndexes(Set<K> keySet) {
        super(Objects.requireNonNull(keySet).size(), 1F);
        int index = 0;
        for (K key : keySet) {
            super.put(key, index++);
        }
    }

    @SuppressWarnings("unchecked")
    public static <K> FixedKeyIndexes<K> of(Set<K> keySet) {
        if (keySet == null || keySet.isEmpty()) {
            return (FixedKeyIndexes<K>) EMPTY;
        }
        return new FixedKeyIndexes<>(keySet);
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
