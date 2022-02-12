package Scheduler;

/**
 * Class that stores our reminder configuration in the program for easy use after
 * querying it out of the database
 */
public class Reminder {
    int appointmentId;
    boolean week;
    boolean threeDays;
    boolean oneDay;
    boolean oneHour;



    public Reminder(int appointmentId, boolean week, boolean threeDays, boolean oneDay, boolean oneHour){
        this.appointmentId = appointmentId;
        this.week = week;
        this.threeDays = threeDays;
        this.oneDay = oneDay;
        this.oneHour = oneHour;
    }

    // Set-Methods
    public void setAppointmentId(int appointmentId) {
        this.appointmentId = appointmentId;
    }

    public void setWeek(boolean week) {
        this.week = week;
    }

    public void setThreeDays(boolean threeDays) {
        this.threeDays = threeDays;
    }

    public void setOneDay(boolean oneDay) {
        this.oneDay = oneDay;
    }

    public void setOneHour(boolean oneHour) {
        this.oneHour = oneHour;
    }


    // Get-Methods
    public int getAppointmentId() {
        return appointmentId;
    }

    public boolean isWeek() {
        return week;
    }

    public boolean isThreeDays() {
        return threeDays;
    }

    public boolean isOneDay() {
        return oneDay;
    }

    public boolean isOneHour() {
        return oneHour;
    }
}
