package com.buddyservice.gui.controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

abstract class SwitchableController {

    void proceedToNextPage(String url, AnchorPane rootPane) {
        AnchorPane newAnchorPane = null;
        try {
            newAnchorPane = FXMLLoader.load(getClass().getClassLoader().getResource(url));
        } catch (IOException e) {
            e.printStackTrace();
        }
        rootPane.getChildren().setAll(newAnchorPane);
    }

}
