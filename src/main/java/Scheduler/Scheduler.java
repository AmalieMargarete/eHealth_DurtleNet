package Scheduler;

import DB_Driver.AppointmentTable;
import org.quartz.JobDetail;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.*;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import org.apache.log4j.*;

import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

/**
 * Class used to create a job using Quartz to send an email at a specific time
 * @author Viktor Benini, StudentID 1298976
 */
public class Scheduler {

    /**
     * converts LocalDateTime to Date so we can use it in Quartz
     * @param localDateTime LocalDateTime
     * @return date
     */
    public static Date localDateTimeToDate(LocalDateTime localDateTime){
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * executes ReminderJob class every minute, used to send reminders to our patients
     */
    public static void runReminder() {
        SchedulerFactory schedulerFactory = new org.quartz.impl.StdSchedulerFactory();
        BasicConfigurator.configure();
        System.out.println("Reminder is running");

        try {
            org.quartz.Scheduler scheduler = schedulerFactory.getScheduler();
            scheduler.start();
            // define the job and tie it to our ReminderJob class
            JobDetail job = newJob(SchedulerJob.class)
                    .withIdentity("myJob", "group1")
                    .build();

            // Trigger the job to run now, and then every 60 seconds
            Trigger trigger = newTrigger()
                    .withIdentity("myTrigger", "group1")
                    .startNow()
                    .withSchedule(cronSchedule("10 0 0/1 1/1 * ? *"))
                    .build();
            // cron: at second 10, minute 0, every hour 0/1, start on first day of month everyday 1/1, every month *, every weekday *, every year *

            // TEll quartz to schedule the job using our trigger
            scheduler.scheduleJob(job, trigger);
        } catch (SchedulerException e) {
            System.out.println("something with the scheduler");
        }
    }

    public static void main(String[] args){
        runReminder();
        System.out.println("Should work when this is printed, maybe?!");
    }
}
