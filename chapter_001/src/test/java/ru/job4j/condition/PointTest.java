package ru.job4j.condition;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class PointTest {

    @Test
    public void theDistanceBetweenPointsIsFive() {
        Point a = new Point(0, 0);
        Point b = new Point(0, 5);
        double result = a.distanceTo(b);
        double expected = 5D;
        assertThat(result, is(expected));
    }
}
