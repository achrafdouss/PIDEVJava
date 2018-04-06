/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bonplan.gui;

import com.bonplan.entities.Produit;
import com.bonplan.services.ProduitService;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author bouyo
 */
public class UpdateProduitFXMLController implements Initializable {

    @FXML
    private Label nom11;
    @FXML
    private JFXButton upload;
    @FXML
    private JFXTextField NomInsertion;
    @FXML
    private JFXTextField stockInsertion;
    @FXML
    private JFXTextField PrixInsertion;
    @FXML
    private ChoiceBox<?> choixInsertion;
    @FXML
    private JFXTextArea descriptionInsertion;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
       
    }    

    @FXML
    private void fileChoosing(ActionEvent event) {
    }

    @FXML
    private void actionInsertion2(ActionEvent event) {
    }
    
}
