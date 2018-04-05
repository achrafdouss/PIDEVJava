/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bonplan.interfaces;

import com.bonplan.entities.Produit;
import java.util.List;

/**
 *
 * @author bouyo
 */
public interface iProduitService {
    public void ajouterProduit(Produit p);
    public void supprimerProduit(int id_produit);
    public void modifierProduit (Produit p);
    public List<Produit> consulterProduit();
    
}
