/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bonplan.services;

import com.bonplan.entities.Voyage;
import com.bonplan.interfaces.VoyageInterface;
import com.bonplan.util.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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
            String req = "INSERT INTO voyage (categorie,type,nbr_place,date_depart,date_arrivee,prix,description,destination,photo) VALUES (?,?,?,?,?,?,?,?,?)";

            PreparedStatement st = cnx.prepareStatement(req);
            st.setString(1, v.getCategorie());
            st.setString(2, v.getType());
            st.setInt(3, v.getNbr_place());
            st.setString(4, v.getDate_dep());
            st.setString(5, v.getDate_arr());
            st.setFloat(6, v.getPrix());
            st.setString(7, v.getDescription());
            st.setString(8, v.getDestination());
            st.setString(9, v.getPhoto());
            
            

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
            st.setString(4, v.getDate_dep());
            st.setString(5, v.getDate_arr());
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
                System.out.println("Categorie " + rs.getString(2) + "DateDepart " + rs.getString(5) + "DateArrivee " + rs.getString(6) + "Prix " + rs.getFloat(7));
                list.add(new Voyage(rs.getString(2),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getFloat(7)
                        
                        
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Voyage RechercherVoyage(String categorie) {
        String requete = "select * from voyage where categorie='" + categorie + "'";
        try {
            PreparedStatement ps = cnx.prepareStatement(requete);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String cat = rs.getString("categorie");
                String date_depart  = rs.getString("date_depart");
                String date_arrivee = rs.getString("date_arrivee");
                float prix = rs.getFloat("prix");
                
                return (new Voyage(categorie, date_depart, date_arrivee, prix));
            }

        } catch (SQLException ex) {
            System.out.println("erreur lors de la recherche  " + ex.getMessage());
        }
        return null;
        
        
    }

  
   
    
    
}
