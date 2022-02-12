package com.gui.ehealt_v2;

import Scheduler.Scheduler;
import Scheduler.SchedulerJob;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
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

    public static void main(String[] args) {
        launch();
    }
}