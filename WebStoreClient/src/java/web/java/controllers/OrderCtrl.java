package web.java.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import web.java.functions.MakeCheck;
import web.java.functions.Messenger;
import web.java.service.Product;

@ManagedBean(name = "order")
@RequestScoped
public class OrderCtrl {
         
         private String fio = "";
         private String email = "";
         private String address = "";
         private String phone = "";
         
         public String makeOrder(List<Product> basket) {
                  
                  try {
                           if(!check() || basket.isEmpty()) return "no";
                           
                           Messenger mes = new Messenger();
                           Date date = new Date();
                           String message = fio + ". Был сделан заказ " + date.toString()
                                   + ". Он будет доставлен по адресу: " + address + ". Контактный номер: " + phone + ".";
                           
                           ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
                           ec.redirect(((HttpServletRequest) ec.getRequest()).getRequestURI());
                           
                           mes.setEmail(email);
                           mes.setFullMesage(message);
                           mes.send();
                           
                           MakeCheck makeCheck = new MakeCheck(fio, phone, new ArrayList<>(basket));
                           
                           System.out.println("-------------------------------------------order maked");
                           return "yes";
                  } catch (IOException ex) {
                           Logger.getLogger(OrderCtrl.class.getName()).log(Level.SEVERE, null, ex);
                  }
                  
                  return "no";
         }
         
         private boolean check() {
                  System.out.println("check");
                  
                  if(fio.equals("") || email.equals("") || address.equals("") || phone.equals("")) return false;
                  
                  System.out.println("its not empty");
                  
                  if(!reg("^(\\w+@[a-zA-Z_]+?\\.[a-zA-Z]{2,6})$", email) ||
                     !reg("^([A-ZА-Яa-zа-я_ ]{1,})$", fio) ||
                     !reg("^(\\+38([0-9]{3})[0-9]{3}[0-9]{4})$", phone)) return false;
                  
                  System.out.println("regulars passed");
                  return true;
         }
         
         private boolean reg(String form, Object field) {
                  Pattern p = Pattern.compile(form);
                  Matcher m = p.matcher(String.valueOf(field));
                  
                  System.out.println(field + " ,,,, " + m.matches());
                  return m.matches();
         }

         public String getFio() {
                  return fio;
         }

         public void setFio(String fio) {
                  this.fio = fio;
         }

         public String getEmail() {
                  return email;
         }

         public void setEmail(String email) {
                  this.email = email;
         }

         public String getAddress() {
                  return address;
         }

         public void setAddress(String address) {
                  this.address = address;
         }

         public String getPhone() {
                  return phone;
         }

         public void setPhone(String phone) {
                  this.phone = phone;
         }
         
}
