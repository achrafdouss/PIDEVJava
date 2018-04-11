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
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author souab
 */
public class ListerEventController implements Initializable {

    @FXML
    private TableView<Evenement> table;

 //   @FXML
 //   private TableColumn<Evenement, Integer> id;

    @FXML
    private TableColumn<Evenement, String> categorie;

    @FXML
    private TableColumn<Evenement, Integer> nbrplace;

    @FXML
    private TableColumn<Evenement, String> date_evenement;

    @FXML
    private TableColumn<Evenement, String> lieu;

    @FXML
    private TableColumn<Evenement, String> description;

    @FXML
    private TableColumn<Evenement, String> nomEvenement;

 //   @FXML
 //   private TableColumn<Evenement, Integer> id_owner;
    EvenementService es = new EvenementService();

    ObservableList<Evenement> data = FXCollections.observableArrayList(es.getMyEvents());
    @FXML
    private Button supprimer;
    @FXML
    private Button update;
    @FXML
    private Button participants;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
   //     id.setCellValueFactory(new PropertyValueFactory("id"));
        categorie.setCellValueFactory(new PropertyValueFactory("categorie"));
        nbrplace.setCellValueFactory(new PropertyValueFactory("nbrplace"));
        date_evenement.setCellValueFactory(new PropertyValueFactory("date_evenement"));
        lieu.setCellValueFactory(new PropertyValueFactory("lieu"));
        description.setCellValueFactory(new PropertyValueFactory("description"));
        nomEvenement.setCellValueFactory(new PropertyValueFactory("nomEvenement"));
   //     id_owner.setCellValueFactory(new PropertyValueFactory("id_owner"));
        table.setItems(data);
        Evenement e = new Evenement();

        if (table.getSelectionModel().isEmpty()) {
            supprimer.setVisible(false);
            update.setVisible(false);
            participants.setVisible(false);
        } else {
            supprimer.setVisible(true);
            update.setVisible(true);
            participants.setVisible(true);
        }
    }

    @FXML
    private void removeEvent(ActionEvent event) {
        Evenement e = table.getSelectionModel().getSelectedItem();
        if (e != null) {
            es.delete(e.id);
            data.remove(e);
        }
    }

    @FXML
    private void UpdateEvent(ActionEvent event) throws IOException {
        Evenement e = table.getSelectionModel().getSelectedItem();
        if (e != null) {

            ModifierEventController cont = new ModifierEventController(e);
            final FXMLLoader loader;
            loader = new FXMLLoader(getClass().getResource("ModifierEvent.fxml"));
            loader.setController(cont);
            final Parent root = loader.load();
            AnchorPane pane = new AnchorPane();
            pane = (AnchorPane) table.getParent();
            pane.getChildren().setAll(root);
        }

    }

    @FXML
    private void hidebutton(MouseEvent event) {
        if (table.getSelectionModel().isEmpty()) {
            supprimer.setVisible(false);
            update.setVisible(false);
                        participants.setVisible(false);

        } else {
            supprimer.setVisible(true);
            update.setVisible(true);
                        participants.setVisible(true);

        }
    }

    @FXML
    private void participants(ActionEvent event) throws IOException {
        Evenement e = table.getSelectionModel().getSelectedItem();
        EventParticipantsController cont = new EventParticipantsController();
        cont.setE(e);
        final FXMLLoader loader;
        loader = new FXMLLoader(getClass().getResource("EventParticipants.fxml"));
        loader.setController(cont);
        final Parent root = loader.load();
        AnchorPane pane;
        pane = (AnchorPane) table.getParent();
        pane.getChildren().setAll(root);
    }

}
