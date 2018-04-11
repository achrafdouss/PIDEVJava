/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bonplan.gui;

import com.bonplan.entities.Recommendation;
import com.bonplan.entities.Reservation;
import com.bonplan.entities.User;
import com.bonplan.entities.Voyage;
import com.bonplan.services.RecommendationService;
import com.bonplan.services.ReserverService;
import com.bonplan.services.VoyageService;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.types.FacebookType;
import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.sql.PreparedStatement;
import java.util.Scanner;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.controlsfx.control.Notifications;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.OAuthAuthorization;
import twitter4j.conf.ConfigurationBuilder;


/**
 * FXML Controller class
 *
 * @author Radhouen
 */
public class AllVoyagesFXMLController extends AcceuilFXMLController {

    @FXML
    private AnchorPane winodow;
    @FXML
    private Pagination paginator;
    @FXML
    private AnchorPane box;
    @FXML
    private ImageView image;
    @FXML
    private Label agence;
    @FXML
    private Label categorie;
    @FXML
    private AnchorPane details;
    @FXML
    private ImageView photo;
    @FXML
    private Label dateDep;
    @FXML
    private Text descriptiond;
    @FXML
    private Label Agenced;
    @FXML
    private Label categoried;
    @FXML
    private Label prix;
    @FXML
    private AnchorPane box1;
    @FXML
    private ImageView image1;
    @FXML
    private Label agence1;
    @FXML
    private Label categorie1;
    @FXML
    private Text prix1;
    @FXML
    private AnchorPane box2;
    @FXML
    private ImageView image2;
    @FXML
    private Label agence2;
    @FXML
    private Label categorie2;
    @FXML
    private Text prix2;
     List<Voyage> liste= new ArrayList<>();
    @FXML
    private DatePicker DateDepd;
    @FXML
    private DatePicker dateArrivd;
    @FXML
    private Label Nbr_place;
    Voyage v=new Voyage();
    @FXML
    private Button reserver;
    @FXML
    private TextField cat;
    @FXML
    private TextField dest;
    @FXML
    private TextField prixmin;
    @FXML
    private TextField prixmax;
    @FXML
    private Button rech;
    private int i=0;
    @FXML
    private Button PartT;
    @FXML
    private Button PartF;
    @FXML
    private Text text;
    @FXML
    private Button modif;
    @FXML
    private Button supp;
    @FXML
    private Button res;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
       rech.setOnAction(new EventHandler<ActionEvent>() {
           @Override
           public void handle(ActionEvent event) {
               VoyageService rs=new VoyageService();
         
         if("".equals(prixmax.getText())) prixmax.setText("0");
                  if("".equals(prixmin.getText())) prixmin.setText("0");

      liste= new ArrayList<>();
       liste=  rs.find(prixmax.getText(),prixmin.getText(),dest.getText(),cat.getText());
              liste = rs.AfficherVoyage();
              if (liste.isEmpty()) {
            box.setVisible(false);
            box1.setVisible(false);
            box2.setVisible(false);
            //ide.setVisible(true);
            paginator.setVisible(false);
        } else {
            paginator.setVisible(true);
            //vide.setVisible(false);
            setNbPages();
            i=1;
            initAnnoncePage(0,i);
        }       
liste.forEach(a->System.out.println(a));
              cat.setText("");
        dest.setText("");
        prixmax.setText("");
        prixmin.setText("");
           }
       });
        
     details.setVisible(false);
         VoyageService rs= new VoyageService();
        liste= new ArrayList<>();
       
        //cate.getValue().toString();
    
      
      
        liste = rs.AfficherVoyage();
        //liste1=  rs.find(prixmax.getText(),prixmin.getText(),dest.getText(),cat.getText());

       
        if (liste.isEmpty()) {
            box.setVisible(false);
            box1.setVisible(false);
            box2.setVisible(false);
            //ide.setVisible(true);
            paginator.setVisible(false);
        } else {
            paginator.setVisible(true);
            //vide.setVisible(false);
            setNbPages();
            initAnnoncePage(0,0);
        }       
    }
