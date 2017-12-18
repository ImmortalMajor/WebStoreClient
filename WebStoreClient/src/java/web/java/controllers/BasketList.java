package web.java.controllers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.AjaxBehaviorEvent;
import web.java.connector.Service;
import web.java.service.Product;

@ManagedBean(name = "basket", eager = true)
@SessionScoped
public class BasketList implements Serializable {
         
         private List<Product> basket;
         private int size;
         private int amount;
         
         public BasketList(){}
         
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
         
         public void basketAction(AjaxBehaviorEvent e) throws AbortProcessingException{
                  Map<String, String> params = 
                           FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
                  
                  int id = Integer.parseInt(params.get("basket"));
                  
                  switch(params.get("basketAction")) {
                           
                           case "В корзину": add(id); System.out.println("add"); break;
                           case "Удалить": remove(id); System.out.println("del"); break;
                           default: System.out.println("no action");
                  }
         }
         
         private void add(int id) {
                  if(checkBasket(id)){
                           Product p = Service.getServ().getProduct(id);
                           p.setAmount(amount);
                           p.setSize(size);
                           
                           basket.add(p);
                  }
                  
                  size = 0;
                  amount = 0;
         }
         
         private void remove(int id) {
                  Product selectedProd = Service.getServ().getProduct(id);
                  Product removedProd = null;
                  
                  for(Product p : basket) {
                           if(p.getId() == selectedProd.getId()) {
                                    removedProd = p;
                           }
                  }
                  
                  basket.remove(removedProd);
         }       

         private boolean checkBasket(int id) {
                  
                  for(Product p : basket) {
                           if(p.getId() == id) {
                                    return false;
                           }
                  }
                  return true;
         }

         public int getSize() {
                  return size;
         }

         public void setSize(int size) {
                  this.size = size;
         }

         public int getAmount() {
                  return amount;
         }
         
         public void setAmount(int amount) {
                  this.amount = amount;
         }
}
