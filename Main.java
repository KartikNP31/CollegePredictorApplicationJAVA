import PERSON.*;
import PERSON.USER.*;

import java.sql.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);
           System.out.println("Enter your JDBC Password");
           String jdbcPassword;
           jdbcPassword =scanner.next();
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/java_proj_college_predictor", "root",jdbcPassword);
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
            
            

            

            connection.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
