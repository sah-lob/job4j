package ru.job4j.sql;
import org.slf4j.*;

import java.sql.*;

public class SQLStorage {
    private static final Logger LOG = LoggerFactory.getLogger(SQLStorage.class);

    public static void main(String[] args) {
        var url = "jdbc:postgresql://localhost:5432/machine_storage";
        var username = "postgres";
        var password = "Qazqaz23";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, username, password);
            PreparedStatement st = conn.prepareStatement("SELECT * FROM car WHERE id = ?");
            st.setInt(1, 3);
            ResultSet rs = st.executeQuery();
            // Если надо добавить данные, то вместо executeQuery надо будет написать executeUpdate();
            while (rs.next()) {
                System.out.println(rs.getString("id"));
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

    }
}
