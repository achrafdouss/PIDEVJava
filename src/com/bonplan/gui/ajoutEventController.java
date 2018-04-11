/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bonplan.gui;

import com.bonplan.entities.Evenement;
import com.bonplan.entities.User;
import com.bonplan.services.EvenementService;
import com.google.maps.*;
import com.lynden.gmapsfx.GoogleMapView;
import com.lynden.gmapsfx.MapComponentInitializedListener;
import com.lynden.gmapsfx.javascript.event.UIEventType;
import com.lynden.gmapsfx.javascript.object.GoogleMap;
import com.lynden.gmapsfx.javascript.object.LatLong;
import com.lynden.gmapsfx.javascript.object.MapOptions;
import com.lynden.gmapsfx.javascript.object.MapTypeIdEnum;
import com.lynden.gmapsfx.javascript.object.Marker;
import com.lynden.gmapsfx.javascript.object.MarkerOptions;
import com.lynden.gmapsfx.service.geocoding.GeocoderStatus;
import com.lynden.gmapsfx.service.geocoding.GeocodingService;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.InputMethodEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import netscape.javascript.JSObject;

/**
 * FXML Controller class
 *
 * @author Saber
 */
public class ajoutEventController implements Initializable, MapComponentInitializedListener {

    /**
     * Initializes the controller class.
     */
    @FXML
    private TextField categorie;

    @FXML
    private TextField nbreplace;

    @FXML
    private TextField lieu;

    @FXML
    private TextField description;

    @FXML
    private TextField nomEvent;

    private TextField idowner;

    @FXML
    private DatePicker date;
    @FXML
    private TextField lat;

    @FXML
    private TextField lng;

    @FXML
    private TextField prix;
    File file;
    Stage stage;
    private GoogleMap map;
    private GeocodingService geocodingService;

    GeoApiContext context = new GeoApiContext().setApiKey("AIzaSyDx80qyb9RKt3CcYtDfyt8paY5gpMK10bc");

    List<String> hints = new ArrayList<>();
    @FXML
    private GoogleMapView mapView;
    @FXML
    private Label erreur;
    boolean valid = true;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        mapView.addMapInializedListener(this);
        date.setDayCellFactory(picker -> new DateCell() {
        public void updateItem(LocalDate date, boolean empty) {
            super.updateItem(date, empty);
            LocalDate today = LocalDate.now();

            setDisable(empty || date.compareTo(today) < 0 );
        }
    });
        nbreplace.focusedProperty().addListener((observable, oldValue, newValue) -> {

            if (!newValue) {
                if (!nbreplace.getText().matches("\\d*")) {
                    erreur.setText("le champ nombre n'accepte que des entiers positifs");
                    valid = false;

                } else if (Integer.parseInt(nbreplace.getText()) < 0) {
                    erreur.setText("le champ nombre n'accepte que des entiers positifs");
                    valid = false;

                } else {
                    erreur.setText("");
                    valid = true;

                }
            };
        });

        prix.focusedProperty().addListener((observable, oldValue, newValue) -> {

            if (!newValue) {
                if (!prix.getText().matches("\\d*")) {
                    erreur.setText("le champ prix n'accepte que des entiers positifs");
                    valid = false;

                    valid = false;
                } else if (Integer.parseInt(prix.getText()) < 0) {
                    erreur.setText("le champ prix n'accepte que des entiers positifs");
                    valid = false;

                } else {
                    erreur.setText("");
                    valid = true;

                }
            };
        });

    }
    Evenement e = new Evenement();

    @FXML
    private void ajoutPhoto(ActionEvent event) throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif")
        );
        file = fileChooser.showOpenDialog(stage);
        if (file != null) {
            System.out.println(file.getAbsolutePath());
            String img = file.getName();
            
String path = "C:/wamp64/www/PIDEV/web/uploads/";
            
                Files.copy(file.toPath(),
                        (new File(path + file.getName())).toPath(),
                        StandardCopyOption.REPLACE_EXISTING);
                System.out.println(img);
                System.out.println("jawek béhi");
                e.setPhoto(img);

            }
    }

    @FXML
    public void ajouterEvenet() {
        if (nomEvent.getText().equals("")) {
            erreur.setText("le champ nom evenement est vide");
            nomEvent.setVisible(true);
            valid = false;
        }

        if (categorie.getText().equals("")) {
            erreur.setText("le champ categorie est vide");
            categorie.setVisible(true);
            valid = false;
        }

        if (categorie.getText().equals("")) {
            erreur.setText("le champ categorie est vide");
            categorie.setVisible(true);
            valid = false;
        }
        if (description.getText().equals("")) {
            erreur.setText("le champ description est vide");
            description.setVisible(true);
            valid = false;
        }

        if (lat.getText().equals("")) {
            erreur.setText("selectionner l'adresse dans la carte");
            valid = false;
        }

        EvenementService es = new EvenementService();
        Evenement g = new Evenement();
        g.setCategorie(categorie.getText());
        g.setDate_evenement(date.getValue().format(DateTimeFormatter.ISO_DATE));
        g.setDescription(description.getText());
        g.setId_owner(User.getUserconnected());
        g.setLat(lat.getText());
        g.setLng(lng.getText());
        g.setLieu(lieu.getText());
        g.setNomEvenement(nomEvent.getText());
        g.setPrix(Integer.parseInt(prix.getText()));
        g.setNbrplace(Integer.parseInt(nbreplace.getText()));
        g.setPhoto(e.getPhoto());
        System.out.println(g.toString());
//es.createEvenement(new Evenement(categorie.getText(), Integer.parseInt(nbreplace.getText()), date.getValue().format(DateTimeFormatter.ISO_DATE), lieu.getText(), description.getText(), nomEvent.getText(), Integer.parseInt(idowner.getText()), e.getPhoto(), lat.getText(), lng.getText(), Integer.parseInt(prix.getText())));
        if (valid == true) {
            es.createEvenement(g);
            categorie.clear();
            description.clear();
            lieu.clear();
            nomEvent.clear();
            prix.clear();
            nbreplace.clear();
            System.out.println("Ajout avec sucess");
        } else {System.out.println("erreueeeeeeeeeeeeeeeeer");
        valid=true;
        erreur.setText("");
        };
    }

    @Override
    public void mapInitialized() {
        geocodingService = new GeocodingService();
        MapOptions options = new MapOptions();
        options.center(new LatLong(34.73, 10.04))
                .zoomControl(true)
                .zoom(6)
                .overviewMapControl(false)
                .mapType(MapTypeIdEnum.ROADMAP);
        map = mapView.createMap(options);
        map.addUIEventHandler(UIEventType.click, (JSObject obj) -> {
            LatLong ll = new LatLong((JSObject) obj.getMember("latLng"));
            MarkerOptions markerOptions = new MarkerOptions();
            markerOptions.position(ll);
            Marker marker = new Marker(markerOptions);
            map.clearMarkers();
            map.addMarker(marker);
            lat.setText(Double.toString(ll.getLatitude()));
            lng.setText(Double.toString(ll.getLongitude()));
            geocodingService.reverseGeocode(ll.getLatitude(), ll.getLongitude(), (com.lynden.gmapsfx.service.geocoding.GeocodingResult[] results, GeocoderStatus status) -> {
                lieu.setText(results[0].getFormattedAddress());
            });

        });

    }

    @FXML
    private void prixvalidator(InputMethodEvent event) {
        System.out.println(prix.getText());
    }
}
