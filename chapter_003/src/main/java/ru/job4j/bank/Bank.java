package ru.job4j.bank;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public class Bank {

    private TreeMap<User, ArrayList<Account>> usersAccounts = new TreeMap<>();


    /**
     * Добавление нового пользователя в список.
     * @param user новый пользователь
     */
    public void addUser(User user) {
        this.usersAccounts.putIfAbsent(user, new ArrayList<>());
    }

    /**
     *  Удаление пользователя из списка.
     * @param user пользователь, которого надо удалить.
     */
    public void deleteUser(User user) {
        this.usersAccounts.remove(user);
    }

    /**
     * Добавить счет пользователю.
     * @param passport паспорт пользователя.
     * @param account счет пользователя.
     */
    public void addAccountToUser(String passport, Account account) {
        this.usersAccounts.get(findByPassport(passport)).add(account);
    }

    /**
     * Удалить один счет пользьзователя.
     * @param passport паспорт пользователя.
     * @param account счет пользователя.
     */
    public void deleteAccountFromUser(String passport, Account account) {
        this.usersAccounts.get(findByPassport(passport)).remove(account);
    }

    /**
     * Получить список счетов для пользователя.
     * @param passport паспорт пользователя.
     */
    public List<Account> getUserAccounts(String passport) {
            return findByPassport(passport) != null ? this.usersAccounts.get(findByPassport(passport)) : null;
    }

    /**
     * метод для перечисления денег с одного счёта на другой счёт;
     * @param srcPassport Паспорт пользователя, со счета которого переводят деньги.
     * @param srcRequisite Реквезиты счета, с которого переводят деньги.
     * @param destPassport Паспорт пользователя, на счет которого переводят деньги.
     * @param destRequisite Реквезиты счета, на который переводят деньги.
     * @param amount сумма перевода.
     * @return результат проведения транзакции.
     */
    public boolean transferMoney(String srcPassport, String srcRequisite, String destPassport, String destRequisite, double amount) {
        boolean flag = false;
            Account account1 = findByPassportandRequisite(srcPassport, srcRequisite);
            Account account2 = findByPassportandRequisite(destPassport, destRequisite);
            if (account1 != null && account2 != null) {
                flag = account1.transfer(amount, account2);
            }
        return flag;
    }

    public TreeMap<User, ArrayList<Account>> getUsersAccounts() {
        return usersAccounts;
    }

    /**
     * Поиск пользователя по номеру паспорта.
     * @param passport номер паспорта.
     * @return пользователь.
     */
    public User findByPassport(String passport) {
        User us = null;
        for (User user: usersAccounts.keySet()) {
            if (passport.equals(user.getPassport())) {
                us = user;
                break;
            }
        }
        return us;
    }

    /**
     * Поиск аккаунта по номеру паспорта и реквизитам.
     * @param passport номер паспорта.
     * @param requisite номер реквизитов.
     * @return аккаунт.
     */
    public Account findByPassportandRequisite(String passport, String requisite) {
        Account account = null;
        for (User user: usersAccounts.keySet()) {
            for (Account a : usersAccounts.get(user)) {
                if (user.getPassport().equals(passport) && a.getRequisites().equals(requisite)) {
                    account = a;
                    break;
                }
            }
        }
        return account;
    }
}
