package com.gui.ehealt_v2;

import UserManagement.User;
import UserManagement.UserHolder;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.sql.*;

/**
 * Class controls GUI of health information
 * @author Viktor Benini; StudentID: 1298976
 */
public class HealthInformationController {

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

    /**
     * Methode stores the health information from the user in a database table. It checks if
     * the user already has info's saved or not. In the first case it updates the db in the second
     * it inserts a new dataset.
     * @throws SQLException
     */
    @FXML
    public void setHealthInfoOnClick() throws SQLException {
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
    }

    public void setUserInfo(User user){
        nameLabel.setText(user.getFirstname() + " " + user.getLastName() + "\n" +
                user.getEmail() + "\n" +
                user.getPhoneNumber());
        addressLabel.setText(user.getStreet() + " " + user.getHousenumber() + "\n" +
                user.getZip() + " " + user.getTown());
    }
}
