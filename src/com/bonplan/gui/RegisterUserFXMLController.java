/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bonplan.gui;

import com.bonplan.entities.User;
import com.bonplan.services.UserServices;
import com.bonplan.util.MailVerification;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

/**
 * FXML Controller class
 *
 * @author Achraf
 */
public class RegisterUserFXMLController implements Initializable {

    @FXML
    private TextField nom;
    @FXML
    private TextField prenom;
    @FXML
    private TextField email;
    @FXML
    private TextField username;
    @FXML
    private TextField tel;
    @FXML
    private TextField addresse;
    @FXML
    private PasswordField password;
    @FXML
    private PasswordField reppassword;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    @SuppressWarnings("empty-statement")
    public void register(ActionEvent event){
        boolean valid = true;
        if (nom.getText().equals("")) {
            nom.setText("Field is empty !");
            nom.setVisible(true);
            valid = false;
        } else {
            nom.setText("");
        }

        if (prenom.getText().equals("")) {
            prenom.setText("Field is empty !");
            prenom.setVisible(true);
            valid = false;
        } else {
            prenom.setText("");
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
        } else {
            email.setText("");
        }

        if (tel.getText().equals("")) {
            tel.setText("Field is empty !");
            tel.setVisible(true);
            valid = false;
        } else {
            tel.setText("");
        }
        if (addresse.getText().equals("")) {
            addresse.setText("Field is empty !");
            addresse.setVisible(true);
            valid = false;
        } else {
            addresse.setText("");
        }

        if (password.getText().equals("")) {
            password.setText("Field is empty !");
            password.setVisible(true);
            valid = false;
        }
        if (reppassword.getText().equals("")) {
            reppassword.setText("Field is empty !");
            reppassword.setVisible(true);
            valid = false;
        }

        if (!password.getText().equals(reppassword.getText())) {
            reppassword.setText("Password doesn't match !");
            reppassword.setVisible(true);
            valid = false;
        } else {
            reppassword.setText("Password match !");
            //reppassword.setTextFill(Color.web("GREEN"));;
            reppassword.setVisible(true);
        }

        UserServices us=new UserServices();
        User u=new User(username.getText(), email.getText(), 0, password.getText()+"{"+username.getText()+"}", "0", nom.getText(), prenom.getText(), addresse.getText(), Integer.parseInt(tel.getText()));
        us.AjouterUser(u);
    }
    
}
