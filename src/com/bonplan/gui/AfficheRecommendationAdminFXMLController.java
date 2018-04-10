/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bonplan.gui;

import com.bonplan.entities.Recommendation;
import com.bonplan.entities.User;
import com.bonplan.services.RecommendationService;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Achraf
 */
public class AfficheRecommendationAdminFXMLController extends AcceuilFXMLController {

    @FXML
    private TableView<Recommendation> table;
    @FXML
    private TableColumn<Recommendation, String> titre;
    @FXML
    private TableColumn<Recommendation, Integer> id;
    @FXML
    private TableColumn<Recommendation, Integer> iddd;
    @FXML
    private TableColumn<Recommendation, String> categorie;
    @FXML
    private TableColumn<Recommendation, String> description;
    @FXML
    private TableColumn<Recommendation, String> nom;
    @FXML
    private TableColumn<Recommendation, String> adresse;
    @FXML
    private TableColumn<Recommendation, Integer> telephone;
    @FXML
    private TableColumn<Recommendation, String> emailar;
    @FXML
    private TableColumn<Recommendation, Double> note;
     private ObservableList<Recommendation> data;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
 data = FXCollections.observableArrayList();
        List<Recommendation> recommendation = null;
        
            RecommendationService rs = new RecommendationService();
            System.out.println(User.getUserconnected());
            recommendation=rs.AfficherAllRecommendation();
            recommendation.forEach(a->System.out.println(a.getId()));
            
        
        
        for (Recommendation i : recommendation) {
            data.add(i);
            System.out.println(i.getId());
        }

        titre.setCellValueFactory(new PropertyValueFactory<>("titre"));
        iddd.setCellValueFactory(new PropertyValueFactory<>("id"));
        categorie.setCellValueFactory(new PropertyValueFactory<>("categorie"));
        adresse.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        description.setCellValueFactory(new PropertyValueFactory<>("description"));
        telephone.setCellValueFactory(new PropertyValueFactory<>("num_tel"));
        nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        note.setCellValueFactory(new PropertyValueFactory<>("note"));
        emailar.setCellValueFactory(new PropertyValueFactory<>("email"));
        
        

        table.setItems(null);
        table.setItems(data);    }    
    @FXML
     public void supprimer(ActionEvent event) throws IOException {
         Recommendation rec=table.getSelectionModel().getSelectedItem();
         RecommendationService rs=new RecommendationService();
         rs.SupprimerRecommendation(rec.getId());
        FXMLLoader loader=new FXMLLoader(getClass().getResource(("AfficheRecommendationAdminFXML.fxml")));
            loader.load();
            AnchorPane parentContent = loader.getRoot();
            window = (AnchorPane) table.getParent().getParent();
            AfficheRecommendationAdminFXMLController cont=loader.getController();
  
            window.getChildren().setAll(parentContent);

    }
     @FXML
     public void Modifier(ActionEvent event) throws IOException {
         Recommendation rec=table.getSelectionModel().getSelectedItem();
         Recommendation.setId_recModifier(rec.getId());
        FXMLLoader loader=new FXMLLoader(getClass().getResource(("ModifierRecommendationFXML.fxml")));
            loader.load();
            AnchorPane parentContent = loader.getRoot();
            window = (AnchorPane) table.getParent().getParent();
            ModifierRecommendationFXMLController cont=loader.getController();
  
            window.getChildren().setAll(parentContent);

    }
     
    
}
