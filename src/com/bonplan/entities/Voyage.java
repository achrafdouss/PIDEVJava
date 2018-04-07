/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bonplan.entities;

import java.util.Objects;
import java.util.Date;

/**
 *
 * @author Radhouen
 */
public class Voyage {
    
    
    public int id_voyage;
    public String categorie;
   public String Type;
   public int nbr_place;
   public Date date_dep;
   public Date date_arr;
  public  float prix;
   public String description;
  public  String destination;
    public static int id_vModifier;
  public  String photo;
  public  int id_owner;

    
    public Voyage()
    {}

    public Voyage(int id_voyage, String categorie, String Type, int nbr_place, Date date_dep, Date date_arr, float prix, String description, String destination, String photo, int id_owner) {
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

    
    public Voyage(String categorie, String Type, int nbr_place, Date date_dep, Date date_arr, float prix, String description, String destination, String photo) {
        
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
    public Voyage(int id_voyage,String categorie, String Type, int nbr_place, Date date_dep, Date date_arr, float prix, String description, String destination,int id_owner) {
        
        this.id_voyage=id_voyage;
        this.categorie = categorie;
        this.Type = Type;
        this.nbr_place = nbr_place;
        this.date_dep = date_dep;
        this.date_arr = date_arr;
        this.prix = prix;
        this.description = description;
        this.destination = destination;
        this.id_owner=id_owner;
       
        
        
    }
    public Voyage(int id_voyage,String categorie, String Type, int nbr_place, Date date_dep, Date date_arr, float prix, String description, String destination) {
        
        this.id_voyage=id_voyage;
        this.categorie = categorie;
        this.Type = Type;
        this.nbr_place = nbr_place;
        this.date_dep = date_dep;
        this.date_arr = date_arr;
        this.prix = prix;
        this.description = description;
        this.destination = destination;
       
        
        
    }
    
    public Voyage(String categorie, String Type, int nbr_place, Date date_dep, Date date_arr, float prix, String description, String destination) {
        
        this.categorie = categorie;
        this.Type = Type;
        this.nbr_place = nbr_place;
        this.date_dep = date_dep;
        this.date_arr = date_arr;
        this.prix = prix;
        this.description = description;
        this.destination = destination;
   
        
        
    }

   
     public Voyage(String categorie, Date date_dep, Date date_arr, float prix) {
        
        this.categorie = categorie;
        this.date_dep = date_dep;
        this.date_arr = date_arr;
        this.prix = prix;
        
        
        
    }
     public Voyage(Integer id_voy, Date date_dep, Date date_arr, float prix) {
        
        this.id_voyage = id_voy;
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

    public Date getDate_dep() {
        return date_dep;
    }

    public void setDate_dep(Date date_dep) {
        this.date_dep = date_dep;
    }

    public Date getDate_arr() {
        return date_arr;
    }

    public void setDate_arr(Date date_arr) {
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
    public static int getId_vModifier() {
        return id_vModifier;
    }

    public static void setId_vModifier(int id_vModifier) {
        Voyage.id_vModifier = id_vModifier;
    }
    
    
    
}
