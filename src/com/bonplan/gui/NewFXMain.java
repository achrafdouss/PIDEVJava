/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bonplan.gui;

import com.bonplan.entities.Commande;
import com.bonplan.entities.Favoris;
import com.bonplan.entities.Produit;
import com.bonplan.services.CommandeService;
import com.bonplan.services.FavoriService;
import com.bonplan.services.ProduitService;
import java.io.IOException;
import java.sql.SQLException;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Side;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author bouyo
 */
public class NewFXMain extends Application {

    @Override
    public void start(Stage stage) throws IOException, SQLException {

        //Parent root = FXMLLoader.load(getClass().getResource("FXMLAjoutProduit.fxml"));
        Parent root = FXMLLoader.load(getClass().getResource("AfficherAllProduitFXML.fxml"));

// Parent root = FXMLLoader.load(getClass().getResource("FXMLListeProduit.fxml"));
//Parent root = FXMLLoader.load(getClass().getResource("AjoutProduit2FXML.fxml"));
//Parent root = FXMLLoader.load(getClass().getResource("StatisticFXML.fxml"));
 Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
//System.out.println(p.getIdProduit());
        CommandeService cs = new CommandeService();
        // System.out.println(c);

        FavoriService fs = new FavoriService();
        // System.out.println(f);
        // System.out.println(c.getProduit().getIdProduit());
        // fs.ajouterFavoris(f);
        // fs.supprimerFavoris(22);
//Parent root = FXMLLoader.load(getClass().getResource("StatisticFXML.fxml"));

       
        /*
Scene scene = new Scene(new Group());
        stage.setTitle("les catégories selon le stock");
        stage.setWidth(500);
        stage.setHeight(500);
 
        ObservableList<PieChart.Data> pieChartData =
                FXCollections.observableArrayList(
                new PieChart.Data("Grapefruit", 13),
                new PieChart.Data("Oranges", 25),
                new PieChart.Data("Plums", 10),
                new PieChart.Data("Pears", 22),
                new PieChart.Data("Apples", 30));
        final PieChart chart = new PieChart(pieChartData);
        chart.setTitle("les catégories selon le stock");
chart.setLabelLineLength(10);
chart.setLegendSide(Side.LEFT);
        ((Group) scene.getRoot()).getChildren().add(chart);
        stage.setScene(scene);
        stage.show();*/
    }

/**
 * @param args the command line arguments
 */
public static void main(String[] args) {
        launch(args);
    }

}
