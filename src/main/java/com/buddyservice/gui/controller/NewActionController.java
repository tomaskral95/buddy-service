package com.buddyservice.gui.controller;

import com.buddyservice.domain.Adresa;
import com.buddyservice.domain.Akce;
import com.buddyservice.domain.DruhAkce;
import com.buddyservice.gui.Main;
import com.buddyservice.service.IAkceService;
import com.buddyservice.service.IDruhAkceService;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import org.springframework.context.ApplicationContext;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class NewActionController
        extends SwitchableController
        implements Initializable {

    private ApplicationContext applicationContext = Main.applicationContext;

    @FXML
    public AnchorPane rootPane;

    @FXML
    public TextField datumTextField;

    @FXML
    public TextField casOdTextField;

    @FXML
    public TextField casDoTextField;

    @FXML
    public TextField popisTextField;

    @FXML
    public TextField cenaTextField;

    @FXML
    public TextField maxPocetTextField;

    @FXML
    public TextField statTextField;

    @FXML
    public TextField mestoTextField;

    @FXML
    public TextField uliceTextField;

    @FXML
    public TextField cisloPopisneTextField;

    @FXML
    public ComboBox druhAkceComboBox;

    @FXML
    public void backButtonAction(ActionEvent event) {
        proceedToNextPage("graphics/fxml/signPostAdmin.fxml", rootPane);
    }

    @FXML
    public void saveButtonAction(ActionEvent event) {
        IAkceService akceService = (IAkceService) applicationContext.getBean("akceService");
        IDruhAkceService druhAkceService = (IDruhAkceService) applicationContext.getBean("druhAkceService");
        List<String> alerts = new ArrayList<>();

        Akce akce = new Akce();
        if (!datumTextField.getText().equals("")) {
            akce.setDatum(datumTextField.getText());
        } else {
            alerts.add("Datum");
        }
        if (!casOdTextField.getText().equals("")) {
            akce.setCasOd(casOdTextField.getText());
        } else {
            alerts.add("Čas od");
        }
        if (!casDoTextField.getText().equals("")) {
            akce.setCasDo(casDoTextField.getText());
        } else {
            alerts.add("Čas do");
        }
        if (!popisTextField.getText().equals("")) {
            akce.setPopis(popisTextField.getText());
        } else {
            alerts.add("Popis");
        }
        if (!cenaTextField.getText().equals("")) {
            akce.setCena(Double.parseDouble(cenaTextField.getText()));
        } else {
            alerts.add("Cena");
        }
        if (!druhAkceComboBox.getValue().equals("")){
            for (DruhAkce druhAkce : druhAkceService.getDruhyAkce()) {
                if (druhAkce.getDruh().equals(druhAkceComboBox.getValue())) {
                    DruhAkce druhAkceToSave = new DruhAkce();
                    druhAkceToSave.setDruh(druhAkce.getDruh());
                    druhAkceToSave.getAkce().add(akce);
                    druhAkceToSave.setIdAkce(druhAkce.getIdAkce());
                    akce.setDruhAkce(druhAkceToSave);
                }
            }
        } else {
            alerts.add("Druh akce");
        }

        Adresa adresa = new Adresa();
        if (!mestoTextField.getText().equals("")) {
            adresa.setMesto(mestoTextField.getText());
        } else {
            alerts.add("Město");
        }
        if (!uliceTextField.getText().equals("")) {
            adresa.setUlice(uliceTextField.getText());
        } else {
            alerts.add("Ulice");
        }
        if (!statTextField.getText().equals("")) {
            adresa.setStat(statTextField.getText());
        } else {
            alerts.add("Stát");
        }
        if (!cisloPopisneTextField.getText().equals("")) {
            adresa.setCisloPopisne(Integer.parseInt(cisloPopisneTextField.getText()));
        } else {
            alerts.add("Číslo popisné");
        }
        adresa.getAkce().add(akce);
        akce.setMisto(adresa);
        if (alerts.size() == 0) {
            akceService.saveAkce(akce);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Akce byla úspěšně uložena!");
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        IDruhAkceService druhAkceService = (IDruhAkceService) applicationContext.getBean("druhAkceService");
        List<DruhAkce> druhyAkce = druhAkceService.getDruhyAkce();
        druhAkceComboBox.getItems().addAll(druhyAkce.stream().map(s -> s.getDruh()).collect(Collectors.toList()));
    }
}
