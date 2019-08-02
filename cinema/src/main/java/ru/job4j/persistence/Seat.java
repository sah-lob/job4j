package ru.job4j.persistence;

import java.util.Objects;

public class Seat implements Comparable<Seat> {
    private String login;
    private int row;
    private int number;
    private int price;
    private boolean busy;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public boolean isBusy() {
        return busy;
    }

    public void setBusy(boolean busy) {
        this.busy = busy;
    }


    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        var seat = (Seat) o;
        return row == seat.row
                && number == seat.number
                && busy == seat.busy
                && Objects.equals(login, seat.login);
    }

    @Override
    public int hashCode() {
        return Objects.hash(login, row, number, busy);
    }

    @Override
    public int compareTo(Seat o) {
        int result = Integer.compare(this.row, o.row);
        return result != 0 ? result : Integer.compare(this.number, o.number);
    }
}
