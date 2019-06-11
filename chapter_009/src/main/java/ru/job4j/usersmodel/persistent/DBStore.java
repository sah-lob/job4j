package ru.job4j.usersmodel.persistent;
import org.apache.commons.dbcp2.BasicDataSource;
import ru.job4j.usersmodel.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DBStore implements Store {

    private static final BasicDataSource SOURCE = new BasicDataSource();
    private final static DBStore DB_STORE = new DBStore();
    private static Connection connection;
    public DBStore() {
        SOURCE.setDriverClassName("org.postgresql.Driver");
        SOURCE.setUrl("jdbc:postgresql://localhost:5432/sah_lob_users_data_base");
        SOURCE.setUsername("postgres");
        SOURCE.setPassword("Qazqaz23");
        SOURCE.setMinIdle(5);
        SOURCE.setMaxIdle(10);
        SOURCE.setMaxOpenPreparedStatements(100);
        try {
            connection = SOURCE.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static DBStore getInstance() {
        return DB_STORE;
    }

    @Override
    public void add(String name, String login, String email, String createDate) {
        var sql = "INSERT into users (name, login, email, createDate) values (?, ?, ?, ?)";
        try (var st = connection.prepareStatement(sql);) {
            st.setString(1, name);
            st.setString(2, login);
            st.setString(3, email);
            st.setString(4, createDate);
            st.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(String id, String name, String login, String email) {
        var sql = "UPDATE users SET name = ?, login = ?, email = ? WHERE id = ?";
        try (var st = connection.prepareStatement(sql);) {
            st.setString(1, name);
            st.setString(2, login);
            st.setString(3, email);
            st.setInt(4, Integer.parseInt(id));
            st.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(String id) {
        var sql = "delete from users where id = ?";
        try (var st = connection.prepareStatement(sql)) {
            st.setInt(1, Integer.parseInt(id));
            st.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<User> findAll() {
        var users = new ArrayList<User>();
        var sql = "SELECT * FROM users";
        try (var st = connection.prepareStatement(sql)) {
            var rs = st.executeQuery();
            while (rs.next()) {
                var user = new User(rs.getString("id"), rs.getString("name"),
                        rs.getString("login"), rs.getString("email"), rs.getString("createDate"));
                users.add(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public User findById(String id) {
        User user = null;
        var sql = "SELECT * FROM users WHERE id = ?";
        try (var st = connection.prepareStatement(sql)) {
            st.setInt(1, Integer.parseInt(id));
            var rs = st.executeQuery();
            rs.next();
            user = new User(rs.getString("id"), rs.getString("name"),
                    rs.getString("login"), rs.getString("email"), rs.getString("createDate"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    public boolean isExists(String id) {
        var result = false;
        var sql = "select 1 from users where id = ? limit 1";
        try (var st = connection.prepareStatement(sql)) {
            st.setInt(1, Integer.parseInt(id));
            var rs = st.executeQuery();
            if (rs.next()) {
                result = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
