package ru.job4j;

import org.junit.Test;
import ru.job4j.coffemachine.CoffeMachine;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class CoffeMachineTest {

    @Test
    public void coffeFor35() {
        CoffeMachine coffeMachine = new CoffeMachine(50, 35);
        Integer[] mas = new Integer[]{10, 5};
        Integer[] result = coffeMachine.iWantACapOfCoffe();
        assertThat(result, is(mas));
    }

    @Test
    public void coffeFor54() {
        CoffeMachine coffeMachine = new CoffeMachine(72, 54);
        Integer[] mas = new Integer[]{10, 5, 2, 1};
        Integer[] result = coffeMachine.iWantACapOfCoffe();
        assertThat(result, is(mas));
    }

    @Test
    public void coffeFor35WitoutMoney() {
        CoffeMachine coffeMachine = new CoffeMachine(10, 35);
        Integer[] mas = new Integer[]{};
        Integer[] result = coffeMachine.iWantACapOfCoffe();
        assertThat(result, is(mas));
    }
}
