/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bonplan.gui;

import com.bonplan.entities.Recommendation;
import com.bonplan.entities.Voyage;
import com.bonplan.entities.User;
import com.bonplan.services.RecommendationService;
import com.bonplan.services.VoyageService;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.types.FacebookType;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DateCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.converter.DateStringConverter;

/**
 * FXML Controller class
 *
 * @author Radhouen
 */
public class ListeVoyageFXMLController implements Initializable {

    @FXML
    private TableView<Voyage> table;
    @FXML
    private TableColumn<Voyage, String> categorie;
    @FXML
    private TableColumn<Voyage, String> Agence;
    @FXML
    private TableColumn<Voyage, Integer> nbr_place;
    @FXML
    private TableColumn<Voyage, DateStringConverter>dateDepart;
    @FXML
    private TableColumn<Voyage, DateStringConverter> dateArrivee;
    @FXML
    private TableColumn<Voyage, Float> prix;
    @FXML
    private TableColumn<Voyage, String> destination;
    @FXML
    private TableColumn <Voyage, String> description;
     private ObservableList<Voyage> data;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
         data = FXCollections.observableArrayList();
        List<Voyage> voyage = null;
        
            VoyageService rs = new VoyageService();
            System.out.println(User.getUserconnected());
            voyage=rs.AfficherVoyage();
            voyage.forEach(a->System.out.println());
            
        
        
        for (Voyage i : voyage) {
            data.add(i);
            System.out.println(i);
        }

        categorie.setCellValueFactory(new PropertyValueFactory<>("categorie"));
        Agence.setCellValueFactory(new PropertyValueFactory<>("Type"));
        nbr_place.setCellValueFactory(new PropertyValueFactory<>("nbr_place"));
        dateDepart.setCellValueFactory(new PropertyValueFactory<>("date_dep"));
        dateArrivee.setCellValueFactory(new PropertyValueFactory<>("date_arr"));
        prix.setCellValueFactory(new PropertyValueFactory<>("prix"));
        destination.setCellValueFactory(new PropertyValueFactory<>("destination"));
        description.setCellValueFactory(new PropertyValueFactory<>("description"));
       
        
        

        table.setItems(null);
        table.setItems(data);  
        // TODO
    }   
    
    @FXML
     public void AjouterV(ActionEvent event) throws IOException {
        ((Node) (event.getSource())).getScene().getWindow().hide();
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("AjouterVoyageFXML.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    } 
    @FXML
     public void supprimer(ActionEvent event) throws IOException {
         Voyage v=table.getSelectionModel().getSelectedItem();
         VoyageService rs=new VoyageService();
         System.out.println(v.getId_voyage());
         rs.SupprimerVoyage(v.getId_voyage());
        ((Node) (event.getSource())).getScene().getWindow().hide();
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("ListeVoyageFXML.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }
      @FXML
     public void Modifier(ActionEvent event) throws IOException {
         Voyage v=table.getSelectionModel().getSelectedItem();
         Voyage.setId_vModifier(v.getId_voyage());
        ((Node) (event.getSource())).getScene().getWindow().hide();
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("ModifierVoyageFXML.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }
     @FXML
    private void Partager(ActionEvent event) {
         Voyage v = table.getItems().get(table.getSelectionModel().getSelectedIndex());
        int selectedItem = table.getSelectionModel().getSelectedIndex();
        
    
         if (selectedItem >= 0) 
        {
//             Facebook fb = new Facebook();
//             fb.partager(selectedPerson);
//         }
 Voyage voyage=new Voyage();
            voyage = table.getItems().get(table.getSelectionModel().getSelectedIndex());
          
            System.out.println("*******"+v.getCategorie());
       
               String accessToken = "EAACOU6PPqrQBALImKqV0lmIHy15fJ6vZAzZAYZBnjgpCfQ4fgH2lAUmV65SGR9nrkQwSgWEFDcxa8M0jS7ROYwlPgD2WGRQaKt2ErJC8kMCZBSoyhttL1wgTL7sTQliKJZBbfl75QZCYVxqh8Y05A6cURkimUNavCuBc5yjGfAA5eZCl53WjB6nogcYn1MpSNYZD";
       Scanner s = new Scanner(System.in);
        FacebookClient fbClient= new DefaultFacebookClient(accessToken);
         FacebookType response = fbClient.publish("me/feed", FacebookType.class,
                           Parameter.with("message", "Voyage  "+voyage.getDestination()+" at"+voyage.getDescription()),
                           Parameter.with("link", "http://127.168.0.1/"));
         System.out.println("fb.com/"+response.getId());
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("success");
        alert.setContentText("Votre Publicite à été publié");
        alert.showAndWait();}
    }
}
