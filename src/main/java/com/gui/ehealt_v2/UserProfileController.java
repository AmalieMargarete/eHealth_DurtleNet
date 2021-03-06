package com.gui.ehealt_v2;

import UserManagement.User;
import UserManagement.UserHolder;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Window;
import javafx.scene.control.Button;

import java.awt.*;
import java.io.IOException;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * Class to control the GUI of the users profile view with the fxml elements and a SceneController
 * object.
 * @author Amalie Wilke; StudentID: 1304925
 */
public class UserProfileController{
    final private SceneController controller = new SceneController();

    @FXML
    private Text firstname_text;
    @FXML
    private Text lastname_text;
    @FXML
    private Text street_text;
    @FXML
    private Text number_text;
    @FXML
    private Text zip_text;
    @FXML
    private Text town_text;
    @FXML
    private Text email_text;
    @FXML
    private Text birthday_text;
    @FXML
    private Text insurancename_text;
    @FXML
    private Text type_text;
    @FXML
    private Button edit_button;
    @FXML
    private Button back_button;


    /**
     * class can be used to bind the users information to the corresponding test field. By
     * using the provided User object.
     * @param user
     */
    public void setUserInfo(User user){
        //birthday from user is returned as Date format, I use DateFormat and SimpleDateFormat to convert it to a string in a designated pattern
        String pattern="yyy-MM-dd";
        DateFormat formatter=new SimpleDateFormat(pattern);
        String bd=formatter.format(user.getBirthday());

        //sets Text fields with user data
        firstname_text.setText(user.getFirstname());
        lastname_text.setText(user.getLastName());
        street_text.setText(user.getStreet());
        number_text.setText(user.getHousenumber());
        zip_text.setText(user.getZip());
        town_text.setText(user.getTown());
        email_text.setText(user.getEmail());
        birthday_text.setText(bd);
        insurancename_text.setText(user.getInsurancename());
        type_text.setText(user.getInsurancetype());

    }

    /**
     * Method is called by the back to menu button on click.
     * This closes the user profile window with the SceneController method,
     * because the MainPage is still open, and we don't to create a new one.
     * @param event
     * @throws IOException
     */
    public void returnToMain(ActionEvent event) throws IOException{
        // MainPage is still open instead of open it again
        // we just close the UserProfile stage

        // closes current stage and opens MainPage
        //controller.switchToMainPage(event);

        // closes current stage
        controller.closeStage(back_button);
    }

    /**
     * Method is called by the edit profile button on click.
     * This method calls the method of the SceneController to open the
     * user edit window.
     * @param event
     * @throws IOException
     */
    public void switchToEditUser(ActionEvent event) throws IOException{
        controller.switchToEditUser(event);


    }


}
