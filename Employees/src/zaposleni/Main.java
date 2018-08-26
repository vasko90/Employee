
package zaposleni;

import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class Main extends VBox{
     public Main(){
         setId("main");
         getStylesheets().add(getClass().getResource("mainStyle.css").toExternalForm());
         setSpacing(10);
         setPadding(new Insets(5));
         
         Label lblTitle = new Label("Welcome");
         lblTitle.setId("title");
         
         Button btnLista = new Button("Employees list");
         btnLista.setId("gl");
         btnLista.setOnAction(e ->{
             try {
                 Zaposleni.changeWindow("Lista");
             } catch (ClassNotFoundException ex) {
                 Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
             } catch (InstantiationException ex) {
                 Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
             } catch (IllegalAccessException ex) {
                 Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
             }
         });
         
         Button btnUnos = new Button("Enter new employee");
         btnUnos.setId("gl");
         btnUnos.setOnAction(e ->{
             try {
                 Zaposleni.changeWindow("Unos");
             } catch (ClassNotFoundException ex) {
                 Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
             } catch (InstantiationException ex) {
                 Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
             } catch (IllegalAccessException ex) {
                 Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
             }
         });
         
         Button btnFind = new Button("Find employee");
         btnFind.setId("gl");
         btnFind.setOnAction(e ->{
             try {
                 Zaposleni.changeWindow("Find");
             } catch (ClassNotFoundException ex) {
                 Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
             } catch (InstantiationException ex) {
                 Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
             } catch (IllegalAccessException ex) {
                 Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
             }
         });
         
         Button btnIzmena = new Button("Change employee data");
         btnIzmena.setId("gl");
         btnIzmena.setOnAction(e ->{
             try {
                 Zaposleni.changeWindow("Izmena");
             } catch (ClassNotFoundException ex) {
                 Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
             } catch (InstantiationException ex) {
                 Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
             } catch (IllegalAccessException ex) {
                 Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
             }
         });
         
         Button btnObrisi = new Button("Delete employee");
         btnObrisi.setId("gl");
         btnObrisi.setOnAction(e ->{
             try {
                 Zaposleni.changeWindow("Brisanje");
             } catch (ClassNotFoundException ex) {
                 Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
             } catch (InstantiationException ex) {
                 Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
             } catch (IllegalAccessException ex) {
                 Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
             }
         });
         
         Button btnZatvori = new Button("Close");
         btnZatvori.setId("gl");
         btnZatvori.setOnAction(e ->{
             Platform.exit();
         });
         
         getChildren().addAll(lblTitle, btnLista, btnUnos, btnFind, btnIzmena, btnObrisi, btnZatvori);
     }
    
}
