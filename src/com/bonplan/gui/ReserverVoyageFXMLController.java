/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bonplan.gui;

import com.bonplan.entities.Reservation;
import com.bonplan.entities.User;
import com.bonplan.entities.Voyage;
import com.bonplan.services.ReserverService;
import com.bonplan.services.VoyageService;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author Radhouen
 */
public class ReserverVoyageFXMLController implements Initializable {

    @FXML
    private TextField nbr_place;
       Voyage v=new Voyage();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }  
    
    @FXML
    void reserver_button(ActionEvent event) throws SQLException, ClassNotFoundException, IOException {
         ReserverService srp=new ReserverService();
         VoyageService vs=new VoyageService();
       v= vs.AfficherDetailVoyage(Voyage.id_vModifier);
       
      
        int nbr=Integer.parseInt(nbr_place.getText());
        System.out.println("aaaaaa"+v.getNbr_place());
        System.out.println(nbr);
        int test9=v.getNbr_place()-nbr;
        System.out.println(test9);
     Reservation  r=new Reservation(Voyage.id_vModifier,User.getUserconnected(),nbr);
        System.out.println(r);
        
if (test9>=0)         
{srp.ReserverVoyage(r);
vs.ModifierVRes(v, test9);
   

}
else
{
     Notifications.create().title("Reservation").text("Pas de place").showError();
}
 ((Node) (event.getSource())).getScene().getWindow().hide();
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("AllVoyagesFXML.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
       
    }
    
}
