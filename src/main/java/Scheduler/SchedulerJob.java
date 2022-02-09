package Scheduler;

import Appointment.Appointment;
import UserManagement.Doctor;
import UserManagement.User;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Reminder Job implements the Job class of Quartz and executes the sending of an email
 * @author Viktor Benini, StudentID: 1298976
 */
public class SchedulerJob implements Job {

    public SchedulerJob(){}

    /**
     *
     * sends reminder to users that have an appointment
     * and deletes an old appointment
     * @param context
     * @throws org.quartz.JobExecutionException
     */
    public void execute(JobExecutionContext context) throws JobExecutionException {
        System.out.println("Reminder looks for appointments");
        LocalDate timeNow = LocalDate.now();
        ArrayList<Appointment> appointments = new ArrayList<Appointment>();

        // get appointment from DB to look up reminder
        Connection connection = null;
        ResultSet resultSet = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/ehealth_db", "ehealth", "hells"); //localhost:3306/
            System.out.println("Successful DB connection");

            // inner join with appointments, doctors and users to create all appointments in a list
            PreparedStatement Insert = connection.prepareStatement("SELECT * FROM ((appointments\n" +
                    "INNER JOIN doctors ON doctors.id = appointments.doctorId)\n" +
                    "INNER JOIN users ON users.id = appointments.userId);");
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
            }

        }catch (SQLException e){
            e.printStackTrace();
        }

        // send reminder mails to all users with appointments at certain time
        for(Appointment appointment : appointments){
            // MailUtil.sendMail(appointment.getUser().getEmail(), appointment);
            System.out.println("Appointment: " + appointment.getId() + " was send!");
            System.out.println(appointment.getUser().getEmail());
        }

        // Delete Appointments older than one day!

        System.out.println(timeNow);
    }
}
