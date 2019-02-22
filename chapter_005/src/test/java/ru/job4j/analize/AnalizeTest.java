package ru.job4j.analize;

import org.junit.Test;

import java.util.ArrayList;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class AnalizeTest {

    @Test
    public void analizeTest() {

        var previous = createToMassiv();
        var current = createToMassiv();

        current.add(new Analize.User(999, "Alexander999"));
        current.add(new Analize.User(998, "Alexander998"));
        current.add(new Analize.User(997, "Alexander997"));
        current.get(0).name = "Petr1";
        current.get(1).name = "Petr2";
        current.get(2).name = "Petr3";
        current.remove(3);
        current.remove(45);
        current.remove(54);
        var analize = new Analize();

        var info = analize.diff(previous, current);

        assertThat(info.added, is(3));
        assertThat(info.changed, is(3));
        assertThat(info.deleted, is(3));
        
    }


    public ArrayList<Analize.User> createToMassiv() {
        ArrayList<Analize.User> list = new ArrayList<>();
        for (int i = 0; i < 213; i++) {
            list.add(new Analize.User(i, "Alexander" + i));
        }
        return list;
    }


}