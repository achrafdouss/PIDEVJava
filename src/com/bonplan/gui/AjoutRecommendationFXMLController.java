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
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Achraf
 */
public class AjoutRecommendationFXMLController extends AcceuilFXMLController {
    
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
    private TextField emailr;
    @FXML
    private ChoiceBox<String> categorie;
    @FXML
    private ChoiceBox<String> namer;
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
        categorie.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
           RecommendationService rs=new RecommendationService();
                   ObservableList<String> listn = FXCollections.observableArrayList(rs.listerNom(newValue));

           namer.setItems(listn );
           
            }
        });
    }
    
    @FXML
    private void ajoutPhoto(ActionEvent event) throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif")
        );
        file = fileChooser.showOpenDialog(stage);
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
        if (emailr.getText().equals("")) {
            emailr.setText("Field is empty !");
            emailr.setVisible(true);
            valid = false;
            
        }
        if (valid = true) {
            
            r.setTitre(titre.getText());
            r.setCategorie(categorie.getValue());
            r.setId_owner(User.getUserconnected());
            r.setDescription(description.getText());
            
            r.setNom(namer.getValue());
            
            r.setAdresse(adresse.getText());
            
            r.setNum_tel(num_tel.getText());
            
            r.setEmail(emailr.getText());
            r.setPhoto(r.getPhoto());
            
            RecommendationService rs = new RecommendationService();
            rs.AjoutRecommendation(r);
             FXMLLoader loader=new FXMLLoader(getClass().getResource(("AfficheRecommendationFXML.fxml")));
            loader.load();
            AnchorPane parentContent = loader.getRoot();
            window = (AnchorPane) titre.getParent().getParent();
            AjoutRecommendationFXMLController cont=loader.getController();
  
            window.getChildren().setAll(parentContent);
        }
    }
    
}
