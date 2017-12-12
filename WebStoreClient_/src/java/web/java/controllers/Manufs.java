package web.java.controllers;

import java.util.List;
import web.java.connector.Service;
import web.service.Manufacturer;

public class Manufs {
                
         private static List<Manufacturer> manufs;
         
         public static List<Manufacturer> get() {
                  if(manufs == null) {
                           manufs = Service.get().getManuf();
                  }
                  return manufs;
         }
    
}
