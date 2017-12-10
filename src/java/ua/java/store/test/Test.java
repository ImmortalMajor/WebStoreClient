package ua.java.store.test;

import java.sql.Connection;
import java.util.ArrayList;
import ua.java.store.db.Connector;
import ua.java.store.db.DBCheck;
import ua.java.store.db.DBProduct;
import ua.java.store.db.DBUser;
import ua.java.store.objects.Check;
import ua.java.store.objects.Product;
import ua.java.store.objects.User;

public class Test {
    
    public Test(){
        Connection con = Connector.getInstance().getConnection();
            if(con != null){ 
                System.out.println("!!!---------CONNECTION OPENED---------!!!"); 
                
                Check listC = DBCheck.getInstance().getCheck(1);
                System.out.println("----- All checks = " + DBCheck.getInstance().getChecks() + " -----");
                
                for(int i = 0; i < listC.getProducts().size(); i++)
                    System.out.println("Check col = " + i + " : " + listC.getId() + " " + listC.getName_u() + " "
                    + listC.getProducts().get(i).getId() + " " + listC.getProducts().get(i).getName() + " "
                    + listC.getProducts().get(i).getType() + " " + listC.getProducts().get(i).getManuf() + " "
                    + listC.getProducts().get(i).getCost() + " " + listC.getProducts().get(i).getSize() + " "
                    + listC.getProducts().get(i).getDiscont() + " " + listC.getProducts().get(i).getAmount() + " "
                    + listC.getFullcost() + "---------------");
                
                ArrayList<Product> listP = DBProduct.getInstance().getProducts();
                for(int i = 0; i < listP.size(); i++)
                    System.out.println("Products : " + listP.get(i).getName());
                
                
                ArrayList<User> listU = DBUser.getInstance().getUsers();
                for(int i = 0; i < listU.size(); i++)
                    System.out.println("Users : " + listU.get(i).getName());
                
                //DBUser.getInstance().deleteUser(3); DONE
                
                
                //DBCheck.getInstance().insertCheck(1, listP); DONE
            }
    }
    
}
