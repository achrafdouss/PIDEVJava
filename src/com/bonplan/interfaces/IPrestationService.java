/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bonplan.interfaces;

import com.bonplan.entities.Prestation;
import java.util.List;
import javafx.collections.ObservableList;

/**
 *
 * @author yosri
 */
public interface IPrestationService {

    public void createPrestation(Prestation p);

    public ObservableList<Prestation> getAllValides();

    public ObservableList<Prestation> getAllNonValides();

    public ObservableList<Prestation> chercher(String text);

    public Prestation findbyid(int id);

    public void updatePrestation(Prestation e);

    public void desactiverPrestation(int id);

    public void deletePrestation(int id);

    public void validerPrestation(int id);
}
