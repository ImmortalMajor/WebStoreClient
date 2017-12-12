package web.java.controllers;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import web.java.connector.Service;
import web.java.service.Product;

@ManagedBean(name = "curProd")
@RequestScoped
public class CurrentProduct implements Serializable{

         private Product product;

         public Product getProduct() {
                  System.out.println("getProduct = " + product);
                  return product;
         }

         public void setProduct(int id) {
                  product = Service.getServ().getProduct(id);
                  System.out.println("---------------------------------------------setProduct = " + product);
         }
}
