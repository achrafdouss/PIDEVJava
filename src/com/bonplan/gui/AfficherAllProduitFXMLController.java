/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bonplan.gui;

import com.bonplan.entities.Produit;
import com.bonplan.services.ProduitService;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
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
import javafx.scene.control.Button;
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
 * @author bouyo
 */
public class AfficherAllProduitFXMLController implements Initializable {

    @FXML
    private AnchorPane winodow;
    @FXML
    private Pagination paginator;
    @FXML
    private AnchorPane box;
    @FXML
    private ImageView image;
    @FXML
    private Label categorie;
    @FXML
    private Label nom;
    @FXML
    private Text prix;
    @FXML
    private Label stock;
    @FXML
    private Label description;
    @FXML
    private AnchorPane box2;
    @FXML
    private ImageView image2;
    @FXML
    private Label categorie2;
    @FXML
    private Label nom2;
    @FXML
    private Text prix2;
    @FXML
    private Text stock2;
    @FXML
    private Text description2;
        List<Produit> liste=new ArrayList<>();
    @FXML
    private Button favori;
    @FXML
    private Button modifier;
    @FXML
    private Button supprimer;
    @FXML
    private Button fovori2;
    @FXML
    private Button modifier2;
    @FXML
    private Button Sypprimer2;
Produit p = new Produit();
private ObservableList<Produit> data = FXCollections.observableArrayList();
    @FXML
    private AnchorPane details;
    @FXML
    private ImageView photo;
    @FXML
    private Text descriptionD;
    @FXML
    private Label categorieD;
    @FXML
    private Label nomD;
    @FXML
    private Label stockD;
    @FXML
    private Label prixD;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         ProduitService es= new ProduitService();

        liste = es.consulterProduit();
                if (liste.isEmpty()) {
            box.setVisible(false);
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

       if (liste.size() % 2 != 0) {
            paginator.setPageCount((liste.size() / 2) + 1);
        } else {
            paginator.setPageCount(liste.size() / 2);
        }
        
        paginator.currentPageIndexProperty().addListener((obs, oldIndex, newIndex) -> {
            initAnnoncePage(newIndex.intValue());
        });
    }
       private void initAnnoncePage(int i) {
        ProduitService es= new ProduitService();
        
        liste= new ArrayList<>();
        liste = es.consulterProduit();
        paginator.setCurrentPageIndex(i);
        List<Produit> TroisAnnonces = getAnnoncesPage(i);     
        if (TroisAnnonces.size() >= 1) {
            box.setVisible(true);
                        System.out.println("    858585858"+TroisAnnonces.get(0).getPhotoProduit());

            System.out.println("    lololololo"+TroisAnnonces.get(1).getPhotoProduit());
           

            Image img=new Image("http://localhost/uploadsimg/"+TroisAnnonces.get(0).getPhotoProduit());

            image.setImage(img);
            categorie.setText(TroisAnnonces.get(0).getCategorieProduit());

            nom.setText(TroisAnnonces.get(0).getNomProduit());
            prix.setText(Integer.toString((int) TroisAnnonces.get(0).getPrixProduit()));
            stock.setText(Integer.toString(TroisAnnonces.get(0).getStockProduit()));
            description.setText(TroisAnnonces.get(0).getDescriptionProduit());
 box.setOnMouseClicked((MouseEvent e) -> {
                initialiserDetails(p=TroisAnnonces.get(1));
                details.setVisible(true);
                box.setVisible(false);
           box2.setVisible(false);
            });
            
           
            
        }   
        else { 
            box.setVisible(false);
        }
       
        if (TroisAnnonces.size() >= 2) {
            System.out.println("    hhhhh"+TroisAnnonces.get(1).getPrixProduit());
            box2.setVisible(true);
            Image img =new Image("http://localhost/uploadsimg/"+TroisAnnonces.get(1).getPhotoProduit());
            image2.setImage(img);
           categorie2.setText(TroisAnnonces.get(1).getCategorieProduit());

            nom2.setText(TroisAnnonces.get(1).getNomProduit());
            prix2.setText(Integer.toString((int) TroisAnnonces.get(1).getPrixProduit()));
            stock2.setText(Integer.toString(TroisAnnonces.get(1).getStockProduit()));
            description2.setText(TroisAnnonces.get(1).getDescriptionProduit());
            box2.setOnMouseClicked((MouseEvent e) -> {
                initialiserDetails(p=TroisAnnonces.get(1));
                details.setVisible(true);
                box.setVisible(false);
           box2.setVisible(false);
            });
            
        }   
        else { 
            box2.setVisible(false);
        }

    }
private List<Produit> getAnnoncesPage(int i) {
        int start = 2 * i;
        int fin = start + 2;
        if (liste.size() > start) {
            if (liste.size() > fin) {
                return liste.subList(start, fin);
            } else {
                return liste.subList(start, liste.size());
            }
        }
        return liste.subList(0, 1);    
    }    
  
         @FXML
     public void supprimer(ActionEvent event) throws IOException {
         ProduitService ps=new ProduitService();
         System.out.println("kklklklk"+p.getIdProduit());
         ps.supprimerProduit(p.getIdProduit());
        ((Node) (event.getSource())).getScene().getWindow().hide();
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("AfficherAllProduitFXML.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }
    
       @FXML
     public void Modifier(ActionEvent event) throws IOException {
         Produit.setId_pModifier(p.getIdProduit());
        ((Node) (event.getSource())).getScene().getWindow().hide();
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("ModifierProduitFXML.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }
      private void initialiserDetails(Produit p) {
        
        Image img=new Image("http://localhost/uploadsimg/"+p.getPhotoProduit());
            image.setImage(img);
            nomD.setText(p.getNomProduit());
            categorieD.setText(p.getCategorieProduit());
            descriptionD.setText(p.getDescriptionProduit());
            prix.setText(Float.toString(p.getPrixProduit()));
            stockD.setText(Integer.toString(p.getStockProduit()));
        
        
        
    } 
}
