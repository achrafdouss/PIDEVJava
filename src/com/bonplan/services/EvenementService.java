/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bonplan.services;

import com.bonplan.entities.Evenement;
import com.bonplan.entities.Participation;
import com.bonplan.entities.User;
import com.bonplan.interfaces.IEvenementService;
import com.bonplan.util.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 *
 * @author bhk
 */
public class EvenementService implements IEvenementService {

    Connection conn = DataSource.getInstance().getConnection();
    Statement stmt;

    @Override
    public void createEvenement(Evenement e) {
        try {
            String req = "INSERT INTO evenement(categorie, nbrplace, date_evenement, lieu, description, nomEvenement, id_owner,photo,lat,lng,prix)"
                    + " VALUES (?,?,?,?,?,?,?,?,?,?,?)";

            PreparedStatement st = conn.prepareStatement(req);
            st.setString(1, e.getCategorie());
            st.setInt(2, e.getNbrplace());
            st.setString(3, e.getDate_evenement());
            st.setString(4, e.getLieu());
            st.setString(5, e.getDescription());
            st.setString(6, e.getNomEvenement());
            st.setInt(7, User.getUserconnected());
            st.setString(8, e.getPhoto());
            st.setString(9, e.getLat());
            st.setString(10, e.getLng());
            st.setInt(11, e.getPrix());
            st.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(EvenementService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<Evenement> getAll() {
        ArrayList<Evenement> listEvenements = new ArrayList<>();
        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("Select categorie, nbrplace, date_evenement, lieu, description, nomEvenement, id_owner,id_event,photo,lat,lng,prix from evenement");
            while (rs.next()) {
                // System.out.println(rs.getString(2) + " (" + rs.getString(3) + ")");
                listEvenements.add(new Evenement(rs.getInt(8), rs.getString(1), rs.getInt(2), rs.getString(3), rs.getString(4),
                        rs.getString(5), rs.getString(6), rs.getInt(7), rs.getString(9), rs.getString(10), rs.getString(11), rs.getInt(12)));
            }
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(EvenementService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listEvenements;
    }

    @Override
    public void update(Evenement e) {
        try {
            String req = "UPDATE `evenement` SET "
                    + "`nbrplace` = ?, "
                    + "`categorie`= ?, "
                    + "`date_evenement`= ?,"
                    + "`lieu`= ?,"
                    + "`prix`= ?,"
                    + "`description`= ?,"
                    + "`nomEvenement`= ?,"
                    + "`photo`= ?,"
                    + "`lat`= ?,"
                    + "`lng`= ? "
                    + "WHERE `id_event` = ?";
            System.out.println(req);
            PreparedStatement st = conn.prepareStatement(req);
            st.setInt(1, e.getNbrplace());
            st.setString(2, e.getCategorie());
            st.setInt(11, e.getId());
            st.setString(3, e.getDate_evenement());
            st.setString(4, e.getLieu());
            st.setInt(5, e.getPrix());
            st.setString(6, e.getDescription());
            st.setString(7, e.getNomEvenement());
            st.setString(8, e.getPhoto());
            st.setString(9, e.getLat());
            st.setString(10, e.getLng());

            st.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(EvenementService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void delete(int id) {
        try {
            String req = "DELETE FROM `evenement` WHERE `id_event` = ? ";
            PreparedStatement st = conn.prepareStatement(req);
            st.setInt(1, id);
            st.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(EvenementService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void participer(Evenement e, User u, Integer nbre) {
        try {
            String req = "INSERT INTO participer_eventt (id_event, id_inscrit, date, nbre, status)"
                    + " VALUES (?,?,?,?,?)";
            PreparedStatement st = conn.prepareStatement(req);
            st.setInt(1, e.getId());
            st.setInt(2, u.getId());
            st.setString(3, DateTimeFormatter.ofPattern("yyy-MM-dd").format(LocalDate.now()));
            st.setInt(4, nbre);
            if (e.nbrplace - nbre >= 0) {
                st.setInt(5, 0);
            } else {
                st.setInt(5, -1);
            }

            st.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(EvenementService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<Evenement> getMyEvents() {
        ArrayList<Evenement> listEvenements = new ArrayList<>();
        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("Select categorie, nbrplace, date_evenement, lieu, description, nomEvenement, id_owner,id_event,photo,lat,lng,prix from evenement");
            while (rs.next()) {
                // System.out.println(rs.getString(2) + " (" + rs.getString(3) + ")");
                listEvenements.add(new Evenement(rs.getInt(8), rs.getString(1), rs.getInt(2), rs.getString(3), rs.getString(4),
                        rs.getString(5), rs.getString(6), rs.getInt(7), rs.getString(9), rs.getString(10), rs.getString(11), rs.getInt(12)));
            }
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(EvenementService.class.getName()).log(Level.SEVERE, null, ex);
        }
        ArrayList<Evenement> MyEvents = new ArrayList<>();
        listEvenements.forEach((e) -> {
            if (e.getId_owner() == User.getUserconnected()) {
                MyEvents.add(e);
            }

        });
        return MyEvents;
    }

    @Override
    public List<User> eventParticipants(Evenement e) {
        ArrayList<User> list = new ArrayList<>();
        ArrayList<Participation> listP = new ArrayList<>();
        UserServices us = new UserServices();
        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("Select id,id_event, id_inscrit, date, nbre, status from participer_eventt");
            while (rs.next()) {
                listP.add(new Participation(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getString(4), rs.getInt(5), rs.getInt(6)));
            }
            stmt.close();
            listP.forEach((p) -> {
                if (p.id_event == e.id) {
                    list.add(us.AfficherUserId(p.id_inscrit));
                }
            });

        } catch (SQLException ex) {
            Logger.getLogger(EvenementService.class.getName()).log(Level.SEVERE, null, ex);
        }

        return list;
    }

    @Override
    public List<Evenement> mesParticipations() {
        ArrayList<Evenement> list = new ArrayList<>();
        ArrayList<Participation> listP = new ArrayList<>();
        UserServices us = new UserServices();
        EvenementService es = new EvenementService();
        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("Select id,id_event, id_inscrit, date, nbre, status from participer_eventt");
            while (rs.next()) {
                listP.add(new Participation(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getString(4), rs.getInt(5), rs.getInt(6)));
            }
            stmt.close();
            listP.forEach((p) -> {
                if (p.id_inscrit == User.getUserconnected()) {
                    es.getAll().forEach((e) -> {
                        if (p.id_event == e.id) {
                            list.add(e);
                        }
                    });
                }
            });

        } catch (SQLException ex) {
            Logger.getLogger(EvenementService.class.getName()).log(Level.SEVERE, null, ex);
        }

        return list;
    }

    @Override
    public void annulerParticipation(int id) {
        try {
            EvenementService es = new EvenementService();
            Evenement e;
            Participation p;
            e = es.getEvenement(id);
            p = es.getParticipation(User.getUserconnected(), id);
            e.setNbrplace(e.getNbrplace() + p.nbre);
            es.update(e);
            String req = "DELETE FROM `participer_eventt` WHERE `id` = ? ";
            PreparedStatement st = conn.prepareStatement(req);
            st.setInt(1, p.id);
            st.executeUpdate();

            List<Participation> lp = new ArrayList<>();
            List<Participation> lp2 = new ArrayList<>();

            es.eventParticipants(e).forEach(u -> lp.add(es.getParticipation(u.getId(), 1)));
            lp2 = lp.stream().distinct().filter((x) -> x.status == -1 && x.nbre <= e.getNbrplace()).collect(Collectors.toCollection(ArrayList<Participation>::new));
            Participation pp = lp2.stream().sorted(Comparator.comparing(Participation::getId)).findFirst().get();
             String req2 = "UPDATE `participer_eventt` SET `status`=0 WHERE `id`=?";
            PreparedStatement st2 = conn.prepareStatement(req2);
            st2.setInt(1, pp.getId());
            st2.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(EvenementService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public Participation getParticipation(int idInscrit, int idEvent) {
        Participation p = new Participation();
        String req = "Select id,id_event, id_inscrit, date, nbre, status from participer_eventt where `id_event` = ? and `id_inscrit` = ?";
        try {
            PreparedStatement st = conn.prepareStatement(req);
            st.setInt(1, idEvent);
            st.setInt(2, idInscrit);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                p.setId(rs.getInt(1));
                p.setId_event(rs.getInt(2));
                p.setId_inscrit(rs.getInt(3));
                p.setDate(rs.getString(4));
                p.setNbre(rs.getInt(5));
                p.setStatus(rs.getInt(6));
            }
        } catch (SQLException ex) {
            Logger.getLogger(EvenementService.class.getName()).log(Level.SEVERE, null, ex);
        }

        return p;
    }

    @Override
    public Evenement getEvenement(int idEvent) {
        EvenementService es = new EvenementService();
        Evenement ev = new Evenement();
        ev = es.getAll().stream().filter(e -> e.id == idEvent).findFirst().get();
        return ev;

    }

    @Override
    public void confirmer(Participation p) {
        try {
            String req = "UPDATE `participer_eventt` SET `status`=1 WHERE `id`=?";
            PreparedStatement st = conn.prepareStatement(req);
            st.setInt(1, p.getId());
            st.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(EvenementService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public List<Evenement> find(int prixmax, int prixmin, String lieu, String categorie) {
        List<Evenement> list = new ArrayList<>();
        EvenementService es = new EvenementService();
        list = es.getAll();
        if (!categorie.equals("")) {
            list = list.stream().filter(e -> e.getCategorie().equals(categorie)).collect(Collectors.toCollection(ArrayList<Evenement>::new));
            System.out.println("**1");
        }
        if (!lieu.equals("")) {
            list = list.stream().filter(e -> e.getLieu().equals(lieu)).collect(Collectors.toCollection(ArrayList<Evenement>::new));
            System.out.println("**2");
        }

        if (prixmax != -1) {
            list = list.stream().filter(e -> e.getPrix() <= prixmax).collect(Collectors.toCollection(ArrayList<Evenement>::new));
            System.out.println("**3");
        }

        if (prixmin != -1) {
            list = list.stream().filter(e -> e.getPrix() >= prixmin).collect(Collectors.toCollection(ArrayList<Evenement>::new));
            System.out.println("**4");
        }
        return list;
    }

    @Override
    public void waitlist(Evenement e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


}
