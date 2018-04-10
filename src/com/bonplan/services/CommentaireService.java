/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bonplan.services;

import com.bonplan.entities.Commentaire;
import com.bonplan.entities.Recommendation;
import com.bonplan.interfaces.CommentaireInterface;
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
public class CommentaireService implements CommentaireInterface {

    Connection cnx;
    Statement stmt;

    public CommentaireService() {
        this.cnx = DataSource.getInstance().getConnection();
    }

    @Override
    public void AjoutCommentaire(Commentaire c) {
        try {
            String req = "INSERT INTO commentaire (id_owner,id_rec,contenu,note) VALUES (?,?,?,?)";

            PreparedStatement st = cnx.prepareStatement(req);
            st.setInt(1, c.getId_owner());
            st.setInt(2, c.getId_rec());
            st.setString(3, c.getContenu());
            st.setDouble(4, c.getNote());

            st.executeUpdate();
            System.out.println("commentaire ajoutée !!");

        } catch (SQLException ex) {
            Logger.getLogger(CommentaireService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void ModifierCommentaire(int id, Commentaire c) {
        try {
            String req = "UPDATE commentaire SET id_owner = ?, `id_rec`= ? , `contenu`= ? , `note`= ?"
                    + " WHERE id_com = '" + id + "'";
            PreparedStatement st = cnx.prepareStatement(req);
            st.setInt(1, c.getId_owner());
            st.setInt(2, c.getId_rec());
            st.setString(3, c.getContenu());
            st.setDouble(4, c.getNote());

            st.executeUpdate();
            System.out.println("Recommendation modilfer !!");

        } catch (SQLException ex) {
            Logger.getLogger(RecommendationService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void SupprimerCommentaire(int id_com) {
        try {
            String req = "DELETE FROM commentaire WHERE commentaire.`id_com` = ? ";
            PreparedStatement st = cnx.prepareStatement(req);
            st.setInt(1, id_com);
            st.executeUpdate();
            System.out.println("commentaire supprimé !!");

        } catch (SQLException ex) {
            Logger.getLogger(CommentaireService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<Commentaire> AfficherCommentaire(int r) {
        ArrayList<Commentaire> listN = new ArrayList<Commentaire>();
        try {
            stmt = cnx.createStatement();
            ResultSet rs = stmt.executeQuery("Select * from commentaire WHERE commentaire.`id_rec` = '" + r + "'");
            while (rs.next()) {
                System.out.println("id " + rs.getString(1) + "contenu  " + rs.getString(4));
                listN.add(new Commentaire(
                        rs.getInt(1),
                        rs.getInt(2),
                        rs.getInt(3),
                        rs.getString(4),
                        rs.getFloat(5)
                ));
            }
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(Recommendation.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listN;
    }

    public void UpdateNote(int id_rec) throws SQLException {
        
String req = "UPDATE recommendation SET note =(Select avg(note) from commentaire WHERE commentaire.`id_rec` = '" + id_rec + "')"
                    + " WHERE id = '" + id_rec + "'";
            PreparedStatement st = cnx.prepareStatement(req);
            

            st.executeUpdate();
            System.out.println("note modilfer !!");

    }

}
