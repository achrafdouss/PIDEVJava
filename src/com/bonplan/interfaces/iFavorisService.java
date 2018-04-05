/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bonplan.interfaces;

import com.bonplan.entities.Favoris;
import java.util.List;

/**
 *
 * @author bouyo
 */
public interface iFavorisService {
    public void ajouterFavoris(Favoris f);
    public void supprimerFavoris(int id_favori);
    public List<Favoris> consulterProduit();
    
}
