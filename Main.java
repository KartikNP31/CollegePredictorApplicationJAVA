import PERSON.Admin;
import PERSON.User;

import java.sql.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {

//            System.out.println("Enter your JDBC Password");
//            String jdbcPassword;
//            jdbcPassword =scanner.next();
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/java_proj_college_predictor", "root","mh153599");
            System.out.println("Welcome To JAVA College Predictor Application");
            
            
            
            
            
            
            
//            String name = "harsh";
//            String pass= "chaudhari";
//            SearchInstitute u = new SearchInstitute();
//            u.setUsername(name);
//            u.setPassword(pass);
//            if(u.Login(connection)) {
//               u.deleteAccount(connection);
//            }else {
//                System.out.println("no");
//            }
//
//            String name1 = "sanket";
//            String pass1= "raut";
//            SearchInstitute u1 = new SearchInstitute();
//            u1.setUsername(name1);
//            u1.setPassword(pass1);
//            if(u1.Login(connection)) {
//               u1.deleteAccount(connection);
//            }else {
//                System.out.println("no");
//            }
//



//            Admin ad= new Admin();
//            ad.UploadDeletedUserCSV(connection);
//            ad.getUserList(connection);
//            User u = new User("Akash","pass@123");
//            if(u.Login(connection))
//            {
//                u.UpdateUserDetails(connection);
//                System.out.println("yes");
//            }
//            else {
//                System.out.println("no");
//            }




           // User a = new User();
//            a.getAllBranch(connection);
//
            Admin a = new Admin();
            a.UploadDeletedUserCSV(connection);
//            a.getUserList(connection);
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
//           User u = new User("manthan","manthan@yahoo.com","pune","open","male",200,3000);
//           u.Register(connection);
//            if(u.Login(connection))
//            {
////            {    boolean check=true;
////                while (check) {
////
////                    System.out.println("Enter Choice");
////                    int select = scanner.nextInt();
////
////                    switch (select) {
////                        case 1:
//                            u.resetPassword(connection,scanner);
////                            break;
////
////
////                        case 2:
////                            u.deleteAccount(connection,scanner);
////                            break;
////
////                        case 3:
//                            u.UpdateUserDetails(connection,scanner);
////                            break;
////
////                        case 4: check=false;
////                                break;
////                    }
////                }
//
////
//
//                u.deleteAccount(connection,scanner);
//            }
//            else System.out.println("NO");

//            SearchInstitute s = new SearchInstitute("harsh","chaudhari");
//            if (s.Login(connection))
//            {
//                s.IncognitoSearch(connection);
//            }
            
            
            
            
            
            

            connection.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        scanner.close();
    }
}