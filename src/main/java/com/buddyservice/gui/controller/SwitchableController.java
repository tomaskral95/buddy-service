package com.buddyservice.gui.controller;

import com.buddyservice.gui.Main;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

/**
 * Abstraktní třída, které je každý Controller potomkem, umožňuje jednoduché použití metody pro přepnutí mezi okny aplikace.
 */
abstract class SwitchableController {

    /**
     * Metoda načte AnchorPane nového okna, nastaví dle něho velikost dosavadního stage a následně přehraje obsah
     * již existující stage novým.
     * @param url
     * @param rootPane
     */
    void proceedToNextPage(String url, AnchorPane rootPane) {
        AnchorPane newAnchorPane = null;
        try {
            newAnchorPane = FXMLLoader.load(getClass().getClassLoader().getResource(url));
            Main.stage.setWidth(newAnchorPane.getPrefWidth());
            Main.stage.setHeight(newAnchorPane.getPrefHeight());
        } catch (IOException e) {
            e.printStackTrace();
        }
        rootPane.getChildren().setAll(newAnchorPane);
    }

}
