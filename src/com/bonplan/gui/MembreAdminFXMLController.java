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
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Achraf
 */
public class MembreAdminFXMLController implements Initializable {

    @FXML
    private TableView<User> table;
    @FXML
    private TableColumn<UserServices, String> username;
    @FXML
    private TableColumn<UserServices, String> email;
    @FXML
    private TableColumn<UserServices, String> nom;
    @FXML
    private TableColumn<UserServices, String> prenom;
    @FXML
    private TableColumn<UserServices, String> adresse;
    @FXML
    private TableColumn<UserServices, Integer> telephone;
    @FXML
    private TableColumn<UserServices, Integer> enabled;
    private ObservableList<User> data;
 
    @FXML
    private TextField txt_id;
    private int selected_id;
   

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        data = FXCollections.observableArrayList();
        List<User> user = null;
        
            UserServices us = new UserServices();
            user=us.getAllUser();
            
        
        
        for (User i : user) {
            data.add(i);
        }

        username.setCellValueFactory(new PropertyValueFactory<>("username"));
        email.setCellValueFactory(new PropertyValueFactory<>("email"));
        nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        adresse.setCellValueFactory(new PropertyValueFactory<>("addresse"));
        telephone.setCellValueFactory(new PropertyValueFactory<>("telephone"));
        enabled.setCellValueFactory(new PropertyValueFactory<>("enabled"));
        

        table.setItems(null);
        table.setItems(data);
    }    
    @FXML
    private void Banner_click(MouseEvent event) throws IOException {
        User u = (User) table.getSelectionModel().getSelectedItem();
        //txt_id.setText(String.valueOf(u.getId()));
        UserServices us= new UserServices();
        System.out.println(u.getEnabled());
        us.UpdateEnabledUser(u.getEnabled(), 0);
         ((Node) (event.getSource())).getScene().getWindow().hide();
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("MembreAdminFXML.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    private void BannerAction(ActionEvent event) throws IOException{
        
            if(txt_id == null || txt_id.getText().isEmpty()) return;
            UserServices us = UserServices.getInstance();
            us.UpdateEnabledUser(Integer.parseInt(txt_id.getText()), 0);
            ((Node) (event.getSource())).getScene().getWindow().hide();
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("MembreAdminFXML.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        
    }

    @FXML
    private void removeBann(ActionEvent event) throws IOException {
        
            if(txt_id == null || txt_id.getText().isEmpty()) return;
            UserServices us = UserServices.getInstance();
            us.UpdateEnabledUser(Integer.parseInt(txt_id.getText()),1);
             ((Node) (event.getSource())).getScene().getWindow().hide();
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("MembreAdminFXML.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        
    }
    
}
