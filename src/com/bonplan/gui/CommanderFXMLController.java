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
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author bouyo
 */
public class CommanderFXMLController implements Initializable {

    @FXML
    private Label nom11;
    @FXML
    private JFXTextField quantiteInsertion;
    Produit p = new Produit();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void actionInsertion2(ActionEvent event) throws IOException, SQLException {
        CommandeService cs = new CommandeService();
        ProduitService ps = new ProduitService();
        p = ps.AfficherDetailProduit(Produit.id_pModifier);

        int quantite = Integer.parseInt(quantiteInsertion.getText());
        System.out.println("yaya" + p.getStockProduit());
        System.out.println(quantite);
        int test9 = p.getStockProduit() - quantite;
        System.out.println(test9);
        Commande r = new Commande(Produit.id_pModifier, User.getUserconnected(), quantite);
        System.out.println(r);

        if (test9 >= 0) {
            cs.CreateProduitsCommand(r);
            ps.quantiteApresCommande(p, test9);

        } else {
   Alert alert = new Alert(Alert.AlertType.WARNING, " Stock insuffisant ", ButtonType.CLOSE);
                alert.show();        }
        ((Node) (event.getSource())).getScene().getWindow().hide();
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("AfficherAllProduitFXML.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }
}


