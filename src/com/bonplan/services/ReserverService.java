/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
    package com.bonplan.services;

import com.bonplan.entities.Recommendation;
import com.bonplan.entities.Reservation;
import com.bonplan.entities.User;
import com.bonplan.entities.Voyage;
import com.bonplan.interfaces.VoyageInterface;
import com.bonplan.interfaces.ReserverInterface;

import java.util.List;
import com.bonplan.util.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import java.sql.Timestamp;


import java.util.logging.Level;
import java.util.logging.Logger;


public class ReserverService implements ReserverInterface{
    Connection cnx;
    Statement stmt;

   public ReserverService() {
        this.cnx = DataSource.getInstance().getConnection();
    }
    

    @Override
    public void ReserverVoyage(Reservation r) {
 try {
            String req = "INSERT INTO reserver_voyage (id_voyage,id_inscrit,Nb_place_reserve) VALUES (?,?,?)";
     System.out.println("+++++"+r.getId_voy());
     System.out.println("-----"+r.getId_user());
     System.out.println("iiiiii"+r.getNbr_place_resv());
     
            PreparedStatement st = cnx.prepareStatement(req);
            st.setInt(1, r.getId_voy());
            st.setInt(2, r.getId_user());
            st.setInt(3,r.getNbr_place_resv());
            
            

            st.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(VoyageService.class.getName()).log(Level.SEVERE, null, ex);
        }


    }

    @Override
    public void ModifierReservation(Reservation r) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void AnnulerReservation(int id_res) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Reservation> AfficherReservation(int id_user) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
           
}

