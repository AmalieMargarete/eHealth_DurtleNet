package com.gui.ehealt_v2;

import Appointment.Appointment;
import UserManagement.Doctor;
import UserManagement.User;
import UserManagement.UserHolder;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;

/**
 * Class that handles the GUI for the AppointmentView.
 * The class uses the fxml elements, to get the information out of the GUI.
 * The UserHolder is used to get the current user to select the correct appointments of this user.
 * The sceneController gives the opportunity to switch to other scenes if needed by simply calling a method.
 * The appointmentList is an observable array list, to store all the appointments of the user.
 * The timeList is used to give the user the opportunity to select the appointment, by due
 * @author Viktor Benini; StudentID: 1298976
 * Appointments selected by time range
 * @author Amalie Wilke, StudentID: 1304925
 */
public class AppointmentViewController {
    UserHolder holder = UserHolder.getInstance();
    User user = holder.getUser();

    String db_password = "hells";

    // used to switch between scenes
    final private SceneController controller = new SceneController();
    // stores the appointments of the user
    ObservableList<Appointment> appointmentObservableList = FXCollections.observableArrayList();
    // used to set the selectable values of the comboBox
    String [] TimeList={"All", "1 Week", "3 days", "1 day", "1 hour", "10 minutes"};

    @FXML
    private Button printAppointmentButton;
    @FXML
    private ListView appointmentListView;
    @FXML
    private Button detailButton;
    @FXML
    private ComboBox <String> TimeComboBox;


    // Table
    @FXML
    private TableView<Appointment> appointment_table;
    @FXML
    private TableColumn<Appointment, String> firstname_col;
    @FXML
    private TableColumn<Appointment, String> lastname_col;
    @FXML
    private TableColumn<Appointment, String> doctyp_col;
    @FXML
    private TableColumn<Appointment, LocalDate> date_col;
    @FXML
    private TableColumn<Appointment, String> time_col;
    @FXML
    private TableColumn<Appointment, String> note_col;

    // to show appointment details
    @FXML
    private Label appointmentDetailsLabel;


    // TODO: if doctor has appointment at this time
    // TODO: add go back to menu and show confirmation message

    /**
     * Method sets the appointment view table in the GUI by pressing the refresh button, and can also be called to preload the table.
     * The comboBox including the times for the appointment is set in this method to ensure the box isn't empty and the user has options
     * to choose.
     * From the database the appointments of the user with the corresponding doctor are selected and inserted into an observable list. This
     * list will be added into the table shown in the GUI to display the user all of his appointments.
     * Method to refresh the Appointments, shown in a table, after changes
     * @throws SQLException
     */
    @FXML
    public void setAppointmentList() throws SQLException {
        Connection connection = null;
        TimeComboBox.setItems(FXCollections.observableArrayList(TimeList));
        appointmentObservableList.clear();  // cleared to ensure appointments doesn't show up multiple times
        try{
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ehealth_db", "ehealth", db_password);
            PreparedStatement preparedStatement=connection.prepareStatement("SELECT * FROM doctors " +      // gets all appointments of a user and the info dependent of the doc
                    "INNER JOIN appointments " +
                    "ON doctors.id = appointments.doctorId AND appointments.userId = ?;"); // need to be inner join
            preparedStatement.setInt(1, user.getUserId());
            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                appointmentObservableList.add(new Appointment(resultSet.getInt("appointments.id"),
                        resultSet.getDate("AppointmentDate").toLocalDate(), resultSet.getString("appointmentTime"),
                        user,
                        new Doctor(resultSet.getInt("doctorId"), resultSet.getString("FirstName"), resultSet.getString("LastName"),  // create new doctor to insert into List
                                resultSet.getString("Street"), resultSet.getString("HouseNumber"), resultSet.getString("ZIP"), resultSet.getString("Town"),
                                resultSet.getString("Email"), resultSet.getString("Telephone"), resultSet.getString("Specialization"), "00:00"),
                        resultSet.getString("note"), resultSet.getInt("reminder")));
                System.out.println("ID: " + resultSet.getInt("appointments.id"));
            }
        }catch(SQLException e){
            System.out.println("Error in query form db and insert into appointment list");
        }
        firstname_col.setCellValueFactory(new PropertyValueFactory<>("docFirstName"));
        lastname_col.setCellValueFactory(new PropertyValueFactory<>("docLastName"));
        doctyp_col.setCellValueFactory(new PropertyValueFactory<>("docType"));
        date_col.setCellValueFactory(new PropertyValueFactory<>("date"));
        time_col.setCellValueFactory(new PropertyValueFactory<>("time"));
        note_col.setCellValueFactory(new PropertyValueFactory<>("note"));
        appointment_table.setItems(appointmentObservableList);

