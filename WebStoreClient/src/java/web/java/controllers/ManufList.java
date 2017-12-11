package web.java.controllers;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import web.java.connector.Service;
import web.java.service.Manufacturer;

@ManagedBean(name = "manufs")
public class ManufList {
         
         private List<Manufacturer> manufs;
        
         @PostConstruct
         public void init() {
                  if(manufs == null) {manufs = Service.getServ().getManuf();}
         }

         public List<Manufacturer> getManufs() {
                  return manufs;
         }

         public void setTyManufs(List<Manufacturer> manufs) {
                  this.manufs = manufs;
         } 
         
}
