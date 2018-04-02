/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bonplan.gui;

import com.bonplan.entities.Voyage;
import com.bonplan.services.VoyageService;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.controlsfx.control.Notifications;
import java.util.Date;
import java.text.DateFormat;
import javafx.util.converter.LocalDateStringConverter;
import com.jfoenix.validation.NumberValidator;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Radhouen
 */
public class AjouterVoyageFXMLController implements Initializable {

    @FXML
    private DatePicker date_dep;
    @FXML
    private DatePicker date_arriv;
    @FXML
    private TextField categorie;
    @FXML
    private TextField agence;
    @FXML
    private TextField nbr_place;
    @FXML
    private TextField prix;
    @FXML
    private TextField dest;
    @FXML
    private TextField photo;
    @FXML
    private TextArea descrip;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void AjouterVoyage(ActionEvent event) {
        NumberValidator nv = new NumberValidator();
        nv.setMessage("Veuillez saisir une nombre valide");
         boolean test = true ;
         boolean test2 = true ;


         test = valideDate(date_arriv.getValue(),date_dep.getValue());
         test2= valideDate(date_dep.getValue(),LocalDate.now());


         
         if(test==true && test2==true)
        
         {
             if(Integer.parseInt(nbr_place.getText())>0 ){
                 
                 if( Float.parseFloat(prix.getText())>0 ){
        VoyageService vs=new VoyageService();
        vs.AjouterVoyage(new Voyage(categorie.getText(),
                agence.getText(),
                Integer.parseInt(nbr_place.getText()),
                java.sql.Date.valueOf(date_dep.getValue()), 
                java.sql.Date.valueOf(date_arriv.getValue()), 
                Float.parseFloat(prix.getText())
                , descrip.getText()
                , dest.getText()
                , photo.getText()));
        Notifications.create().title("Ajout avec succés").text("Ajout avec succés").showInformation();
        
        
        categorie.setText("");
        agence.setText("");
        nbr_place.setText("");
        date_dep.setValue(LocalDate.now());
        date_arriv.setValue(LocalDate.now());
        prix.setText("");
        descrip.setText("");
        dest.setText("");
        photo.setText("");
         }
             
             else
              Notifications.create().title("Ajout").text("Le prix doit etre superieur à zero").showError();}
             
             else{Notifications.create().title("Ajout").text("Le nombre de place doit etre un entier superieur à zero").showError();}
         
         
         }
         else
         {   Notifications.create().title("Ajout").text("Date Invalide").showError();
}

    }
        public boolean valideDate(LocalDate x , LocalDate y)
    {
        return x.compareTo(y) >= 0;
                
    }
        
         public boolean isInteger(TextField input) {
        try {
            int nbr_place = Integer.parseInt(input.getText());
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
     @FXML
     public void Back(ActionEvent event) throws IOException {
        ((Node) (event.getSource())).getScene().getWindow().hide();
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("ListeVoyageFXML.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    } 
    
}
    

