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
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import java.util.Date;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Radhouen
 */
public class MesReservationsFXMLController implements Initializable {

    @FXML
    private TableColumn<v, String> Dest;
    @FXML
    private TableColumn<v, Integer> nbrpla;
    @FXML
    private TableColumn<v, Float> prix;
    @FXML
    private TableColumn<v, Date> dateDep;
    @FXML
    private TableColumn<v, Date> DateArriv;
    @FXML
    private TableView<v> table;
    private ObservableList<v> data;
   ReserverService rs=new ReserverService();
   

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        data = FXCollections.observableArrayList();
        List<Reservation> reservation = null;
       
            ReserverService rs = new ReserverService();
            VoyageService vs=new VoyageService();
         
            reservation=rs.AfficherReservation(User.getUserconnected());
            
            reservation.forEach(a->data.add(new v(a.getNbr_place_resv(),vs.AfficherDetailVoyage(a.getId_voy()).getDate_dep(),vs.AfficherDetailVoyage(a.getId_voy()).getDate_arr(),vs.AfficherDetailVoyage(a.getId_voy()).getPrix()*a.getNbr_place_resv(),vs.AfficherDetailVoyage(a.getId_voy()).getDestination(),vs.AfficherDetailVoyage(a.getId_voy()).getId_voyage() )));
       
       
      
      
        nbrpla.setCellValueFactory(new PropertyValueFactory("nbr_place_resv"));
        dateDep.setCellValueFactory(new PropertyValueFactory("date_dep"));
       DateArriv.setCellValueFactory(new PropertyValueFactory("date_arr"));
       prix.setCellValueFactory(new PropertyValueFactory("prix"));
        Dest.setCellValueFactory(new PropertyValueFactory("destination"));
        
       
        
        

        table.getItems().clear();
        table.getItems().addAll(data); 
        
        table.getItems().forEach(a->System.out.println(a));
       
        // TODO
    }    
   
    @FXML
    private void AnnulerReservation(ActionEvent event) throws IOException {
    
    
        v e =table.getSelectionModel().getSelectedItem();
        rs.AnnulerReservation(e.id_voy);
        data.remove(e);
     
    
    }
    
    
     @FXML
    private void exporter_pdf_reservation(ActionEvent event) throws DocumentException {
        Document doc=new Document();
        try {
            PdfWriter.getInstance(doc,new FileOutputStream("VolRéservé.pdf"));
            doc.open();
            Paragraph p1=new Paragraph();
            Paragraph p2=new Paragraph();
            Paragraph p3=new Paragraph();
            Paragraph p4=new Paragraph();
            Paragraph p5=new Paragraph();
            Paragraph p6=new Paragraph();
           
          
            p1.add("------------------Vols réservé---------------------------");
            ObservableList<v> data=FXCollections.observableArrayList();
        
           data=(ObservableList<v>) table.getSelectionModel().getSelectedItems();
           for (int i=0; i<data.size();i++){
           p2.add("Nombre de place Reserve: "+String.valueOf(data.get(i).nbr_place_resv));
           p3.add("Prix:"+String.valueOf(data.get(i).prix));
            p4.add("Destination:"+data.get(i).destination);
            p5.add("date depart:"+data.get(i).date_dep.toString());
            p6.add("date arrive:"+data.get(i).date_arr.toString());
           
             doc.add(p1);
            doc.add(p2);
            doc.add(p3);
            doc.add(p4);
            doc.add(p5);
            doc.add(p6);
          
            doc.close();
           }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(MesReservationsFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    public static class v{
        public int nbr_place_resv;
        public Date date_dep;
        public Date date_arr;
        public float prix;
        public String destination;
        public int id_voy;

        public v(int nbr_place_resv, Date date_dep, Date date_arr, float prix, String destination,int id_voy) {
            this.nbr_place_resv = nbr_place_resv;
            this.date_dep = date_dep;
            this.date_arr = date_arr;
            this.prix = prix;
            this.destination = destination;
            this.id_voy=id_voy;
        }

        @Override
        public String toString() {
            return "v{" + "nbr_place_resv=" + nbr_place_resv + ", date_dep=" + date_dep + ", date_arr=" + date_arr + ", prix=" + prix + ", destination=" + destination + '}';
        }
        
    }
    
}
