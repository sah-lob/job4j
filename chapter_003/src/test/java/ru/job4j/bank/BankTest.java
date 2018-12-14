package ru.job4j.bank;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;


public class BankTest {

    @Test
    public void addNewUserInUsersAccounts() {
        Bank bank = new Bank();
        User user = new User("Alex", "231232");
        bank.addUser(user);
        int num = bank.getUsersAccounts().size();
        assertThat(num, is(1));
    }

    @Test
    public void deleteUserInUsersAccounts() {
        Bank bank = new Bank();
        User user = new User("Alex", "231232");
        bank.addUser(user);
        bank.deleteUser(user);
        int num = bank.getUsersAccounts().size();
        assertThat(num, is(0));
    }

    @Test
    public void addAccountToUser() {
        Bank bank = new Bank();
        User user = new User("Alex", "231232");
        Account account = new Account(30_000, "sd31wq1");
        bank.addUser(user);
        bank.addAccountToUser(user.getPassport(), account);
        double value = bank.getUsersAccounts().get(user).get(0).getValue();
        assertThat(value, is(account.getValue()));
    }

    @Test
    public void deleteAccountFromUser() {
        Bank bank = new Bank();
        User user = new User("Alex", "231232");
        Account account = new Account(30_000, "sd31wq1");
        bank.addUser(user);
        bank.addAccountToUser(user.getPassport(), account);
        bank.deleteAccountFromUser(user.getPassport(), account);
        int accountnum = bank.getUsersAccounts().get(user).size();
        assertThat(accountnum, is(0));
    }

    @Test
    public void getUserAccounts() {
        Bank bank = new Bank();
        User user = new User("Alex", "231232");
        Account account = new Account(30_000, "sd31wq1");
        bank.addUser(user);
        bank.addAccountToUser(user.getPassport(), account);
        Account newAccount = bank.getUserAccounts(user.getPassport()).get(0);
        assertThat(account, is(newAccount));
    }

    @Test
    public void transferMoney() {
        Bank bank = new Bank();

        User user = new User("Alex", "231232");
        User user2 = new User("Alexander", "893212");
        Account account = new Account(30_000, "sd31wq1");
        Account account2 = new Account(30_000, "s1");
        bank.addUser(user);
        bank.addUser(user2);
        bank.addAccountToUser(user.getPassport(), account);
        bank.addAccountToUser(user2.getPassport(), account2);
        bank.transferMoney(user.getPassport(), account.getRequisites(), user2.getPassport(), account2.getRequisites(), 2_000);
        double result = bank.getUserAccounts(user2.getPassport()).get(0).getValue();
        double value = 32_000;
        assertThat(value, is(result));
    }
}
