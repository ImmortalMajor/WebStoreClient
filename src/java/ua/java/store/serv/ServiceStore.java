package ua.java.store.serv;

import java.util.ArrayList;
import java.util.Map;
import javax.jws.WebService;
import ua.java.store.db.DBCheck;
import ua.java.store.db.DBProduct;
import ua.java.store.objects.Check;
import ua.java.store.objects.Manufacturer;
import ua.java.store.objects.Product;

@WebService(serviceName="ServiceStore")
public class ServiceStore {
    
    public Check getCheck(int id){
        return DBCheck.getInstance().getCheck(id);
    }
    
    public int getChecks(){
        return DBCheck.getInstance().getChecks();
    }
    
    public boolean insertCheck(String name, String phone, ArrayList<Product> products){
        return DBCheck.getInstance().insertCheck(name, phone, products);
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
    
}