        for(Appointment appointment : appointmentObservableList){
            System.out.println("\nAppointmentID: " + appointment.getId());
        }

        connection.close();
    }


    //Method to get appointments in a specific user specfified time range using SQL  join and time stamp calculations, Amalie
    /**
     * Method is used to select appointments specified by the time. The user selects via the TimeComboBox the appointments in a certain range of time.
     * The method checks for the selected option all, 1 week, 3 days, 1 day and 1 hour. By parsing an int value to differ between the remaining time, it selects
     * the correct appointments, that are in the range for example in 1 week (time_dif <= 7). Afterwards the appointments are inserted into the fxml table.
     * @throws SQLException
     */
    public void getAppointmentsInTimeRange() throws SQLException{

        appointmentObservableList.clear();
        String t=TimeComboBox.getSelectionModel().getSelectedItem();
        int ti;
        Connection connection = null;
        try{
            connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/ehealth_db", "ehealth", "hells");
            System.out.println("Successful DB connection for appointments in time range");

            if(t=="All"){
                setAppointmentList();
            }
            if(t=="1 Week"){
                ti=7;
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM doctors JOIN appointments ON doctors.id=appointments.doctorId AND appointments.userId=? AND (datediff(appointments.realTimeAppointment,CURRENT_TIMESTAMP)<=? AND appointments.realTimeAppointment>=CURRENT_TIMESTAMP);");
            preparedStatement.setInt(1, user.getUserId());
            preparedStatement.setInt(2, ti);
            ResultSet resultSet=preparedStatement.executeQuery();
                System.out.println("Time range selected is: "+ti);
                while(resultSet.next()){
                    appointmentObservableList.add(new Appointment(resultSet.getInt("appointments.id"),
                            resultSet.getDate("AppointmentDate").toLocalDate(), resultSet.getString("appointmentTime"),    // TODO: need to get LocalDateTime but only Date is provided by DB
                            user,
                            new Doctor(resultSet.getInt("doctorId"), resultSet.getString("FirstName"), resultSet.getString("LastName"),  // create new doctor to insert into List
                                    resultSet.getString("Street"), resultSet.getString("HouseNumber"), resultSet.getString("ZIP"), resultSet.getString("Town"),
                                    resultSet.getString("Email"), resultSet.getString("Telephone"), resultSet.getString("Specialization"), "00:00"),
                            resultSet.getString("note"), resultSet.getInt("reminder")));
                    System.out.println("ID: " + resultSet.getInt("appointments.id"));
                }

            firstname_col.setCellValueFactory(new PropertyValueFactory<>("docFirstName"));
            lastname_col.setCellValueFactory(new PropertyValueFactory<>("docLastName"));
            doctyp_col.setCellValueFactory(new PropertyValueFactory<>("docType"));
            date_col.setCellValueFactory(new PropertyValueFactory<>("date"));
            time_col.setCellValueFactory(new PropertyValueFactory<>("time"));
            note_col.setCellValueFactory(new PropertyValueFactory<>("note"));
            appointment_table.setItems(appointmentObservableList);

            for(Appointment appointment : appointmentObservableList){
                System.out.println("\nAppointmentID: " + appointment.getId());
            }
            }
            if(t=="3 days"){
                ti=3;
                PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM doctors JOIN appointments ON doctors.id=appointments.doctorId AND appointments.userId=? AND (datediff(appointments.realTimeAppointment,CURRENT_TIMESTAMP)<=?);");
                preparedStatement.setInt(1, user.getUserId());
                preparedStatement.setInt(2, ti);
                ResultSet resultSet=preparedStatement.executeQuery();
                System.out.println("Time range selected is:"+ti);
                while(resultSet.next()){
                    appointmentObservableList.add(new Appointment(resultSet.getInt("appointments.id"),
                            resultSet.getDate("AppointmentDate").toLocalDate(), resultSet.getString("appointmentTime"),    // TODO: need to get LocalDateTime but only Date is provided by DB
                            user,
                            new Doctor(resultSet.getInt("doctorId"), resultSet.getString("FirstName"), resultSet.getString("LastName"),  // create new doctor to insert into List
                                    resultSet.getString("Street"), resultSet.getString("HouseNumber"), resultSet.getString("ZIP"), resultSet.getString("Town"),
                                    resultSet.getString("Email"), resultSet.getString("Telephone"), resultSet.getString("Specialization"), "00:00"),
                            resultSet.getString("note"), resultSet.getInt("reminder")));
                    System.out.println("ID: " + resultSet.getInt("appointments.id"));
                }

                firstname_col.setCellValueFactory(new PropertyValueFactory<>("docFirstName"));
                lastname_col.setCellValueFactory(new PropertyValueFactory<>("docLastName"));
                doctyp_col.setCellValueFactory(new PropertyValueFactory<>("docType"));
                date_col.setCellValueFactory(new PropertyValueFactory<>("date"));
                time_col.setCellValueFactory(new PropertyValueFactory<>("time"));
                note_col.setCellValueFactory(new PropertyValueFactory<>("note"));
                appointment_table.setItems(appointmentObservableList);

                for(Appointment appointment : appointmentObservableList){
                    System.out.println("\nAppointmentID: " + appointment.getId());
                }
            }
            if(t=="1 day"){
                ti=1;
                PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM doctors JOIN appointments ON doctors.id=appointments.doctorId AND appointments.userId=? AND (datediff(appointments.realTimeAppointment,CURRENT_TIMESTAMP)<=?);");
                preparedStatement.setInt(1, user.getUserId());
                preparedStatement.setInt(2, ti);
                ResultSet resultSet=preparedStatement.executeQuery();
                System.out.println("Time range selected is:"+ti);
                while(resultSet.next()){
                    appointmentObservableList.add(new Appointment(resultSet.getInt("appointments.id"),
                            resultSet.getDate("AppointmentDate").toLocalDate(), resultSet.getString("appointmentTime"),    // TODO: need to get LocalDateTime but only Date is provided by DB
                            user,
                            new Doctor(resultSet.getInt("doctorId"), resultSet.getString("FirstName"), resultSet.getString("LastName"),  // create new doctor to insert into List
                                    resultSet.getString("Street"), resultSet.getString("HouseNumber"), resultSet.getString("ZIP"), resultSet.getString("Town"),
                                    resultSet.getString("Email"), resultSet.getString("Telephone"), resultSet.getString("Specialization"), "00:00"),
                            resultSet.getString("note"), resultSet.getInt("reminder")));
                    System.out.println("ID: " + resultSet.getInt("appointments.id"));
                }

                firstname_col.setCellValueFactory(new PropertyValueFactory<>("docFirstName"));
                lastname_col.setCellValueFactory(new PropertyValueFactory<>("docLastName"));
                doctyp_col.setCellValueFactory(new PropertyValueFactory<>("docType"));
                date_col.setCellValueFactory(new PropertyValueFactory<>("date"));
                time_col.setCellValueFactory(new PropertyValueFactory<>("time"));
                note_col.setCellValueFactory(new PropertyValueFactory<>("note"));
                appointment_table.setItems(appointmentObservableList);

                for(Appointment appointment : appointmentObservableList){
                    System.out.println("\nAppointmentID: " + appointment.getId());
                }
            }

            if(t=="1 hour"){
                ti=1;
                PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM doctors JOIN appointments ON doctors.id=appointments.doctorId AND appointments.userId=? AND (timediff(appointments.realTimeAppointment,CURRENT_TIMESTAMP)<=?);");
                preparedStatement.setInt(1, user.getUserId());
                preparedStatement.setInt(2, ti);
                ResultSet resultSet=preparedStatement.executeQuery();
                System.out.println("Time range selected is:"+ti);
                while(resultSet.next()){
                    appointmentObservableList.add(new Appointment(resultSet.getInt("appointments.id"),
                            resultSet.getDate("AppointmentDate").toLocalDate(), resultSet.getString("appointmentTime"),    // TODO: need to get LocalDateTime but only Date is provided by DB
                            user,
                            new Doctor(resultSet.getInt("doctorId"), resultSet.getString("FirstName"), resultSet.getString("LastName"),  // create new doctor to insert into List
                                    resultSet.getString("Street"), resultSet.getString("HouseNumber"), resultSet.getString("ZIP"), resultSet.getString("Town"),
                                    resultSet.getString("Email"), resultSet.getString("Telephone"), resultSet.getString("Specialization"), "00:00"),
                            resultSet.getString("note"), resultSet.getInt("reminder")));
                    System.out.println("ID: " + resultSet.getInt("appointments.id"));
                }

                firstname_col.setCellValueFactory(new PropertyValueFactory<>("docFirstName"));
                lastname_col.setCellValueFactory(new PropertyValueFactory<>("docLastName"));
                doctyp_col.setCellValueFactory(new PropertyValueFactory<>("docType"));
                date_col.setCellValueFactory(new PropertyValueFactory<>("date"));
                time_col.setCellValueFactory(new PropertyValueFactory<>("time"));
                note_col.setCellValueFactory(new PropertyValueFactory<>("note"));
                appointment_table.setItems(appointmentObservableList);

                for(Appointment appointment : appointmentObservableList){
                    System.out.println("\nAppointmentID: " + appointment.getId());
                }
            }
            connection.close();

        }catch(SQLException e){
            System.out.println("Error while finding appointments in time range selected");
        }

    }
    // TODO: need reminder implementation for the doctor

    /**
     * Method is called when pressing the cancel button in the GUI.
     * The method deletes the appointment, by the selected appointment in the fxml table and uses the id, to parse it into the database delete command
     * @throws SQLException
     */
    @FXML
    public void onCancelAppointmentButtonClick() throws SQLException {
        Connection connection = null;

        try{
            connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/ehealth_db", "ehealth", "hells");
            System.out.println("Successful DB connection");
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM appointments WHERE id = ?");
            System.out.println("Current selected ID: " + appointment_table.getSelectionModel().getSelectedItem().getId());
            preparedStatement.setInt(1, appointment_table.getSelectionModel().getSelectedItem().getId());
            preparedStatement.executeUpdate();
        }catch(SQLException e){
            System.out.println("Error in delete Appointment");
        }
        setAppointmentList();
    }

    /**
     * Method to view appointment details in new Window
     * The method gets the selected appointment out of the fxml table and stores it as an object to provide it to the scene switch function of
     * the SceneController object
     * @throws IOException
     */
    @FXML
    public void onDetailsButtonClick() throws IOException {
        System.out.println(appointment_table.getSelectionModel().getSelectedItem());
        Appointment appointment = appointment_table.getSelectionModel().getSelectedItem();
        controller.switchToAppointmentDetailsView(appointment);
  }

    /**
     * Method is called by Change Time button click
     * Method is used to update the date and time of the appointment by opening new window via the SceneController. The appointment to be
     * updated is provided to the next window.
     * @throws IOException
     * @throws SQLException
     */
    @FXML
    public void onUpdateAppointmentButtonClick() throws IOException, SQLException {
        Appointment appointment = appointment_table.getSelectionModel().getSelectedItem();
        System.out.println("Update -> appointment id: " + appointment.getId());
        if(appointment == null){
            return;
        }
        controller.switchToUpdateAppointmentView(appointment);
        // updates Table
        setAppointmentList();   // doesn't work for some reason
    }

    /**
     * Method is called by back button on click
     * switches to main page, by calling method of SceneController
     * @param event
     * @throws IOException
     */
    @FXML
    public void switchToMainPage(ActionEvent event) throws IOException {
            controller.switchToMainPage(event);
    }

}

