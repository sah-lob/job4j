package ru.job4j.map;

import java.util.Iterator;

public class MyHashMap<K, V> implements Iterator {

    private Entry<K, V>[] table;
    private int tableLength;
    private int index;
    private int iteratorIndex;

    public MyHashMap() {
        this.tableLength = 16;
        this.table = new Entry[tableLength];
        this.index = 0;
        this.iteratorIndex = 0;
    }

    boolean insert(K key, V value) {

        var hash = hash(key.hashCode());
        var indexf = indexFor(hash, tableLength);
        var result = false;

        if (table[indexf] == null) {
            table[indexf] = new Entry(hash, key, value);
            index++;
            result = true;
        }

        return result;
    }

    V get(K key) {
        V result = null;
        var hash = hash(key.hashCode());
        var indexf = indexFor(hash, tableLength);
        if (table[indexf] != null) {
            result = table[indexf].getValue();
        }
        return result;
    }

    boolean delete(K key) {
        var result = false;
        var hash = hash(key.hashCode());
        var indexf = indexFor(hash, tableLength);
        if (table[indexf] != null) {
            table[indexf] = null;
            index--;
            result = true;
        }
        return result;
    }

    int size() {
        return index;
    }

    @Override
    public boolean hasNext() {
        var result = false;
        if (iteratorIndex != table.length - 1) {
            for (int i = iteratorIndex; i < table.length; i++) {
                if (table[i] != null) {
                    iteratorIndex = i;
                    result = true;
                    break;
                }
            }
        }
        return result;
    }

    @Override
    public Object next() {
        V result = null;
        if (hasNext()) {
            result = table[iteratorIndex++].getValue();
        }
        return result;
    }

    private static int hash(int h) {
        h ^= (h >>> 20) ^ (h >>> 12);
        return h ^ (h >>> 7) ^ (h >>> 4);
    }

    private static int indexFor(int h, int length) {
        return h & (length - 1);
    }

    static class Entry<K, V> {

        private int hash;
        private K key;
        private V val;

         private Entry(int hash, K key, V val) {
            this.hash = hash;
            this.key = key;
            this.val = val;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return val;
        }

    }
}
