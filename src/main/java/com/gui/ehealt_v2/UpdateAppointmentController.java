package com.gui.ehealt_v2;

import Appointment.Appointment;
import UserManagement.Doctor;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * Class that handles the GUI, to update the selected time of an appointment
 * @author Viktor Benini; StudentID: 1298976
 */
public class UpdateAppointmentController {

    String [] timeList = {"08:00", "08:10", "08:20", "08:30", "08:40", "08:50",
            "09:00", "09:10", "09:20", "09:30", "09:40", "09:50",
            "10:00", "10:10", "10:20", "10:30", "10:40", "10:50",
            "11:00", "11:10", "11:20", "11:30", "11:40", "11:50",
            "12:00", "12:10", "12:20", "12:30", "12:40", "12:50",
            "13:00", "13:10", "13:20", "13:30", "13:40", "13:50",
            "14:00", "14:10", "14:20", "14:30", "14:40", "14:50",
            "15:00", "15:10", "15:20", "15:30", "15:40", "15:50",
            "16:00", "16:10", "16:20", "16:30", "16:40", "16:50",
            "17:00", "17:10", "17:20", "17:30", "17:40", "17:50" };

    @FXML
    private Button okButton;
    @FXML
    private ComboBox<String> timeComboBox;
    @FXML
    private DatePicker datePicker;

    private Appointment appointment;

    /**
     * Update the appointment in the DB, by the given values LocalDate and Time as String
     * @throws SQLException
     */
    public void onOkButtonClick() throws SQLException {

        if(datePicker.getValue().isBefore(LocalDate.now())){
            showAlert(Alert.AlertType.ERROR, okButton.getScene().getWindow(), "Form Error!", "The picked date is in the past");
        }

        // shows error if date is already taken
        //-----------------------------------------------------------------------------------------------------------------------------------
        Window owner=okButton.getScene().getWindow();
        Connection connection = null;
        ResultSet resultSet = null;
        try{
            connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/ehealth_db", "ehealth", "hells"); //localhost:3306/
            System.out.println("Successful DB connection");

            PreparedStatement Insert = connection.prepareStatement("SELECT * FROM appointments WHERE doctorId = ?");
            Insert.setInt(1, appointment.getDoctor().getId());
            resultSet = Insert.executeQuery();
            System.out.println("Insert completed");
        }catch (SQLException e){
            e.printStackTrace();
        }
        // doctorId;  value already exists
        while (resultSet.next()){
            if(resultSet.getString("appointmentTime").equals(timeComboBox.getSelectionModel().getSelectedItem()) &&
                    resultSet.getDate("appointmentDate").toLocalDate().equals(datePicker.getValue())){
                showAlert(Alert.AlertType.ERROR, owner, "Error!", "The chosen date is already taken");
                return;
            }
        }
        connection.close();
        //------------------------------------------------------------------------------------------------------------------------

         try {
             connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/ehealth_db", "ehealth", "hells");
             System.out.println("Successful DB connection");
             //put method here to calculate time stamp date of appointment, appointment saved as timestamp and parsed to this, Amalie - a lot of different options here because I was trying different approaches
             LocalDateTime appointmentrealtime= LocalDateTime.of(LocalDate.parse((datePicker.getValue()).toString()), LocalTime.parse(timeComboBox.getSelectionModel().getSelectedItem()));
             Timestamp timestamp =Timestamp.valueOf(appointmentrealtime);
             System.out.println("Timestamp of LocalDateTime appointment is:"+timestamp);  //testing values against each other
             System.out.println("LocalDateTime appointment is:"+appointment);            //testing values against each other
             //the above is needed because I want to save a timestamp in the DB to work with it for reminders and time frames

             PreparedStatement preparedStatement = connection.prepareStatement("UPDATE appointments SET appointmentDate = ? , appointmentTime = ?, realTimeAppointment = ? WHERE id = ?");
             preparedStatement.setDate(1, Date.valueOf(datePicker.getValue())); // TODO: Test date need to be set by date picker
             preparedStatement.setString(2, timeComboBox.getSelectionModel().getSelectedItem());
             preparedStatement.setTimestamp(3, timestamp);
             preparedStatement.setInt(4, appointment.getId());



             preparedStatement.executeUpdate();
         }catch(SQLException e){
             e.printStackTrace();
         }

         connection.close();
         Stage stage = (Stage) okButton.getScene().getWindow();
         stage.close();

    }

    /**
     * By opening the window, set the comboBox and the appointment that should be edited
     * @param appointment
     */
    public void setAll(Appointment appointment){
        timeComboBox.setItems(FXCollections.observableArrayList(timeList));
        this.appointment = appointment;
    }

    /**
     * calss to throw alert when called
     * @param alertType
     * @param owner
     * @param s
     * @param alertmessage
     */
    public static void showAlert(Alert.AlertType alertType, Window owner, String s, String alertmessage) {

        Alert alert= new Alert(alertType);
        alert.setTitle(s);
        alert.setHeaderText(null);
        alert.setContentText(alertmessage);
        alert.initOwner(owner);
        alert.show();
    }

}
