package ru.job4j.array;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class BubbleSortTest {

    @Test
    public void sortArrayOfThreeNumbers() {
        BubbleSort bubbleSort = new BubbleSort();
        int[] mas = new int[]{3, 2, 1};
        mas = bubbleSort.sort(mas);
        int[] result = new int[]{1, 2, 3};
        assertThat(mas, is(result));
    }

    @Test
    public void sortArrayOfFiveNumbers() {
        BubbleSort bubbleSort = new BubbleSort();
        int[] mas = new int[]{3, 2, 1, 8, 9};
        mas = bubbleSort.sort(mas);
        int[] result = new int[]{1, 2, 3, 8, 9};
        assertThat(mas, is(result));
    }
}
