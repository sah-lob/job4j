package ru.job4j.map;

import java.util.Hashtable;
import java.util.Iterator;

public class MyHashMap<K, V> implements Iterator {

    private Hashtable<K, V> hashTable = new Hashtable();
    private Object[] mas = hashTable.values().toArray();
    int index = 0;

    boolean insert(K key, V value) {
        var result = true;
        for (K k : hashTable.keySet()) {
            if (k.equals(key)) {
                result = false;
                break;
            }
        }
        if (result) {
            hashTable.put(key, value);
        }
        return result;
    }

    V get(K key) {
        return hashTable.get(key);
    }

    boolean delete(K key) {
        var size = hashTable.size();
        hashTable.remove(key);
        return size != hashTable.size();
    }

    int size() {
        return hashTable.size();
    }

    @Override
    public boolean hasNext() {
        if (mas.length != hashTable.size()) {
            mas = hashTable.values().toArray();
            index = 0;
        }
        var result = false;

        if (mas.length - 1  >= index) {
            result = true;
        }

        return result;
    }

    @Override
    public Object next() {
        V result = null;
        if (hasNext()) {
            result = (V) mas[index++];
        }
        return result;
    }
}
