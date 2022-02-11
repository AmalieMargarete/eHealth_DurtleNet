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
    private Button edit_button;
    @FXML
    private Button back_button;



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

    }
    public void returnToMain(ActionEvent event) throws IOException{
        controller.switchToMainPage(event);


    }
    public void switchToEditUser(ActionEvent event) throws IOException{
        controller.switchToEditUser(event);


    }


}
