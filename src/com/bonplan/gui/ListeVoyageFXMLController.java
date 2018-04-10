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
import com.bonplan.services.UserServices;
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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.converter.DateStringConverter;
import twitter4j.*;
import twitter4j.auth.AccessToken;
import twitter4j.auth.OAuthAuthorization;
import twitter4j.auth.RequestToken;
import twitter4j.conf.ConfigurationBuilder;

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
    @FXML
    private TextField cat;
    @FXML
    private TextField prixmax;
    @FXML
    private TextField prixmin;
    @FXML
    private TextField dest;

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
       
               String accessToken = "EAACOU6PPqrQBAPH1wvxE9mjxUomkYGNVuCEWjTvPBMS1EzlEkZAikZBfbhUCD9hAuneZB6kiazgwcVyagpMa55XD2PxfVmX4ggaogu3CwFNaHLkFQZCFp4k2FlutxslnSRM2Vo6wLumEPZBAnqyt5YLTZA8uZBxJrbztZBDQYUZAyTL5j9uxw2xsEtKhkPJ1VuzEJxMqGQGLVXgZDZD";
       Scanner s = new Scanner(System.in);
        FacebookClient fbClient= new DefaultFacebookClient(accessToken);
         FacebookType response = fbClient.publish("me/feed", FacebookType.class,
                           Parameter.with("message", "Voyage  "+voyage.getDestination()+" at"+voyage.getDescription()+" photo "+voyage.getPhoto()),
                           Parameter.with("link", "http://127.168.0.1/"));
         System.out.println("fb.com/"+response.getId());
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("success");
        alert.setContentText("Votre Publicite à été publié");
        alert.showAndWait();}
    }
    private void PartagerTwitter(ActionEvent event) throws TwitterException {
         Voyage v = table.getItems().get(table.getSelectionModel().getSelectedIndex());
        int selectedItem = table.getSelectionModel().getSelectedIndex();
        
    
         if (selectedItem >= 0) 
        {
//             Facebook fb = new Facebook();
//             fb.partager(selectedPerson);
//         }
 Voyage voyage=new Voyage();
            voyage = table.getItems().get(table.getSelectionModel().getSelectedIndex());
          


        //Twitter Conf.
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
                .setOAuthConsumerKey("aOWJFwq3Fp2O8qVeJiUkDqP26")
                .setOAuthConsumerSecret("OBxr0mVSm8FxbyF1S4qbxbG9ua6vxABXUjoVB12qff9hmGa28u")
                .setOAuthAccessToken("967519700501651457-lSPXuCGbkvIRVgx942XjFYZGuDutJRN")
                .setOAuthAccessTokenSecret("SGQHpFTOZqUeWeCIIVpvtOJxfghaLmxJH8YWbz0aW5z1H");
 OAuthAuthorization auth = new OAuthAuthorization(cb.build());

      Twitter twitter = new TwitterFactory().getInstance(auth);

        try {
            
 twitter.updateStatus("Voyage vers "+voyage.getDestination()+"à partir de "+voyage.getPrix()+"Dt"+voyage.getPhoto());


        }catch (TwitterException e){

            System.err.println("Error occurred while updating the status!");

            return;

        }


       

    

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("success");
        alert.setContentText("Votre Publicite à été publié");
        alert.showAndWait();}
    }
    
    
    private void pdf(ActionEvent event) {
         Voyage v = table.getItems().get(table.getSelectionModel().getSelectedIndex());
       UserServices p = new UserServices();
       // User u = p.AfficherUserId(u.getId());
        //Pdf.getPdf(a, u);
    }
    
     public void Rechercher(ActionEvent event) throws IOException {
       
         VoyageService rs=new VoyageService();
         List<Voyage> voyage = null;
         if("".equals(prixmax.getText())) prixmax.setText("0");
                  if("".equals(prixmin.getText())) prixmin.setText("0");

     
       voyage=  rs.find(prixmax.getText(),prixmin.getText(),dest.getText(),cat.getText());
         
         data = FXCollections.observableArrayList();
       
        
//           System.out.println(Float.parseFloat(prixmax.getText()));
       //    System.out.println(Float.parseFloat(prixmin.getText()));

          
        
        
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
        cat.setText("");
        dest.setText("");
        prixmax.setText("");
        prixmin.setText("");

        // TODO

    }

}
