/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bonplan.gui;

import com.bonplan.entities.Prestation;
import com.bonplan.services.PrestationService;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Callback;
import jxl.Workbook;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

/**
 * FXML Controller class
 *
 * @author dell
 */
public class ListerPrestationController extends AcceuilFXMLController{

    private int i = 0;
    private int userid;
    private int id;
    private PrestationService ps = new PrestationService();
    @FXML
    private AnchorPane listermainanchor;
    @FXML
    private AnchorPane liste_prestations;
    private ObservableList<Prestation> data;
    private Pagination pagination;
    String[] fonts = new String[]{};
    @FXML
    private Pane pane1;
    @FXML
    private Pane pane2;
    @FXML
    private Pane pane3;
    @FXML
    private TextField rechercher;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        listermainanchor.toFront();
        listermainanchor.toFront();
        data = ps.getAllValides();
        initpagination();
    }


    public void initData(int userid)
    {
        this.userid= userid;
    }
    public void setId(int id) {
        this.id = id;
    }

    @FXML
    public void gotoAjout(ActionEvent event) throws IOException {
        FXMLLoader loader=new FXMLLoader(getClass().getResource(("ajoutPrestation.fxml")));
            loader.load();
            AnchorPane parentContent = loader.getRoot();
            window = (AnchorPane) rechercher.getParent().getParent();
            AjoutPrestationController cont=loader.getController();
 
            window.getChildren().setAll(parentContent);
    }

    public void gotoConsulter(int id) throws IOException {
        FXMLLoader loader=new FXMLLoader(getClass().getResource(("consulterPrestation.fxml")));
            loader.load();
            AnchorPane parentContent = loader.getRoot();
            window = (AnchorPane) rechercher.getParent().getParent();
            ConsulterPrestationController cont=loader.getController();
            window.getChildren().setAll(parentContent);
    }
    /* public void loadtableview() {
        
     liste_prestations.setEditable(false);
     data = ps.getAllValides();
     TableColumn titreCol = new TableColumn("Titre");
     titreCol.setMaxWidth(250);
     TableColumn lieuCol = new TableColumn("Lieu");
     lieuCol.setMaxWidth(150);
     TableColumn categorieCol = new TableColumn("Categorie");
     categorieCol.setMaxWidth(150);
     TableColumn salaireCol = new TableColumn("Remuneration");
     salaireCol.setMinWidth(110);
     TableColumn dateCol = new TableColumn("Date");
     dateCol.setMaxWidth(100);
     TableColumn gotoCol = new TableColumn("Action");
     gotoCol.setMaxWidth(100);
     liste_prestations.getColumns().addAll(titreCol,lieuCol,categorieCol,salaireCol,dateCol,gotoCol);
     gotoCol.setCellValueFactory(new PropertyValueFactory<Prestation,String>("button"));
     titreCol.setCellValueFactory(new PropertyValueFactory<Prestation,String>("titre"));
     dateCol.setCellValueFactory(new PropertyValueFactory<Prestation,String>("DateAjout"));
     categorieCol.setCellValueFactory(new PropertyValueFactory<Prestation,String>("categorie"));
     salaireCol.setCellValueFactory(new PropertyValueFactory<Prestation,String>("salaire"));
     lieuCol.setCellValueFactory(new PropertyValueFactory<Prestation,String>("lieu"));
     liste_prestations.setItems(data);
     }
     */

    @FXML
    public void rechercher() {

        String text = rechercher.getText();
        data = ps.chercher(text);
        liste_prestations.getChildren().remove(pagination);
        initpagination();
    }

    public int itemsPerPage() {

        return 3;
    }

    public VBox createPage(int pageIndex) {
        VBox box = new VBox(5);
        int page = pageIndex * itemsPerPage();
        for (int j = page; j < page + itemsPerPage(); j++) {
            if (j < data.size()) {
                Pane pane = createpane(j);
                box.getChildren().add(pane);
            }
        }

        return box;
    }

    private void initpagination() {

        pagination = new Pagination((int) Math.ceil((double) data.size() / (double) itemsPerPage()), 0);
        pagination.setPageFactory(new Callback<Integer, Node>() {

            @Override
            public Node call(Integer pageIndex) {
                return createPage(pageIndex);
            }
        });

        liste_prestations.setTopAnchor(pagination, 10.0);
        liste_prestations.setRightAnchor(pagination, 10.0);
        liste_prestations.setBottomAnchor(pagination, 10.0);
        liste_prestations.setLeftAnchor(pagination, 10.0);
        liste_prestations.getChildren().addAll(pagination);
    }

    public Pane createpane(int j) {
        Pane pane = new Pane();
        pane.setPrefSize(709, 200);
        Label titre = new Label(data.get(j).getTitre());
        titre.setPrefSize(600, 31);
        titre.setFont(new Font(21));
        titre.setLayoutX(14);
        titre.setLayoutY(14);
        ///////////
        Label categorie = new Label(data.get(j).getCategorie());
        categorie.setLayoutX(645);
        categorie.setLayoutY(52);
        categorie.setPrefSize(136, 31);
        categorie.setFont(new Font(21));
        //////////////
        Label date = new Label(data.get(j).getDateAjout().toString());
        date.setPrefSize(214, 31);
        date.setLayoutX(710);
        date.setLayoutY(14);
        date.setFont(new Font(20));
        date.setAlignment(Pos.TOP_RIGHT);
        ////
        String contenu = data.get(j).getDescription();
        Label preview = new Label(contenu.substring(0, Math.min(contenu.length(), 60)) + "...");
        preview.setPrefSize(700, 131);
        preview.setFont(new Font(19));
        preview.setLayoutX(14);
        preview.setLayoutY(52);
        preview.setWrapText(true);
        //preview.setAlignment(Pos.CENTER);
        ///
        float salary = data.get(j).getSalaire();
        Label salaire = new Label();
        salaire.setPrefSize(170, 31);
        salaire.setFont(new Font(20));
        salaire.setLayoutX(645);
        salaire.setLayoutY(86);
        if (salary == 0.0) {
            salaire.setText("Salaire à négocier");
        } else {
            salaire.setText(Float.toString(data.get(j).getSalaire()) + " TND");
        }
        ///
        Label lieu = new Label(data.get(j).getLieu());
        lieu.setPrefSize(136, 31);
        lieu.setFont(new Font(20));
        lieu.setLayoutX(645);
        lieu.setLayoutY(118);
        ///
        Button consulter = new Button("voir");
        consulter = data.get(j).getButton();
        consulter.setLayoutX(825);
        consulter.setLayoutY(81);
        pane.getChildren().addAll(titre, categorie, date, preview, salaire, consulter, lieu);
        return pane;
    }

    @FXML
    public void exporttoexcel(){
        try {
            data = ps.getAllValides();
            File file = new File(System.getProperty("user.home") + "\\Desktop\\prestations.xls");
            WritableWorkbook myexcel = Workbook.createWorkbook(file);
            WritableSheet mysheet = myexcel.createSheet("PRESTATIONS", 0);
            for (int j = 0; j < data.size(); j++) {
                jxl.write.Label etitre = new jxl.write.Label(0, j, data.get(j).getTitre());
                jxl.write.Label edate = new jxl.write.Label(1, j, data.get(j).getDateAjout().toString());
                jxl.write.Label econtenu = new jxl.write.Label(2, j, data.get(j).getDescription());
                jxl.write.Label ecategorie = new jxl.write.Label(3, j, data.get(j).getCategorie());
                jxl.write.Label elieu = new jxl.write.Label(4, j, data.get(j).getLieu());
                jxl.write.Label esalaire = new jxl.write.Label(5, j, Float.toString(data.get(j).getSalaire()));
                mysheet.addCell(etitre);
                mysheet.addCell(edate);
                mysheet.addCell(econtenu);
                mysheet.addCell(ecategorie);
                mysheet.addCell(elieu);
                mysheet.addCell(esalaire);
            }
            myexcel.write();
            myexcel.close();
            showsuccessalert();
        } catch (IOException | WriteException ex) {
            Logger.getLogger(ListerPrestationController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void showsuccessalert() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Liste exportée");
        alert.setHeaderText(null);
        alert.setContentText("Les prestations disponibles ont été exportées vers votre bureau");
        alert.showAndWait();
        }
    private void showfailalert() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText("Les prestations disponibles n'ont pas pu être exportées\n Veuillez réessayer plus tard");
        alert.showAndWait();
        }
}
