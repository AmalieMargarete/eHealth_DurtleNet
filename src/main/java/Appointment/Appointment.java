package Appointment;

import UserManagement.Doctor;
import UserManagement.User;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Class to hold the AppointmentData
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

    public Appointment(){}

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

    public Appointment(Appointment appointment){
        this.date = appointment.getDate();
        this.user = appointment.getUser();
        this.doctor = appointment.getDoctor();
        this.note = appointment.getNote();
    }

    // Set-Method's
    public void setDate(LocalDate date) {
        this.date = date;
    }
    public void setPatient(User patient) {
        this.user = patient;
    }
    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }
    public void setNote(String note) {
        this.note = note;
    }

    // Get-Method's
    public int getId(){
        return id;
    }
    public LocalDate getDate() {
        return date;
    }
    public User getUser() {
        return user;
    }
    public Doctor getDoctor() {
        return doctor;
    }
    public String getNote() {
        return note;
    }
    public String getDocFirstName(){
        return docFirstName;
    }
    public String getDocLastName(){
        return docLastName;
    }
    public String getDocType(){
        return docType;
    }
    public String getTime(){
        return time;
    }

    // Functions
    /**
     * print the appointment as PDF or txt
     */
    public void printAppointment(){

    }

}
