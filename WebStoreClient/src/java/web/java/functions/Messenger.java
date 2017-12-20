package web.java.functions;

import java.util.Date;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.mail.Message;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.naming.InitialContext;
import javax.naming.NamingException;

@ManagedBean(name = "mes")
@RequestScoped
public class Messenger {
         
         private final String MAIL_SUPPORT_PARAM = "mail.SUPPORT";
         private String message = "";
         private String email = "";
         private String fio = "";
         private String fullMessage = "";
         
         public void send() {
                  try {

                           String mailRecipient = FacesContext.getCurrentInstance().getExternalContext().getInitParameter(MAIL_SUPPORT_PARAM);

                           Message msg = new MimeMessage(getSession());

                           InternetAddress to = new InternetAddress(mailRecipient);
                           //InternetAddress from = new InternetAddress(email);
                           
                           msg.setRecipient(RecipientType.TO, to);
                           //msg.setFrom(from);
                           if(fullMessage.equals(""))
                                    fullMessage = getFio() + ". " + getMessage();
                           
                           msg.setText(fullMessage);
                           msg.setSubject(getEmail());
                           
                           Transport.send(msg);

                  } catch (NamingException ex) {
                           ex.printStackTrace();
                  } catch (MessagingException me) {
                           me.printStackTrace();
                  }

         }
         
         private Session getSession() throws NamingException {
                  InitialContext ic = new InitialContext();
                  return (javax.mail.Session) ic.lookup("java:comp/env/mail/GMAIL");
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

         public String getFullMesage() {
                  return fullMessage;
         }

         public void setFullMesage(String fullMessage) {
                  this.fullMessage = fullMessage;
         }
}
