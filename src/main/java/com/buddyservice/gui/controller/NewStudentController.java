package com.buddyservice.gui.controller;

import com.buddyservice.auth.IAuthService;
import com.buddyservice.domain.Adresa;
import com.buddyservice.domain.Pohlavi;
import com.buddyservice.domain.Student;
import com.buddyservice.gui.Main;
import com.buddyservice.service.IStudentService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class NewStudentController extends SwitchableController {

    private ApplicationContext applicationContext = Main.applicationContext;

    @FXML
    public AnchorPane rootPane;

    @FXML
    public TextField xnameTextField;

    @FXML
    public TextField jmenoTextField;

    @FXML
    public TextField prijmeniTextField;

    @FXML
    public TextField titulTextField;

    @FXML
    public TextField datumNarozeniTextField;

    @FXML
    public TextField pohlaviTextField;

    @FXML
    public TextField statniPrislusnostTextField;

    @FXML
    public TextField telefonTextField;

    @FXML
    public TextField emailTextField;

    @FXML
    public TextField zahranicniTextField;

    @FXML
    public TextField statTextField;

    @FXML
    public TextField mestoTextField;

    @FXML
    public TextField uliceTextField;

    @FXML
    public TextField cisloPopisneTextField;

    @FXML
    public TextField rodneCisloTextField;

    @FXML
    public PasswordField hesloPasswordField;

    @FXML
    public void saveButtonAction(ActionEvent event) {
        IAuthService authService = (IAuthService) applicationContext.getBean("authService");
        IStudentService studentService = (IStudentService) applicationContext.getBean("studentService");
        List<String> alerts = new ArrayList<>();
        Student student = new Student();

        if (!jmenoTextField.getText().equals("")) {
            student.setJmeno(jmenoTextField.getText());
        } else {
            alerts.add("Jméno");
        }
        if (!prijmeniTextField.getText().equals("")) {
            student.setPrijmeni(prijmeniTextField.getText());
        } else {
            alerts.add("Příjmení");
        }
        if (!rodneCisloTextField.getText().equals("")) {
            student.setRodneCislo(rodneCisloTextField.getText());
        } else {
            alerts.add("Rodné číslo");
        }
        student.setTitul(titulTextField.getText());
        if (!datumNarozeniTextField.getText().equals("")) {
            student.setDatumNarozeni(datumNarozeniTextField.getText());
        } else {
            alerts.add("Datum narození");
        }
        if (!pohlaviTextField.getText().equals("")) {
            student.setPohlavi(Pohlavi.getPohlavi(pohlaviTextField.getText()));
        } else {
            alerts.add("Pohlaví");
        }
        if (!statniPrislusnostTextField.getText().equals("")) {
            student.setStatniPrislusnost(statniPrislusnostTextField.getText());
        } else {
            alerts.add("Státní příslušnost");
        }
        if (!emailTextField.getText().equals("")) {
            student.setEmail(emailTextField.getText());
        } else {
            alerts.add("Emailová adresa");
        }
        student.setTelefon(telefonTextField.getText());
        if (!zahranicniTextField.getText().equals("")) {
            boolean zahranicni;
            if (zahranicniTextField.getText().trim().equals("1")) {
                zahranicni = true;
            } else {
                zahranicni = false;
            }
            student.setZahranicni(zahranicni);
        } else {
            alerts.add("Zahraniční student");
        }
        if (!xnameTextField.getText().equals("")) {
            if (student.isZahranicni()) {
                student.setXname(null);
            } else {
                student.setXname(xnameTextField.getText());
            }
        } else {
            alerts.add("Xname");
        }
        student.setAdmin(false);

        Adresa adresa = new Adresa();
        if (!mestoTextField.getText().equals("")) {
            adresa.setMesto(mestoTextField.getText());
        } else {
            alerts.add("Město");
        }
        if (!uliceTextField.getText().equals("")) {
            adresa.setUlice(uliceTextField.getText());
        } else {
            alerts.add("Název");
        }
        if (!statTextField.getText().equals("")) {
            adresa.setStat(statTextField.getText());
        } else {
            alerts.add("Stát");
        }
        if (!cisloPopisneTextField.getText().equals("")) {
            adresa.setCisloPopisne(Integer.parseInt(cisloPopisneTextField.getText()));
        } else {
            alerts.add("Číslo");
        }

        adresa.setStudent(student);

        student.setAdresa(adresa);

        try {
            if (!hesloPasswordField.getText().equals("")) {
                student.setHeslo(authService.createMD5Hash(hesloPasswordField.getText()));
            } else {
                alerts.add("Heslo");
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        if (alerts.size() == 0) {
            studentService.saveStudent(student);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Uživatel byl úspěšně uložen!");
            alert.showAndWait();
        } else {
            String alertString = "Musíte vyplnit pole:\n";
            for (int i = 0; i < alerts.size(); i++) {
                if (i == alerts.size() - 1) {
                    alertString += alerts.get(i);
                } else {
                    alertString += alerts.get(i) + ", ";
                }
            }
            Alert alert = new Alert(Alert.AlertType.ERROR, alertString);
            alert.showAndWait();
        }
    }

    public void backButtonAction(ActionEvent event) {
        proceedToNextPage("graphics/fxml/signPostAdmin.fxml", rootPane);
    }
}
