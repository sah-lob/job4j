package ru.job4j.sqlite;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StoreSQL implements AutoCloseable {

    private static final Logger LOG = LoggerFactory.getLogger(StoreSQL.class);
    private final Config config;
    private Connection connection;

    public StoreSQL(Config config) {
        this.config = config;
    }

    public static void main(String[] args) {
        var config = new Config();
        config.init();
        var storeSQL = new StoreSQL(config);
        storeSQL.init();
    }

    public void init() {
        try {
            connection = DriverManager.getConnection(config.get("url"));
            if (connection != null) {
                connection.getMetaData();
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        checkTables();
    }

    private void checkTables() {
        try {
            var meta = this.connection.getMetaData();
            var tables = meta.getTables(null, null, "entry", null);
            if (!tables.next()) {
                try (var statement = this.connection.prepareStatement(SQLEntry.CREATE.query)) {
                    statement.executeUpdate();
                }
            }
            tables.close();
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    private void deleteAllData() {
        try (var statement = this.connection.prepareStatement(SQLEntry.CLEAN.query)) {
            statement.executeUpdate();
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    public void generate(int size) {
        deleteAllData();
        try (var statement = this.connection.prepareStatement(SQLEntry.INSERT.query)) {
            this.connection.setAutoCommit(false);
            for (int i = 1; i <= size; i++) {
                statement.setInt(1, i);
                statement.executeUpdate();
            }
            this.connection.commit();
        } catch (SQLException e) {
            try {
                this.connection.rollback();
            } catch (SQLException e1) {
                LOG.error(e.getMessage(), e);
            }
            LOG.error(e.getMessage(), e);
        } finally {
            try {
                this.connection.setAutoCommit(true);
            } catch (SQLException e) {
                LOG.error(e.getMessage(), e);
            }
        }
    }





    public List<Entry> load() {
        var result = new ArrayList<Entry>();
        try (var statement = this.connection.prepareStatement(SQLEntry.SELECT.query)) {
            this.connection.setAutoCommit(false);
            var rs = statement.executeQuery();
            while (rs.next()) {
                result.add(new Entry(rs.getInt("field")));
            }
            this.connection.commit();
        } catch (SQLException e) {
            try {
                this.connection.rollback();
            } catch (SQLException e1) {
                LOG.error(e.getMessage(), e);
            }
            LOG.error(e.getMessage(), e);
        } finally {
            try {
                this.connection.setAutoCommit(true);
            } catch (SQLException e) {
                LOG.error(e.getMessage(), e);
            }
        }
        return result;
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }


    private enum SQLEntry {
        CREATE("CREATE TABLE entries (field INTEGER PRIMARY KEY)"),
        CLEAN("DELETE FROM entries"),
        INSERT("INSERT INTO entries (field) VALUES (?)"),
        SELECT("SELECT field FROM entries");

        private String query;

        SQLEntry(String query) {
            this.query = query;
        }
    }

}