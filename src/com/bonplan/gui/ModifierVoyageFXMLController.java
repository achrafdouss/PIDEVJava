/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bonplan.gui;

import com.bonplan.entities.Recommendation;
import com.bonplan.entities.Voyage;
import com.bonplan.services.RecommendationService;
import com.bonplan.services.VoyageService;
import com.jfoenix.validation.NumberValidator;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.time.Month;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.controlsfx.control.Notifications;
import java.util.Date;
import javafx.stage.FileChooser;
import jdk.nashorn.internal.parser.DateParser;





/**
 * FXML Controller class
 *
 * @author Radhouen
 */
public class ModifierVoyageFXMLController implements Initializable {

    @FXML
    private TextField categorie;
    @FXML
    private TextField agence;
    @FXML
    private TextField nbr_place;
    @FXML
    private DatePicker date_dep;
    @FXML
    private DatePicker date_arriv;
    @FXML
    private TextField prix;
    @FXML
    private TextArea descrip;
    @FXML
    private TextField dest;
    @FXML
    private TextField photo;
     public Stage stage;
     Voyage v;
     Voyage r = new Voyage();


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         VoyageService rs= new VoyageService();
        Voyage vs=rs.AfficherDetailVoyage(Voyage.getId_vModifier());
        v=vs;
        
        categorie.setText(vs.getCategorie());
        agence.setText(vs.getType());
        nbr_place.setText(Integer.toString(vs.getNbr_place()));
        date_dep.setValue(LocalDate.parse(vs.getDate_dep().toString()));
        date_arriv.setValue(LocalDate.parse(vs.getDate_arr().toString()));
        prix.setText(Float.toString(vs.getPrix()));
        descrip.setText(vs.getDescription());
        dest.setText(vs.getDestination());
        photo.setText(vs.getPhoto());
   

        // TODO
    }    

    @FXML
    private void ModifierVoyage(ActionEvent event) {
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
      @FXML
    private void ajoutPhoto(ActionEvent event) throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif")
        );
        File file = fileChooser.showOpenDialog(stage);
        if (file != null) {
            System.out.println(file.getAbsolutePath());
            String img = file.getName();
            
String path = "C:/wamp64/www/PIDEV/web/uploads/";
            
                Files.copy(file.toPath(),
                        (new File(path + file.getName())).toPath(),
                        StandardCopyOption.REPLACE_EXISTING);
                System.out.println(img);
                System.out.println("jawek béhi");
                r.setPhoto(img);

            }
        }
     
}
