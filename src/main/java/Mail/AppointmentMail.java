package Mail;

import Appointment.Appointment;
import UserManagement.Doctor;
import UserManagement.User;
import UserManagement.UserHolder;

import javax.mail.MessagingException;
import java.sql.*;
import java.time.LocalDate;
import java.util.Date;

/**
 * Test Class to check the mail functions
 */
public class AppointmentMail {
    /**
     * main for test reasons
     * @param args
     * @throws MessagingException
     */
    public static void main(String[] args) throws MessagingException {
        AppointmentMail appointmentMail = new AppointmentMail();
        appointmentMail.test2();

    }

    /**
     * Test functions to check if it workes as intended
     * @author Amalie Wilke
     * @throws MessagingException
     */
    public void test1() throws MessagingException {
        User user  = new User();
        Doctor doctor = new Doctor();
        Appointment appointment = new Appointment(1, LocalDate.now(), "20:20", user, doctor, "Hallo" , 1);
        LocalDate date=LocalDate.now();
        MailUtil.sendMail("molly91@gmx.de", "Johan", "Faust", "Heilsbrunnen", "1", "123", "Fegefeuer", date, "9.00");  //changed email here while running tests (Amalie)

    }

    /**
     * Test to send mail to a "doctor" and adding a pdf file
     * @throws MessagingException
     */
    public void test2() throws MessagingException {
        // Predefine UserHolder to make accurate test
        Connection connection = null;

        // Fast entry without login
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ehealth_db", "ehealth", "hells");
            System.out.println("Successful DB connection");
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM users WHERE ID = ?");
            preparedStatement.setInt(1, 3);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                // Viktor: get user information and store it in user
                User user = new User(resultSet.getInt("id"), resultSet.getString("FirstName")
                        , resultSet.getString("LastName"), resultSet.getDate("BirthDate"),
                        resultSet.getString("Street"), resultSet.getString("HouseNumber"),
                        resultSet.getString("ZIP"), resultSet.getString("Town"), resultSet.getString("Email"),
                        resultSet.getString("InsuranceName"), resultSet.getString("InsuranceType"), resultSet.getFloat("Latitude"), resultSet.getFloat("Longitude"));
                // UserHolder stores user, so it can be easily requested at any point in the program
                UserHolder userHolder = UserHolder.getInstance();
                userHolder.setUser(user);
                System.out.println(user.getFirstname());
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        UserHolder holder = UserHolder.getInstance();
        User user = holder.getUser();
        Doctor doctor = new Doctor();
        Appointment appointment = new Appointment(1, LocalDate.now(), "20:20", user, doctor, "Hallo" , 1);

        // MailUtil.sendMail("v.benini99@gmail.com", appointment);

    }


}
