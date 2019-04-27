package ru.job4j.calculator;
import java.util.Scanner;

public class InteractCalc {

    protected Calculator calculator;
    protected Scanner scanner = new Scanner(System.in);
    private double result;
    public boolean isResult = false;

    public InteractCalc() {
        calculator = new IngCalculator();
    }

    public static void main(String[] args) {
        new InteractCalc().start();
    }

    public void start() {
        while (true) {
            info();
            var firstString = input(1).toUpperCase();
            if (firstString.equals("КОНЕЦ")) {
                break;
            } else if (firstString.equals("ДА")) {
                result = calculate(result, Double.parseDouble(input(2)), input(3));
            } else {
                result = calculate(Double.parseDouble(firstString), Double.parseDouble(input(2)), input(3));
                isResult = true;
            }
            System.out.println("Ответ: " + result);
        }
    }

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
            default : calculator.add(0, 0);
                System.out.println("Такой операции нет.");
        }
        result = calculator.getResult();
        return result;
    }


    protected void info() {
        System.out.println("Введите первое число, знак, второе число.");
        System.out.println("Чтобы выйти из калькулятора введите 'Конец' вместо первого числа.");
        if (isResult) {
            System.out.println("Введите 'да' вместо первого числа, если хотите использовать полученный результат из прошлого вычисления.");
        }
    }

    protected String input(int num) {
        if (num == 1) {
            System.out.println("Введите первое число.");
        } else if (num ==  2) {
            System.out.println("Введите второе число.");
        } else if (num == 3) {
            System.out.println("Введите '+', '-', '*' или '/'");
        }
        var st = scanner.next();
        return st;
    }
}
