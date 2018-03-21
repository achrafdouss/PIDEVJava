/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bonplan.services;

import com.bonplan.entities.User;
import com.bonplan.interfaces.UserInterface;
import com.bonplan.util.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Achraf
 */
public class UserServices implements UserInterface{
    Connection cnx;
    Statement stmt;

    public UserServices() {
                this.cnx = DataSource.getInstance().getConnection();

    }
    

    @Override
    public void AjouterUser(User u) {
         try {
            String req = "INSERT INTO fos (id_owner,id_rec,contenu,note) VALUES (?,?,?,?)";

            PreparedStatement st = cnx.prepareStatement(req);
            st.setInt(1, c.getId_owner());
            st.setInt(2, c.getId_rec());
            st.setString(3, c.getContenu());
            st.setFloat(4, c.getNote());
            

            st.executeUpdate();
            System.out.println("commentaire ajoutée !!");

        } catch (SQLException ex) {
            Logger.getLogger(CommentaireService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void ModiferUser(int id, User u) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void SupprimerUser(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Boolean Login(String username, String password) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public User AfficherUser(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<User> getAllUser() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
