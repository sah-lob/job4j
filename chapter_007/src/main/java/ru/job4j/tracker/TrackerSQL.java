package ru.job4j.tracker;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.sql.SQLStorage;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;

public class TrackerSQL implements ITracker, AutoCloseable {

    private static final Logger LOG = LoggerFactory.getLogger(SQLStorage.class);
    private final String url;
    private final String username;
    private final String password;
    private Connection conn;


    public TrackerSQL(String propertiesFileName) {
        var classLoader = TrackerSQL.class.getClassLoader();
        var path = classLoader.getResourceAsStream(propertiesFileName);
        var property = new Properties();
        try {
            property.load(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        url = property.getProperty("url");
        username = property.getProperty("username");
        password = property.getProperty("password");
    }

    @Override
    public void close() throws Exception {
        conn.close();
    }

    @Override
    public int add(Item item) {
        var name = item.getName();
        var description = item.getDesc();
        var comments = item.getComments();
        int idOfItem = -1;
        try {
            conn = DriverManager.getConnection(url, username, password);
            var sql = "INSERT into items (name, description) values (?,?) RETURNING ID";
            var st = conn.prepareStatement(sql);
            st.setString(1, name);
            st.setString(2, description);
            var rs = st.executeQuery();
            rs.next();
            idOfItem = rs.getInt("id");
            for (var comment : comments) {
                sql = "INSERT into comments (comment, item_id) values (?, ?)";
                st = conn.prepareStatement(sql);
                st.setString(1, comment);
                st.setInt(2, idOfItem);
                st.executeUpdate();
            }
            st.close();
            rs.close();
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    LOG.error(e.getMessage(), e);
                }
            }
        }
        return idOfItem;
    }

    @Override
    public void replace(String id, Item item) {
        var name = item.getName();
        var desc = item.getDesc();
        var comments = item.getComments();
        try {
            conn = DriverManager.getConnection(url, username, password);
            var sql = "DELETE FROM comments WHERE item_id = ?;"
                    + "UPDATE items SET name = ?, description = ?, date_of_creation = now() WHERE id = ?";
            var st = conn.prepareStatement(sql);
            st.setInt(1, Integer.parseInt(id));
            st.setString(2,  name);
            st.setString(3, desc);
            st.setInt(4, Integer.parseInt(id));
            st.executeQuery();
            sql = "INSERT into comments (comment, item_id) values (?, ?)";
            for (var comment : comments) {
                st = conn.prepareStatement(sql);
                st.setString(1, comment);
                st.setInt(2, Integer.parseInt(id));
                st.executeUpdate();
            }
            st.close();
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    LOG.error(e.getMessage(), e);
                }
            }
        }
    }

    @Override
    public void delete(String id) {
        try {
            conn = DriverManager.getConnection(url, username, password);
            var sql = "DELETE from comments where item_id = ?;"
                    + "DELETE from items where id = ?; ";
            var st = conn.prepareStatement(sql);
            st.setInt(1, Integer.parseInt(id));
            st.setInt(2, Integer.parseInt(id));
            st.executeQuery();
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    LOG.error(e.getMessage(), e);
                }
            }
        }
    }

    @Override
    public ArrayList<Item> findAll() {
        var items = new ArrayList<Item>();
        try {
            conn = DriverManager.getConnection(url, username, password);
            var st = conn.prepareStatement("SELECT * FROM items");
            var rs = st.executeQuery();
            // Если надо добавить данные, то вместо executeQuery надо будет написать executeUpdate();
            while (rs.next()) {
                var item = new Item(rs.getString("name"), rs.getString("description"));
                item.setId(rs.getString("id"));
                item.setDateOfCreation(rs.getString("date_of_creation"));
                items.add(item);
            }
            rs.close();
            st.close();
            for (var item : items) {
                var comments = new ArrayList<String>();
                st = conn.prepareStatement("SELECT * FROM comments c INNER JOIN items i ON i.id = c.item_id where i.id = ?");
                st.setInt(1, Integer.parseInt(item.getId()));
                rs = st.executeQuery();

                while (rs.next()) {
                    comments.add(rs.getString("comment"));
                }
                item.setComments(comments);
                rs.close();
                st.close();
            }
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    LOG.error(e.getMessage(), e);
                }
            }
        }
        return items;
    }

    @Override
    public ArrayList<Item> findByName(String key) {
        var items = new ArrayList<Item>();
        try {
            conn = DriverManager.getConnection(url, username, password);
            var st = conn.prepareStatement("SELECT * FROM items WHERE name = ?");
            st.setString(1, key);
            var rs = st.executeQuery();
            // Если надо добавить данные, то вместо executeQuery надо будет написать executeUpdate();
            while (rs.next()) {
                var item = new Item(rs.getString("name"), rs.getString("description"));
                item.setId(rs.getString("id"));
                item.setDateOfCreation(rs.getString("date_of_creation"));
                items.add(item);
            }
            rs.close();
            st.close();
            for (Item item : items) {
                var comments = new ArrayList<String>();
                st = conn.prepareStatement("SELECT * FROM comments c INNER JOIN items i ON i.id = c.item_id where i.id = ?");
                st.setInt(1, Integer.parseInt(item.getId()));
                rs = st.executeQuery();

                while (rs.next()) {
                    comments.add(rs.getString("comment"));
                }
                item.setComments(comments);
                rs.close();
                st.close();
            }
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    LOG.error(e.getMessage(), e);
                }
            }
        }
        return items;
    }

    @Override
    public Item findById(String id) {
        Item item = null;
        try {
            conn = DriverManager.getConnection(url, username, password);
            var st = conn.prepareStatement("SELECT * FROM items WHERE id = ?");
            st.setInt(1, Integer.parseInt(id));
            var rs = st.executeQuery();
            // Если надо добавить данные, то вместо executeQuery надо будет написать executeUpdate();
            while (rs.next()) {
                item = new Item(rs.getString("name"), rs.getString("description"));
                item.setId(rs.getString("id"));
                item.setDateOfCreation(rs.getString("date_of_creation"));
            }
            rs.close();
            st.close();
            var comments = new ArrayList<String>();
            st = conn.prepareStatement("SELECT * FROM comments c INNER JOIN items i ON i.id = c.item_id where i.id = ?");
            st.setInt(1, Integer.parseInt(id));
            rs = st.executeQuery();

            while (rs.next()) {
                comments.add(rs.getString("comment"));
            }
            if (item != null) {
                item.setComments(comments);
            }
            rs.close();
            st.close();

        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    LOG.error(e.getMessage(), e);
                }
            }
        }
        return item;
    }

    public int countOfItems() {
        int count = -1;
        try {
            conn = DriverManager.getConnection(url, username, password);
            var sql = "select count(*) from items";
            var st = conn.prepareStatement(sql);
            var rs = st.executeQuery();
            rs.next();
            count = rs.getInt(1);
            st.close();
            rs.close();
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    LOG.error(e.getMessage(), e);
                }
            }
        }
        return count;
    }
}
