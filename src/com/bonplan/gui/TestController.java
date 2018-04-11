/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bonplan.gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author souab
 */
public class TestController implements Initializable {

    @FXML
    private AnchorPane gestion;
    @FXML
    private AnchorPane reclamations;
    @FXML
    private AnchorPane reclamations1;
    @FXML
    private AnchorPane reclamations12;
    @FXML
    private AnchorPane reclamations121;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void ShowAllEvents(ActionEvent event) throws IOException {
               load("ShowAllEvents");

    }

    @FXML
    private void AjoutEvent(MouseEvent event) throws IOException {
                load("ajoutEvent");

    }

    @FXML
    private void ListerEvent(MouseEvent event) throws IOException {
                load("ListerEvent");

    }

    @FXML
    private void mesParticipations(ActionEvent event) throws IOException {
                    load("MyParticipations");

    }

    private void load(String ui) throws IOException {
        Parent root = null;
        root = FXMLLoader.load(getClass().getResource(ui + ".fxml"));
        //  borderpane.setCenter(root);
        gestion.getChildren().setAll(root);
    }

}
