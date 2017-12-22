package ua.java.store.objects;

import java.io.Serializable;
import java.util.ArrayList;

public class Check implements Serializable {

         private int id;
         private String name_u;
         private String phon_u;
         private ArrayList<Product> products;
         private int fullcost;

         public int getId() {
                  return id;
         }

         public void setId(int id) {
                  this.id = id;
         }

         public String getName_u() {
                  return name_u;
         }

         public void setName_u(String name_u) {
                  this.name_u = name_u;
         }

         public int getFullcost() {
                  return fullcost;
         }

         public void setFullcost(int fullcost) {
                  this.fullcost = fullcost;
         }

         public ArrayList<Product> getProducts() {
                  return products;
         }

         public void setProducts(ArrayList<Product> products) {
                  this.products = products;
         }

         public String getPhon_u() {
                  return phon_u;
         }

         public void setPhon_u(String phon_u) {
                  this.phon_u = phon_u;
         }

}
