/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bonplan.gui;

import com.bonplan.entities.Diplome;
import com.bonplan.entities.Prestation;
import com.bonplan.services.DiplomeService;
import com.bonplan.services.PrestationService;
import com.lynden.gmapsfx.GoogleMapView;
import com.lynden.gmapsfx.MapComponentInitializedListener;
import com.lynden.gmapsfx.javascript.event.GMapMouseEvent;
import com.lynden.gmapsfx.javascript.event.UIEventType;
import com.lynden.gmapsfx.javascript.object.GoogleMap;
import com.lynden.gmapsfx.javascript.object.LatLong;
import com.lynden.gmapsfx.javascript.object.MapOptions;
import com.lynden.gmapsfx.javascript.object.MapTypeIdEnum;
import com.lynden.gmapsfx.service.geocoding.GeocodingService;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author dell
 */
public class AjoutPrestationController extends ListerPrestationController{

    @FXML
    private AnchorPane ajoutPrestation;
    @FXML
    private TextField prestation_titre;
    @FXML
    private TextArea prestation_description;
    @FXML
    private ComboBox<String> prestation_categorie;
    @FXML
    private TextField prestation_salaire;
    @FXML
    private ComboBox<String> prestation_lieu;
    @FXML
    private ToggleGroup prestation_salaire_check;
    @FXML
    private ToggleGroup prestation_diplome_check;
    @FXML
    private ComboBox<String> prestation_diplome_categorie;
    ObservableList<String> categoriesdiplome = FXCollections.observableArrayList("Doctorat", "Ingenieur", "Master", "Expert", "Technicien", "License", "Formation");
    @FXML
    private ComboBox<String> prestation_diplome_etablissement;
    @FXML
    private TextField prestation_diplome_annee;
    @FXML
    private ChoiceBox<String> prestation_diplome_type;
    @FXML
    private Button prestation_ajouterprestation;
    @FXML
    private RadioButton diplome_yes;
    @FXML
    private RadioButton diplome_no;
    @FXML
    private AnchorPane prestation_diplomepane;
    @FXML
    private RadioButton salaire_yes;
    @FXML
    private RadioButton salaire_no;
    @FXML
    private AnchorPane prestation_salairepane;
    private int userid;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        PrestationService ps = new PrestationService();
        DiplomeService ds = new DiplomeService();
        prestation_diplome_type.setItems(categoriesdiplome);
        prestation_categorie.setItems(ps.getCategories());
        prestation_lieu.setItems(ps.getLieux());
        prestation_diplome_categorie.setItems(ds.getCategoriesdiplome());
        prestation_diplome_etablissement.setItems(ds.getEtablissementsdiplome());
        
    }
    public void initData(int userid)
    {
        this.userid = userid;
    }

    @FXML
    public void ajoutEtudiant() {
        PrestationService pss = new PrestationService();
        DiplomeService dss = new DiplomeService();
        int diplome = 0;
        if (!checktextfields() && !checksalarirefields() && !checkdiplomefields()) {
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Confirmer");
            alert.setHeaderText(null);
            alert.setContentText("Etes vous sur de vouloir continuer ?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {

                float remuneration = 0;
                if (salaire_yes.isSelected() == true) {
                    remuneration = Float.parseFloat(prestation_salaire.getText());
                }
                if (diplome_yes.isSelected() == true) {
                    Diplome d = new Diplome(0, prestation_diplome_type.getValue(), prestation_diplome_categorie.getValue(), prestation_diplome_etablissement.getValue(), Integer.parseInt(prestation_diplome_annee.getText()));
                    dss.createDiplome(d);
                    d = dss.findbycolumns(d.getType(), d.getCategorie(), d.getEtablissement(), d.getAnnee());
                    Prestation p = new Prestation(d.getIdDiplome(), userid, prestation_titre.getText(), prestation_description.getText(),
                            remuneration, prestation_lieu.getValue(), prestation_categorie.getValue());
                    pss.createPrestation(p);
                } else {
                    Prestation p = new Prestation(0, userid, prestation_titre.getText(), prestation_description.getText(),
                            remuneration, prestation_lieu.getValue(), prestation_categorie.getValue());
                    pss.createPrestation(p);
                }
                showsuccessalert();
                clearform();
            }

        } else {
            showfailalert();
        }

    }

    public boolean checkdiplomefields() {
        if (diplome_yes.isSelected() == true) {
            if (Integer.parseInt(prestation_diplome_annee.getText()) > 2018 || Integer.parseInt(prestation_diplome_annee.getText()) < 1960) {
                prestation_diplome_annee.setText("veuillez saisir une annee correcte");
                return true;
            }
            if (prestation_diplome_categorie.getValue().isEmpty()) {
                prestation_diplome_categorie.setValue("La catégorie ne peut être vide");
                return true;
            }
            if (prestation_diplome_etablissement.getValue().isEmpty()) {
                prestation_diplome_etablissement.setValue("L'établissement ne peut être vide");
                return true;
            }
        }
        return false;
    }

    public boolean checktextfields() {
        if (prestation_titre.getText().isEmpty()) {
            prestation_titre.setText("le titre ne peut etre vide");
            return true;
        }
        if (prestation_description.getText().length() < 20) {
            prestation_description.setText("La description doit contenir au moins 20 caracteres");
            return true;
        }
        if (prestation_categorie.getValue().isEmpty()) {
            prestation_categorie.setValue("La categorie ne peut etre vide");
            return true;
        }
        if (prestation_lieu.getValue().isEmpty()) {
            prestation_lieu.setValue("Le lieu ne peut etre vide");
            return true;
        }
        return false;

    }

    @FXML
    public void showSalaire() {
        prestation_salairepane.setVisible(true);
    }

    @FXML
    public void hideSalaire() {
        prestation_salairepane.setVisible(false);
    }

    @FXML
    public void showDiplomeform() {
        prestation_diplomepane.setVisible(true);
    }

    @FXML
    public void hideDiplomeform() {
        prestation_diplomepane.setVisible(false);
    }

    private boolean checksalarirefields() {
        if (salaire_yes.isSelected() == true) {
            if (Integer.parseInt(prestation_salaire.getText()) <= 0) {
                prestation_salaire.setText("La remuneration ne peut etre vide");
                return true;
            }
        }
        return false;
    }

    public void gotoLister() throws Exception {
        FXMLLoader loader=new FXMLLoader(getClass().getResource(("listerPrestation.fxml")));
            loader.load();
            AnchorPane parentContent = loader.getRoot();
            window = (AnchorPane) prestation_categorie.getParent().getParent();
            ListerPrestationController cont=loader.getController();
 
            window.getChildren().setAll(parentContent);
    }

    private void clearform() {
        prestation_categorie.setValue(null);
        prestation_lieu.setValue(null);
        prestation_titre.clear();
        prestation_description.clear();
        prestation_salaire.clear();
        prestation_diplome_annee.clear();
        prestation_diplome_categorie.setValue(null);
        prestation_diplome_etablissement.setValue(null);
        prestation_diplome_type.setValue(null);
    }

    private void showsuccessalert() {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Ajout effectué");
        alert.setHeaderText(null);
        alert.setContentText("La prestation a été ajoutée avec succés");
        alert.showAndWait();
    }

    private void showfailalert() {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText("Une erreur s'est produite");
        alert.showAndWait();
    }

    @FXML
    private void gotoAccueil(ActionEvent event) throws IOException {
        FXMLLoader loader=new FXMLLoader(getClass().getResource(("listerPrestation.fxml")));
            loader.load();
            AnchorPane parentContent = loader.getRoot();
            

            window = (AnchorPane) prestation_categorie.getParent().getParent();
            ListerPrestationController cont=loader.getController();
 
            window.getChildren().setAll(parentContent);
    }



}
