/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bonplan.services;

import com.bonplan.entities.Recommendation;
import com.bonplan.interfaces.RecommendationInterfaces;
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
 * @author Achraf
 */
public class RecommendationService implements RecommendationInterfaces {

    Connection cnx;
    Statement stmt;

    public RecommendationService() {
        this.cnx = DataSource.getInstance().getConnection();
    }

    @Override
    public void AjoutRecommendation(Recommendation r) {
        try {
            String req = "INSERT INTO recommendation (id_owner,titre,categorie,description,nom,adresse,num_tel,email,note,photo) VALUES (?,?,?,?,?,?,?,?,?,?)";

            PreparedStatement st = cnx.prepareStatement(req);
            st.setInt(1, r.getId_owner());
            st.setString(2, r.getTitre());
            st.setString(3, r.getCategorie());
            st.setString(4, r.getDescription());
            st.setString(5, r.getNom());
            st.setString(6, r.getAdresse());
            st.setString(7, r.getNum_tel());
            st.setString(8, r.getEmail());
            st.setFloat(9, r.getNote());
            st.setString(10, r.getPhoto());

            st.executeUpdate();
            System.out.println("Recommendation ajoutée !!");

        } catch (SQLException ex) {
            Logger.getLogger(RecommendationService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void ModifierRecommendation(int id, Recommendation r) {
        try {
            String req = "UPDATE recommendation SET id_owner = ?, `titre`= ? , `categorie`= ? , `description`= ?, `nom`= ?, `adresse`= ?, `num_tel`= ?, `email`= ?, `note`= ?, `photo`= ?"
                    + " WHERE id = '" + id + "'";
            PreparedStatement st = cnx.prepareStatement(req);
            st.setInt(1, r.getId_owner());
            st.setString(2, r.getTitre());
            st.setString(3, r.getCategorie());
            st.setString(4, r.getDescription());
            st.setString(5, r.getNom());
            st.setString(6, r.getAdresse());
            st.setString(7, r.getNum_tel());
            st.setString(8, r.getEmail());
            st.setFloat(9, r.getNote());
            st.setString(10, r.getPhoto());

            st.executeUpdate();
            System.out.println("Recommendation modilfer !!");

        } catch (SQLException ex) {
            Logger.getLogger(RecommendationService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void SupprimerRecommendation(int id_rec) {
        try {
            System.out.println("-------"+id_rec);
            String req = "DELETE FROM recommendation WHERE recommendation.`id` = ? ";
            PreparedStatement st = cnx.prepareStatement(req);
            st.setInt(1, id_rec);
            st.executeUpdate();
            System.out.println("Recommendation supprimé !!");

        } catch (SQLException ex) {
            Logger.getLogger(RecommendationService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<Recommendation> AfficherAllRecommendation() {
        ArrayList<Recommendation> listN = new ArrayList<Recommendation>();
        try {
            stmt = cnx.createStatement();
            ResultSet rs = stmt.executeQuery("Select * from recommendation");
            while (rs.next()) {
                //System.out.println("titre " + rs.getString(3) + "description  " + rs.getString(4) );
                listN.add(new Recommendation(
                        rs.getInt(1),
                        rs.getInt(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getString(9),
                        rs.getFloat(10),
                        rs.getString(11)));
            }
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(Recommendation.class.getName()).log(Level.SEVERE, null, ex);
        }

        return listN;
    }

    public List<Recommendation> AfficherRecommendationById(int id_owner) {
        ArrayList<Recommendation> listN = new ArrayList<Recommendation>();
        try {
            stmt = cnx.createStatement();
            ResultSet rs = stmt.executeQuery("Select * from recommendation where id_owner='" + id_owner + "'");
            while (rs.next()) {
                //System.out.println("titre " + rs.getString(3) + "description  " + rs.getString(4) );
                listN.add(new Recommendation(
                        
                        rs.getInt(1),
                        rs.getInt(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getString(9),
                        rs.getFloat(10),
                        rs.getString(11)));
                //System.out.println(rs.getInt(1));
            }
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(Recommendation.class.getName()).log(Level.SEVERE, null, ex);
        }

        return listN;
    }

    @Override
    public Recommendation AfficherDetailRecommendation(int id_rec) {

        ArrayList<Recommendation> listN = new ArrayList<Recommendation>();
        try {
            stmt = cnx.createStatement();
            ResultSet rs = stmt.executeQuery("Select * from recommendation WHERE recommendation.`id` = '" + id_rec + "'");
            while (rs.next()) {
                System.out.println("titre " + rs.getString(3) + "description  " + rs.getString(4));
                listN.add(new Recommendation(
                        rs.getInt(1),
                        rs.getInt(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getString(9),
                        rs.getFloat(10),
                        rs.getString(11)));
            }
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(Recommendation.class.getName()).log(Level.SEVERE, null, ex);
        }

        return listN.get(0);
    }

    @Override
    public List<Recommendation> AfficherTopRecommendation(String categorie) {
        ArrayList<Recommendation> listN = new ArrayList<Recommendation>();
        try {
            stmt = cnx.createStatement();
            ResultSet rs = stmt.executeQuery("Select * from recommendation WHERE recommendation.`categorie` = '" + categorie + "'ORDER BY note ASC");
            while (rs.next()) {
                System.out.println("titre " + rs.getString(3) + "description  " + rs.getString(4));
                listN.add(new Recommendation(
                        rs.getInt(1),
                        rs.getInt(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getString(9),
                        rs.getFloat(10),
                        rs.getString(11)));
            }
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(Recommendation.class.getName()).log(Level.SEVERE, null, ex);
        }

        return listN;
    }
}
