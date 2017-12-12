package web.java.controllers;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import web.java.service.Product;

@ManagedBean(name = "basket")
@SessionScoped
public class BasketList implements java.io.Serializable {
         
         private List<Product> basket;
         
         @PostConstruct
         public void init() {
                  if(basket == null || basket.size() < 1) {basket = new ArrayList();}
         }

         public List<Product> getBasket() {
                  return basket;
         }

         public void setBasket(List<Product> basket) {
                  this.basket = basket;
         }
         
         public void addToBasket(Product p) {
                  basket.add(p);
         }
         
         public void removeFromBasket(Product p) {
                  basket.remove(p);
         }
         
}
