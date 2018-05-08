package com.buddyservice.gui.controller;

import com.buddyservice.gui.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.util.ResourceBundle;


@Controller
public class SignPostStudentController
        extends SwitchableController
        implements Initializable {

    @FXML
    private AnchorPane rootPane;

    @FXML
    private Label xnameLabel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        xnameLabel.setText(Main.getCacheManager().getValue("xname"));
    }

    @FXML
    public void logOutButtonAction(ActionEvent event) {
        Main.setLoggedStudent(null);
        Main.getCacheManager().clearCache();
        proceedToNextPage("graphics/fxml/logIn.fxml", rootPane);
    }

    @FXML
    public void zobrazProfilButtonAction(ActionEvent event) {
        proceedToNextPage("graphics/fxml/showProfile.fxml", rootPane);
    }

    @FXML
    public void zobrazVsechnyAkceButtonAction(ActionEvent event) {
        proceedToNextPage("graphics/fxml/showAllActions.fxml", rootPane);
    }
}
