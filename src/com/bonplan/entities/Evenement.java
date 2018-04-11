/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bonplan.entities;

/**
 *
 * @author bhk
 */
public class Evenement {
    public int id ;
    public String categorie;
    public int nbrplace;
    private String date_evenement;
    private String lieu;
    private String description;
    private String nomEvenement;
    private int id_owner;
    private String photo;
    private String lat;
    private String lng;
    private int prix;

    public Evenement(String categorie, int nbrplace, String date_evenement, String lieu, String description, String nomEvenement, int id_owner, String photo, String lat, String lng, int prix) {
        this.categorie = categorie;
        this.nbrplace = nbrplace;
        this.date_evenement = date_evenement;
        this.lieu = lieu;
        this.description = description;
        this.nomEvenement = nomEvenement;
        this.id_owner = id_owner;
        this.photo = photo;
        this.lat = lat;
        this.lng = lng;
        this.prix = prix;
    }

    public Evenement(int id, String categorie, int nbrplace, String date_evenement, String lieu, String description, String nomEvenement, int id_owner, String photo, String lat, String lng, int prix) {
        this.id = id;
        this.categorie = categorie;
        this.nbrplace = nbrplace;
        this.date_evenement = date_evenement;
        this.lieu = lieu;
        this.description = description;
        this.nomEvenement = nomEvenement;
        this.id_owner = id_owner;
        this.photo = photo;
        this.lat = lat;
        this.lng = lng;
        this.prix = prix;
    }

    public Evenement() {
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }
    


    public Evenement(int id, String categorie, int nbrplace, String date_evenement, String lieu, String description, String nomEvenement, int id_owner) {
        this.id = id;
        this.categorie = categorie;
        this.nbrplace = nbrplace;
        this.date_evenement = date_evenement;
        this.lieu = lieu;
        this.description = description;
        this.nomEvenement = nomEvenement;
        this.id_owner = id_owner;
    }

    public Evenement(String categorie, int nbrplace, String date_evenement, String lieu, String description, String nomEvenement, int id_owner) {
        this.categorie = categorie;
        this.nbrplace = nbrplace;
        this.date_evenement = date_evenement;
        this.lieu = lieu;
        this.description = description;
        this.nomEvenement = nomEvenement;
        this.id_owner = id_owner;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public int getNbrplace() {
        return nbrplace;
    }

    public void setNbrplace(int nbrplace) {
        this.nbrplace = nbrplace;
    }

    public String getDate_evenement() {
        return date_evenement;
    }

    public void setDate_evenement(String date_evenement) {
        this.date_evenement = date_evenement;
    }

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNomEvenement() {
        return nomEvenement;
    }

    public void setNomEvenement(String nomEvenement) {
        this.nomEvenement = nomEvenement;
    }

    public int getId_owner() {
        return id_owner;
    }

    public void setId_owner(int id_owner) {
        this.id_owner = id_owner;
    }
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Evenement{" + "id=" + id + ", categorie=" + categorie + ", nbrplace=" + nbrplace + ", date_evenement=" + date_evenement + ", lieu=" + lieu + ", description=" + description + ", nomEvenement=" + nomEvenement + ", id_owner=" + id_owner + ", photo=" + photo + ", lat=" + lat + ", lng=" + lng + ", prix=" + prix + '}';
    }



    
    
    
}
