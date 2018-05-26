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
    private AnchorPane rootPane;

    @FXML
    private TextField xnameTextField;

    @FXML
    private TextField jmenoTextField;

    @FXML
    private TextField prijmeniTextField;

    @FXML
    private TextField titulTextField;

    @FXML
    private TextField datumNarozeniTextField;

    @FXML
    private TextField pohlaviTextField;

    @FXML
    private TextField statniPrislusnostTextField;

    @FXML
    private TextField telefonTextField;

    @FXML
    private TextField emailTextField;

    @FXML
    private TextField zahranicniTextField;

    @FXML
    private TextField statTextField;

    @FXML
    private TextField mestoTextField;

    @FXML
    private TextField uliceTextField;

    @FXML
    private TextField cisloPopisneTextField;

    @FXML
    private TextField rodneCisloTextField;

    @FXML
    private PasswordField hesloPasswordField;

    @FXML
    public void saveButtonAction(ActionEvent event) {
        IAuthService authService = (IAuthService) applicationContext.getBean("authService");
        IStudentService studentService = (IStudentService) applicationContext.getBean("studentService");
        List<String> alerts = new ArrayList<>();
        Student student = new Student();

        if (!jmenoTextField.getText().equals("") && InputChecker.checkStringWithoutNumbers(jmenoTextField.getText())) {
            student.setJmeno(jmenoTextField.getText());
        } else {
            alerts.add("Jméno není vyplněno, nebo je ve špatném formátu");
        }
        if (!prijmeniTextField.getText().equals("") && InputChecker.checkStringWithoutNumbers(prijmeniTextField.getText())) {
            student.setPrijmeni(prijmeniTextField.getText());
        } else {
            alerts.add("Příjmení není vyplněno, nebo je ve špatném formátu");
        }
        if (!rodneCisloTextField.getText().equals("") && InputChecker.checkRodneCislo(rodneCisloTextField.getText())) {
            student.setRodneCislo(rodneCisloTextField.getText());
        } else {
            alerts.add("Rodné číslo není vyplněno, nebo je ve špatném formátu");
        }
        student.setTitul(titulTextField.getText());
        if (!datumNarozeniTextField.getText().equals("") && InputChecker.checkDate(datumNarozeniTextField.getText())) {
            student.setDatumNarozeni(datumNarozeniTextField.getText());
        } else {
            alerts.add("Datum narození není vyplněno, nebo je ve špatném formátu");
        }
        if (!pohlaviTextField.getText().equals("") && InputChecker.checkStringWithoutNumbers(pohlaviTextField.getText())) {
            student.setPohlavi(Pohlavi.getPohlavi(pohlaviTextField.getText()));
        } else {
            alerts.add("Pohlaví není vyplněno, nebo je ve špatném formátu");
        }
        if (!statniPrislusnostTextField.getText().equals("") && InputChecker.checkStringWithoutNumbers(statniPrislusnostTextField.getText())) {
            student.setStatniPrislusnost(statniPrislusnostTextField.getText());
        } else {
            alerts.add("Státní příslušnost není vyplněna, nebo je ve špatném formátu");
        }
        if (!emailTextField.getText().equals("") && InputChecker.checkEmail(emailTextField.getText())) {
            student.setEmail(emailTextField.getText());
        } else {
            alerts.add("Emailová adresa není vyplněna, nebo je ve špatném formátu");
        }

        if (!telefonTextField.getText().equals("")) {
            if (InputChecker.checkPhoneNumber(telefonTextField.getText())) {

                student.setTelefon(telefonTextField.getText());
            } else {
                alerts.add("Telefon je ve špatném formátu");
            }
        }
        if (!zahranicniTextField.getText().equals("")) {
            boolean zahranicni;
            if (zahranicniTextField.getText().trim().equals("1")) {
                zahranicni = true;
            } else {
                zahranicni = false;
            }
            student.setZahranicni(zahranicni);
        } else {
            alerts.add("Hodnota, zda je student zahraniční není vyplněna, nebo není ve správném formátu");
        }
        if (!xnameTextField.getText().equals("") && InputChecker.checkStringWithoutNumbers(xnameTextField.getText())) {
            if (student.isZahranicni()) {
                student.setXname(null);
            } else {
                student.setXname(xnameTextField.getText());
            }
        } else {
            alerts.add("Xname není vyplněno, nebo je ve špatném formátu");
        }
        student.setAdmin(false);

        Adresa adresa = new Adresa();
        if (!mestoTextField.getText().equals("") && InputChecker.checkStringWithoutNumbers(mestoTextField.getText())) {
            adresa.setMesto(mestoTextField.getText());
        } else {
            alerts.add("Město není vyplněno, nebo je ve špatném formátu");
        }
        if (!uliceTextField.getText().equals("") && InputChecker.checkStringWithoutNumbers(uliceTextField.getText())) {
            adresa.setUlice(uliceTextField.getText());
        } else {
            alerts.add("Ulice není vyplněna, nebo je ve špatném formátu");
        }
        if (!statTextField.getText().equals("") && InputChecker.checkStringWithoutNumbers(statniPrislusnostTextField.getText())) {
            adresa.setStat(statTextField.getText());
        } else {
            alerts.add("Stát není vyplněn, nebo je ve špatném formátu");
        }
        if (!cisloPopisneTextField.getText().equals("") && InputChecker.isNumber(cisloPopisneTextField.getText())) {
            adresa.setCisloPopisne(Integer.parseInt(cisloPopisneTextField.getText()));
        } else {
            alerts.add("Číslo popisné není vyplněno, nebo je ve špatném formátu");
        }

        adresa.setStudent(student);
        student.setAdresa(adresa);

        try {
            if (!hesloPasswordField.getText().equals("") && InputChecker.checkPassword(hesloPasswordField.getText())) {
                student.setHeslo(authService.createMD5Hash(hesloPasswordField.getText()));
            } else {
                alerts.add("Heslo není vyplněno, nebo není ve správném formátu");
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        if (alerts.size() == 0) {
            studentService.saveStudent(student);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Student byl úspěšně uložen!");
            alert.showAndWait();
        } else {
            String alertString = "Chyby:\n";
            for (int i = 0; i < alerts.size(); i++) {
                if (i == alerts.size() - 1) {
                    alertString += alerts.get(i);
                } else {
                    alertString += alerts.get(i) + "\n";
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
