/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bonplan.gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

/**
 * FXML Controller class
 *
 * @author souab
 */
public class Home2Controller implements Initializable {

    @FXML
    private Button Event;

    
    @FXML
    private BorderPane borderpane;
        @FXML
    private AnchorPane centerpane;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void EventHome(MouseEvent event) throws IOException {
        load("GestionEvent");
    }
    
    private void load(String ui) throws IOException{
        Parent root= null;
               root= FXMLLoader.load(getClass().getResource(ui+".fxml"));
      //  borderpane.setCenter(root);
        centerpane.getChildren().setAll(root);
        
    }
}
