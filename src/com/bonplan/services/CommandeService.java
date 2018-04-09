/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bonplan.services;

import com.bonplan.util.DataSource;
import com.bonplan.entities.Commande;
import com.bonplan.entities.Favoris;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.bonplan.interfaces.iCommandeService;
import java.sql.PreparedStatement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author bouyo
 */
public class CommandeService implements iCommandeService{
    Connection cnx;
    Statement stmt;

   public CommandeService() {
        this.cnx = DataSource.getInstance().getConnection();
    }
    
 public void CreateProduitsCommand(Commande c) throws SQLException {
     
 try {
            String req = "insert into commandeprod (id_acheteur,quantite,id_produit) values(1,?,?)";
     System.out.println("+++++"+c.getIdAcheteur());
     System.out.println("-----"+c.getQuantiteCommandeProduit());
     System.out.println("iiiiii"+c.getIdproduit());
     
            PreparedStatement st = cnx.prepareStatement(req);
            st.setInt(1, c.getIdAcheteur());
            st.setInt(2, c.getQuantiteCommandeProduit());
            st.setInt(3,c.getIdproduit());
            
            

            st.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(VoyageService.class.getName()).log(Level.SEVERE, null, ex);
        }


    }

    @Override
    public List<Commande> consulterCommande() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

       

   
    
}