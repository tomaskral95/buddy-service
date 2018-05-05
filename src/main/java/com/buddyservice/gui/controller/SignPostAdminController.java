package com.buddyservice.gui.controller;

import com.buddyservice.gui.Main;
import com.buddyservice.service.IStudentService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.util.ResourceBundle;


@Controller
public class SignPostAdminController
        extends SwitchableController
        implements Initializable {

    @Autowired
    private IStudentService studentService;

    @FXML
    private AnchorPane rootPane;

    @FXML
    private Label xnameLabel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        xnameLabel.setText(Main.getCacheManager().getValue("xname"));
    }

    @FXML
    private void ulozBtnHandle(ActionEvent event) {
    }

    @FXML
    public void logOutButtonAction(ActionEvent event) {
        Main.setLoggedStudent(null);
        Main.getCacheManager().clearCache();
        proceedToNextPage("graphics/fxml/logIn.fxml", rootPane);
    }

    @FXML
    public void newStudentButtonAction(ActionEvent event) {
        proceedToNextPage("graphics/fxml/newStudent.fxml", rootPane);
    }

    @FXML
    public void displayStudentsButtonAction(ActionEvent event) {

    }

    @FXML
    public void addActionButtonAction(ActionEvent event) {
    }

    @FXML
    public void displayActionsButtonAction(ActionEvent event) {
    }

}
