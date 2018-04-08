/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bonplan.entities;

/**
 *
 * @author bouyo
 */
public class Commande {

    private int idCommandeProduit;
    private int idproduitroduit;
    private int idAcheteur;
    private int quantiteCommandeProduit;

    public Commande() {
    }

    public Commande(int idproduitroduit, int idAcheteur, int quantiteCommandeProduit) {
        this.idproduitroduit = idproduitroduit;
        this.idAcheteur = idAcheteur;
        this.quantiteCommandeProduit = quantiteCommandeProduit;
    }

  

    public Commande(int idAcheteur) {
        this.idAcheteur = idAcheteur;
    }

    public int getIdCommandeProduit() {
        return idCommandeProduit;
    }

    public void setIdCommandeProduit(int idCommandeProduit) {
        this.idCommandeProduit = idCommandeProduit;
    }

    public int getIdproduitroduit() {
        return idproduitroduit;
    }

    public void setIdproduitroduit(int idproduitroduit) {
        this.idproduitroduit = idproduitroduit;
    }

   

    public int getIdAcheteur() {
        return idAcheteur;
    }

    public void setIdAcheteur(int idAcheteur) {
        this.idAcheteur = idAcheteur;
    }

    public int getQuantiteCommandeProduit() {
        return quantiteCommandeProduit;
    }

    public void setQuantiteCommandeProduit(int quantiteCommandeProduit) {
        this.quantiteCommandeProduit = quantiteCommandeProduit;
    }

    @Override
    public String toString() {
        return "Commande{" + "idCommandeProduit=" + idCommandeProduit + ", idproduitroduit=" + idproduitroduit + ", idAcheteur=" + idAcheteur + ", quantiteCommandeProduit=" + quantiteCommandeProduit + '}';
    }

   

   

    

}
