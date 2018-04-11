/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bonplan.entities;

/**
 *
 * @author souab
 */
public class Participation {
    public int id;
    public int id_event;
    public int id_inscrit;
    public String date;
    public int nbre;
    public int status;
    
    public Participation(){}

    @Override
    public String toString() {
        return "Participation{" + "id=" + id + ", id_event=" + id_event + ", id_inscrit=" + id_inscrit + ", date=" + date + ", nbre=" + nbre + ", status=" + status + '}';
    }

    public Participation(int id, int id_event, int id_inscrit, String date, int nbre, int status) {
        this.id = id;
        this.id_event = id_event;
        this.id_inscrit = id_inscrit;
        this.date = date;
        this.nbre = nbre;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_event() {
        return id_event;
    }

    public void setId_event(int id_event) {
        this.id_event = id_event;
    }

    public int getId_inscrit() {
        return id_inscrit;
    }

    public void setId_inscrit(int id_inscrit) {
        this.id_inscrit = id_inscrit;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getNbre() {
        return nbre;
    }

    public void setNbre(int nbre) {
        this.nbre = nbre;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
    
}
