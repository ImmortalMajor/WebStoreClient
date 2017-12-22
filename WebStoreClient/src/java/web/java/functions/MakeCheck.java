package web.java.functions;

import java.util.ArrayList;
import web.java.connector.Service;
import web.java.service.Product;

public class MakeCheck {
         
         public MakeCheck(String name, String phone, ArrayList<Product> products) {
                  Service.getServ().insertCheck(name, phone, products);
                  System.out.println("check in db");
         }
         
}
