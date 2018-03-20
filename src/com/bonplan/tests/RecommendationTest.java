/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bonplan.tests;

import com.bonplan.entities.Recommendation;
import com.bonplan.services.RecommendationService;

/**
 *
 * @author Achraf
 */
public class RecommendationTest {
    public static void main(String[] args) {
        Recommendation r=new Recommendation(1, "abc", "aaa", "aaa", "aaa", "aaa", "2213156", "aaa", 4, "aaaa");
        RecommendationService rs= new RecommendationService();
       // rs.AjoutRecommendation(r);
        //rs.ModifierRecommendation(1,r);
        //rs.SupprimerRecommendation(1);
        //rs.AfficherAllRecommendation().forEach(System.out::println);
      // rs.AfficherDetailRecommendation(3).toString();
      rs.AfficherTopRecommendation("Produit").forEach(System.out::print);
    }
}
