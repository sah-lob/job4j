package ru.job4j.bank;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;


public class AccountTest {

    @Test
    public void createAccountTest() {
        double value = 32_000;
        String requisites = "32sde3w2";
        Account account = new Account(value, requisites);
        assertThat(account.getRequisites() + account.getValue(), is(requisites + value));
    }

}
