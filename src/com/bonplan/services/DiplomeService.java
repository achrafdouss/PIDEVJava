/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bonplan.services;

import com.bonplan.interfaces.IDiplomeService;
import com.bonplan.entities.Diplome;
import com.bonplan.entities.Prestation;
import com.bonplan.util.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author dell
 */
public class DiplomeService implements IDiplomeService{
    
    
    Connection conn = DataSource.getInstance().getConnection();
    Statement stmt;
    @Override
    public void createDiplome(Diplome d) {
        try {
            String req = "INSERT INTO Diplome(type, categorie,etablissement,date_obtention) "
                    + "VALUES (?,?,?,?)";
            PreparedStatement st = conn.prepareStatement(req);
            st.setString(1,d.getType());
            st.setString(2,d.getCategorie());
            st.setString(3,d.getEtablissement());
            st.setInt(4,d.getAnnee());
            st.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(PrestationService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @Override
    public Diplome findbycolumns(String type, String categorie, String etab, int date)
    {
        Diplome d = null;
        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("Select * from diplome where type like '"+type+"' AND categorie like '"+
                    categorie+"' AND etablissement like'"+etab+"' and date_obtention ="+date);
            if (rs.next()) {
                d = new Diplome(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getInt(5));
            }
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(PrestationService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return d; 
    }
    @Override
    public Diplome findbyid(int id)
    {
        Diplome d = null;
        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("Select * from diplome where id_diplome ="+id);
            if (rs.next()) {
                d = new Diplome(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getInt(5));
            }
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(PrestationService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return d; 
    }
    
    @Override
    public ObservableList<String> getAnneesdiplome()
    {
        ObservableList<String> annees = FXCollections.observableArrayList();
        try{
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("Select DISTINCT(date_obtention) from diplome");
            while (rs.next()) {
                annees.add(rs.getString(1));
            }
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(PrestationService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return annees;
    }
    @Override
    public ObservableList<String> getEtablissementsdiplome()
    {
        ObservableList<String> etablissements = FXCollections.observableArrayList();
        try{
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("Select DISTINCT(etablissement) from diplome");
            while (rs.next()) {
                etablissements.add(rs.getString(1));
            }
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(PrestationService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return etablissements;
    }
    @Override
    public ObservableList<String> getCategoriesdiplome()
    {
        ObservableList<String> categoriesdiplome = FXCollections.observableArrayList();
        try{
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("Select DISTINCT(categorie) from diplome");
            while (rs.next()) {
                categoriesdiplome.add(rs.getString(1));
            }
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(PrestationService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return categoriesdiplome;
    }

}
