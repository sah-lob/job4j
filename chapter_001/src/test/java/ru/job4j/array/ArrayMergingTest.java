package ru.job4j.array;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ArrayMergingTest {

    @Test
    public void twoArraysInOneArray() {
        ArrayMerging arrayMerging = new ArrayMerging();
        int[] array1 = new int[]{1, 3, 5, 7};
        int[] array2 = new int[]{2, 4, 6, 8};
        int[] result = new int[]{1, 2, 3, 4, 5, 6, 7, 8};
        assertThat(arrayMerging.merginArrays(array1, array2), is(result));
    }

    @Test
    public void twoArraysInOneArray2() {
        ArrayMerging arrayMerging = new ArrayMerging();
        int[] array1 = new int[]{1, 3, 5, 7};
        int[] array2 = new int[]{2, 8};
        int[] result = new int[]{1, 2, 3, 5, 7, 8};
        assertThat(arrayMerging.merginArrays(array1, array2), is(result));
    }

    @Test
    public void twoArraysInOneArray3() {
        ArrayMerging arrayMerging = new ArrayMerging();
        int[] array1 = new int[]{1, 7};
        int[] array2 = new int[]{2, 4, 6, 8};
        int[] result = new int[]{1, 2, 4, 6, 7, 8};
        assertThat(arrayMerging.merginArrays(array1, array2), is(result));
    }
    @Test
    public void twoArraysInOneArray4() {
        ArrayMerging arrayMerging = new ArrayMerging();
        int[] array1 = new int[]{1};
        int[] array2 = new int[]{1};
        int[] result = new int[]{1, 1};
        assertThat(arrayMerging.merginArrays(array1, array2), is(result));
    }
    @Test
    public void twoArraysInOneArray5() {
        ArrayMerging arrayMerging = new ArrayMerging();
        int[] array1 = new int[]{1, 1, 1, 1};
        int[] array2 = new int[]{1, 1};
        int[] result = new int[]{1, 1, 1, 1, 1, 1};
        assertThat(arrayMerging.merginArrays(array1, array2), is(result));
    }
}
