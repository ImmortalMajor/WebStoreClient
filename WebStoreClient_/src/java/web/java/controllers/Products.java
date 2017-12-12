package web.java.controllers;

import java.util.List;
import web.java.connector.Service;
import web.service.Product;

public class Products {
         
         private static List<Product> products;
         private static String type;
         private static String manuf;
         
         public static List<Product> get() {
                  if(products == null) {
                           products = Service.get().getAllProducts();
                  }
                  return products;
         }
         
         private static void sortList(String type, String manuf) {
                  products = Service.get().getProducts(type, manuf);
         }

         public static void sortBy(String var, String val) {
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
