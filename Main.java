import java.sql.*;

public class Main {
    public static void main(String[] args) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/java_proj_college_predictor", "root","#KAR331@tikNP");
//            String name = "nishnat";
//            String pass= "shinden";
//            String email = "nishant@gmail.com";
//            SearchInstitute u = new SearchInstitute();
//            u.setUsername(name);
//            u.setPassword(pass);
//            if(u.Login(connection)) {
//                u.searchCollege(connection);
//            }else {
//                System.out.println("no");
//            }
            
            Admin a = new Admin();
            a.getUser(connection);
//            if(a.removeInstitute(connection,"Malaviya National Institute of Technology Jaipur"))
//            {
//                System.out.println("'Malaviya National Institute of Technology Jaipur' removed successfully from all rounds");
//            }
//            else {
//                System.out.println("no");
//            }
            
//            a.addNewAdmin("letap","patelkn1303@gmail.com","knp13");
//            a.getAdmins(connection);
//            User u = new User("harsh","harsh@yahoo.com","chaudhari","open","male",2000,2000);
//            u.Register(connection);
//            SearchInstitute s = new SearchInstitute("harsh","chaudhari");
//            if (s.Login(connection))
//            {
//                s.IncognitoSearch(connection);
//            }
            

            connection.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}