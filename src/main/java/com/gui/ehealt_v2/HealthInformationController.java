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
 * @author Viktor Benini; StudentID: 1298976
 */
public class HealthInformationController {

    SceneController controller = new SceneController();

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
        UserHolder holder = UserHolder.getInstance();
        User user = holder.getUser();

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
     * where the user can select the saving directory. The directory will be saved in a .txt file for further use.
     */
    @FXML
    public void selectPathOnClick(){
        JFileChooser fileChooser = new JFileChooser();

        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);        // select only folders
        fileChooser.showSaveDialog(null);                                 // select file to save
        String path = fileChooser.getSelectedFile().getAbsolutePath();          // get path
        filePathTextField.setText(path);                                        // show path in txtField
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
        stage.show();
    }

    @FXML
    public void switchToMainPage(ActionEvent event) throws IOException {
        controller.switchToMainPage(event);
    }

    public void setUserInfo(User user) throws FileNotFoundException {
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

    }
}
