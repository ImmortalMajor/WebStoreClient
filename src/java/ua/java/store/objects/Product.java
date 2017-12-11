package ua.java.store.objects;

import java.io.Serializable;

public class Product implements Serializable {

         private int id;
         private String name;
         private String type;
         private String manuf;
         private int cost;
         private double discont;
         private int size;
         private int amount;
         private String image;
         private String desc;

         public int getId() {
                  return id;
         }

         public void setId(int id) {
                  this.id = id;
         }

         public String getName() {
                  return name;
         }

         public void setName(String name) {
                  this.name = name;
         }

         public String getType() {
                  return type;
         }

         public void setType(String type) {
                  this.type = type;
         }

         public String getManuf() {
                  return manuf;
         }

         public void setManuf(String manuf) {
                  this.manuf = manuf;
         }

         public int getCost() {
                  return cost;
         }

         public void setCost(int cost) {
                  this.cost = cost;
         }

         public double getDiscont() {
                  return discont;
         }

         public void setDiscont(double discont) {
                  this.discont = discont;
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

         public String getImage() {
                  return image;
         }

         public void setImage(String image) {
                  this.image = image;
         }

         public String getDesc() {
                  return desc;
         }

         public void setDesc(String desc) {
                  this.desc = desc;
         }

}
