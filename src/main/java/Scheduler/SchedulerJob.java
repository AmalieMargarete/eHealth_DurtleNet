package Scheduler;

import Appointment.Appointment;
import Mail.MailUtil;
import UserManagement.Doctor;
import UserManagement.User;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.collections.ObservableListBase;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import javax.mail.MessagingException;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

/**
 * Reminder Job implements the Job class of Quartz and executes the sending of an email,
 * by calling a method of the MailUtility class.
 * @author Viktor Benini, StudentID: 1298976
 */
public class SchedulerJob implements Job {

    /**
     * Method is called by the Scheduler.
     * This method queries all appointments and reminder out of the database and stores
     * them in two arrayLists. Then for each reminderList element the corresponding
     * appointment is searched. If found it checks if the appointment has a reminder
     * and if it has it sends an email at the correct time, by checking the current time
     * of the job call and the appointment time.
     * @param context
     * @throws org.quartz.JobExecutionException
     */
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        System.out.println("Reminder looks for appointments");
        LocalDateTime timeNow = LocalDateTime.now().withNano(0);
        ArrayList<Appointment> appointments = new ArrayList<Appointment>();
        ArrayList<Reminder> reminders = new ArrayList<Reminder>();

        System.out.println(timeNow);

        // reset arraylists
        appointments.clear();
        reminders.clear();

        // QUERY OUT OF APPOINTMENT AND REMINDER TABLE
        // insert the appointment data into an arraylist : appointments
        // insert the reminder data into an arraylist : reminders
        //------------------------------------------------------------------------------------------------

        // get appointment from DB to look up reminder
        Connection connection = null;
        ResultSet resultSet = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/ehealth_db", "ehealth", "hells"); //localhost:3306/
            System.out.println("Successful DB connection");

            // inner join with appointments, doctors and users to create all appointments in a list
            PreparedStatement Insert = connection.prepareStatement("SELECT * FROM (((appointments\n" +
                    "INNER JOIN doctors ON doctors.id = appointments.doctorId)\n" +
                    "INNER JOIN users ON users.id = appointments.userId)" +
                    "INNER JOIN reminder ON appointments.id = reminder.AppointmentID);");
            resultSet = Insert.executeQuery();
            System.out.println("Insert completed");

            while (resultSet.next()){
                appointments.add(
                        new Appointment(
                                resultSet.getInt("appointments.id"), resultSet.getDate("appointmentDate").toLocalDate(), resultSet.getString("appointmentTime"),
                        new User(resultSet.getInt("userId"), resultSet.getString("users.FirstName"), resultSet.getString("users.LastName"),
                                resultSet.getString("users.Street"), resultSet.getString("users.HouseNumber"), resultSet.getString("users.ZIP"), resultSet.getString("users.Town"),
                                resultSet.getString("users.Email"), resultSet.getDate("users.BirthDate"), resultSet.getString("users.CreationDate"),
                                resultSet.getString("users.InsuranceName"), resultSet.getString("users.InsuranceType"), " "),
                        new Doctor(resultSet.getInt("doctorId"), resultSet.getString("doctors.FirstName"), resultSet.getString("doctors.LastName"),
                                resultSet.getString("doctors.Street"),resultSet.getString("doctors.HouseNumber"), resultSet.getString("doctors.zip"), resultSet.getString("doctors.Town"),
                                resultSet.getString("doctors.Email"), resultSet.getString("doctors.Telephone"), resultSet.getString("doctors.Specialization"),
                                "00-24"),
                                resultSet.getString("note"),
                                resultSet.getInt("reminder"))
                );
                reminders.add(new Reminder(resultSet.getInt("reminder.AppointmentID"), resultSet.getBoolean("reminder.ReminderWeek"),
                        resultSet.getBoolean("reminder.ReminderThreeDays"),
                        resultSet.getBoolean("reminder.ReminderOneDay"), resultSet.getBoolean("reminder.ReminderOneHour")));
            }

            connection.close();
        }catch (SQLException e){
            e.printStackTrace();
        }


        // check if mail has to be sent
        for (Reminder reminder : reminders){
            for(Appointment appointment : appointments){

                // check if reminder is set to one hour
                if(reminder.isOneHour()){
                    if(appointment.getId() == reminder.getAppointmentId()){
                        // get the time of the appointment
                        LocalDateTime dateTime = LocalDateTime.of(appointment.getDate(), LocalTime.parse(appointment.getTime()));
                        // sends mail if the appointment is one hour ahead
                        if(dateTime.equals(timeNow.plusHours(1))){
                            System.out.println("Time is now one hour: " + appointment.getId());
                            try {
                                MailUtil.sendMailReminder(appointment.getUser().getEmail(), appointment);
                                System.out.println("Email was sent!");
                            } catch (MessagingException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }

                // check if reminder is set to one day
                if(reminder.isOneDay()){
                    if(appointment.getId() == reminder.getAppointmentId()){
                        // get the time of the appointment
                        LocalDateTime dateTime = LocalDateTime.of(appointment.getDate(), LocalTime.parse(appointment.getTime()));
                        // sends mail if the appointment is one day ahead
                        if(dateTime.equals(timeNow.plusDays(1))){
                            System.out.println("Time is now one day: " + appointment.getId());
                            try {
                                MailUtil.sendMailReminder(appointment.getUser().getEmail(), appointment);
                                System.out.println("Email was sent!");
                            } catch (MessagingException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }

                // check if reminder is set to three day
                if(reminder.isThreeDays()){
                    if(appointment.getId() == reminder.getAppointmentId()){
                        // get the time of the appointment
                        LocalDateTime dateTime = LocalDateTime.of(appointment.getDate(), LocalTime.parse(appointment.getTime()));
                        // sends mail if the appointment is 3 days ahead
                        if(dateTime.equals(timeNow.plusDays(3))){
                            System.out.println("Time is now 3 days: " + appointment.getId());
                            try {
                                MailUtil.sendMailReminder(appointment.getUser().getEmail(), appointment);
                                System.out.println("Email was sent!");
                            } catch (MessagingException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }

                // check if reminder is set to one day
                if(reminder.isWeek()){
                    if(appointment.getId() == reminder.getAppointmentId()){
                        // get the time of the appointment
                        LocalDateTime dateTime = LocalDateTime.of(appointment.getDate(), LocalTime.parse(appointment.getTime()));
                        // sends mail if the appointment is 7 days ahead
                        if(dateTime.equals(timeNow.plusDays(7))){
                            System.out.println("Time is now one week: " + appointment.getId());
                            try {
                                MailUtil.sendMailReminder(appointment.getUser().getEmail(), appointment);
                                System.out.println("Email was sent!");
                            } catch (MessagingException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
        }

        // Delete Appointments older than one day!
    }
}
