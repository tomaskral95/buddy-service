package com.buddyservice.gui.controller;

import com.buddyservice.service.IStudentService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.io.IOException;

@Controller
public class LogInController {

    @Autowired
    private IStudentService studentService;

    @FXML
    private AnchorPane rootPane;

    @FXML
    private TextField rodneCisloTextField;

    @FXML
    private PasswordField hesloPasswordField;

    @FXML
    private void prihlasitBtnHandle(ActionEvent event) {
        String rodneCislo = rodneCisloTextField.getText();
        String heslo = hesloPasswordField.getText();
        boolean isRegisteredUser = studentService.authenticateStudent(rodneCislo, heslo);
        if (isRegisteredUser) {
            proceedToNextPage();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Nesprávné přihlašovací údaje");
            alert.show();
        }
    }

    private void proceedToNextPage() {
        AnchorPane newAnchorPane = null;
        try {
            newAnchorPane = FXMLLoader.load(getClass().getClassLoader().getResource("graphics/fxml/upravAkci.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        rootPane.getChildren().setAll(newAnchorPane);
    }

}
