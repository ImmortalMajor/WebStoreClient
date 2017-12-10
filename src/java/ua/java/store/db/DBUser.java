package ua.java.store.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import ua.java.store.objects.User;

public class DBUser {
    private DBUser(){}
    private static DBUser instance;
    
    public static DBUser getInstance(){
        
        if(instance == null){
            instance = new DBUser();
        }
        
        return instance;
    }

//////////////////////////////// GET ////////////////////////////////    
    public ArrayList<User> getUsers(){
        
        try {
            return getUsers(getUsersStmnt());
        } catch (SQLException ex) {
            Logger.getLogger(DBUser.class.getName()).log(Level.SEVERE, null, ex);
        } 
        
        return null;
    }
    
    public ArrayList<User> getUsers(int id){
        
        try {
            return getUsers(getUsersStmnt(id));
        } catch (SQLException ex) {
            Logger.getLogger(DBUser.class.getName()).log(Level.SEVERE, null, ex);
        } 
        
        return null;
    }
    
    public ArrayList<User> getUsers(String name){
        
        try {
            return getUsers(getUsersStmnt(name));
        } catch (SQLException ex) {
            Logger.getLogger(DBUser.class.getName()).log(Level.SEVERE, null, ex);
        } 
        
        return null;
    }
    
    public ArrayList<User> getUsers(String email, String password){
        
        try {
            return getUsers(getUsersStmnt(email, password));
        } catch (SQLException ex) {
            Logger.getLogger(DBUser.class.getName()).log(Level.SEVERE, null, ex);
        } 
        
        return null;
    }
    
    private ArrayList<User> getUsers(PreparedStatement stmnt){
        ArrayList<User> users = new ArrayList<>();
        
        ResultSet res;
        
        try {
            res = stmnt.executeQuery();
            
            while(res.next()){
                User u = new User();
                
                u.setId(res.getInt("id_u"));
                u.setName(res.getString("name_u"));
                u.setPhone(res.getString("phone_u"));
                u.setLogin(res.getString("login_u"));
                u.setPass(res.getString("password_u"));
                u.setEmail(res.getString("email_u"));
                
                users.add(u);
            }
            
            res.close();
            stmnt.close();
                
        } catch (SQLException ex) {
            Logger.getLogger(DBUser.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            Connector.getInstance().closeConnection();
        }
  
        return users;
    }
    
    private PreparedStatement getUsersStmnt() throws SQLException{
        Connection con = Connector.getInstance().getConnection();
        PreparedStatement stmnt = con.prepareStatement("SELECT * from webstore.user_");
        return stmnt;
    } 
    
    private PreparedStatement getUsersStmnt(int id) throws SQLException{
        Connection con = Connector.getInstance().getConnection();
        PreparedStatement stmnt = con.prepareStatement("SELECT * from webstore.user_ u where u.id_u = ?");
        stmnt.setInt(1, id);
        return stmnt;
    }
    
    private PreparedStatement getUsersStmnt(String name) throws SQLException{
        Connection con = Connector.getInstance().getConnection();
        PreparedStatement stmnt = con.prepareStatement("SELECT * from webstore.user_ u where u.name_u = ?");
        stmnt.setString(1, name);
        return stmnt;
    }
    
    private PreparedStatement getUsersStmnt(String email, String password) throws SQLException{
        Connection con = Connector.getInstance().getConnection();
        PreparedStatement stmnt = con.prepareStatement("SELECT * from webstore.user_ u where u.email_u=? and u.password_u=?;");
        stmnt.setString(1, email);
        stmnt.setString(2, password);
        return stmnt;
    }
    
    //////////////////////////////// UPDATE ////////////////////////////////
    public boolean updateUser(int id, String name, String phone, String login, String password, String email){
        try {
            Connection con = Connector.getInstance().getConnection();
            PreparedStatement stmnt = con.prepareStatement("update webstore.user_ u "
                    + "set u.name_u = ?, u.phone_u = ?, u.login_u = ?, u.password_u = ?, u.email_u=? "
                    + "where u.id_u = ?; ");
            stmnt.setString(1, name);
            stmnt.setString(2, phone);
            stmnt.setString(3, login);
            stmnt.setString(4, password);
            stmnt.setString(5, email);
            stmnt.setInt(6, id);
            stmnt.executeUpdate();
            
            stmnt.close();
            return true;
            
        } catch (SQLException ex) {
            Logger.getLogger(DBUser.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            Connector.getInstance().closeConnection();
        }        
    }
    
    //////////////////////////////// INSERT ////////////////////////////////
    public boolean insertUser(String name, String phone, String login, String password, String email){
        try {
            Connection con = Connector.getInstance().getConnection();
            PreparedStatement stmnt = con.prepareStatement("insert into webstore.user_ values(null, ?, ?, ?, ?, ?);");
            stmnt.setString(1, name);
            stmnt.setString(2, phone);
            stmnt.setString(3, login);
            stmnt.setString(4, password);
            stmnt.setString(5, email);
            stmnt.executeUpdate();
            
            stmnt.close();
            return true;
            
        } catch (SQLException ex) {
            Logger.getLogger(DBUser.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            Connector.getInstance().closeConnection();
        }
    }
    
    //////////////////////////////// DELETE ////////////////////////////////
    public boolean deleteUser(int id){
        try {
            Connection con = Connector.getInstance().getConnection();
            PreparedStatement stmnt = con.prepareStatement("DELETE from webstore.user_  where webstore.user_.id_u = ?;");
            stmnt.setInt(1, id);
            stmnt.executeUpdate();
            
            stmnt.close();
            return true;
            
        } catch (SQLException ex) {
            Logger.getLogger(DBUser.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            Connector.getInstance().closeConnection();
        }
    }
}
