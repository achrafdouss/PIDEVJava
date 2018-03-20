/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bonplan.entities;

/**
 *
 * @author Radhouen
 */
public class Reservation {
    
    int id_resv;
    Voyage voyage;
    int id_user;
    int nbr_place_resv;

    public int getId_resv() {
        return id_resv;
    }

    public void setId_resv(int id_resv) {
        this.id_resv = id_resv;
    }

    public Voyage getVoyage() {
        return voyage;
    }

    public void setVoyage(Voyage voyage) {
        this.voyage = voyage;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public int getNbr_place_resv() {
        return nbr_place_resv;
    }

    public void setNbr_place_resv(int nbr_place_resv) {
        this.nbr_place_resv = nbr_place_resv;
    }

    @Override
    public String toString() {
        return "Reservation{" + "id_resv=" + id_resv + ", voyage=" + voyage + ", id_user=" + id_user + ", nbr_place_resv=" + nbr_place_resv + '}';
    }
    
    
}
