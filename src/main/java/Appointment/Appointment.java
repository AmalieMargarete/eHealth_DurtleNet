package Appointment;

import UserManagement.Doctor;
import UserManagement.User;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Class to hold the AppointmentData.
 * It holds an int: id for the database and identification,
 * the Date:date and String:time of the appointment, an object user of the UserClass and an object doctor of the DoctorClass
 * and the String:docFirstName, String:docLastName, String:docType for use in a javaFX table that requires this values to be in the class
 * and not in an object. Also, it stores a note for the doctor to specify the appointment reason
 * @author Viktor Benini, StudentID: 1298976
 */
public class Appointment {
    private int id;
    private LocalDate date;
    private String time;
    private User user;
    private String docFirstName;
    private String docLastName;
    private String docType;
    private Doctor doctor;
    private String note;
    private int reminder;

    /**
     * The standard constructor used for past test reasons
     */
    public Appointment(){}

    /**
     * Constructor to create a sting by the given values
     * @param id
     * @param date
     * @param time
     * @param patient
     * @param doctor
     * @param note
     * @param reminder
     */
    public Appointment(int id, LocalDate date, String time, User patient, Doctor doctor, String note, int reminder){
        this.id = id;
        this.date = date;
        this.time = time;
        this.user = patient;
        this.doctor = doctor;
        this.note = note;
        this.reminder = reminder;

        // used cause of table in AppointmentViewController
        this.docFirstName = doctor.getName();
        this.docLastName = doctor.getLastName();
        this.docType = doctor.getDocType();
    }

    /**
     * Constructor to copy an appointment, by passing another appointment
     * @param appointment
     */
    public Appointment(Appointment appointment){
        this.date = appointment.getDate();
        this.user = appointment.getUser();
        this.doctor = appointment.getDoctor();
        this.note = appointment.getNote();

        // used cause of table in AppointmentViewController
        this.docFirstName = doctor.getName();
        this.docLastName = doctor.getLastName();
        this.docType = doctor.getDocType();
    }

    // Set-Method's

    /**
     * sets the date of the appointment
     * @param date
     */
    public void setDate(LocalDate date) {
        this.date = date;
    }

    /**
     * sets the user of the appointment by passing a user
     * @param patient
     */
    public void setPatient(User patient) {
        this.user = patient;
    }

    /**
     * sets the doctor of the appointment by passing a doctor
     * @param doctor
     */
    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    /**
     * sets the note of the appointment by passing a note(String)
     * @param note
     */
    public void setNote(String note) {
        this.note = note;
    }

    // Get-Method's

    /**
     * returns the id of the appointment
     * @return
     */
    public int getId(){
        return id;
    }

    /**
     * returns the date of the appointment
     * @return
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * returns the user of the appointment
     * @return
     */
    public User getUser() {
        return user;
    }

    /**
     * returns the doctor of the appointment
     * @return
     */
    public Doctor getDoctor() {
        return doctor;
    }

    /**
     * returns the note of the appointment
     * @return
     */
    public String getNote() {
        return note;
    }

    /**
     * returns the doctors firstname  of the appointment
     * @return
     */
    public String getDocFirstName(){
        return docFirstName;
    }

    /**
     * returns the doctors lastname  of the appointment
     * @return
     */
    public String getDocLastName(){
        return docLastName;
    }

    /**
     * returns the doctors specificationType as String of the appointment
     * @return
     */
    public String getDocType(){
        return docType;
    }

    /**
     * returns the doctors firstname  of the appointment
     * @return
     */
    public String getTime(){
        return time;
    }

}
