/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bonplan.gui;

import com.bonplan.entities.Commande;
import com.bonplan.entities.Produit;
import com.bonplan.entities.User;
import com.bonplan.services.CommandeService;
import com.bonplan.services.ProduitService;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
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
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author bouyo
 */
public class CommanderFXMLController extends AfficherAllProduitFXMLController {

    @FXML
    private Label nom11;
    @FXML
    private TextField quantiteInsertion;
    Produit v = new Produit();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void CommanderProd(ActionEvent event) throws IOException, SQLException {
         CommandeService srp=new CommandeService();
         ProduitService vs=new ProduitService();
       v= vs.AfficherDetailProduit(Produit.id_pModifier);

      int nbr=Integer.parseInt(quantiteInsertion.getText());
        System.out.println("aaaaaa"+v.getStockProduit());
        System.out.println(nbr);
        int test9=v.getStockProduit()-nbr;
        System.out.println(test9);
     Commande  r=new Commande(Produit.id_pModifier,nbr);
        System.out.println(r);

        if (test9>=0)         
{srp.CreateProduitsCommand(r);
vs.quantiteApresCommande(v, test9);
   

        } else {
   Alert alert = new Alert(Alert.AlertType.WARNING, " Stock insuffisant ", ButtonType.CLOSE);
                alert.show();        }
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficherAllProduitFXML.fxml"));

        loader.load();
        AnchorPane parentContent = loader.getRoot();
        window = (AnchorPane) nom11.getParent().getParent();
        AfficherAllProduitFXMLController cont = loader.getController();

        window.getChildren().setAll(parentContent); 

    }

}


