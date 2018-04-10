/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bonplan.gui;

import com.bonplan.entities.Favoris;
import com.bonplan.entities.Produit;
import com.bonplan.entities.User;
import com.bonplan.services.FavoriService;
import com.bonplan.services.ProduitService;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
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
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
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
    @FXML
    private Button commander;
    private List<Produit> all_articles;
    ProduitService produitservice ;
    @FXML
    private AnchorPane box1;
    @FXML
    private ImageView image1;
    @FXML
    private Label categorie1;
    @FXML
    private Label nom1;
    @FXML
    private Text prix1;
    @FXML
    private Label stock1;
    @FXML
    private Label description1;
    @FXML
    private Label idp1;
    @FXML
    private Pane filter_type;
    @FXML
    private Label filt;
    @FXML
    private JFXRadioButton Habillement;
    @FXML
    private ToggleGroup type;
    @FXML
    private JFXRadioButton Immobilier;
    @FXML
    private JFXRadioButton Vehicule;
    @FXML
    private JFXRadioButton Informatique;
    @FXML
    private Label filt1;
    @FXML
    private JFXRadioButton prixup;
    @FXML
    private ToggleGroup type3;
    @FXML
    private JFXRadioButton prixdown;
    Favoris f = new Favoris();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        details.setVisible(false);

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
        ProduitService es= new ProduitService();
        
        liste= new ArrayList<>();
        liste = es.consulterProduit();
        paginator.setCurrentPageIndex(i);
        List<Produit> TroisAnnonces = getAnnoncesPage(i); 
        
  
        
        
        
        
        if (TroisAnnonces.size() >= 1) {
            box.setVisible(true);
                       // System.out.println("    858585858"+TroisAnnonces.get(0).getPhotoProduit());

            //System.out.println("    lololololo"+TroisAnnonces.get(1).getPhotoProduit());
           

            Image img=new Image("http://localhost/uploadsimg/"+TroisAnnonces.get(0).getPhotoProduit());

            image.setImage(img);
            categorie.setText(TroisAnnonces.get(0).getCategorieProduit());
            idp1.setText(Integer.toString((int)TroisAnnonces.get(0).getIdProduit()));
            System.out.println("  mmmmmmm"+idp1.getText().toString());
            nom.setText(TroisAnnonces.get(0).getNomProduit());
            prix.setText(Integer.toString((int) TroisAnnonces.get(0).getPrixProduit())+"DT");
            stock.setText(Integer.toString(TroisAnnonces.get(0).getStockProduit()));
           
            description.setText(TroisAnnonces.get(0).getDescriptionProduit());
               box.setOnMouseClicked((MouseEvent e) -> {
                initialiserDetails(p=TroisAnnonces.get(0));
                details.setVisible(true);
                box2.setVisible(false);
           box.setVisible(false);
           box1.setVisible(false);
            });
        
           
            
        }   
        else { 
            box.setVisible(false);
        }
        if (TroisAnnonces.size() >= 2) {
            box1.setVisible(true);
                        //System.out.println("    858585858"+TroisAnnonces.get(1).getPhotoProduit());

            //System.out.println("    lololololo"+TroisAnnonces.get(1).getPhotoProduit());
           

            Image img=new Image("http://localhost/uploadsimg/"+TroisAnnonces.get(1).getPhotoProduit());

            image1.setImage(img);
            categorie1.setText(TroisAnnonces.get(1).getCategorieProduit());

            nom1.setText(TroisAnnonces.get(1).getNomProduit());
            prix1.setText(Integer.toString((int) TroisAnnonces.get(1).getPrixProduit())+"DT");
            stock1.setText(Integer.toString(TroisAnnonces.get(1).getStockProduit()));
           
            description1.setText(TroisAnnonces.get(1).getDescriptionProduit());
               box1.setOnMouseClicked((MouseEvent e) -> {
                initialiserDetails(p=TroisAnnonces.get(1));
                details.setVisible(true);
                box.setVisible(false);
           box2.setVisible(false);
           box1.setVisible(false);
            });
            
     
        }else { 
            box1.setVisible(false);
        }

       
        if (TroisAnnonces.size() >= 3) {
            System.out.println("    hhhhh"+TroisAnnonces.get(2).getPrixProduit());
            box2.setVisible(true);
            Image img =new Image("http://localhost/uploadsimg/"+TroisAnnonces.get(2).getPhotoProduit());
            image2.setImage(img);
           categorie2.setText(TroisAnnonces.get(2).getCategorieProduit());

            nom2.setText(TroisAnnonces.get(2).getNomProduit());
            prix2.setText(Integer.toString((int) TroisAnnonces.get(2).getPrixProduit())+"DT");
            stock2.setText(Integer.toString(TroisAnnonces.get(2).getStockProduit()));
            description2.setText(TroisAnnonces.get(2).getDescriptionProduit());
            box2.setOnMouseClicked((MouseEvent e) -> {
                initialiserDetails(p=TroisAnnonces.get(2));

              details.setVisible(true);
           box.setVisible(false);
           box2.setVisible(false);
           box1.setVisible(false);
            });
           // System.out.println("deeetaail ");
             
            
        }   
        else { 
            box2.setVisible(false);
        }

    }
private List<Produit> getAnnoncesPage(int i) {
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
        Parent root = FXMLLoader.load(getClass().getResource("UpdateProduitFXML.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

        
        
    }
      private void initialiserDetails(Produit p) {
       /*if(User.getUserconnected()==p.getIdOwer())
                commander.setVisible(false);*/
           if (p.getStockProduit() == 0) {
            commander.setDisable(true);
          
        } else {
            commander.setDisable(false);
            
        }
        Image img=new Image("http://localhost/uploadsimg/"+p.getPhotoProduit());
            photo.setImage(img);
            nomD.setText(p.getNomProduit());
            categorieD.setText(p.getCategorieProduit());
            descriptionD.setText(p.getDescriptionProduit());
            prixD.setText(Float.toString(p.getPrixProduit()));
            stockD.setText(Integer.toString(p.getStockProduit()));
        Produit.id_pModifier=p.getIdProduit();
            System.out.println(idp1.getText().toString());
      
        
    } 
        @FXML
     public void CommanderProduit(ActionEvent event) throws IOException {
         
        ((Node) (event.getSource())).getScene().getWindow().hide();
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("CommanderFXML.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }
     
    @FXML
       private void Habillement(ActionEvent event) {
        
         all_articles = produitservice.findAllFiltrer("Habillement et bien etre");
            
                setNbPages();
                initAnnoncePage(0);
            }  
       
    @FXML
        private void Immobilier(ActionEvent event) {
        
         all_articles = produitservice.findAllFiltrer("Immobilier");
            
            } 
    @FXML
          private void Vehicule(ActionEvent event) {
        
         all_articles = produitservice.findAllFiltrer("Vehicule");
            
                setNbPages();
                initAnnoncePage(0);
            } 
    @FXML
              private void Informatique(ActionEvent event) {
        
         all_articles = produitservice.findAllFiltrer("Informatique et multimédia");
            
                setNbPages();
                initAnnoncePage(0);
            } 

    @FXML
    private void prixup(ActionEvent event) {
    }

    @FXML
    private void prixdown(ActionEvent event) {
    }

    @FXML
    private void Favoris() throws IOException {
         FavoriService ps=new FavoriService();
        System.out.println("Fooovooriii"+f.getProduit().getIdProduit());
        System.out.println("hethi"+f);
         /*ps.ajouterFavoris(f);
        ((Node) (event.getSource())).getScene().getWindow().hide();
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("AfficherAllProduitFXML.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();*/
    }
              
       
    }



