package ru.job4j.lambda;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;

public class Range {

    List<Double> diapason(int start, int end, Function<Double, Double> func) {
        List<Double> function = new ArrayList<>();
        for (int i = start; i <= end; i++) {
            function.add(func.apply(Double.valueOf(i)));
        }
        return function;
    }

}
