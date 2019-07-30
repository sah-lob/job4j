package ru.job4j.persistence;

import org.apache.commons.dbcp2.BasicDataSource;
import java.util.HashMap;
import java.util.Map;

public class DBUsersStore implements UsersStore {

    private static final BasicDataSource SOURCE = new BasicDataSource();
    private final static DBUsersStore DB_STORE = new DBUsersStore();

    public DBUsersStore() {
        SOURCE.setDriverClassName("org.postgresql.Driver");
        SOURCE.setUrl("jdbc:postgresql://localhost:5432/sah_lob_cinema");
        SOURCE.setUsername("postgres");
        SOURCE.setPassword("Qazqaz23");
        SOURCE.setMinIdle(5);
        SOURCE.setMaxIdle(10);
        SOURCE.setMaxOpenPreparedStatements(100);
    }

    public static DBUsersStore getInstance() {
        return DB_STORE;
    }

    @Override
    public void add(Person person) {
        var sql = "INSERT into users (login, password) values (?, ?)";
        try (var connection = SOURCE.getConnection()) {
            var st = connection.prepareStatement(sql);
            st.setString(1, person.getLogin());
            st.setString(2, person.getPassword());
            st.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Map<String, Person> findAll() {
        var persons = new HashMap<String, Person>();
        var sql = "SELECT * FROM users";
        try (var connection = SOURCE.getConnection()) {
            var st = connection.prepareStatement(sql);
            var rs = st.executeQuery();
            while (rs.next()) {
                var person = new Person();
                person.setLogin(rs.getString("login"));
                person.setPassword(rs.getString("password"));
                person.setDescription("O");
                persons.put(person.getLogin(), person);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return persons;
    }

    @Override
    public Person findByLogin(String login) {
        var person = new Person();
        var sql = "SELECT * FROM users WHERE login = ?";
        try (var connection = SOURCE.getConnection()) {
            var st = connection.prepareStatement(sql);
            st.setString(1, person.getLogin());
            var rs = st.executeQuery();
            rs.next();
            person.setLogin(rs.getString("login"));
            person.setPassword(rs.getString("password"));
            person.setDescription("O");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return person;
    }

    @Override
    public boolean isExists(String login) {
        var result = false;
        var sql = "select 1 from users where login = ? limit 1";
        try (var connection = SOURCE.getConnection()) {
            var st = connection.prepareStatement(sql);
            st.setString(1, login);
            var rs = st.executeQuery();
            if (rs.next()) {
                result = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean validatePerson(Person person) {
        var result = false;
        var sql = "select 1 from users where login = ? and password = ? limit 1";
        try (var connection = SOURCE.getConnection()) {
            var st = connection.prepareStatement(sql);
            st.setString(1, person.getLogin());
            st.setString(2, person.getPassword());
            var rs = st.executeQuery();
            if (rs.next()) {
                result = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean editPerson(String login, String fio, String number) {
        var result = false;
        var sql = "UPDATE  users SET phone = ?, fio = ? WHERE login = ?";
        try (var connection = SOURCE.getConnection()) {
            var st = connection.prepareStatement(sql);
            st.setString(1, number);
            st.setString(2, fio);
            st.setString(3, login);
            st.executeUpdate();
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public String[] getFioAndNumber(String login) {
        String[] strings = new String[2];
        var sql = "select fio, phone from users where login = ? limit 1";
        try (var connection = SOURCE.getConnection()) {
            var st = connection.prepareStatement(sql);
            st.setString(1, login);
            var rs = st.executeQuery();
            if (rs.next()) {
                strings[0] = rs.getString("fio");
                strings[1] = rs.getString("phone");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return strings;
    }
}
