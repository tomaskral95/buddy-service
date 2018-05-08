package com.buddyservice.gui.controller;

import com.buddyservice.domain.Akce;
import com.buddyservice.gui.Main;
import com.buddyservice.service.IAkceService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import org.springframework.context.ApplicationContext;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ShowAllActionsController
        extends SwitchableController
        implements Initializable {

    private ApplicationContext applicationContext = Main.applicationContext;

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
    public void backButtonAction(ActionEvent event) {
        proceedToNextPage("graphics/fxml/signPostStudent.fxml", rootPane);
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
}
