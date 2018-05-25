package com.buddyservice.gui.controller;

import com.buddyservice.domain.Student;
import com.buddyservice.gui.Main;
import com.buddyservice.service.IStudentService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import org.springframework.context.ApplicationContext;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class AddBuddyController
        extends SwitchableController
        implements Initializable {

    private ApplicationContext applicationContext = Main.applicationContext;

    @FXML
    public AnchorPane rootPane;

    @FXML
    public TableView<Student> tabulkaZahranicni;

    @FXML
    public TableView<Student> tabulkaBuddies;

    @FXML
    public TableColumn rodneCislo1Column;

    @FXML
    public TableColumn xname1Column;

    @FXML
    public TableColumn jmeno1Column;

    @FXML
    public TableColumn prijmeni1Column;

    @FXML
    public TableColumn rodneCisloColumn;

    @FXML
    public TableColumn statniPrislusnost;

    @FXML
    public TableColumn jmenoColumn;

    @FXML
    public TableColumn prijmeniColumn;

    @FXML
    public ComboBox zahranicniComboBox;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        IStudentService studentService = (IStudentService) applicationContext.getBean("studentService");

        List<Student> buddies = studentService.findBuddies();
        ObservableList<Student> buddiesObservableList = FXCollections.observableArrayList();
        buddiesObservableList.addAll(buddies);
        tabulkaBuddies.setItems(buddiesObservableList);

        rodneCislo1Column.setCellValueFactory(new PropertyValueFactory<>("rodneCislo"));
        xname1Column.setCellValueFactory(new PropertyValueFactory<>("xname"));
        jmeno1Column.setCellValueFactory(new PropertyValueFactory<>("jmeno"));
        prijmeni1Column.setCellValueFactory(new PropertyValueFactory<>("prijmeni"));


        List<Student> zahranicniStudenti = studentService.findAllForeignStudents();
        for (Student student : zahranicniStudenti) {
            zahranicniComboBox.getItems().add( student.getJmeno() + " " + student.getPrijmeni() + " - " + student.getRodneCislo());
        }
    }

    public void backButtonAction(ActionEvent event) {
        proceedToNextPage("graphics/fxml/signPostAdmin.fxml", rootPane);
    }

    public void addToBuddyButtonAction(ActionEvent event) {
        IStudentService studentService = (IStudentService) applicationContext.getBean("studentService");
        if (zahranicniComboBox.getValue() != null && tabulkaBuddies.getSelectionModel().getSelectedItem() != null) {
            Student selectedBuddy = tabulkaBuddies.getSelectionModel().getSelectedItem();
            Student selectedForeignStudent = studentService.findStudent(zahranicniComboBox.getValue().toString().split("-")[1].trim());
            if(selectedBuddy.getStudents().stream().noneMatch(student -> student.getRodneCislo().equals(selectedForeignStudent.getRodneCislo()))) {
                selectedBuddy.getStudents().add(selectedForeignStudent);
                selectedForeignStudent.setBuddy(selectedBuddy);
                studentService.saveStudent(selectedForeignStudent);

                tabulkaZahranicni.getItems().add(selectedForeignStudent);

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Úspěšně přiřazen k buddymu!");
                alert.showAndWait();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Tento buddy je již s tímto studentem propojen.");
                alert.showAndWait();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Musíš vybrat zahraničního studenta a zvolit buddyho");
            alert.showAndWait();
        }
    }

    public void buddyhoStudentiButtonAction(ActionEvent event) {
        IStudentService studentService = (IStudentService) applicationContext.getBean("studentService");
        if (tabulkaBuddies.getSelectionModel().getSelectedItem() != null) {
            Student selectedBuddy = studentService.findStudent(tabulkaBuddies.getSelectionModel().getSelectedItem().getRodneCislo());

            ObservableList<Student> foreignStudentsObservableList = FXCollections.observableArrayList();
            foreignStudentsObservableList.addAll(selectedBuddy.getStudents());
            tabulkaZahranicni.setItems(foreignStudentsObservableList);

            rodneCisloColumn.setCellValueFactory(new PropertyValueFactory<>("rodneCislo"));
            statniPrislusnost.setCellValueFactory(new PropertyValueFactory<>("statniPrislusnost"));
            jmenoColumn.setCellValueFactory(new PropertyValueFactory<>("jmeno"));
            prijmeniColumn.setCellValueFactory(new PropertyValueFactory<>("prijmeni"));
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Musíš první zvolit buddyho!");
            alert.showAndWait();
        }



    }

    public void odeberStudentaButtonAction(ActionEvent event) {
        IStudentService studentService = (IStudentService) applicationContext.getBean("studentService");

        if (tabulkaBuddies.getSelectionModel().getSelectedItem() != null && tabulkaZahranicni.getSelectionModel().getSelectedItem() != null) {
            Student selectedBuddy = studentService.findStudent(tabulkaBuddies.getSelectionModel().getSelectedItem().getRodneCislo());
            Student selectedForeignStudent = studentService.findStudent(tabulkaZahranicni.getSelectionModel().getSelectedItem().getRodneCislo());
            selectedBuddy.getStudents().removeIf(student -> student.getRodneCislo().equals(selectedForeignStudent.getRodneCislo()));
            selectedForeignStudent.setBuddy(null);
            studentService.saveStudent(selectedForeignStudent);

            tabulkaZahranicni.getItems().removeIf(student -> student.getRodneCislo().equals(selectedForeignStudent.getRodneCislo()));

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Student úspěšně odebrán!");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Musíš první zvolit buddyho a zahraničního studenta, kterého chceš odstranit");
            alert.showAndWait();
        }
    }
}
