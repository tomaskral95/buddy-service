package com.buddyservice.gui.controller;

import com.buddyservice.domain.Akce;
import com.buddyservice.domain.Student;
import com.buddyservice.gui.Main;
import com.buddyservice.service.IAkceService;
import com.buddyservice.service.IStudentService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import org.springframework.context.ApplicationContext;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ActionDetailController
        extends SwitchableController
        implements Initializable {

    private ApplicationContext applicationContext = Main.applicationContext;

    @FXML
    public AnchorPane rootPane;

    @FXML
    public Label nazevLabel;

    @FXML
    public Label casOdLabel;

    @FXML
    public Label casDoLabel;

    @FXML
    public Label mistoLabel;

    @FXML
    public Label popisLabel;

    @FXML
    public Label cenaLabel;

    @FXML
    public Label maxUcastLabel;

    @FXML
    public ComboBox akceComboBox;

    @FXML
    public Label findStatusLabel;

    @FXML
    public TextField idAkceTextField;

    @FXML
    public void findButtonAction(ActionEvent event) {
        clearAllLabels();
        IAkceService akceService = (IAkceService) applicationContext.getBean("akceService");
        Akce foundAkce = null;

        if (idAkceTextField.getText().equals("")) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Musíš zadat ID akce!");
            alert.showAndWait();
        } else {
            foundAkce = akceService.findAkceById(Long.valueOf(idAkceTextField.getText()));
        }
        if (foundAkce == null) {
            findStatusLabel.setText("Akce nebyla nalezena");
            return;
        }

        findStatusLabel.setText("Úspěšně vyhledáno");

        nazevLabel.setText(foundAkce.getNazev());
        casOdLabel.setText(foundAkce.getCasDo());
        casDoLabel.setText(foundAkce.getCasDo());
        if (foundAkce.getMisto() != null) {
            mistoLabel.setText(
                    foundAkce.getMisto().getUlice() + " "
                            + foundAkce.getMisto().getCisloPopisne() + ", "
                            + foundAkce.getMisto().getMesto()
            );
        }
        popisLabel.setText(foundAkce.getPopis());
        cenaLabel.setText(String.valueOf(foundAkce.getCena()));
        maxUcastLabel.setText(String.valueOf(foundAkce.getKapacita()));
    }

    @FXML
    public void backButtonAction(ActionEvent event) {
        proceedToNextPage("graphics/fxml/signPostAdmin.fxml", rootPane);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        clearAllLabels();
        IAkceService akceService = (IAkceService) applicationContext.getBean("akceService");

        findStatusLabel.setText("");
        List<Akce> akce = akceService.findAll();
        List<String> comboInformations = new ArrayList<>();
        for (Akce a : akce) {
            if (a.getNazev() != null && a.getIdAkce() != null) {
                comboInformations.add(a.getNazev() + " - " + a.getIdAkce());
            }
        }

        akceComboBox.getItems().addAll(comboInformations);
    }

    private void clearAllLabels() {
        findStatusLabel.setText("");
        nazevLabel.setText("");
        casOdLabel.setText("");
        casDoLabel.setText("");
        mistoLabel.setText("");
        popisLabel.setText("");
        cenaLabel.setText("");
        maxUcastLabel.setText("");
    }
}
