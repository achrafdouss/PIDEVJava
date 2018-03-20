/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bonplan.interfaces;

import com.bonplan.entities.Recommendation;
import java.util.List;

/**
 *
 * @author Achraf
 */
public interface RecommendationInterfaces {
    public void AjoutRecommendation(Recommendation r);
    public void ModifierRecommendation(Recommendation r);
    public void SupprimerRecommendation(int id_rec);
    public List<Recommendation> AfficherAllRecommendation();
    public Recommendation AfficherDetailRecommendation(int id_rec);
    public List<Recommendation> AfficherTopRecommendation(String categorie);
    
    
}
