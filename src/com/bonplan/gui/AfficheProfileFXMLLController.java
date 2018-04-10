/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bonplan.gui;

import com.bonplan.entities.User;
import com.bonplan.services.UserServices;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Achraf
 */
public class AfficheProfileFXMLLController extends AcceuilFXMLController {

    @FXML
    private Label nom1;
    @FXML
    private Label username;
    @FXML
    private Label email;
    @FXML
    private Label nom2;
    @FXML
    private Label prenom;
    @FXML
    private Label adresse;
    @FXML
    private Label tel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        UserServices us= new UserServices();
        User u=us.AfficherUserId(User.getUserconnected());
        nom1.setText(u.getNom());
        username.setText(u.getUsername());
        email.setText(u.getEmail());
        nom2.setText(u.getNom());
        prenom.setText(u.getPrenom());
        adresse.setText(u.getAddresse());
        tel.setText(Integer.toString(u.getTelephone()));
    }  
     @FXML
     public void back(ActionEvent event) throws IOException {
        ((Node) (event.getSource())).getScene().getWindow().hide();
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("AcceuilLoginFXML.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }
     public void Modifier(ActionEvent event) throws IOException {
        FXMLLoader loader=new FXMLLoader(getClass().getResource(("ModifierProfileFXML.fxml")));
            loader.load();
            AnchorPane parentContent = loader.getRoot();
            window = (AnchorPane) nom1.getParent().getParent();
            ModifierProfileFXMLController cont=loader.getController();
  
            window.getChildren().setAll(parentContent);

    }
    
    
}
