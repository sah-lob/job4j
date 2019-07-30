package ru.job4j.persistence;
import java.util.ArrayList;

public class Hall {
    private final ArrayList<Seat> seats;
    public Hall(int rows, int numbers, int price) {
        seats = new ArrayList<>();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < numbers; j++) {
                var s = new Seat();
                if (i ==  0 || i == rows - 1 || j == 0 || j == numbers - 1) {
                    s.setPrice((int) (price * 0.8));
                } else {
                    s.setPrice(price);
                }
                s.setBusy(false);
                s.setRow(i + 1);
                s.setNumber(j + 1);
                seats.add(s);
            }
        }
    }

    public ArrayList<Seat> createNewHall() {
        return seats;
    }
}
