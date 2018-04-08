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
    Statement st;
    PreparedStatement pst;
    ResultSet rs;
    
    public CommandeService() {
        this.cnx = DataSource.getInstance().getConnection();

   }
 public void CreateProduitsCommand(Commande c) throws SQLException {
       String req1 = "insert into commandeprod (id_acheteur,quantite,id_produit) values(?,?,?) ";
         try {
             pst = cnx.prepareStatement(req1);
       pst.setInt(1, c.getIdAcheteur());
       pst.setInt(2, c.getQuantiteCommandeProduit());
       pst.setInt(3, c.getIdproduitroduit());
        
                pst.executeUpdate();
       
         } catch (SQLException ex) {
             Logger.getLogger(FavoriService.class.getName()).log(Level.SEVERE, null, ex);
         }
            
    
        
    }

        public float CalculerPrixProduitParQuantite(int id_produit,int quantite) throws SQLException{
        float somme = 0;
        
         String select = "SELECT prix FROM produit where id_produit = ? ;";
            pst= cnx.prepareStatement(select);
            pst.setInt(1, id_produit);
            rs = pst.executeQuery();
        
            while (rs.next()) {
             somme = rs.getFloat("prix") * quantite;
            }
        return somme;
        
        
    }

    @Override
    public List<Commande> consulterCommande() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   

   
    
}