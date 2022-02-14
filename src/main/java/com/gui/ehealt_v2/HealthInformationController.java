package com.gui.ehealt_v2;

import UserManagement.User;
import UserManagement.UserHolder;
import com.itextpdf.text.DocumentException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.util.Scanner;

/**
 * Class controls GUI of health information
 * The user can set his information with the given fxml textFields and can select the path, where the created pdf of
 * his information will be saved.
 * The SceneController object is used to jump easily between scenes if needed and provides the corresponding functions.
 * The UserHolder is used to access the data of the current user, without opening a database connection.
 * @author Viktor Benini; StudentID: 1298976
 */
public class HealthInformationController {

    SceneController controller = new SceneController();

    UserHolder holder = UserHolder.getInstance();
    User user = holder.getUser();

    @FXML
    private Label nameLabel;
    @FXML
    private Label addressLabel;
    @FXML
    private TextField socialSecurityNumberField;
    @FXML
    private TextField medicalRecordNumberField;
    @FXML
    private TextField healthIncuranceNumberField;
    @FXML
    private TextField heightField;
    @FXML
    private TextField weightField;
    @FXML
    private TextArea medicineArea;
    @FXML
    private TextArea allergiesArea;
    @FXML
    private TextField filePathTextField;

    /**
     * Methode stores the health information from the user in a database table. It checks if
     * the user already has info's saved or not. In the first case it updates the db in the second
     * it inserts a new dataset.
     * @throws SQLException
     */
    @FXML
    public void setHealthInfoOnClick() throws SQLException, DocumentException, FileNotFoundException {


        Connection connection = null;
        ResultSet resultSet = null;
        try {
            connection= DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/ehealth_db", "ehealth", "hells"); //localhost:3306/
            System.out.println("Successful DB connection");
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM healthInfo");
            preparedStatement.executeQuery();
            resultSet = preparedStatement.getResultSet();
        }catch(SQLException e){
            e.printStackTrace();
        }

        while(resultSet.next()){
            // checks if user already has his health info saved in db, if that's the case update.
            if(resultSet.getInt("userId") == user.getUserId()){
                try{
                    PreparedStatement update =connection.prepareStatement("UPDATE healthInfo " +
                            "SET socialSecurityNumber = ?, medicalRecordNumber = ?, HealthInsuranceNumber = ?, height = ?, weight = ?, medicineUse = ?, allergies = ? " +
                            "WHERE userId = ?");
                    update.setString(1, socialSecurityNumberField.getText());
                    update.setString(2, medicalRecordNumberField.getText());
                    update.setString(3, healthIncuranceNumberField.getText());
                    update.setString(4, heightField.getText());
                    update.setString(5, weightField.getText());
                    update.setString(6, medicineArea.getText());
                    update.setString(7, allergiesArea.getText());
                    update.setInt(8, user.getUserId());
                    update.executeUpdate();  //never forget executeQuery because otherwise everything does not work
                    System.out.println("Update successful");
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                connection.close();


                // store the information as pdf by calling the "store" methode defined in MainPageController
                MainPageController mainPageController = new MainPageController();
                mainPageController.onPrintMenuClick();

                // close stage
                Stage stage = (Stage)nameLabel.getScene().getWindow();
                stage.close();
                return;
            }
        }

        // if no info are stored in the db create a new dataset
        try{
            PreparedStatement Insert = connection.prepareStatement("INSERT INTO healthInfo (userId, socialSecurityNumber, medicalRecordNumber, HealthInsuranceNumber, height, weight, medicineUse, allergies) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
            Insert.setInt(1, user.getUserId());
            Insert.setString(2,socialSecurityNumberField.getText());
            Insert.setString(3,medicalRecordNumberField.getText());
            Insert.setString(4,healthIncuranceNumberField.getText());
            Insert.setString(5,heightField.getText());
            Insert.setString(6,weightField.getText());
            Insert.setString(7,medicineArea.getText());
            Insert.setString(8,allergiesArea.getText());
            Insert.executeUpdate();
            System.out.println("Insert successful, health info now in DB");

        }catch (SQLException e){
            e.printStackTrace();
        }
        connection.close();

        // close stage after saving file
        Stage stage = (Stage)nameLabel.getScene().getWindow();
        stage.close();
    }

    /**
     * method to select a folder to save the healthInformation of the user. JFileChooser opens the internal folder tree
     * where the user can select the saving directory. The directory will be saved in a .txt file for further use, for example
     * opening the file at another point in the program. By this method it can easily be found.
     */
    @FXML
    public void selectPathOnClick(){


        JFileChooser fileChooser = new JFileChooser();

        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);        // select only folders
        fileChooser.showSaveDialog(null);                                 // select file to save
        String path = fileChooser.getSelectedFile().getAbsolutePath();          // get path
        filePathTextField.setText(path + "\\HealthInfo.pdf");                                        // show path in txtField
        path = path.replace("\\", "\\\\");                      // replace the \ with \\ because java
        System.out.println(path);

        FileWriter writer;
        File file = new File("HealthInfoPath.txt");                     // file with filename

        try{
            writer = new FileWriter(file, false);
            writer.write(path); // "write" selected path name
            writer.flush();     // writes and checks if data is written
            writer.close();

        }catch(Exception e){
            e.printStackTrace();
        }

        // show the HealthInformation Edit window primary
        Stage stage = (Stage)nameLabel.getScene().getWindow();
        stage.setAlwaysOnTop(true);    // not a perfect fix but stage is in top of mainPage
    }

    /**
     * Method to switch back to the main page after pressing the back button.
     * Method calls objects, method of SceneController to switch scenes
     * @param event
     * @throws IOException
     */
    @FXML
    public void switchToMainPage(ActionEvent event) throws IOException {
        // controller.switchToMainPage(event);

        // mainPage still open need too close current stage
        controller.closeStage(nameLabel);
    }

    /**
     * Method that sets the information of the user to the Scene. So the user sees his personal information
     * that are added to the HealthInformation and can confirm that they are correct. This happens by getting
     * the information out of the database, by using his user id to select the correct dataset.
     * @param user
     * @throws FileNotFoundException
     */
    public void setUserInfo(User user) throws FileNotFoundException, SQLException {
        nameLabel.setText(user.getFirstname() + " " + user.getLastName() + "\n" +
                user.getEmail() + "\n" +
                user.getPhoneNumber());
        addressLabel.setText(user.getStreet() + " " + user.getHousenumber() + "\n" +
                user.getZip() + " " + user.getTown());

        // file path
        Scanner scanner;                                        // create scanner to read from file
        File file = new File("HealthInfoPath.txt");     // load file
        if(file.exists()) {
            try {
                scanner = new Scanner(file);
                String path = scanner.next();                   // selects next element (String)
                path.replace("\\\\", "\\");     // replaces \\ by \
                filePathTextField.setText(path);                // for user friendly use
                scanner.close();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }else {
            filePathTextField.setText("C:\\");                  // set default path
        }

        // fill existing healthInformation
        Connection connection = null;
        ResultSet resultSet = null;
        try {
            connection= DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/ehealth_db", "ehealth", "hells"); //localhost:3306/
            System.out.println("Successful DB connection");
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM healthInfo WHERE userId = ?");
            preparedStatement.setInt(1, user.getUserId());
            preparedStatement.executeQuery();
            resultSet = preparedStatement.getResultSet();
        }catch(SQLException e){
            e.printStackTrace();
        }

        while(resultSet.next()){
            socialSecurityNumberField.setText(resultSet.getString("socialSecurityNumber"));
            medicalRecordNumberField.setText(resultSet.getString("medicalRecordNumber"));
            healthIncuranceNumberField.setText(resultSet.getString("HealthInsuranceNumber"));
            heightField.setText(resultSet.getString("height"));
            weightField.setText(resultSet.getString("weight"));
            medicineArea.setText(resultSet.getString("medicineUse"));
            allergiesArea.setText(resultSet.getString("allergies"));
        }
        connection.close();

    }
}
