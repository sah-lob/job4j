package ru.job4j.calculator;

public class IngCalculator {

    private Calculator calculator = new Calculator();
    private double result;

    public void add(double first, double second) {
        calculator.add(first, second);
        result = calculator.result;
    }
    public void subtraction(double first, double second) {
        calculator.subtraction(first, second);
        result = calculator.result;
    }
    public void multiply(double first, double second) {
        calculator.multiply(first, second);
        result = calculator.result;
    }
    public void division(double first, double second) {
        calculator.division(first, second);
        result = calculator.result;
    }
    public void exponentiation(double first, double second) {
        result = Math.pow(first, second);
    }
    public void root(double first, double second) {
        result = Math.pow( first, 1.0 / second );
    }
    public double getResult() {
        return this.result;
    }
}
