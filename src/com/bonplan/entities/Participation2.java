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
public class Participation2 {
    public int id;
    public Evenement event;
    public User inscrit;

    public Participation2(int id, Evenement event, User inscrit, String date, int nbre, int status) {
        this.id = id;
        this.event = event;
        this.inscrit = inscrit;
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

    public Evenement getEvent() {
        return event;
    }

    public void setEvent(Evenement event) {
        this.event = event;
    }

    public User getInscrit() {
        return inscrit;
    }

    public void setInscrit(User inscrit) {
        this.inscrit = inscrit;
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
    public String date;
    public int nbre;
    public int status;
    
    public Participation2(){}

}
