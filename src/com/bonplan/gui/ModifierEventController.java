/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bonplan.gui;

import com.bonplan.entities.Evenement;
import com.bonplan.entities.User;
import com.bonplan.services.EvenementService;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author souab
 */
public class ModifierEventController implements Initializable {
    Evenement e;

    public ModifierEventController(Evenement e) {
        this.e = e;
    }

    @FXML
    private TextField categorie;
    @FXML
    private TextField nbreplace;
    @FXML
    private TextField description;
    @FXML
    private TextField nomEvent;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
categorie.setText(e.getCategorie());
nbreplace.setText(Integer.toString(e.getNbrplace()));
description.setText(e.getDescription());
nomEvent.setText(e.getNomEvenement());
// TODO
    }    

    @FXML
    private void UpdateEvent(ActionEvent event) {
                EvenementService es = new EvenementService();
es.update(new Evenement(e.id,categorie.getText(), Integer.parseInt(nbreplace.getText()), e.getDate_evenement(), e.getLieu(), description.getText(), nomEvent.getText(), User.getUserconnected(),e.getPhoto(), e.getLat(), e.getLng(), e.getPrix()));
   
            System.out.println("Mod avec sucess");
}
    
}
