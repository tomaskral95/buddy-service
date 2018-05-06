package com.buddyservice.gui.controller;

import com.buddyservice.domain.Adresa;
import com.buddyservice.domain.Pohlavi;
import com.buddyservice.domain.Student;
import com.buddyservice.gui.Main;
import com.buddyservice.service.IStudentService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

@Controller
public class EditStudentController
        extends SwitchableController
        implements Initializable {

    private ApplicationContext applicationContext = Main.applicationContext;

    @FXML
    public ComboBox studentiComboBox;

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
    public void upravitButtonAction(ActionEvent event) {
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

        if (alerts.size() == 0) {
            studentService.saveStudent(student);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Student byl úspěšně upraven!");
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

    @FXML
    public void backButtonAction(ActionEvent event) {
        proceedToNextPage("graphics/fxml/signPostAdmin.fxml", rootPane);
    }

    @FXML
    public void vyhledatButtonAction(ActionEvent event) {
        IStudentService studentService = (IStudentService) applicationContext.getBean("studentService");
        String rodneCislo = studentiComboBox.getValue().toString().split("-")[1].trim();
        Student foundStudent = studentService.findStudent(rodneCislo);
        if (foundStudent == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Musíš vybrat studenta k vyhledání!");
            alert.showAndWait();
        } else {
            xnameTextField.setText(foundStudent.getXname());
            rodneCisloTextField.setText(foundStudent.getRodneCislo());
            jmenoTextField.setText(foundStudent.getJmeno());
            prijmeniTextField.setText(foundStudent.getPrijmeni());
            titulTextField.setText(foundStudent.getTitul());
            datumNarozeniTextField.setText(foundStudent.getDatumNarozeni());
            pohlaviTextField.setText(foundStudent.getPohlavi().toString());
            statniPrislusnostTextField.setText(foundStudent.getStatniPrislusnost());
            telefonTextField.setText(foundStudent.getTelefon());
            emailTextField.setText(foundStudent.getEmail());
            zahranicniTextField.setText(String.valueOf(foundStudent.isZahranicni()));
            statTextField.setText(foundStudent.getAdresa().getStat());
            mestoTextField.setText(foundStudent.getAdresa().getMesto());
            uliceTextField.setText(foundStudent.getAdresa().getUlice());
            cisloPopisneTextField.setText(String.valueOf(foundStudent.getAdresa().getCisloPopisne()));
        }


    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        IStudentService studentService = (IStudentService) applicationContext.getBean("studentService");

        List<Student> students = studentService.findAllStudents();
        List<String> comboInformations = new ArrayList<>();
        for (Student student : students) {
            if (student.getRodneCislo() != null && student.getJmeno() != null && student.getPrijmeni() != null) {
                comboInformations.add(student.getJmeno() + " " + student.getPrijmeni() + " - " + student.getRodneCislo());
            }
        }
        studentiComboBox.getItems().addAll(comboInformations);
        comboInformations.clear();
    }
}
