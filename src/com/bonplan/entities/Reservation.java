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
    int id_voy;
    int id_inscrit;
    int nbr_place_resv;
     public Reservation(){} 
    public Reservation(int id_voy,int id_inscrit,int nbr_place_res)
    {this.id_voy=id_voy;
    this.id_inscrit=id_inscrit;
    this.nbr_place_resv=nbr_place_res;
    }
    public Reservation(int id_voy,int id_inscrit,int nbr_place_res,int id_resv)
    {this.id_voy=id_voy;
    this.id_inscrit=id_inscrit;
    this.nbr_place_resv=nbr_place_res;
    this.id_resv=id_resv;   }

    public int getId_resv() {
        return id_resv;
    }

    public void setId_resv(int id_resv) {
        this.id_resv = id_resv;
    }

    public int getId_voy() {
        return id_voy;
    }

    public void setId_voy(int id_voy) {
        this.id_voy = id_voy;
    }

    public int getId_inscrit() {
        return id_inscrit;
    }

    public void setId_inscrit(int id_inscrit) {
        this.id_inscrit = id_inscrit;
    }

 

    public int getId_user() {
        return id_inscrit;
    }

    public void setId_user(int id_user) {
        this.id_inscrit = id_user;
    }

    public int getNbr_place_resv() {
        return nbr_place_resv;
    }

    public void setNbr_place_resv(int nbr_place_resv) {
        this.nbr_place_resv = nbr_place_resv;
    }

    @Override
    public String toString() {
        return "Reservation{" + "id_resv=" + id_resv + ", id_voy=" + id_voy + ", id_inscrit=" + id_inscrit + ", nbr_place_resv=" + nbr_place_resv + '}';
    }

  
}
