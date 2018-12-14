package ru.job4j.bank;


public class Account {

    private double value;
    private String requisites;


    public Account(double value, String requisites) {
        this.value = value;
        this.requisites = requisites;
    }


    public double getValue() {
        return value;
    }

    public String getRequisites() {
        return requisites;
    }

    public boolean transfer(double amount, Account account) {
        boolean flag = false;
        if (value >= amount) {
            flag = true;
            value -= amount;
            account.value += amount;
        }
        return flag;
    }
}
