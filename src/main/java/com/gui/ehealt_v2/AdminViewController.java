package com.gui.ehealt_v2;
/**
 * Tableview of user data in DB
 * DB connection achieved with JDBC driver
 * DB: MYSQL
 * After registration immediate return to login page for login
 * @author Amalie Wilke; StudentID: 1304925
 */
//import UserManagement.User;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Window;

import java.sql.*;
import java.time.LocalDate;

public class AdminViewController {
    LocalDate b;
    final private SceneController controller = new SceneController();
    @FXML
    private TableView<TestUser> user_table;
    @FXML
    private Button update_button;
    @FXML
    private TableColumn<TestUser, String> id_col;
    @FXML
    private TableColumn<TestUser, String> firstname_col;
    @FXML
    private TableColumn<TestUser, String> lastname_col;
    @FXML
    private TableColumn<TestUser, String> street_col;
    @FXML
    private TableColumn<TestUser, String> no_col;
    @FXML
    private TableColumn<TestUser, String> zip_col;
    @FXML
    private TableColumn<TestUser, String> town_col;
    @FXML
    private TableColumn<TestUser, String> email_col;
    @FXML
    private TableColumn<TestUser, String> birthday_col;
    @FXML
    private TableColumn<TestUser, String> insurancename_col;
    @FXML
    private TableColumn<TestUser, Integer> admin_col;
    @FXML
    private TableColumn<TestUser, String> insurancetype_col;
    //@FXML
    //private TableColumn<User, String> creationdate_column;
    //FXML elements for adding a user
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
    private DatePicker birthday_input;
    @FXML
    private TextField insurancename_input;
    @FXML
    private Button adduser_button;
    @FXML
    private Button edituser_button;
    @FXML
    private TextField userid_input;
    @FXML
    private TextField firstname_edit;
    @FXML
    private TextField lastname_edit;
    @FXML
    private TextField street_edit;
    @FXML
    private TextField zip_edit;
    @FXML
    private TextField number_edit;
    @FXML
    private TextField town_edit;
    @FXML
    private TextField email_edit;
    @FXML
    private TextField password_edit;
    @FXML
    private DatePicker birthday_edit;
    @FXML
    private TextField insurancename_edit;
    //delete user button
    @FXML
    private Button deleteuser_button;
    //@FXML
    //private ComboBox insurancetype_box;

    ObservableList<TestUser> testUserObservableList = FXCollections.observableArrayList();
    /*ObservableList<String> insurancetypeList=FXCollections.observableArrayList("Public", "Private");


    public void selectInsuranceType(ActionEvent event) throws Exception{
        insurancetype_box.setItems(insurancetypeList);
        String t=insurancetype_box.getSelectionModel().getSelectedItem().toString();
        System.out.println(t);
    } */


    /**
     * Method is called by the Display User button on click
     * Method loads the user information out of the database by querying all user data and parses into the tableview, by the given values,
     * accept the password, for previews safety reasons and due to the fact, that the password is encrypted and useless information.
     * @param event
     * @throws Exception
     */
    public void DisplayUsers(ActionEvent event) throws Exception {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            testUserObservableList.clear();
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ehealth_db", "ehealth", "hells");
            System.out.println("Successful DB connection");
            resultSet = connection.createStatement().executeQuery("SELECT * FROM users");

            while (resultSet.next()) {
                testUserObservableList.add(new TestUser(resultSet.getInt("id"), resultSet.getString("FirstName"), resultSet.getString("LastName"), resultSet.getString("Street"),
                        resultSet.getString("HouseNumber"), resultSet.getString("ZIP"), resultSet.getString("Town"),
                        resultSet.getString("Email"), resultSet.getDate("BirthDate"), resultSet.getString("InsuranceName"), resultSet.getString("InsuranceType"), resultSet.getInt("IsAdmin")));
            }


        } catch (SQLException exception) {
            System.out.println("Something went wrong connection wise while loading user list");
        }

