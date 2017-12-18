package web.java.controllers;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import web.java.connector.Service;
import web.java.functions.Messenger;
import web.java.service.Product;

@ManagedBean(name = "products")
@ViewScoped
public class ProductList implements Serializable{

         private List<Product> products;
         private String type;
         private String manuf;
         
         @PostConstruct
         public void init() {
                  sortList(type, manuf);
         }
         
         public List<Product> getProducts() {
                  return products;
         }

         public void setProducts(List<Product> products) {
                  this.products = products;
         }
         
         private void sortList(String type, String manuf) {
                  products = Service.getServ().getProducts(type, manuf);
         }

         public void sortBy(String var, String val) {
                  switch(var) {
                           case "type":
                                    if(type != null && type.equals(val)) type = null;
                                    else type = val;
                                    break;
                           case "manuf": 
                                    if(manuf != null && manuf.equals(val)) manuf = null;
                                    else manuf = val;
                                    break;
                  }
                  
                  sortList(type, manuf);
         }
}
