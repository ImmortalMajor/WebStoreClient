package web.java.controllers;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import web.java.functions.Messenger;

@ManagedBean(name = "mes")
@RequestScoped
public class Message {
         
         private String message = "";
         private String email = "";
         private String fio = "";

         public void sendMessage() {
                  if(!check()) return;
                  Messenger mes = new Messenger();
                  
                  String full = getFio() + ". " + getMessage();
                  mes.setEmail(email);
                  mes.setFullMesage(full);
                  mes.send();
                  
                  System.out.println("-------------------------------------------message sent");
         }
         
         private boolean check() {
                  System.out.println("check");

                  if (fio.equals("") || email.equals("") || message.equals("")) {
                           return false;
                  }

                  System.out.println("its not empty");

                  if (!reg("^(\\w+@[a-zA-Z_]+?\\.[a-zA-Z]{2,6})$", email)
                          || !reg("^([A-ZА-Яa-zа-я_ ]{1,})$", fio)) {
                           return false;
                  }

                  System.out.println("regulars passed");
                  return true;
         }

         private boolean reg(String form, Object field) {
                  Pattern p = Pattern.compile(form);
                  Matcher m = p.matcher(String.valueOf(field));

                  System.out.println(field + " ,,,, " + m.matches());
                  return m.matches();
         }

         public String getMessage() {
                  return message;
         }

         public void setMessage(String message) {
                  this.message = message;
         }
         
         public String getEmail() {
                  return email;
         }

         public void setEmail(String email) {
                  this.email = email;
         }

         public String getFio() {
                  return fio;
         }

         public void setFio(String fio) {
                  this.fio = fio;
         }
}
