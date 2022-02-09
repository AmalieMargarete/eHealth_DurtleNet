package Mail;

import Appointment.Appointment;
import UserManagement.Doctor;
import UserManagement.User;

import javax.mail.MessagingException;
import java.time.LocalDate;
import java.util.Date;

public class AppointmentMail {
    public static void main(String[] args) throws MessagingException {
        User user  = new User();
        Doctor doctor = new Doctor();
        Appointment appointment = new Appointment(1, LocalDate.now(), "20:20", user, doctor, "Hallo" , 1);
        LocalDate date=LocalDate.now();
        MailUtil.sendMail("molly91@gmx.de", "Johan", "Faust", "Heilsbrunnen", "1", "123", "Fegefeuer", date, "9.00");  //changed email here while running tests (Amalie)
    }
}
