package ru.job4j.stream;

import java.util.Arrays;

public class Stream {
    public int filter(int[] mas) {
        return Arrays.stream(mas).filter(x -> x % 2 == 0).map(t -> t = t * t).reduce((x1, x2) -> x1 + x2).getAsInt();
    }
}
