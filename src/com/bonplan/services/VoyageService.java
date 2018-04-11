/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bonplan.services;

import com.bonplan.entities.Recommendation;
import com.bonplan.entities.User;
import com.bonplan.entities.Voyage;
import com.bonplan.interfaces.VoyageInterface;
import com.bonplan.util.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import java.util.stream.Collectors;


import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Radhouen
 */
public class VoyageService implements VoyageInterface{

    
    Connection cnx;
    Statement stmt;

   public VoyageService() {
        this.cnx = DataSource.getInstance().getConnection();
    }
    
    @Override
    public void AjouterVoyage(Voyage v) {
        
        
        try {
            String req = "INSERT INTO voyage (categorie,type,nbr_place,date_depart,date_arrivee,prix,description,destination,photo,id_owner) VALUES (?,?,?,?,?,?,?,?,?,?)";

            PreparedStatement st = cnx.prepareStatement(req);
            st.setString(1, v.getCategorie());
            st.setString(2, v.getType());
            st.setInt(3, v.getNbr_place());
            st.setDate(4,(java.sql.Date) v.getDate_dep());
            st.setDate(5,(java.sql.Date) v.getDate_arr());
            st.setFloat(6, v.getPrix());
            st.setString(7, v.getDescription());
            st.setString(8, v.getDestination());
            st.setString(9, v.getPhoto());
            st.setInt(10, User.getUserconnected());
            System.out.println(v.getPhoto());
            

            st.executeUpdate();
            System.out.println("Voyage ajoutée !!");

        } catch (SQLException ex) {
            Logger.getLogger(VoyageService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @Override
    public void ModifierVoyage(Voyage v) {
try {
            String req = "UPDATE voyage SET `categorie`= ?, `type`= ? , `nbr_place`= ? , `date_depart`= ?, `date_arrivee`= ?, `prix`= ?, `description`= ?, `destination`= ?, `photo`= ?"
                    + " WHERE id_voyage = '" + v.getId_voyage() + "'";
            PreparedStatement st = cnx.prepareStatement(req);
            st.setString(1, v.getCategorie());
            st.setString(2, v.getType());
            st.setInt(3, v.getNbr_place());
            st.setDate(4,(java.sql.Date) v.getDate_dep());
            st.setDate(5,(java.sql.Date) v.getDate_arr());
            st.setFloat(6, v.getPrix());
            st.setString(7, v.getDescription());
            st.setString(8, v.getDestination());
            st.setString(9, v.getPhoto());
            st.executeUpdate();
            System.out.println("Voyage modifié !!");
            System.out.println("L'id mta3 lvoyage modifiee"+v.getPhoto());
    System.out.println("Categorie jdida"+v.getCategorie());
        } catch (SQLException ex) {
            Logger.getLogger(VoyageService.class.getName()).log(Level.SEVERE, null, ex);
        }




    }

    @Override
    public void SupprimerVoyage(int id_voy) {
         try {
            String req = "DELETE FROM voyage WHERE voyage.`id_voyage` = ? ";
            String req2="DELETE FROM reserver_voyage WHERE reserver_voyage.`id_voyage` = ? ";
            PreparedStatement st = cnx.prepareStatement(req);
            PreparedStatement st1 = cnx.prepareStatement(req2);
            st.setInt(1, id_voy);
            st1.setInt(1, id_voy);
            st.executeUpdate();
            st1.executeUpdate();
            System.out.println("Voyage supprimé !!");

        } catch (SQLException ex) {
            Logger.getLogger(VoyageService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }

    @Override
    public List<Voyage> AfficherVoyage() {
 ArrayList<Voyage> list = new ArrayList<Voyage>();
        try {
            stmt = cnx.createStatement();
            ResultSet rs = stmt.executeQuery("Select * from voyage");
            while (rs.next()) {
                
                list.add(new Voyage(rs.getInt(1),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getInt(5),
                        rs.getDate(6),
                        rs.getDate(7),
                        rs.getFloat(8),
                        rs.getString(9),
                        rs.getString(10),
                        rs.getInt(2),
                        rs.getString(11)
                        ));
            }
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(VoyageService.class.getName()).log(Level.SEVERE, null, ex);
        }

        return list;


    }

    @Override
    public Voyage AfficherDetailVoyage(int id_voy) {
        ArrayList<Voyage> listW = new ArrayList<Voyage>();
        Voyage v=new Voyage();
        try {
            stmt = cnx.createStatement();
            ResultSet rs = stmt.executeQuery("Select * from voyage WHERE voyage.`id_voyage` = '" + id_voy + "'");
            while (rs.next()) {
                v.setId_voyage(rs.getInt(1));
                v.setCategorie(rs.getString(3));  
                v.setType(rs.getString(4));
                v.setNbr_place(rs.getInt(5));
                 v.setDate_dep(rs.getDate(6));
                  v.setDate_arr(rs.getDate(7));
                   v.setPrix( rs.getFloat(8));
                    v.setDescription(rs.getString(9));
                     v.setDestination(rs.getString(10));
                      v.setId_owner(rs.getInt(2));
                     v.setPhoto( rs.getString(11));  
                        
                        
                        
                        
                       
                          }
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(Recommendation.class.getName()).log(Level.SEVERE, null, ex);
        }

        return v;
    }

    @Override
    public Voyage RechercherVoyage(String categorie) {
        String requete = "select * from voyage where categorie='" + categorie + "'";
        try {
            PreparedStatement ps = cnx.prepareStatement(requete);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String cat = rs.getString("categorie");
                Date date_depart  = rs.getDate("date_depart");
                Date date_arrivee = rs.getDate("date_arrivee");
                float prix = rs.getFloat("prix");
                
                return (new Voyage(categorie, date_depart, date_arrivee, prix));
            }

        } catch (SQLException ex) {
            System.out.println("erreur lors de la recherche  " + ex.getMessage());
        }
        return null;
        
        
    }
 @Override
    public Voyage RechercherVoyageByID(int id_voy) {
        String requete = "select * from voyage where voyage.`id_voyage` = '" + id_voy + "'";
        try {
            PreparedStatement ps = cnx.prepareStatement(requete);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Integer cat = rs.getInt("id_voyage");
                Date date_depart  = rs.getDate("date_depart");
                Date date_arrivee = rs.getDate("date_arrivee");
                float prix = rs.getFloat("prix");
                
                return (new Voyage(id_voy, date_depart, date_arrivee, prix));
            }

        } catch (SQLException ex) {
            System.out.println("erreur lors de la recherche  " + ex.getMessage());
        }
        return null;
        
        
    }
    

    @Override
    public void ModifierVRes(Voyage v,int nbr_place_nouv) {
            
     try {    
         System.out.println("get ID VOYAGE"+v.getId_voyage());
         System.out.println("Nouveau nmbre de place");
       String req= "UPDATE voyage SET nbr_place='"+nbr_place_nouv+"'WHERE id_voyage='"+v.getId_voyage()+"'";
       PreparedStatement st = cnx.prepareStatement(req);

       st.executeUpdate();

        }catch (SQLException ex)
                {
                     Logger.getLogger(VoyageService.class.getName()).log(Level.SEVERE, null, ex);

                }
        
    }

    
    @Override
    public List<Voyage> find(String PrixMax, String PrixMin, String Destination, String Categorie) {
        List<Voyage> list = new ArrayList<>();
        VoyageService es = new VoyageService();
        list = es.AfficherVoyage();
        if (!Categorie.equals("")) {
            list = list.stream().filter(e -> e.getCategorie().equals(Categorie)).collect(Collectors.toCollection(ArrayList<Voyage>::new));
            System.out.println("**1");
        }
        if (!Destination.equals("")) {
            list = list.stream().filter(e -> e.getDestination().equals(Destination)).collect(Collectors.toCollection(ArrayList<Voyage>::new));
            System.out.println("**2");
        }

        if (!PrixMax.equals("0")) {
            list = list.stream().filter(e ->e.getPrix() <= Float.parseFloat(PrixMax)).collect(Collectors.toCollection(ArrayList<Voyage>::new));
            System.out.println("**3");
        }

        if (!PrixMin.equals("0")) {
            list = list.stream().filter(e -> e.getPrix() >= Float.parseFloat(PrixMin)).collect(Collectors.toCollection(ArrayList<Voyage>::new));
            System.out.println("**4");
        }
        return list;
    }
   
    
     @Override
    public Voyage getVoyage(int id_voy) {
        VoyageService es = new VoyageService();
        Voyage ev = new Voyage();
        ev = es.AfficherVoyage().stream().filter(e -> e.id_voyage == id_voy).findFirst().get();
        return ev;

    }
        
}
