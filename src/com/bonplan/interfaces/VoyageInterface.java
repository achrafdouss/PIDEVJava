/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bonplan.interfaces;

import com.bonplan.entities.Recommendation;
import com.bonplan.entities.Voyage;
import java.util.List;

/**
 *
 * @author Radhouen
 */
public interface VoyageInterface {
    
    public void AjouterVoyage(Voyage v);
    public void ModifierVoyage(Voyage v);
    public void SupprimerVoyage(int id_voy);
    public List<Voyage> AfficherVoyage();
    public Voyage AfficherDetailVoyage(int id_voy);
    public Voyage RechercherVoyage(String categorie);
    public void ModifierVRes(Voyage v,int nbr_place_nouv);
    public Voyage RechercherVoyageByID(int id_voy);
    public List<Voyage> find(String PrixMax, String PrixMin, String Destination, String Categorie);
     public Voyage getVoyage(int id_voy);

 

    
    
    
}
