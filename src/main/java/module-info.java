module com.gui.ehealt {
    // JavaFX
    requires javafx.controls;
    requires javafx.fxml;
    requires org.controlsfx.controls;

    // Mail
    // ... don't work in maven
    // requires sendgrid.java;
    // requires java.http.client;
    // requires javax.mail.api;

    // PDF
    requires itextpdf;
    requires kernel;
    requires layout;

    // Scheduler
    requires quartz;
    requires log4j;

    // Database
    requires java.sql;
    requires javafx.graphics;
    requires org.json;
    requires java.desktop;
    requires java.xml.bind;
    requires java.mail;

    // to access Appointment in AppointmentView via setCellValueFactory
    opens com.gui.ehealt_v2 to javafx.fxml;
    exports com.gui.ehealt_v2;
    exports Appointment;
    exports Scheduler;
}

