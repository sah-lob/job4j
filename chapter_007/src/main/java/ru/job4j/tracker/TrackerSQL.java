package ru.job4j.tracker;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.sql.SQLStorage;
import java.sql.*;
import java.util.ArrayList;

public class TrackerSQL implements ITracker, AutoCloseable {

    private static final Logger LOG = LoggerFactory.getLogger(SQLStorage.class);
    private Connection conn;
    private String sql;

    public TrackerSQL(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void close() {
        try {
            conn.close();
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    @Override
    public int add(Item item) {
        var name = item.getName();
        var description = item.getDesc();
        var comments = item.getComments();
        int idOfItem = -1;
        sql = "INSERT into items (name, description) values (?,?) RETURNING ID";
        try (var st = conn.prepareStatement(sql)) {
            st.setString(1, name);
            st.setString(2, description);
            var rs = st.executeQuery();
            rs.next();
            idOfItem = rs.getInt("id");
            sql = "INSERT into comments (comment, item_id) values (?, ?)";
            try (var newSt = conn.prepareStatement(sql);) {
                for (var comment : comments) {
                    newSt.setString(1, comment);
                    newSt.setInt(2, idOfItem);
                    newSt.executeUpdate();
                }
            }
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return idOfItem;
    }

    @Override
    public void replace(String id, Item item) {
        var name = item.getName();
        var desc = item.getDesc();
        var comments = item.getComments();
        sql = "DELETE FROM comments WHERE item_id = ?;"
                + "UPDATE items SET name = ?, description = ?, date_of_creation = now() WHERE id = ?";
        try (var st = conn.prepareStatement(sql)) {
            st.setInt(1, Integer.parseInt(id));
            st.setString(2,  name);
            st.setString(3, desc);
            st.setInt(4, Integer.parseInt(id));
            st.executeQuery();
            sql = "INSERT into comments (comment, item_id) values (?, ?)";
            try (var newSt = conn.prepareStatement(sql)) {
                for (var comment : comments) {
                    newSt.setString(1, comment);
                    newSt.setInt(2, Integer.parseInt(id));
                    newSt.executeUpdate();
                }
            }
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
    }

    @Override
    public void delete(String id) {
        sql = "DELETE from comments where item_id = ?;"
                + "DELETE from items where id = ?; ";
        try (var st = conn.prepareStatement(sql)) {
            st.setInt(1, Integer.parseInt(id));
            st.setInt(2, Integer.parseInt(id));
            st.executeQuery();
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
    }

    @Override
    public ArrayList<Item> findAll() {
        var items = new ArrayList<Item>();
        sql = "SELECT * FROM items";
        try (var st = conn.prepareStatement(sql)) {
            var rs = st.executeQuery();
            while (rs.next()) {
                var item = new Item(rs.getString("name"), rs.getString("description"));
                item.setId(rs.getString("id"));
                item.setDateOfCreation(rs.getString("date_of_creation"));
                items.add(item);
            }
            sql = "SELECT * FROM comments c INNER JOIN items i ON i.id = c.item_id where i.id = ?";
            try (var newSt = conn.prepareStatement(sql)) {
                for (var item : items) {
                    var comments = new ArrayList<String>();
                    newSt.setInt(1, Integer.parseInt(item.getId()));
                    rs = newSt.executeQuery();
                    while (rs.next()) {
                        comments.add(rs.getString("comment"));
                    }
                    item.setComments(comments);
                }
            }
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return items;
    }

    @Override
    public ArrayList<Item> findByName(String key) {
        var items = new ArrayList<Item>();
        sql = "SELECT * FROM items WHERE name = ?";
        try (var st = conn.prepareStatement(sql)) {
            st.setString(1, key);
            var rs = st.executeQuery();
            while (rs.next()) {
                var item = new Item(rs.getString("name"), rs.getString("description"));
                item.setId(rs.getString("id"));
                item.setDateOfCreation(rs.getString("date_of_creation"));
                items.add(item);
            }
            sql = "SELECT * FROM comments c INNER JOIN items i ON i.id = c.item_id where i.id = ?";
            try (var newSt = conn.prepareStatement(sql)) {
                for (var item : items) {
                    var comments = new ArrayList<String>();
                    newSt.setInt(1, Integer.parseInt(item.getId()));
                    rs = newSt.executeQuery();
                    while (rs.next()) {
                        comments.add(rs.getString("comment"));
                    }
                    item.setComments(comments);
                }
            }
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return items;
    }

    @Override
    public Item findById(String id) {
        Item item = null;
        sql = "SELECT * FROM items WHERE id = ?";
        try (var st = conn.prepareStatement(sql)) {
            st.setInt(1, Integer.parseInt(id));
            var rs = st.executeQuery();
            while (rs.next()) {
                item = new Item(rs.getString("name"), rs.getString("description"));
                item.setId(rs.getString("id"));
                item.setDateOfCreation(rs.getString("date_of_creation"));
            }
            var comments = new ArrayList<String>();
            sql = "SELECT * FROM comments c INNER JOIN items i ON i.id = c.item_id where i.id = ?";
            try (var newSt = conn.prepareStatement(sql)) {
                newSt.setInt(1, Integer.parseInt(id));
                rs = newSt.executeQuery();
                while (rs.next()) {
                    comments.add(rs.getString("comment"));
                }
                if (item != null) {
                    item.setComments(comments);
                }
            }
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return item;
    }

    public int countOfItems() {
        var count = -1;
        sql = "select count(*) from items";
        try (var st = conn.prepareStatement(sql)) {
            var rs = st.executeQuery();
            rs.next();
            count = rs.getInt(1);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return count;
    }
}
