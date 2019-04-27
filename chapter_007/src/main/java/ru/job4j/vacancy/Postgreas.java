package ru.job4j.vacancy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;


public class Postgreas {

    private static final Logger LOG = LoggerFactory.getLogger(Postgreas.class);
    private Connection conn;
    private String sql;

    public Postgreas(Connection conn) {
        this.conn = conn;
    }

    public void add(ArrayList<Vacancy> vacancies) {
        LOG.info("Добавляем вакансии в постгрес в количестве: " + vacancies.size());
        if (vacancies.size() > 0) {
            for (var v : vacancies) {
                System.out.println(v.getData());
            }
        }
        for (var vacancy : vacancies) {
            var name = vacancy.getName();
            var description = vacancy.getText();
            var url = vacancy.getLink();
            var data = vacancy.getData();
            var timestamp = new Timestamp(data.getTime());
            timestamp.setSeconds(0);
            timestamp.setNanos(0);
            sql = "INSERT into vacansy (name, description, url, data) values (?, ?, ?, ?)";
            try (var st = conn.prepareStatement(sql)) {
                st.setString(1, name);
                st.setString(2, description);
                st.setString(3, url);
                st.setTimestamp(4, timestamp);
                var rs = st.executeQuery();
                rs.next();
            } catch (Exception e) {
                LOG.error(e.getMessage(), e);
            }
        }
        LOG.info("Вакансии добавлены.");
    }

    public Date getLastDate() {
        Date date = null;
        if (countOfVacancy() < 1) {
            Calendar cal = Calendar.getInstance();
            cal.setTime(new Date());
            cal.add(Calendar.YEAR, -1);
            date = cal.getTime();
        } else {
            sql = "SELECT * FROM vacansy where data = (select MAX(data) from vacansy)";
            try (var st = conn.prepareStatement(sql)) {
                var rs = st.executeQuery();
                while (rs.next()) {
                    date = rs.getTimestamp("data");
                }
            } catch (Exception e) {
                LOG.error(e.getMessage(), e);
            }
        }
        LOG.info("Дата последней загрузки: " + date);
        return date;
    }

    private int countOfVacancy() {
        var count = -1;
        sql = "select count(*) from vacansy";
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
