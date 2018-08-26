
package zaposleni;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

public class Lista extends VBox{
    public Lista() throws SQLException{
        setId("rest");
        getStylesheets().add(getClass().getResource("mainStyle.css").toExternalForm());
        setSpacing(10);
        setPadding(new Insets(10));
        
        TableView tw = new TableView();
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
        tw.setItems(Baza.listaZaposlehih());
        
        Button btnBack = new Button("Back");
        btnBack.setOnAction(e ->{
            try {
                Zaposleni.changeWindow("Main");
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Lista.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InstantiationException ex) {
                Logger.getLogger(Lista.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
                Logger.getLogger(Lista.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        getChildren().addAll(tw, btnBack);
    }
}
