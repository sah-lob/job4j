package ru.job4j.vacancy;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class SqlRuParser implements Job {

    private static final Logger LOG = LoggerFactory.getLogger(SqlRuParser.class);

    public static void main(String[] args) {

//        var name = args[0];
        var name = "app.properties";
        Config config = new Config(name);
        config.init();
//        System.out.println("asf");
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
        LOG.info("Start schedule");
        var dataMap = jobExecutionContext.getJobDetail().getJobDataMap();
        var driver = dataMap.getString("jdbc.driver");
        var url = dataMap.getString("jdbc.url");
        var name = dataMap.getString("jdbc.username");
        var pass = dataMap.getString("jdbc.password");
        var postgreas = new Postgreas(new Config("app.properties").create());
        var parsing = new Parsing(postgreas.getLastDate());
        var vacancies = parsing.startParsing();
        postgreas.add(vacancies);
        LOG.info("Finish schedule");
    }
}
