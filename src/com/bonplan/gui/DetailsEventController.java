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
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author souab
 */
public class DetailsEventController implements Initializable {

    @FXML
    private Label eventName;
    @FXML
    private TextArea eventDescription;
    @FXML
    private Label id_owner;
    @FXML
    private Label date;
    @FXML
    private Label prix;
    @FXML
    private Label lieu;
    @FXML
    private Label nbrplace;
    @FXML
    private TextField nombre;
    Evenement e = new Evenement();
    @FXML
    private ImageView image;
    @FXML
    private TextField from;
    @FXML
    private Button buutonP;

    public void setE(Evenement e) {
        this.e = e;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    public void init() {
        UserServices us = new UserServices();
EvenementService es=new EvenementService();
        eventName.setText(e.getNomEvenement());
        eventDescription.setText(e.getDescription());
        id_owner.setText(us.AfficherUserId(e.getId_owner()).getUsername());
        date.setText(e.getDate_evenement());
        prix.setText(Integer.toString(e.getPrix()));
        lieu.setText(e.getLieu());
        nbrplace.setText(Integer.toString(e.getNbrplace()));
        Image img = new Image("file:/home/souab/html/PIDEV/web/uploads/" + e.getPhoto());
        image.setImage(img);
        if (es.getParticipation(User.getUserconnected(), e.id).id!=0){
            buutonP.setVisible(false);
            nombre.setText("Deja Participant ");
            nombre.setDisable(true);
            
        };
    }

    @FXML
    private void participer(ActionEvent event) {
        UserServices us = new UserServices();
        EvenementService es = new EvenementService();
        if (es.getParticipation(User.getUserconnected(), e.id).id == 0) {
            es.participer(e, us.AfficherUserId(User.getUserconnected()), Integer.parseInt(nombre.getText()));
            if (e.getNbrplace() - Integer.parseInt(nombre.getText()) >= 0) {
                e.setNbrplace(e.getNbrplace() - Integer.parseInt(nombre.getText()));
                es.update(e);
            }
            nbrplace.setText(Integer.toString(e.getNbrplace()));
                    if (es.getParticipation(User.getUserconnected(), e.id).id!=0){
            buutonP.setVisible(false);
            nombre.setText("Deja Participant ");
            nombre.setDisable(true);
            
        };
        }

    }

    @FXML
    private void getDirections(ActionEvent event) throws IOException {
        FXMLLoader Loader= new FXMLLoader();
        Loader.setLocation(getClass().getResource("EventDirection.fxml"));
        Loader.load();
        EventDirectionController cont=Loader.getController();
        cont.setFromTextField(from.getText());
        cont.setToTextField(e.getLieu());
        Parent p =Loader.getRoot();
        Stage stage=new Stage();
        stage.setScene(new Scene(p));
        stage.showAndWait();
    }

}
