package com.buddyservice.gui.controller;

import com.buddyservice.domain.Akce;
import com.buddyservice.domain.Student;
import com.buddyservice.gui.Main;
import com.buddyservice.service.IAkceService;
import com.buddyservice.service.IStudentService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import org.springframework.context.ApplicationContext;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;

public class DisplayActionsOfStudents extends SwitchableController implements Initializable {

    private ApplicationContext applicationContext = Main.applicationContext;

    @FXML
    public TableColumn rodneCisloColumn;

    @FXML
    public TableColumn xnameColumn;

    @FXML
    public TableColumn jmenoColumn;

    @FXML
    public TableColumn prijmeniColumn;

    @FXML
    public TableColumn idColumn;

    @FXML
    public TableColumn nazevColumn;

    @FXML
    public TableColumn popisColumn;

    @FXML
    public TableColumn cenaColumn;

    @FXML
    public TableColumn kapacitaColumn;

    @FXML
    public TableColumn volnychMistColumn;

    @FXML
    public AnchorPane rootPane;

    @FXML
    public TableView<Akce> tabulkaAkce;

    @FXML
    public TableView<Student> tabulkaStudenti;

    @FXML
    public void backButtonAction(ActionEvent event) {
        proceedToNextPage("graphics/fxml/signPostAdmin.fxml", rootPane);
    }

    @FXML
    public void registrovaniStudentiButtonAction(ActionEvent event) {
        tabulkaStudenti.getItems().clear();
        IAkceService akceService = (IAkceService) applicationContext.getBean("akceService");
        Akce selectedAkce = tabulkaAkce.getSelectionModel().getSelectedItem();
        Set<Student> students = akceService.findAkceById(selectedAkce.getIdAkce()).getStudenti();
        ObservableList<Student> studentsObservableList = FXCollections.observableArrayList();
        studentsObservableList.addAll(students);

        tabulkaStudenti.setItems(studentsObservableList);
        rodneCisloColumn.setCellValueFactory(new PropertyValueFactory<>("rodneCislo"));
        xnameColumn.setCellValueFactory(new PropertyValueFactory<>("xname"));
        jmenoColumn.setCellValueFactory(new PropertyValueFactory<>("jmeno"));
        prijmeniColumn.setCellValueFactory(new PropertyValueFactory<>("prijmeni"));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        IAkceService akceService = (IAkceService) applicationContext.getBean("akceService");
        List<Akce> vsechnyAkce = akceService.findAll();
        ObservableList<Akce> akceObservableList = FXCollections.observableArrayList();
        akceObservableList.addAll(vsechnyAkce);

        tabulkaAkce.setItems(akceObservableList);
        idColumn.setCellValueFactory(new PropertyValueFactory<>("idAkce"));
        nazevColumn.setCellValueFactory(new PropertyValueFactory<>("nazev"));
        popisColumn.setCellValueFactory(new PropertyValueFactory<>("popis"));
        cenaColumn.setCellValueFactory(new PropertyValueFactory<>("cena"));
        kapacitaColumn.setCellValueFactory(new PropertyValueFactory<>("kapacita"));
        volnychMistColumn.setCellValueFactory(new PropertyValueFactory<>("volnychMist"));
    }

    @FXML
    public void vymazStudentButtonAction(ActionEvent event) {
        IStudentService studentService = (IStudentService) applicationContext.getBean("studentService");
        Student selectedStudent = tabulkaStudenti.getSelectionModel().getSelectedItem();
        Student foundStudent = studentService.findStudent(selectedStudent.getRodneCislo());
        Akce selectedAkce = tabulkaAkce.getSelectionModel().getSelectedItem();
        foundStudent.getAkce().removeIf(akce -> akce.getIdAkce() == selectedAkce.getIdAkce());
        studentService.saveStudent(foundStudent);
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Student byl z akce úspěšně odebrán");
        alert.showAndWait();
        tabulkaStudenti.getItems().removeIf(student -> student.getRodneCislo().equals(foundStudent.getRodneCislo()));
    }
}
