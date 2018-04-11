/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bonplan.entities;

import com.bonplan.gui.AcceuilFXMLController;
import com.bonplan.gui.ConsulterPrestationController;
import com.bonplan.gui.GererPrestationController;
import java.io.IOException;
import java.sql.Date;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author yosri
 */
public class Prestation extends AcceuilFXMLController {

    private int idPrestation;
    private int idDiplome;
    private int idInscrit;
    private String titre;
    private String description;
    private float salaire;
    private String lieu;
    private Date dateAjout;
    private String categorie;
    private boolean valide;
    private boolean statut;
    private Button button;
    private Button supprimer;
    private Button approuver;

    public Prestation() {
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + this.idPrestation;
        hash = 59 * hash + this.idInscrit;
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
        final Prestation other = (Prestation) obj;
        if (this.idPrestation != other.idPrestation) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Prestation{" + "idPrestation=" + idPrestation + ", idDiplome=" + idDiplome + ", idInscrit=" + idInscrit + ", titre=" + titre + ", description=" + description + ", salaire=" + salaire + ", lieu=" + lieu + ", dateAjout=" + dateAjout + ", categorie=" + categorie + '}';
    }

    ///
    ///CONSTRUCTEURS
    ///
    public Prestation(int idPrestation, int idDiplome, int idInscrit, String titre, String description, float salaire, String lieu, Date dateAjout, String categorie, boolean valide, boolean statut) {
        this.idPrestation = idPrestation;
        this.idDiplome = idDiplome;
        this.idInscrit = idInscrit;
        this.titre = titre;
        this.description = description;
        this.salaire = salaire;
        this.lieu = lieu;
        this.dateAjout = dateAjout;
        this.categorie = categorie;
        this.valide = valide;
        this.statut = statut;
        this.button = new Button("Voir");
        this.supprimer = new Button("Supprimer");
        this.approuver = new Button("Approuver");
    }

    public Prestation(int idDiplome, int idInscrit, String titre, String description, float salaire, String lieu, String categorie) {
        this.idPrestation = idPrestation;
        this.idDiplome = idDiplome;
        this.idInscrit = idInscrit;
        this.titre = titre;
        this.description = description;
        this.salaire = salaire;
        this.lieu = lieu;
        this.dateAjout = new java.sql.Date(Calendar.getInstance().getTime().getTime());
        this.categorie = categorie;
        this.valide = false;
        this.statut = true;
    }
    ///
    ///GETTERS AND SETTERS
    ///

    public Button getButton() {
        this.button.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent t) {

                try {

                    FXMLLoader loader = new FXMLLoader(getClass().getResource(("/com/bonplan/gui/consulterPrestation.fxml")));
                    loader.load();
                    AnchorPane parentContent = loader.getRoot();
                    window = (AnchorPane) button.getParent().getParent().getParent().getParent().getParent().getParent();
                    ConsulterPrestationController cont = loader.getController();
                    cont.initData(idPrestation);
                    window.getChildren().setAll(parentContent);
                } catch (Exception ex) {
                    try {
                       // Logger.getLogger(Prestation.class.getName()).log(Level.SEVERE, null, ex);
                        FXMLLoader loader = new FXMLLoader(getClass().getResource(("/com/bonplan/gui/consulterPrestation.fxml")));
                        loader.load();
                        AnchorPane parentContent = loader.getRoot();
                        window = (AnchorPane) button.getParent().getParent().getParent().getParent().getParent().getParent().getParent();
                        ConsulterPrestationController cont = loader.getController();
                        cont.initData(idPrestation);
                        cont.hidebutton();
                        window.getChildren().setAll(parentContent);
                    } catch (IOException ex1) {
                        Logger.getLogger(Prestation.class.getName()).log(Level.SEVERE, null, ex1);
                    }
                }

            }
        });
        return button;
    }

    public void setButton(Button button) {
        this.button = button;
    }

    public int getIdPrestation() {
        return idPrestation;
    }

    public void setIdPrestation(int idPrestation) {
        this.idPrestation = idPrestation;
    }

    public int getIdDiplome() {
        return idDiplome;
    }

    public void setIdDiplome(int idDiplome) {
        this.idDiplome = idDiplome;
    }

    public int getIdInscrit() {
        return idInscrit;
    }

    public void setIdInscrit(int idInscrit) {
        this.idInscrit = idInscrit;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getSalaire() {
        return salaire;
    }

    public void setSalaire(float salaire) {
        this.salaire = salaire;
    }

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public Date getDateAjout() {
        return dateAjout;
    }

    public void setDateAjout(Date dateAjout) {
        this.dateAjout = dateAjout;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public boolean isValide() {
        return valide;
    }

    public void setValide(boolean valide) {
        this.valide = valide;
    }

    public boolean isStatut() {
        return statut;
    }

    public void setStatut(boolean statut) {
        this.statut = statut;
    }

    public Button getSupprimer() {
        this.supprimer.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent t) {
                try {
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/bonplan/gui/gererPrestation.fxml"));
                    Parent root = (Parent) fxmlLoader.load();
                    GererPrestationController controller = fxmlLoader.<GererPrestationController>getController();
                    controller.delete(idPrestation);
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
            }
        });
        return supprimer;
    }

    public void setSupprimer(Button supprimer) {
        this.supprimer = supprimer;
    }

    public Button getApprouver() {
        this.approuver.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent t) {
                try {
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/bonplan/gui/gererPrestation.fxml"));
                    Parent root = (Parent) fxmlLoader.load();
                    GererPrestationController controller = fxmlLoader.<GererPrestationController>getController();
                    controller.confirm(idPrestation);
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
            }
        });
        return approuver;
    }

    public void setApprouver(Button approuver) {
        this.approuver = approuver;
    }

}
