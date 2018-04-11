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
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author souab
 */
public class EventBackController implements Initializable {

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

    ObservableList<Evenement> data = FXCollections.observableArrayList(es.getAll());
    @FXML
    private Button supprimer;


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
        } else {
            supprimer.setVisible(true);
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
    private void hidebutton(MouseEvent event) {
        if (table.getSelectionModel().isEmpty()) {
            supprimer.setVisible(false);

        } else {
            supprimer.setVisible(true);

        }
    }


}