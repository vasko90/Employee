
package zaposleni;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

public class Brisanje extends GridPane{
    public Brisanje(){
        setId("rest");
        getStylesheets().add(getClass().getResource("mainStyle.css").toExternalForm());
        setPadding(new Insets(10));
        setVgap(5);
        setHgap(5);
        
        Label lblTitle = new Label("Employee change");
        lblTitle.setId("titles");
        add(lblTitle, 0, 0, 2, 1);
        
        Label lblParametar = new Label("Choose search field:");
        add(lblParametar, 0, 1);
        
        RadioButton rbName = new RadioButton("name");
        rbName.setSelected(true);
        add(rbName, 1, 1);
        
        RadioButton rbAge = new RadioButton("age");
        add(rbAge, 1, 2);
        
        RadioButton rbAddress = new RadioButton("address");
        add(rbAddress, 1, 3);
        
        RadioButton rbSalary = new RadioButton("salary");
        add(rbSalary, 1, 4);
        
        ToggleGroup tg = new ToggleGroup();
        tg.getToggles().addAll(rbName, rbAge, rbAddress, rbSalary);        
        
        Label lblSearch = new Label("Search");
        add(lblSearch, 0, 5);
        
        TextField tfSearch = new TextField();
        add(tfSearch, 1, 5);    
        
        Button btnSearch = new Button("Submit");
        add(btnSearch, 1, 6);        
        
        TableView tw = new TableView();
        tw.setPrefHeight(200);
        TableColumn name = new TableColumn("Name");
        name.setCellValueFactory(new PropertyValueFactory<Zaposlenik,String>("name"));
        TableColumn age = new TableColumn("Age");
        age.setCellValueFactory(new PropertyValueFactory<Zaposlenik,String>("age"));
        TableColumn address = new TableColumn("Address");
        address.setCellValueFactory(new PropertyValueFactory<Zaposlenik,String>("address"));
        TableColumn salary = new TableColumn("Salary");
        salary.setCellValueFactory(new PropertyValueFactory<Zaposlenik,String>("salary"));
        TableColumn id = new TableColumn("Id");
        id.setCellValueFactory(new PropertyValueFactory<Zaposlenik,String>("id"));
        
        tw.getColumns().addAll(name, age, address, salary, id);
        add(tw, 0, 7, 2, 1);
        
        btnSearch.setOnAction(e ->{
             if((tfSearch.getText()).equals("")){
                 try {
                     tw.setItems(Baza.listaZaposlehih());
                 } catch (SQLException ex) {
                     Logger.getLogger(Izmena.class.getName()).log(Level.SEVERE, null, ex);
                 }
             }
                 try {
                     String kolona = ((RadioButton)tg.getSelectedToggle()).getText();
                     String vrednost = tfSearch.getText();
                     
                     tw.setItems(Baza.find(kolona, vrednost));
                 } catch (SQLException ex) {
                     Logger.getLogger(Izmena.class.getName()).log(Level.SEVERE, null, ex);
                 }
        });
        
        HBox hb = new HBox(5);
        add(hb, 0, 8);
        
        Button btnDelete = new Button("Delete");
        btnDelete.setOnAction(e ->{
            if(tw.getSelectionModel().getSelectedItem() != null){
                
            Zaposlenik z = (Zaposlenik)tw.getSelectionModel().getSelectedItem();
            
            try {
                Baza.delete(z.getId());
                Baza.list = FXCollections.observableArrayList();
                tw.setItems(Baza.list);
                Alert a = new Alert(AlertType.CONFIRMATION,"Employee successfully deleted.", ButtonType.OK);
                a.showAndWait();
            } catch (SQLException ex) {
                Logger.getLogger(Brisanje.class.getName()).log(Level.SEVERE, null, ex);
            }
            }
        });
        
        Button btnBack = new Button("Back");
        btnBack.setOnAction(e ->{
            try {
                Zaposleni.changeWindow("Main");
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Brisanje.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InstantiationException ex) {
                Logger.getLogger(Brisanje.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
                Logger.getLogger(Brisanje.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        hb.getChildren().addAll(btnDelete, btnBack);
    }
}
