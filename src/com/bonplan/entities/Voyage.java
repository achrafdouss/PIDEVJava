/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bonplan.entities;

import java.util.Objects;

/**
 *
 * @author Radhouen
 */
public class Voyage {
    
    
    int id_voyage;
    String categorie;
    String Type;
    int nbr_place;
    String date_dep;
    String date_arr;
    float prix;
    String description;
    String destination;

    public Voyage(int id_voyage, String categorie, String Type, int nbr_place, String date_dep, String date_arr, float prix, String description, String destination, String photo, int id_owner) {
        this.id_voyage = id_voyage;
        this.categorie = categorie;
        this.Type = Type;
        this.nbr_place = nbr_place;
        this.date_dep = date_dep;
        this.date_arr = date_arr;
        this.prix = prix;
        this.description = description;
        this.destination = destination;
        this.photo = photo;
        this.id_owner = id_owner;
    }

    
    public Voyage(String categorie, String Type, int nbr_place, String date_dep, String date_arr, float prix, String description, String destination, String photo) {
        
        this.categorie = categorie;
        this.Type = Type;
        this.nbr_place = nbr_place;
        this.date_dep = date_dep;
        this.date_arr = date_arr;
        this.prix = prix;
        this.description = description;
        this.destination = destination;
        this.photo = photo;
        
        
    }

   
     public Voyage(String categorie, String date_dep, String date_arr, float prix) {
        
        this.categorie = categorie;
        this.date_dep = date_dep;
        this.date_arr = date_arr;
        this.prix = prix;
        
        
        
    }
    

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }
    String photo;
    int id_owner;

    public int getId_voyage() {
        return id_voyage;
    }

    public void setId_voyage(int id_voyage) {
        this.id_voyage = id_voyage;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public String getType() {
        return Type;
    }

    public void setType(String Type) {
        this.Type = Type;
    }

    public int getNbr_place() {
        return nbr_place;
    }

    public void setNbr_place(int nbr_place) {
        this.nbr_place = nbr_place;
    }

    public String getDate_dep() {
        return date_dep;
    }

    public void setDate_dep(String date_dep) {
        this.date_dep = date_dep;
    }

    public String getDate_arr() {
        return date_arr;
    }

    public void setDate_arr(String date_arr) {
        this.date_arr = date_arr;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public int getId_owner() {
        return id_owner;
    }

    public void setId_owner(int id_owner) {
        this.id_owner = id_owner;
    }

    @Override
    public String toString() {
        return "Voyage{" + "id_voyage=" + id_voyage + ", categorie=" + categorie + ", Type=" + Type + ", nbr_place=" + nbr_place + ", date_dep=" + date_dep + ", date_arr=" + date_arr + ", prix=" + prix + ", description=" + description + ", photo=" + photo + ", id_owner=" + id_owner + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 37 * hash + this.id_voyage;
        hash = 37 * hash + Objects.hashCode(this.categorie);
        hash = 37 * hash + Objects.hashCode(this.Type);
        hash = 37 * hash + this.nbr_place;
        hash = 37 * hash + Objects.hashCode(this.date_dep);
        hash = 37 * hash + Objects.hashCode(this.date_arr);
        hash = 37 * hash + Float.floatToIntBits(this.prix);
        hash = 37 * hash + Objects.hashCode(this.description);
        hash = 37 * hash + Objects.hashCode(this.photo);
        hash = 37 * hash + this.id_owner;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Voyage other = (Voyage) obj;
        if (this.id_voyage != other.id_voyage) {
            return false;
        }
        if (this.nbr_place != other.nbr_place) {
            return false;
        }
        if (Float.floatToIntBits(this.prix) != Float.floatToIntBits(other.prix)) {
            return false;
        }
        if (this.id_owner != other.id_owner) {
            return false;
        }
        if (!Objects.equals(this.categorie, other.categorie)) {
            return false;
        }
        if (!Objects.equals(this.Type, other.Type)) {
            return false;
        }
        if (!Objects.equals(this.date_dep, other.date_dep)) {
            return false;
        }
        if (!Objects.equals(this.date_arr, other.date_arr)) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        if (!Objects.equals(this.photo, other.photo)) {
            return false;
        }
        return true;
    }
    
    
    
}
