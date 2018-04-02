/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bonplan.gui;

import com.bonplan.entities.Recommendation;
import com.bonplan.entities.User;
import com.bonplan.services.RecommendationService;
import com.bonplan.services.UserServices;
import com.bonplan.util.MailVerification;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Achraf
 */
public class ModifierRecommendationFXMLController implements Initializable {

    @FXML
    private TextField titre;
    @FXML
    private MenuItem restaurant;
    @FXML
    private MenuItem prestation;
    @FXML
    private MenuItem produit;
    @FXML
    private MenuItem voyage;
    @FXML
    private MenuItem evenement;
    @FXML
    private TextArea description;
    @FXML
    private TextField nom;
    @FXML
    private TextField adresse;
    @FXML
    private TextField num_tel;
    @FXML
    private TextField email;
    public File file;
    public Stage stage;
    Recommendation r;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        RecommendationService rs= new RecommendationService();
        Recommendation rec=rs.AfficherDetailRecommendation(Recommendation.getId_recModifier());
        r=rec;
        
        titre.setText(rec.getTitre());
        
        description.setText(rec.getDescription());
        nom.setText(rec.getNom());
        adresse.setText(rec.getAdresse());
        num_tel.setText(rec.getNum_tel());
        email.setText(rec.getEmail());

    }    
    @FXML
    private void ajoutPhoto(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Image Files","*.png","*.jpg","*.gif")
        );
        file = fileChooser.showOpenDialog(stage);
        if (file!=null) {
            
                String img = file.getName();
                System.out.println(img);
                System.out.println("jawek béhi");
                r.setPhoto(img);
            
         }    
        
    }
     @FXML
    @SuppressWarnings("empty-statement")
    public void modifier(ActionEvent event) throws IOException{
        boolean valid = true;
        if (titre.getText().equals("")) {
            titre.setText("Field is empty !");
            titre.setVisible(true);
            valid = false;
        }

        if (description.getText().equals("")) {
            description.setText("Field is empty !");
            description.setVisible(true);
            valid = false;
        }

        
        if (nom.getText().equals("")) {
            nom.setText("Field is empty !");
            nom.setVisible(true);
            valid = false;
        }

        
        if (adresse.getText().equals("")) {
            adresse.setText("Field is empty !");
            adresse.setVisible(true);
            valid = false;
        }
        if (num_tel.getText().equals("")) {
            num_tel.setText("Field is empty !");
            num_tel.setVisible(true);
            valid = false;
        
        }
        if (email.getText().equals("")) {
            email.setText("Field is empty !");
            email.setVisible(true);
            valid = false;
        
        }
      if(valid=true){

        
        if(titre.getText().equals(""))
            r.setTitre(r.getTitre());
        else r.setTitre(titre.getText());
        if(description.getText().equals(""))
            r.setDescription(r.getDescription());
        else r.setDescription(description.getText());
        if(nom.getText().equals(""))
            r.setNom(r.getNom());
        else r.setNom(nom.getText());
        if(adresse.getText().equals(""))
            r.setAdresse(r.getAdresse());
        else r.setAdresse(adresse.getText());
        if(num_tel.getText().equals(""))
            r.setNum_tel(r.getNum_tel());
        else r.setNum_tel(num_tel.getText());
        if(email.getText().equals(""))
            r.setEmail(r.getEmail());
        else r.setEmail(email.getText());
        r.setPhoto(r.getPhoto());
        
       RecommendationService rs=new RecommendationService();
        rs.ModifierRecommendation(Recommendation.getId_recModifier(), r);
        ((Node) (event.getSource())).getScene().getWindow().hide();
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("AfficheRecommendationUserFXML.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
}}
