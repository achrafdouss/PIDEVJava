/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bonplan.gui;

import com.bonplan.entities.Recommendation;
import com.bonplan.entities.User;
import com.bonplan.services.RecommendationService;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Achraf
 */
public class AjoutRecommendationFXMLController implements Initializable {
    
    @FXML
    private TextField titre;
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
    @FXML
    private ChoiceBox<String> categorie;
    File file;
    Stage stage;
    Recommendation r = new Recommendation();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<String> list = FXCollections.observableArrayList("Restaurant", "Produit", "Prestation", "Voyage", "Evenement");
        categorie.setItems(list);
    }
    
    @FXML
    private void ajoutPhoto(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif")
        );
        file = fileChooser.showOpenDialog(stage);
        if (file != null) {
            
            String img = file.getName();
            System.out.println(img);
            System.out.println("jawek béhi");
            r.setPhoto(img);
            
        }
    }
    
    @FXML
    private void modifier(ActionEvent event) throws IOException {
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
        if (valid = true) {
            
            r.setTitre(titre.getText());
            r.setCategorie(categorie.getValue());
            r.setId_owner(User.getUserconnected());
            r.setDescription(description.getText());
            
            r.setNom(nom.getText());
            
            r.setAdresse(adresse.getText());
            
            r.setNum_tel(num_tel.getText());
            
            r.setEmail(email.getText());
            r.setPhoto(r.getPhoto());
            
            RecommendationService rs = new RecommendationService();
            rs.AjoutRecommendation(r);
            ((Node) (event.getSource())).getScene().getWindow().hide();
            Stage stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("AfficheRecommendationUserFXML.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }
    
}
