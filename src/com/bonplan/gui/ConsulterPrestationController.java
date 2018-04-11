/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bonplan.gui;

import com.bonplan.entities.CommentairePrestation;
import com.bonplan.entities.Diplome;
import com.bonplan.entities.Prestation;
import com.bonplan.services.CommentairePrestationService;
import com.bonplan.services.DiplomeService;
import com.bonplan.services.PrestationService;
import com.lynden.gmapsfx.GoogleMapView;
import com.lynden.gmapsfx.MapComponentInitializedListener;
import com.lynden.gmapsfx.javascript.object.DirectionsPane;
import com.lynden.gmapsfx.javascript.object.GoogleMap;
import com.lynden.gmapsfx.javascript.object.LatLong;
import com.lynden.gmapsfx.javascript.object.MapOptions;
import com.lynden.gmapsfx.javascript.object.MapTypeIdEnum;
import com.lynden.gmapsfx.service.directions.DirectionStatus;
import com.lynden.gmapsfx.service.directions.DirectionsResult;
import com.lynden.gmapsfx.service.directions.DirectionsService;
import com.lynden.gmapsfx.service.directions.DirectionsServiceCallback;
import com.lynden.gmapsfx.service.geocoding.GeocoderStatus;
import com.lynden.gmapsfx.service.geocoding.GeocodingResult;
import com.lynden.gmapsfx.service.geocoding.GeocodingService;
import java.net.URL;
import java.sql.Date;
import java.util.Calendar;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author dell
 */
public class ConsulterPrestationController extends ListerPrestationController implements MapComponentInitializedListener, DirectionsServiceCallback {

    private int id = 0;
    private DiplomeService ds = new DiplomeService();
    private Prestation p = new Prestation();
    private PrestationService ps = new PrestationService();
    private CommentairePrestationService cps = new CommentairePrestationService();

    @FXML
    private Label p_titre;
    @FXML
    private Label p_date;
    @FXML
    private Label p_categorie;
    @FXML
    private TextField p_commentaire;
    @FXML
    private Label p_desc;
    @FXML
    private Label p_salaire;
    @FXML
    private Label p_lieu;
    @FXML
    private ListView<String> p_listcommentaires;
    ObservableList<String> commentaires = FXCollections.observableArrayList();
    @FXML
    private Pane diplome_pane;
    @FXML
    private Label d_annee;
    @FXML
    private Label d_etablissement;
    @FXML
    private Label d_categorie;
    @FXML
    private Label d_type;
    @FXML
    private Label diplome_check;
    @FXML
    private GoogleMapView gmap;
    private GoogleMap map;
    @FXML
    private TextField addressTextField;
    private StringProperty address = new SimpleStringProperty();
    @FXML
    private Button contacter;
    @FXML
    private Button retour;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    public void initData(int a) {
        System.out.println("ya9ra fil fonction");

        this.id = a;
        try {
            filllabels();
        } catch (Exception ex) {
            System.out.println("MOCHKEL !!!!!" + ex.getMessage());
        }
        try {
            addressTextField.setText(p.getLieu());
            gmap.addMapInializedListener(this);
            address.bind(addressTextField.textProperty());
        } catch (Exception ex) {
            System.out.println(ex.getStackTrace() + "\n" + ex.getMessage());
        }
    }

    @FXML
    public void addCommentaire() {
        //System.out.println("5lat lel fonction");
        CommentairePrestation cp = new CommentairePrestation(id, 0, p_commentaire.getText(), 0);
        //System.out.println("3mal instanciation");
        cps.createCommentaire(cp);
        //System.out.println("3mal ajout :)");
        p_commentaire.setEditable(false);
        p_commentaire.setText("Votre commentaire a été ajouté avec succés");
        commentaires.clear();
        cps.getAllValides(id).forEach(a -> commentaires.add(a.getContenu()));
    }

