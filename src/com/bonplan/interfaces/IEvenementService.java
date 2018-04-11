/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bonplan.interfaces;

import com.bonplan.entities.Evenement;
import com.bonplan.entities.Participation;
import com.bonplan.entities.User;
import java.util.List;

/**
 *
 * @author bhk
 */
public interface IEvenementService {

    public void createEvenement(Evenement e);

    public List<Evenement> getAll();
    public List<Evenement> getMyEvents();

    public void update(Evenement e);

    public void delete(int id);
    public void participer(Evenement e,User u,Integer nbre);
    public List<User> eventParticipants(Evenement e);
    public List<Evenement> mesParticipations();
    public void annulerParticipation(int id);
    public Participation getParticipation(int idInscrit,int idEvent);
    public Evenement getEvenement(int idEvent);
    public void confirmer(Participation p);
    public List<Evenement> find(int prixmax,int prixmin,String lieu,String categorie);
    public void waitlist(Evenement e);

}
