/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bonplan.services;

import com.bonplan.util.DataSource;
import com.bonplan.entities.Produit;
import com.bonplan.entities.Recommendation;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.bonplan.interfaces.iProduitService;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author bouyo
 */
public class ProduitService implements iProduitService {

    Connection cnx;
    Statement stmt;

    public ProduitService() {
        this.cnx = DataSource.getInstance().getConnection();
    }

    @Override
    public void ajouterProduit(Produit p) {

        try {
            String req = "INSERT INTO produit (id_owner,nom_produit,description,photo,prix_produit,stock_produit,categorie_produit,video) VALUES (1,?,?,?,?,?,?,?)";

            PreparedStatement st = cnx.prepareStatement(req);
            st.setString(1, p.getNomProduit());
            st.setString(2, p.getDescriptionProduit());
            st.setString(3, p.getPhotoProduit());
            st.setFloat(4, p.getPrixProduit());
            st.setInt(5, p.getStockProduit());
            st.setString(6, p.getCategorieProduit());
            st.setString(7, p.getVideoProduit());

            st.executeUpdate();
            System.out.println("Produit ajoutée !!");

        } catch (SQLException ex) {
            Logger.getLogger(ProduitService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void supprimerProduit(int id_produit) {
        try {
            String req = "DELETE FROM produit WHERE produit.`id_produit` = ? ";
            PreparedStatement st = cnx.prepareStatement(req);
            st.setInt(1, id_produit);
            st.executeUpdate();
            System.out.println("Produit supprimé !!");

        } catch (SQLException ex) {
            Logger.getLogger(ProduitService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    
    
    @Override
    public List<Produit> consulterProduit() {
        ArrayList<Produit> listN = new ArrayList<Produit>();
        try {
            stmt = cnx.createStatement();
            ResultSet rs = stmt.executeQuery("Select * from produit");
            while (rs.next()) {
                System.out.println("nom Produit " + rs.getString(3) + "description de Produit " + rs.getString(4) + "photo de produit " + rs.getString(5) + "prix de produit " + rs.getFloat(6) + "sttock de produit " + rs.getInt(7) + "catégorie de produit " + rs.getString(8) + "video de produit " + rs.getString(9));
                listN.add(new Produit(rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getFloat(6),
                        rs.getInt(7),
                        rs.getString(8),
                        rs.getString(9)));
            }
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(ProduitService.class.getName()).log(Level.SEVERE, null, ex);
        }

        return listN;
    }

    public Produit rechercherroduitById(int id_produit) {
        String requete = "select * from produit where id_produit='" + id_produit + "'";
        try {
            PreparedStatement ps = cnx.prepareStatement(requete);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String nom_produit = rs.getString("nom_produit");
                String description = rs.getString("description");
                String photo = rs.getString("photo");
                float prix_produit = rs.getFloat("prix_produit");
                int stock_produit = rs.getInt("stock_produit");
                String categorie_produit = rs.getString("categorie_produit");
                String video = rs.getString("video");
                return (new Produit(id_produit, nom_produit, description, photo, prix_produit, stock_produit, categorie_produit, video));
            }

        } catch (SQLException ex) {
            System.out.println("erreur lors de la recherche du compte " + ex.getMessage());
        }
        return null;
    }

    @Override
    public void modifierProduit(Produit p) {
        try {
            
            String req = "UPDATE `produit` SET `nom_produit` = ?, `description`= ? , `photo`= ? , `prix_produit`= ?, `stock_produit`= ?, `categorie_produit`= ?, `video`= ? WHERE `id_produit` = ?";
            PreparedStatement st = cnx.prepareStatement(req);
            st.setString(1, p.getNomProduit());
            st.setString(2, p.getDescriptionProduit());
            st.setString(3, p.getPhotoProduit());
            st.setFloat(4, p.getPrixProduit());
            st.setInt(5, p.getStockProduit());
            st.setString(6, p.getCategorieProduit());
            st.setString(7, p.getVideoProduit());
            st.setInt(8, p.getIdProduit());

            st.executeUpdate();
            System.out.println("Produit modifié !!");

        } catch (SQLException ex) {
            Logger.getLogger(ProduitService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
  

              public int getnbrInfo() {
        String req="SELECT sum(stock_produit)as a FROM produit where categorie_produit like 'Informatique et multimédia'" ;

        int x=0;
        try 
        { 
            PreparedStatement ste = cnx.prepareStatement(req) ;
            //ste.setString(1, "annonce_perte");
            ResultSet rs = ste.executeQuery(); 
            while (rs.next())
            {
                x= rs.getInt("a");
            }

        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return x;    
    }
      public int getnbrVeh() {
        String req="SELECT sum(stock_produit)as a FROM produit where categorie_produit like 'Vehicule'" ;

        int x=0;
        try 
        { 
            PreparedStatement ste = cnx.prepareStatement(req) ;
            //ste.setString(1, "annonce_perte");
            ResultSet rs = ste.executeQuery(); 
            while (rs.next())
            {
                x= rs.getInt("a");
            }

        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return x;    
    }
          public int getnbrImm() {
        String req="SELECT sum(stock_produit)as a FROM produit where categorie_produit like 'Immobilier'" ;

        int x=0;
        try 
        { 
            PreparedStatement ste = cnx.prepareStatement(req) ;
            //ste.setString(1, "annonce_perte");
            ResultSet rs = ste.executeQuery(); 
            while (rs.next())
            {
                x= rs.getInt("a");
            }

        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return x;    
    }
           public int getnbrHab() {
        String req="SELECT sum(stock_produit)as a FROM produit where categorie_produit like 'Habillement et bien etre'" ;

        int x=0;
        try 
        { 
            PreparedStatement ste = cnx.prepareStatement(req) ;
            //ste.setString(1, "annonce_perte");
            ResultSet rs = ste.executeQuery(); 
            while (rs.next())
            {
                x= rs.getInt("a");
            }

        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return x;    
    }
           
      
           
             public void quantiteApresCommande(Produit p,int stockn) {
            
     try {    
        // System.out.println("produit"+p.getIdProduit());
         System.out.println("Stock aprés Commande ****** ");
       String req= "UPDATE produit SET stock_produit='"+stockn+"'WHERE id_produit='"+p.getIdProduit()+"'";
       PreparedStatement st = cnx.prepareStatement(req);

       st.executeUpdate();

        }catch (SQLException ex)
                {
                     Logger.getLogger(VoyageService.class.getName()).log(Level.SEVERE, null, ex);

                }
}

    public ArrayList<Produit> findAllFiltrer(String filtre) {
        String sql = "SELECT * FROM `produit` where categorie_produit	=? and stock_produit <> '0'";
         try {
             PreparedStatement statement = this.cnx.prepareStatement(sql);
             statement.setString(1,filtre);
             ResultSet results =  statement.executeQuery();
             ArrayList<Produit> produits = new ArrayList<>();
             Produit produit;
             while (results.next()) {
                 produit = new Produit(results.getInt("id_produit"),results.getString("nom_produit"),results.getString("description"),results.getString("photo"),results.getFloat("prix_produit"),results.getInt("stock_produit"),results.getString("categorie"));
                 produits.add(produit);
             }
             return produits;
         } catch (SQLException ex) {
             System.out.println("erreur affichage produit");
         }
        return null;
    }
    
     public ArrayList<Produit> findAllFiltrerup(String filtre) {
        String sql = "SELECT * FROM `produit` where categorie_produit=? and stock_produit <> '0' order by prix_produit";
         try {
             PreparedStatement statement = this.cnx.prepareStatement(sql);
             statement.setString(1,filtre);
             ResultSet results =  statement.executeQuery();
             ArrayList<Produit> produits = new ArrayList<>();
             Produit produit;
             while (results.next()) {
                 produit = new Produit(results.getInt("id_produit"),results.getString("nom_produit"),results.getString("description"),results.getString("photo"),results.getFloat("prix_produit"),results.getInt("stock_produit"),results.getString("categorie"));
                 produits.add(produit);
             }
             return produits;
         } catch (SQLException ex) {
             System.out.println("erreur affichage produit");
         }
        return null;
    }       
         
            public ArrayList<Produit> findAllFiltrerdown(String filtre) {
        String sql = "SELECT * FROM `produit` where categorie_produit=? and stock_produit <> '0' order by prix_produit desc";
         try {
             PreparedStatement statement = this.cnx.prepareStatement(sql);
             statement.setString(1,filtre);
             ResultSet results =  statement.executeQuery();
             ArrayList<Produit> produits = new ArrayList<>();
             Produit produit;
             while (results.next()) {
                 produit = new Produit(results.getInt("id_produit"),results.getString("nom_produit"),results.getString("description"),results.getString("photo"),results.getFloat("prix_produit"),results.getInt("stock_produit"),results.getString("categorie"));
                 produits.add(produit);
             }
             return produits;
         } catch (SQLException ex) {
             System.out.println("erreur affichage produit");
         }
        return null;
    }       

    public Produit AfficherDetailProduit(int id_prod) {
ArrayList<Produit> listN = new ArrayList<Produit>();
        try {
            stmt = cnx.createStatement();
            ResultSet rs = stmt.executeQuery("Select * from produit WHERE produit.`id_produit` = '" + id_prod+ "'");
            while (rs.next()) {
                listN.add(new Produit(
                          rs.getInt(1),
                       rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getFloat(6),
                        rs.getInt(7),
                        rs.getString(8),
                        rs.getString(9)
                ));
            }
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(Recommendation.class.getName()).log(Level.SEVERE, null, ex);
        }

        return listN.get(0);
    }
    }
   


