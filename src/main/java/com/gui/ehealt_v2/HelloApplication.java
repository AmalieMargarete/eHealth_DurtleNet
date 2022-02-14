package com.gui.ehealt_v2;

import Scheduler.Scheduler;
import Scheduler.SchedulerJob;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Start class. This class defines the start of the program and opens the login window, by using
 * javaFX, to open a stage and a scene, and selecting the correct fxml data.
 */
public class HelloApplication extends Application {
    /**
     * method that opens the login window, by calling an FXMLLoader that reads the fxml file and
     * provides it to the scene and stage.
     * @param stage
     * @throws IOException
     */
    @Override
    public void start(Stage stage) throws IOException {
        // starts the reminder scheduler when starting the program
        Scheduler.runReminder();

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("login_design_amalie.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 452, 307);
        //scene.getStylesheets().add("MutedGreen.css"); I would work in some CSS with more time (Amalie)
        stage.setTitle("Durtle says hi");
        scene.setFill(Color.BLACK);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * main starts the start method
     * @param args
     */
    public static void main(String[] args) {
        launch();
    }
}