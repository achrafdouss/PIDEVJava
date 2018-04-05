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
import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Radhouen
 */
public class AllVoyagesFXMLController implements Initializable {

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
    private Text text;
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
    private Label dateArriv;
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
     details.setVisible(false);
         VoyageService rs= new VoyageService();
        liste= new ArrayList<>();
       
        //cate.getValue().toString();
    
      
      
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
            initAnnoncePage(0);
        }       
    }
private void setNbPages() {

       if (liste.size() % 3 != 0) {
            paginator.setPageCount((liste.size() / 3) + 1);
        } else {
            paginator.setPageCount(liste.size() / 3);
        }
        
        paginator.currentPageIndexProperty().addListener((obs, oldIndex, newIndex) -> {
            initAnnoncePage(newIndex.intValue());
        });
    }

    private void initAnnoncePage(int i) {
        VoyageService rs= new VoyageService();
        
        liste= new ArrayList<>();
        
        liste = rs.AfficherVoyage();
      
        paginator.setCurrentPageIndex(i);
        List<Voyage> TroisAnnonces = getAnnoncesPage(i);     
        if (TroisAnnonces.size() >= 1) {
            box.setVisible(true);
            Image img=new Image("file:/C:/xampp/htdocs/Our/web/uploads/"+TroisAnnonces.get(0).getPhoto());
            image.setImage(img);
            categorie.setText(TroisAnnonces.get(0).getCategorie());
            agence.setText(TroisAnnonces.get(0).getType());
            prix.setText(Float.toString(TroisAnnonces.get(0).getPrix()));
            box.setOnMouseClicked((MouseEvent e) -> {
                initialiserDetails(v=TroisAnnonces.get(0));
                details.setVisible(true);
                box.setVisible(false);
           box2.setVisible(false);
           box1.setVisible(false);
            });
            
           
            
        }   
        else { 
            box.setVisible(false);
        }
        if (TroisAnnonces.size() >= 2) {
             box1.setVisible(true);
            Image img=new Image("file:/C:/xampp/htdocs/Our/web/uploads/"+TroisAnnonces.get(1).getPhoto());
            image1.setImage(img);
            categorie1.setText(TroisAnnonces.get(1).getCategorie());
            agence1.setText(TroisAnnonces.get(1).getType());
            prix1.setText(Float.toString(TroisAnnonces.get(1).getPrix()));
            box1.setOnMouseClicked((MouseEvent e) -> {
                initialiserDetails(v=TroisAnnonces.get(1));
                details.setVisible(true);
                box.setVisible(false);
           box2.setVisible(false);
           box1.setVisible(false);
            });
           
            
        }   
        else { 
            box1.setVisible(false);
        }
        if (TroisAnnonces.size() >= 3) {
            box2.setVisible(true);
            Image img=new Image("file:/C:/xampp/htdocs/Our/web/uploads/"+TroisAnnonces.get(2).getPhoto());
            image2.setImage(img);
            categorie2.setText(TroisAnnonces.get(2).getCategorie());
            agence2.setText(TroisAnnonces.get(2).getType());
            prix2.setText(Float.toString(TroisAnnonces.get(2).getPrix()));
            box2.setOnMouseClicked((MouseEvent e) -> {
                initialiserDetails(v=TroisAnnonces.get(2));
                details.setVisible(true);
           box.setVisible(false);
           box2.setVisible(false);
           box1.setVisible(false);
            
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
        
        Image img=new Image("file:/C:/xampp/htdocs/Our/web/uploads/"+r.getPhoto());
            photo.setImage(img);
            categoried.setText(r.getCategorie());
            Agenced.setText(r.getType());
            descriptiond.setText(r.getDescription());
            DateDepd.setValue(LocalDate.parse(r.getDate_dep().toString()));
            dateArrivd.setValue(LocalDate.parse(r.getDate_arr().toString()));
            prix.setText(Float.toString(r.getPrix()));
            Nbr_place.setText(Integer.toString(r.getNbr_place()));
        
        
        
    }    

    @FXML
    private void annuler(ActionEvent event) {
                details.setVisible(false);
                box.setVisible(true);
                box1.setVisible(true);
                box2.setVisible(true);
               
    }
    
    
    @FXML
     public void supprimer(ActionEvent event) throws IOException {
         VoyageService rs=new VoyageService();
         System.out.println(v.getId_voyage());
         rs.SupprimerVoyage(v.getId_voyage());
        ((Node) (event.getSource())).getScene().getWindow().hide();
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("AllVoyagesFXML.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }
      @FXML
     public void Modifier(ActionEvent event) throws IOException {
         Voyage.setId_vModifier(v.getId_voyage());
        ((Node) (event.getSource())).getScene().getWindow().hide();
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("ModifierVoyageFXML.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

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
     public void Back(ActionEvent event) throws IOException {
        ((Node) (event.getSource())).getScene().getWindow().hide();
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("AcceuilLoginFXML.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    } 
    
}
