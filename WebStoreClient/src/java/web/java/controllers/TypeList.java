package web.java.controllers;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import web.java.connector.Service;

@ManagedBean(name = "types")
public class TypeList {
      
         private List<String> types;
         
         @PostConstruct
         public void init() {
                  if(types == null) {types = Service.getServ().getTypes();}
         }

         public List<String> getTypes() {
                  return types;
         }

         public void setTypes(List<String> types) {
                  this.types = types;
         }
         
}
