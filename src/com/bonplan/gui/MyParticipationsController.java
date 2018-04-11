/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bonplan.gui;

import com.bonplan.entities.Evenement;
import com.bonplan.entities.User;
import com.bonplan.services.EvenementService;
import com.bonplan.services.UserServices;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author souab
 */
public class MyParticipationsController implements Initializable {

    @FXML
    private TableView<my> table;
    @FXML
    private TableColumn<my, String> categorie;
    @FXML
    private TableColumn<my, String> date_evenement;
    @FXML
    private TableColumn<my, String> lieu;
    @FXML
    private TableColumn<my, String> nomEvenement;

    @FXML
    private TableColumn<my, String> status;
    EvenementService es = new EvenementService();
    UserServices us = new UserServices();
    ObservableList<my> data;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        List<my> list = new ArrayList<>();
        es.mesParticipations().forEach((e) -> {list.add(new my(us.AfficherUserId(User.getUserconnected()).getUsername(), es.getParticipation(User.getUserconnected(), e.getId()).getDate(), es.getParticipation(User.getUserconnected(), e.getId()).getNbre(), e.getNomEvenement(), status(es.getParticipation(User.getUserconnected(), e.getId()).getStatus()), e.getId()));});
        //list.forEach((l)->System.out.println(l.toString()) );
        data = FXCollections.observableArrayList(list);
        categorie.setCellValueFactory(new PropertyValueFactory("name"));
        date_evenement.setCellValueFactory(new PropertyValueFactory("date"));
        lieu.setCellValueFactory(new PropertyValueFactory("nbre"));
        nomEvenement.setCellValueFactory(new PropertyValueFactory("event"));
        status.setCellValueFactory(new PropertyValueFactory("status"));
        table.setItems(data);
    }

    @FXML
    private void annulerParticipation(ActionEvent event) {
        my e = table.getSelectionModel().getSelectedItem();
        es.annulerParticipation(e.idEvent);
        data.remove(e);
    }

    public String status(int i) {
        if (i == 0) {
            return "Pending";
        }
        if (i == 1) {
            return "Confirmed";
        }
        if (i == -1) {
            return "Waiting List";
        }
        return "error";
    }

    public static class my {

        String name;
        String date;
        int nbre;
        String event;
        int idEvent;

        public int getIdEvent() {
            return idEvent;
        }

        public void setIdEvent(int idEvent) {
            this.idEvent = idEvent;
        }

        @Override
        public String toString() {
            return "my{" + "name=" + name + ", date=" + date + ", nbre=" + nbre + ", event=" + event + ", Status=" + Status + '}';
        }

        public my(String name, String date, int nbre, String event, String Status, int idEvent) {
            this.name = name;
            this.date = date;
            this.nbre = nbre;
            this.event = event;
            this.Status = Status;
            this.idEvent = idEvent;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public int getNbre() {
            return nbre;
        }

        public void setNbre(int nbre) {
            this.nbre = nbre;
        }

        public String getEvent() {
            return event;
        }

        public void setEvent(String event) {
            this.event = event;
        }

        public String getStatus() {
            return Status;
        }

        public void setStatus(String Status) {
            this.Status = Status;
        }
        String Status;
    }
}
