/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bonplan.gui;

import com.bonplan.entities.Evenement;
import com.bonplan.services.EvenementService;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.control.Slider;
import javafx.scene.control.Spinner;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author souab
 */
public class ShowAllEventsController implements Initializable {

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
    private AnchorPane box1;
    @FXML
    private ImageView image1;
    @FXML
    private Label titre1;
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
    private Label prix;
    @FXML
    private Text lieu;
    Evenement e0, e1, e2 = new Evenement();

    List<Evenement> liste = new ArrayList<>();
    @FXML
    private ComboBox<String> cat;
    @FXML
    private ComboBox<String> adresse;
    @FXML
    private Slider prixmin;
    @FXML
    private Slider prixmax;
    @FXML
    private Button filter;
    @FXML
    private Label prixmaxlabel ;
    @FXML
    private Label prixminlabel;
    @FXML
    private Label prixminlabel1;
    @FXML
    private Label prixminlabel11;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        EvenementService es = new EvenementService();
        ObservableList<String> list = FXCollections.observableArrayList();
        liste = es.getAll();
        cat.getItems().add("");
            adresse.getItems().add("");
        liste.forEach((l) -> {
            cat.getItems().add(l.getCategorie());
            adresse.getItems().add(l.getLieu());
        });
        prixmax.setBlockIncrement(1);
        prixmax.setShowTickLabels(true);
        prixmax.setShowTickMarks(true);
        prixmax.setSnapToTicks(true);        
        prixmin.setBlockIncrement(1);
        prixmin.setShowTickLabels(true);
        prixmin.setShowTickMarks(true);
        prixmin.setSnapToTicks(true);
        prixmax.setMax(liste.stream().map(Evenement::getPrix).max(Integer::compare).get());
        prixmin.setMax(liste.stream().map(Evenement::getPrix).max(Integer::compare).get());        prixmax.setMax(liste.stream().map(Evenement::getPrix).max(Integer::compare).get());
        prixmax.setMin(liste.stream().map(Evenement::getPrix).min(Integer::compare).get());        prixmax.setMax(liste.stream().map(Evenement::getPrix).max(Integer::compare).get());
        prixmin.setMin(liste.stream().map(Evenement::getPrix).min(Integer::compare).get());
        prixmax.setValue(prixmax.getMax());
        cat.setItems(cat.getItems().stream().distinct().collect(Collectors.toCollection(FXCollections::observableArrayList)));
        adresse.setItems(adresse.getItems().stream().distinct().collect(Collectors.toCollection(FXCollections::observableArrayList)));
        cat.getSelectionModel().select("");
        adresse.getSelectionModel().select("");
prixmaxlabel.setText(Double.toString(prixmax.getValue()));
prixminlabel.setText(Double.toString(prixmin.getValue()));
prixmax.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
               prixmax.setValue(newValue.intValue());
                prixmaxlabel.setText(String.format("%.2f", prixmax.getValue()));
                filter(new ActionEvent());

            }
        });
prixmin.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
               prixmin.setValue(newValue.intValue());
                prixminlabel.setText(String.format("%.2f", prixmin.getValue()));
                filter(new ActionEvent());

            }
        });
cat.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                filter(new ActionEvent());
            }
        });
adresse.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                filter(new ActionEvent());
            }
        });
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
        EvenementService es = new EvenementService();

        //liste= new ArrayList<>();
        // liste = es.getAll();
        paginator.setCurrentPageIndex(i);
        List<Evenement> TroisAnnonces = getAnnoncesPage(i);
        if (TroisAnnonces.size() >= 1) {
            box.setVisible(true);
            e0 = TroisAnnonces.get(0);
            Image img = new Image("file:C:/wamp64/www/PIDEV/web/uploads/" + TroisAnnonces.get(0).getPhoto());

            image.setImage(img);
            prix.setText(Integer.toString(TroisAnnonces.get(0).getPrix()));
            titre.setText(TroisAnnonces.get(0).getNomEvenement());
            lieu.setText(TroisAnnonces.get(0).getLieu());

        } else {
            box.setVisible(false);
        }
        if (TroisAnnonces.size() >= 2) {
            box1.setVisible(true);
            Image img = new Image("file:C:/wamp64/www/PIDEV/web/uploads/" + TroisAnnonces.get(1).getPhoto());
            e1 = TroisAnnonces.get(1);

            image1.setImage(img);
            categorie1.setText(Integer.toString(TroisAnnonces.get(1).getPrix()));
            titre1.setText(TroisAnnonces.get(1).getNomEvenement());
            text1.setText(TroisAnnonces.get(1).getLieu());

        } else {
            box1.setVisible(false);
        }
        if (TroisAnnonces.size() >= 3) {
            box2.setVisible(true);
            e2 = TroisAnnonces.get(2);

            Image img = new Image("file:C:/wamp64/www/PIDEV/web/uploads/" + TroisAnnonces.get(2).getPhoto());
            image2.setImage(img);
            categorie2.setText(Integer.toString(TroisAnnonces.get(2).getPrix()));
            titre2.setText(TroisAnnonces.get(2).getNomEvenement());
            text2.setText(TroisAnnonces.get(2).getLieu());

        } else {
            box2.setVisible(false);
        }

    }

    private List<Evenement> getAnnoncesPage(int i) {
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
    private void DetailsEvent0(ActionEvent event) throws IOException {
        FXMLLoader Loader = new FXMLLoader();
        Loader.setLocation(getClass().getResource("DetailsEvent.fxml"));
        Parent p = Loader.load();
        DetailsEventController cont = Loader.getController();
        cont.setE(e0);
        cont.init();

        AnchorPane pane = (AnchorPane) image.getParent().getParent().getParent();
        pane.getChildren().clear();
        pane.getChildren().setAll(p);

    }

    @FXML
    private void DetailsEvent1(ActionEvent event) throws IOException {
              FXMLLoader Loader = new FXMLLoader();
        Loader.setLocation(getClass().getResource("DetailsEvent.fxml"));
        Parent p = Loader.load();
        DetailsEventController cont = Loader.getController();
        cont.setE(e1);
        cont.init();

        AnchorPane pane = (AnchorPane) image.getParent().getParent().getParent();
        pane.getChildren().clear();
        pane.getChildren().setAll(p);
    }

    @FXML
    private void DetailsEvent2(ActionEvent event) throws IOException {
        FXMLLoader Loader = new FXMLLoader();
        Loader.setLocation(getClass().getResource("DetailsEvent.fxml"));
        Parent p = Loader.load();
        DetailsEventController cont = Loader.getController();
        cont.setE(e2);
        cont.init();

        AnchorPane pane = (AnchorPane) image.getParent().getParent().getParent();
        pane.getChildren().clear();
        pane.getChildren().setAll(p);
    }

    @FXML
    private void filter(ActionEvent event) {
        EvenementService es = new EvenementService();
        liste = es.find((int) prixmax.getValue(), (int)prixmin.getValue(), adresse.getSelectionModel().getSelectedItem(), cat.getSelectionModel().getSelectedItem());
        System.out.println(adresse.getSelectionModel().getSelectedItem());
        System.out.println(cat.getSelectionModel().getSelectedItem());

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

}
