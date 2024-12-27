package com.supalle.tools.code;

import lombok.Getter;

import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
public class FixedKeyMap<K, V> implements Map<K, V> {

    private final FixedKeyIndexes<K> fixedKeyIndexes;
    private final Object[] values;

    public FixedKeyMap(FixedKeyIndexes<K> fixedKeyIndexes) {
        this.fixedKeyIndexes = fixedKeyIndexes;
        values = new Object[fixedKeyIndexes.size()];
    }

    @Override
    public int size() {
        return fixedKeyIndexes.size();
    }

    @Override
    public boolean isEmpty() {
        return fixedKeyIndexes.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        return get(key) != null;
    }

    @Override
    public boolean containsValue(Object value) {
        return Arrays.asList(values).contains(value);
    }

    @SuppressWarnings("unchecked")
    @Override
    public V get(Object key) {
        Integer index = fixedKeyIndexes.get(key);
        if (index == null) {
            return null;
        }
        return (V) values[index];
    }

    @SuppressWarnings("unchecked")
    @Override
    public V put(K key, V value) {
        Integer index = fixedKeyIndexes.get(key);
        if (index == null) {
            return null;
        }
        Object oldValue = values[index];
        values[index] = value;
        return (V) oldValue;
    }

    @SuppressWarnings("all")
    @Override
    public V remove(Object key) {
        Integer index = fixedKeyIndexes.get(key);
        if (index == null) {
            return null;
        }
        Object oldValue = values[index];
        values[index] = null;
        return (V) oldValue;
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
        if (m == null) {
            return;
        }
        m.forEach(this::put);
    }

    @Override
    public void clear() {
        for (int i = 0, len = values.length; i < len; i++) {
            values[i] = null;
        }
    }

    @Override
    public Set<K> keySet() {
        return fixedKeyIndexes.keySet();
    }

    @SuppressWarnings("unchecked")
    @Override
    public Collection<V> values() {
        return Arrays.stream(values).map(v -> v == null ? null : (V) v).collect(Collectors.toList());
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        Set<Entry<K, Integer>> entries = fixedKeyIndexes.entrySet();
        return entries.stream().map(FixedKeyMapEntry::new).collect(Collectors.toSet());
    }

    class FixedKeyMapEntry implements Entry<K, V> {
        private final Entry<K, Integer> fixedKeyIndexEntry;

        FixedKeyMapEntry(Entry<K, Integer> fixedKeyIndexEntry) {
            this.fixedKeyIndexEntry = fixedKeyIndexEntry;
        }

        @Override
        public K getKey() {
            return fixedKeyIndexEntry.getKey();
        }

        @Override
        public V getValue() {
            return get(getKey());
        }

        @Override
        public V setValue(V value) {
            return put(getKey(), value);
        }
    }

    @Override
    public String toString() {
        if (isEmpty()) {
            return "{}";
        }
        StringBuilder builder = new StringBuilder();
        builder.append('{');
        fixedKeyIndexes.forEach((k, v) -> builder.append(k).append('=').append(values[v]).append(',').append(' '));
        builder.replace(builder.length() - 2, builder.length(), "}");
        return builder.toString();
    }

}
