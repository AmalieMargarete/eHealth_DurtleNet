package Mail;
/*
@author Viktor Benini, StudentID: 1298976
@author Amalie Wilke, 1304925
 */
import Appointment.Appointment;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.time.LocalDate;
import java.util.Date;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MailUtil {
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
}



