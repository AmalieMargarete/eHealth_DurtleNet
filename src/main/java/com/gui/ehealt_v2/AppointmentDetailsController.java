package com.gui.ehealt_v2;

import Appointment.Appointment;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

/**
 * Class to handle the GUI, to show the user the details of his selected appointment
 * @author Viktor Benini; StudentID: 1298976
 */
public class AppointmentDetailsController {

    @FXML
    public Label appointmentDetailsLabel;


    // TODO: look need to be optimized || can be replaced by Text, maybe ?!
    /**
     * Sets the Label, to the details of the appointment, by the provided appointment object.
     * @param appointment
     */
    public void setAppointmentDetailLabel(Appointment appointment){

        appointmentDetailsLabel.setText("Dr. " +appointment.getDoctor().getFirstname() + " " + appointment.getDoctor().getLastName() + "\n" +
                appointment.getDoctor().getDocType() + "\n" +
                appointment.getDoctor().getStreet() +" "+ appointment.getDoctor().getHouseNumber() + "\n"+
                appointment.getDoctor().getTown() + " " + appointment.getDoctor().getZIP() + "\n\n" +
                "Appointment Date: " + appointment.getDate() + " at: " + appointment.getTime() +"\n\n"+
                appointment.getNote()
                );
    }



}
