/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bonplan.services;

import com.bonplan.entities.Recommendation;
import com.bonplan.entities.User;
import com.bonplan.entities.Voyage;
import com.bonplan.interfaces.VoyageInterface;
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


/**
 *
 * @author Radhouen
 */
public class VoyageService implements VoyageInterface{

    
    Connection cnx;
    Statement stmt;

   public VoyageService() {
        this.cnx = DataSource.getInstance().getConnection();
    }
    
    @Override
    public void AjouterVoyage(Voyage v) {
        
        
        try {
            String req = "INSERT INTO voyage (categorie,type,nbr_place,date_depart,date_arrivee,prix,description,destination,photo,id_owner) VALUES (?,?,?,?,?,?,?,?,?,?)";

            PreparedStatement st = cnx.prepareStatement(req);
            st.setString(1, v.getCategorie());
            st.setString(2, v.getType());
            st.setInt(3, v.getNbr_place());
            st.setDate(4,(java.sql.Date) v.getDate_dep());
            st.setDate(5,(java.sql.Date) v.getDate_arr());
            st.setFloat(6, v.getPrix());
            st.setString(7, v.getDescription());
            st.setString(8, v.getDestination());
            st.setString(9, v.getPhoto());
            st.setInt(10, User.getUserconnected());
            
            

            st.executeUpdate();
            System.out.println("Voyage ajoutée !!");

        } catch (SQLException ex) {
            Logger.getLogger(VoyageService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @Override
    public void ModifierVoyage(Voyage v) {
try {
            String req = "UPDATE voyage SET `categorie`= ?, `type`= ? , `nbr_place`= ? , `date_depart`= ?, `date_arrivee`= ?, `prix`= ?, `description`= ?, `destination`= ?, `photo`= ?"
                    + " WHERE id_voyage = '" + v.getId_voyage() + "'";
            PreparedStatement st = cnx.prepareStatement(req);
            st.setString(1, v.getCategorie());
            st.setString(2, v.getType());
            st.setInt(3, v.getNbr_place());
            st.setDate(4,(java.sql.Date) v.getDate_dep());
            st.setDate(5,(java.sql.Date) v.getDate_arr());
            st.setFloat(6, v.getPrix());
            st.setString(7, v.getDescription());
            st.setString(8, v.getDestination());
            st.setString(9, v.getPhoto());
            st.executeUpdate();
            System.out.println("Voyage modifié !!");

        } catch (SQLException ex) {
            Logger.getLogger(VoyageService.class.getName()).log(Level.SEVERE, null, ex);
        }




    }

    @Override
    public void SupprimerVoyage(int id_voy) {
         try {
            String req = "DELETE FROM voyage WHERE voyage.`id_voyage` = ? ";
            PreparedStatement st = cnx.prepareStatement(req);
            st.setInt(1, id_voy);
            st.executeUpdate();
            System.out.println("Voyage supprimé !!");

        } catch (SQLException ex) {
            Logger.getLogger(VoyageService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }

    @Override
    public List<Voyage> AfficherVoyage() {
 ArrayList<Voyage> list = new ArrayList<Voyage>();
        try {
            stmt = cnx.createStatement();
            ResultSet rs = stmt.executeQuery("Select * from voyage");
            while (rs.next()) {
                
                list.add(new Voyage(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getDate(5),
                        rs.getDate(6),
                        rs.getFloat(7),
                        rs.getString(8),
                        rs.getString(9)
                        
                        
                        
                        ));
            }
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(VoyageService.class.getName()).log(Level.SEVERE, null, ex);
        }

        return list;


    }

    @Override
    public Voyage AfficherDetailVoyage(int id_voy) {
        ArrayList<Voyage> listN = new ArrayList<Voyage>();
        try {
            stmt = cnx.createStatement();
            ResultSet rs = stmt.executeQuery("Select * from voyage WHERE voyage.`id_voyage` = '" + id_voy + "'");
            while (rs.next()) {
                listN.add(new Voyage(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getDate(5),
                        rs.getDate(6),
                        rs.getFloat(7),
                        rs.getString(8),
                        rs.getString(9)
                ));
            }
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(Recommendation.class.getName()).log(Level.SEVERE, null, ex);
        }

        return listN.get(0);
    }

    @Override
    public Voyage RechercherVoyage(String categorie) {
        String requete = "select * from voyage where categorie='" + categorie + "'";
        try {
            PreparedStatement ps = cnx.prepareStatement(requete);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String cat = rs.getString("categorie");
                Date date_depart  = rs.getDate("date_depart");
                Date date_arrivee = rs.getDate("date_arrivee");
                float prix = rs.getFloat("prix");
                
                return (new Voyage(categorie, date_depart, date_arrivee, prix));
            }

        } catch (SQLException ex) {
            System.out.println("erreur lors de la recherche  " + ex.getMessage());
        }
        return null;
        
        
    }

  
   
    
    
}
