package com.gui.ehealt_v2;

import UserManagement.User;
import UserManagement.UserHolder;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;

/**
 * Test class to start the MainPage without registration
 */
public class MainPageApplication extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Method for Testing only! used to skip login with a testUser
     * @param stage
     * @throws IOException
     */
    @Override
    public void start(Stage stage) throws IOException {
        Connection connection = null;

        // Fast entry without login
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ehealth_db", "ehealth", "hells");
            System.out.println("Successful DB connection");
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM users WHERE ID = ?");
            preparedStatement.setInt(1, 3);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                // Viktor: get user information and store it in user
                User user = new User(resultSet.getInt("id"), resultSet.getString("FirstName")
                        , resultSet.getString("LastName"), resultSet.getDate("BirthDate"),
                        resultSet.getString("Street"), resultSet.getString("HouseNumber"),
                        resultSet.getString("ZIP"), resultSet.getString("Town"), resultSet.getString("Email"),
                        resultSet.getString("InsuranceName"), resultSet.getString("InsuranceType"), resultSet.getFloat("Latitude"), resultSet.getFloat("Longitude"));
                // UserHolder stores user, so it can be easily requested at any point in the program
                UserHolder userHolder = UserHolder.getInstance();
                userHolder.setUser(user);
                System.out.println(user.getFirstname());
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        //------------------------------------------------------
        // Start Application
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("mainPage.fxml"));
        Parent root = fxmlLoader.load();

        MainPageController mainPageController = fxmlLoader.getController();
        mainPageController.setImage(new Image(getClass().getResource("Images/Durtle.png").toString()));

        Scene scene = new Scene(root);
        stage.setTitle("Main Page");
        stage.setScene(scene);
        stage.show();
    }
}
