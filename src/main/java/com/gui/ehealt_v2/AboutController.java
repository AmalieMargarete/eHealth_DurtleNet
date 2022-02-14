package com.gui.ehealt_v2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

/**
 *
 * @author Amalie Wilke; StudentID: 1304925
 */
public class AboutController {

    final private SceneController controller = new SceneController();
    @FXML
    private Button back_button;

    public void returnToMain(ActionEvent event) throws IOException {
        controller.switchToMainPage(event);


    }
}
