/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bonplan.entities;

import java.sql.Date;

/**
 *
 * @author dell
 */
public class CommentairePrestation {
    private int id_commentaire;
    private int id_prestation;
    private int id_user;
    private String contenu;
    private int signalisation;
    private Date date_ajout;

    public CommentairePrestation(int id_commentaire, int id_prestation, int id_user, String contenu, int signalisation, Date date_ajout) {
        this.id_commentaire = id_commentaire;
        this.id_prestation = id_prestation;
        this.id_user = id_user;
        this.contenu = contenu;
        this.signalisation = signalisation;
        this.date_ajout = date_ajout;
    }

    public CommentairePrestation(int id_prestation, int id_user, String contenu, int signalisation) {
        this.id_prestation = id_prestation;
        this.id_user = id_user;
        this.contenu = contenu;
        this.signalisation = signalisation;
    }

    public int getSignalisation() {
        return signalisation;
    }

    public void setSignalisation(int signalisation) {
        this.signalisation = signalisation;
    }


    public int getId_commentaire() {
        return id_commentaire;
    }

    public void setId_commentaire(int id_commentaire) {
        this.id_commentaire = id_commentaire;
    }

    public int getId_prestation() {
        return id_prestation;
    }

    public void setId_prestation(int id_prestation) {
        this.id_prestation = id_prestation;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public Date getDate_ajout() {
        return date_ajout;
    }

    public void setDate_ajout(Date date_ajout) {
        this.date_ajout = date_ajout;
    }
    
    
}
