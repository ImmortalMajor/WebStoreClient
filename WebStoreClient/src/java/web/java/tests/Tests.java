package web.java.tests;

import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

@ManagedBean(name = "tests")
@RequestScoped
public class Tests {
         
         public boolean ifBasket() {
                  if(getParam("curProd") != null)
                           if(getParam("curProd").equals("basket")) return true;
                  
                  return false;
         }
         
         public boolean ifMain() {
                  if(getParam("curProd") != null)
                           if(getParam("curProd").equals("main")) return true;
                  
                  return false;
         }
         
         private String getParam(String param) {
                  
                  Map<String, String> params = 
                           FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
                  
                  return params.get(param);
         }
}
