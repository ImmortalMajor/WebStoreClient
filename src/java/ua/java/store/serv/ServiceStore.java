package ua.java.store.serv;

import java.util.ArrayList;
import java.util.Map;
import javax.jws.WebService;
import ua.java.store.db.DBCheck;
import ua.java.store.db.DBProduct;
import ua.java.store.db.DBUser;
import ua.java.store.objects.Check;
import ua.java.store.objects.Manufacturer;
import ua.java.store.objects.Product;
import ua.java.store.objects.User;

@WebService(serviceName="ServiceStore")
public class ServiceStore {
    
    public Check getCheck(int id){
        return DBCheck.getInstance().getCheck(id);
    }
    
    public int getChecks(){
        return DBCheck.getInstance().getChecks();
    }
    
    public boolean insertCheck(int idU, ArrayList<Product> products){
        return DBCheck.getInstance().insertCheck(idU, products);
    }
    
    public boolean deleteCheck(int id){
        return DBCheck.getInstance().deleteCheck(id);
    }
    
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////      
    public ArrayList<Product> getAllProducts(){    
        return DBProduct.getInstance().getProducts();
    }
    
    public Product getProduct(int id){    
        return DBProduct.getInstance().getProduct(id);
    }
    
    public ArrayList<Product> getProducts(String type, String manuf){  
        if(type == null && manuf == null)
                 return getAllProducts();
        else
                 return DBProduct.getInstance().getProducts(type, manuf);
    }
    
    public ArrayList<String> getTypes(){    
        return DBProduct.getInstance().getTypes();
    }
    
    public ArrayList<Manufacturer> getManuf(){    
        return DBProduct.getInstance().getManuf();
    }
    
    public boolean updateProduct(int id, String name, int idT, int idM, int cost, double discont, String desc){
        return DBProduct.getInstance().updateProduct(id, name, idT, idM, cost, discont, desc);
    }
    
    public boolean insertProduct(String name, int idT, int idM, int cost, double discont, String desc){
        return DBProduct.getInstance().insertProduct(name, idT, idM, cost, discont, desc);
    }
    
    public boolean insertType(String type){
        return DBProduct.getInstance().insertType(type);
    }
    
    public boolean insertManuf(String manuf, String logo){
        return DBProduct.getInstance().insertManuf(manuf, logo);
    }
    
    public boolean deleteProduct(int id){
        return DBProduct.getInstance().deleteProduct(id);
    }
    
    public boolean deleteManuf(int id){
        return DBProduct.getInstance().deleteManuf(id);
    }
    
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////  
    public ArrayList<User> getAllUsers(){
        return DBUser.getInstance().getUsers();
    }
    
    public ArrayList<User> getUserById(int id){
        return DBUser.getInstance().getUsers(id);
    }
    
    public ArrayList<User> getUserByName(String name){
        return DBUser.getInstance().getUsers(name);
    }
    
    public User getUser(String login, String password){
        return DBUser.getInstance().getUsers(login, password).get(0);
    }
    
    public boolean updateUser(int id, String name, String phone, String login, String password, String email){
        return DBUser.getInstance().updateUser(id, name, phone, login, password, email);
    }
    
    public boolean insertUser(String name, String phone, String login, String password, String email){
        return DBUser.getInstance().insertUser(name, phone, login, password, email);
    }
    
    public boolean deleteUser(int id){
        return DBUser.getInstance().deleteUser(id);
    }
    
}
