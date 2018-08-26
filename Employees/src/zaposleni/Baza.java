
package zaposleni;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Baza {
    static Connection conn;
    static ObservableList<Zaposlenik> list;
    
    public static void connect(){
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost/zaposleni", "root", "mysql");
        } catch (SQLException ex) {
            Logger.getLogger(Baza.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void disconnect(){
        try {
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(Baza.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static ObservableList<Zaposlenik> listaZaposlehih() throws SQLException{
        connect();
        list = FXCollections.observableArrayList();
        
        ResultSet rs = conn.createStatement().executeQuery("select * from employees");
        while(rs.next()){
            Zaposlenik z = new Zaposlenik();
            z.setName(rs.getString("name"));
            z.setAge(rs.getInt("age"));
            z.setAddress(rs.getString("address"));
            z.setSalary(rs.getDouble("salary"));
            z.setId(rs.getInt("id"));
            
            list.add(z);
        }
        disconnect();
        return list;
    }
    
    public static void unos(String ime, String adresa, int godine, double plata) throws SQLException{
        connect();
        
        PreparedStatement ps = conn.prepareStatement("insert into employees values(null, ?, ?, ?, ?)");
        ps.setString(1, ime);
        ps.setInt(2, godine);
        ps.setString(3, adresa);
        ps.setDouble(4, plata);
        ps.execute();
        
        disconnect();
    }
    
    public static ObservableList<Zaposlenik> find(String a, String b) throws SQLException{
        connect();
        
        list = FXCollections.observableArrayList();
        Statement ps = conn.createStatement();
        ps.executeQuery("select * from employees where "+ a + " like '%"+ b +"%'");
        
        ResultSet rs = ps.getResultSet();
        
        while(rs.next()){
            Zaposlenik z = new Zaposlenik();
            z.setName(rs.getString("name"));
            z.setAge(rs.getInt("age"));
            z.setAddress(rs.getString("address"));
            z.setSalary(rs.getDouble("salary"));
            z.setId(rs.getInt("id"));
            
            list.add(z);
        }
        disconnect();
        
        return list;
    }
    
    public static void change(String a, String b, int c) throws SQLException{
        connect();
        
        Statement ps = conn.createStatement();
        ps.execute("update employees set "+ a +" = '"+ b +"' where id = " + c );
        
        disconnect();
    }
    
     public static void change2(String a, int b, int c) throws SQLException{
        connect();
        
        Statement ps = conn.createStatement();
        ps.execute("update employees set "+ a +" = "+ b +" where id = " + c );
        
        disconnect();
    }
     
      public static void change3(String a, double b, int c) throws SQLException{
        connect();
        
        Statement ps = conn.createStatement();
        ps.execute("update employees set "+ a +" = "+ b +" where id = " + c );
        
        disconnect();
    }
    
    public static void delete(int a) throws SQLException{
        connect();
            
        PreparedStatement ps = conn.prepareStatement("delete from employees where id = ?");
        ps.setInt(1, a);
        ps.execute();
        
        disconnect();
    }
}
