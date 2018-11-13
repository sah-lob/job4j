package ru.job4j.calculator;

public class Calculator {

    private double result;

    /**
     * Сложение двух чисел.
     * @param first - первое число.
     * @param second - второе число.
     */
    public void add(double first, double second) {
        this.result = first + second;
    }

    /**
     * Вычитание двух чисел.
     * @param first - первое число.
     * @param second - второе число.
     */
    public void subtraction(double first, double second){
        this.result = first - second;
    }

    /**
     * Умножение двух чисел.
     * @param first - первое число.
     * @param second - второе число.
     */
    public void multiply(double first, double second){
        this.result = first*second;
    }

    /**
     * Деление двух чисел.
     * @param first - первое число.
     * @param second - второе число.
     */
    public void division(double first, double second){
        if(second !=0){
            this.result = first/second;
        }
    }

    public double getResult() {
        return this.result;
    }
}
