/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bonplan.gui;

import com.bonplan.entities.Prestation;
import com.bonplan.services.PrestationService;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author dell
 */
public class GererPrestationController extends AcceuilFXMLController {

    @FXML
    private Button button_gotoAccueil;
    @FXML
    private TableView<Prestation> allprestation;
    @FXML
    private TableView<Prestation> awaitingconfirmation;
    private int id;
    PrestationService ps = new PrestationService();
    private ObservableList<Prestation> data;
    private ObservableList<Prestation> data2;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadallprestation();
        loadawaiting();
    }

    public void initData(int id) {
        this.id = id;
    }

    public void delete(int id) throws IOException {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Confirmer ?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            ps.deletePrestation(id);

        }
    }

    public void confirm(int id) throws IOException {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Confirmer ?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            ps.validerPrestation(id);
            refresh();
        }
    }

    @FXML
    public void gotoAccueil(ActionEvent event) throws IOException {
        FXMLLoader loader=new FXMLLoader(getClass().getResource(("AcceuilFXML.fxml")));
            loader.load();
            AnchorPane parentContent = loader.getRoot();
            window = (AnchorPane) awaitingconfirmation.getParent().getParent();
            AcceuilFXMLController cont=loader.getController();
 
           window.getChildren().setAll(parentContent);
    }

    public void loadallprestation() {

        allprestation.setEditable(false);
        data = ps.getAllValides();
        TableColumn titreCol = new TableColumn("Titre");
        titreCol.setMaxWidth(250);
        TableColumn lieuCol = new TableColumn("Lieu");
        lieuCol.setMaxWidth(150);
        TableColumn categorieCol = new TableColumn("Categorie");
        categorieCol.setMaxWidth(150);
        TableColumn salaireCol = new TableColumn("Remuneration");
        salaireCol.setMinWidth(110);
        TableColumn dateCol = new TableColumn("Date");
        dateCol.setMaxWidth(100);
        TableColumn gotoCol = new TableColumn("Voir");
        gotoCol.setMaxWidth(100);
        TableColumn deleteCol = new TableColumn("Supprimer");
        deleteCol.setMaxWidth(100);
        allprestation.getColumns().addAll(titreCol, lieuCol, categorieCol, salaireCol, dateCol, gotoCol, deleteCol);
        gotoCol.setCellValueFactory(new PropertyValueFactory<Prestation, String>("button"));
        deleteCol.setCellValueFactory(new PropertyValueFactory<Prestation, String>("supprimer"));
        titreCol.setCellValueFactory(new PropertyValueFactory<Prestation, String>("titre"));
        dateCol.setCellValueFactory(new PropertyValueFactory<Prestation, String>("DateAjout"));
        categorieCol.setCellValueFactory(new PropertyValueFactory<Prestation, String>("categorie"));
        salaireCol.setCellValueFactory(new PropertyValueFactory<Prestation, String>("salaire"));
        lieuCol.setCellValueFactory(new PropertyValueFactory<Prestation, String>("lieu"));
        allprestation.setItems(data);
    }

    public void loadawaiting() {

        awaitingconfirmation.setEditable(false);
        data = ps.getAllNonValides();
        data.forEach(a -> a.toString());
        TableColumn titreCol = new TableColumn("Titre");
        titreCol.setMaxWidth(250);
        TableColumn lieuCol = new TableColumn("Lieu");
        lieuCol.setMaxWidth(150);
        TableColumn categorieCol = new TableColumn("Categorie");
        categorieCol.setMaxWidth(150);
        TableColumn salaireCol = new TableColumn("Remuneration");
        salaireCol.setMinWidth(110);
        TableColumn dateCol = new TableColumn("Date");
        dateCol.setMaxWidth(100);
        TableColumn gotoCol = new TableColumn("Voir");
        gotoCol.setMaxWidth(100);
        TableColumn confirmCol = new TableColumn("Approuver");
        confirmCol.setMaxWidth(100);
        TableColumn deleteCol = new TableColumn("Supprimer");
        deleteCol.setMaxWidth(100);
        awaitingconfirmation.getColumns().addAll(titreCol, lieuCol, categorieCol, salaireCol, dateCol, gotoCol, confirmCol, deleteCol);
        gotoCol.setCellValueFactory(new PropertyValueFactory<Prestation, String>("button"));
        deleteCol.setCellValueFactory(new PropertyValueFactory<Prestation, String>("supprimer"));
        confirmCol.setCellValueFactory(new PropertyValueFactory<Prestation, String>("approuver"));
        titreCol.setCellValueFactory(new PropertyValueFactory<Prestation, String>("titre"));
        dateCol.setCellValueFactory(new PropertyValueFactory<Prestation, String>("DateAjout"));
        categorieCol.setCellValueFactory(new PropertyValueFactory<Prestation, String>("categorie"));
        salaireCol.setCellValueFactory(new PropertyValueFactory<Prestation, String>("salaire"));
        lieuCol.setCellValueFactory(new PropertyValueFactory<Prestation, String>("lieu"));
        awaitingconfirmation.setItems(data);
    }

    private void refresh() {
        data.clear();
        data2.clear();
        awaitingconfirmation.setVisible(false);
        allprestation.setVisible(false);
        
        loadawaiting();
        loadallprestation();
        awaitingconfirmation.setVisible(true);
        allprestation.setVisible(true);
        allprestation.getColumns().get(0).setVisible(false);
        allprestation.getColumns().get(0).setVisible(true);
        awaitingconfirmation.getColumns().get(0).setVisible(false);
        awaitingconfirmation.getColumns().get(0).setVisible(true);
    }

}
