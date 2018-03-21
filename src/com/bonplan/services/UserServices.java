/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bonplan.services;

import com.bonplan.entities.Commentaire;
import com.bonplan.entities.Recommendation;
import com.bonplan.entities.User;
import com.bonplan.interfaces.UserInterface;
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
public class UserServices implements UserInterface {

    Connection cnx;
    Statement stmt;

    public UserServices() {
        this.cnx = DataSource.getInstance().getConnection();

    }

    @Override
    public void AjouterUser(User u) {
        try {
            String req = "INSERT INTO fos_user (username,email,enabled,password,confirmation_token,nom,prenom,addresse,telephone) VALUES (?,?,?,?,?,?,?,?,?)";

            PreparedStatement st = cnx.prepareStatement(req);
            st.setString(1, u.getUsername());
            st.setString(2, u.getEmail());
            st.setInt(3, u.getEnabled());
            st.setString(4, u.getPassword());
            st.setString(4, u.getConfirmation_token());
            st.setString(4, u.getNom());
            st.setString(4, u.getPrenom());
            st.setString(4, u.getAddresse());
            st.setString(4, u.getTelephone());

            st.executeUpdate();
            System.out.println("user ajoutée !!");

        } catch (SQLException ex) {
            Logger.getLogger(UserServices.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void ModiferUser(int id, User u) {
        try {
            String req = "UPDATE fos_user SET username = ?, `email`= ? , `enabled`= ? , `password`= ?, `confirmation_token`= ?, `nom`= ?, `prenom`= ?, `addresse`= ?, `telephone`= ?"
                    + " WHERE id_com = '" + u.getId() + "'";
            PreparedStatement st = cnx.prepareStatement(req);
            st.setString(1, u.getUsername());
            st.setString(2, u.getEmail());
            st.setInt(3, u.getEnabled());
            st.setString(4, u.getPassword());
            st.setString(4, u.getConfirmation_token());
            st.setString(4, u.getNom());
            st.setString(4, u.getPrenom());
            st.setString(4, u.getAddresse());
            st.setString(4, u.getTelephone());

            st.executeUpdate();
            System.out.println("user modilfer !!");

        } catch (SQLException ex) {
            Logger.getLogger(UserServices.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void SupprimerUser(int id) {
        try {
            String req = "DELETE FROM fos_user WHERE fos_user.`id` = ? ";
            PreparedStatement st = cnx.prepareStatement(req);
            st.setInt(1, id);
            st.executeUpdate();
            System.out.println("user supprimé !!");

        } catch (SQLException ex) {
            Logger.getLogger(UserServices.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public Boolean Login(String username, String password) {

        try {
            stmt = cnx.createStatement();
            ResultSet rs = stmt.executeQuery("Select * from fos_user WHERE fos_user.`username` = '" + username + "'and  fos_user.`password` = '" + password + " ");
            if (rs == null) {
                System.out.println("login failed");
                return false;
            }

            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }

        return true;
    }

    @Override
    public User AfficherUser(int id) {
 ArrayList<User> listN = new ArrayList<User>();
        try {
            stmt = cnx.createStatement();
            ResultSet rs = stmt.executeQuery("Select * from fos_user where fos_user.`id`'"+id+"'");
            while (rs.next()) {
                System.out.println("id " + rs.getString(1) + "contenu  " + rs.getString(4));
                listN.add(new User(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getString(9),
                        rs.getString(10)
                ));
            }
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(Recommendation.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listN.get(0);
    }

    @Override
    public List<User> getAllUser() {
        ArrayList<User> listN = new ArrayList<User>();
        try {
            stmt = cnx.createStatement();
            ResultSet rs = stmt.executeQuery("Select * from fos_user");
            while (rs.next()) {
                System.out.println("id " + rs.getString(1) + "contenu  " + rs.getString(4));
                listN.add(new User(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getString(9),
                        rs.getString(10)
                ));
            }
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(Recommendation.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listN;
    }

}