    public void gotoLister() throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("listerPrestation.fxml"));
        Parent root = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage = (Stage) p_titre.getScene().getWindow();
        stage.hide();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    public void gotoAccueil() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(("listerPrestation.fxml")));
        loader.load();
        AnchorPane parentContent = loader.getRoot();
        window = (AnchorPane) p_titre.getParent().getParent();
        ListerPrestationController cont = loader.getController();
        window.getChildren().setAll(parentContent);
    }

    public boolean checkCommentaire() {

        if (p_commentaire.getText().isEmpty()) {
            p_commentaire.setText("Le commentaire ne peut pas être vide");
            return true;
        }
        return false;
    }

    public void filllabels() throws Exception {
        Prestation p = ps.findbyid(id);
        this.p = p;
        p_titre.setText(p.getTitre());
        p_categorie.setText(p.getCategorie());
        p_date.setText(p.getDateAjout().toString());
        p_desc.setText(p.getDescription());
        if (p.getSalaire() == 0) {
            p_salaire.setText("A négocier");
        } else {
            p_salaire.setText(Float.toString(p.getSalaire()) + " TND");
        }
        p_lieu.setText(p.getLieu().toUpperCase());
        p_listcommentaires.setItems(commentaires);
        cps.getAllValides(id).forEach(a -> commentaires.add(a.getContenu()));
        try {
            checkdiplome(p);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void checkdiplome(Prestation p) throws Exception {
        if (p.getIdDiplome() != 0) {
            diplome_pane.setVisible(true);
            Diplome d = ds.findbyid(p.getIdDiplome());
            d_categorie.setText(d.getCategorie());
            d_type.setText(d.getType());
            d_etablissement.setText(d.getEtablissement());
            d_annee.setText(Integer.toString(d.getAnnee()));
            diplome_check.setText("Diplome:");
        } else {
            diplome_check.setText("Aucun diplome");
            diplome_pane.setVisible(false);
        }
    }

    @Override
    public void mapInitialized() {
        // geocodingService = new GeocodingService();
        MapOptions mapOptions = new MapOptions();

        mapOptions.center(new LatLong(0, 0))
                .mapType(MapTypeIdEnum.ROADMAP)
                .overviewMapControl(false)
                .panControl(false)
                .rotateControl(false)
                .scaleControl(false)
                .streetViewControl(false)
                .zoomControl(false)
                .zoom(12);

        map = gmap.createMap(mapOptions);
        GeocodingService geocodingService = new GeocodingService();
        geocodingService.geocode(
                address.get(),
                (GeocodingResult[] results,
                        GeocoderStatus status) -> {

                    LatLong latLong = null;

                    if (status == GeocoderStatus.ZERO_RESULTS) {
                        Alert alert = new Alert(Alert.AlertType.ERROR, "No matching address found");
                        alert.show();
                        return;
                    } else if (results.length > 1) {
                        Alert alert = new Alert(Alert.AlertType.WARNING, "Multiple results found, showing the first one.");
                        alert.show();
                        latLong = new LatLong(results[0].getGeometry().getLocation().getLatitude(), results[0].getGeometry().getLocation().getLongitude());
                    } else {
                        latLong = new LatLong(results[0].getGeometry().getLocation().getLatitude(), results[0].getGeometry().getLocation().getLongitude());
                    }

                    map.setCenter(latLong);

                });
    }

    @Override
    public void directionsReceived(DirectionsResult dr, DirectionStatus ds) {
    }

    @FXML
    public void sendmail() {
        contacter.setDisable(true);
        final String username = "pidevbonplan@gmail.com";
        final String password = "espritpidev";

        Properties props = new Properties();
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            //TO
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse("pidevbonplan@gmail.com"));
            //content
            message.setSubject("Contact au sujet de votre offre de prestation");
            message.setText("Chers utilisateurs,"
                    + "\n\nNous vous mettons en contact entre vous concernant la prestation: " + p.getTitre() + " postée le "
                    + p.getDateAjout()
                    + "\nVous pourrez en discutez à travers cet email.\nMerci pour votre fidélité\n\nL'équipe OverAchievers, développeurs de BonPlan");

            Transport.send(message);

            showsuccessalert();

        } catch (MessagingException e) {
            showfailalert();
            throw new RuntimeException(e);
        }

    }

    private void showsuccessalert() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Mail envoyé");
        alert.setHeaderText(null);
        alert.setContentText("Une discussion a été créée sur votre boite mail avec l'auteur de la prestation.\nVeuillez consulter votre boite email");
        alert.showAndWait();
    }

    private void showfailalert() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Mail non envoyé");
        alert.setHeaderText(null);
        alert.setContentText("Une erreur s'est produite, le mail n'a pas pu être envoyé\nVeuillez réessayer plus tard");
        alert.showAndWait();
    }
    public void hidebutton()
    {
        this.retour.setVisible(false);
    }

}
