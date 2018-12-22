package ru.job4j.department;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class DepartmentTest {

    @Test
    public void descendingSort() {
        Department department = new Department();
        String[] mas = new String[]{"K1\\SK1", "K1\\SK2", "K1\\SK1\\SSK1", "K1\\SK1\\SSK2", "K2", "K2\\SK1\\SSK1", "K2\\SK1\\SSK2"};
        String[] result = department.descendingSort(mas);
        String[] massiv = new String[]{"K2", "K2\\SK1", "K2\\SK1\\SSK2", "K2\\SK1\\SSK1", "K1", "K1\\SK2", "K1\\SK1", "K1\\SK1\\SSK2", "K1\\SK1\\SSK1"};

        assertThat(result, is(massiv));
    }

    @Test
    public void ascendingSort() {
        Department department = new Department();
        String[] mas = new String[]{"K1\\SK1", "K1\\SK2", "K1\\SK1\\SSK1", "K1\\SK1\\SSK2", "K2", "K2\\SK1\\SSK1", "K2\\SK1\\SSK2"};
        String[] result = department.ascendingSort(mas);
        String[] massiv = new String[]{"K1", "K1\\SK1", "K1\\SK1\\SSK1", "K1\\SK1\\SSK2", "K1\\SK2", "K2", "K2\\SK1", "K2\\SK1\\SSK1", "K2\\SK1\\SSK2"};

        assertThat(result, is(massiv));
    }
}
