/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bonplan.interfaces;

import com.bonplan.entities.CommentairePrestation;
import java.util.List;

/**
 *
 * @author dell
 */
public interface ICommentairePrestationService {
    public void createCommentaire(CommentairePrestation cp);
    public void deleteCommentaire(int id);
    
    public List<CommentairePrestation> getAll();
    public List<CommentairePrestation> getAllValides(int idprest);
    
}
