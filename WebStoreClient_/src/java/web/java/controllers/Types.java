package web.java.controllers;

import java.util.List;
import web.java.connector.Service;

public class Types {
                      
         private static List<String> types;
         
         public static List<String> get() {
                  if(types == null) {
                           types = Service.get().getTypes();
                  }
                  return types;
         }
       
}
