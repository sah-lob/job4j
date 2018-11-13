package ru.job4j.calculator;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class CalculatorTest {

    @Test
    public void whenAddOnePlusOneThenTwo() {
        Calculator calc = new Calculator();
        calc.add(1D, 1D);
        double result = calc.getResult();
        double expected = 2D;
        assertThat(result, is(expected));
    }

    @Test
    public void whenSubtractionOneMinusOneThenZero() {
        Calculator calc = new Calculator();
        calc.subtraction(1D, 1D);
        double result = calc.getResult();
        double expected = 0D;
        assertThat(result, is(expected));
    }

    @Test
    public void whenMultiplyTwoToFourThenEitht() {
        Calculator calc = new Calculator();
        calc.multiply(2D, 4D);
        double result = calc.getResult();
        double expected = 8D;
        assertThat(result, is(expected));
    }

    @Test
    public void whenDivisionFourtoTwoThenTwo(){
        Calculator calc = new Calculator();
        calc.division(4D,2D);
        double result = calc.getResult();
        double expected = 2D;
        assertThat(result, is(expected));
    }

    @Test
    public void whenDivisionFourtoZeroThenZero(){
        Calculator calc = new Calculator();
        calc.division(4D,0D);
        double result = calc.getResult();
        double expected = 0;
        assertThat(result, is(expected));
    }
}