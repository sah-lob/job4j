package ru.job4j.coffemachine;

import java.util.ArrayList;

public class CoffeMachine {

    int[] changes = new int[2];

    public CoffeMachine(int value, int price) {
        changes[0] = value;
        changes[1] = price;
    }

    public Integer[] change() {
        int delivery = changes[0] - changes[1];
        ArrayList<Integer> manyCoins = new ArrayList<>();
        int[] coins = new int[]{10, 5, 2, 1};
        for (int coin : coins) {
            while (delivery >= coin) {
                delivery -= coin;
                manyCoins.add(coin);
            }
        }
        return manyCoins.toArray(new Integer[0]);
    }
}
