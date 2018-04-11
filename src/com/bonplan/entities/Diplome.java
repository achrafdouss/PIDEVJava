/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bonplan.entities;

/**
 *
 * @author dell
 */
public class Diplome {
    private int idDiplome;
    private String type;
    private String categorie;
    private String etablissement;
    private int annee;

    public Diplome(int idDiplome, String type, String categorie, String etablissement, int annee) {
        this.idDiplome = idDiplome;
        this.type = type;
        this.categorie = categorie;
        this.etablissement = etablissement;
        this.annee = annee;
    }

    public Diplome(String type, String categorie, String etablissement, int annee) {
        this.type = type;
        this.categorie = categorie;
        this.etablissement = etablissement;
        this.annee = annee;
    }

    public int getIdDiplome() {
        return idDiplome;
    }

    public void setIdDiplome(int idDiplome) {
        this.idDiplome = idDiplome;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public String getEtablissement() {
        return etablissement;
    }

    public void setEtablissement(String etablissement) {
        this.etablissement = etablissement;
    }

    public int getAnnee() {
        return annee;
    }

    public void setAnnee(int annee) {
        this.annee = annee;
    }
    

    @Override
    public String toString() {
        return "Diplome{" + "idDiplome=" + idDiplome + ", type=" + type + ", categorie=" + categorie + ", etablissement=" + etablissement + ", annee=" + annee + '}';
    }
    
    
}
