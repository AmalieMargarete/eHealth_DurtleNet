package com.gui.ehealt_v2;

import Mail.MailUtil;
import UserManagement.Doctor;
import UserManagement.DoctorHolder;
import UserManagement.User;
import UserManagement.UserHolder;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Window;

import javax.mail.MessagingException;
import java.io.IOException;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * Class to handle GUI
 * @author Viktor Benini; StudentID: 1298976
 * Doctor radius search and email sent as a reminder after appointments
 * @author Amalie Wilke, 1304925
 */
public class MakeAppointmentController {

    // to excess database easy by password
    String db_password = "hells";

    // Controller to switch between Scenes
    SceneController controller = new SceneController();

    // stored selected values
    int doctorId;
    //Email reminder will always be sent to either me (Amalie) or Viktor so we can check our methods are working and so we do not have to create several user email addresses
    String testemailuser="molly91@gmx.de";

    UserHolder holder = UserHolder.getInstance();
    User user = holder.getUser();

    DoctorHolder holder1= DoctorHolder.getInstance();
    Doctor doctor=holder1.getDoctor();

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

    Integer [] radiusList={1, 3, 5, 7, 10, 15, 20, 25, 30};

    @FXML
    private ListView doctorList;
    @FXML
    private ListView specializationList;
    @FXML
    private TextField doctorSearch;
    @FXML
    private TextField specializationField;
    @FXML
    private DatePicker datePicker;
    @FXML
    private Button createAppointmentButton;
    @FXML
    private TextArea noteField;
    @FXML
    private ComboBox<String> timeComboBox;
    @FXML
    private ComboBox<Integer> radiusComboBox;


