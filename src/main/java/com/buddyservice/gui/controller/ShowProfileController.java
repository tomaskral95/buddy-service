package com.buddyservice.gui.controller;

import com.buddyservice.domain.Student;
import com.buddyservice.gui.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import org.hibernate.boot.jaxb.hbm.spi.JaxbHbmFetchStyleWithSubselectEnum;
import org.springframework.context.ApplicationContext;

import java.net.URL;
import java.util.ResourceBundle;

public class ShowProfileController
        extends SwitchableController
        implements Initializable {

    private ApplicationContext applicationContext = Main.applicationContext;

    @FXML
    private AnchorPane rootPane;

    @FXML
    private Label xnameLabel;

    @FXML
    private Label jmenoLabel;

    @FXML
    private Label prijmeniLabel;

    @FXML
    private Label titulLabel;

    @FXML
    private Label datumNarizeniLabel;

    @FXML
    private Label pohlaviLabel;

    @FXML
    private Label statniPrislusnostLabel;

    @FXML
    private Label telefonLabel;

    @FXML
    private Label mailLabel;

    @FXML
    private Label statLabel;

    @FXML
    private Label mestoLabel;

    @FXML
    private Label uliceLabel;

    @FXML
    private Label cisloPopisneLabel;

    @FXML
    private Label rodneCisloLabel;


    public void backButtonAction(ActionEvent event) {
        proceedToNextPage("graphics/fxml/signPostStudent.fxml", rootPane);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Student loggedStudent = Main.getLoggedStudent();
        xnameLabel.setText(loggedStudent.getXname());
        rodneCisloLabel.setText(loggedStudent.getRodneCislo());
        jmenoLabel.setText(loggedStudent.getJmeno());
        prijmeniLabel.setText(loggedStudent.getPrijmeni());
        titulLabel.setText(loggedStudent.getTitul());
        datumNarizeniLabel.setText(loggedStudent.getDatumNarozeni());
        pohlaviLabel.setText(loggedStudent.getPohlavi().toString());
        statniPrislusnostLabel.setText(loggedStudent.getStatniPrislusnost());
        telefonLabel.setText(loggedStudent.getTelefon());
        mailLabel.setText(loggedStudent.getEmail());
        if (loggedStudent.getAdresa() != null) {
            statLabel.setText(loggedStudent.getAdresa().getStat());
            mestoLabel.setText(loggedStudent.getAdresa().getMesto());
            uliceLabel.setText(loggedStudent.getAdresa().getUlice());
            cisloPopisneLabel.setText(String.valueOf(loggedStudent.getAdresa().getCisloPopisne()));
        }
    }
}
