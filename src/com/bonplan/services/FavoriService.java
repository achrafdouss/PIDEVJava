/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bonplan.services;

import com.bonplan.util.DataSource;
import com.bonplan.entities.Favoris;
import com.bonplan.entities.Produit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.bonplan.interfaces.iFavorisService;
import com.sun.org.apache.bcel.internal.generic.D2F;
import java.sql.Array;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author bouyo
 */
public class FavoriService implements iFavorisService{
     Connection cnx;
    PreparedStatement pst;
Favoris favori = new Favoris();
    ResultSet rs;

    public FavoriService() {
        this.cnx = DataSource.getInstance().getConnection();

   }
    
 
   @Override
    public void ajouterFavoris(Favoris f)  {
         
                      
        String req1 = "insert into produitfavories (id_produit,id_owner) values(?,?) ";
         try {
             pst = cnx.prepareStatement(req1);
       pst.setInt(1, f.getProduit().getIdProduit());
       pst.setInt(2, f.getIdOwner());
        
                pst.executeUpdate();
       
            } catch (SQLException ex) {
             Logger.getLogger(FavoriService.class.getName()).log(Level.SEVERE, null, ex);
         }
    
     
     
     }
   

    @Override
    public void supprimerFavoris(int id_favori) {
     try {
            String req= "DELETE FROM `produitfavories` WHERE `id_favori` = ? ";
            PreparedStatement st = cnx.prepareStatement(req);
            st.setInt(1, id_favori);
            st.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(FavoriService.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 

    @Override
    public List<Favoris> consulterProduit() {
 ArrayList<Favoris> listN = new ArrayList<Favoris>();
       /*try {
            Statement st2 = cnx.createStatement();
            ResultSet rs = st2.executeQuery("Select * from produitfavories");
            while (rs.next()) {
                System.out.println(rs.getString(2) + " (" + rs.getString(3) + ")");
                listN.add(new Favoris(rs.getInt(1),
                        //rs.getInt(2),
                        rs.getInt(3)));
            }
            st2.close();
        } catch (SQLException ex) {
            Logger.getLogger(FavoriService.class.getName()).log(Level.SEVERE, null, ex);
        }

        return listN;
    }
*/
 
  return listN;  
}

    public void ajouterFavoris(Produit p, int owner) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    }