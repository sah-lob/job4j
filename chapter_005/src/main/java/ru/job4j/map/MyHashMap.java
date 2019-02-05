package ru.job4j.map;

import java.util.Arrays;
import java.util.Iterator;

public class MyHashMap<K, V> implements Iterator {

    private PairMassiv[][] mas;
    private V[] mas2 = (V[]) new Object[0];
    private int index;
    private int[] ind;
    private int iteratorIndex;

    public MyHashMap() {
        this.mas = new PairMassiv[16][6];
        this.index = 0;
        this.ind = new int[16];
        for (int i: ind) {
            ind[i] = 0;
        }
    }

    boolean insert(K key, V value) {
        var position = key.hashCode() % mas.length;
        if (mas[position].length == ind[position]) {
            mas = Arrays.copyOf(mas, mas[0].length + 1);
        }
        var result = true;
        for (int i = 0; i < ind[position]; i++) {
            if (key.equals(mas[position][i].getKey())) {
                result = false;
            }
        }
        if (result) {
            mas[position][ind[position]++] = new PairMassiv(key, value);
            index++;
        }
        return result;
    }

    V get(K key) {
        V result = null;
        var position = key.hashCode() % mas.length;
        if (ind[position] > 1) {
            for (int i = 0; i < ind[position]; i++) {
                if (key.equals(mas[position][i].getKey())) {
                    result = (V) mas[position][i].getValue();
                    break;
                }
            }
        } else {
            result = (V) mas[position][0].getValue();
        }
        return result;
    }

    boolean delete(K key) {
        var result = false;
        var position = key.hashCode() % mas.length;
        if (ind[position] != 0) {
            for (int i = 0; i < ind[position]; i++) {
                if (key.equals(mas[position][i].getKey())) {
                    mas[position][i].setKey(null);
                    mas[position][i].setValue(null);
                    System.arraycopy(mas[position], i + 1, mas[position], i, mas[position].length - i - 1);
                    index--;
                    ind[position]--;
                    result = true;
                    break;
                }
            }
        }
        return result;
    }

    int size() {
        return index;
    }

    @Override
    public boolean hasNext() {
        var result = false;
        if (mas2.length != index) {
            iteratorIndex = 0;
            mas2 = (V[]) new Object[index];
            var len = 0;
            for (int i = 0; i < mas.length; i++) {
                for (int j = 0; j < ind[i]; j++) {
                    mas2[len] = (V) mas[i][j].getValue();
                    len++;
                }
            }
        }
        if (iteratorIndex != mas2.length) {
            result = true;
        }

        return result;
    }

    @Override
    public Object next() {
        V result = null;
        if (hasNext()) {
            result = mas2[iteratorIndex++];
        }
        return result;
    }
}
