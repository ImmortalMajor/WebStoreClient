package ua.java.store.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import ua.java.store.objects.Check;
import ua.java.store.objects.Product;

public class DBCheck {
    
    private DBCheck(){}
    private static DBCheck instance;
    
    public static DBCheck getInstance(){
        
        if(instance == null){
            instance = new DBCheck();
        }
        
        return instance;
    }

//////////////////////////////// GET ////////////////////////////////
    public Check getCheck(int id){
        
        try {
            return getCheck(getChecksStmnt(id));
        } catch (SQLException ex) {
            Logger.getLogger(DBCheck.class.getName()).log(Level.SEVERE, null, ex);
        } 
        
        return null;
    }
    
    public int getChecks(){
        
        try {
                 
            Connection con = Connector.getInstance().getConnection();
            PreparedStatement stmnt = con.prepareStatement("SELECT distinct c.id_c FROM webstore.check_ c;");
            ResultSet res = stmnt.executeQuery();
            res.last(); int id_c = res.getInt("id_c");
            
            res.close();
            stmnt.close();
            
            return id_c;
            
        } catch (SQLException ex) {
            Logger.getLogger(DBCheck.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            Connector.getInstance().closeConnection();
        } 
        
        return 0;
    }
    
    private Check getCheck(PreparedStatement stmnt){
        
        Check check = null;
        ArrayList<Product> products;
        ResultSet res;
        
        try {
            check = new Check();
            products = new ArrayList<>();
            int fullcost = 0;
            
            res = stmnt.executeQuery();
            res.next();
            
            check.setId(res.getInt("id_c"));
            check.setName_u(res.getString("name_u"));  
            
            do{
                Product p = new Product();
                
                p.setId(res.getInt("id_p"));
                p.setName(res.getString("name_p"));
                p.setType(res.getString("name_t"));
                p.setManuf(res.getString("name_m"));
                p.setCost(res.getInt("cost"));
                p.setDiscont(res.getDouble("discont"));   
                p.setAmount(res.getInt("amount_p"));
                p.setSize(res.getInt("size_p"));
                p.setImage(res.getString("image_p"));
                p.setDesc(res.getString("desc_p"));
                
                fullcost += res.getInt("cost") * res.getInt("amount_p");
                
                products.add(p);
            }while(res.next());
            
            check.setProducts(products);
            check.setFullcost(fullcost);
            
            res.close();
            stmnt.close();
                
        } catch (SQLException ex) {
            Logger.getLogger(DBCheck.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            Connector.getInstance().closeConnection();
        }
  
        return check;
    }

    private PreparedStatement getChecksStmnt(int id) throws SQLException{
        Connection con = Connector.getInstance().getConnection();
        PreparedStatement stmnt = con.prepareStatement(""
                + " SELECT c.id_c, c.name_u, c.phone, p.id_p, p.name_p, t.name_t, m.name_m, c.size_p, p.cost, p.discont, c.amount_p, p.desc_p"
                + " FROM webstore.check_ c, webstore.product p, webstore.manuf m, webstore.type_ t"
                + " where c.id_prod = p.id_p and p.id_m = m.id_m and p.id_t = t.id_t and c.id_c = ?;");
        stmnt.setInt(1, id);
        return stmnt;
    }
    
    //////////////////////////////// UPDATE ////////////////////////////////
    
    //////////////////////////////// INSERT ////////////////////////////////
    
    public boolean insertCheck(String name_u, String phone, ArrayList<Product> products){
        try {
            int idCheck = getChecks() + 1;
            
            Connection con = Connector.getInstance().getConnection();
            PreparedStatement stmnt = con.prepareStatement("insert into webstore.check_ values(?, ?, ?, ?, ?, ?, null);");
            
            for(int i = 0; i < products.size(); i++){
                
                stmnt.setInt(1, idCheck);
                stmnt.setString(2, name_u);
                stmnt.setString(3, phone);
                stmnt.setInt(4, products.get(i).getId());
                stmnt.setInt(5, products.get(i).getAmount());
                stmnt.setInt(6, products.get(i).getSize());
                
                stmnt.executeUpdate();
            }
            
            stmnt.close();
            return true;
            
        } catch (SQLException ex) {
            Logger.getLogger(DBCheck.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            Connector.getInstance().closeConnection();
        }
        
    }
    
    //////////////////////////////// DELETE ////////////////////////////////
    public boolean deleteCheck(int id){
        try {
            Connection con = Connector.getInstance().getConnection();
            PreparedStatement stmnt = con.prepareStatement("DELETE from webstore.check_ where webstore.check_.id_c = ?;");
            stmnt.setInt(1, id);
            stmnt.executeUpdate();
            
            stmnt.close();
            return true;
            
        } catch (SQLException ex) {
            Logger.getLogger(DBCheck.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            Connector.getInstance().closeConnection();
        }
    }
}
