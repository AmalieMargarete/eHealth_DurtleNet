package com.gui.ehealt_v2;
/**
 * Class allows for user with admin privileges to edit, delete and create(?) users and view their appointments
 * view as a table
 * DB connection achieved with JDBC driver
 * DB: MYSQL, localhost
 * @author Amalie Wilke; StudentID: 1304925
 */

import UserManagement.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminController {

    // I want to switch back to login with close button: final private SceneController controller = new SceneController();

    @FXML
    private TableView<User> user_tableview;
    @FXML
    private TableColumn<User, String> id_column;
    @FXML
    private TableColumn<User, String> firstname_column;
    @FXML
    private TableColumn<User, String> lastname_column;
    @FXML
    private TableColumn<User, String> street_column;
    @FXML
    private TableColumn<User, String> housenumber_column;
    @FXML
    private TableColumn<User, String> zip_column;
    @FXML
    private TableColumn<User, String> town_column;
    @FXML
    private TableColumn<User, String> email_column;
    @FXML
    private TableColumn<User, String> birthday_column;
    @FXML
    private TableColumn<User, String> insurancename_column;
    @FXML
    private TableColumn<User, String> insurancetype_column;
    @FXML
    private TableColumn<User, String> creationdate_column;
    //FXML elements for adding a user
    @FXML
    private TextField firstname_input;
    @FXML
    private TextField lastname_input;
    @FXML
    private TextField street_input;
    @FXML
    private TextField number_input;
    @FXML
    private TextField zip_input;
    @FXML
    private TextField town_input;
    @FXML
    private TextField email_input;
    @FXML
    private TextField birthday_input;
    @FXML
    private TextField insurancename_input;

    //@FXML
    //private Button adduser_button;

    ObservableList<User> userlist = FXCollections.observableArrayList();

    public void initialize() {

        String query = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        User user = null;
        try {
            connection = DBConnection.getConnection();
            resultSet = connection.createStatement().executeQuery("SELECT * from users");
            while(resultSet.next()){
                userlist.add(new User(resultSet.getInt("id"),
                        (resultSet.getString("firstname"))));
            }


        } catch (SQLException ex) {
            System.out.println("Something went wrong loading DB users into list");
        }

        id_column.setCellValueFactory(new PropertyValueFactory<>("id"));
        firstname_column.setCellValueFactory(new PropertyValueFactory<>("firstname"));
        lastname_column.setCellValueFactory(new PropertyValueFactory<>("lastname"));

        user_tableview.setItems(userlist);

    }


}

    /* Code that somehow just will not work:
    ObservableList<User> UserList= FXCollections.observableArrayList();

    public void loadInfo(){

        connection =DBConnection.getConnection();
        id_column.setCellValueFactory(new PropertyValueFactory<>("id"));
        firstname_column.setCellValueFactory(new PropertyValueFactory<>("firstname"));

    }

    public void updateButton(ActionEvent event) throws IOException{
        loadInfo();
        UserList.clear();
        try{
            preparedStatement=connection.prepareStatement("SELECT * FROM users");
            resultSet=preparedStatement.executeQuery();

            while(resultSet.next()){
                UserList.add(new User(
                        resultSet.getInt("id"),
                        resultSet.getString("FirstName")
                ));
                user_tableview.setItems(UserList);
            }
        }catch(SQLException ex){
            System.out.println("Refreshing table from DB went wrong");
        }
    }*/





