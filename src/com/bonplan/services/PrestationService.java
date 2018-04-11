/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bonplan.services;

import com.bonplan.interfaces.IPrestationService;
import com.bonplan.entities.Prestation;
import com.bonplan.util.DataSource;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author yosri
 */
public class PrestationService implements IPrestationService{
    
    Connection conn = DataSource.getInstance().getConnection();
    Statement stmt;

    @Override
    public void createPrestation(Prestation p) {
        try {
            String req = "INSERT INTO Prestation(id_inscrit,id_diplome,titre,description,salaire,lieu,date_ajout,categorie,statut,valide) "
                    + "VALUES (?,?,?,?,?,?,?,?,1,0)";
            PreparedStatement st = conn.prepareStatement(req);
            if(p.getIdInscrit()!=0){st.setInt(1,p.getIdInscrit());}
            else{st.setNull(1,1);}
            if(p.getIdDiplome()!=0){st.setInt(2,p.getIdDiplome());}
            else{st.setNull(2, 1 );}
            st.setString(3,p.getTitre());
            st.setString(4,p.getDescription());
            if(p.getSalaire()!=0){st.setFloat(5,p.getSalaire());}
            else{st.setNull(5, 1 );}
            st.setString(6,p.getLieu());
            st.setDate(7,p.getDateAjout());
            st.setString(8,p.getCategorie());
            st.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(PrestationService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public ObservableList<Prestation> getAllValides() {
        ObservableList<Prestation> data =FXCollections.observableArrayList();
        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("Select * from prestation where valide = 1 and statut = 1");
            while (rs.next()) {
                data.add(new Prestation(rs.getInt(1),rs.getInt("id_inscrit"),rs.getInt("id_diplome"),rs.getString(4),
                                                    rs.getString(5),rs.getFloat(6),rs.getString(7),rs.getDate(8),
                                                    rs.getString(9),rs.getBoolean(10), rs.getBoolean(11)));
            }
            
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(PrestationService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return data;
    }
    

    @Override
    public void updatePrestation(Prestation p) {
        try {
            String req = "UPDATE `prestation` SET `titre` = ?, `description` = ?, `salaire` = ?, `date_ajout` = ?,"
                    + "`statut` = 1, valide = 0"
                    + " WHERE `id_prestation` = ?";

            PreparedStatement st = conn.prepareStatement(req);
            st.setString(1, p.getTitre());
            st.setString(2, p.getDescription());
            st.setFloat(3, p.getSalaire());
            st.setDate(4, p.getDateAjout());
            
            st.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(PrestationService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void desactiverPrestation(int id) {
        try {
            String req= "UPDATE `prestation` SET statut = 0 WHERE `id_prestation` = ?";
            PreparedStatement st = conn.prepareStatement(req);
            st.setInt(1, id);
            st.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(PrestationService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void deletePrestation(int id) {
        try {
            String req= "UPDATE `prestation` SET statut = 0 WHERE `id_prestation` = ?";
            PreparedStatement st = conn.prepareStatement(req);
            st.setInt(1, id);
            st.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(PrestationService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @Override
    public void validerPrestation(int id)
    {
        try {
            String req= "UPDATE `prestation` SET valide = 1 WHERE `id_prestation` = ?";
            PreparedStatement st = conn.prepareStatement(req);
            st.setInt(1, id);
            st.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(PrestationService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    public ObservableList<String> getCategories()
    {
        ObservableList<String> categories = FXCollections.observableArrayList();
        try{
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("Select DISTINCT(categorie) from prestation where valide = 1");
            while (rs.next()) {
                categories.add(rs.getString(1));
            }
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(PrestationService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return categories;
            
    }
    public ObservableList<String> getLieux()
    {
        ObservableList<String> lieux = FXCollections.observableArrayList();
        try{
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("Select DISTINCT(lieu) from prestation where valide = 1");
            while (rs.next()) {
                lieux.add(rs.getString(1));
            }
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(PrestationService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lieux;
    }

    @Override
    public Prestation findbyid(int id) {
        Prestation p = null;
        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("Select * from prestation where id_prestation = "+id);
            if (rs.next()) {
                p = new Prestation(rs.getInt(1),rs.getInt("id_diplome"),rs.getInt("id_inscrit"),rs.getString(4),
                                                    rs.getString(5),rs.getFloat(6),rs.getString(7),rs.getDate(8),
                                                    rs.getString(9),rs.getBoolean(10), rs.getBoolean(11));
            }
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(PrestationService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return p;    
    }

    public ObservableList<Prestation> chercher(String text) {
        ObservableList<Prestation> data =FXCollections.observableArrayList();
        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM `prestation` WHERE `titre` like '%"+text
                    +"%' or `lieu` like '%"+text+"%' or `categorie` like '%"+text+"%' or `date_ajout` like '%"+text+"%' and valide = 1"
                    + " and statut = 1");
            while (rs.next()) {
                data.add(new Prestation(rs.getInt(1),rs.getInt("id_inscrit"),rs.getInt("id_diplome"),rs.getString(4),
                                                    rs.getString(5),rs.getFloat(6),rs.getString(7),rs.getDate(8),
                                                    rs.getString(9),rs.getBoolean(10), rs.getBoolean(11)));
            }
            
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(PrestationService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return data;
       }

    public ObservableList<Prestation> getAllNonValides() {
        ObservableList<Prestation> data =FXCollections.observableArrayList();
        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("Select * from prestation where valide = 0 and statut = 1");
            while (rs.next()) {
                data.add(new Prestation(rs.getInt(1),rs.getInt("id_inscrit"),rs.getInt("id_diplome"),rs.getString(4),
                                                    rs.getString(5),rs.getFloat(6),rs.getString(7),rs.getDate(8),
                                                    rs.getString(9),rs.getBoolean(10), rs.getBoolean(11)));
            }
            
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(PrestationService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return data;
    }
    
    
    
}
