/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bonplan.services;

import com.bonplan.interfaces.ICommentairePrestationService;
import com.bonplan.entities.CommentairePrestation;
import com.bonplan.entities.Prestation;
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
 * @author dell
 */
public class CommentairePrestationService implements ICommentairePrestationService{
    Connection conn = DataSource.getInstance().getConnection();
    Statement stmt;

    @Override
    public void createCommentaire(CommentairePrestation cp) {
        try {
            String req = "INSERT INTO commentaireprestation(id_prestation,id_user,contenu,nbr_signalisation) "
                    + "VALUES (?,?,?,?)";
            PreparedStatement st = conn.prepareStatement(req);
            st.setInt(1,cp.getId_prestation());
            if(cp.getId_user()!=0){st.setInt(2,cp.getId_user());}
            else{st.setNull(2,1);}
            st.setString(3,cp.getContenu());
            st.setInt(4, 0);
            st.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(PrestationService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void deleteCommentaire(int id) {
        try {
            String req= "DELETE FROM `commentaireprestation` WHERE `commentaireprestation`.`id_commentaire` = ? ";
            PreparedStatement st = conn.prepareStatement(req);
            st.setInt(1, id);
            st.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(PrestationService.class.getName()).log(Level.SEVERE, null, ex);
        }   
    }

    @Override
    public List<CommentairePrestation> getAll() {
        ArrayList<CommentairePrestation> listCommentaires = new ArrayList<>();
        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("Select * from commentaireprestation");
            while (rs.next()) {
                listCommentaires.add(new CommentairePrestation(rs.getInt(1),rs.getInt(2),rs.getInt(3),rs.getString(4),
                                                    rs.getInt(5),rs.getDate(6)));
            }
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(PrestationService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listCommentaires;   
    }

    @Override
    public List<CommentairePrestation> getAllValides(int idprest) {
        ArrayList<CommentairePrestation> listCommentaires = new ArrayList<>();
        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("Select * from commentaireprestation where nbr_signalisation = 0 and id_prestation like'"+idprest+"'");
            while (rs.next()) {
                listCommentaires.add(new CommentairePrestation(rs.getInt(1),rs.getInt(2),rs.getInt(3),rs.getString(4),
                                                    rs.getInt(5),rs.getDate(6)));
            }
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(PrestationService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listCommentaires;     
    }
    

    
}
