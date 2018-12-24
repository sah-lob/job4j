package ru.job4j.lambda;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class RangeTest {

    @Test
    public void linearFunction0To5() {
        Range range = new Range();
        List<Double> result;
        result = range.diapason(
                0, 5,
                (index) -> index);
        List<Double> function = Arrays.asList(0D, 1D, 2D, 3D, 4D, 5D);

        assertThat(result, is(function));
    }

    @Test
    public void quadraticFunction0To5() {
        Range range = new Range();
        List<Double> result;
        result = range.diapason(
                0, 5,
                (index) -> index * index);
        List<Double> function = Arrays.asList(0D, 1D, 4D, 9D, 16D, 25D);

        assertThat(result, is(function));
    }

    @Test
    public void logarithmicFunction0To5() {
        Range range = new Range();
        List<Double> result;
        result = range.diapason(
                0, 5,
                (index) -> Math.log(index));
        List<Double> function = Arrays.asList(Double.POSITIVE_INFINITY * -1, 0.0, 0.6931471805599453, 1.0986122886681098, 1.3862943611198906, 1.6094379124341003);

        assertThat(result, is(function));
    }


}
