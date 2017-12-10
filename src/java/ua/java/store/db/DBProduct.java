package ua.java.store.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import ua.java.store.objects.Manufacturer;
import ua.java.store.objects.Product;

public class DBProduct {
        
    private DBProduct(){}
    private static DBProduct instance;
    
    public static DBProduct getInstance(){
        
        if(instance == null){
            instance = new DBProduct();
        }
        
        return instance;
    }
    
//////////////////////////////// GET ////////////////////////////////   
    public ArrayList<Product> getProducts(){
        
        try {
            return getProducts(getProductsStmnt());
        } catch (SQLException ex) {
            Logger.getLogger(DBProduct.class.getName()).log(Level.SEVERE, null, ex);
        } 
        
        return null;
    }
    
    public ArrayList<Product> getProducts(String type, String manuf){
        
        try {
            if(type != null && manuf == null){
               return getProducts(getProductsStmnt(type, 1)); 
            } else {
                
                if(type == null && manuf != null){
                    return getProducts(getProductsStmnt(manuf, 2));
                } else {
                    return getProducts(getProductsStmnt(type, manuf));
                }
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBProduct.class.getName()).log(Level.SEVERE, null, ex);
        } 
        
        return null;
    }
    
    public ArrayList<String> getTypes(){
        
        try {
            Connection con = Connector.getInstance().getConnection();
            PreparedStatement stmnt = con.prepareStatement("SELECT t.name_t from webstore.type_ t;");
            ResultSet res = stmnt.executeQuery();
            
            ArrayList<String> types = new ArrayList<>();
            while(res.next()){
                types.add(res.getString("name_t"));
            }
            
            res.close();
            stmnt.close();
            
            return types;
            
        } catch (SQLException ex) {
            Logger.getLogger(DBProduct.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            Connector.getInstance().closeConnection();
        } 
        
        return null;
    }
    
    public ArrayList<Manufacturer> getManuf(){
        
        try {
            Connection con = Connector.getInstance().getConnection();
            PreparedStatement stmnt = con.prepareStatement("SELECT * FROM webstore.manuf m;");
            ResultSet res = stmnt.executeQuery();
            
            ArrayList<Manufacturer> manufs = new ArrayList<>();
            while(res.next()){
                Manufacturer m = new Manufacturer();
                m.setId(res.getInt("id_m"));
                m.setName_m(res.getString("name_m"));
                m.setLogo(res.getString("logo"));
                
                manufs.add(m);
            }
            
            res.close();
            stmnt.close();
            
            return manufs;
            
        } catch (SQLException ex) {
            Logger.getLogger(DBProduct.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            Connector.getInstance().closeConnection();
        } 
        
        return null;
    }
    
    private ArrayList<Product> getProducts(PreparedStatement stmnt){
        ArrayList<Product> products = new ArrayList<>();
        
        ResultSet res;
        
        try {
            res = stmnt.executeQuery();
            
            while(res.next()){
                Product p = new Product();
                
                p.setId(res.getInt("id_p"));
                p.setName(res.getString("name_p"));
                p.setType(res.getString("name_t"));
                p.setManuf(res.getString("name_m"));
                p.setCost(res.getInt("cost"));
                p.setDiscont(res.getDouble("discont"));
                p.setImage(res.getString("image_p"));
                p.setDesc(res.getString("desc_p"));
                
                products.add(p);
            }
            
            res.close();
            stmnt.close();
                
        } catch (SQLException ex) {
            Logger.getLogger(DBProduct.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            Connector.getInstance().closeConnection();
        }
  
        return products;
    }
    
    private PreparedStatement getProductsStmnt() throws SQLException{
        Connection con = Connector.getInstance().getConnection();
        PreparedStatement stmnt = con.prepareStatement("SELECT p.id_p, p.name_p, t.name_t, m.name_m, p.cost, p.discont, p.image_p, p.desc_p"
                + " FROM webstore.product p, webstore.manuf m, webstore.type_ t"
                + " where p.id_m = m.id_m and p.id_t = t.id_t order by p.discont desc;");
        return stmnt;
    } 
    
    private PreparedStatement getProductsStmnt(String arg, int choise) throws SQLException{
        Connection con = Connector.getInstance().getConnection();
        PreparedStatement stmnt;
        
        if(choise == 1){
                 System.out.println("by type");
            stmnt = con.prepareStatement("SELECT p.id_p, p.name_p, t.name_t, m.name_m, p.cost, p.discont, p.image_p, p.desc_p"
                + " FROM webstore.product p, webstore.manuf m, webstore.type_ t"
                + " where p.id_m = m.id_m and p.id_t = t.id_t and t.name_t = ? order by p.discont desc;");
        } else {
                 System.out.println("by manuf");
            stmnt = con.prepareStatement("SELECT p.id_p, p.name_p, t.name_t, m.name_m, p.cost, p.discont, p.image_p, p.desc_p"
                + " FROM webstore.product p, webstore.manuf m, webstore.type_ t"
                + " where p.id_m = m.id_m and p.id_t = t.id_t and m.name_m = ? order by p.discont desc;");
        }
        stmnt.setString(1, arg);
        return stmnt;
    }
    
    private PreparedStatement getProductsStmnt(String type, String manuf) throws SQLException{
        Connection con = Connector.getInstance().getConnection();
        PreparedStatement stmnt = con.prepareStatement("SELECT p.id_p, p.name_p, t.name_t, m.name_m, p.cost, p.discont, p.image_p, p.desc_p"
                + " FROM webstore.product p, webstore.manuf m, webstore.type_ t"
                + " where p.id_m = m.id_m and p.id_t = t.id_t and t.name_t = ? and m.name_m = ? order by p.discont desc;");
        stmnt.setString(1, type);
        stmnt.setString(2, manuf);
        return stmnt;
    }
    
    //////////////////////////////// UPDATE ////////////////////////////////
    public boolean updateProduct(int id, String name, int idT, int idM, int cost, double discont, String desc){
        try {
            Connection con = Connector.getInstance().getConnection();
            
            PreparedStatement stmnt = con.prepareStatement("update webstore.product p "
                    + "set p.name_p = ?, p.id_t = ?, p.id_m = ?, p.cost = ?, p.discont = ?, p.desc_p = ? "
                    + "where p.id_p = ?;");
            stmnt.setString(1, name);
            stmnt.setInt(2, idT);
            stmnt.setInt(3, idM);
            stmnt.setInt(4, cost);
            stmnt.setDouble(5, discont);
            stmnt.setString(6, desc);
            stmnt.setInt(7, id);
            
            stmnt.executeUpdate();
            
            stmnt.close();
            return true;
            
        } catch (SQLException ex) {
            Logger.getLogger(DBProduct.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            Connector.getInstance().closeConnection();
        }
    }
    
    //////////////////////////////// INSERT ////////////////////////////////
    public boolean insertProduct(String name, int idT, int idM, int cost, double discont, String desc){
        try {
            Connection con = Connector.getInstance().getConnection();
            
            PreparedStatement stmnt = con.prepareStatement("insert into webstore.product values(null, ?, ?, ?, ?, ?, ?);");
            stmnt.setString(1, name);
            stmnt.setInt(2, idT);
            stmnt.setInt(3, idM);
            stmnt.setInt(4, cost);
            stmnt.setDouble(5, discont);
            stmnt.setString(6, desc);
            
            stmnt.executeUpdate();
            
            stmnt.close();
            return true;
            
        } catch (SQLException ex) {
            Logger.getLogger(DBProduct.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            Connector.getInstance().closeConnection();
        }
    }
    
    public boolean insertType(String type){
        try {
            Connection con = Connector.getInstance().getConnection();
            
            PreparedStatement stmnt = con.prepareStatement("insert into webstore.type_ values(null, ?);");
            stmnt.setString(1, type);

            
            stmnt.executeUpdate();
            
            stmnt.close();
            return true;
            
        } catch (SQLException ex) {
            Logger.getLogger(DBProduct.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            Connector.getInstance().closeConnection();
        }
    }
    
    public boolean insertManuf(String manuf, String logo){
        try {
            Connection con = Connector.getInstance().getConnection();
            
            PreparedStatement stmnt = con.prepareStatement("insert into webstore.manuf values(null, ?, ?);");
            stmnt.setString(1, manuf);
            stmnt.setString(2, logo);

            
            stmnt.executeUpdate();
            
            stmnt.close();
            return true;
            
        } catch (SQLException ex) {
            Logger.getLogger(DBProduct.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            Connector.getInstance().closeConnection();
        }
    }
    
    //////////////////////////////// DELETE ////////////////////////////////
    public boolean deleteProduct(int id){
        try {
            Connection con = Connector.getInstance().getConnection();
            PreparedStatement stmnt = con.prepareStatement("DELETE from webstore.product where webstore.product.id_p = ?;");
            stmnt.setInt(1, id);
            stmnt.executeUpdate();
            
            stmnt.close();
            return true;
            
        } catch (SQLException ex) {
            Logger.getLogger(DBProduct.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            Connector.getInstance().closeConnection();
        }
    }
    
    public boolean deleteManuf(int id){
        try {
            Connection con = Connector.getInstance().getConnection();
            
            PreparedStatement stmnt = con.prepareStatement("delete from webstore.manuf where webstore.manuf.id_m = ?;");
            stmnt.setInt(1, id);
            
            stmnt.executeUpdate();
            
            stmnt.close();
            return true;
            
        } catch (SQLException ex) {
            Logger.getLogger(DBProduct.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            Connector.getInstance().closeConnection();
        }
    }
}
