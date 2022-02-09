package com.gui.ehealt_v2;
/**
 * Class allows for login of user and redirecting to either mainpage, admin view or registration
 * DB connection achieved with JDBC driver
 * DB: MYSQL, localhost
 * @author Amalie Wilke; StudentID: 1304925, scene controll function used by Viktor Benini
 */

import Encryption.HashClass;
import UserManagement.User;
import UserManagement.UserHolder;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Window;

import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;

public class HelloController {

    // easy changeable DB password
    String db_password = "hells";


    HashClass hash = new HashClass();
    // Used to switch scenes
    final private SceneController controller = new SceneController();

    @FXML
    private TextField email_textfield;

    @FXML
    private TextField password_textfield;

    @FXML
    private Button login_button;


    public void switchToRegistration(ActionEvent event) throws IOException{

        controller.switchToRegistration(event);

    }

    public void login(ActionEvent event) throws Exception{

        Window owner=login_button.getScene().getWindow();
        System.out.println(email_textfield.getText());  //just as a checkup
        System.out.println(password_textfield.getText());  //just as a checkup

        if(email_textfield.getText().isEmpty()){
            showAlert(Alert.AlertType.ERROR, owner, "Form Error!", "Email needs to be entered");
            return;
        }

        if(password_textfield.getText().isEmpty()){
            showAlert(AlertType.ERROR, owner, "Form Error!", "Password needs to be entered");
            return;
        }

        String email=email_textfield.getText();
        String password=hash.getHash(password_textfield.getText());

        Connection connection = null;
        PreparedStatement preparedStatement=null;
        ResultSet resultSet=null;
        int IsAdmin=0;
        int port=3306;
        String db_name= "ehealth_db";
        String username= "root";
        String pw= "hells";


        try{
            connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/ehealth_db", "ehealth", db_password);
            System.out.println("Successful DB connection");
            preparedStatement=connection.prepareStatement("SELECT * FROM users WHERE Email = ?");
            preparedStatement.setString(1, email);
            resultSet=preparedStatement.executeQuery();


            if(!resultSet.isBeforeFirst()){
                System.out.println("User not found");
                //display as alert
                showAlert(Alert.AlertType.ERROR, owner, "Form Error!", "User not found");
            }

            else{
                while(resultSet.next()){
                    String retrievedPassword=resultSet.getString("Kennwort");
                    IsAdmin=resultSet.getInt("IsAdmin");
                    System.out.println(IsAdmin);
                    System.out.println("Direct from SQL: "+resultSet.getString("FirstName"));

                    if((retrievedPassword.equals(password)) & (IsAdmin==1)){
                        System.out.println("User is an admin");
                        controller.switchToAdminView(event);
                        connection.close();
                        return;

                    }
                    if(retrievedPassword.equals(password)){
                      //here I want to switch scenes
                        System.out.println("Password is correct");

                        // Viktor: get user information and store it in user Amalie: added latitude and longitude

                        // Switched constructor for user
                        /*  User user = new User(resultSet.getInt("id"), resultSet.getString("FirstName")
                        , resultSet.getString("LastName"), resultSet.getString("Street"), resultSet.getString("HouseNumber"),
                                resultSet.getString("ZIP"), resultSet.getString("Town"), resultSet.getString("Email"), resultSet.getDate("BirthDate"),
                                resultSet.getString("InsuranceType"), resultSet.getString("InsuranceName"), resultSet.getFloat("Latitude"), resultSet.getFloat("Longitude"));
*/
                        User user = new User(resultSet.getInt("id"), resultSet.getString("FirstName")
                                , resultSet.getString("LastName"), resultSet.getDate("BirthDate"),
                                resultSet.getString("Street"), resultSet.getString("HouseNumber"),
                                resultSet.getString("ZIP"), resultSet.getString("Town"), resultSet.getString("Email"),
                                resultSet.getString("InsuranceName"), resultSet.getString("InsuranceType"), resultSet.getFloat("Latitude"), resultSet.getFloat("Longitude"));
                        // UserHolder stores user, so it can be easily requested at any point in the program
                        UserHolder userHolder = UserHolder.getInstance();
                        userHolder.setUser(user);
                        System.out.println(user.getFirstname());
                        //------------------------------------------------------

                        controller.switchToMainPage(event);

                    }
                    else{
                        showAlert(Alert.AlertType.ERROR, owner, "Form Error!", "User not found");
                        return;
                    }
                }
                connection.close();  //close db connection
                System.out.println("DB connection closed");
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public static void showAlert(Alert.AlertType alertType, Window owner, String s, String alertmessage) {

        Alert alert= new Alert(alertType);
        alert.setTitle(s);
        alert.setHeaderText(null);
        alert.setContentText(alertmessage);
        alert.initOwner(owner);
        alert.show();
    }

    /* Viktor Testcode:
    private Stage stage;
       private Scene scene;
    private Parent root;

    @FXML
    private Label welcomeText;
    public TextField loginFiled;
    public KeyCode key;

    public void switchToMainPage(ActionEvent event) throws IOException {
        // simplified test to check login scene switch
        if(loginFiled.getText().equals("Viktor") || loginFiled.getText().equals("Amalie") || loginFiled.getText().equals("Serkan")) {
            Parent root = FXMLLoader.load(getClass().getResource("mainPage.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }


    @FXML
    protected void ButtonPressed(KeyEvent event) throws IOException {
        if((loginFiled.getText().equals("Viktor") || loginFiled.getText().equals("Amalie") || loginFiled.getText().equals("Serkan"))
                && event.getCode().toString().equals("ENTER")) {
            Parent root = FXMLLoader.load(getClass().getResource("mainPage.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } */

}