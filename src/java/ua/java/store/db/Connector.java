package ua.java.store.db;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class Connector {
    
    public static Connection connection;
    public static InitialContext ic;
    public static DataSource data;
    
    private Connector(){}
    private static Connector instance;
    
    public static Connector getInstance(){
        
        if(instance == null){
            instance = new Connector();
        }
        
        return instance;
    }
    
    public Connection getConnection(){
        
        try {
        
            if(connection == null || connection.isClosed()){
                ic = new InitialContext();
                data = (DataSource) ic.lookup("java:comp/env/jdbc/webstore");
                connection = data.getConnection();
            }
           
        } catch (NamingException ex) {
            Logger.getLogger(Connector.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Connector.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return connection;
    }
    
    public void closeConnection(){
        
        if(connection != null){
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(Connector.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
        
}
