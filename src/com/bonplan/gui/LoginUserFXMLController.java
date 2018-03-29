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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Achraf
 */
public class LoginUserFXMLController implements Initializable {

    int logged = 0;
    @FXML
    private TextField login;
    @FXML
    private TextField password;
    @FXML
    private Button button;
    @FXML
    private Button buttonAdmin;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void login(ActionEvent event) throws IOException {
        UserServices us = new UserServices();
if (login.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Insert your Pseudo");
            alert.setHeaderText("Insert your Pseudo");
            alert.setContentText("Insert your Pseudo");

            alert.showAndWait();

        } else if (password.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Insert your Password");
            alert.setHeaderText("Insert your Password");
            alert.setContentText("Insert your Password");

            alert.showAndWait();
        } else{
            User u=us.AfficherUser(login.getText());
         if (u != null) {
        if (us.Login(login.getText(), password.getText())) {
            if (u.getEnabled()== 0) {
                 Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setTitle("Blocked");
                            alert.setHeaderText("You are blocked ! ");
                            alert.setContentText(" You are blocked !");
                             alert.showAndWait();
            }
                
            
            else{
                ((Node) (event.getSource())).getScene().getWindow().hide();
                User.setUserconnected(u.getId());
            Stage stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("LoginSuccessFXML.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
            }
            

        } else {
            Alert a = new Alert(Alert.AlertType.INFORMATION, "failed", ButtonType.CLOSE);
            a.show();
        }

    }}}

    public void register(ActionEvent event) throws IOException {
        ((Node) (event.getSource())).getScene().getWindow().hide();
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("RegisterUserFXML.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }
   

}
