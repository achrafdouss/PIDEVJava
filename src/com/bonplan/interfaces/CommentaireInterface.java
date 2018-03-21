/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bonplan.interfaces;

import com.bonplan.entities.Commentaire;
import com.bonplan.entities.Recommendation;
import java.util.List;

/**
 *
 * @author Achraf
 */
public interface CommentaireInterface {
    public void AjoutCommentaire(Commentaire c);
    public void ModifierCommentaire(int id,Commentaire c);
    public void SupprimerCommentaire(int id_com);
    public List<Commentaire> AfficherCommentaire(Recommendation r);
    
}
