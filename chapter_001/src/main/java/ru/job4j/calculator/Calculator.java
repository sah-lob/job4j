package ru.job4j.calculator;

public class Calculator {

    private double result;

    public void add(double first, double second) {
        this.result = first + second;
    }

    public void subtraction(double first, double second){
        this.result = first - second;
    }

    public void multiply(double first, double second){
        this.result = first*second;
    }

    public void division(double first, double second){
        if(second !=0){
            this.result = first/second;
        }
    }

    public double getResult() {
        return this.result;
    }
}