        id_col.setCellValueFactory(new PropertyValueFactory<>("id_user"));  //use the class names here, not the column names in the DB, you get it confused sometimes..(Amalie)
        firstname_col.setCellValueFactory(new PropertyValueFactory<>("first"));
        lastname_col.setCellValueFactory(new PropertyValueFactory<>("last"));
        street_col.setCellValueFactory(new PropertyValueFactory<>("stre"));
        no_col.setCellValueFactory(new PropertyValueFactory<>("numb"));
        zip_col.setCellValueFactory(new PropertyValueFactory<>("zip_"));
        town_col.setCellValueFactory(new PropertyValueFactory<>("town_"));
        email_col.setCellValueFactory(new PropertyValueFactory<>("email_"));
        birthday_col.setCellValueFactory(new PropertyValueFactory<>("birth"));
        insurancename_col.setCellValueFactory(new PropertyValueFactory<>("insu"));
        insurancetype_col.setCellValueFactory(new PropertyValueFactory<>("type"));
        admin_col.setCellValueFactory(new PropertyValueFactory<>("isAdmin"));
        user_table.setItems(testUserObservableList);
    }

    //public void getBirthday(ActionEvent event) throws Exception{
    //    Date birthday_entered=Date.valueOf(String.valueOf(birthday_input));
    //   System.out.println(b.toString());  //just to check date is entered
    //}
    //Method to add user after button is clicked

    /**
     * Method is called by the add button on click
     * Method adds user to the database by using the inputted information of the GUI and parsing it with an INSERT into the database
     * if the user doesn't already exist. After the insert the textFields in the GUI are cleared to easily add the next user.
     * An alert is thrown if the email or password is empty, or the user already exists.
     * @param event
     * @throws Exception
     */
    public void addNewUser(ActionEvent event) throws Exception {

        Window owner = adduser_button.getScene().getWindow();
        if (email_input.getText().isEmpty() || password_input.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "Form Error!", "Email and password is mandatory even as an admin add");
            return;
        }
        String fn = firstname_input.getText();
        String ln = lastname_input.getText();
        String str = street_input.getText();
        String hn = number_input.getText();
        String zi = zip_input.getText();
        String to = town_input.getText();
        String em = email_input.getText();
        String pw = password_input.getText();
        LocalDate bi = b;
        String in = insurancename_input.getText();

        Connection connection;
        PreparedStatement Insert = null;
        PreparedStatement DoesUserExist = null;
        ResultSet resultSet = null;

        try {
            connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/ehealth_db", "ehealth", "hells"); //localhost:3306/
            System.out.println("Successful DB connection");
            DoesUserExist = connection.prepareStatement("SELECT * FROM users WHERE Email=?");
            DoesUserExist.setString(1, em);
            resultSet = DoesUserExist.executeQuery();  //never forget executeQuery because otherwise everything does not work

            if (resultSet.isBeforeFirst()) {
                System.out.println("User already exists");  //wouldnt this be a security thing
                showAlert(Alert.AlertType.ERROR, owner, "Form Error", "Unable to register, user already exists");
                return;
            } else {

                Insert = connection.prepareStatement("INSERT INTO users (FirstName, LastName, Street, HouseNumber, ZIP, Town, Email, Kennwort, InsuranceName) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");
                Insert.setString(1, fn);
                Insert.setString(2, ln);
                Insert.setString(3, str);
                Insert.setString(4, hn);
                Insert.setString(5, zi);
                Insert.setString(6, to);
                Insert.setString(7, em);
                Insert.setString(8, pw);
                Insert.setString(9, in);
                Insert.executeUpdate();

                //clears text fields after text entry
                firstname_input.clear();
                lastname_input.clear();
                street_input.clear();
                number_input.clear();
                zip_input.clear();
                email_input.clear();
                password_input.clear();
                town_input.clear();
                //birthday_input.setValue(null);
                insurancename_input.clear();
            }

        } catch (SQLException ex) {
            System.out.println("Unsuccessful connection");
        }

    }

    /**
     * Method is called by the edit button on click
     * Method is used to edit an existing user, by inserting the values provided by the GUI via the TextFields. The data can be separately
     * inserted by checking if the TextField are filled. An alert is thrown if the user id isn't set, because the insert is based on the id.
     * @param event
     * @throws Exception
     */
    public void editUser(ActionEvent event) throws Exception {

        Window owner = adduser_button.getScene().getWindow();
        if (userid_input.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "Form Error!", "User ID needs to be entered to edit specific user");
            return;
        } else {
            Connection connection;
            PreparedStatement Insert = null;
            PreparedStatement DoesUserIDExist = null;
            PreparedStatement DoesUserEmailExist = null;
            ResultSet resultSet = null;

            try {
                int userid = Integer.parseInt(userid_input.getText());
                connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/ehealth_db", "ehealth", "hells"); //localhost:3306/
                System.out.println("Successful DB connection");
                DoesUserIDExist = connection.prepareStatement("SELECT * FROM users WHERE id=?");
                DoesUserIDExist.setInt(1, userid);
                resultSet = DoesUserIDExist.executeQuery();  //never forget executeQuery because otherwise everything does not work

                //check if resultSet is empty
                if (resultSet.next() == false) {
                    System.out.println("UserID does not exist");  //wouldnt this be a security thing
                    showAlert(Alert.AlertType.ERROR, owner, "Form Error", "Unable to find UserID");
                    return;
                }
                if (!firstname_edit.getText().isEmpty()) {
                    String nfn = firstname_edit.getText();
                    Insert = connection.prepareStatement("UPDATE users SET FirstName=? WHERE id=?");
                    Insert.setString(1, nfn);
                    Insert.setInt(2, userid);
                    Insert.executeUpdate();
                }

                if (!lastname_edit.getText().isEmpty()) {
                    String nln = lastname_edit.getText();
                    Insert = connection.prepareStatement("UPDATE users SET LastName=? WHERE id=?");
                    Insert.setString(1, nln);
                    Insert.setInt(2, userid);
                    Insert.executeUpdate();
                }

                if (!street_edit.getText().isEmpty()) {
                    String ns = street_edit.getText();
                    Insert = connection.prepareStatement("UPDATE users SET Street=? WHERE id=?");
                    Insert.setString(1, ns);
                    Insert.setInt(2, userid);
                    Insert.executeUpdate();
                }


                if (!number_edit.getText().isEmpty()) {
                    String nn = number_edit.getText();
                    Insert = connection.prepareStatement("UPDATE users SET HouseNumber=? WHERE id=?");
                    Insert.setString(1, nn);
                    Insert.setInt(2, userid);
                    Insert.executeUpdate();
                }

                if (!zip_edit.getText().isEmpty()) {
                    String nz = zip_edit.getText();
                    Insert = connection.prepareStatement("UPDATE users SET ZIP=? WHERE id=?");
                    Insert.setString(1, nz);
                    Insert.setInt(2, userid);
                    Insert.executeUpdate();
                }

                if (!town_edit.getText().isEmpty()) {
                    String nt = town_edit.getText();
                    Insert = connection.prepareStatement("UPDATE users SET Town=? WHERE id=?");
                    Insert.setString(1, nt);
                    Insert.setInt(2, userid);
                    Insert.executeUpdate();
                }

                if (!email_edit.getText().isEmpty()) {
                    String ne = email_edit.getText();
                    DoesUserEmailExist = connection.prepareStatement("SELECT * FROM users WHERE Email=?");
                    DoesUserEmailExist.setString(1, ne);
                    resultSet = DoesUserEmailExist.executeQuery();  //never forget executeQuery because otherwise everything does not work
                    System.out.println("Check if account exists");

                    if (resultSet.isBeforeFirst()) {
                        System.out.println("User already exists");  //wouldn't this be a security thing
                        showAlert(Alert.AlertType.ERROR, owner, "Form Error", "Email is already in database usage");
                        return;
                    } else {
                        Insert = connection.prepareStatement("UPDATE users SET Email=? WHERE id=?");
                        Insert.setString(1, ne);
                        Insert.setInt(2, userid);
                        Insert.executeUpdate();
                    }
                }
                //can an admin change a password?
                if (!password_edit.getText().isEmpty()) {
                    String np = password_edit.getText();
                    Insert = connection.prepareStatement("UPDATE users SET Kennwort=? WHERE id=?");
                    Insert.setString(1, np);
                    Insert.setInt(2, userid);
                    Insert.executeUpdate();
                }

                if (!insurancename_edit.getText().isEmpty()) {
                    String ni = insurancename_edit.getText();
                    Insert = connection.prepareStatement("UPDATE users SET InsuranceName=? WHERE id=?");
                    Insert.setString(1, ni);
                    Insert.setInt(2, userid);
                    Insert.executeUpdate();
                }
                //clears text fields after text entry
                userid_input.clear();
                firstname_edit.clear();
                lastname_edit.clear();
                street_edit.clear();
                number_edit.clear();
                zip_edit.clear();
                email_edit.clear();
                password_edit.clear();
                town_edit.clear();
                //birthday_input.setValue(null);
                insurancename_edit.clear();

            } catch (SQLException ex) {
                System.out.println("Unsuccessful connection");
            }

        }
    }

    /**
     * Method is called by the delete button on click
     * Method is used to delete a user. The user has to be selected in the table to get the email and parse it into the delete statement.
     * @param event
     * @throws Exception
     */
    public void deleteUser(ActionEvent event) throws Exception {

        ObservableList<TestUser> selectedUser, allUsers;
        allUsers = user_table.getItems();
        selectedUser = user_table.getSelectionModel().getSelectedItems();

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/ehealth_db", "ehealth", "hells"); //localhost:3306/
            System.out.println("Successful DB connection");
            preparedStatement = connection.prepareStatement("DELETE FROM users WHERE Email=?");
            preparedStatement.setString(1, user_table.getSelectionModel().getSelectedItem().getEmail_());
            preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            System.out.println("Something went wrong with the DB connection while deleting user");
        }


    }

    /**
     * Method calls a method of the SceneController class, to open up the login window and closes the admin view.
     * @param event
     * @throws Exception
     */
    public void AdminLogout(ActionEvent event) throws Exception {
        controller.switchToLogin(event);

    }

    /**
     * Method is used to throw an alert, by opening a new window. The method needs the parameters AlertType which specific the alert reason
     * the Window owner which parses the current window, and the Stage name as well as the alert massage shown in the window.
     * @param alertType
     * @param owner
     * @param s
     * @param alertmessage
     */
    public static void showAlert(Alert.AlertType alertType, Window owner, String s, String alertmessage) {
        Alert alert = new Alert(alertType);
        alert.setTitle(s);
        alert.setHeaderText(null);
        alert.setContentText(alertmessage);
        alert.initOwner(owner);
        alert.show();
    }

}
