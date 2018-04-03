/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bonplan.gui;

import com.bonplan.entities.Recommendation;
import com.bonplan.services.RecommendationService;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Pagination;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.controlsfx.control.Notifications;

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
    private ImageView photo;
    @FXML
    private Label titre2;
    @FXML
    private Button aff;
    @FXML
    private Label categorie2;
    @FXML
    private Label titred;
    @FXML
    private Label categoried;
    @FXML
    private Label nomd;
    @FXML
    private Label adressed;
    @FXML
    private Label numteld;
    @FXML
    private Label emaild;
    @FXML
    private Text descriptiond;
    @FXML
    private Button btn;
    @FXML
    private Button btn1;
    @FXML
    private Button btn2;

    @FXML
    private Text text2;
    @FXML
    private ChoiceBox<String> cate;
    List<Recommendation> liste;
    String catt = "";
    @FXML
    private AnchorPane details;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        details.setVisible(false);
        RecommendationService rs = new RecommendationService();
        liste = new ArrayList<>();
        //ObservableList<String> list = FXCollections.observableArrayList("Restaurant", "Produit", "Prestation", "Voyage", "Evenement");
        //cate.setItems(list);
        cate.getItems().add("Restaurant");
        cate.getItems().add("Produit");
        cate.getItems().add("Prestation");
        cate.getItems().add("Voyage");
        cate.getItems().add("Evenement");
        cate.setValue("aa");
        cate.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                liste = rs.AfficherTopRecommendation(newValue);
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
                    catt = newValue;
                    initAnnoncePage(0, newValue);
                }
            }
        });
        //cate.getValue().toString();

        liste = rs.AfficherAllRecommendation();

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
            initAnnoncePage(0, catt);
        }
    }

    private void setNbPages() {

        if (liste.size() % 3 != 0) {
            paginator.setPageCount((liste.size() / 3) + 1);
        } else {
            paginator.setPageCount(liste.size() / 3);
        }

        paginator.currentPageIndexProperty().addListener((obs, oldIndex, newIndex) -> {
            initAnnoncePage(newIndex.intValue(), catt);
        });
    }

    private void initAnnoncePage(int i, String cat) {
        RecommendationService rs = new RecommendationService();

        liste = new ArrayList<>();
        if (cat.equals("")) {
            liste = rs.AfficherAllRecommendation();
        } else {
            liste = rs.AfficherTopRecommendation(cat);
        }
        paginator.setCurrentPageIndex(i);
        List<Recommendation> TroisAnnonces = getAnnoncesPage(i);
        if (TroisAnnonces.size() >= 1) {
            box.setVisible(true);
            Image img = new Image("http://localhost/PIDEV/web/uploads/" + TroisAnnonces.get(0).photo);
            image.setImage(img);
            categorie.setText(TroisAnnonces.get(0).getCategorie());
            titre.setText(TroisAnnonces.get(0).getTitre());
            text.setText(TroisAnnonces.get(0).getDescription());
            box.setOnMouseClicked((MouseEvent e) -> {
                initialiserDetails(TroisAnnonces.get(0));
                details.setVisible(true);
            });
            btn.setOnMouseClicked((MouseEvent e)
                    -> {
                Recommendation.id_recModifier = TroisAnnonces.get(0).id;
                
            });

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

    private void initialiserDetails(Recommendation r) {

        Image img = new Image("http://localhost/PIDEV/web/uploads/" + r.photo);
        photo.setImage(img);
        categoried.setText(r.getCategorie());
        titred.setText(r.getTitre());
        descriptiond.setText(r.getDescription());
        nomd.setText(r.getNom());
        numteld.setText(r.num_tel);
        adressed.setText(r.getAdresse());
        emaild.setText(r.getEmail());

    }

    @FXML
    private void annuler(ActionEvent event) {
        details.setVisible(false);
    }
    @FXML
     public void commentaire(ActionEvent event) throws IOException {
        ((Node) (event.getSource())).getScene().getWindow().hide();
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("AfficherCommentaireFXML.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

}
