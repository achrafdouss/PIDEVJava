/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bonplan.gui;

import com.bonplan.entities.Recommendation;
import com.bonplan.services.RecommendationService;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Pagination;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author Achraf
 */
public class AfficherTopRecommendationFXMLController implements Initializable {

    @FXML
    private AnchorPane winodow;
    @FXML
    private Pagination paginator;
    @FXML
    private AnchorPane box;
    @FXML
    private ImageView image;
    @FXML
    private Label titre;
    @FXML
    private Label titre1;
    @FXML
    private Label categorie;
    @FXML
    private Text text;
    @FXML
    private AnchorPane box1;
    @FXML
    private ImageView image1;
    @FXML
    private Label categorie1;
    @FXML
    private Text text1;
    @FXML
    private AnchorPane box2;
    @FXML
    private ImageView image2;
    @FXML
    private Label titre2;
    @FXML
    private Label categorie2;
    
    @FXML
    private Text text2;
    @FXML
    private ChoiceBox<String> cate;
    List<Recommendation> liste;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         RecommendationService rs= new RecommendationService();
        liste= new ArrayList<>();
         ObservableList<String> list = FXCollections.observableArrayList("Restaurant", "Produit", "Prestation", "Voyage", "Evenement");
        cate.setItems(list);
       
    
      
       /*if(cat.equals(""))
        liste = rs.AfficherAllRecommendation();
       else if(cat.equals("Produit"))
           liste= rs.AfficherTopRecommendation("Produit");
       else if(cat.equals("Voyage"))
           liste= rs.AfficherTopRecommendation("Voyage");
       else if(cat.equals("Restaurant"))
           liste= rs.AfficherTopRecommendation("Restaurant");
       else if(cat.equals("Prestation"))
           liste= rs.AfficherTopRecommendation("Prestation");
       else if(cat.equals("Evenement"))
           liste= rs.AfficherTopRecommendation("Evenement");*/
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
        RecommendationService rs= new RecommendationService();
        
        liste= new ArrayList<>();
        liste = rs.AfficherAllRecommendation();
        paginator.setCurrentPageIndex(i);
        List<Recommendation> TroisAnnonces = getAnnoncesPage(i);     
        if (TroisAnnonces.size() >= 1) {
            box.setVisible(true);
            Image img=new Image("http://localhost/PIDEV/web/uploads/"+TroisAnnonces.get(0).photo);
            image.setImage(img);
            categorie.setText(TroisAnnonces.get(0).getCategorie());
            titre.setText(TroisAnnonces.get(0).getTitre());
            text.setText(TroisAnnonces.get(0).getDescription());
            
           
            
        }   
        else { 
            box.setVisible(false);
        }
        if (TroisAnnonces.size() >= 2) {
            box1.setVisible(true);
            Image img=new Image("http://localhost/PIDEV/web/uploads/"+TroisAnnonces.get(1).photo);
            image1.setImage(img);
            categorie1.setText(TroisAnnonces.get(1).getCategorie());
            titre1.setText(TroisAnnonces.get(1).getTitre());
            text1.setText(TroisAnnonces.get(1).getDescription());
            
           
            
        }   
        else { 
            box1.setVisible(false);
        }
        if (TroisAnnonces.size() >= 3) {
            box2.setVisible(true);
            Image img=new Image("http://localhost/PIDEV/web/uploads/"+TroisAnnonces.get(2).photo);
            image2.setImage(img);
            categorie2.setText(TroisAnnonces.get(2).getCategorie());
            titre2.setText(TroisAnnonces.get(2).getTitre());
            text2.setText(TroisAnnonces.get(2).getDescription());
            
           
            
        }   
        else { 
            box.setVisible(false);
        }

    }
private List<Recommendation> getAnnoncesPage(int i) {
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
    
}