private void setNbPages() {

       if (liste.size() % 3 != 0) {
            paginator.setPageCount((liste.size() / 3) + 1);
        } else {
            paginator.setPageCount(liste.size() / 3);
        }
        
        paginator.currentPageIndexProperty().addListener((obs, oldIndex, newIndex) -> {
            initAnnoncePage(newIndex.intValue(),i);
        });
    }

    private void initAnnoncePage(int i,int j) {
        VoyageService rs= new VoyageService();
        
        liste= new ArrayList<>();
        if(j==0)
        liste = rs.AfficherVoyage();
        else liste=  rs.find(prixmax.getText(),prixmin.getText(),dest.getText(),cat.getText());

        
      
        paginator.setCurrentPageIndex(i);
        List<Voyage> TroisAnnonces = getAnnoncesPage(i);     
        if (TroisAnnonces.size() >= 1) {
            box.setVisible(true);
            Image img=new Image("http://localhost:1020/Our/web/uploads/"+TroisAnnonces.get(0).getPhoto());
            image.setImage(img);
            categorie.setText(TroisAnnonces.get(0).getCategorie());
            agence.setText(TroisAnnonces.get(0).getType());
            prix.setText(Float.toString(TroisAnnonces.get(0).getPrix()));
            box.setOnMouseClicked((MouseEvent e) -> {
                System.out.println("moula l 3amla berassmi"+TroisAnnonces.get(0).getId_owner());
                initialiserDetails(TroisAnnonces.get(0));
                details.setVisible(true);
              
                System.out.println("Taswiraaaaaaaaaa"+TroisAnnonces.get(0).getPhoto());
           
            });
            
           
            
        }   
        else { 
            box.setVisible(false);
        }
        if (TroisAnnonces.size() >= 2) {
             box1.setVisible(true);
            Image img=new Image("http://localhost:1020/Our/web/uploads/"+TroisAnnonces.get(1).getPhoto());
            image1.setImage(img);
            categorie1.setText(TroisAnnonces.get(1).getCategorie());
            agence1.setText(TroisAnnonces.get(1).getType());
            prix1.setText(Float.toString(TroisAnnonces.get(1).getPrix()));
            box1.setOnMouseClicked((MouseEvent e) -> {
                initialiserDetails(v=TroisAnnonces.get(1));
                details.setVisible(true);
                
            });
           
            
        }   
        else { 
            box1.setVisible(false);
        }
        if (TroisAnnonces.size() >= 3) {
            box2.setVisible(true);
            Image img=new Image("http://localhost:1020/Our/web/uploads/"+TroisAnnonces.get(2).getPhoto());
            image2.setImage(img);
            categorie2.setText(TroisAnnonces.get(2).getCategorie());
            agence2.setText(TroisAnnonces.get(2).getType());
            prix2.setText(Float.toString(TroisAnnonces.get(2).getPrix()));
            box2.setOnMouseClicked((MouseEvent e) -> {
                initialiserDetails(v=TroisAnnonces.get(2));
                details.setVisible(true);
          
            
        });   
        }else { 
            box2.setVisible(false);
        }

    }
