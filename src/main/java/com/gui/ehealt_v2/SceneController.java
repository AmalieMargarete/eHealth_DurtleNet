package com.gui.ehealt_v2;

import Appointment.Appointment;
import UserManagement.User;
import UserManagement.UserHolder;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;


/**
 * Class allows to control scene switches easy from other controller.
 * Scenes can be switched by an ActionEvent or by pressing a button
 * @author Viktor Benini; StudentID: 1298976
 */
public class SceneController {

    private Stage stage;
    private Scene scene;
    private Parent root;

//  Switch to main page     ------------------------------------------------

    /**
     * Changes window to MainPage.fxml
     * @param event
     * @throws IOException
     */
    public void switchToMainPage(ActionEvent event) throws IOException {
        UserHolder holder=UserHolder.getInstance();
        User user= holder.getUser();
        System.out.println(user.getFirstname());
        FXMLLoader loader = new FXMLLoader(getClass().getResource("mainPage.fxml"));
        root = loader.load();

        MainPageController mainPageController = loader.getController();
        mainPageController.setImage(new Image(getClass().getResource("Images/Durtle.png").toString()));

        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

//  Switch to log in        -------------------------------------------------

    /**
     * Changes window to Login.fxml
     * @param event
     * @throws IOException
     */
    public void switchToLogin(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("login_design_amalie.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToLoginTwo(MenuBar bar) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("login_design_amalie.fxml"));
        root = loader.load();
        HelloController helloController = loader.getController();
        stage = (Stage) bar.getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    //added a switch to registration scene here (Amalie)

    /**
     *Changes window to Registration.fxml
     * @param event
     * @throws IOException
     */
    public void switchToRegistration(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("registration_design.fxml"));
        root=loader.load();
        RegistrationController registrationController = loader.getController();
        // TODO: setAll() not a method. Intended?
        // registrationController.setAll();
        registrationController.setImage(new Image(getClass().getResource("Images/Durtle.png").toString())); // set Durtle image
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


    //@Amalie added: switch to Admin View

    /**
     * Changes window to AdminView
     * @param event
     * @throws IOException
     */
    public void switchToAdminView(ActionEvent event) throws IOException{
        root = FXMLLoader.load(getClass().getResource("UserDB_tableview.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }




//  Switch to appointment view  -------------------------------------------------

    /**
     * Changes window to AppointmentView.fxml
     * @param event
     * @throws IOException
     * @throws SQLException
     */
    public void switchToAppointmentView(ActionEvent event) throws IOException, SQLException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("appointmentView.fxml"));
        root = loader.load();
        // root = FXMLLoader.load(getClass().getResource("appointmentView.fxml"));
        AppointmentViewController appointmentViewController = loader.getController();
        appointmentViewController.setAppointmentList();
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);

        // change th backgroundColor, but doesn't
        scene.setFill(Color.color(0.2,0.2,1.0));

        stage.setScene(scene);
        stage.show();
    }


//  Switch to make Appointment view

    /**
     * Changes window to MakeAppointment.fxml
     * @param event
     * @throws IOException
     * @throws SQLException
     */
    public void switchToMakeAppointment(ActionEvent event) throws IOException, SQLException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("makeAppointment.fxml"));
        root = loader.load();
        MakeAppointmentController makeAppointmentController = loader.getController();
        makeAppointmentController.setAll();
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Opens new window, shows Appointment details
     * @param appointment
     * @throws IOException
     */
    public void switchToAppointmentDetailsView(Appointment appointment) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("appointmentDetailView.fxml"));
        Parent root = loader.load();
        AppointmentDetailsController appointmentDetailsController = loader.getController();
        appointmentDetailsController.setAppointmentDetailLabel(appointment);
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Details");
        stage.show();
    }

    /**
     * Opens new Window, to change time of the appointment
     * @param appointment
     * @throws IOException
     */
    public void switchToUpdateAppointmentView(Appointment appointment) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("updateAppointment.fxml"));
        Parent root = loader.load();
        UpdateAppointmentController updateAppointmentController = loader.getController();
        updateAppointmentController.setAll(appointment);
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Details");
        stage.show();
    }

    /**
     * opens the healthInformation and sets the values by calling the method of the corresponding controller
     * @param bar
     * @throws IOException
     * @throws SQLException
     */
   public void switchToEditHealthInfoController(MenuBar bar) throws IOException, SQLException {
        UserHolder holder = UserHolder.getInstance();
        User user = holder.getUser();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("healthInfo.fxml"));
        Parent root = loader.load();
        HealthInformationController healthInformationController = loader.getController();
        healthInformationController.setUserInfo(user);
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Details");
        stage.show();
    }

    /**
     * opens the UserInformation and sets the values by calling the method of the corresponding controller
     * @param bar
     * @throws IOException
     * @throws SQLException
     */
    public void switchToUserProfile(MenuBar bar) throws IOException{
        UserHolder holder = UserHolder.getInstance();
        User user = holder.getUser();
        System.out.println(user.getFirstname());

        FXMLLoader loader = new FXMLLoader(getClass().getResource("userProfile_design_amalie.fxml"));
        Parent root=loader.load();
        UserProfileController userProfileController=loader.getController();
        userProfileController.setUserInfo(user);
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("My profile");
        stage.show();

    }

    public void switchToAbout(MenuBar bar) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("About.fxml"));
        Parent root=loader.load();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("About DurtleNet");
        stage.show();

    }

    /**
     * opens the edit user window
     * @param event
     * @throws IOException
     */
    public void switchToEditUser(ActionEvent event) throws IOException{
        root = FXMLLoader.load(getClass().getResource("editUser_design.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Editing Profile");
        stage.show();
    }

    /**
     * method can be used to close the current stage, by parsing a button of it.
     * @param button
     */
    public void closeStage(Button button){
        stage = (Stage)button.getScene().getWindow();
        stage.close();
    }

    /**
     * method can be used to close the current stage, by parsing a label of it.
     * @param label
     */
    public void closeStage(Label label){
        stage = (Stage)label.getScene().getWindow();
        stage.close();
    }

    /**
     * method can be used to close the current stage, by parsing a fxml text of it.
     * @param text
     */
    public void closeStage(Text text){
        stage = (Stage)text.getScene().getWindow();
        stage.close();
    }

}
