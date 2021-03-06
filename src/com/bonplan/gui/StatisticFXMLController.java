/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bonplan.gui;

import com.bonplan.entities.Produit;
import com.bonplan.services.ProduitService;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import static javax.swing.UIManager.getString;

/**
 * FXML Controller class
 *
 * @author bouyo
 */
public class StatisticFXMLController implements Initializable {
private BarChart<Integer, Integer> barChart;
	@FXML
	private Button btnLoad;

	private Connection connection;

	private ObservableList data;
	@FXML
	//private BarChart<String, Integer> BarChart;
      
        private PieChart BarChart;
      
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    @FXML
	private void LoadChart(ActionEvent event) throws SQLException {
		ProduitService ps = new ProduitService();

		try {
			connection = connexionBD();
			System.out.println("test");
                        
 
	ObservableList<PieChart.Data> a = FXCollections.observableArrayList(
             new PieChart.Data("Habillement et bien etre", ps.getnbrHab()),
             new PieChart.Data("Vehicule ", ps.getnbrVeh()),
             new PieChart.Data("Immobilier", ps.getnbrImm()),
             new PieChart.Data("Informatique et multimédia", ps.getnbrInfo())


         ); 
        
       BarChart.setData(a);
       final Label caption = new Label("");
caption.setTextFill(Color.DARKORANGE);
caption.setStyle("-fx-font: 24 arial;");

for (final PieChart.Data data : BarChart.getData()) {
    data.getNode().addEventHandler(MouseEvent.MOUSE_PRESSED,
        new EventHandler<MouseEvent>() {
            @Override public void handle(MouseEvent e) {
                caption.setTranslateX(e.getSceneX());
                caption.setTranslateY(e.getSceneY());
                caption.setText(String.valueOf(data.getPieValue()) + "%");
             }
        });
}

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	private Connection connexionBD() throws SQLException {
		try {
			String dbString = "jdbc:mysql://localhost:3306/pidev";
			String user = "root";
			String password = "";
			Connection con = DriverManager.getConnection(dbString, user, password);
			System.out.println("Connexion okay");
			return con;
		} catch (SQLException ex) {
			Logger.getLogger(StatisticFXMLController.class.getName()).log(Level.SEVERE, null, ex);
		}
		return null;
	}
}
