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


    /**
     * Constructor for the Reminder class, by the given values
     * @param appointmentId
     * @param week
     * @param threeDays
     * @param oneDay
     * @param oneHour
     */
    public Reminder(int appointmentId, boolean week, boolean threeDays, boolean oneDay, boolean oneHour){
        this.appointmentId = appointmentId;
        this.week = week;
        this.threeDays = threeDays;
        this.oneDay = oneDay;
        this.oneHour = oneHour;
    }

    // Set-Methods

    /**
     * set int appointment id
     * @param appointmentId
     */
    public void setAppointmentId(int appointmentId) {
        this.appointmentId = appointmentId;
    }

    /**
     * set boolean week
     * parameter to check if the user should be reminded.
     * @param week
     */
    public void setWeek(boolean week) {
        this.week = week;
    }

    /**
     * set boolean three days
     * parameter to check if the user should be reminded.
     * @param threeDays
     */
    public void setThreeDays(boolean threeDays) {
        this.threeDays = threeDays;
    }

    /**
     * set boolean on day
     * parameter to check if the user should be reminded.
     * @param oneDay
     */
    public void setOneDay(boolean oneDay) {
        this.oneDay = oneDay;
    }

    /**
     * set boolean on hour
     * parameter to check if the user should be reminded.
     * @param oneHour
     */
    public void setOneHour(boolean oneHour) {
        this.oneHour = oneHour;
    }


    // Get-Methods

    /**
     * get int appointment id
     * @return
     */
    public int getAppointmentId() {
        return appointmentId;
    }

    /**
     * get boolean week
     * parameter to check if the user should be reminded.
     * @return
     */
    public boolean isWeek() {
        return week;
    }

    /**
     * get boolean three days
     * parameter to check if the user should be reminded.
     * @return
     */
    public boolean isThreeDays() {
        return threeDays;
    }

    /**
     * get boolean on day
     * parameter to check if the user should be reminded.
     * @return
     */
    public boolean isOneDay() {
        return oneDay;
    }

    /**
     * get boolean one hour
     * parameter to check if the user should be reminded.
     * @return
     */
    public boolean isOneHour() {
        return oneHour;
    }
}
