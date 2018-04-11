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
public class MyParticipationsController_1 implements Initializable {

    @FXML
    private TableView<Evenement> table;
    @FXML
    private TableColumn<Evenement, String> categorie;
    @FXML
    private TableColumn<Evenement, String> date_evenement;
    @FXML
    private TableColumn<Evenement, String> lieu;
    @FXML
    private TableColumn<Evenement, String> nomEvenement;
        EvenementService es = new EvenementService();
    UserServices us=new UserServices();
    ObservableList<Evenement> data = FXCollections.observableArrayList(es.mesParticipations());

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        data = FXCollections.observableArrayList(es.mesParticipations());
        List<my> list=new ArrayList<>();
        es.mesParticipations().forEach((e)->{
        list.add(new my(us.AfficherUserId(User.getUserconnected()).getUsername(), es.getParticipation(User.getUserconnected(), e.getId()).getDate(),es.getParticipation(User.getUserconnected(), e.getId()).getNbre() , e.getNomEvenement(), es.getParticipation(User.getUserconnected(), e.getId()).getStatus()));
      });
list.forEach((l)->System.out.println(l.toString()) );
        categorie.setCellValueFactory(new PropertyValueFactory("categorie"));
        date_evenement.setCellValueFactory(new PropertyValueFactory("date_evenement"));
        lieu.setCellValueFactory(new PropertyValueFactory("lieu"));
        nomEvenement.setCellValueFactory(new PropertyValueFactory("nomEvenement"));
        table.setItems(data);
    }    

    @FXML
    private void annulerParticipation(ActionEvent event) {
        Evenement e =table.getSelectionModel().getSelectedItem();
        es.annulerParticipation(e.getId());
        data.remove(e);
    }
    
    public static class my{
    String name;
    String date;
    int nbre;
    String event;

        @Override
        public String toString() {
            return "my{" + "name=" + name + ", date=" + date + ", nbre=" + nbre + ", event=" + event + ", Status=" + Status + '}';
        }

 

        public  my(String name, String date, int nbre, String event, int Status) {
            this.name = name;
            this.date = date;
            this.nbre = nbre;
            this.event = event;
            this.Status = Status;
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

        public int getStatus() {
            return Status;
        }

        public void setStatus(int Status) {
            this.Status = Status;
        }
    int Status;
    }
}
