
package zaposleni;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Zaposleni extends Application{
    public static Stage primary;
    static double x;
    static double y;
    
    public static void changeWindow(String a) throws ClassNotFoundException, InstantiationException, IllegalAccessException{
        Pane p = (Pane)Class.forName("zaposleni."+a).newInstance();
        Scene s = new Scene(p);
        
        s.setOnMousePressed(e ->{
            x = e.getScreenX() - primary.getX();
            y = e.getScreenY() - primary.getY();
        });
        s.setOnMouseDragged(e ->{
            primary.setX(e.getScreenX() - x);
            primary.setY(e.getScreenY() - y);
        });
        
        primary.setScene(s);
        primary.centerOnScreen();
        primary.show();
    }
    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
           primary = primaryStage;
           primary.initStyle(StageStyle.UNDECORATED);
           changeWindow("Main");
    }
    
}
