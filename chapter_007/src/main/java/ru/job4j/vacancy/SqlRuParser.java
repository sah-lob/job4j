package ru.job4j.vacancy;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class SqlRuParser implements Job {

    private static final Logger LOG = LoggerFactory.getLogger(SqlRuParser.class);

    public static void main(String[] args) {

        var name = args[0];
        Config config = new Config(name);
        config.init();
            JobDetail job = JobBuilder.newJob(SqlRuParser.class)
                    .withIdentity("myJob")
                    .usingJobData("jdbc.driver", config.get("jdbc.driver"))
                    .usingJobData("jdbc.url", config.get("jdbc.url"))
                    .usingJobData("jdbc.username", config.get("jdbc.username"))
                    .usingJobData("jdbc.password", config.get("jdbc.password"))
                    .build();
            Trigger trigger = TriggerBuilder.newTrigger().withIdentity("CronTrigger")
                    .withSchedule(CronScheduleBuilder.cronSchedule(config.get("cron.time"))).build();
            try {
                Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
                scheduler.start();
                scheduler.scheduleJob(job, trigger);
            } catch (SchedulerException e) {
                LOG.error(e.getMessage(), e);
                e.printStackTrace();
            }
    }





    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println("Start schedule");
        JobDataMap dataMap = jobExecutionContext.getJobDetail().getJobDataMap();
        String driver = dataMap.getString("jdbc.driver");
        String url = dataMap.getString("jdbc.url");
        String name = dataMap.getString("jdbc.username");
        String pass = dataMap.getString("jdbc.password");
        Postgreas postgreas = new Postgreas(new Config("app.properties").create());
        Parsing parsing = new Parsing();
        var vacancies = parsing.startParsing(postgreas.getLastDate());
        postgreas.add(vacancies);
//            try (Connection connection = DriverManager.getConnection(url, name.isEmpty() ? null : name, pass.isEmpty() ? null : pass)) {
//                WebParser parser = new WebParser(new VacancyDAO(connection));
//                parser.addVacanciesFromWebToDB();
//                parser.resultToLog();
//            } catch (SQLException e) {
//                LOG.error(e.getMessage(), e);
//                e.printStackTrace();
//            }
        System.out.println("Finish schedule");
    }
}
