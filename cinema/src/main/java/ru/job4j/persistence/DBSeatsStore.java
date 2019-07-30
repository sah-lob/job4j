package ru.job4j.persistence;

import org.apache.commons.dbcp2.BasicDataSource;

import java.util.ArrayList;
import java.util.List;

public class DBSeatsStore implements SeatsStore {


    private static final BasicDataSource SOURCE = new BasicDataSource();
    private static final DBSeatsStore DB_STORE = new DBSeatsStore();

    public static DBSeatsStore getInstance() {
        return DB_STORE;
    }

    public DBSeatsStore() {
        SOURCE.setDriverClassName("org.postgresql.Driver");
        SOURCE.setUrl("jdbc:postgresql://localhost:5432/sah_lob_cinema");
        SOURCE.setUsername("postgres");
        SOURCE.setPassword("Qazqaz23");
        SOURCE.setMinIdle(5);
        SOURCE.setMaxIdle(10);
        SOURCE.setMaxOpenPreparedStatements(100);
    }

    public static void main(String[] args) {
        DBSeatsStore dbSeatsStore = DBSeatsStore.getInstance();
        dbSeatsStore.addAll(10, 10, 300);
    }

        @Override
    public void addAll(int rows, int numbers, int price) {
        List<Seat> seats = new Hall(rows, numbers, price).createNewHall();
        for (var s: seats) {
            var sql = "INSERT into seats (login, row, number, price, busy ) values (?, ?, ?, ?, ?)";
            try (var connection = SOURCE.getConnection()) {
                var st = connection.prepareStatement(sql);
                st.setString(1, s.getLogin());
                st.setInt(2, s.getRow());
                st.setInt(3, s.getNumber());
                st.setInt(4, s.getPrice());
                st.setBoolean(5, s.isBusy());
                st.executeUpdate();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public List<Seat> findAll() {
        List<Seat> seats = new ArrayList<>();
        var sql = "SELECT * FROM seats";
        try (var connection = SOURCE.getConnection()) {
            var st = connection.prepareStatement(sql);
            var rs = st.executeQuery();
            while (rs.next()) {
                var seat = new Seat();
                seat.setLogin(rs.getString("login"));
                seat.setRow(rs.getInt("row"));
                seat.setNumber(rs.getInt("number"));
                seat.setPrice(rs.getInt("price"));
                seat.setBusy(rs.getBoolean("busy"));
                seats.add(seat);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return seats;
    }

    @Override
    public boolean update(String login, int row, int number) {
        var result = false;
        var sql = "UPDATE seats SET busy = ?, login = ? WHERE row  = ? and number = ?;";
        try (var connection = SOURCE.getConnection()) {
            var st = connection.prepareStatement(sql);
            st.setBoolean(1, true);
            st.setString(2, login);
            st.setInt(3, row);
            st.setInt(4, number);
            st.executeUpdate();
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
