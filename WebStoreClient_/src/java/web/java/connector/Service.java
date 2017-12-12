package web.java.connector;

import web.service.ServiceStore;
import web.service.ServiceStore_Service;

public class Service {
         
         private static final ServiceStore_Service ss_serv = new ServiceStore_Service();
         private static ServiceStore serv;

         public static ServiceStore getServ() {

                  if (serv == null) {
                           serv = ss_serv.getServiceStorePort();
                  }

                  return serv;
         }
         
}
