package ru.job4j.usersmodel.persistent;
import org.apache.commons.dbcp2.BasicDataSource;
import ru.job4j.usersmodel.User;
import java.util.ArrayList;
import java.util.List;

public class DBStore implements Store {

    private static final BasicDataSource SOURCE = new BasicDataSource();
    private final static DBStore DB_STORE = new DBStore();
    public DBStore() {
        SOURCE.setDriverClassName("org.postgresql.Driver");
        SOURCE.setUrl("jdbc:postgresql://localhost:5432/sah_lob_users_data_base");
        SOURCE.setUsername("postgres");
        SOURCE.setPassword("Qazqaz23");
        SOURCE.setMinIdle(5);
        SOURCE.setMaxIdle(10);
        SOURCE.setMaxOpenPreparedStatements(100);
    }

    public static DBStore getInstance() {
        return DB_STORE;
    }

    @Override
    public void add(User user) {
        var sql = "INSERT into users (name, login, email, createDate, admin, password) values (?, ?, ?, ?, ?, ?)";
        String adm = user.isAdmin() ? "yes" : "no";
        try (var connection = SOURCE.getConnection()) {
            var st = connection.prepareStatement(sql);
            st.setString(1, user.getName());
            st.setString(2, user.getLogin());
            st.setString(3, user.getEmail());
            st.setString(4, user.getCreateDate());
            st.setString(5, adm);
            st.setString(6, user.getPassword());
            st.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(String id, String name, String login, String email, boolean admin, String password) {
        var sql = "UPDATE users SET name = ?, login = ?, email = ?, admin = ?, password = ? WHERE id = ?";
        String adm = admin ? "yes" : "no";
        try (var connection = SOURCE.getConnection()) {
            var st = connection.prepareStatement(sql);
            st.setString(1, name);
            st.setString(2, login);
            st.setString(3, email);
            st.setString(4, adm);
            st.setString(5, password);
            st.setInt(6, Integer.parseInt(id));
            st.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(String id) {
        var sql = "delete from users where id = ?";
        try (var connection = SOURCE.getConnection()) {
            var st = connection.prepareStatement(sql);
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
        try (var connection = SOURCE.getConnection()) {
            var st = connection.prepareStatement(sql);
            var rs = st.executeQuery();
            while (rs.next()) {
                var user = new User(rs.getString("id"), rs.getString("name"),
                        rs.getString("login"), rs.getString("email"), rs.getString("createDate"),
                        rs.getString("admin").equals("yes"), rs.getString("password"));
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
        try (var connection = SOURCE.getConnection()) {
            var st = connection.prepareStatement(sql);
            st.setInt(1, Integer.parseInt(id));
            var rs = st.executeQuery();
            rs.next();
            boolean admin = rs.getString("admin").equals("yes");
            user = new User(rs.getString("id"), rs.getString("name"),
                    rs.getString("login"), rs.getString("email"),
                    rs.getString("createDate"), admin, rs.getString("password"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    public boolean isExists(String id) {
        var result = false;
        var sql = "select 1 from users where id = ? limit 1";
        try (var connection = SOURCE.getConnection()) {
            var st = connection.prepareStatement(sql);
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
