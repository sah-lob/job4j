package ru.job4j.gc.cache;

import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;

public class Cache<V, T> {

    Map<V, SoftReference<T>> cacheStore = new HashMap<>();

    public void add(V v, T t) {
        cacheStore.put(v, new SoftReference<>(t));
    }

    T get(V v) {
        SoftReference<T> softRef = cacheStore.get(v);
        return softRef != null ? softRef.get() : null;
    }
}
