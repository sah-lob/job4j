package ru.job4j.calculator;

public class IngInteractCalc extends InteractCalc {

    protected IngCalculator calculator;


    public IngInteractCalc() {
        calculator = new IngCalculator();
    }

    public static void main(String[] args) {
        new IngInteractCalc().start();
    }

    @Override
    protected double calculate(double first, double second, String operation) {
        double result;
        switch (operation) {
            case ("+") : calculator.add(first, second);
                break;
            case ("-") : calculator.subtraction(first, second);
                break;
            case ("*") : calculator.multiply(first, second);
                break;
            case ("/") : calculator.division(first, second);
                break;
            case ("root") : calculator.root(first, second);
                break;
            case ("exp") : calculator.exponentiation(first, second);
                break;
            default : calculator.add(0, 0);
                System.out.println("Такой операции нет.");
        }
        result = calculator.getResult();
        return result;
    }


    @Override
    protected String input(int num) {
        if (num == 1) {
            System.out.println("Введите первое число.");
        } else if (num == 3) {
            System.out.println("Введите '+', '-', '*', '/' , 'root' или 'exp'");
        } else if (num ==  2) {
            System.out.println("Введите второе число.");
        }
        var st = scanner.next();
        return st;
    }
}
