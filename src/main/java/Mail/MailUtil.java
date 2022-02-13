package Mail;

import Appointment.Appointment;
import UserManagement.User;
import UserManagement.UserHolder;
import com.itextpdf.text.Document;

//import javax.activation.DataHandler;
//import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.Date;
import java.util.Properties;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Class is used to send Mails by authentic the mail with the given password. Creating a message and sending it
 * by parsing the given information in an email form.
 * @author Viktor Benini, StudentID: 1298976
 * @author Amalie Wilke, 1304925
 */
public class MailUtil {
    /**
     * Method prepares sending by authentic the email and password, preparing the mail by using
     * an extra method prepareMessage and sends it to the defined email
     * @param recipient
     * @param df
     * @param dl
     * @param ds
     * @param dn
     * @param dz
     * @param dt
     * @param ad
     * @param at
     * @throws MessagingException
     */
    public static void sendMail(String recipient, String df, String dl, String ds, String dn, String dz, String dt, LocalDate ad, String at /*Appointment appointment*/) throws MessagingException {
        System.out.println("Preparing to send Mail");
        Properties properties = new Properties();

        properties.put("mail.smtp.auth", true);                 // defines if authentication is needed
        properties.put("mail.smtp.starttls.enable", true);      // tls encryption due to gmail
        properties.put("mail.smtp.host", "smtp.gmail.com");     // host from mail server
        properties.put("mail.smtp.port", "587");                // gmail port

        String myAccountEmail = "DurtleTeam@gmail.com";  //changed email here to run tests (Amalie)
        String password = "durtleteam2022!";

        Session session = Session.getInstance(properties, new Authenticator() {      // prepare authentication
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(myAccountEmail, password);
            }
        });

