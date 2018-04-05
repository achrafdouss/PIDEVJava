/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bonplan.gui;

import com.bonplan.entities.Commentaire;
import com.bonplan.entities.Recommendation;
import com.bonplan.entities.User;
import com.bonplan.services.CommentaireService;
import com.bonplan.services.RecommendationService;
import com.bonplan.services.UserServices;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
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
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.controlsfx.control.Rating;

/**
 * FXML Controller class
 *
 * @author Achraf
 */
public class AfficherCommentaireFXMLController implements Initializable {

    @FXML
    private AnchorPane box;
    @FXML
    private Text contenu;
    @FXML
    private Label name;
    @FXML
    private Rating rating;
    @FXML
    private AnchorPane box1;
    @FXML
    private Text contenu1;
    @FXML
    private Label name1;
    @FXML
    private Rating rating1;
    @FXML
    private AnchorPane box2;
    @FXML
    private Text contenu2;
    @FXML
    private Label nom2;
    @FXML
    private Rating rating2;
    @FXML
    private AnchorPane box4;
    @FXML
    private Rating rating3;
    @FXML
    private TextArea contenu3;
    @FXML
    private Pagination paginator;
      private ObservableList<Commentaire> data;
       List<Commentaire> liste;
       Commentaire c = new Commentaire();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
           //details.setVisible(false);
        CommentaireService cs = new CommentaireService();
        liste = new ArrayList<>();
        //ObservableList<String> list = FXCollections.observableArrayList("Restaurant", "Produit", "Prestation", "Voyage", "Evenement");
        //cate.setItems(list);
        
        liste = cs.AfficherCommentaire(Recommendation.id_recModifier);

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
        CommentaireService cs = new CommentaireService();

        liste = new ArrayList<>();
        
            liste = cs.AfficherCommentaire(Recommendation.id_recModifier);
        
        paginator.setCurrentPageIndex(i);
        List<Commentaire> TroisAnnonces = getAnnoncesPage(i);
        if (TroisAnnonces.size() >= 1) {
            box.setVisible(true);
            UserServices us=UserServices.getInstance();
            User u=us.AfficherUserId(TroisAnnonces.get(0).id_owner);
            name.setText(u.getNom());
            contenu.setText(TroisAnnonces.get(0).getContenu());
            rating.setRating(TroisAnnonces.get(0).note);
            


        } else {
            box.setVisible(false);
        }
        if (TroisAnnonces.size() >= 2) {
            box1.setVisible(true);
           
            UserServices us=UserServices.getInstance();
            User u=us.AfficherUserId(TroisAnnonces.get(1).id_owner);
            name1.setText(u.getNom());
            contenu1.setText(TroisAnnonces.get(1).getContenu());
            rating1.setRating(TroisAnnonces.get(1).note);

        } else {
            box1.setVisible(false);
        }
        if (TroisAnnonces.size() >= 3) {
           box2.setVisible(true);
            UserServices us=UserServices.getInstance();
            User u=us.AfficherUserId(TroisAnnonces.get(2).id_owner);
            nom2.setText(u.getNom());
            contenu2.setText(TroisAnnonces.get(2).getContenu());
            rating2.setRating(TroisAnnonces.get(2).note);

        } else {
            box2.setVisible(false);
        }

    }
     private List<Commentaire> getAnnoncesPage(int i) {
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
     public void AjouterCommentaire(ActionEvent event) throws IOException {
        CommentaireService cs=new CommentaireService();
         System.out.println(contenu3.getText());
         System.out.println(User.getUserconnected());
         System.out.println(rating3.getRating());
         System.out.println(Recommendation.id_recModifier);
        c.setContenu(contenu3.getText());
        c.setId_owner(User.getUserconnected());
        c.setNote(rating3.getRating());
        c.setId_rec(Recommendation.id_recModifier);
        cs.AjoutCommentaire(c);
        
         ((Node) (event.getSource())).getScene().getWindow().hide();
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("AfficherCommentaireFXML.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }
     @FXML
     public void back(ActionEvent event) throws IOException {
        ((Node) (event.getSource())).getScene().getWindow().hide();
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("AfficherTopRecommendationFXML.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }
    
}
