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
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Achraf
 */
public class ModifierRecommendationFXMLController extends AfficheRecommendationUserFXMLController {

    @FXML
    private TextField titremr;
    @FXML
    private MenuItem restaurantmr;
    @FXML
    private MenuItem prestationmr;
    @FXML
    private MenuItem produitmr;
    @FXML
    private MenuItem voyagemr;
    @FXML
    private MenuItem evenementmr;
    @FXML
    private TextArea descriptionmr;
    @FXML
    private TextField nommr;
    @FXML
    private TextField adressemr;
    @FXML
    private TextField num_telmr;
    @FXML
    private TextField emailmr;
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
        
        titremr.setText(rec.getTitre());
        
        descriptionmr.setText(rec.getDescription());
        nommr.setText(rec.getNom());
        adressemr.setText(rec.getAdresse());
        num_telmr.setText(rec.getNum_tel());
        emailmr.setText(rec.getEmail());

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
        if (titremr.getText().equals("")) {
            titremr.setText("Field is empty !");
            titremr.setVisible(true);
            valid = false;
        }

        if (descriptionmr.getText().equals("")) {
            descriptionmr.setText("Field is empty !");
            descriptionmr.setVisible(true);
            valid = false;
        }

        
        if (nommr.getText().equals("")) {
            nommr.setText("Field is empty !");
            nommr.setVisible(true);
            valid = false;
        }

        
        if (adressemr.getText().equals("")) {
            adressemr.setText("Field is empty !");
            adressemr.setVisible(true);
            valid = false;
        }
        if (num_telmr.getText().equals("")) {
            num_telmr.setText("Field is empty !");
            num_telmr.setVisible(true);
            valid = false;
        
        }
        if (emailmr.getText().equals("")) {
            emailmr.setText("Field is empty !");
            emailmr.setVisible(true);
            valid = false;
        
        }
      if(valid=true){

        
        if(titremr.getText().equals(""))
            r.setTitre(r.getTitre());
        else r.setTitre(titremr.getText());
        if(descriptionmr.getText().equals(""))
            r.setDescription(r.getDescription());
        else r.setDescription(descriptionmr.getText());
        if(nommr.getText().equals(""))
            r.setNom(r.getNom());
        else r.setNom(nommr.getText());
        if(adressemr.getText().equals(""))
            r.setAdresse(r.getAdresse());
        else r.setAdresse(adressemr.getText());
        if(num_telmr.getText().equals(""))
            r.setNum_tel(r.getNum_tel());
        else r.setNum_tel(num_telmr.getText());
        if(emailmr.getText().equals(""))
            r.setEmail(r.getEmail());
        else r.setEmail(emailmr.getText());
        r.setPhoto(r.getPhoto());
        
       RecommendationService rs=new RecommendationService();
        rs.ModifierRecommendation(Recommendation.getId_recModifier(), r);
        FXMLLoader loader=new FXMLLoader(getClass().getResource(("AfficheRecommendationuserFXML.fxml")));
            loader.load();
            AnchorPane parentContent = loader.getRoot();
            window = (AnchorPane) titremr.getParent().getParent();
            AfficheRecommendationUserFXMLController cont=loader.getController();
  
            window.getChildren().setAll(parentContent);
    }
    
}}
