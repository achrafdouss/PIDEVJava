/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bonplan.gui;

import com.bonplan.entities.Evenement;
import com.bonplan.entities.Participation;
import com.bonplan.services.EvenementService;
import com.bonplan.services.UserServices;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

/**
 * FXML Controller class
 *
 * @author souab
 */
public class EventParticipantsController implements Initializable {

    @FXML
    private TableView<my2> table;
    @FXML
    private TableColumn<my2, String> categorie;
    @FXML
    private TableColumn<my2, String> date_evenement;
    @FXML
    private TableColumn<my2, String> lieu;
    @FXML
    private TableColumn<my2, String> nomEvenement;

    @FXML
    private TableColumn<my2, String> status;
    Evenement e;


            EvenementService es = new EvenementService();
    UserServices us=new UserServices();
    ObservableList<my2> data ;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        List<my2> list=new ArrayList<>();
        es.eventParticipants(e).forEach((u)->list.add(new my2(u.getUsername(), es.getParticipation(u.getId(), e.id).getDate(), es.getParticipation(u.getId(), e.id).nbre, e.getNomEvenement(), status(es.getParticipation(u.getId(), e.id).getStatus()), e.id)));
    data = FXCollections.observableArrayList(list);
        categorie.setCellValueFactory(new PropertyValueFactory("name"));
        date_evenement.setCellValueFactory(new PropertyValueFactory("date"));
        lieu.setCellValueFactory(new PropertyValueFactory("nbre"));
        nomEvenement.setCellValueFactory(new PropertyValueFactory("event"));
         status.setCellValueFactory(new PropertyValueFactory("status"));

        table.setItems(data);
    } 
        public Evenement getE() {
        return e;
    }

    public void setE(Evenement e) {
        this.e = e;
    }
    public String status(int i) {
        if (i == 0) {
            return "Pending";
        }
        if (i == 1) {
            return "Confirmed";
        }
        if (i == -1) {
            return "Waiting List";
        }
        return "error";
    }


    @FXML
    private void confirmer(ActionEvent event) {
                my2 e =table.getSelectionModel().getSelectedItem();
             Participation p= es.getParticipation(us.AfficherUser(e.getName()).getId(), e.getIdEvent());
             data.remove(e);
             e.setStatus(status(1));
             es.confirmer(p);
             data.add(e);
           sendSMS  sms =new sendSMS();
          // sms.fsendSms("Votre participation a l'evenement "+e.event+" a été confirmé ,l'evenement aura lieu le "+e.getDate());

    }
        public static class my2{
    String name;
    String date;
    int nbre;
    String event;
    int idEvent;

        public int getIdEvent() {
            return idEvent;
        }

        public void setIdEvent(int idEvent) {
            this.idEvent = idEvent;
        }

        @Override
        public String toString() {
            return "my{" + "name=" + name + ", date=" + date + ", nbre=" + nbre + ", event=" + event + ", Status=" + Status + '}';
        }

 

        public  my2(String name, String date, int nbre, String event, String Status,int idEvent) {
            this.name = name;
            this.date = date;
            this.nbre = nbre;
            this.event = event;
            this.Status = Status;
            this.idEvent=idEvent;
        }
    

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
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

        public String getEvent() {
            return event;
        }

        public void setEvent(String event) {
            this.event = event;
        }

        public String getStatus() {
            return Status;
        }

        public void setStatus(String Status) {
            this.Status = Status;
        }
    String Status;
    }

        public class sendSMS {
	public String fsendSms(String msg) {
		try {
			// Construct data
			String apiKey = "apikey=" + "OKV0rd1N8yg-aavn5qZ0I1GbqEvKmTmWvEXb8ZVIOS";
			String message = "&message=" + msg;
			String sender = "&sender=" + "BonPlan";
			String numbers = "&numbers=" + "0021625031993";
			
			// Send data
			HttpURLConnection conn = (HttpURLConnection) new URL("https://api.txtlocal.com/send/?").openConnection();
			String data = apiKey + numbers + message + sender;
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Length", Integer.toString(data.length()));
			conn.getOutputStream().write(data.getBytes("UTF-8"));
			final BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			final StringBuffer stringBuffer = new StringBuffer();
			String line;
			while ((line = rd.readLine()) != null) {
				stringBuffer.append(line);
			}
			rd.close();
			
			return stringBuffer.toString();
		} catch (Exception e) {
			System.out.println("Error SMS "+e);
			return "Error "+e;
		}
	}
}
}
