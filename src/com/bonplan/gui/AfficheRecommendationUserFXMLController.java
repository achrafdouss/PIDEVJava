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
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Achraf
 */
public class AfficheRecommendationUserFXMLController implements Initializable {

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
    private TableColumn<Recommendation, String> email;
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
            recommendation=rs.AfficherRecommendationById(2);
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
        email.setCellValueFactory(new PropertyValueFactory<>("email"));
        
        

        table.setItems(null);
        table.setItems(data);    }    
    @FXML
     public void supprimer(ActionEvent event) throws IOException {
         Recommendation rec=table.getSelectionModel().getSelectedItem();
         RecommendationService rs=new RecommendationService();
         rs.SupprimerRecommendation(rec.getId());
        ((Node) (event.getSource())).getScene().getWindow().hide();
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("AfficheRecommendationUserFXML.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }
    
}
