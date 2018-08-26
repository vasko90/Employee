
package zaposleni;

import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

public class Unos extends GridPane{
    public Unos(){
        setPrefSize(320, 200);
        setId("rest");
        getStylesheets().add(getClass().getResource("mainStyle.css").toExternalForm());
        setPadding(new Insets(10));
        setVgap(5);
        setHgap(5);
        
        Label lblTitle = new Label("New employee");
        lblTitle.setId("titles");
        add(lblTitle, 0, 0, 2, 1);
        
        Label lblName = new Label("Name:");
        add(lblName, 0, 1);
        
        Label lblAddress = new Label("Address:");
        add(lblAddress, 0, 2);
        
        Label lblAge = new Label("Age:");
        add(lblAge, 0, 3);
        
        Label lblSalary = new Label("Salary:");
        add(lblSalary, 0, 4);
        
        TextField tfName = new TextField();
        add(tfName, 1, 1);
        
        TextField tfAddress = new TextField();
        add(tfAddress, 1, 2);
        
        TextField tfAge = new TextField();
        add(tfAge, 1, 3);
        
        TextField tfSalary = new TextField();
        add(tfSalary, 1, 4);
        
        HBox hb = new HBox(5);
        
        Button btnSave = new Button("Save");
        btnSave.setOnAction(e ->{
            if(!(tfName.getText()).equals("") || !(tfAge.getText()).equals("") 
                || !(tfAddress.getText()).equals("") || !(tfSalary.getText()).equals("")){
                
            Zaposlenik z = new Zaposlenik();
            
            try{
            z.setName(tfName.getText());
            z.setAge(Integer.parseInt(tfAge.getText()));
            z.setAddress(tfAddress.getText());
            z.setSalary(Double.valueOf(tfSalary.getText())); 
            
            Baza.unos(z.getName(), z.getAddress(), z.getAge(), z.getSalary());
            Alert a = new Alert(AlertType.CONFIRMATION, "New employee successfully saved.",
                ButtonType.OK);
            a.showAndWait();
            
            }catch(Exception ex){
                Alert a = new Alert(AlertType.ERROR, "Fields Age and Salary must be filled with numbers!",
                ButtonType.OK);
                a.showAndWait();
            }
            }else{
                Alert a = new Alert(AlertType.ERROR,"All fields must be filled!", ButtonType.OK);
                a.showAndWait();
            }
           
        });
        
        Button btnBack = new Button("Back");
        btnBack.setOnAction(e ->{
            try {
                Zaposleni.changeWindow("Main");
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Unos.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InstantiationException ex) {
                Logger.getLogger(Unos.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
                Logger.getLogger(Unos.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        hb.getChildren().addAll(btnSave, btnBack);
        
        add(hb, 0, 6);
    }
    
}
