/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bonplan.interfaces;

import com.bonplan.entities.Reservation;
import com.bonplan.entities.Voyage;
import com.bonplan.gui.MesReservationsFXMLController.v;
import java.util.List;

/**
 *
 * @author Radhouen
 */
public interface ReserverInterface {
    
        public void ReserverVoyage(Reservation r);
    public void ModifierReservation(Reservation r);
  public void AnnulerReservation(v res);
  public List<Reservation> AfficherReservation(int id_user);
     public Reservation ReturnRes(int idInscrit, int id_voy);
  
    
}