private List<Voyage> getAnnoncesPage(int i) {
        int start = 3 * i;
        int fin = start + 3;
        if (liste.size() > start) {
            if (liste.size() > fin) {
                return liste.subList(start, fin);
            } else {
                return liste.subList(start, liste.size());
            }
        }
        return liste.subList(0, 2);    
    }  
 private void initialiserDetails(Voyage r) {
      modif.setVisible(false);
                supp.setVisible(false);
                reserver.setVisible(false);
     if(User.getUserconnected()==r.getId_owner())
     { 
                modif.setVisible(true);
                supp.setVisible(true);}
               
     else if(User.getUserconnected()!=r.getId_owner())
                { reserver.setVisible(true);
               }
        
        Image img=new Image("http://localhost:1020/Our/web/uploads/"+r.getPhoto());
            photo.setImage(img);
            categoried.setText(r.getCategorie());
            Agenced.setText(r.getType());
            descriptiond.setText(r.getDescription());
            DateDepd.setValue(LocalDate.parse(r.getDate_dep().toString()));
            dateArrivd.setValue(LocalDate.parse(r.getDate_arr().toString()));
            prix.setText(Float.toString(r.getPrix()));
            Nbr_place.setText(Integer.toString(r.getNbr_place()));
            Voyage.id_vModifier=r.getId_voyage();
            System.out.println(r.getId_voyage());
            
            
            
            //Partage Facebook
            PartF.setOnAction(new EventHandler<ActionEvent>() {
           @Override
          public void handle(ActionEvent event) {
                 String accessToken = "EAACOU6PPqrQBAMAkcoTdcNLeTG1lREIN7WlRFidzrZB0PEmnbwEVZA7ppO9mIllsZCW0FrB1mjMNSBT6zl2e5IH1EfcpTZBUMeqUM4BDjQTdcq38l0khCn46E2BcRXOwLWKZB0vbjtP99THPkepS6CBZCIyyOHrf2lHc1GH1nJfgGKp9u5K8a7I6ZALh1shF5wZA8wPECehMZCgZDZD";
       Scanner s = new Scanner(System.in);
        FacebookClient fbClient= new DefaultFacebookClient(accessToken);
         FacebookType response = fbClient.publish("me/feed", FacebookType.class,
                           Parameter.with("message", "Voyager vers "+r.getDestination()+" à partir de"+r.getPrix()+"Dt "),
                           Parameter.with("link", "http://127.168.0.1/"));
         Notifications.create().title("Publication Facebook").text("L'offre voyage a été publié avec succes").showInformation();
          }
      });
           
            //Partage Twitter
            
            PartT.setOnAction(new EventHandler<ActionEvent>() {
          @Override
          public void handle(ActionEvent event) {
              
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
            
 twitter.updateStatus("Voyage vers "+r.getDestination()+" à partir de "+r.getPrix()+"Dt ");


        }catch (TwitterException e){

            System.err.println("Error occurred while updating the status!");

            return;

        }


       

    

                Notifications.create().title("Publication Twitter").text("L'offre voyage a été publié avec succes").showInformation();

          
          }
      });
           
        
    }    

    @FXML
    private void annuler(ActionEvent event) {
                details.setVisible(false);
                
               
    }
    
    
    @FXML
     public void supprimer(ActionEvent event) throws IOException {
         VoyageService rs=new VoyageService();
         System.out.println(v.getId_voyage());
         rs.SupprimerVoyage(Voyage.getId_vModifier());
        FXMLLoader loader=new FXMLLoader(getClass().getResource(("AllVoyagesFXML.fxml")));
            loader.load();
            AnchorPane parentContent = loader.getRoot();
            window = (AnchorPane) paginator.getParent().getParent();
            AllVoyagesFXMLController cont=loader.getController();
  
            window.getChildren().setAll(parentContent);

    }
      @FXML
     public void Modifier(ActionEvent event) throws IOException {
         FXMLLoader loader=new FXMLLoader(getClass().getResource(("ModifierVoyageFXML.fxml")));
            loader.load();
            AnchorPane parentContent = loader.getRoot();
            window = (AnchorPane) paginator.getParent().getParent();
            ModifierVoyageFXMLController cont=loader.getController();
  
            window.getChildren().setAll(parentContent);


    } 
     
    @FXML
     public void AjouterV(ActionEvent event) throws IOException {
       FXMLLoader loader=new FXMLLoader(getClass().getResource(("AjouterVoyageFXML.fxml")));
            loader.load();
            AnchorPane parentContent = loader.getRoot();
            window = (AnchorPane) paginator.getParent().getParent();
            AjouterVoyageFXMLController cont=loader.getController();
  
            window.getChildren().setAll(parentContent);

    }
     
     @FXML
     public void Back(ActionEvent event) throws IOException {
        ((Node) (event.getSource())).getScene().getWindow().hide();
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("AcceuilLoginFXML.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    } 
     
    
    
    @FXML
     public void Reserv(ActionEvent event) throws IOException {
         reserver.setVisible(true);
        FXMLLoader loader=new FXMLLoader(getClass().getResource(("ReserverVoyageFXML.fxml")));
            loader.load();
            AnchorPane parentContent = loader.getRoot();
            window = (AnchorPane) paginator.getParent().getParent();
           ReserverVoyageFXMLController cont=loader.getController();
  
            window.getChildren().setAll(parentContent);

    }

    private void Rechercher(ActionEvent event) {
        
         VoyageService rs=new VoyageService();
         
         if("".equals(prixmax.getText())) prixmax.setText("0");
                  if("".equals(prixmin.getText())) prixmin.setText("0");

      liste= new ArrayList<>();
       liste=  rs.find(prixmax.getText(),prixmin.getText(),dest.getText(),cat.getText());
              liste = rs.AfficherVoyage();
              if (liste.isEmpty()) {
            box.setVisible(false);
            box1.setVisible(false);
            box2.setVisible(false);
            //ide.setVisible(true);
            paginator.setVisible(false);
        } else {
            paginator.setVisible(true);
            //vide.setVisible(false);
            setNbPages();
            initAnnoncePage(0,i);
        }       
liste.forEach(a->System.out.println(a));
              cat.setText("");
        dest.setText("");
        prixmax.setText("");
        prixmin.setText("");
    }
    
    
    
    
  
    @FXML
    public void MesReserv(ActionEvent event) throws IOException {
         
        FXMLLoader loader=new FXMLLoader(getClass().getResource(("MesReservationsFXML.fxml")));
            loader.load();
            AnchorPane parentContent = loader.getRoot();
            window = (AnchorPane) paginator.getParent().getParent();
           MesReservationsFXMLController cont=loader.getController();
  
            window.getChildren().setAll(parentContent);

    }

}
