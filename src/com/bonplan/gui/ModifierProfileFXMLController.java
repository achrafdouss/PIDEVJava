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
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Achraf
 */
public class ModifierProfileFXMLController implements Initializable {

    @FXML
    private TextField username;
    @FXML
    private TextField email;
    @FXML
    private TextField nom;
    @FXML
    private TextField prenom;
    @FXML
    private TextField adresse;
    @FXML
    private TextField tel;
    @FXML
    private TextField telep;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
           UserServices us= new UserServices();
        User u=us.AfficherUserId(User.getUserconnected());
        nom.setText(u.getNom());
        username.setText(u.getUsername());
        email.setText(u.getEmail());
        prenom.setText(u.getPrenom());
        adresse.setText(u.getAddresse());
        telep.setText(Integer.toString(u.getTelephone()));
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
        if (nom.getText().equals("")) {
            nom.setText("Field is empty !");
            nom.setVisible(true);
            valid = false;
        }

        if (prenom.getText().equals("")) {
            prenom.setText("Field is empty !");
            prenom.setVisible(true);
            valid = false;
        }

        
        if (username.getText().equals("")) {
            username.setText("Field is empty !");
            username.setVisible(true);
            valid = false;
        }

        if (!MailVerification.validate(email.getText())) {
            email.setText("E-mail is not valid !");
            email.setVisible(true);
            valid = false;
       
        }
        if (telep.getText().equals("")) {
            telep.setText("Field is empty !");
            telep.setVisible(true);
            valid = false;
        }
        if (adresse.getText().equals("")) {
            adresse.setText("Field is empty !");
            adresse.setVisible(true);
            valid = false;
        
        }
      if(valid=true){

        UserServices us=new UserServices();
        User u=us.AfficherUserId(User.getUserconnected());
        if(username.getText().equals(""))
            u.setUsername(u.getUsername());
        else u.setUsername(username.getText());
        if(email.getText().equals(""))
            u.setEmail(u.getEmail());
        else u.setEmail(email.getText());
        if(prenom.getText().equals(""))
            u.setPrenom(u.getPrenom());
        else u.setPrenom(prenom.getText());
        if(adresse.getText().equals(""))
            u.setAddresse(u.getAddresse());
        else u.setAddresse(adresse.getText());
        if(nom.getText().equals(""))
            u.setNom(u.getNom());
        else u.setNom(nom.getText());
        if(telep.getText().equals(""))
            u.setTelephone(u.getTelephone());
        else u.setTelephone(Integer.parseInt(telep.getText()));
        
       
        us.ModiferUser(User.getUserconnected(), u);
        ((Node) (event.getSource())).getScene().getWindow().hide();
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("AfficheProfileFXMLL.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
}}