        Message message = prepareMessagePublic(session, myAccountEmail, recipient, df, dl, ds, dn, dz, dt, ad, at);
        Transport.send(message);
        System.out.println("Message sent successfully");
    }

    /**
     * Don't use this Method!
     * Predefined Method to test.
     * Method sends a mail with raw information to the user.
     * @param session
     * @param myAccountEmail
     * @param recipient
     * @param appointment
     * @return
     * @throws MessagingException
     */
    private static Message prepareMessage(Session session, String myAccountEmail, String recipient, Appointment appointment) throws MessagingException {
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(myAccountEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
            message.setSubject("Email Reminder");
            message.setText("Dr. " + appointment.getDoctor().getFirstname() + " " + appointment.getDoctor().getLastName() + "\n" +
                    appointment.getDoctor().getDocType() + "\n" +
                    appointment.getDoctor().getStreet() + " " + appointment.getDoctor().getHouseNumber() + "\n" +
                    appointment.getDoctor().getTown() + " " + appointment.getDoctor().getZIP() + "\n\n" +
                    "Hello, \n you have an appointment on the " + appointment.getDate() + " at: " + appointment.getTime() + "\n\n" +
                    "Due to you're note: " + appointment.getNote());
            return message;
        } catch (Exception e) {
            Logger.getLogger(MailUtil.class.getName()).log(Level.SEVERE, null, e);
        }
        return null;

    }

    /**
     * Method prepares a predefined Message (Email) as a reminder for the user that he made an appointment or
     * if he enabled the option he gets the body as reminder
     * @param session
     * @param myAccountEmail
     * @param recipient
     * @param docfirstname
     * @param doclastname
     * @param docstreet
     * @param docnum
     * @param doczip
     * @param doctown
     * @param appointmentdate
     * @param appointmenttime
     * @return
     * @throws MessagingException
     */
    public static Message prepareMessagePublic(Session session, String myAccountEmail, String recipient, String docfirstname, String doclastname, String docstreet, String docnum, String doczip, String doctown, LocalDate appointmentdate, String appointmenttime) throws MessagingException {
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(myAccountEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
            message.setSubject("Your eHealth appointment");
            message.setText("Hey there from Durtle and team! You have scheduled an appointment with Dr. " + docfirstname + " " + doclastname + "\n" +
                    "Your appointment is on the " + appointmentdate + " at: " + appointmenttime + "\n\n" +
                    "Address: \n" + docstreet + " " + docnum + "\n" + doczip + " " + doctown);
            return message;
        } catch (Exception e) {
            Logger.getLogger(MailUtil.class.getName()).log(Level.SEVERE, null, e);
        }

        return null;
    }




    //====================================================================================================
    // FUNCTIONS USED IN PROGRAM !

    public static void sendMailUpdateReminder(String recipient, Appointment appointment) throws MessagingException {
        System.out.println("Preparing to send Mail");
        Properties properties = new Properties();

        properties.put("mail.smtp.auth", true);                 // defines if authentication is needed
        properties.put("mail.smtp.starttls.enable", true);      // tls encryption due to gmail
        properties.put("mail.smtp.host", "smtp.gmail.com");     // host from mail server
        properties.put("mail.smtp.port", "587");                // gmail port

        String myAccountEmail = "DurtleTeam@gmail.com";  //changed email here to run tests (Amalie)
        String password = "durtleteam2022!";

        Session session = Session.getInstance(properties, new Authenticator() {      // prepare authentication
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(myAccountEmail, password);
            }
        });

        Message message = prepareMessageUpdateReminder(session, myAccountEmail, recipient, appointment);
        Transport.send(message);
        System.out.println("Message sent successfully");
    }

    public static Message prepareMessageUpdateReminder(Session session, String myAccountEmail, String recipient, Appointment appointment){
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(myAccountEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
            message.setSubject("Your eHealth appointment");
            message.setText("Hey there from Durtle and team! You have updated your  appointment with Dr. " + appointment.getDoctor().getFirstname() + " " + appointment.getDoctor().getLastName() + "\n" +
                    "Your appointment is now on the " + appointment.getDate() + " at: " + appointment.getTime() + "\n\n" +
                    "Address: \n" + appointment.getDoctor().getStreet() + " " + appointment.getDoctor().getHouseNumber() + "\n" + appointment.getDoctor().getZIP() + " " + appointment.getDoctor().getTown());
            return message;
        } catch (Exception e) {
            Logger.getLogger(MailUtil.class.getName()).log(Level.SEVERE, null, e);
        }

        return null;
    }

    /**
     * Method to send email to the doctor, to request the
     * @param recipient
     * @throws MessagingException
     */
    public static void sendMailReminder(String recipient, Appointment appointment) throws MessagingException {
        System.out.println("Preparing to send Mail");
        Properties properties = new Properties();

        properties.put("mail.smtp.auth", true);                 // defines if authentication is needed
        properties.put("mail.smtp.starttls.enable", true);      // tls encryption due to gmail
        properties.put("mail.smtp.host", "smtp.gmail.com");     // host from mail server
        properties.put("mail.smtp.port", "587");                // gmail port

        String myAccountEmail = "DurtleTeam@gmail.com";  //changed email here to run tests (Amalie)
        String password = "durtleteam2022!";

        Session session = Session.getInstance(properties, new Authenticator() {      // prepare authentication
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(myAccountEmail, password);
            }
        });

        Message message = prepareMessageReminder(session, myAccountEmail, recipient, appointment);
        Transport.send(message);
        System.out.println("Message sent successfully");
    }

    /**
     *
     * @param session
     * @param myAccountEmail
     * @param recipient
     * @param appointment
     * @return
     */
    public static Message prepareMessageReminder(Session session, String myAccountEmail, String recipient, Appointment appointment){
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(myAccountEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
            message.setSubject("Your eHealth appointment");
            message.setText("Hey there from Durtle and team! You have scheduled an appointment with Dr. " + appointment.getDoctor().getFirstname() + " " + appointment.getDoctor().getLastName() + "\n" +
                    "Your appointment is on the " + appointment.getDate() + " at: " + appointment.getTime() + "\n\n" +
                    "Address: \n" + appointment.getDoctor().getStreet() + " " + appointment.getDoctor().getHouseNumber() + "\n" + appointment.getDoctor().getZIP() + " " + appointment.getDoctor().getTown());
            return message;
        } catch (Exception e) {
            Logger.getLogger(MailUtil.class.getName()).log(Level.SEVERE, null, e);
        }

        return null;
    }



    /**
     * Method to create a predefined message (email), that is sent to the doctor, when a user created an appointment
     * @param session
     * @param myAccountEmail
     * @param recipient
     * @param appointment
     * @return
     * @throws MessagingException
     */
    // TODO: not finished need overwork
    // TODO: sent file is not a pdf!
    public static Message prepareMessageDoctorAppointment(Session session, String myAccountEmail, String recipient, Appointment appointment){
     /*   // Get User information
        UserHolder holder = UserHolder.getInstance();
        User user = holder.getUser();

        System.out.println("Prepare Main -> userName: " + user.getFirstname());

        // select health information
        File file = new File("HealthInfoPath.txt");
        File healthInfoPdf;
        Scanner scanner;
        String path = "C:\\";
        try{
            scanner = new Scanner(file);
            path = scanner.next();
            scanner.close();
        }catch (Exception e){
            System.out.println("DocMail getting HealthInfoPath file!");
        }
        healthInfoPdf = new File(path + "\\\\HealthInfo.pdf");


        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(myAccountEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
            message.setSubject("Your eHealth appointment");
            message.setText("Hey there from Durtle and team! Our user " + user.getFirstname() + " " + user.getLastName() + "wants to have an appointment on " + appointment.getDate() + " at " + appointment.getTime() + "\n" +
                    "Your appointment is on the ");
            message.setDataHandler(new DataHandler(new FileDataSource(healthInfoPdf)));
            message.setFileName("Health Information");
            return message;
        } catch (Exception e) {
            Logger.getLogger(MailUtil.class.getName()).log(Level.SEVERE, null, e);
        }
*/
        return null;
    }
}



