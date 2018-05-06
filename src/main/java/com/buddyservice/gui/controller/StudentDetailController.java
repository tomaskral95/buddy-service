package com.buddyservice.gui.controller;

import com.buddyservice.domain.Student;
import com.buddyservice.gui.Main;
import com.buddyservice.service.IStudentService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import org.springframework.context.ApplicationContext;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class StudentDetailController
        extends SwitchableController
        implements Initializable {

    private ApplicationContext applicationContext = Main.applicationContext;

    @FXML
    public AnchorPane rootPane;

    @FXML
    public Label xnameLabel;

    @FXML
    public Label jmenoLabel;

    @FXML
    public Label prijmeniLabel;

    @FXML
    public Label titulLabel;

    @FXML
    public Label datumNarizeniLabel;

    @FXML
    public Label pohlaviLabel;

    @FXML
    public Label statniPrislusnostLabel;

    @FXML
    public Label telefonLabel;

    @FXML
    public Label mailLabel;

    @FXML
    public Label statLabel;

    @FXML
    public Label mestoLabel;

    @FXML
    public Label uliceLabel;

    @FXML
    public Label cisloPopisneLabel;

    @FXML
    public TextField rodneCisloTextField;

    @FXML
    public Label findStatusLabel;

    @FXML
    public ComboBox studentListComboBox;

    @FXML
    public ImageView photoImageView;

    @FXML
    public void findButtonAction(ActionEvent event) {
        clearAllLabels();
        IStudentService studentService = (IStudentService) applicationContext.getBean("studentService");
        Student foundStudent = null;

        if (rodneCisloTextField.getText().equals("")) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Musíš zadat rodné číslo studenta!");
            alert.showAndWait();
        } else {
            foundStudent = studentService.findStudent(rodneCisloTextField.getText().trim());
        }
        if (foundStudent == null) {
            findStatusLabel.setText("Student nebyl nalezen");
            return;
        }

        findStatusLabel.setText("Úspěšně vyhledáno");


        xnameLabel.setText(foundStudent.getXname());
        jmenoLabel.setText(foundStudent.getJmeno());
        prijmeniLabel.setText(foundStudent.getPrijmeni());
        titulLabel.setText(foundStudent.getTitul());
        datumNarizeniLabel.setText(foundStudent.getDatumNarozeni());
        pohlaviLabel.setText(foundStudent.getPohlavi().toString());
        statniPrislusnostLabel.setText(foundStudent.getStatniPrislusnost());
        telefonLabel.setText(foundStudent.getTelefon());
        mailLabel.setText(foundStudent.getEmail());
        if (foundStudent.getAdresa() != null) {
            statLabel.setText(foundStudent.getAdresa().getStat());
            mestoLabel.setText(foundStudent.getAdresa().getMesto());
            uliceLabel.setText(foundStudent.getAdresa().getUlice());
            cisloPopisneLabel.setText(String.valueOf(foundStudent.getAdresa().getCisloPopisne()));
        }

    }

    @FXML
    public void backButtonAction(ActionEvent event) {
        proceedToNextPage("graphics/fxml/signPostAdmin.fxml", rootPane);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        clearAllLabels();
        IStudentService studentService = (IStudentService) applicationContext.getBean("studentService");

        findStatusLabel.setText("");
        List<Student> students = studentService.findAllStudents();
        List<String> comboInformations = new ArrayList<>();
        for (Student student : students) {
            if (student.getRodneCislo() != null && student.getJmeno() != null && student.getPrijmeni() != null) {
                comboInformations.add(student.getJmeno() + " " + student.getPrijmeni() + " - " + student.getRodneCislo());
            }
        }

        studentListComboBox.getItems().addAll(comboInformations);
    }

    private void clearAllLabels() {
        findStatusLabel.setText("");
        xnameLabel.setText("");
        jmenoLabel.setText("");
        prijmeniLabel.setText("");
        titulLabel.setText("");
        datumNarizeniLabel.setText("");
        pohlaviLabel.setText("".toString());
        statniPrislusnostLabel.setText("");
        telefonLabel.setText("");
        mailLabel.setText("");
        statLabel.setText("");
        mestoLabel.setText("");
        uliceLabel.setText("");
        cisloPopisneLabel.setText("");
    }
}