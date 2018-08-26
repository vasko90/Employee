
package zaposleni;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

public class Izmena extends GridPane{
    public Izmena() throws SQLException{
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
        
        Label lblInfo = new Label("Change employee data");
        add(lblInfo, 0, 8);
        
        Label lblChange = new Label("Choose field");
        add(lblChange, 0, 9);
        
        Label lblChange2 = new Label("Enter new value");
        add(lblChange2, 1, 9);
        
        ComboBox cbChoice = new ComboBox();
        cbChoice.getItems().addAll("name", "address", "age", "salary");
        add(cbChoice, 0, 10);
        
        TextField tfValue = new TextField();
        add(tfValue, 1, 10);
        
        HBox hb = new HBox(5);
        add(hb, 0, 11);
        
        Button btnSub = new Button("Submit change");
        btnSub.setOnAction(e ->{
            if(tw.getSelectionModel().getSelectedItem() != null && cbChoice.getValue() != null &&
                    !tfValue.getText().equals("")){
                Zaposlenik z = (Zaposlenik)tw.getSelectionModel().getSelectedItem();
                switch (String.valueOf(cbChoice.getValue())) {
                    case "name":
                    case "address":
                        try {
                            String col = String.valueOf(cbChoice.getValue());
                            String val = tfValue.getText();
                            int cod = z.getId();
                            Baza.change(col, val, cod);
                            tw.setItems(Baza.find(((RadioButton)tg.getSelectedToggle()).getText(), tfSearch.getText()));
                            Alert a = new Alert(AlertType.CONFIRMATION, "Employee successfully changed.", ButtonType.OK);
                            a.showAndWait();
                        } catch (SQLException ex) {
                            Logger.getLogger(Izmena.class.getName()).log(Level.SEVERE, null, ex);
                        }       break;
                    case "age":
                        try {
                            Baza.change2(String.valueOf(cbChoice.getValue()), Integer.parseInt(tfValue.getText()), z.getId());
                            tw.setItems(Baza.find(((RadioButton)tg.getSelectedToggle()).getText(), tfSearch.getText()));
                            Alert a = new Alert(AlertType.CONFIRMATION, "Employee successfully changed.", ButtonType.OK);
                            a.showAndWait();
                        } catch (Exception ex) {
                            Alert a = new Alert(AlertType.ERROR, "Field must be filled with number.", ButtonType.OK);
                            a.showAndWait();
                        }   break;
                    default:
                        try {
                            Baza.change3(String.valueOf(cbChoice.getValue()), Double.parseDouble(tfValue.getText()), z.getId());
                            tw.setItems(Baza.find(((RadioButton)tg.getSelectedToggle()).getText(), tfSearch.getText()));
                            Alert a = new Alert(AlertType.CONFIRMATION, "Employee successfully changed.", ButtonType.OK);
                            a.showAndWait();
                        } catch (Exception ex) {
                            Alert a = new Alert(AlertType.ERROR, "Field must be filled with number.", ButtonType.OK);
                            a.showAndWait();
                        }   break;
                }
            }
        });
        
        Button btnBack = new Button("Back");
        btnBack.setOnAction(e ->{
            try {
                Zaposleni.changeWindow("Main");
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Izmena.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InstantiationException ex) {
                Logger.getLogger(Izmena.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
                Logger.getLogger(Izmena.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        hb.getChildren().addAll(btnSub, btnBack);
    }

}
