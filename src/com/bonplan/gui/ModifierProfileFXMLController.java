/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bonplan.gui;

import com.bonplan.entities.User;
import com.bonplan.services.UserServices;
import com.bonplan.util.MailVerification;
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
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Achraf
 */
public class ModifierProfileFXMLController extends AfficheProfileFXMLLController {

    @FXML
    private TextField usernamemp;
    @FXML
    private TextField emailmp;
    @FXML
    private TextField nommp;
    @FXML
    private TextField prenommp;
    @FXML
    private TextField adressemp;
    @FXML
    private TextField telmp;
    @FXML
    private TextField telepmp;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
           UserServices us= new UserServices();
        User u=us.AfficherUserId(User.getUserconnected());
        nommp.setText(u.getNom());
        usernamemp.setText(u.getUsername());
        emailmp.setText(u.getEmail());
        prenommp.setText(u.getPrenom());
        adressemp.setText(u.getAddresse());
        telepmp.setText(Integer.toString(u.getTelephone()));
    }    
    @FXML
     public void profile(ActionEvent event) throws IOException {
        ((Node) (event.getSource())).getScene().getWindow().hide();
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("AfficheProfileFXMLL.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }
     @FXML
    @SuppressWarnings("empty-statement")
    public void register(ActionEvent event) throws IOException{
        boolean valid = true;
        if (nommp.getText().equals("")) {
            nommp.setText("Field is empty !");
            nommp.setVisible(true);
            valid = false;
        }

        if (prenommp.getText().equals("")) {
            prenommp.setText("Field is empty !");
            prenommp.setVisible(true);
            valid = false;
        }

        
        if (usernamemp.getText().equals("")) {
            usernamemp.setText("Field is empty !");
            usernamemp.setVisible(true);
            valid = false;
        }

        if (!MailVerification.validate(emailmp.getText())) {
            emailmp.setText("E-mail is not valid !");
            emailmp.setVisible(true);
            valid = false;
       
        }
        if (telepmp.getText().equals("")) {
            telepmp.setText("Field is empty !");
            telepmp.setVisible(true);
            valid = false;
        }
        if (adressemp.getText().equals("")) {
            adressemp.setText("Field is empty !");
            adressemp.setVisible(true);
            valid = false;
        
        }
      if(valid=true){

        UserServices us=new UserServices();
        User u=us.AfficherUserId(User.getUserconnected());
        if(usernamemp.getText().equals(""))
            u.setUsername(u.getUsername());
        else u.setUsername(usernamemp.getText());
        if(emailmp.getText().equals(""))
            u.setEmail(u.getEmail());
        else u.setEmail(emailmp.getText());
        if(prenommp.getText().equals(""))
            u.setPrenom(u.getPrenom());
        else u.setPrenom(prenommp.getText());
        if(adressemp.getText().equals(""))
            u.setAddresse(u.getAddresse());
        else u.setAddresse(adressemp.getText());
        if(nommp.getText().equals(""))
            u.setNom(u.getNom());
        else u.setNom(nommp.getText());
        if(telepmp.getText().equals(""))
            u.setTelephone(u.getTelephone());
        else u.setTelephone(Integer.parseInt(telepmp.getText()));
        
       
        us.ModiferUser(User.getUserconnected(), u);
       FXMLLoader loader=new FXMLLoader(getClass().getResource(("AfficheProfileFXMLl.fxml")));
            loader.load();
            AnchorPane parentContent = loader.getRoot();
            window = (AnchorPane) usernamemp.getParent().getParent();
            AfficheProfileFXMLLController cont=loader.getController();
  
            window.getChildren().setAll(parentContent);
    }
    
}}
