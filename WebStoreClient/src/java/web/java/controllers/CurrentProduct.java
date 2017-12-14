package web.java.controllers;

import java.io.Serializable;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import web.java.connector.Service;
import web.java.service.Product;

@ManagedBean(name = "curProd")
@RequestScoped
public class CurrentProduct implements Serializable{

         private Product product;

         public Product getProduct() {
                  
                  Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
                  
                  if(params.get("productId") != null) {
                           product = Service.getServ().getProduct(Integer.valueOf(params.get("productId")));
                           return product;
                  } else {
                           return null;
                  }
         }
}
