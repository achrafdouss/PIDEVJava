/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bonplan.gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author bouyo
 */
public class AllProductFXMLController implements Initializable {

    @FXML
    private AnchorPane winodow;
    @FXML
    private Pagination paginator;
    @FXML
    private AnchorPane details;
    @FXML
    private ImageView photo;
    @FXML
    private Label dateDep;
    @FXML
    private Text descriptiond;
    @FXML
    private Label Agenced;
    @FXML
    private Label categoried;
    @FXML
    private Label Nbr_place;
    @FXML
    private Label prix;
    @FXML
    private DatePicker DateDepd;
    @FXML
    private DatePicker dateArrivd;
    @FXML
    private AnchorPane box;
    @FXML
    private ImageView image;
    @FXML
    private Label categorie;
    @FXML
    private Label nom;
    @FXML
    private Text prix3;
    @FXML
    private Button favori;
    @FXML
    private Button modifier;
    @FXML
    private Label stock;
    @FXML
    private Label description;
    @FXML
    private Button supprimer;
    @FXML
    private AnchorPane box2;
    @FXML
    private ImageView image2;
    @FXML
    private Label categorie2;
    @FXML
    private Label nom2;
    @FXML
    private Text prix2;
    @FXML
    private Button fovori2;
    @FXML
    private Button modifier2;
    @FXML
    private Text stock2;
    @FXML
    private Text description2;
    @FXML
    private Button Sypprimer2;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void annuler(ActionEvent event) {
    }

    @FXML
    private void Modifier(ActionEvent event) {
    }

    @FXML
    private void supprimer(ActionEvent event) {
    }

    @FXML
    private void Back(ActionEvent event) {
    }
    
}
