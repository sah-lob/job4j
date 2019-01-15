package arrays;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class SimpleArrayTest {

    SimpleArray simpleArrayList;

    @Before
    public void setUp() {
        simpleArrayList = new SimpleArray<Integer>(5);
        simpleArrayList.add(1);
        simpleArrayList.add(3);
        simpleArrayList.add(2);
        simpleArrayList.add(8);
        simpleArrayList.add(3);
    }

    @Test
    public void addThreeElements() {
        assertThat(simpleArrayList.get(0), is(1));
        assertThat(simpleArrayList.get(1), is(3));
        assertThat(simpleArrayList.get(2), is(2));
    }

    @Test
    public void setSecondElement() {
        simpleArrayList.set(1, 99);
        assertThat(simpleArrayList.get(1), is(99));
    }


    @Test
    public void removeForthElement() {
        simpleArrayList.remove(3);
        assertThat(simpleArrayList.get(3), is(3));
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void addExtraElement() {
        simpleArrayList.add(3);
    }
}