package com.buddyservice.gui.controller;

import com.buddyservice.domain.Student;
import com.buddyservice.gui.Main;
import com.buddyservice.service.IStudentService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class LogInController extends SwitchableController {

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
            Student loggedStudent = studentService.findStudent(rodneCislo);
            Main.getCacheManager().addValue("xname", loggedStudent.getXname());
            Main.setLoggedStudent(loggedStudent);
            proceedToNextPage("graphics/fxml/signPostAdmin.fxml", rootPane);
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Nesprávné přihlašovací údaje");
            alert.show();
        }
    }

}
