import PERSON.*;
import PERSON.USER.*;

import java.sql.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);
//            System.out.println("Enter your JDBC Password");
//            String jdbcPassword;
//            jdbcPassword =scanner.next();
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/java_proj_college_predictor", "root","#KAR331@tikNP");
            System.out.println("Welcome To JAVA College Predictor Application");
            
            
            
            
            
            
            
//            String name = "Kartik";
//            String pass= "letapK";
//            String email = "kartik@gmail.com";
//            SearchInstitute u = new SearchInstitute();
//            u.setUsername(name);
//            u.setPassword(pass);
//            if(u.Login(connection)) {
//               u.deleteAccount(connection);
//            }else {
//                System.out.println("no");
//            }


//            Admin ad= new Admin("Kartik","letap_kartik");
//            {
//                if(ad.Login(connection))
//                {
//                    ad.addNewAdmin(connection,"Nishant","nishant@gmail.com","shinde");
//                    ad.addNewAdmin(connection,"manthan","manthan@gmail.com","pune");
//
//                    ad.getAdminList(connection);
//
//                    ad.removeAdmin(connection,"manthan","manthan@gmail.com");
//
//                    ad.getUserList(connection,scanner);
//                    ad.getUser(connection,"harsh","harsh@gmail.com");
//                    ad.removeUser(connection,"harsh","harsh@gmail.com");
//                    ad.getUserList(connection,scanner);
//
//                    ad.getAllInstitute(connection);
//                    if(ad.removeInstitute(connection,"Maulana Azad National Institute of Technology Bhopal"))
//                    {
//                        System.out.println("You successfully removed , Maulana Azad National Institute of Technology Bhopal");
//                    }
//                    ad.getAllBranch(connection);
//
//
//                }
//            }
            
            
            
           // User a = new User();
//            a.getAllBranch(connection);
            
//            Admin a = new Admin();
//            a.getUser(connection);
//            if(a.removeInstitute(connection,"Malaviya National Institute of Technology Jaipur"))
//            {
//                System.out.println("'Malaviya National Institute of Technology Jaipur' removed successfully from all rounds");
//            }
//            else {
//                System.out.println("no");
//            }
            
//            a.addNewAdmin("letap","patelkn1303@gmail.com","knp13");
//            a.getAdmins(connection);
//            User u3 = new User("harsh","harsh@yahoo.com","chaudhari","open","male",2000,2000);
//            u3.Register(connection);
//            User u3 = new User("sanket","sanket@yahoo.com","raut","obc","male",522,2015);
//            u3.Register(connection);
    
            Admin a = new Admin();
            
            a.removeUser(connection,"shrutika","shrutika@yahoo.com");
            User u3 = new User("shrutika","shrutika@yahoo.com","312004","obc","female",546,5366);
            u3.Register(connection);
            if(u3.Login(connection))
            {
                u3.resetPassword(connection,scanner);

                u3.UpdateUserDetails(connection,scanner);


                
            }
            
            
//            SearchInstitute s = new SearchInstitute("s","shinden");
//            if (s.Login(connection))
//            {
//                s.searchCollege(connection,scanner);
//            }
    
            
            a.getUserList(connection,scanner);
            a.getUser(connection,"shrutika","shrutika@yahoo.com");
            u3.deleteAccount(connection,scanner);
            a.UploadDeletedUserCSV(connection);
            
            

            connection.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}