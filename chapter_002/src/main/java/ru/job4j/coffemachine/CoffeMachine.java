package ru.job4j.coffemachine;

import java.util.ArrayList;

public class CoffeMachine {

    int[] changes = new int[2];

    public CoffeMachine(int value, int price) {
        changes[0] = value;
        changes[1] = price;
    }

    public Integer[] iWantACapOfCoffe() {

        int delivery = changes[0] - changes[1];

        ArrayList<Integer> manyCoins = new ArrayList<>();

            while (delivery > 0) {
                if (delivery >= 10) {
                    delivery -= 10;
                    manyCoins.add(10);
                } else if (delivery >= 5) {
                    delivery -= 5;
                    manyCoins.add(5);
                } else if (delivery >= 2) {
                    delivery -= 2;
                    manyCoins.add(2);
                } else {
                    delivery -= 1;
                    manyCoins.add(1);
                }
            }
        return manyCoins.toArray(new Integer[0]);
    }
}
