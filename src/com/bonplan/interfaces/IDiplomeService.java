/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bonplan.interfaces;

import com.bonplan.entities.Diplome;
import java.util.List;
import javafx.collections.ObservableList;

/**
 *
 * @author dell
 */
public interface IDiplomeService {
   public void createDiplome(Diplome d);
   
   public Diplome findbycolumns(String type, String categorie, String etab, int date);
   public Diplome findbyid(int id);
   
   public ObservableList<String> getEtablissementsdiplome();
   public ObservableList<String> getCategoriesdiplome();
   public ObservableList<String> getAnneesdiplome();
    
}
