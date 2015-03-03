
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Александр
 */
public class DBConnection {

    private Connection con = null;
    private Statement st = null;
    
    private static DBConnection instance = null;

    protected DBConnection() {
        
        
        try {

            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/Parking";
            con = DriverManager.getConnection(url, "root", "root");
            System.out.println("Connected to base");
            st = con.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static DBConnection getInsance(){
        if(instance == null){
            instance = new DBConnection();
        }
        return instance;
    }
    

    /**
     * @return the con
     */
    public Connection getCon() {
        return con;
    }

    /**
     * @return the st
     */
    public Statement getSt() {
        return st;
    }

}
