package com.gui.ehealt_v2;

import Encryption.HashClass;
import UserManagement.User;
import UserManagement.UserHolder;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Window;
import org.w3c.dom.Text;

import java.sql.*;
import java.time.LocalDate;

/**
 * Class handles the GUI of the edit user Scene and provides functions to edit
 * the user in the database. It includes a UserHolder for easy user information
 * access and a SceneController object.
 * @author Amalie Wilke; StudentID: 1304925
 */
public class UserProfileEditController {
    @FXML
    private Button save_button;
    @FXML
    private TextField firstname_input;
    @FXML
    private TextField lastname_input;
    @FXML
    private TextField street_input;
    @FXML
    private TextField zip_input;
    @FXML
    private TextField number_input;
    @FXML
    private TextField town_input;
    @FXML
    private TextField email_input;
    @FXML
    private TextField password_input;
    @FXML
    private TextField password_confirmation;
    @FXML
    private DatePicker birthday_input;
    @FXML
    private TextField insurancename_input;

    final private SceneController controller = new SceneController();
    UserHolder userHolder = UserHolder.getInstance();
    User user = userHolder.getUser();

    /**
     * This method is called by pressing the edit button.
     * It opens a database connection and checks the user. Afterwards
     * it checks the fxml textField for any input and add this by an update
     * command to the database.
     * @param event
     * @throws Exception
     */
    public void UserEdit(ActionEvent event) throws Exception {

        Window owner = save_button.getScene().getWindow();

            Connection connection;
            PreparedStatement Insert = null;
            PreparedStatement DoesUserIDExist = null;
            PreparedStatement DoesUserEmailExist = null;
            ResultSet resultSet = null;

            try {
                int userid = user.getUserId();
                connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/ehealth_db", "ehealth", "hells"); //localhost:3306/
                System.out.println("Successful DB connection");
                DoesUserIDExist = connection.prepareStatement("SELECT * FROM users WHERE id=?");
                DoesUserIDExist.setInt(1, userid);
                resultSet = DoesUserIDExist.executeQuery();  //never forget executeQuery because otherwise everything does not work

                //check if resultSet is empty
                if (resultSet.next() == false) {
                    System.out.println("UserID does not exist");  //wouldnt this be a security thing
                    showAlert(Alert.AlertType.ERROR, owner, "Form Error", "Unable to find UserID, please contact our support team");
                   controller.switchToMainPage(event);
                }
                if (!firstname_input.getText().isEmpty()) {
                    String nfn = firstname_input.getText();
                    Insert = connection.prepareStatement("UPDATE users SET FirstName=? WHERE id=?");
                    Insert.setString(1, nfn);
                    Insert.setInt(2, userid);
                    Insert.executeUpdate();
                    user.setFirstname(nfn);
                    userHolder.setUser(user);
                }

                if (!lastname_input.getText().isEmpty()) {
                    String nln = lastname_input.getText();
                    Insert = connection.prepareStatement("UPDATE users SET LastName=? WHERE id=?");
                    Insert.setString(1, nln);
                    Insert.setInt(2, userid);
                    Insert.executeUpdate();
                    user.setLastName(nln);
                    userHolder.setUser(user);
                }

                if (!street_input.getText().isEmpty()) {
                    String ns = street_input.getText();
                    Insert = connection.prepareStatement("UPDATE users SET Street=? WHERE id=?");
                    Insert.setString(1, ns);
                    Insert.setInt(2, userid);
                    Insert.executeUpdate();
                    user.setStreet(ns);
                    userHolder.setUser(user);
                }

                if (!number_input.getText().isEmpty()) {
                    String nn = number_input.getText();
                    Insert = connection.prepareStatement("UPDATE users SET HouseNumber=? WHERE id=?");
                    Insert.setString(1, nn);
                    Insert.setInt(2, userid);
                    Insert.executeUpdate();
                    user.setHousenumber(nn);
                    userHolder.setUser(user);
                }

                if (!zip_input.getText().isEmpty()) {
                    String nz = zip_input.getText();
                    Insert = connection.prepareStatement("UPDATE users SET ZIP=? WHERE id=?");
                    Insert.setString(1, nz);
                    Insert.setInt(2, userid);
                    Insert.executeUpdate();
                    user.setZip(nz);
                    userHolder.setUser(user);
                }

                if (!town_input.getText().isEmpty()) {
                    String nt = town_input.getText();
                    Insert = connection.prepareStatement("UPDATE users SET Town=? WHERE id=?");
                    Insert.setString(1, nt);
                    Insert.setInt(2, userid);
                    Insert.executeUpdate();
                    user.setTown(nt);
                    userHolder.setUser(user);
                }

                if (!email_input.getText().isEmpty()) {
                    String ne = email_input.getText();
                    DoesUserEmailExist = connection.prepareStatement("SELECT * FROM users WHERE Email=?");
                    DoesUserEmailExist.setString(1, ne);
                    resultSet = DoesUserEmailExist.executeQuery();  //never forget executeQuery because otherwise everything does not work
                    System.out.println("Check if account exists");

                    if (resultSet.isBeforeFirst()) {
                        System.out.println("User already exists");  //wouldn't this be a security thing
                        showAlert(Alert.AlertType.ERROR, owner, "Form Error", "Email not available");
                        return;
                    } else {
                        Insert = connection.prepareStatement("UPDATE users SET Email=? WHERE id=?");
                        Insert.setString(1, ne);
                        Insert.setInt(2, userid);
                        Insert.executeUpdate();
                    }
                }

                /*if (!(birthday_input.getValue()==null)) {
                    if(birthday_input.getValue().isAfter(LocalDate.now())){
                        System.out.println("Error bday ");
                    }
                    Date bd=Date.valueOf(birthday_input.getValue());
                    Insert = connection.prepareStatement("UPDATE users SET BirthDate=? WHERE id=?");
                    Insert.setDate(1, bd);
                    Insert.setInt(2, userid);
                    Insert.executeUpdate();
                }*/

                //can an admin change a password?
                if (!password_input.getText().isEmpty()) {
                    if(password_confirmation.getText().isEmpty()){
                        showAlert(Alert.AlertType.ERROR, owner, "Form Error", "Enter confirmation password as well");
                        return;
                    }

                    if(false==password_input.getText().equals(password_confirmation.getText())){
                        System.out.println("Passwords do not match!");
                        showAlert(Alert.AlertType.ERROR, owner, "Form Error", "Passwords do not match!");
                        password_input.clear();
                        password_confirmation.clear();
                        return;
                    }
                    String np = HashClass.getHash(password_input.getText());    // Hashes password
                    System.out.println(np);
                    Insert = connection.prepareStatement("UPDATE users SET Kennwort=? WHERE id=?");
                    Insert.setString(1, HashClass.getHash(np));
                    Insert.setInt(2, userid);
                    Insert.executeUpdate();
                }

                if (!insurancename_input.getText().isEmpty()) {
                    String ni = insurancename_input.getText();
                    Insert = connection.prepareStatement("UPDATE users SET InsuranceName=? WHERE id=?");
                    Insert.setString(1, ni);
                    Insert.setInt(2, userid);
                    Insert.executeUpdate();
                }

                connection.close();

                // window still open just close this one
                //controller.switchToMainPage(event);
                controller.closeStage(save_button);

            } catch (SQLException ex) {
                System.out.println("Unsuccessful connection");
            }

        }

    /**
     * Method is used to throw an alert, by opening a new window. The method needs the parameters AlertType which specific the alert reason
     * the Window owner which parses the current window, and the Stage name as well as the alert massage shown in the window.
     * @param alertType
     * @param owner
     * @param s
     * @param alertmessage
     */
    public static void showAlert (Alert.AlertType alertType, Window owner, String s, String alertmessage){

        Alert alert = new Alert(alertType);
        alert.setTitle(s);
        alert.setHeaderText(null);
        alert.setContentText(alertmessage);
        alert.initOwner(owner);
        alert.show();
    }
    }