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
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import jdk.nashorn.internal.parser.DateParser;





/**
 * FXML Controller class
 *
 * @author Radhouen
 */
public class ModifierVoyageFXMLController extends AcceuilFXMLController {

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
      
   

        // TODO
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
            String img = file.getName();System.out.println(img);
            
String path = "C:/xampp/htdocs/Our/web/uploads/";
            
                Files.copy(file.toPath(),
                        (new File(path + file.getName())).toPath(),
                        StandardCopyOption.REPLACE_EXISTING);
                System.out.println(img);
                System.out.println("jawek béhi");
                v.setPhoto(img);

            }
        }
    
    
    @FXML
    private void ModifierVoyage(ActionEvent event) throws IOException {
        
         boolean valid = true;
        if (categorie.getText().equals("")) {
            categorie.setText("Field is empty !");
            categorie.setVisible(true);
            valid = false;
        }
        
        if (descrip.getText().equals("")) {
            descrip.setText("Field is empty !");
            descrip.setVisible(true);
            valid = false;
        }
        
        
        
        if (agence.getText().equals("")) {
            agence.setText("Field is empty !");
            agence.setVisible(true);
            valid = false;
        }
        if (nbr_place.getText().equals("")) {
            nbr_place.setText("Field is empty !");
            nbr_place.setVisible(true);
            valid = false;
            
        }
        if (dest.getText().equals("")) {
            dest.setText("Field is empty !");
            dest.setVisible(true);
            valid = false;
            
        }
        if (prix.getText().equals("")) {
            prix.setText("Field is empty !");
            prix.setVisible(true);
            valid = false;
            
        }
        
        
        
        NumberValidator nv = new NumberValidator();
        nv.setMessage("Veuillez saisir une nombre valide");
         boolean test = true ;
         boolean test2 = true ;


         test = valideDate(date_arriv.getValue(),date_dep.getValue());
         test2= valideDate(date_dep.getValue(),LocalDate.now());


         if(valid==true){
         if(test==true && test2==true)
        
         {
             if(Integer.parseInt(nbr_place.getText())>0 ){
                 
                 if( Float.parseFloat(prix.getText())>0 ){
        VoyageService vs=new VoyageService();
        
        vs.ModifierVoyage(new Voyage(v.getId_vModifier(),
                categorie.getText(),
                agence.getText(),
                Integer.parseInt(nbr_place.getText()),
                java.sql.Date.valueOf(date_dep.getValue()), 
                java.sql.Date.valueOf(date_arriv.getValue()), 
                Float.parseFloat(prix.getText())
                , descrip.getText()
                , dest.getText()
                ,v.getPhoto()));
        Notifications.create().title("Modifié avec succés").text("Modifié avec succés").showInformation();
        
        
        categorie.setText("");
        agence.setText("");
        nbr_place.setText("");
        date_dep.setValue(LocalDate.now());
        date_arriv.setValue(LocalDate.now());
        prix.setText("");
        descrip.setText("");
        dest.setText("");
        
         }
             
             else
              Notifications.create().title("Ajout").text("Le prix doit etre superieur à zero").showError();}
             
             else{Notifications.create().title("Ajout").text("Le nombre de place doit etre un entier superieur à zero").showError();}
         
         
         }
         else
         {   Notifications.create().title("Ajout").text("Date Invalide").showError();
}
         }
          FXMLLoader loader=new FXMLLoader(getClass().getResource(("AllVoyagesFXML.fxml")));
            loader.load();
            AnchorPane parentContent = loader.getRoot();
            window = (AnchorPane) dest.getParent().getParent();
            AllVoyagesFXMLController cont=loader.getController();
  
            window.getChildren().setAll(parentContent);
    }
        public boolean valideDate(LocalDate x , LocalDate y)
    {
        return x.compareTo(y) > 0;
                
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
