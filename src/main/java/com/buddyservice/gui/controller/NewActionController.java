package com.buddyservice.gui.controller;

import com.buddyservice.domain.Adresa;
import com.buddyservice.domain.Akce;
import com.buddyservice.domain.DruhAkce;
import com.buddyservice.gui.Main;
import com.buddyservice.service.IAdresaService;
import com.buddyservice.service.IAkceService;
import com.buddyservice.service.IDruhAkceService;
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
    private AnchorPane rootPane;

    @FXML
    private TextField nazevTextField;

    @FXML
    private TextField datumTextField;

    @FXML
    private TextField casOdTextField;

    @FXML
    private TextField casDoTextField;

    @FXML
    private TextField popisTextField;

    @FXML
    private TextField cenaTextField;

    @FXML
    private TextField maxPocetTextField;

    @FXML
    private TextField statTextField;

    @FXML
    private TextField mestoTextField;

    @FXML
    private TextField uliceTextField;

    @FXML
    private TextField cisloPopisneTextField;

    @FXML
    private ComboBox druhAkceComboBox;

    @FXML
    public void backButtonAction(ActionEvent event) {
        proceedToNextPage("graphics/fxml/signPostAdmin.fxml", rootPane);
    }

    @FXML
    public void saveButtonAction(ActionEvent event) {
        IAkceService akceService = (IAkceService) applicationContext.getBean("akceService");
        IDruhAkceService druhAkceService = (IDruhAkceService) applicationContext.getBean("druhAkceService");
        IAdresaService adresaService = (IAdresaService) applicationContext.getBean("adresaService");

        List<String> alerts = new ArrayList<>();

        Akce akce = new Akce();
        if (!nazevTextField.getText().equals("")) {
            akce.setNazev(nazevTextField.getText());
        } else {
            alerts.add("Název není vyplněn");
        }
        if (!datumTextField.getText().equals("") && InputChecker.checkDate(datumTextField.getText())) {
            akce.setDatum(datumTextField.getText());
        } else {
            alerts.add("Datum není vyplněno, nebo je ve špatném formátu");
        }
        if (!casOdTextField.getText().equals("") && InputChecker.checkTime(casOdTextField.getText())) {
            akce.setCasOd(casOdTextField.getText());
        } else {
            alerts.add("Čas od není vyplněn, nebo je ve špatném formátu");
        }
        if (!casDoTextField.getText().equals("") && InputChecker.checkTime(casDoTextField.getText())) {
            akce.setCasDo(casDoTextField.getText());
        } else {
            alerts.add("Čas do není vyplněn, nebo je ve špatném formátu");
        }
        if (!popisTextField.getText().equals("")) {
            akce.setPopis(popisTextField.getText());
        } else {
            alerts.add("Popis musí být vyplněn");
        }
        if (!cenaTextField.getText().equals("") && InputChecker.checkDouble(cenaTextField.getText())) {
            akce.setCena(Double.parseDouble(cenaTextField.getText()));
        } else {
            alerts.add("Cena není vyplněna, nebo je ve špatném formátu");
        }
        if (druhAkceComboBox.getValue() != null) {
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
            alerts.add("Druh akce není vyplněn");
        }
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
        if (!statTextField.getText().equals("") && InputChecker.checkStringWithoutNumbers(statTextField.getText())) {
            adresa.setStat(statTextField.getText());
        } else {
            alerts.add("Stát není vyplněn, nebo je ve špatném formátu");
        }
        if (!cisloPopisneTextField.getText().equals("") && InputChecker.isNumber(cisloPopisneTextField.getText())) {
            adresa.setCisloPopisne(Integer.parseInt(cisloPopisneTextField.getText()));
        } else {
            alerts.add("Číslo popisné není vyplněno, nebo je ve špatném formátu");
        }
        if (!maxPocetTextField.getText().equals("") && InputChecker.isNumber(maxPocetTextField.getText())) {
            akce.setKapacita(Integer.parseInt(maxPocetTextField.getText()));
        } else {
            alerts.add("Číslo popisné není vyplněno, nebo je ve špatném formátu");
        }
        adresa.getAkce().add(akce);
        akce.setMisto(adresa);
        if (alerts.size() == 0) {
            adresaService.saveAdresa(adresa);
            akceService.saveAkce(akce);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Akce byla úspěšně uložena!");
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        IDruhAkceService druhAkceService = (IDruhAkceService) applicationContext.getBean("druhAkceService");
        List<DruhAkce> druhyAkce = druhAkceService.getDruhyAkce();
        druhAkceComboBox.getItems().addAll(druhyAkce.stream().map(s -> s.getDruh()).collect(Collectors.toList()));
    }
}
