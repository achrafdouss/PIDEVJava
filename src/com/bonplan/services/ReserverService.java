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
import com.bonplan.gui.MesReservationsFXMLController.v;
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

   
    public void AnnulerReservation(v res) {
       try {
            VoyageService vs=new VoyageService();
            ReserverService rs=new ReserverService();
            Voyage v;
           
            v=vs.AfficherDetailVoyage(res.id_voy);
            
            System.out.println("les places l9dom fel article"+v.getNbr_place());
            v.setNbr_place(v.getNbr_place()+res.nbr_place_resv);
            
            System.out.println("elli reserver l 3dad"+res.nbr_place_resv);
            System.out.println("3odnaaa"+v.getNbr_place());
           int x=v.getNbr_place();
            vs.ModifierVRes(v,x);
            System.out.println("+++++"+res.id_res);
            
            String req2="DELETE FROM reserver_voyage WHERE reserver_voyage.`id_reservation` = ? ";
            
            PreparedStatement st1 = cnx.prepareStatement(req2);
            
            st1.setInt(1, res.id_res);
            
            st1.executeUpdate();
            System.out.println("Reservation supprimé !!");

        } catch (SQLException ex) {
            Logger.getLogger(VoyageService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<Reservation> AfficherReservation(int id_user) {
ArrayList<Reservation> list = new ArrayList<Reservation>();
        try {
            stmt = cnx.createStatement();
            ResultSet rs = stmt.executeQuery("Select * from  reserver_voyage WHERE reserver_voyage.`id_inscrit` = '" + id_user + "'");
            while (rs.next()) {
                
                list.add(new Reservation(
                rs.getInt(1),
                        rs.getInt(2),
                        rs.getInt(3),
                        rs.getInt(4))
                        
                        
                    
                        
                        );
            }
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(VoyageService.class.getName()).log(Level.SEVERE, null, ex);
        }

        return list;


    }
    
    @Override
    public Reservation ReturnRes(int idInscrit, int id_voy) {
        Reservation r = new Reservation();
        String req = "Select * from reserver_voyage where `id_voyage` = ? and `id_inscrit` = ?";
        try {
            PreparedStatement st = cnx.prepareStatement(req);
            st.setInt(1, id_voy);
            st.setInt(2, idInscrit);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                r.setId_voy(rs.getInt(1));
                r.setId_inscrit(rs.getInt(2));
                r.setNbr_place_resv(rs.getInt(3));
                r.setId_resv(rs.getInt(4));
              
            }
        } catch (SQLException ex) {
            Logger.getLogger(ReserverService.class.getName()).log(Level.SEVERE, null, ex);
        }

        return r;
    }
    
    
      
}

