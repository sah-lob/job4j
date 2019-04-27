package ru.job4j.calculator;

public class IngCalculator extends Calculator {

    public void exponentiation(double first, double second) {
        result = Math.pow(first, second);
    }

    public void root(double first, double second) {
        result = Math.pow( first, 1.0 / second );
    }
}
