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
    private Produit Produit;
    private int idAcheteur;
    private int quantiteCommandeProduit;

    public Commande() {
    }

    public Commande(int idAcheteur, int quantiteCommandeProduit ,Produit idProduit) {
        this.idAcheteur = idAcheteur;
        this.quantiteCommandeProduit = quantiteCommandeProduit;
                this.Produit = idProduit;

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

    public Produit getProduit() {
        return Produit;
    }

    public void setProduit(Produit Produit) {
        this.Produit = Produit;
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
        return "Commande{" + "idCommandeProduit=" + idCommandeProduit + ", Produit=" + Produit.getIdProduit() + ", idAcheteur=" + idAcheteur + ", quantiteCommandeProduit=" + quantiteCommandeProduit + '}';
    }

   

    

}