    /**
     * By tipping in the Searchbar doctors get selected by First
     * or Lastname out of database
     * @throws SQLException
     */
    @FXML
    protected void DoctorSearch() throws SQLException {
        Connection connection = null;

        doctorList.getItems().clear();
        specializationList.getItems().clear();
        try {
             connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ehealth_db", "ehealth", db_password);
            System.out.println("Successful DB connection");
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM doctors WHERE FirstName LIKE ? OR LastName LIKE ? ");
            preparedStatement.setString(1, "%" + doctorSearch.getText() + "%");
            preparedStatement.setString(2, "%"+doctorSearch.getText() + "%");
            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                doctorList.getItems().add("Dr. " + /* maybe better without */ resultSet.getString("FirstName")+ " " +resultSet.getString("LastName"));
                specializationList.getItems().add(resultSet.getString("Specialization"));
                System.out.println("FirstName: " + resultSet.getString("FirstName") + "\tLastName: "
                        + resultSet.getString("LastName"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        connection.close();
    }

    /**
     * By tipping in the Searchbar specification the database
     * @throws SQLException
     */
    @FXML
    protected void specificationSearch() throws SQLException {
        Connection connection = null;

        doctorList.getItems().clear();
        specializationList.getItems().clear();
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ehealth_db", "ehealth", db_password);
            System.out.println("Successful DB connection");
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM doctors WHERE Specialization LIKE ?");
            preparedStatement.setString(1, "%" + specializationField.getText() + "%");
            ResultSet resultSet = preparedStatement.executeQuery();
            System.out.println(radiusComboBox.getSelectionModel().getSelectedItem());
            while(resultSet.next()){
                doctorList.getItems().add("Dr. " + resultSet.getString("FirstName")+ " " +resultSet.getString("LastName"));
                specializationList.getItems().add(resultSet.getString("Specialization"));
                System.out.println(resultSet.getString("Specialization"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        connection.close();
    }

    /**
     *
     * @throws SQLException
     */
    @FXML
    public void onSpecificationMouseClick() throws SQLException {
        String specification = specializationList.getSelectionModel().getSelectedItem().toString();
        specializationField.setText(specification);

        // clear list to avoid adding the same element multiple times
        specializationList.getItems().clear();
        doctorList.getItems().clear();

        Connection connection = null;
        try{
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ehealth_db", "ehealth", db_password);
            System.out.println("Successful DB connection");
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM doctors WHERE Specialization LIKE ?");
            preparedStatement.setString(1, specification);
            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                specializationField.setText(resultSet.getString("Specialization"));
                specializationList.getItems().add(resultSet.getString("Specialization"));
                doctorList.getItems().add("Dr. " + resultSet.getString("FirstName") + " " + resultSet.getString("LastName") );
                doctorId = resultSet.getInt("id");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        connection.close();

    }

    /**
     * Class that puts selected doctor in textBox for later uses
     * and enters the depending specialisation
     */
    @FXML
    protected void doctorListOnMouseClick() throws SQLException {
        String doctor = doctorList.getSelectionModel().getSelectedItem().toString();
        doctorSearch.setText(doctor);
        String[] stringSplit = doctor.split("\\s+");
        // test of split
        System.out.println(stringSplit[1] + " " + stringSplit[2]);

        Connection connection = null;
        try{
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ehealth_db", "ehealth", db_password);
            System.out.println("Successful DB connection");
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM doctors WHERE FirstName LIKE ? AND LastName LIKE ? ");
            preparedStatement.setString(1, stringSplit[1]);
            preparedStatement.setString(2, stringSplit[2]);
            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                specializationField.setText(resultSet.getString("Specialization"));
                doctorId = resultSet.getInt("id");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        connection.close();
        System.out.println("doctorID: " + doctorId);
    }


    /**
     * Method to create Appointment and inserts it into DB
     * @throws SQLException
     */
    @FXML
    protected void createAppointmentOnClick(ActionEvent event) throws SQLException, IOException {
        Window owner=createAppointmentButton.getScene().getWindow();
        // check if fields are empty
        if(doctorSearch.equals("") || specializationField.equals("") || datePicker.getValue() == null || timeComboBox.getSelectionModel() == null){
            showAlert(Alert.AlertType.ERROR, owner, "Form Error!", "Not all fields are filled!");
            return;
        }
        // check if date is in the future
        if(datePicker.getValue().isBefore(LocalDate.now())){
            showAlert(Alert.AlertType.ERROR, owner, "Form Error!", "The selected date is in the past!");
            return;
        }

        // USerHolder Test
        // userdata can be easily requested by the patientHolder
        UserHolder userHolder = UserHolder.getInstance();
        User user = userHolder.getUser();
        System.out.println(
                "\n\nMake Appointment Test Values\n--------------------------------------\n" +
                "User ID:\t" + user.getUserId() +
                "\nDoctor ID:\t" + doctorId +
                "\nDate:\t" + Date.valueOf(datePicker.getValue()) +
                "\nNote:\t" + noteField.getText()+
                "\n--------------------------------------");

        // shows error if date is already taken     || if time exclude taken times from selection, not much work
        //-----------------------------------------------------------------------------------------------------------------------------------
        Connection connection = null;
        ResultSet resultSet = null;
        try{
            connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/ehealth_db", "ehealth", db_password); //localhost:3306/
            System.out.println("Successful DB connection");

            PreparedStatement Insert = connection.prepareStatement("SELECT * FROM appointments WHERE id = ?");
            Insert.setInt(1, doctorId);
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
            connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/ehealth_db", "ehealth", db_password); //localhost:3306/
            System.out.println("Successful DB connection");

            //put method here to calculate time stamp date of appointment, appointment saved as timestamp and parsed to this, Amalie - a lot of different options here because I was trying different approaches
            LocalDateTime appointment= LocalDateTime.of(LocalDate.parse((datePicker.getValue()).toString()), LocalTime.parse(timeComboBox.getSelectionModel().getSelectedItem()));
            Timestamp timestamp =Timestamp.valueOf(appointment);
            System.out.println("Timestamp of LocalDateTime appointment is:"+timestamp);  //testing values against each other
            System.out.println("LocalDateTime appointment is:"+appointment);            //testing values against each other
            //the above is needed because I want to save a timestamp in the DB to work with it for reminders and time frames

            System.out.println("Appointment as a time stamp is" +appointment);
            PreparedStatement Insert = connection.prepareStatement("INSERT INTO appointments (doctorId, userId, appointmentDate, appointmentTime,  note, realTimeAppointment) VALUES (?, ?, ?, ?, ?, ?)");
            Insert.setInt(1, doctorId);
            Insert.setInt(2, user.getUserId());
            Insert.setDate(3, Date.valueOf(datePicker.getValue()));
            Insert.setString(4, timeComboBox.getSelectionModel().getSelectedItem());
            Insert.setString(5, noteField.getText());
            Insert.setTimestamp(6, timestamp);
            Insert.executeUpdate();

            System.out.println("Insert completed");

            //Amalie: email reminder after appointment is made
            PreparedStatement preparedStatement=connection.prepareStatement("SELECT * FROM doctors WHERE id = ?");
            preparedStatement.setInt(1, doctorId);
            ResultSet resultSet1=preparedStatement.executeQuery();

            while (resultSet1.next()) {
                Doctor doctor = new Doctor(resultSet1.getString("FirstName"), resultSet1.getString("LastName"),
                        resultSet1.getString("Specialization"), resultSet1.getString("Street"),
                        resultSet1.getString("HouseNumber"), resultSet1.getString("ZIP"), resultSet1.getString("Town"));
                DoctorHolder doctorHolder = DoctorHolder.getInstance();
                doctorHolder.setDoctor(doctor);
                doctorHolder.getDoctor();
                System.out.println(doctor.getDocType());
                MailUtil.sendMail(testemailuser, doctor.getName(), doctor.getLastName(), doctor.getStreet(), doctor.getHouseNumber(),
                        doctor.getZIP(), doctor.getTown(), datePicker.getValue(), timeComboBox.getSelectionModel().getSelectedItem());
            }
            //just test email to make sure email is working and the database pull is the problem
            //MailUtil.sendMail(testemailuser, "Test", "Test", "Test", "Test", "1234", "Test", datePicker.getValue(), timeComboBox.getSelectionModel().getSelectedItem());

        }catch(SQLException | MessagingException e ){
            System.out.println("Connection failed in MakeAppointment or potentially email sending went wrong");
        }
        connection.close();

        controller.switchToMainPage(event);

    }

    // Function that gets doctors in radius and clears list (Amalie), the magic here happens in SQL where I query with the spherical law of cosines
    public void getDoctorInRadius() throws SQLException {
        doctorList.getItems().clear();
        specializationList.getItems().clear();
        int rad=radiusComboBox.getSelectionModel().getSelectedItem();

        Connection connection = null;

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ehealth_db", "ehealth", db_password);
            System.out.println("Successful DB connection");
            PreparedStatement preparedStatement = connection.prepareStatement("select * from doctors WHERE (\n" +
                    "          acos(sin(Latitude * 0.0175) * sin( ?* 0.0175) \n" +
                    "               + cos(Latitude * 0.0175) * cos( ?* 0.0175) *    \n" +
                    "                 cos(( ?* 0.0175) - (Longitude * 0.0175))\n" +
                    "              ) * 6371  <= ?\n" +
                    "      ); ");
            preparedStatement.setFloat(1, user.getLatitude());
            preparedStatement.setFloat(2, user.getLatitude());
            preparedStatement.setFloat(3, user.getLongitude());
            preparedStatement.setInt(4, rad);  //receives integer that shows km range user wants to search in
            ResultSet resultSet = preparedStatement.executeQuery();
            System.out.println(radiusComboBox.getSelectionModel().getSelectedItem());
            while(resultSet.next()){
                doctorList.getItems().add("Dr. " + resultSet.getString("FirstName")+ " " +resultSet.getString("LastName"));
                specializationList.getItems().add(resultSet.getString("Specialization"));
                System.out.println(resultSet.getString("Specialization"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        connection.close();

    }


    /**
     * Class to switch back to mainPage after button click
     * @param event
     * @throws IOException
     */
        @FXML
    public void goBack(ActionEvent event) throws IOException {
        controller.switchToMainPage(event);
    }


    /**
     * Method to call before showing the stage, updates the doctor list and inserts times and radii into the comboBoxes
     * @throws SQLException
     */
    public void setAll() throws SQLException {
        timeComboBox.setItems(FXCollections.observableArrayList(timeList));
        radiusComboBox.setItems(FXCollections.observableArrayList(radiusList));
        System.out.println("ChoiceBox method started");

        Connection connection = null;

        doctorList.getItems().clear();
        specializationList.getItems().clear();
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ehealth_db", "ehealth", db_password);
            System.out.println("Successful DB connection");
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM doctors ");
            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                doctorList.getItems().add("Dr. " + /* maybe better without */ resultSet.getString("FirstName")+ " " +resultSet.getString("LastName"));
                specializationList.getItems().add(resultSet.getString("Specialization"));
                System.out.println("FirstName: " + resultSet.getString("FirstName") + "\tLastName: "
                        + resultSet.getString("LastName"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        connection.close();

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
