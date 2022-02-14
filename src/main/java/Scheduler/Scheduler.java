package Scheduler;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

/**
 * Class used to create a job using Quartz to send an email at a specific time by creating a trigger that
 * executes a job at a specific time.
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
     * executes ReminderJob class every minute, used to send reminders to our patients.
     * The trigger is specified by the API included TriggerBuilder and uses the cronScheduler
     * to trigger at a full minute every minute. The job is another class that defines the executed
     * job in a method.
     */
    public static void runReminder() {
        System.out.println("Reminder is running");

        try {
            // define the job and tie it to our ReminderJob class
            JobDetail job = JobBuilder.newJob(SchedulerJob.class).build();


            // Trigger the job to run now, and then every 60 seconds
            Trigger trigger = TriggerBuilder.newTrigger()
                    .withIdentity("myTrigger")
                    .withSchedule(CronScheduleBuilder.cronSchedule("0 0/1 * 1/1 * ? *")/*SimpleScheduleBuilder.simpleSchedule().withIntervalInMinutes(1)
                            .repeatForever()*/).build();
            // crone: starts at 12 and repeats every minute

            org.quartz.Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
            // TEll quartz to schedule the job using our trigger
            scheduler.start();
            scheduler.scheduleJob(job, trigger);
        } catch (SchedulerException e) {
            System.out.println("something with the scheduler");
        }
    }

    /**
     * method to start the reminder if called.
     * @param args
     */
    public static void main(String[] args){
        runReminder();
        System.out.println("Should work when this is printed, maybe?!");
    }
}
