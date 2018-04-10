/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bonplan.tests;

import com.bonplan.entities.Commentaire;
import com.bonplan.entities.Recommendation;
import com.bonplan.entities.Reservation;
import com.bonplan.entities.User;
import com.bonplan.services.CommentaireService;
import com.bonplan.services.RecommendationService;
import com.bonplan.services.ReserverService;
import com.bonplan.services.UserServices;

/**
 *
 * @author Achraf
 */
public class RecommendationTest {
    public static void main(String[] args) {
        
       // rs.AjoutRecommendation(r);
        //rs.ModifierRecommendation(1,r);
        //rs.SupprimerRecommendation(1);
        //rs.AfficherAllRecommendation().forEach(System.out::println);
      // rs.AfficherDetailRecommendation(3).toString();
      //rs.AfficherTopRecommendation("Produit").forEach(System.out::print);
        //Commentaire c= new Commentaire( 1, 8, "abcde", 2);
        //CommentaireService cs= new CommentaireService();
        //cs.AjoutCommentaire(c);
        //r1.setId(8);
        //System.out.println(r1.id);
       // cs.AfficherCommentaire(r1).forEach(System.out::println);
       // cs.ModifierCommentaire(c);
        
        //us.AfficherUser("user").toString();
        //us.AjouterUser(u);
        //if(us.Login("user", "123")){
          //  System.out.println("success");}
        //else{ System.out.println("failed");}
        //System.out.println(us.verifAdmin("assil"));
      
          // User u1=new User("radhouen", "radhouen@abidi.com", 0, "123"+"{"+"radhouen"+"}", "0", "radhouen", "abidi", "ariana", 27326457);
        //us.ModiferUser(1, u);
       // us.UpdateEnabledUser(1, 0);
      //  rs.AfficherRecommendationById(2).forEach(a->a.getId());
      
        ReserverService res= new ReserverService();
       
        res.AfficherReservation(2);
        System.out.println(res.AfficherReservation(2));
        
    }
}
