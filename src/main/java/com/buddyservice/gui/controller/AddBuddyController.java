package com.buddyservice.gui.controller;

import com.buddyservice.domain.Student;
import com.buddyservice.gui.Main;
import com.buddyservice.service.IStudentService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.springframework.context.ApplicationContext;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class AddBuddyController
        extends SwitchableController
        implements Initializable {

    private ApplicationContext applicationContext = Main.applicationContext;

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
    public TableColumn xnameColumn;

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

        rodneCisloColumn.setCellValueFactory(new PropertyValueFactory<>("rodneCislo"));
        xnameColumn.setCellValueFactory(new PropertyValueFactory<>("xname"));
        jmenoColumn.setCellValueFactory(new PropertyValueFactory<>("jmeno"));
        prijmeniColumn.setCellValueFactory(new PropertyValueFactory<>("prijmeni"));
    }

    public void backButtonAction(ActionEvent event) {
    }

    public void addToBuddyButtonAction(ActionEvent event) {
    }

    public void buddyhoStudentiButtonAction(ActionEvent event) {
    }
}
