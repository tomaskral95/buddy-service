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

public class EditActionController
        extends SwitchableController
        implements Initializable {

    private ApplicationContext applicationContext = Main.applicationContext;

    private Long idAkce;

    @FXML
    public ComboBox vsechnyAkceComboBox;

    @FXML
    public AnchorPane rootPane;

    @FXML
    public TextField nazevTextField;

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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        IAkceService akceService = (IAkceService) applicationContext.getBean("akceService");

        List<Akce> akce = akceService.findAll();
        List<String> comboInformations = new ArrayList<>();
        for (Akce a : akce) {
            if (a.getNazev() != null && a.getIdAkce() != null) {
                comboInformations.add(a.getNazev() + " - " + a.getIdAkce());
            }
        }
        vsechnyAkceComboBox.getItems().addAll(comboInformations);
    }

    @FXML
    public void backButtonAction(ActionEvent event) {
        proceedToNextPage("graphics/fxml/signPostAdmin.fxml", rootPane);
    }

    @FXML
    public void editButtonAction(ActionEvent event) {
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

        if (akceService.findAkceById(idAkce) != null) {
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
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Tato akce neexistuje, nelze ji tak upravit!");
            alert.showAndWait();
        }

    }

    public void vyhledatButtonAction(ActionEvent event) {
        if (vsechnyAkceComboBox.getValue() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Musíš první vybrat akci!");
            alert.showAndWait();
        } else {
            Long idAkce = Long.valueOf(vsechnyAkceComboBox.getValue().toString().split("-")[1].trim());
            this.idAkce = idAkce;
            IAkceService akceService = (IAkceService) applicationContext.getBean("akceService");
            Akce foundAkce = akceService.findAkceById(idAkce);

            nazevTextField.setText(foundAkce.getNazev());
            datumTextField.setText(foundAkce.getDatum());
            casOdTextField.setText(foundAkce.getCasOd());
            casDoTextField.setText(foundAkce.getCasDo());
            popisTextField.setText(foundAkce.getPopis());
            cenaTextField.setText(String.valueOf(foundAkce.getCena()));
            maxPocetTextField.setText(String.valueOf(foundAkce.getKapacita()));
            statTextField.setText(foundAkce.getMisto().getStat());
            mestoTextField.setText(foundAkce.getMisto().getMesto());
            uliceTextField.setText(foundAkce.getMisto().getUlice());
            cisloPopisneTextField.setText(String.valueOf(foundAkce.getMisto().getCisloPopisne()));
            druhAkceComboBox.setValue(foundAkce.getDruhAkce().getDruh());
        }
    }
}
