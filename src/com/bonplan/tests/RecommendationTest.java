/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bonplan.tests;

import com.bonplan.entities.Commentaire;
import com.bonplan.entities.Recommendation;
import com.bonplan.entities.User;
import com.bonplan.services.CommentaireService;
import com.bonplan.services.RecommendationService;
import com.bonplan.services.UserServices;

/**
 *
 * @author Achraf
 */
public class RecommendationTest {
    public static void main(String[] args) {
        Recommendation r=new Recommendation(8,1, "abc", "aaa", "aaa", "aaa", "aaa", "2213156", "aaa", 4, "aaaa");
Recommendation r1= new Recommendation(8, 1, "aaa", "zzzz", "zzzz", "zzzz", "zzz", "zzzz", "zzzz", 4, "zzzz");
        RecommendationService rs= new RecommendationService();
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
        User u=new User("user", "aaa", 0, "123", "0", "aaa", "aaa", "aaa", 4654654);
        UserServices us=new UserServices();
        us.AfficherUser("user").toString();
        //us.AjouterUser(u);
        //if(us.Login("user", "123")){
          //  System.out.println("success");}
        //else{ System.out.println("failed");}
        us.AjouterUser(u);
    }
}
