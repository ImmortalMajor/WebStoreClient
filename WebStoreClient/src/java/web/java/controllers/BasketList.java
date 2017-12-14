package web.java.controllers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import web.java.connector.Service;
import web.java.service.Product;

@ManagedBean(name = "basket", eager = true)
@SessionScoped
public class BasketList implements Serializable {
         
         private List<Product> basket;
         
         @PostConstruct
         public void init() {
                  if(basket == null) { basket = new ArrayList(); }
         }

         public List<Product> getBasket() {
                  return basket;
         }

         public void setBasket(List<Product> basket) {
                  this.basket = basket;
         }
         
         public void add() {
                  basket.add(getSelectedProduct("basketAdd"));
         }
         
         public void remove() {
                  Product selectedProd = getSelectedProduct("basketRemove");
                  Product removedProd = null;
                  
                  for(Product p : basket) {
                           if(p.getId() == selectedProd.getId()) {
                                    removedProd = p;
                           }
                  }
                  
                  basket.remove(removedProd);
         }       
         
         private Product getSelectedProduct(String param) {
                  
                  Map<String, String> params = 
                           FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
                  int id = Integer.parseInt(params.get(param));
                  return Service.getServ().getProduct(id);
         }
}
