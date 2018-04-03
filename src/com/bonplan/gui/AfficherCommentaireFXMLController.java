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
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
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
        List<Recommendation> TroisAnnonces = getAnnoncesPage(i);
        if (TroisAnnonces.size() >= 1) {
            box.setVisible(true);
            UserServices us=UserServices.getInstance();
            User u=us
            name.setText(TroisAnnonces.get(0).);

        } else {
            box.setVisible(false);
        }
        if (TroisAnnonces.size() >= 2) {
            box1.setVisible(true);
            Image img = new Image("http://localhost/PIDEV/web/uploads/" + TroisAnnonces.get(1).photo);
            image1.setImage(img);
            categorie1.setText(TroisAnnonces.get(1).getCategorie());
            titre1.setText(TroisAnnonces.get(1).getTitre());
            text1.setText(TroisAnnonces.get(1).getDescription());
            box1.setOnMouseClicked((MouseEvent e) -> {
                initialiserDetails(TroisAnnonces.get(1));
                details.setVisible(true);
            });
            btn1.setOnMouseClicked((MouseEvent e)
                    -> {
                Recommendation.id_recModifier = TroisAnnonces.get(1).id;
                
            });

        } else {
            box1.setVisible(false);
        }
        if (TroisAnnonces.size() >= 3) {
            box2.setVisible(true);
            Image img = new Image("http://localhost/PIDEV/web/uploads/" + TroisAnnonces.get(2).photo);
            image2.setImage(img);
            categorie2.setText(TroisAnnonces.get(2).getCategorie());
            titre2.setText(TroisAnnonces.get(2).getTitre());
            text2.setText(TroisAnnonces.get(2).getDescription());
            box2.setOnMouseClicked((MouseEvent e) -> {
                initialiserDetails(TroisAnnonces.get(2));
                details.setVisible(true);
            });
            btn2.setOnMouseClicked((MouseEvent e)
                    -> {
                Recommendation.id_recModifier = TroisAnnonces.get(2).id;
                
            });

        } else {
            box2.setVisible(false);
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
