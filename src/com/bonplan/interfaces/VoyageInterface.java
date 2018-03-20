/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bonplan.interfaces;

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
    public Voyage RechercherVoyage(float prixMin,float prixMax,String categorie);
    
    
    
    
}
